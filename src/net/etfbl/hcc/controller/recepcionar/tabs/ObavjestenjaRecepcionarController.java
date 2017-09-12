package net.etfbl.hcc.controller.recepcionar.tabs;

import java.time.LocalDateTime;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.etfbl.hcc.util.ColumnResizer;
import net.etfbl.hcc.util.TemporalStringConverters;

public class ObavjestenjaRecepcionarController {
	
	private ObservableList<ObavjestenjeTest> list;

	@FXML
	private TableView<ObavjestenjeTest> table;

	@FXML
	private TableColumn<ObavjestenjeTest, String> colVrijeme;

	@FXML
	private TableColumn<ObavjestenjeTest, String> colTekst;

	@FXML
	private TableColumn<ObavjestenjeTest, String> colProcitano;

	@FXML
	private TableColumn<ObavjestenjeTest, String> colAkcija;

	@FXML
	void initialize() {
		
		colVrijeme.setCellValueFactory(
				param -> new SimpleStringProperty(TemporalStringConverters.toString(param.getValue().getVrijeme())));
		colTekst.setCellValueFactory(
				param -> new SimpleStringProperty(param.getValue().getTekst()));
		colProcitano.setCellValueFactory(
				param -> new SimpleStringProperty(param.getValue().isProcitano() ? "Da" : "Ne"));
		colAkcija.setCellFactory(
				param -> {
					final TableCell<ObavjestenjeTest, String> cell = new TableCell<ObavjestenjeTest, String>() {
		
						final Button btnDetaljno = new Button("Detaljno");
		
						@Override
						public void updateItem(String item, boolean empty) {
							super.updateItem(item, empty);
							if (empty) {
								setGraphic(null);
								setText(null);
							} else {
								ObavjestenjeTest o = getTableView().getItems().get(getIndex());
								btnDetaljno.setOnAction(event -> handleShow(o));
								setGraphic(btnDetaljno);
								setText(null);
							}
						}
					};
					return cell;
				});

		list = FXCollections.observableArrayList();
		list.addAll(
				new ObavjestenjeTest("Tekstualni sadrzaj", LocalDateTime.now()),
				new ObavjestenjeTest("Tekstualni sadrzaj", LocalDateTime.now()),
				new ObavjestenjeTest("Tekstualni sadrzaj", LocalDateTime.now()),
				new ObavjestenjeTest("Tekstualni sadrzaj", LocalDateTime.now()),
				new ObavjestenjeTest("Tekstualni sadrzaj", LocalDateTime.now()));
		table.setItems(list);
		ColumnResizer.resize(new Double[]{20.0, 60.0, 10.0, 10.0}, table);
	}
	
	/*
	 * Prikazuje dato obavjestenje u novom dijalogu.
	 */
	private void handleShow(ObavjestenjeTest o) {
		o.setProcitano(true);
		table.refresh();
		ObavjestenjeDialog dialog = new ObavjestenjeDialog(o);
		dialog.showAndWait();
	}
	
	private class ObavjestenjeDialog {
		
		private Stage primaryStage;
		
		public ObavjestenjeDialog(ObavjestenjeTest obavjestenje) {

			Label label = new Label("Vrijeme:");
			label.setStyle("-fx-font-weight: bold;");
			Label lblTimestamp = new Label("" + TemporalStringConverters.toString(obavjestenje.getVrijeme()));
			
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
			primaryStage.setTitle("Obavjestenje " + obavjestenje.getId());
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.setResizable(false);
		}
		
		public void showAndWait() {
			primaryStage.showAndWait();
		}

	}

}

class ObavjestenjeTest {
	
	private static int counter;
	
	private int id;
	private boolean procitano;
	private String tekst;
	private LocalDateTime vrijeme;

	public ObavjestenjeTest() {
		super();
		counter++;
		id = counter;
	}

	public ObavjestenjeTest(String tekst, LocalDateTime vrijeme) {
		this();
		this.tekst = tekst;
		this.vrijeme = vrijeme;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isProcitano() {
		return procitano;
	}

	public void setProcitano(boolean procitano) {
		this.procitano = procitano;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public LocalDateTime getVrijeme() {
		return vrijeme;
	}

	public void setVrijeme(LocalDateTime vrijeme) {
		this.vrijeme = vrijeme;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObavjestenjeTest other = (ObavjestenjeTest) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
