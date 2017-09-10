package net.etfbl.hcc.controller.recepcionar.tabs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class ProizvodiRecepcionarController {

    @FXML
    private TableColumn<?, ?> colNaziv;

    @FXML
    private TableColumn<?, ?> colCijena;

    @FXML
    private TableColumn<?, ?> colTip;
    
    @FXML
    private ComboBox<?> cmbTip;

    @FXML
    private TextField tfNaziv;

    @FXML
    private TextField tfCijena;

    @FXML
    private Button btnDodaj;

    @FXML
    private Button btnObrisi;

    @FXML
    void handleDodaj(ActionEvent event) {

    }

    @FXML
    void handleObrisi(ActionEvent event) {

    }

}
