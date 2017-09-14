package net.etfbl.hcc.controller.recepcionar.tabs;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.etfbl.hcc.Client;
import net.etfbl.hcc.model.Gost;
import net.etfbl.hcc.model.Racun;
import net.etfbl.hcc.model.Stavka;
import net.etfbl.hcc.util.TemporalStringConverters;
import net.etfbl.hcc.view.recepcionar.Dialogs;

public class RacunRecepcionarController {
	
	private Gost gost;
	
	private ObservableList<Stavka> list;
	
	@FXML
	private AnchorPane root;
	
	@FXML
	private Label lblGost;

    @FXML
    private TableView<Stavka> table;

    @FXML
    private TableColumn<Stavka, String> colNaziv;
    
    @FXML
    private TableColumn<Stavka, String> colDatum;

    @FXML
    private TableColumn<Stavka, String> colCijena;

    @FXML
    private TextField tfUkupnaCijena;

    @FXML
    private Button btnPlati;
    
    @FXML
    private Button btnZatvori;    
    
    @FXML
    void initialize() {
    	
    	colNaziv.setCellValueFactory(param ->
    			new SimpleStringProperty(param.getValue().getUsluga().getNaziv()));
    	colDatum.setCellValueFactory(param ->
				new SimpleStringProperty(TemporalStringConverters.toString(param.getValue().getDatum())));
    	colCijena.setCellValueFactory(param ->
    			new SimpleStringProperty("" + param.getValue().getUsluga().getCijena()));
    	 	
    	tfUkupnaCijena.prefWidthProperty().bind(colCijena.widthProperty());
    	btnPlati.setDisable(true);
    }

    @FXML
    void handlePlati(ActionEvent event) {
    	ButtonType type = Dialogs.showConfirmationDialog("Potvrda", "Potvrda", "Da li zaista zelite da naplatite racun?");
    	if (ButtonType.OK.equals(type)) {
			Racun racun = gost.getRacun();
			if (!racun.isPlacen()) {
				if (Client.getInstance().platiRacun(racun)) {
					racun.setPlacen(true);
					Dialogs.showInfoDialog("Informacija", "Informacija",
							"Racun za gosta \"" + gost.getIme() + " " + gost.getPrezime() + "\" je uspjesno placen!");
				}
			} else {
				Dialogs.showInfoDialog("Informacija", "Informacija",
						"Racun za gosta \"" + gost.getIme() + " " + gost.getPrezime() + "\" je vec placen!");
			}
    	}
    }
    
    @FXML
    void handleZatvori(ActionEvent event) {
    	((Stage) root.getScene().getWindow()).close(); 
    }
    
    public void ucitajRacun(Gost gost) {
    	this.gost = gost;
    	lblGost.setText(gost.getIme() + " " + gost.getPrezime());
    	list = FXCollections.observableArrayList(gost.getRacun().getStavke());
    	table.setItems(list);
    	tfUkupnaCijena.setText("" + list.stream().mapToDouble(e -> e.getUsluga().getCijena()).sum());
    	if (!list.isEmpty()) // Omoguci placanje samo ako racun nije prazan
    		btnPlati.setDisable(false);
    }

}
