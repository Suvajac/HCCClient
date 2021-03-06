package net.etfbl.hcc.controller.recepcionar.tabs;

import java.time.LocalDateTime;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.etfbl.hcc.Client;
import net.etfbl.hcc.model.Oglas;
import net.etfbl.hcc.util.ColumnResizer;
import net.etfbl.hcc.util.TemporalStringConverters;
import net.etfbl.hcc.view.recepcionar.Dialogs;

public class OglasiRecepcionarController implements RefreshableController {

	private ObservableList<Oglas> list;

	@FXML
	private TableView<Oglas> table;

	@FXML
	private TableColumn<Oglas, String> colDatum;

	@FXML
	private TableColumn<Oglas, String> colTekst;

	@FXML
	private Button btnKreiraj;

	@FXML
	private Button btnObrisi;

	@FXML
	void initialize() {
		
		// Otvori oglas u novom dijalogu nakon dvoklika
		table.setRowFactory(param -> {
		    TableRow<Oglas> row = new TableRow<>();
		    row.setOnMouseClicked(e -> {
		    	if (e.getClickCount() == 2 && !row.isEmpty()) {
		    		showOglasDialog(row.getItem());
			    }
		    });
			return row;
		 });

		colDatum.setCellValueFactory(
				param -> new SimpleStringProperty(TemporalStringConverters.toString(param.getValue().getDatum())));
		colTekst.setCellValueFactory(
				param -> new SimpleStringProperty(param.getValue().getPoruka()));

		refresh();
		ColumnResizer.resize(new Double[] {30.0, 70.0}, table);
	}

	@FXML
	void handleKreiraj(ActionEvent event) {
		showOglasDialog(new Oglas(0, null, LocalDateTime.now()));
	}

	@FXML
	void handleObrisi(ActionEvent event) {
		Oglas oglas = table.getSelectionModel().getSelectedItem();
		if (oglas != null) {
			ButtonType type = Dialogs.showConfirmationDialog("Potvrda", "Potvrda",
					"Da li zaista zelite da obrisete dati oglas?");
			if (ButtonType.OK.equals(type)) {
				if (Client.getInstance().obrisiOglas(oglas)) {
					table.getItems().remove(oglas);
				} else {
					Dialogs.showErrorDialog("Greska", "Greska", 
							"Desila se greska prilikom brisanja oglasa.");
				}
			}
		}
	}

	@Override
	public void refresh() {
		list = FXCollections.observableArrayList(Client.getInstance().getOglasi());
		list.sort((o1,o2) -> o2.getDatum().compareTo(o1.getDatum()));
		table.setItems(list);
	}
	
	private void showOglasDialog(Oglas oglas) {
		OglasDialog dialog = new OglasDialog(oglas);
		dialog.showAndWait();
	}

	private class OglasDialog {

		private Stage primaryStage;

		public OglasDialog(Oglas oglas) {
			
			TextArea textArea = new TextArea();
			textArea.setText(oglas.getPoruka());
			textArea.setPrefWidth(400);
			textArea.setPrefHeight(300);
			textArea.setWrapText(true);

			Button btnConfirm = new Button("Potvrdi");
			if (oglas.getIdOglasa() != 0) {
				textArea.setEditable(false);
				btnConfirm.setDisable(true);
			}
			btnConfirm.setOnAction(e -> {
				int idOglasa = 0;
				if (oglas.getPoruka() == null) {
					oglas.setPoruka(textArea.getText());
				}
				if ((idOglasa = Client.getInstance().dodajOglas(oglas)) != -1) {
					oglas.setIdOglasa(idOglasa);
					list.add(oglas);
				}  else {
					Dialogs.showErrorDialog("Greska", "Greska", 
							"Desila se greska prilikom dodavanja oglasa.");
				}
				primaryStage.close();
			});

			Button btnClose = new Button("Zatvori");
			btnClose.setOnAction(e -> primaryStage.close());

			HBox hbButtons = new HBox();
			hbButtons.setAlignment(Pos.CENTER_RIGHT);
			hbButtons.setSpacing(10);
			hbButtons.getChildren().addAll(btnConfirm, btnClose);

			VBox vbContent = new VBox();
			vbContent.setPadding(new Insets(10, 10, 10, 10));
			vbContent.setSpacing(10);
			vbContent.setAlignment(Pos.CENTER);
			vbContent.getChildren().addAll(textArea, hbButtons);

			Scene scene = new Scene(vbContent);
			primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.setTitle("Oglas");
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.setResizable(false);
		}

		public void showAndWait() {
			primaryStage.showAndWait();
		}

	}

}
