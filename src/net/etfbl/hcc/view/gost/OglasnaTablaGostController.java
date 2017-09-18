package net.etfbl.hcc.view.gost;

import java.time.LocalDateTime;
import java.util.*;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import net.etfbl.hcc.Client;
import net.etfbl.hcc.model.Oglas;

//Poslije nekad promijeniti imena klasa za css da ne budu utisak i utisakHeader

public class OglasnaTablaGostController {
	@FXML
	private StackPane stackPane;
	@FXML
	private FlowPane flowPane;
	
	private List<Oglas> listaOglasa;
	
	public void initialize(){
		listaOglasa = Client.getInstance().getOglasi();
		Collections.reverse(listaOglasa);
		
		for(Oglas og : listaOglasa){
			iscrtajOglas(og);
		}
	}
	
	public void iscrtajOglas(Oglas o){
		AnchorPane oglasAnchorPane = new AnchorPane();
		oglasAnchorPane.setPrefWidth(280);
		oglasAnchorPane.setPrefHeight(150);
		oglasAnchorPane.getStyleClass().add("utisak");
		oglasAnchorPane.setOnMouseClicked((e) -> {
			AnchorPane parent = new AnchorPane();
			AnchorPane oglasFull = new AnchorPane();
			oglasFull.setPrefWidth(660);
			oglasFull.setPrefHeight(410);
			oglasFull.getStyleClass().add("utisakFull");
			oglasFull.setBackground(oglasAnchorPane.getBackground());
			AnchorPane.setTopAnchor(oglasFull,30.0);
			AnchorPane.setLeftAnchor(oglasFull,350.0);
				
			AnchorPane headerAnchorPane = new AnchorPane();
			headerAnchorPane.setPrefWidth(660);
			headerAnchorPane.setPrefHeight(60);
			headerAnchorPane.getStyleClass().add("utisakHeader");
			
			LocalDateTime time = o.getDatum();
			
			String dateString = String.format("%02d", time.getHour())+":"+String.format("%02d", time.getMinute())+" "+time.getDayOfMonth()+"."+time.getMonthValue()+"."+time.getYear();
				
			Label date = new Label(dateString);
			headerAnchorPane.getChildren().add(date);
			AnchorPane.setTopAnchor(date, 10.0);
			AnchorPane.setLeftAnchor(date,20.0);
			
			JFXButton closeButton = new JFXButton("X");
			headerAnchorPane.getChildren().add(closeButton);
			closeButton.setOnAction(ev -> {
				stackPane.getChildren().remove(parent);
				parent.toBack();
				flowPane.toFront();
			});
			AnchorPane.setTopAnchor(closeButton, 0.0);
			AnchorPane.setRightAnchor(closeButton,0.0);
			
				
			oglasFull.getChildren().add(headerAnchorPane);
			
			Label text = new Label(o.getPoruka());
			text.setPrefWidth(620);
			text.setWrapText(true);
			AnchorPane.setTopAnchor(text, 90.0);
			AnchorPane.setLeftAnchor(text,20.0);
			oglasFull.getChildren().add(text);
						
			parent.getChildren().add(oglasFull);
				
			stackPane.getChildren().add(parent);
			parent.toFront();
		});
		oglasAnchorPane.getStyleClass().add("zutiStil");
		
		AnchorPane headerAnchorPane = new AnchorPane();
		headerAnchorPane.setPrefWidth(280);
		headerAnchorPane.setPrefHeight(40);
		headerAnchorPane.getStyleClass().add("utisakHeader");
		
		LocalDateTime time = o.getDatum();
	
		String dateString = String.format("%02d", time.getHour())+":"+String.format("%02d", time.getMinute())+" "+time.getDayOfMonth()+"."+time.getMonthValue()+"."+time.getYear();
		
		Label date = new Label(dateString);
		headerAnchorPane.getChildren().add(date);
		AnchorPane.setTopAnchor(date, 5.0);
		AnchorPane.setLeftAnchor(date,10.0);
		
		oglasAnchorPane.getChildren().add(headerAnchorPane);
		Label text = new Label(o.getPoruka());
		text.setPrefWidth(260);
		text.setPrefHeight(85);
		text.setWrapText(true);
		AnchorPane.setTopAnchor(text, 45.0);
		AnchorPane.setLeftAnchor(text,10.0);
		
		oglasAnchorPane.getChildren().add(text);
		
		
		flowPane.getChildren().add(oglasAnchorPane);
	}
}
