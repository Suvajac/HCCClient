package net.etfbl.hcc.controller.recepcionar.tabs;

import java.time.LocalDate;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import net.etfbl.hcc.util.ColumnResizer;
import net.etfbl.hcc.util.TemporalStringConverters;

public class KnjigaUtisakaRecepcionarController {
	
	private ObservableList<UtisakTest> list;

    @FXML
    private TableView<UtisakTest> table;

    @FXML
    private TableColumn<UtisakTest, String> colDatum;

    @FXML
    private TableColumn<UtisakTest, String> colKorisnik;

    @FXML
    private TableColumn<UtisakTest, String> colTekst;

    @FXML
    private Button btnObrisi;
    
    @FXML
    void initialize() {
    	// Set cell value factories
    	colDatum.setCellValueFactory(
    			param -> new SimpleStringProperty(TemporalStringConverters.toString(param.getValue().getDatum())));
    	colKorisnik.setCellValueFactory(
    			param -> new SimpleStringProperty(param.getValue().getKorisnik()));
    	colTekst.setCellValueFactory(
    			param -> new SimpleStringProperty(param.getValue().getTekst()));
    	
    	// Populate table
    	list = FXCollections.observableArrayList();
    	list.addAll(
    			new UtisakTest(LocalDate.now(), "NekiKorisnik", "Ovo je najgori hotel ikada! :D"),
    			new UtisakTest(LocalDate.now(), "NekiKorisnik", "Ovo je najgori hotel ikada! :D"),
    			new UtisakTest(LocalDate.now(), "NekiKorisnik", "Ovo je najgori hotel ikada! :D"),
    			new UtisakTest(LocalDate.now(), "NekiKorisnik", "Ovo je najgori hotel ikada! :D"),
    			new UtisakTest(LocalDate.now(), "NekiKorisnik", "Ovo je najgori hotel ikada! :D"));
    	table.setItems(list);
    	ColumnResizer.resize(new Double[]{25.0, 25.0, 50.0}, table);
    			
    }

    @FXML
    void handleObrisi(ActionEvent event) {
    	UtisakTest utisak = table.getSelectionModel().getSelectedItem();
    	if (utisak != null) {
    		list.remove(utisak);
    	}
    }

}

class UtisakTest {
	
	private LocalDate datum;
	private String korisnik;
	private String tekst;
	
	public UtisakTest() {
		super();
	}

	public UtisakTest(LocalDate datum, String korisnik, String tekst) {
		super();
		this.datum = datum;
		this.korisnik = korisnik;
		this.tekst = tekst;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	public String getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(String korisnik) {
		this.korisnik = korisnik;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}
	
}
