package net.etfbl.hcc.controller.recepcionar.tabs;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import net.etfbl.hcc.Client;
import net.etfbl.hcc.model.Proizvod;
import net.etfbl.hcc.view.recepcionar.Dialogs;

public class ProizvodiRecepcionarController implements RefreshableController {

	private ObservableList<Proizvod> list;
	
	@FXML
	private TableView<Proizvod> table;

	@FXML
	private TableColumn<Proizvod, String> colNaziv;

	@FXML
	private TableColumn<Proizvod, String> colCijena;

	@FXML
	private TableColumn<Proizvod, String> colTip;

	@FXML
	private ComboBox<String> cmbTip;

	@FXML
	private TextField tfNaziv;

	@FXML
	private TextField tfCijena;

	@FXML
	private Button btnDodaj;

	@FXML
	private Button btnObrisi;

	@FXML
	void initialize() {
		
		colNaziv.setCellValueFactory(
				new PropertyValueFactory<>("naziv"));
		colCijena.setCellValueFactory(param -> 
				new SimpleStringProperty(String.format("%.2f", param.getValue().getCijena())));
		colTip.setCellValueFactory(
				new PropertyValueFactory<>("tip"));
		
		cmbTip.getItems().addAll("Hrana", "Pice", "Higijensko sredstvo");

		refresh();
	}

	@FXML
	void handleDodaj(ActionEvent event) {
		if (inputValid()) {
			Proizvod proizvod = new Proizvod();
			proizvod.setNaziv(tfNaziv.getText());
			proizvod.setCijena(new Double(tfCijena.getText()));
			proizvod.setTip(cmbTip.getValue());
			int idProizvoda = 0;
			if ((idProizvoda = Client.getInstance().dodajProizvod(proizvod)) != -1) {
				proizvod.setIdProizvoda(idProizvoda);
				list.add(proizvod);
				clearFields();
			}
		}
	}

	@FXML
	void handleObrisi(ActionEvent event) {
		Proizvod proizvod = table.getSelectionModel().getSelectedItem();
		if (proizvod != null && Client.getInstance().obrisiProizvod(proizvod)) {
			list.remove(proizvod);
		}
	}
	
	@Override
	public void refresh() {
		list = FXCollections.observableArrayList(Client.getInstance().getProizvodi());
		table.setItems(list);
	}
	
	/*
     * Postavlja vrijednosti polja na podrazumijevane.
     */
	private void clearFields() {
		tfNaziv.setText("");
		tfCijena.setText("");
		cmbTip.setValue("");
	}
	
	/*
     * Pomocna metoda za provjeru da li su uneseni podaci ispravni.
     */
    private boolean inputValid() {
    	String message = "";
    	
    	if (tfNaziv.getText() == null | tfNaziv.getText().isEmpty()) {
    		message += "Nedostaje naziv proizvoda.\n";
    	}
    	
    	try {
    		Double cijena = Double.valueOf(tfCijena.getText());
    		if (cijena < 0) {
    			throw new NumberFormatException();
    		}
    	} catch (NumberFormatException ex) {
    		message += "Cijena mora biti pozitivan broj ili 0.\n";
    	}
    	
    	if (cmbTip.getValue() == null || cmbTip.getValue().trim().isEmpty()) {
    		message += "Nedostaje tip proizvoda.\n";
    	}
    	
    	if (message.isEmpty()) {
    		return true;
    	} else {
    		Dialogs.showErrorDialog("Greska", "Ispravite sljedeca polja:", message);
    		return false;
    	}
    }

}
