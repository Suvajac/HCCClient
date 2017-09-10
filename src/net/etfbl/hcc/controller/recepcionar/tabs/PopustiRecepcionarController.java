package net.etfbl.hcc.controller.recepcionar.tabs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import net.etfbl.hcc.view.recepcionar.Dialogs;

public class PopustiRecepcionarController {

    @FXML
    private TableView<Object> table;

    @FXML
    private TableColumn<Object, String> colKod;

    @FXML
    private TableColumn<Object, Double> colProcenat;

    @FXML
    private TableColumn<Object, String> colAktivan;

    @FXML
    private TextField tfKod;
    
    @FXML
    private TextField tfProcenat;

    @FXML
    private Button btnDodaj;

    @FXML
    private Button btnObrisi;

    @FXML
    void handleDodaj(ActionEvent event) {
    	if (inputValid()) {
    		
    	}
    }

    @FXML
    void handleObrisi(ActionEvent event) {

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
    		Double procenat = new Double(tfProcenat.getText());
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
