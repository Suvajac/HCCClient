package net.etfbl.hcc.controller.gost;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import net.etfbl.hcc.Client;

public class SettingsController implements Initializable{
	@FXML
	private Label promjenaLozinkeLabel;
	@FXML
	private Label staraLozinkaLabel;
	@FXML
	private JFXPasswordField staraLozinkaPasswordField;
	@FXML
	private Label novaLozinkaLabel;
	@FXML
	private JFXPasswordField novaLozinkaPasswordField;
	@FXML
	private Label potvrdiLozinkuLabel;
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
	
	private ResourceBundle rb;
	
	public void initialize(URL url,ResourceBundle rb){
		this.rb = rb;
		promjenaLozinkeLabel.setText(rb.getString("promjenaLozinkeLabel"));
		staraLozinkaLabel.setText(rb.getString("staraLozinkaLabel"));
		novaLozinkaLabel.setText(rb.getString("novaLozinkaLabel"));
		potvrdiLozinkuLabel.setText(rb.getString("potvrdiLozinkuLabel"));
		promijeniButton.setText(rb.getString("promijeniButton"));
		
		staraLozinka = false;
		novaLozinka = false;
		ponovljenaLozinka = false;
		
		statusLabel.getStyleClass().add("crveniLabel");
		statusLabel.setVisible(false);
		
		staraLozinkaPasswordField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
			if (!newValue) { 
				if(staraLozinkaPasswordField.getText().hashCode()!=Integer.parseInt(RootGostController.gost.getLozinkaHash())){
					staraLozinkaPasswordField.setFocusColor(Color.valueOf("#e31414"));
					staraLozinkaPasswordField.setUnFocusColor(Color.valueOf("#e31414"));
					setStatus(rb.getString("pogresnaStaraLozinka"));
					staraLozinka = true;
				}
				else{
					staraLozinkaPasswordField.setFocusColor(Color.valueOf("#23a429"));
					staraLozinkaPasswordField.setUnFocusColor(Color.valueOf("#23a429"));
					staraLozinka = true;
					statusLabel.setVisible(false);
				}
			}
		});
		
		novaLozinkaPasswordField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
			if (!newValue) { 
				if(novaLozinkaPasswordField.getText().length()<8){
					novaLozinkaPasswordField.setFocusColor(Color.valueOf("#e31414"));
					novaLozinkaPasswordField.setUnFocusColor(Color.valueOf("#e31414"));
					setStatus(rb.getString("lozinkaManjaOdOsam"));
					novaLozinka = false;
				}
				else{
					novaLozinkaPasswordField.setFocusColor(Color.valueOf("#23a429"));
					novaLozinkaPasswordField.setUnFocusColor(Color.valueOf("#23a429"));
					novaLozinka = true;
					if(staraLozinka)
						statusLabel.setVisible(false);
				}
			}
		});
		
		ponovljenaLozinkaPasswordField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
			if (!newValue) { 
				if(ponovljenaLozinkaPasswordField.getText().hashCode()!=novaLozinkaPasswordField.getText().hashCode()){
					ponovljenaLozinkaPasswordField.setFocusColor(Color.valueOf("#e31414"));
					ponovljenaLozinkaPasswordField.setUnFocusColor(Color.valueOf("#e31414"));
					setStatus(rb.getString("lozinkeSeNePoklapaju"));
					ponovljenaLozinka = false;

				}
				else{
					ponovljenaLozinkaPasswordField.setFocusColor(Color.valueOf("#23a429"));
					ponovljenaLozinkaPasswordField.setUnFocusColor(Color.valueOf("#23a429"));
					ponovljenaLozinka = true;
					if(novaLozinka)
						statusLabel.setVisible(false);
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
				statusLabel.getStyleClass().remove("crveniLabel");
				statusLabel.getStyleClass().add("plaviLabel");
				statusLabel.setText(rb.getString("uspjesnaPromjenaLozinke"));
			}
			else{
				statusLabel.setText(rb.getString("greska"));
			}
		}
	}
	
	public boolean checkFields(){
		return staraLozinka&novaLozinka&ponovljenaLozinka;
	}
	
	public void setStatus(String text){
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
