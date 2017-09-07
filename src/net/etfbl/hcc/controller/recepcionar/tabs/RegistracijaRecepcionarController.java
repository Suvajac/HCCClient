package net.etfbl.hcc.controller.recepcionar.tabs;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import net.etfbl.hcc.view.recepcionar.Dialogs;

public class RegistracijaRecepcionarController {
	
	private static final String DATE_PATTERN = "dd.MM.yyyy";

    @FXML
    private TextField tfUsername;

    @FXML
    private PasswordField pfPassword;
    
    @FXML
    private PasswordField pfConfirmPassword;
    
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
    	
    	// Set date formatter
    	final StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

			@Override
			public String toString(LocalDate date) {
				if (date != null) {
					return dateFormatter.format(date);
				} else {
					return "";
				}
			}

			@Override
			public LocalDate fromString(String string) {
				if (string != null && !string.isEmpty()) {
					return LocalDate.parse(string, dateFormatter);
				} else {
					return null;
				}
			}
		};
		dpDatumOd.setConverter(converter);
		dpDatumDo.setConverter(converter);
		
		// Set default dates
		dpDatumOd.setValue(LocalDate.now());
		dpDatumDo.setValue(dpDatumOd.getValue().plusDays(7));
    }

    @FXML
    void handlePotvrdi(ActionEvent event) {
    	if (inputValid()) {
    		
    	}
    }
    
    private boolean inputValid() {
    	String message = "";
    	
    	if (tfUsername.getText() == null || tfUsername.getText().trim().length() == 0) {
    		message += "- Nedostaje korisnicko ime\n";
    	}
    	if (pfPassword.getText() == null || pfPassword.getText().length() < 8) {
    		message += "- Lozinka ne moze imati manje od 8 karaktera\n";
    	}
    	if  (!pfPassword.getText().equals(pfConfirmPassword.getText())) {
    		message += "- Lozinke se ne podudaraju\n";
    	}
    	if (tfIme.getText() == null || tfIme.getText().trim().length() == 0) {
    		message += "- Nedostaje ime\n";
    	}
    	if (tfPrezime.getText() == null || tfPrezime.getText().trim().length() == 0) {
    		message += "- Nedostaje prezime\n";
    	}
    	if (tfBrojTelefona.getText() == null || tfBrojTelefona.getText().trim().length() == 0) {
    		message += "- Nedostaje broj telefona\n";
    	}
    	if (dpDatumOd.getValue().compareTo(dpDatumDo.getValue()) >= 0) {
    		message += "- Nevalidan datum\n";
    	}
    	
    	if (message.length() == 0) {
    		return true;
    	} else {
    		Dialogs.showErrorDialog("Greska", "Nevalidan unos", "Prepravite sljedeca polja:\n" + message);
    		return false;
    	}
    }
    
}
