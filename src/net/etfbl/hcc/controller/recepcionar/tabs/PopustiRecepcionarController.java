package net.etfbl.hcc.controller.recepcionar.tabs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    		Alert dialog = new Alert(AlertType.INFORMATION);
    		dialog.setTitle("Greska");
    		dialog.setHeaderText("Nevalidna vrijednost");
    		dialog.setContentText("Unesite broj izmedju 0 i 100.");
    		dialog.showAndWait();
    	}
    }

    @FXML
    void handleObrisi(ActionEvent event) {

    }

}
