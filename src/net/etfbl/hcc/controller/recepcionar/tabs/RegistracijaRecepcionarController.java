package net.etfbl.hcc.controller.recepcionar.tabs;

import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import net.etfbl.hcc.util.TemporalStringConverters;
import net.etfbl.hcc.view.recepcionar.Dialogs;

public class RegistracijaRecepcionarController {

    @FXML
    private TextField tfKorisnickoIme;

    @FXML
    private PasswordField pfLozinka;
    
    @FXML
    private PasswordField pfPotvrdiLozinku;
    
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
    private Button btnPotvrdi;
    
    @FXML
    void initialize() {
    	// Populate choice box
    	cbBrojKreveta.getItems().addAll(1, 2, 3, 4);
    	cbBrojKreveta.setValue(1);
    	
    	// Set proper string converter
		dpDatumOd.setConverter(TemporalStringConverters.getLocalDateConverter());
		dpDatumDo.setConverter(TemporalStringConverters.getLocalDateConverter());
		
		// Set default dates
		dpDatumOd.setValue(LocalDate.now());
		dpDatumDo.setValue(dpDatumOd.getValue().plusDays(7));
    }

    @FXML
    void handlePotvrdi(ActionEvent event) {
    	if (inputValid()) {
    		
    	}
    	// Clear sensitive data
		pfLozinka.setText("");
		pfPotvrdiLozinku.setText("");
    }
    
    /**
     * Validates the user input in the text fields.
     */
    private boolean inputValid() {
    	String message = "";
    	
    	if (tfKorisnickoIme.getText() == null || tfKorisnickoIme.getText().trim().isEmpty()) {
    		message += "Nedostaje korisnicko ime.\n";
    	}
    	if (pfLozinka.getText() == null || pfLozinka.getText().length() < 8) {
    		message += "Lozinka ne moze imati manje od 8 karaktera.\n";
    	}
    	if  (!pfLozinka.getText().equals(pfPotvrdiLozinku.getText())) {
    		message += "Lozinke se ne podudaraju.\n";
    	}
    	if (tfIme.getText() == null || tfIme.getText().trim().isEmpty()) {
    		message += "Nedostaje ime.\n";
    	}
    	if (tfPrezime.getText() == null || tfPrezime.getText().trim().isEmpty()) {
    		message += "Nedostaje prezime.\n";
    	}
    	if (tfBrojTelefona.getText() == null || tfBrojTelefona.getText().trim().isEmpty()) {
    		message += "Nedostaje broj telefona.\n";
    	}
    	if (dpDatumOd.getValue().compareTo(dpDatumDo.getValue()) >= 0) {
    		message += "Nevalidan datum.\n";
    	}
    	
    	if (message.isEmpty()) {
    		return true;
    	} else {
    		Dialogs.showErrorDialog("Greska", "Ispravite sljedeca polja:", message);
    		return false;
    	}
    }
    
}
