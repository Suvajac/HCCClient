package net.etfbl.hcc.view.gost;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextArea;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import net.etfbl.hcc.Client;
import net.etfbl.hcc.model.Utisak;

public class DodajUtisakController implements Initializable{
	@FXML
	private JFXTextArea textArea;
	
	@FXML
	private Button dodajButton;
	@FXML
	private Button otkaziButton;
	
	KnjigaUtisakaGostController controller;
	private AnchorPane anchorPane;
	
	public void initialize(URL url,ResourceBundle rb){
		dodajButton.setText(rb.getString("dodajButton"));
		dodajButton.setPrefWidth(100.0);
		otkaziButton.setText(rb.getString("otkaziButton"));
		
		Platform.runLater(() -> {
			textArea.requestFocus();
		});
	}
	
	@FXML
	public void handleDodaj(){
		Utisak u = new Utisak(-1,textArea.getText(),LocalDateTime.now(),RootGostController.gost);
		KnjigaUtisakaGostController.listaUtisaka.add(u);
		Client.getInstance().dodajUtisak(u);
		controller.iscrtaj();
		handleCancel();
	}
	
	@FXML
	private void handleCancel(){
		controller.getStackPane().getChildren().remove(anchorPane);
		anchorPane.toBack();
		controller.getFlowPane().toFront();
	}

	public AnchorPane getAnchorPane() {
		return anchorPane;
	}

	public void setAnchorPane(AnchorPane anchorPane) {
		this.anchorPane = anchorPane;
	}

	public KnjigaUtisakaGostController getController() {
		return controller;
	}

	public void setController(KnjigaUtisakaGostController controller) {
		this.controller = controller;
	}
	
	
	
}
