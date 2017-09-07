package net.etfbl.hcc.view.gost;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import net.etfbl.hcc.Main;
import net.etfbl.hcc.model.Korisnik;
import net.etfbl.hcc.model.Utisak;

public class KnjigaUtisakaGostController {
	@FXML
	private StackPane stackPane;
	@FXML
	private FlowPane flowPane;
	
	public static List<Utisak> listaUtisaka;
	
	public int brojacUtisaka=0;
	public int josUtisakaCounter=0;
	
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
		listaUtisaka.add(new Utisak(1,text,date,k));
		listaUtisaka.add(new Utisak(1,text,date,k));
		listaUtisaka.add(new Utisak(1,text,date,k));
		listaUtisaka.add(new Utisak(1,text,date,k));
		listaUtisaka.add(new Utisak(1,text,date,k));
		listaUtisaka.add(new Utisak(1,text,date,k));
		listaUtisaka.add(new Utisak(1,text,date,k));
		listaUtisaka.add(new Utisak(1,text,date,k));
		listaUtisaka.add(new Utisak(1,text,date,k));
		listaUtisaka.add(new Utisak(1,text,date,k));
		listaUtisaka.add(new Utisak(1,text,date,k));
		listaUtisaka.add(new Utisak(1,text,date,k));
		listaUtisaka.add(new Utisak(1,text,date,k));
		listaUtisaka.add(new Utisak(1,text,date,k));
		listaUtisaka.add(new Utisak(1,text,date,k));
		listaUtisaka.add(new Utisak(1,text,date,k));
		listaUtisaka.add(new Utisak(1,text,date,k));
		listaUtisaka.add(new Utisak(1,text,date,k));
		listaUtisaka.add(new Utisak(1,text,date,k));
		listaUtisaka.add(new Utisak(1,text,date,k));
		listaUtisaka.add(new Utisak(1,text,date,k));
		listaUtisaka.add(new Utisak(1,text,date,k));
		listaUtisaka.add(new Utisak(1,text,date,k));
		listaUtisaka.add(new Utisak(1,text,date,k));
		listaUtisaka.add(new Utisak(1,text,date,k));
		listaUtisaka.add(new Utisak(1,text,date,k));
		listaUtisaka.add(new Utisak(1,text,date,k));
		listaUtisaka.add(new Utisak(1,text,date,k));
		listaUtisaka.add(new Utisak(1,text,date,k));
		listaUtisaka.add(new Utisak(1,text,date,k));
	
		
		brojacUtisaka=0;
		josUtisakaCounter=0;
		iscrtaj();
	}
	
	
	public void iscrtajJosButton(){
		JFXButton josUtisakaButton = new JFXButton();
		josUtisakaButton.setPrefWidth(280);
		josUtisakaButton.setPrefHeight(150);
		josUtisakaButton.getStylesheets().add("button");
		josUtisakaButton.setOnAction((e) -> {
			handleJosUtisaka();
		});
		josUtisakaButton.setButtonType(ButtonType.RAISED);
		Pane pane = new Pane();
		pane.setPrefWidth(280);
		pane.setPrefHeight(150);
		Label tritacke = new Label("...");
		tritacke.setLayoutX(105.0);
		tritacke.setLayoutY(30.0);
		Label jos = new Label("Jo� utisaka");
		jos.setLayoutX(45.0);
		jos.setLayoutY(82.0);
		pane.getChildren().add(tritacke);
		pane.getChildren().add(jos);
		josUtisakaButton.setGraphic(pane);
		brojacUtisaka++;
		flowPane.getChildren().add(josUtisakaButton);
	}
	
	public void iscrtajDodajUtisakButton(){
		JFXButton dodajUtisakButton = new JFXButton();
		dodajUtisakButton.setPrefWidth(280);
		dodajUtisakButton.setPrefHeight(150);
		dodajUtisakButton.getStylesheets().add("button");
		dodajUtisakButton.setOnAction((e) -> {
			handleDodajUtisak();
		});
		dodajUtisakButton.setButtonType(ButtonType.RAISED);
		Pane pane = new Pane();
		pane.setPrefWidth(280);
		pane.setPrefHeight(150);
		Label tritacke = new Label("+");
		tritacke.setLayoutX(105.0);
		tritacke.setLayoutY(30.0);
		Label jos = new Label("Dodaj utisak");
		jos.setLayoutX(45.0);
		jos.setLayoutY(82.0);
		pane.getChildren().add(tritacke);
		pane.getChildren().add(jos);
		dodajUtisakButton.setGraphic(pane);
		brojacUtisaka++;
		flowPane.getChildren().add(dodajUtisakButton);
	}
	
	public void iscrtajUtisak(Utisak u){
			AnchorPane utisakAnchorPane = new AnchorPane();
			utisakAnchorPane.setPrefWidth(280);
			utisakAnchorPane.setPrefHeight(150);
			utisakAnchorPane.getStyleClass().add("utisak");
			utisakAnchorPane.setOnMouseClicked((e) -> {
				AnchorPane parent = new AnchorPane();
				AnchorPane utisakFull = new AnchorPane();
				utisakFull.setPrefWidth(660);
				utisakFull.setPrefHeight(410);
				utisakFull.getStyleClass().add("utisakFull");
				AnchorPane.setTopAnchor(utisakFull,30.0);
				AnchorPane.setLeftAnchor(utisakFull,350.0);

				
				AnchorPane headerAnchorPane = new AnchorPane();
				headerAnchorPane.setPrefWidth(660);
				headerAnchorPane.setPrefHeight(60);
				headerAnchorPane.getStyleClass().add("utisakHeader");
				
				Label username = new Label(u.getKorisnik().getUsername());
				headerAnchorPane.getChildren().add(username);
				AnchorPane.setTopAnchor(username, 10.0);
				AnchorPane.setLeftAnchor(username,20.0);
				
				LocalDateTime time = LocalDateTime.ofInstant(u.getDatum().toInstant(),ZoneId.systemDefault());
				
				String dateString = String.format("%02d", time.getHour())+":"+String.format("%02d", time.getMinute())+" "+time.getDayOfMonth()+"."+time.getMonthValue()+"."+time.getYear();
				
				Label date = new Label(dateString);
				headerAnchorPane.getChildren().add(date);
				AnchorPane.setTopAnchor(date, 10.0);
				AnchorPane.setLeftAnchor(date,250.0);
				
				JFXButton closeButton = new JFXButton("X");
				headerAnchorPane.getChildren().add(closeButton);
				closeButton.setOnAction(ev -> {
					parent.toBack();
					flowPane.toFront();
				});
				AnchorPane.setTopAnchor(closeButton, 0.0);
				AnchorPane.setRightAnchor(closeButton,0.0);
				
				
				utisakFull.getChildren().add(headerAnchorPane);
				
				Label text = new Label(u.getTekst());
				text.setPrefWidth(620);
				text.setWrapText(true);
				AnchorPane.setTopAnchor(text, 90.0);
				AnchorPane.setLeftAnchor(text,20.0);
				utisakFull.getChildren().add(text);
							
				parent.getChildren().add(utisakFull);
				
				stackPane.getChildren().add(parent);
				parent.toFront();
			});
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
			
			
			flowPane.getChildren().add(utisakAnchorPane);
	}
	
	public void iscrtaj(){
		brojacUtisaka=0;
		
		flowPane.getChildren().clear();
		
		iscrtajDodajUtisakButton();
		Collections.sort(listaUtisaka);
		for(int i=0;i<listaUtisaka.size();i++){
			if(i<(9*josUtisakaCounter)+7){
				iscrtajUtisak(listaUtisaka.get(i));
			}
		}
		if(brojacUtisaka!=listaUtisaka.size()+1){
			iscrtajJosButton();
		}
	}	
	
	public void handleJosUtisaka(){
		josUtisakaCounter++;
		iscrtaj();
	}
	
	@FXML
	public void handleDodajUtisak(){
		try{
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/gost/dodajUtisak.fxml"));
			AnchorPane dodajUtisakAnchorPane = (AnchorPane) loader.load();
			DodajUtisakController controller = loader.getController();
			controller.setController(this);
			controller.setAnchorPane(dodajUtisakAnchorPane);
			stackPane.getChildren().add(dodajUtisakAnchorPane);
			dodajUtisakAnchorPane.toFront();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}


	public FlowPane getFlowPane() {
		return flowPane;
	}


	public void setFlowPane(FlowPane flowPane) {
		this.flowPane = flowPane;
	}
	
	
	
}
