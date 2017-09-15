package net.etfbl.hcc.view.gost;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import net.etfbl.hcc.Client;
import net.etfbl.hcc.model.*;
import net.etfbl.hcc.util.TemporalStringConverters;

public class RacunGostController implements Initializable{
	@FXML
	private BorderPane borderPane;
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private VBox vBox;
	@FXML
	private Label racunLabel;
	@FXML
	private Label cijenaLabel;
	@FXML
	private Label popustLabel;
	@FXML
	private Label ukupnaCijenaLabel;

	private ResourceBundle rb;
	
	public void initialize(URL url,ResourceBundle rb){
		this.rb = rb;
		racunLabel.setText(rb.getString("racunTab"));
		RootGostController.gost =(Gost) Client.getInstance().login((Korisnik)RootGostController.gost);
		prikaziRacun();
	}

	public void prikaziRacun(){
		AnchorPane headAnchorPane = new AnchorPane();
		Label nazivL = new Label(rb.getString("naziv"));
		AnchorPane.setLeftAnchor(nazivL,50.0);
		Label datumL = new Label(rb.getString("datum"));
		AnchorPane.setLeftAnchor(datumL,350.0);
		Label cijenaL = new Label(rb.getString("cijena"));
		AnchorPane.setRightAnchor(cijenaL, 50.0);
		
		headAnchorPane.getChildren().addAll(nazivL,datumL,cijenaL);
		headAnchorPane.getStyleClass().add("artikal");
		vBox.getChildren().add(headAnchorPane);
		
		for(Stavka s : RootGostController.gost.getRacun().getStavke()){
			AnchorPane anchorPane = new AnchorPane();
			anchorPane.setPrefHeight(30);
			String nazivStr = new String();
			nazivStr = s.getUsluga().getNaziv();
			Label naziv = new Label(nazivStr);
			AnchorPane.setTopAnchor(naziv,10.0);
			AnchorPane.setLeftAnchor(naziv,20.0);
			Label datum = new Label(TemporalStringConverters.toString(s.getDatum()));
			AnchorPane.setTopAnchor(datum,10.0);
			AnchorPane.setLeftAnchor(datum,310.0);
			
			Label cijena = new Label(s.getUsluga().getCijena()+" EUR");
			AnchorPane.setTopAnchor(cijena,10.0);
			AnchorPane.setRightAnchor(cijena,35.0);
			
			anchorPane.getChildren().addAll(naziv,datum,cijena);
			anchorPane.getStyleClass().add("artikal");
			vBox.getChildren().add(anchorPane);
			this.anchorPane.setPrefHeight(this.anchorPane.getPrefHeight()+36);
		}
		double cijena = RootGostController.gost.getRacun().getUkupnaCijena();
		cijenaLabel.setText(String.format("%s: %3.2f EUR", rb.getString("cijena"),cijena));
		double procenat = RootGostController.gost.getRacun().getPopust().getProcenat();
		popustLabel.setText(String.format("%s %3.1f%%", rb.getString("popust"),procenat));
		double ukupnaCijena = cijena*((100-procenat)*0.01);
		ukupnaCijenaLabel.setText(String.format("%s %3.2f EUR",rb.getString("ukupnaCijena"),ukupnaCijena));
	}
}
