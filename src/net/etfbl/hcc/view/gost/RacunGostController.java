package net.etfbl.hcc.view.gost;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import net.etfbl.hcc.model.Proizvod;
import net.etfbl.hcc.model.Stavka;
import net.etfbl.hcc.model.Usluga;
import net.etfbl.hcc.util.TemporalStringConverters;

public class RacunGostController {
	@FXML
	private BorderPane borderPane;
	@FXML
	private AnchorPane anchorPane;
	@FXML
	protected VBox vBox;
	@FXML
	protected Label ukupnoLabel;
	
	protected Map<Proizvod, Label> mapaProizvoda;
	protected StackPane stackPane;
	protected AnchorPane parent;
	protected Label brojacLabel;
	private ArrayList<Stavka> listaStavki;
	
	public void initialize(){
		listaStavki = new ArrayList<>();
		for(int i=0;i<10;i++){
			Stavka s = new Stavka(0,LocalDateTime.now(),new Usluga(0,"Usluga restorana",23));
			Stavka s2 = new Stavka(0,LocalDateTime.now(),new Usluga(0,"Sport usluga",23));
			listaStavki.add(s);
			listaStavki.add(s2);
		}
		prikaziRacun();
	}

	public void prikaziRacun(){
		AnchorPane headAnchorPane = new AnchorPane();
		Label nazivL = new Label("Naziv");
		AnchorPane.setLeftAnchor(nazivL,50.0);
		Label datumL = new Label("Datum");
		AnchorPane.setLeftAnchor(datumL,350.0);
		Label cijenaL = new Label("Cijena");
		AnchorPane.setRightAnchor(cijenaL, 50.0);
		
		headAnchorPane.getChildren().addAll(nazivL,datumL,cijenaL);
		headAnchorPane.getStyleClass().add("artikal");
		vBox.getChildren().add(headAnchorPane);
		
		for(Stavka s : listaStavki){
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
				this.anchorPane.setPrefHeight(this.anchorPane.getPrefHeight()+22);
		}
		ukupnoLabel.setText(String.format("Ukupna cijena: %3.2f EUR",RootGostController.gost.getRacun().getUkupnaCijena()));
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
