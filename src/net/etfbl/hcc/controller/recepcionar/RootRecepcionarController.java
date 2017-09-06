package net.etfbl.hcc.controller.recepcionar;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

public class RootRecepcionarController {

	@FXML
	private Label lblUsername;

	@FXML
	private Button btnLogout;

	@FXML
	private Tab tabObavjestenja;

	@FXML
	private Tab tabRegistracija;

	@FXML
	private Tab tabRacun;

	@FXML
	private Tab tabPopusti;

	@FXML
	private Tab tabOglasi;

	@FXML
	private Tab tabDodajProizvod;

	@FXML
	private Tab tabKnjigaUtisaka;

	@FXML
	void initialize() {

		// Load fxmls into tabs
		try {
			loadIntoTab(tabRegistracija, "/net/etfbl/hcc/view/recepcionar/tabs/RegistracijaRecepcionarView.fxml");
			loadIntoTab(tabRacun, "/net/etfbl/hcc/view/recepcionar/tabs/RacunRecepcionarView.fxml");
			loadIntoTab(tabPopusti, "/net/etfbl/hcc/view/recepcionar/tabs/PopustiRecepcionarView.fxml");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void handleLogout(ActionEvent event) {
		
	}
	
	private void loadIntoTab(Tab tab, String fxml) throws IOException {
		Node root = FXMLLoader.load(getClass().getResource(fxml));
		AnchorPane pane = (AnchorPane) tab.getContent();
		pane.getChildren().add(root);
		AnchorPane.setBottomAnchor(root, 0.0);
		AnchorPane.setLeftAnchor(root, 0.0);
		AnchorPane.setRightAnchor(root, 0.0);
		AnchorPane.setTopAnchor(root, 0.0);
	}

}
