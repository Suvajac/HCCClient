package net.etfbl.hcc.controller.gost.usluge;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import net.etfbl.hcc.*;
import net.etfbl.hcc.controller.gost.*;
import net.etfbl.hcc.model.*;

public class SobaController implements Initializable{
	@FXML
	private VBox hranaVBox;
	@FXML
	private VBox piceVBox;
	@FXML
	private Label brojacLabel;
	@FXML
	private Label dostavaUSobuLabel;
	@FXML
	private Label hranaLabel;
	@FXML
	private Label piceLabel;
	@FXML
	private Button pospremanjeSobeButton;
	@FXML
	private Button veserajButton;
	@FXML
	private Button naruciDostavuButton;
	
	private Korpa korpa;
	private Map<Label, Proizvod> mapaLabelProizvod;
	private StackPane stackPane;
	
	private ResourceBundle rb;
	
	public void initialize(URL url,ResourceBundle rb){
		this.rb = rb;
		dostavaUSobuLabel.setText(rb.getString("dostavaUSobuLabel"));
		hranaLabel.setText(rb.getString("hranaLabel"));
		piceLabel.setText(rb.getString("piceLabel"));
		veserajButton.setText(rb.getString("veserajButton"));
		pospremanjeSobeButton.setText(rb.getString("pospremanjeSobeButton"));
		naruciDostavuButton.setText(rb.getString("naruciDostavuButton"));

		
		mapaLabelProizvod = new HashMap<>();
		korpa = new Korpa();
		
		if(UslugaController.meni==null)
			UslugaController.meni = Client.getInstance().getProizvodi();
		
		prikaziMeni();
		brojacLabel.setText("");
	}
	
	@FXML
	public void handlePospremanjeSobe(){
		SobnaUsluga usluga = new SobnaUsluga(0,"Sobna usluga",0,"Pospremanje sobe");
		
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/gost/potvrdaAlert.fxml"),rb);
			AnchorPane alertAnchorPane;
			alertAnchorPane = (AnchorPane) loader.load();
			
			PotvrdaAlertController controller = loader.getController();
			controller.setStackPane(stackPane);
			controller.setAnchorPane(alertAnchorPane);
			controller.setUsluga(usluga);
			
			stackPane.getChildren().add(alertAnchorPane);
			alertAnchorPane.toFront();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void handleVeseraj(){
		SobnaUsluga usluga = new SobnaUsluga(0,"Sobna usluga",0,"Veseraj");

		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/gost/potvrdaAlert.fxml"),rb);
			AnchorPane alertAnchorPane;
			alertAnchorPane = (AnchorPane) loader.load();
			
			PotvrdaAlertController controller = loader.getController();
			controller.setStackPane(stackPane);
			controller.setAnchorPane(alertAnchorPane);
			controller.setUsluga(usluga);
			
			stackPane.getChildren().add(alertAnchorPane);
			alertAnchorPane.toFront();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void handleNaruciDostavu(){
		SobnaUsluga usluga = new SobnaUsluga(0,"Sobna usluga",korpa.getUkupnaCijena(),"Dostava");
		usluga.setListaProizvoda(korpa.getListaProizvoda());
		
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
	
	@FXML
	public void handleKorpa(){
		try{
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/gost/korpa.fxml"),rb);
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
		for(Proizvod p : UslugaController.meni){
			StringBuilder tackeSb = new StringBuilder();
			for(int i=0;i<28-p.getNaziv().length()-(p.getCijena()+"").length();i++){
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
//				handleKorpa();
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

	public void setStackPane(StackPane stackPane) {
		this.stackPane = stackPane;
	}
	
	
}
