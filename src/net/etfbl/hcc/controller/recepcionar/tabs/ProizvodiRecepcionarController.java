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
import net.etfbl.hcc.view.recepcionar.Dialogs;

public class ProizvodiRecepcionarController {

	private ObservableList<ProizvodTest> list;
	
	@FXML
	private TableView<ProizvodTest> table;

	@FXML
	private TableColumn<ProizvodTest, String> colNaziv;

	@FXML
	private TableColumn<ProizvodTest, String> colCijena;

	@FXML
	private TableColumn<ProizvodTest, String> colTip;

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
		// Set cell value factories
		colNaziv.setCellValueFactory(
				param -> new SimpleStringProperty(param.getValue().getNaziv()));
		colCijena.setCellValueFactory(
				param -> new SimpleStringProperty(String.format("%.2f", param.getValue().getCijena())));
		colTip.setCellValueFactory(
				param -> new SimpleStringProperty(param.getValue().getTip()));
		
		// Set some predefined values into combo box
		cmbTip.getItems().addAll("Prehrambeni proizvod", "Higijensko sredstvo");
		
		// Populate table
		list = FXCollections.observableArrayList();
		list.addAll(
				new ProizvodTest("Proizvod1", "Tip1", 10.0),
				new ProizvodTest("Proizvod2", "Tip2", 15.0),
				new ProizvodTest("Proizvod3", "Tip3", 10.0),
				new ProizvodTest("Proizvod4", "Tip4", 5.0),
				new ProizvodTest("Proizvod5", "Tip5", 3.99));
		table.setItems(list);
	}

	@FXML
	void handleDodaj(ActionEvent event) {
		if (inputValid()) {
			ProizvodTest proizvod = new ProizvodTest();
			proizvod.setNaziv(tfNaziv.getText());
			proizvod.setCijena(new Double(tfCijena.getText()));
			proizvod.setTip(cmbTip.getValue());
			list.add(proizvod);
			clearFields();
		}
	}

	@FXML
	void handleObrisi(ActionEvent event) {
		ProizvodTest proizvod = table.getSelectionModel().getSelectedItem();
		if (proizvod != null) {
			list.remove(proizvod);
		}
	}
	
	private void clearFields() {
		tfNaziv.setText("");
		tfCijena.setText("");
		cmbTip.setValue("");
	}
	
	/**
     * Validates the user input in the text fields.
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

class ProizvodTest {

	private static int counter;
	private Integer id;
	private String naziv;
	private String tip;
	private Double cijena;

	public ProizvodTest() {
		super();
		counter++;
		id = counter;
	}

	public ProizvodTest(String naziv, String tip, Double cijena) {
		super();
		this.naziv = naziv;
		this.tip = tip;
		this.cijena = cijena;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public Double getCijena() {
		return cijena;
	}

	public void setCijena(Double cijena) {
		this.cijena = cijena;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProizvodTest other = (ProizvodTest) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
