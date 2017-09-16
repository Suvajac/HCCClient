package net.etfbl.hcc.controller.recepcionar.tabs;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import net.etfbl.hcc.Client;
import net.etfbl.hcc.model.Popust;
import net.etfbl.hcc.view.recepcionar.Dialogs;

public class PopustiRecepcionarController implements RefreshableController {

	private ObservableList<Popust> list;

	@FXML
	private TableView<Popust> table;

	@FXML
	private TableColumn<Popust, String> colKod;

	@FXML
	private TableColumn<Popust, String> colProcenat;

	@FXML
	private TableColumn<Popust, String> colAktivan;

	@FXML
	private TextField tfKod;

	@FXML
	private TextField tfProcenat;

	@FXML
	private Button btnDodaj;

	@FXML
	private Button btnObrisi;

	@FXML
	void initialize() {

		colKod.setCellValueFactory(
				param -> new SimpleStringProperty("" + param.getValue().getKodPopusta()));
		colProcenat.setCellValueFactory(
				param -> new SimpleStringProperty(param.getValue().getProcenat() + " %"));
		colAktivan.setCellValueFactory(
				param -> new SimpleStringProperty(param.getValue().isAktivan() ? "Da" : "Ne"));

		refresh();
	}

	@FXML
	void handleDodaj(ActionEvent event) {
		if (inputValid()) {
			Popust popust = new Popust();
			popust.setKodPopusta(Integer.valueOf(tfKod.getText()));
			popust.setProcenat(Double.valueOf(tfProcenat.getText()));
			popust.setAktivan(true);
			if (!list.contains(popust)) { // Zabrani dodavanje popusta sa istim kodom
				if (Client.getInstance().dodajPopust(popust)) {
					list.add(popust);
					clearFields();
				} else {
					Dialogs.showErrorDialog("Greska", "Greska", "Desila se greska prilikom dodavanja popusta.");
				}
			} else {
				Dialogs.showErrorDialog("Greska", "Nevalidan kod", "Popust sa datim kodom vec postoji.");
			}

		}
	}

	@FXML
	void handleObrisi(ActionEvent event) {
		Popust popust = table.getSelectionModel().getSelectedItem();
		if (popust != null) {
			ButtonType type = Dialogs.showConfirmationDialog("Potvrda", "Potvrda",
					"Da li zaista zelite da obrisete dati popust?");
			if (ButtonType.OK.equals(type)) {
				if (Client.getInstance().obrisiPopust(popust)) {
					list.remove(popust);
				} else {
					Dialogs.showErrorDialog("Greska", "Greska", "Desila se greska prilikom brisanja popusta.");
				}
			}
		}
	}

	@Override
	public void refresh() {
		list = FXCollections.observableArrayList(Client.getInstance().getPopusti());
		table.setItems(list);
	}

	/*
	 * Postavlja vrijednosti polja na podrazumijevane.
	 */
	private void clearFields() {
		tfKod.setText("");
		tfProcenat.setText("");
	}

	/*
	 * Pomocna metoda za provjeru da li su uneseni podaci ispravni.
	 */
	private boolean inputValid() {
		String message = "";

		try {
			Integer kodPopusta = Integer.valueOf(tfKod.getText());
			if (kodPopusta < 0) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException ex) {
			message += "Kod mora biti pozitivan broj ili 0.\n";
		}

		try {
			Double procenat = Double.valueOf(tfProcenat.getText());
			if (procenat <= 0 || procenat > 100) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException ex) {
			message += "Procenat mora biti broj izmedju 0 i 100.\n";
		}

		if (message.isEmpty()) {
			return true;
		} else {
			Dialogs.showErrorDialog("Greska", "Ispravite sljedeca polja:", message);
			return false;
		}
	}

}
