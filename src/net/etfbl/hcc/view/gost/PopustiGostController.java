package net.etfbl.hcc.view.gost;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import net.etfbl.hcc.Client;
import net.etfbl.hcc.model.Gost;
import net.etfbl.hcc.model.Korisnik;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PopustiGostController implements Initializable{
	
	@FXML
	private TextField unosKoda;
	
	@FXML
	private Button potvrdiKodButton;
	
	@FXML
	private Label statusLabel;
	@FXML
	private Label unesiteKodLabel;
	
	private ResourceBundle rb;
	
	public void initialize(URL url,ResourceBundle rb){
		this.rb = rb;
		unesiteKodLabel.setText(rb.getString("unesiteKodLabel"));
		potvrdiKodButton.setText(rb.getString("potvrdiKodButton"));
	}
	
	@FXML
	public void handlePotvrdi(){
		if(!unosKoda.getText().isEmpty()){
			int kod = Integer.parseInt(unosKoda.getText().trim());
			boolean response = Client.getInstance().potvrdiPopust(kod, RootGostController.gost);
			if(response){
				RootGostController.gost =(Gost) Client.getInstance().login((Korisnik)RootGostController.gost);
				statusLabel.setText(rb.getString("dobiliStePopust")+" "+RootGostController.gost.getRacun().getPopust().getProcenat()+"%.");	
			}
			else{
				statusLabel.setText("    "+rb.getString("kodNijeValidan"));
			}
		}
	}
	

}
