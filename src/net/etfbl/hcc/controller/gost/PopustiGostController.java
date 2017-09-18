package net.etfbl.hcc.controller.gost;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.*;
import javafx.scene.control.TextField;
import net.etfbl.hcc.Client;
import net.etfbl.hcc.model.*;
import javafx.scene.control.*;

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
