package net.etfbl.hcc.view.gost;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import com.jfoenix.controls.JFXTextArea;

public class PopustiGostController{
	
	@FXML
	private TextField unosKoda;
	
	@FXML
	private Button potvrdaKoda;
	
	@FXML
	private JFXTextArea status;
	
	String kod;
	
	public void initialize(){}
	
	public void validacijaKoda(){
		kod=unosKoda.getText();
		
		status.setText("Kod " + kod + " je validan.");
		
	}
	

}
