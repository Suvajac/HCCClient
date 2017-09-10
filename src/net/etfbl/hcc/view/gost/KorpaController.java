package net.etfbl.hcc.view.gost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import net.etfbl.hcc.model.Korpa;
import net.etfbl.hcc.model.Proizvod;
import net.etfbl.hcc.model.SportskaOprema;

public class KorpaController {
	@FXML
	protected VBox vBox;
	@FXML
	protected Label ukupnoLabel;
	
	protected Korpa korpa;
	protected Map<Proizvod, Label> mapaProizvoda;
	protected StackPane stackPane;
	protected AnchorPane parent;
	protected Label brojacLabel;

	public void prikaziProizvode(){
		AnchorPane headAnchorPane = new AnchorPane();
		Label artikal = new Label("Artikal");
		AnchorPane.setLeftAnchor(artikal,20.0);
		Label kolicinaL = new Label("Kolicina");
		AnchorPane.setLeftAnchor(kolicinaL,205.0);
		Label cijenaL = new Label("Cijena");
		AnchorPane.setRightAnchor(cijenaL, 110.0);
		
		headAnchorPane.getChildren().addAll(artikal,kolicinaL,cijenaL);
		headAnchorPane.getStyleClass().add("artikal");
		vBox.getChildren().add(headAnchorPane);
		
		mapaProizvoda = new HashMap<>();
		for(Proizvod p : korpa.getListaProizvoda()){
			if(mapaProizvoda.get(p)==null){
				AnchorPane anchorPane = new AnchorPane();
				anchorPane.setPrefHeight(30);
				String nazivStr = new String();
				if(p instanceof SportskaOprema){
					SportskaOprema o = (SportskaOprema) p;
					nazivStr = o.getNaziv()+" vel. "+o.getVelicina();
				}
				else{
					nazivStr = p.getNaziv();
				}
				Label naziv = new Label(nazivStr);
				AnchorPane.setTopAnchor(naziv,10.0);
				AnchorPane.setLeftAnchor(naziv,0.0);
			
				HBox kolicinaHBox = new HBox();
				AnchorPane.setTopAnchor(kolicinaHBox,5.0);
				AnchorPane.setLeftAnchor(kolicinaHBox,185.0);
				kolicinaHBox.setSpacing(10.0);
			
				Label kolicina = new Label("1");
				mapaProizvoda.put(p,kolicina);
				kolicina.setPrefHeight(37.0);
				JFXButton minusButton = new JFXButton("-");
				minusButton.setOnAction(e -> {
					int kolicinaTemp = Integer.parseInt(kolicina.getText());
					if(kolicinaTemp>1){
						kolicinaTemp--;
						korpa.remove(p);
						kolicina.setText(kolicinaTemp+"");
						izracunajCijenu();
					}
				});
				JFXButton plusButton = new JFXButton("+");
				plusButton.setOnAction(e -> {
					int kolicinaTemp = Integer.parseInt(kolicina.getText());
					kolicinaTemp++;
					korpa.add(p);
					kolicina.setText(kolicinaTemp+"");
					izracunajCijenu();
				});
				kolicinaHBox.getChildren().addAll(minusButton,kolicina,plusButton);
				
				Label cijena = new Label(p.getCijena()+" EUR");
				AnchorPane.setTopAnchor(cijena,10.0);
				AnchorPane.setRightAnchor(cijena,110.0);
				
				JFXButton closeButton = new JFXButton("x");
				AnchorPane.setTopAnchor(closeButton,5.0);
				AnchorPane.setRightAnchor(closeButton,5.0);
				closeButton.setOnAction(e -> {
					int kolicinaTemp = Integer.parseInt(kolicina.getText());
					for(int i=0;i<kolicinaTemp;i++){
						korpa.remove(p);
					}
					vBox.getChildren().clear();
					prikaziProizvode();
				});
				
				anchorPane.getChildren().addAll(naziv,kolicinaHBox,cijena,closeButton);
				anchorPane.getStyleClass().add("artikal");
				vBox.getChildren().add(anchorPane);
				
			}
			else{
				Label kolicina = mapaProizvoda.get(p);
				int kolicinaTemp = Integer.parseInt(kolicina.getText());
				kolicinaTemp++;
				kolicina.setText(kolicinaTemp+"");
			}
		}
		izracunajCijenu();
	}
	
	public void izracunajCijenu(){
		double cijena = 0;
		for(Proizvod p : korpa.getListaProizvoda()){
			cijena+=p.getCijena();
		}
		ukupnoLabel.setText("Ukupno: "+cijena+" EUR");
	}
	
	@FXML
	public void handleNaruci(){
		
	}
	
	@FXML
	private void handleClose(){
		parent.toBack();
		stackPane.getChildren().remove(parent);
		if(korpa.getListaProizvoda().size()==0){
			brojacLabel.setText("");
		}
		else{
			brojacLabel.setText(korpa.getListaProizvoda().size()+"");
		}
		
	}

	public void setKorpa(Korpa korpa) {
		this.korpa = korpa;
	}

	public void setStackPane(StackPane stackPane) {
		this.stackPane = stackPane;
	}

	public void setParent(AnchorPane parent) {
		this.parent = parent;
	}

	public void setBrojacLabel(Label brojacLabel) {
		this.brojacLabel = brojacLabel;
	}

	
	
	
	
}
