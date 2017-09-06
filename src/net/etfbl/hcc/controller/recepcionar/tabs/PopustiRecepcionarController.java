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
    private TextField tfProcenat;

    @FXML
    private Button btnDodaj;

    @FXML
    private Button btnObrisi;

    @FXML
    void handleDodaj(ActionEvent event) {
    	try {
    		Double procenat = new Double(tfProcenat.getText());
    		if (procenat <= 0 || procenat > 100) {
    			throw new NumberFormatException();
    		}
    	} catch (NumberFormatException ex) {
    		Dialogs.showErrorDialog("Greska", "Nevalidna vrijednost", "Unesite broj izmedju 0 i 100.");
    	}
    }

    @FXML
    void handleObrisi(ActionEvent event) {

    }

}
