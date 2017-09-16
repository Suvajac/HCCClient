package net.etfbl.hcc.controller.recepcionar.tabs;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.etfbl.hcc.Client;
import net.etfbl.hcc.model.Utisak;
import net.etfbl.hcc.util.ColumnResizer;
import net.etfbl.hcc.util.TemporalStringConverters;
import net.etfbl.hcc.view.recepcionar.Dialogs;

public class KnjigaUtisakaRecepcionarController implements RefreshableController {

	private ObservableList<Utisak> list;

	@FXML
	private TableView<Utisak> table;

	@FXML
	private TableColumn<Utisak, String> colDatum;

	@FXML
	private TableColumn<Utisak, String> colKorisnik;

	@FXML
	private TableColumn<Utisak, String> colTekst;

	@FXML
	private Button btnObrisi;

	@FXML
	void initialize() {

		colDatum.setCellValueFactory(param -> 
				new SimpleStringProperty(TemporalStringConverters.toString(param.getValue().getDatum())));
		colKorisnik.setCellValueFactory(param -> 
				new SimpleStringProperty(param.getValue().getKorisnik().getUsername()));
		colTekst.setCellValueFactory(
				new PropertyValueFactory<>("tekst"));

		refresh();
		ColumnResizer.resize(new Double[] {25.0, 25.0, 50.0}, table);

	}

	@FXML
	void handleObrisi(ActionEvent event) {
		Utisak utisak = table.getSelectionModel().getSelectedItem();
		if (utisak != null) {
			ButtonType type = Dialogs.showConfirmationDialog("Potvrda", "Potvrda",
					"Da li zaista zelite da obrisete dati utisak?");
			if (ButtonType.OK.equals(type)) {
				if (Client.getInstance().obrisiUtisak(utisak)) {
					list.remove(utisak);
				} else {
					Dialogs.showErrorDialog("Greska", "Greska", "Desila se greska prilikom brisanja utiska.");
				}
			}
		}
	}

	@Override
	public void refresh() {
		list = FXCollections.observableArrayList(Client.getInstance().getUtisci());
		table.setItems(list);
	}

}
