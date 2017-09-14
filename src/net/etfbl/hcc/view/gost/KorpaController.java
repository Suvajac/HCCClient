package net.etfbl.hcc.view.gost;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import net.etfbl.hcc.Client;
import net.etfbl.hcc.model.Korpa;
import net.etfbl.hcc.model.Proizvod;
import net.etfbl.hcc.model.SobnaUsluga;
import net.etfbl.hcc.model.SportskaOprema;
import net.etfbl.hcc.view.recepcionar.Dialogs;

public class KorpaController implements Initializable{
	@FXML
	protected VBox vBox;
	@FXML
	protected Label ukupnoLabel;
	@FXML
	protected Label korpaLabel;
	@FXML
	protected Label opremaLabel;
	@FXML
	private Button naruciButton;
	
	protected ResourceBundle rb;
	
	public void initialize(URL url,ResourceBundle rb){
		this.rb = rb;
		ukupnoLabel.setText(rb.getString("ukupnaCijena"));
		if(opremaLabel!=null)
			opremaLabel.setText(rb.getString("opremaLabel"));
		if(korpaLabel!=null)
			korpaLabel.setText(rb.getString("korpaLabel"));
		if(naruciButton!=null)
			naruciButton.setText(rb.getString("naruciButton"));
	}
	
	protected Korpa korpa;
	protected Map<Proizvod, Label> mapaProizvoda;
	protected StackPane stackPane;
	protected AnchorPane parent;
	protected Label brojacLabel;

	public void prikaziProizvode(){
		AnchorPane headAnchorPane = new AnchorPane();
		Label artikal = new Label(rb.getString("artikalLabel"));
		AnchorPane.setLeftAnchor(artikal,20.0);
		Label kolicinaL = new Label(rb.getString("kolicinaLabel"));
		AnchorPane.setLeftAnchor(kolicinaL,205.0);
		Label cijenaL = new Label(rb.getString("cijena"));
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
						promjenaLabela();
					}
				});
				JFXButton plusButton = new JFXButton("+");
				plusButton.setOnAction(e -> {
					int kolicinaTemp = Integer.parseInt(kolicina.getText());
					kolicinaTemp++;
					korpa.add(p);
					kolicina.setText(kolicinaTemp+"");
					izracunajCijenu();
					promjenaLabela();
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
					promjenaLabela();
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
		ukupnoLabel.setText(String.format("%s: %3.2f EUR",rb.getString("ukupnaCijena"),korpa.getUkupnaCijena()));
	}
	
	@FXML
	public void handleNaruci(){
		SobnaUsluga usluga = new SobnaUsluga(0,"Sobna usluga",korpa.getUkupnaCijena(),"Dostava");
		usluga.setListaProizvoda(korpa.getListaProizvoda());
		int returnValue = -1;
		if(Dialogs.showConfirmationDialog("Conf", "asdasd", "asdas").equals(ButtonType.OK)){
			returnValue = Client.getInstance().dodajUslugu(usluga,RootGostController.gost.getRacun());
			System.out.println(usluga.getCijena());
		}
		if(returnValue>0){
			System.out.println(returnValue);
			handleClose();
		}
	}
	
	@FXML
	private void handleClose(){
		parent.toBack();
		stackPane.getChildren().remove(parent);		
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

	public void promjenaLabela(){
		if(korpa.getListaProizvoda().size()==0){
			brojacLabel.setText("");
		}
		else{
			brojacLabel.setText(korpa.getListaProizvoda().size()+"");
		}
	}
	
	
}
