package net.etfbl.hcc.controller.recepcionar.tabs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistracijaRecepcionarController {

    @FXML
    private TextField tfUsername;
    
    @FXML
    private PasswordField pfPassword;

    @FXML
    private TextField tfIme;

    @FXML
    private TextField tfPrezime;

    @FXML
    private TextField tfBrojTelefona;

    @FXML
    private ChoiceBox<Integer> cbBrojKreveta;

    @FXML
    private DatePicker dpDatumOd;

    @FXML
    private DatePicker dpDatumDo;

    @FXML
    private Button btnConfirm;

    @FXML
    private Button btnClear;
    
    @FXML
    void handleConfirm(ActionEvent event) {

    }
    
    @FXML
    void handleClear(ActionEvent event) {

    }

}
