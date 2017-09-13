package net.etfbl.hcc.controller.recepcionar.tabs;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.etfbl.hcc.Client;
import net.etfbl.hcc.model.Gost;
import net.etfbl.hcc.util.TemporalStringConverters;

public class GostiRecepcionarController implements RefreshableController {

	private ObservableList<Gost> list;
	
	@FXML
	private TableView<Gost> table;
	
    @FXML
    private TableColumn<Gost, String> colIme;

    @FXML
    private TableColumn<Gost, String> colPrezime;

    @FXML
    private TableColumn<Gost, String> colUsername;

    @FXML
    private TableColumn<Gost, String> colBrojTelefona;

    @FXML
    private TableColumn<Gost, String> colBrojSobe;

    @FXML
    private TableColumn<Gost, String> colDatumOd;

    @FXML
    private TableColumn<Gost, String> colDatumDo;

    @FXML
    private Button btnRacun;
    
    @FXML
    private Button btnRegistracija;
    
    @FXML
    void initialize() {
    	
    	colIme.setCellValueFactory(
    			new PropertyValueFactory<>("ime"));
    	colPrezime.setCellValueFactory(
    			new PropertyValueFactory<>("prezime"));
    	colUsername.setCellValueFactory(
    			new PropertyValueFactory<>("username"));
    	colBrojTelefona.setCellValueFactory(
    			new PropertyValueFactory<>("brojTelefona"));
    	colBrojSobe.setCellValueFactory(param -> 
    			new SimpleStringProperty("" + param.getValue().getSoba().getBrSobe())
		);
		colDatumOd.setCellValueFactory(param -> {
			LocalDate datum = ((Date)param.getValue().getDatumOd()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			return new SimpleStringProperty(TemporalStringConverters.toString(datum));
		});
		colDatumDo.setCellValueFactory(param -> {
			LocalDate datum = ((Date)param.getValue().getDatumDo()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			return new SimpleStringProperty(TemporalStringConverters.toString(datum));
		});

		refresh();
    }
    
    @FXML
    void handleRacun(ActionEvent event) {
    	Gost gost = table.getSelectionModel().getSelectedItem();
    	if (gost != null) {
	    	RacunRecepcionarController controller = 
	    			loadDialog("Racun", "/net/etfbl/hcc/view/recepcionar/tabs/RacunRecepcionarView.fxml");
	    	controller.ucitajRacun(gost);
    	}
    }

    @FXML
    void handleRegistracija(ActionEvent event) {
    	loadDialog("Registracija gosta", "/net/etfbl/hcc/view/recepcionar/tabs/RegistracijaRecepcionarView.fxml");
    }
    
	@Override
	public void refresh() {
		list = FXCollections.observableArrayList(Client.getInstance().getGosti());
    	table.setItems(list);
	}
    
    /*
     * Ucitava dati fxml u novi dijalog i prikazuje ga. Vraca controller za dati fxml.
     */
    private <T> T loadDialog(String title, String fxml) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
    		Parent root = loader.load();
    		Scene scene = new Scene(root);
    		Stage stage = new Stage();
    		stage.setScene(scene);
    		stage.setTitle(title);
    		stage.initModality(Modality.APPLICATION_MODAL);
    		stage.setResizable(true);
    		stage.show();
    		return loader.getController();
    	} catch (IOException ex) {
    		ex.printStackTrace();
    	}
		return null;
    }

}
