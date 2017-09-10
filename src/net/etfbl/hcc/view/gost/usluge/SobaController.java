package net.etfbl.hcc.view.gost.usluge;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import net.etfbl.hcc.Main;
import net.etfbl.hcc.model.Korpa;
import net.etfbl.hcc.model.Proizvod;
import net.etfbl.hcc.view.gost.KorpaController;

public class SobaController {
	@FXML
	private VBox hranaVBox;
	@FXML
	private VBox piceVBox;
	@FXML
	private Label brojacLabel;
	
	private Korpa korpa;
	private List<Proizvod> meni;
	private Map<Label, Proizvod> mapaLabelProizvod;
	private StackPane stackPane;
	
	public void initialize(){
		meni= new ArrayList<>();
		mapaLabelProizvod = new HashMap<>();
		korpa = new Korpa();
		
		for(int i=0;i<5;i++){
			Proizvod p1 = new Proizvod(1, "Pice", "Coca cola", 3);
			Proizvod p2 = new Proizvod(2,"Hrana","Cordon bleu",5);
			Proizvod p3 = new Proizvod(3, "Hrana", "Makarone", 1);
			Proizvod p4 = new Proizvod(4,"Pice","Nektar",0.5);
			Proizvod p5 = new Proizvod(5,"Hrana","Presnac",0.3);
			meni.add(p1);
			meni.add(p2);
			meni.add(p3);
			meni.add(p4);
			meni.add(p5);
		}
		
		prikaziMeni();
		brojacLabel.setText("");
	}
	
	@FXML
	public void handlePospremanjeSobe(){
		
	}
	
	@FXML
	public void handleVeseraj(){
		
	}
	
	@FXML
	public void handleKorpa(){
		try{
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/gost/korpa.fxml"));
			AnchorPane korpaAnchorPane = (AnchorPane) loader.load();
			AnchorPane.setTopAnchor(korpaAnchorPane,5.0);
			AnchorPane.setLeftAnchor(korpaAnchorPane,383.0);
			KorpaController controller = loader.getController();
			controller.setKorpa(korpa);
			controller.prikaziProizvode();
			controller.setStackPane(stackPane);
			controller.setBrojacLabel(brojacLabel);
			
			AnchorPane parent = new AnchorPane();
			controller.setParent(parent);
			
			parent.getChildren().add(korpaAnchorPane);
			stackPane.getChildren().add(parent);
			korpaAnchorPane.toFront();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void prikaziMeni(){
		for(Proizvod p : meni){
			StringBuilder tackeSb = new StringBuilder();
			for(int i=0;i<36-p.getNaziv().length()-(p.getCijena()+"").length();i++){
				tackeSb.append(".");
			}
			Label label = new Label(p.getNaziv()+tackeSb.toString()+p.getCijena()+" EUR");
			label.getStyleClass().add("stavkaLabel");
			label.setOnMouseClicked((e) ->{
				korpa.add(mapaLabelProizvod.get(label));
				if(korpa.getListaProizvoda().size()==0){
					brojacLabel.setText("");
				}
				else{
					brojacLabel.setText(korpa.getListaProizvoda().size()+"");
				}
				handleKorpa();
			});
			mapaLabelProizvod.put(label, p);
			if(p.getTip().equals("Hrana")){
				hranaVBox.getChildren().add(label);
			}
			else if(p.getTip().equals("Pice")){	
				piceVBox.getChildren().add(label);
			}
		}
	}
	
	public List<Proizvod> getMeni(){
		return meni;
	}

	public void setStackPane(StackPane stackPane) {
		this.stackPane = stackPane;
	}
	
	
}
