package net.etfbl.hcc.controller.recepcionar;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.etfbl.hcc.Client;
import net.etfbl.hcc.ClientMulticast;
import net.etfbl.hcc.controller.recepcionar.tabs.RefreshableController;

public class RootRecepcionarController {

	@FXML
	private Stage primaryStage;
	
	@FXML
	private Label lblTrenutniKorisnik;

	@FXML
	private Button btnLogout;

	@FXML
	private Tab tabObavjestenja;

	@FXML
	private Tab tabGosti;

	@FXML
	private Tab tabRacun;

	@FXML
	private Tab tabPopusti;

	@FXML
	private Tab tabOglasi;

	@FXML
	private Tab tabProizvodi;

	@FXML
	private Tab tabKnjigaUtisaka;
	
	private ClientMulticast cm;

	@FXML
	void initialize() {

		// Ucitaj sve tabove
		try {
			loadIntoTab(tabObavjestenja, "/net/etfbl/hcc/view/recepcionar/tabs/ObavjestenjaRecepcionarView.fxml");
			loadIntoTab(tabGosti, "/net/etfbl/hcc/view/recepcionar/tabs/GostiRecepcionarView.fxml");
			loadIntoTab(tabPopusti, "/net/etfbl/hcc/view/recepcionar/tabs/PopustiRecepcionarView.fxml");
			loadIntoTab(tabOglasi, "/net/etfbl/hcc/view/recepcionar/tabs/OglasiRecepcionarView.fxml");
			loadIntoTab(tabProizvodi, "/net/etfbl/hcc/view/recepcionar/tabs/ProizvodiRecepcionarView.fxml");
			loadIntoTab(tabKnjigaUtisaka, "/net/etfbl/hcc/view/recepcionar/tabs/KnjigaUtisakaRecepcionarView.fxml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@FXML
	void handleLogout(ActionEvent event) {
		logout();
	}
	
	public void setTrenutniKorisnik(String username) {
		lblTrenutniKorisnik.setText(username);
	}
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
		primaryStage.setOnCloseRequest(value -> logout());
	}
	
	private void logout() {
		Client.getInstance().logout();
		cm.close();
		primaryStage.close();
	}
	
	/*
	 * Pomocna metoda za ucitavanje fxml fajlova u tabove ovog kontrolera.
	 */
	private void loadIntoTab(Tab tab, String fxml) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
		Parent root = loader.load();
		RefreshableController controller = loader.getController();
		
		AnchorPane pane = (AnchorPane) tab.getContent();
		pane.getChildren().add(root);
		AnchorPane.setBottomAnchor(root, 0.0);
		AnchorPane.setLeftAnchor(root, 0.0);
		AnchorPane.setRightAnchor(root, 0.0);
		AnchorPane.setTopAnchor(root, 0.0);
		
		// Azuriraj podatke svaki put kada se otvori dati tab
		tab.setOnSelectionChanged(e -> {
			if (tab.selectedProperty().get())
				controller.refresh();
		});
	}

	public ClientMulticast getCm() {
		return cm;
	}

	public void setCm(ClientMulticast cm) {
		this.cm = cm;
	}

}
