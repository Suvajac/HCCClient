package net.etfbl.hcc.view.gost;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import net.etfbl.hcc.Client;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PopustiGostController{
	
	@FXML
	private TextField unosKoda;
	
	@FXML
	private Button potvrdaKoda;
	
	@FXML
	private Label statusLabel;
	
	public void initialize(){}
	
	@FXML
	public void handlePotvrdi(){
		boolean response = Client.getInstance().potvrdiPopust(Integer.parseInt(unosKoda.getText()), RootGostController.gost);
		if(response)
			statusLabel.setText("Kod je validan.");
		else{
			statusLabel.setText("Kod je nevalidan.");
		}
		
	}
	

}
