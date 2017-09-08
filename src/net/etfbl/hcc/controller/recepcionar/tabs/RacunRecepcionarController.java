package net.etfbl.hcc.controller.recepcionar.tabs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import net.etfbl.hcc.view.recepcionar.Dialogs;

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
    private TextField tfUkupnaCijena;

    @FXML
    private Button btnPlati;
    
    @FXML
    void initialize() {
    	tfUkupnaCijena.prefWidthProperty().bind(colCijena.widthProperty());
    }

    @FXML
    void handlePlati(ActionEvent event) {
    	
    }

    @FXML
    void handlePrikazi(ActionEvent event) {
    	if (!ucitajRacun(tfKorisnickoIme.getText())) {
    		Dialogs.showErrorDialog("Greska", 
    								"Nepostojeci korisnik", 
    								"Ne postoji korisnik sa imenom \"" + tfKorisnickoIme.getText() + "\".");
    	}
    }
    
    public boolean ucitajRacun(String username) {
    	return false;  
    }

}
