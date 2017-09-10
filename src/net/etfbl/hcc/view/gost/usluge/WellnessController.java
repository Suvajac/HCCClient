package net.etfbl.hcc.view.gost.usluge;

import java.time.LocalDate;

import com.jfoenix.controls.JFXDatePicker;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class WellnessController {
	@FXML
	private JFXDatePicker datumDatePicker;
	@FXML
	private ComboBox<String> vrijemeComboBox;
	
	public void initialize(){
		datumDatePicker.setValue(LocalDate.now());
	}
	
	@FXML
	private void handleNaruci(){
		
	}
}
