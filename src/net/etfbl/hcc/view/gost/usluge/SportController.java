package net.etfbl.hcc.view.gost.usluge;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import net.etfbl.hcc.Main;
import net.etfbl.hcc.model.Korpa;
import net.etfbl.hcc.model.Proizvod;
import net.etfbl.hcc.model.SportskaOprema;
import net.etfbl.hcc.view.gost.KorpaController;

public class SportController {
	@FXML
	private VBox opremaVBox;
	@FXML
	private JFXDatePicker datumDatePicker;
	@FXML
	private JFXComboBox<String> vrijemeComboBox;
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
			SportskaOprema o1 = new SportskaOprema(1, "Kopacke Addidas", 1, "43");
			SportskaOprema o2 = new SportskaOprema(2, "Kopacke Addidasl", 1, "42");
			SportskaOprema o3 = new SportskaOprema(3, "Kopacke Addidas", 1, "44");
			SportskaOprema o4 = new SportskaOprema(4, "Majica", 1, "L");
			SportskaOprema o5 = new SportskaOprema(5, "Sorc", 1, "L");
			meni.add(o1);
			meni.add(o2);
			meni.add(o3);
			meni.add(o4);
			meni.add(o5);
		}
		
		datumDatePicker.setValue(LocalDate.now());
		
		prikaziMeni();
		brojacLabel.setText("");
	}
	
	@FXML
	public void handleOprema(){
		try{
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/gost/opremaKorpa.fxml"));
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
	
	@FXML
	public void handleNaruci(){
		
	}
	
	public void prikaziMeni(){
		for(Proizvod p : meni){
			StringBuilder tackeSb = new StringBuilder();
			for(int i=0;i<45-p.getNaziv().length()-(p.getCijena()+"").length()-((SportskaOprema)p).getVelicina().length();i++){
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
				handleOprema();
			});
			mapaLabelProizvod.put(label, p);
			opremaVBox.getChildren().add(label);
		}
	}
	
	public List<Proizvod> getMeni(){
		return meni;
	}

	public void setStackPane(StackPane stackPane) {
		this.stackPane = stackPane;
	}
	
	
}
