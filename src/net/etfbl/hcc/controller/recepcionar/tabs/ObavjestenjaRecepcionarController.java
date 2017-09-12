package net.etfbl.hcc.controller.recepcionar.tabs;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.etfbl.hcc.model.Obavjestenje;
import net.etfbl.hcc.util.ColumnResizer;
import net.etfbl.hcc.util.TemporalStringConverters;

public class ObavjestenjaRecepcionarController {
	
	private ObservableList<Obavjestenje> list;

	@FXML
	private TableView<Obavjestenje> table;

	@FXML
	private TableColumn<Obavjestenje, String> colVrijeme;

	@FXML
	private TableColumn<Obavjestenje, String> colTekst;

	@FXML
	private TableColumn<Obavjestenje, String> colProcitano;

	@FXML
	private TableColumn<Obavjestenje, String> colAkcija;

	@FXML
	void initialize() {
		
		colVrijeme.setCellValueFactory(
				param -> new SimpleStringProperty(TemporalStringConverters.toString(param.getValue().getDatum())));
		colTekst.setCellValueFactory(
				param -> new SimpleStringProperty(param.getValue().getTekst()));
		colProcitano.setCellValueFactory(
				param -> new SimpleStringProperty(param.getValue().isProcitano() ? "Da" : "Ne"));
		colAkcija.setCellFactory(
				param -> {
					final TableCell<Obavjestenje, String> cell = new TableCell<Obavjestenje, String>() {
		
						final Button btnDetaljno = new Button("Detaljno");
		
						@Override
						public void updateItem(String item, boolean empty) {
							super.updateItem(item, empty);
							if (empty) {
								setGraphic(null);
								setText(null);
							} else {
								Obavjestenje o = getTableView().getItems().get(getIndex());
								btnDetaljno.setOnAction(event -> handleShow(o));
								setGraphic(btnDetaljno);
								setText(null);
							}
						}
					};
					return cell;
				});

		list = FXCollections.observableArrayList();
		table.setItems(list);
		ColumnResizer.resize(new Double[]{20.0, 60.0, 10.0, 10.0}, table);
	}
	
	/*
	 * Prikazuje dato obavjestenje u novom dijalogu.
	 */
	private void handleShow(Obavjestenje o) {
		//o.setProcitano(true);
		table.refresh();
		ObavjestenjeDialog dialog = new ObavjestenjeDialog(o);
		dialog.showAndWait();
	}
	
	private class ObavjestenjeDialog {
		
		private Stage primaryStage;
		
		public ObavjestenjeDialog(Obavjestenje obavjestenje) {

			Label label = new Label("Vrijeme:");
			label.setStyle("-fx-font-weight: bold;");
			Label lblTimestamp = new Label("" + TemporalStringConverters.toString(obavjestenje.getDatum()));
			
			HBox hbHeader = new HBox();
			hbHeader.setSpacing(5);
			hbHeader.getChildren().addAll(label, lblTimestamp);
			
			TextArea textArea = new TextArea(obavjestenje.getTekst());
			textArea.setEditable(false);
			textArea.setPrefWidth(400);
			textArea.setPrefHeight(300);
			textArea.setWrapText(true);
			
			Button btnClose = new Button("Zatvori");
			btnClose.setOnAction(e -> primaryStage.close());
			
			VBox vbContent = new VBox();
			vbContent.setPadding(new Insets(10, 10, 10, 10));
			vbContent.setSpacing(10);
			vbContent.setAlignment(Pos.CENTER);
			vbContent.getChildren().addAll(hbHeader, textArea, btnClose);
			
			Scene scene = new Scene(vbContent);
			primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.setTitle("Obavjestenje " + obavjestenje.getIdObavjestenje());
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.setResizable(false);
		}
		
		public void showAndWait() {
			primaryStage.showAndWait();
		}

	}

}

