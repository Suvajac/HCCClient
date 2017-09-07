package net.etfbl.hcc.view.gost;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import net.etfbl.hcc.model.Korisnik;
import net.etfbl.hcc.model.Utisak;

public class KnjigaUtisakaGostController {
	@FXML
	private GridPane gridPane;
	
	private List<Utisak> listaUtisaka;
	
	public int brojacUtisaka=0;
	
	public void initialize(){
		listaUtisaka = new ArrayList<>();
		Korisnik k = new Korisnik("user","ime","prezi","1234","hash");
		String text = "Lorem ipsum dolor sit amet, ea eum veri dicam. Euismod senserit reprimique qui te. Ne hinc prompta debitis vis, ut aperiam eloquentiam qui, vel id vidit feugiat. Amet vide senserit te vix, ponderum scribentur ad pri. Id dolorem pericula consulatu per, ex his eripuit perpetua, quidam regione assueverit ad pri.";
		Date date = new Date(System.currentTimeMillis());
		listaUtisaka.add(new Utisak(1,text,date,k));
		listaUtisaka.add(new Utisak(1,text,date,k));
		listaUtisaka.add(new Utisak(1,text,date,k));
		listaUtisaka.add(new Utisak(1,text,date,k));
		listaUtisaka.add(new Utisak(1,text,date,k));
		listaUtisaka.add(new Utisak(1,text,date,k));
	
		brojacUtisaka=0;
		for(int i=0;i<listaUtisaka.size();i++){
			Utisak u = listaUtisaka.get(i);
			if(i<7)
				iscrtajUtisak(u);
		}
		iscrtajJosButton();
	}
	
	
	public void iscrtajJosButton(){
		JFXButton josUtisakaButton = new JFXButton();
		josUtisakaButton.setPrefWidth(280);
		josUtisakaButton.setPrefHeight(150);
		Pane pane = new Pane();
		pane.setPrefWidth(280);
		pane.setPrefHeight(150);
		Label tritacke = new Label("...");
		Label jos = new Label("Još utisaka");
		pane.getChildren().add(tritacke);
		pane.getChildren().add(jos);
		brojacUtisaka++;
		gridPane.add(josUtisakaButton, brojacUtisaka%3, brojacUtisaka/3);
	}
	
	public void iscrtajUtisak(Utisak u){
			AnchorPane utisakAnchorPane = new AnchorPane();
			utisakAnchorPane.setPrefWidth(280);
			utisakAnchorPane.setPrefHeight(150);
			utisakAnchorPane.getStyleClass().add("utisak");
			brojacUtisaka++;
			if(brojacUtisaka%4==0){
				utisakAnchorPane.getStyleClass().add("crveniStil");
			}
			else if(brojacUtisaka%4==1){
				utisakAnchorPane.getStyleClass().add("zutiStil");
			}
			else if(brojacUtisaka%4==2){
				utisakAnchorPane.getStyleClass().add("plaviStil");
			}
			else if(brojacUtisaka%4==3){
				utisakAnchorPane.getStyleClass().add("zeleniStil");
			}
			
			AnchorPane headerAnchorPane = new AnchorPane();
			headerAnchorPane.setPrefWidth(280);
			headerAnchorPane.setPrefHeight(40);
			headerAnchorPane.getStyleClass().add("utisakHeader");
			
			Label username = new Label(u.getKorisnik().getUsername());
			headerAnchorPane.getChildren().add(username);
			AnchorPane.setTopAnchor(username, 5.0);
			AnchorPane.setLeftAnchor(username,10.0);
			
			LocalDateTime time = LocalDateTime.ofInstant(u.getDatum().toInstant(),ZoneId.systemDefault());
			
			String dateString = String.format("%02d", time.getHour())+":"+String.format("%02d", time.getMinute())+" "+time.getDayOfMonth()+"."+time.getMonthValue()+"."+time.getYear();
			
			Label date = new Label(dateString);
			headerAnchorPane.getChildren().add(date);
			AnchorPane.setTopAnchor(date, 5.0);
			AnchorPane.setRightAnchor(date,10.0);
			
			utisakAnchorPane.getChildren().add(headerAnchorPane);
			Label text = new Label(u.getTekst());
			text.setPrefWidth(260);
			text.setPrefHeight(85);
			text.setWrapText(true);
			AnchorPane.setTopAnchor(text, 45.0);
			AnchorPane.setLeftAnchor(text,10.0);
			
			utisakAnchorPane.getChildren().add(text);
			
			
			gridPane.add(utisakAnchorPane,brojacUtisaka%3,brojacUtisaka/3);
	}
	
	@FXML
	public void handleDodajUtisak(){
		
	}
	
}
