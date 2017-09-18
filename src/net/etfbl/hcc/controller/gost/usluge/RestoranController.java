package net.etfbl.hcc.controller.gost.usluge;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.*;
import com.jfoenix.controls.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import net.etfbl.hcc.*;
import net.etfbl.hcc.controller.gost.*;
import net.etfbl.hcc.model.*;
import net.etfbl.hcc.util.TemporalStringConverters;

public class RestoranController implements Initializable{
	@FXML
	private VBox hranaVBox;
	@FXML
	private VBox piceVBox;
	@FXML
	private JFXTimePicker vrijemeTimePicker;
	@FXML
	private JFXComboBox<Integer> brojStolicaComboBox;
	@FXML
	private Label brojacLabel;
	@FXML
	private Label restoranLabel;
	@FXML
	private Label hranaLabel;
	@FXML
	private Label piceLabel;
	@FXML
	private Label vrijemeLabel;
	@FXML
	private Label brojStolicaLabel;
	@FXML
	private Button naruciButton;

	private Korpa korpa;
	private Map<Label, Proizvod> mapaLabelProizvod;
	private StackPane stackPane;
	
	private ResourceBundle rb;

	public void initialize(URL url, ResourceBundle rb){
		this.rb = rb;
		restoranLabel.setText(rb.getString("restoranLabel"));
		hranaLabel.setText(rb.getString("hranaLabel"));
		piceLabel.setText(rb.getString("piceLabel"));
		vrijemeLabel.setText(rb.getString("vrijemeLabel"));
		brojStolicaLabel.setText(rb.getString("brojStolicaLabel"));
		naruciButton.setText(rb.getString("naruciButton"));
		
		mapaLabelProizvod = new HashMap<>();
		korpa = new Korpa();

		if(UslugaController.meni==null)
			UslugaController.meni = Client.getInstance().getProizvodi();

		brojStolicaComboBox.getItems().addAll(2,3,4,5,6,7,8,9,10);
		brojStolicaComboBox.setValue(2);
		vrijemeTimePicker.setValue(LocalTime.now().plusMinutes(30));
		prikaziMeni();
		brojacLabel.setText("");
	}

	@FXML
	public void handleKorpa(){
		try{
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/gost/korpa.fxml"),rb);
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

	public void prikaziMeni(){
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

	@FXML
	public void handleNaruci(){
		String vrijeme = TemporalStringConverters.toString(vrijemeTimePicker.getValue());
		UslugaRestorana usluga = new UslugaRestorana(0,"Usluga restorana",korpa.getUkupnaCijena(),vrijeme,brojStolicaComboBox.getValue());
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
	
	public void setStackPane(StackPane stackPane) {
		this.stackPane = stackPane;
	}

}
