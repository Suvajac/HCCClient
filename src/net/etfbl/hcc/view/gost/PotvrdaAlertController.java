package net.etfbl.hcc.view.gost;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import net.etfbl.hcc.Client;
import net.etfbl.hcc.model.Usluga;

public class PotvrdaAlertController implements Initializable{
	@FXML
	private Label potvrdaLabel;
	@FXML
	private Label tekstLabel;
	@FXML
	private Label statusLabel;
	@FXML
	private Button daButton;
	@FXML
	private Button neButton;
	
	private StackPane stackPane;
	private AnchorPane anchorPane;
	private Usluga usluga;
	private ResourceBundle rb;
	
	public void initialize(URL url,ResourceBundle rb){
		this.rb = rb;
	}
	
	public void setTekstLabel(){
		tekstLabel.setText(rb.getString("daLiZelite")+" "+usluga.getNaziv()+" "+rb.getString("poCijeni")+" "+usluga.getCijena()+" EUR?");
	}
	
	@FXML
	public void handleDa(){
		int idUsluge = Client.getInstance().dodajUslugu(usluga,RootGostController.gost.getRacun());
		if(idUsluge>0){
			statusLabel.getStyleClass().add("plaviLabel");
			statusLabel.setText(rb.getString("uspjesno"));
		}
		else{
			statusLabel.getStyleClass().add("crveniLabel");
			statusLabel.setText(rb.getString("neuspjesno"));
		}
		daButton.setDisable(true);
	}
	
	@FXML
	public void handleNe(){
		anchorPane.toBack();
		stackPane.getChildren().remove(anchorPane);
	}
	
	public void setStackPane(StackPane stackPane) {
		this.stackPane = stackPane;
	}

	public void setAnchorPane(AnchorPane anchorPane) {
		this.anchorPane = anchorPane;
	}

	public Usluga getUsluga() {
		return usluga;
	}

	public void setUsluga(Usluga usluga) {
		this.usluga = usluga;
		setTekstLabel();
	}

	
	
}
