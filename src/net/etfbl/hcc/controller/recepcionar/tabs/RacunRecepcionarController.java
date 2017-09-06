package net.etfbl.hcc.controller.recepcionar.tabs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class RacunRecepcionarController {

    @FXML
    private TextField tfKorisnickoIme;

    @FXML
    private Button btnPrikazi;

    @FXML
    private TableView<Object> table;

    @FXML
    private TableColumn<Object, String> colNaziv;

    @FXML
    private TableColumn<Object, Double> colCijena;

    @FXML
    private TableColumn<Object, String> colDatum;

    @FXML
    private Label lblUkupnaCijena;

    @FXML
    private Button btnPlati;

    @FXML
    void handlePlati(ActionEvent event) {
    	
    }

    @FXML
    void handlePrikazi(ActionEvent event) {
    	if (!ucitajRacun(tfKorisnickoIme.getText())) {
    		Alert dialog = new Alert(AlertType.INFORMATION);
    		dialog.setTitle("Greska");
    		dialog.setHeaderText( "Nepostojeci korisnik");
    		dialog.setContentText("Ne postoji korisnik sa imenom \"" + tfKorisnickoIme.getText() + "\".");
    		dialog.showAndWait();
    	}
    }
    
    public boolean ucitajRacun(String username) {
    	return false;  
    }

}
