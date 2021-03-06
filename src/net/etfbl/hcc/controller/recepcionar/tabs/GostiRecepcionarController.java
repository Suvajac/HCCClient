package net.etfbl.hcc.controller.recepcionar.tabs;

import java.io.IOException;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.etfbl.hcc.Client;
import net.etfbl.hcc.model.Registracija;
import net.etfbl.hcc.util.TemporalStringConverters;

public class GostiRecepcionarController implements RefreshableController {

	static ObservableList<Registracija> list;

	@FXML
	private TableView<Registracija> table;

    @FXML
    private TableColumn<Registracija, String> colIme;

    @FXML
    private TableColumn<Registracija, String> colPrezime;

    @FXML
    private TableColumn<Registracija, String> colUsername;

    @FXML
    private TableColumn<Registracija, String> colBrojTelefona;

    @FXML
    private TableColumn<Registracija, String> colBrojSobe;

    @FXML
    private TableColumn<Registracija, String> colDatumOd;

    @FXML
    private TableColumn<Registracija, String> colDatumDo;

    @FXML
    private Button btnRacun;

    @FXML
    private Button btnRegistracija;

    @FXML
    void initialize() {

    	colIme.setCellValueFactory(
    			param -> new SimpleStringProperty(param.getValue().getGost().getIme()));
    	colPrezime.setCellValueFactory(
    			param -> new SimpleStringProperty(param.getValue().getGost().getPrezime()));
    	colUsername.setCellValueFactory(
    			param -> new SimpleStringProperty(param.getValue().getGost().getUsername()));
    	colBrojTelefona.setCellValueFactory(
    			param -> new SimpleStringProperty(param.getValue().getGost().getBrojTelefona()));
    	colBrojSobe.setCellValueFactory(
    			param -> new SimpleStringProperty("" + param.getValue().getSoba().getBrSobe()));
		colDatumOd.setCellValueFactory(
				param -> new SimpleStringProperty(TemporalStringConverters.toString(param.getValue().getDatumOd())));
		colDatumDo.setCellValueFactory(
				param -> new SimpleStringProperty(TemporalStringConverters.toString(param.getValue().getDatumDo())));

		refresh();
    }

    @FXML
    void handleRacun(ActionEvent event) {
    	Registracija registracija = table.getSelectionModel().getSelectedItem();
    	if (registracija != null) {
	    	RacunRecepcionarController controller =
	    			loadDialog("Racun", "/net/etfbl/hcc/view/recepcionar/tabs/RacunRecepcionarView.fxml");
	    	controller.ucitajRacun(registracija.getGost());
    	}
    }

    @FXML
    void handleRegistracija(ActionEvent event) {
    	loadDialog("Registracija gosta", "/net/etfbl/hcc/view/recepcionar/tabs/RegistracijaRecepcionarView.fxml");
    }

	@Override
	public void refresh() {
		list = FXCollections.observableArrayList(Client.getInstance().getRegistracije());
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
