package net.etfbl.hcc.controller.gost.usluge;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.fxml.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import net.etfbl.hcc.Main;
import net.etfbl.hcc.controller.gost.*;
import net.etfbl.hcc.model.*;

public abstract class AbstractUslugaController {
	@FXML
	protected Label brojacLabel;
	
	protected Korpa korpa;
	protected Map<Label, Proizvod> mapaLabelProizvod;
	protected StackPane stackPane;
	protected ResourceBundle rb;
	
	@FXML
	public void handleKorpa(){
		prikaziKorpu(Main.class.getResource("view/gost/korpa.fxml"));
	}
	
	@FXML
	public void handleOprema(){
		prikaziKorpu(Main.class.getResource("view/gost/opremaKorpa.fxml"));
	}
	
	private void prikaziKorpu(URL resource){
		try{
			FXMLLoader loader = new FXMLLoader(resource,rb);
			AnchorPane korpaAnchorPane = (AnchorPane) loader.load();
			AnchorPane.setTopAnchor(korpaAnchorPane,5.0);
			AnchorPane.setLeftAnchor(korpaAnchorPane,383.0);
			
			AnchorPane parent = new AnchorPane();
			KorpaController controller = loader.getController();
			controller.setKorpa(korpa);
			controller.prikaziProizvode();
			controller.setStackPane(stackPane);
			controller.setBrojacLabel(brojacLabel);
			controller.setParent(parent);
			
			parent.getChildren().add(korpaAnchorPane);
			stackPane.getChildren().add(parent);
			korpaAnchorPane.toFront();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void prikaziMeni(VBox hranaVBox, VBox piceVBox){
		for(Proizvod p : UslugaController.meni){
			StringBuilder tackeSb = new StringBuilder();
			for(int i=0;i<28-p.getNaziv().length()-(p.getCijena()+"").length();i++){
				tackeSb.append(".");
			}
			Label label = new Label(p.getNaziv()+tackeSb.toString()+p.getCijena()+" EUR");
			mapaLabelProizvod.put(label, p);
			label.getStyleClass().add("stavkaLabel");
			label.setOnMouseClicked((e) ->{
				korpa.add(mapaLabelProizvod.get(label));
				if(korpa.getListaProizvoda().size()==0){
					brojacLabel.setText("");
				}
				else{
					brojacLabel.setText(korpa.getListaProizvoda().size()+"");
				}
//				handleKorpa();
			});
			
			if(p.getTip().equals("Hrana")){
				hranaVBox.getChildren().add(label);
			}
			else if(p.getTip().equals("Pice")){
				piceVBox.getChildren().add(label);
			}
		}
	}
	
	public void prikaziMeniOpreme(VBox opremaVBox){
		for(Proizvod p : UslugaController.oprema){
			StringBuilder tackeSb = new StringBuilder();
			for(int i=0;i<50-p.getNaziv().length()-(p.getCijena()+"").length()-((SportskaOprema)p).getVelicina().length();i++){
				tackeSb.append(".");
			}
			Label label = new Label(p.getNaziv()+" vel. "+((SportskaOprema)p).getVelicina()+tackeSb.toString()+p.getCijena()+" EUR");
			label.getStyleClass().add("stavkaLabel");
			label.setOnMouseClicked((e) ->{
				korpa.add(mapaLabelProizvod.get(label));
				if(korpa.getListaProizvoda().size()==0){
					brojacLabel.setText("");
				}
				else{
					brojacLabel.setText(korpa.getListaProizvoda().size()+"");
				}
//				handleOprema();
			});
			mapaLabelProizvod.put(label, p);
			opremaVBox.getChildren().add(label);
		}
	}
	
	public void naruci(Usluga usluga){
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/gost/potvrdaAlert.fxml"),rb);
			AnchorPane alertAnchorPane;
			alertAnchorPane = (AnchorPane) loader.load();
			
			PotvrdaAlertController controller = loader.getController();
			controller.setStackPane(stackPane);
			controller.setAnchorPane(alertAnchorPane);
			controller.setUsluga(usluga);
			controller.check();
			
			stackPane.getChildren().add(alertAnchorPane);
			alertAnchorPane.toFront();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setStackPane(StackPane stackPane) {
		this.stackPane = stackPane;
	}
}
