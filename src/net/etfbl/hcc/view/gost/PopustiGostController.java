package net.etfbl.hcc.view.gost;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import net.etfbl.hcc.Client;
import net.etfbl.hcc.model.Gost;
import net.etfbl.hcc.model.Korisnik;
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
		if(response){
			RootGostController.gost =(Gost) Client.getInstance().login((Korisnik)RootGostController.gost);
			statusLabel.setText("Dobili ste popust od "+RootGostController.gost.getRacun().getPopust().getProcenat()+"%.");	
		}
		else{
			statusLabel.setText("     Kod nije validan.");
		}
		
	}
	

}
