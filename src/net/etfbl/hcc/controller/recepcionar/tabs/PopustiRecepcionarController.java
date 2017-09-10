package net.etfbl.hcc.controller.recepcionar.tabs;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import net.etfbl.hcc.view.recepcionar.Dialogs;

public class PopustiRecepcionarController {
	
	private ObservableList<PopustTest> list;

    @FXML
    private TableView<PopustTest> table;

    @FXML
    private TableColumn<PopustTest, String> colKod;

    @FXML
    private TableColumn<PopustTest, String> colProcenat;

    @FXML
    private TableColumn<PopustTest, String> colAktivan;

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
    	// Set cell value factories
    	colKod.setCellValueFactory(
    			param -> new SimpleStringProperty(param.getValue().getKod()));
    	colProcenat.setCellValueFactory(
    			param -> new SimpleStringProperty(param.getValue().getProcenat() + " %"));
    	colAktivan.setCellValueFactory(
    			param -> new SimpleStringProperty(param.getValue().getAktivan() ? "Da" : "Ne"));
    	
    	// Populate table
    	list = FXCollections.observableArrayList();
    	list.addAll(
    			new PopustTest("001", 10.0, true),
    			new PopustTest("002", 15.0, true),
    			new PopustTest("003", 30.0, true));
    	table.setItems(list);
    }

    @FXML
    void handleDodaj(ActionEvent event) {
    	if (inputValid()) {
    		PopustTest popust = new PopustTest();
    		popust.setKod(tfKod.getText());
    		popust.setProcenat(Double.valueOf(tfProcenat.getText()));
    		popust.setAktivan(true);
    		list.add(popust);
    		clearFields();
    	}
    }

    @FXML
    void handleObrisi(ActionEvent event) {
    	PopustTest popust = table.getSelectionModel().getSelectedItem();
    	if (popust != null) {
    		list.remove(popust);
    	}
    }
    
    private void clearFields() {
    	tfKod.setText("");
    	tfProcenat.setText("");
    }
    
    /**
     * Validates the user input in the text fields.
     */
    private boolean inputValid() {
    	String message = "";
    	
    	if (tfKod.getText() == null | tfKod.getText().isEmpty()) {
    		message += "Nedostaje kod.\n";
    	}
    	
    	try {
    		Double procenat = Double.valueOf(tfProcenat.getText());
    		if (procenat <= 0 || procenat >= 100) {
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

class PopustTest {
	
	private String kod;
	private Double procenat;
	private Boolean aktivan;
	
	public PopustTest() {
		super();
	}

	public PopustTest(String kod, Double procenat, Boolean aktivan) {
		super();
		this.kod = kod;
		this.procenat = procenat;
		this.aktivan = aktivan;
	}

	public String getKod() {
		return kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}

	public Double getProcenat() {
		return procenat;
	}

	public void setProcenat(Double procenat) {
		this.procenat = procenat;
	}

	public Boolean getAktivan() {
		return aktivan;
	}

	public void setAktivan(Boolean aktivan) {
		this.aktivan = aktivan;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((kod == null) ? 0 : kod.hashCode());
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
		PopustTest other = (PopustTest) obj;
		if (kod == null) {
			if (other.kod != null)
				return false;
		} else if (!kod.equals(other.kod))
			return false;
		return true;
	}
	
}
