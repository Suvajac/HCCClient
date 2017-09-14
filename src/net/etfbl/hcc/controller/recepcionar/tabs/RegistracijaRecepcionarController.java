package net.etfbl.hcc.controller.recepcionar.tabs;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.etfbl.hcc.Client;
import net.etfbl.hcc.model.Gost;
import net.etfbl.hcc.model.Registracija;
import net.etfbl.hcc.model.Soba;
import net.etfbl.hcc.util.TemporalStringConverters;
import net.etfbl.hcc.view.recepcionar.Dialogs;

public class RegistracijaRecepcionarController {
	
	private ObservableList<Soba> slobodneSobe;

	@FXML
	private TextField tfUsername;

	@FXML
	private PasswordField pfPassword;

	@FXML
	private PasswordField pfConfirmPassword;

	@FXML
	private TextField tfIme;

	@FXML
	private TextField tfPrezime;

	@FXML
	private TextField tfBrojTelefona;

	@FXML
	private ComboBox<Integer> cmbBrojKreveta;
	
	@FXML
	private ComboBox<Soba> cmbBrojSobe;

	@FXML
	private DatePicker dpDatumOd;

	@FXML
	private DatePicker dpDatumDo;

	@FXML
	private Button btnPotvrdi;
	
	@FXML
	private Button btnZatvori;

	@FXML
	void initialize() {
		
		slobodneSobe = FXCollections.observableArrayList(Client.getInstance().getSlobodneSobe());
		
		List<Integer> list = slobodneSobe.stream().
										mapToInt(e -> e.getBrKreveta()).
										distinct().
										boxed().
										collect(Collectors.toList());
									
		cmbBrojKreveta.setItems(FXCollections.observableArrayList(list));
		
		cmbBrojSobe.setCellFactory(c -> new ListCell<Soba>() {
			@Override
			protected void updateItem(Soba item, boolean empty) {
				super.updateItem(item, empty);
				if (item != null && !empty) {
					setText("" + item.getBrSobe());
				}
			}
		});
		cmbBrojSobe.setButtonCell(cmbBrojSobe.getCellFactory().call(null));
		
		dpDatumOd.setConverter(TemporalStringConverters.getLocalDateConverter());
		dpDatumDo.setConverter(TemporalStringConverters.getLocalDateConverter());
		clearFields();
	}
	
	@FXML
	void handleBrojKrevetaSelected(ActionEvent event) {
		updateBrojSobeComboBox();
	}

	@FXML
	void handlePotvrdi(ActionEvent event) {
		if (inputValid()) {
			Gost gost = new Gost();
			gost.setUsername(tfUsername.getText());
			gost.setLozinkaHash("" + pfPassword.getText().hashCode());
			gost.setIme(tfIme.getText());
			gost.setPrezime(tfPrezime.getText());
			gost.setBrojTelefona(tfBrojTelefona.getText());

			Soba soba = cmbBrojSobe.getValue();
			
			List<Gost> gosti = GostiRecepcionarController.list.stream().
																map(e -> e.getGost()).
																collect(Collectors.toList());

			// Provjeri da li postoji gost sa datim username-om
			if (!gosti.contains(gost)) {
				Registracija registracija = new Registracija();
				registracija.setGost(gost);
				registracija.setSoba(soba);
				registracija.setDatumOd(dpDatumOd.getValue());
				registracija.setDatumDo(dpDatumDo.getValue());

				Registracija novaReg = null;
				if ((novaReg = Client.getInstance().dodajRegistraciju(registracija)) != null) {
					GostiRecepcionarController.list.add(novaReg);
					Dialogs.showInfoDialog("Informacija", "Informacija",
							"Korisnik \"" + gost.getUsername() + "\" uspjesno registrovan.");
					closeStage();
				}
				clearFields();
			} else {
				Dialogs.showErrorDialog("Greska", "Greska",
						"Gost sa korisnickim imenom \"" + gost.getUsername() + "\" vec postoji.");
			}

		}
		pfPassword.setText("");
		pfConfirmPassword.setText("");
	}
	
	@FXML
	void handleZatvori(ActionEvent event) {
		closeStage();
	}
	
	private void updateBrojSobeComboBox() {
		Integer selectedValue = cmbBrojKreveta.getValue();
		List<Soba> list = slobodneSobe.stream().
										filter(e -> selectedValue.equals(e.getBrKreveta())).
										collect(Collectors.toList());
		
		cmbBrojSobe.setItems(FXCollections.observableArrayList(list));
		cmbBrojSobe.setValue(cmbBrojSobe.getItems().get(0));
	}

	/*
	 * Postavlja vrijednosti polja na podrazumijevane.
	 */
	private void clearFields() {
		tfUsername.setText("");
		pfPassword.setText("");
		pfConfirmPassword.setText("");
		tfIme.setText("");
		tfPrezime.setText("");
		cmbBrojKreveta.setValue(cmbBrojKreveta.getItems().get(0));
		updateBrojSobeComboBox();
		dpDatumOd.setValue(LocalDate.now());
		dpDatumDo.setValue(dpDatumOd.getValue().plusDays(7));
	}
	
	/*
	 * Zatvara stage ovog controllera.
	 */
	private void closeStage() {
		((Stage) btnZatvori.getScene().getWindow()).close();
	}

	/*
	 * Pomocna metoda za provjeru da li su uneseni podaci ispravni.
	 */
	private boolean inputValid() {
		String message = "";

		if (tfUsername.getText() == null || tfUsername.getText().trim().isEmpty()) {
			message += "Nedostaje korisnicko ime.\n";
		}
		if (pfPassword.getText() == null || pfPassword.getText().length() < 8) {
			message += "Lozinka ne moze imati manje od 8 karaktera.\n";
		}
		if (!pfPassword.getText().equals(pfConfirmPassword.getText())) {
			message += "Lozinke se ne podudaraju.\n";
		}
		if (tfIme.getText() == null || tfIme.getText().trim().isEmpty()) {
			message += "Nedostaje ime.\n";
		}
		if (tfPrezime.getText() == null || tfPrezime.getText().trim().isEmpty()) {
			message += "Nedostaje prezime.\n";
		}
		if (tfBrojTelefona.getText() == null || tfBrojTelefona.getText().trim().isEmpty()) {
			message += "Nedostaje broj telefona.\n";
		}
		if (dpDatumOd.getValue().compareTo(dpDatumDo.getValue()) >= 0) {
			message += "Nevalidan datum.\n";
		}

		if (message.isEmpty()) {
			return true;
		} else {
			Dialogs.showErrorDialog("Greska", "Ispravite sljedeca polja:", message);
			return false;
		}
	}

}
