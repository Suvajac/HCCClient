package net.etfbl.hcc.view.gost;

import com.jfoenix.controls.JFXPasswordField;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import net.etfbl.hcc.Client;

public class SettingsController {
	@FXML
	private JFXPasswordField staraLozinkaPasswordField;
	@FXML
	private JFXPasswordField novaLozinkaPasswordField;
	@FXML
	private JFXPasswordField ponovljenaLozinkaPasswordField;
	@FXML
	private Label statusLabel;
	@FXML
	private Button promijeniButton;
	
	private StackPane stackPane;
	private AnchorPane anchorPane;
	private boolean staraLozinka;
	private boolean novaLozinka;
	private boolean ponovljenaLozinka;
	
	public void initialize(){
		staraLozinka = false;
		novaLozinka = false;
		ponovljenaLozinka = false;
		statusLabel.setVisible(false);
		staraLozinkaPasswordField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
			if (!newValue) { 
				if(staraLozinkaPasswordField.getText().hashCode()!=Integer.parseInt(RootGostController.gost.getLozinkaHash())){
					staraLozinkaPasswordField.setFocusColor(Color.valueOf("#e31414"));
					staraLozinkaPasswordField.setUnFocusColor(Color.valueOf("#e31414"));
					setStatus("Pogresno ste unijeli staru lozinku!");
					staraLozinka = true;
				}
				else{
					staraLozinkaPasswordField.setFocusColor(Color.valueOf("#23a429"));
					staraLozinkaPasswordField.setUnFocusColor(Color.valueOf("#23a429"));
					staraLozinka = true;
				}
			}
		});
		
		novaLozinkaPasswordField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
			if (!newValue) { 
				if(novaLozinkaPasswordField.getText().length()<8){
					novaLozinkaPasswordField.setFocusColor(Color.valueOf("#e31414"));
					novaLozinkaPasswordField.setUnFocusColor(Color.valueOf("#e31414"));
					setStatus("Lozinka mora biti minimalno 8 karaktera!");
					novaLozinka = false;
				}
				else{
					novaLozinkaPasswordField.setFocusColor(Color.valueOf("#23a429"));
					novaLozinkaPasswordField.setUnFocusColor(Color.valueOf("#23a429"));
					novaLozinka = true;
				}
			}
		});
		
		ponovljenaLozinkaPasswordField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
			if (!newValue) { 
				if(ponovljenaLozinkaPasswordField.getText().hashCode()!=novaLozinkaPasswordField.getText().hashCode()){
					ponovljenaLozinkaPasswordField.setFocusColor(Color.valueOf("#e31414"));
					ponovljenaLozinkaPasswordField.setUnFocusColor(Color.valueOf("#e31414"));
					setStatus("Lozinke se ne poklapaju!");
					ponovljenaLozinka = false;

				}
				else{
					ponovljenaLozinkaPasswordField.setFocusColor(Color.valueOf("#23a429"));
					ponovljenaLozinkaPasswordField.setUnFocusColor(Color.valueOf("#23a429"));
					ponovljenaLozinka = true;					
				}
			}
		});
	}
	
	@FXML
	public void handleClose(){
		anchorPane.toBack();
		stackPane.getChildren().remove(anchorPane);
	}
	
	@FXML
	public void handlePromijeni(){
		if(checkFields()){
			RootGostController.gost.setLozinkaHash(String.valueOf(ponovljenaLozinkaPasswordField.getText().hashCode()));
			if(Client.getInstance().promjenaLozinke(RootGostController.gost)){
				statusLabel.setText("Uspjesno ste promijenili lozinku!");
			}
			else{
				statusLabel.setText("Greska");
			}
		}
	}
	
	private boolean checkFields(){
		return staraLozinka&novaLozinka&ponovljenaLozinka;
	}
	
	private void setStatus(String text){
		statusLabel.setText(text);
		statusLabel.setVisible(true);
	}

	public void setStackPane(StackPane stackPane) {
		this.stackPane = stackPane;
	}

	public void setAnchorPane(AnchorPane anchorPane) {
		this.anchorPane = anchorPane;
	}
	
	
}
