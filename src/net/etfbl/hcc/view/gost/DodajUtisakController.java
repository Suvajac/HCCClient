package net.etfbl.hcc.view.gost;

import java.util.Date;

import com.jfoenix.controls.JFXTextArea;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import net.etfbl.hcc.Client;
import net.etfbl.hcc.model.Utisak;

public class DodajUtisakController {
	@FXML
	private JFXTextArea textArea;
	
	KnjigaUtisakaGostController controller;
	private AnchorPane anchorPane;
	
	public void initialize(){
		Platform.runLater(() -> {
			textArea.requestFocus();
		});
	}
	
	@FXML
	public void handleDodaj(){
		Utisak u = new Utisak(-1,textArea.getText(),new Date(System.currentTimeMillis()),RootGostController.gost);
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
