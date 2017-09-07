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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.etfbl.hcc.util.ColumnResizer;

public class ObavjestenjaRecepcionarController {

	@FXML
	private TableView<ObavjestenjeTest> table;

	@FXML
	private TableColumn<ObavjestenjeTest, String> colDate;

	@FXML
	private TableColumn<ObavjestenjeTest, String> colText;

	@FXML
	private TableColumn<ObavjestenjeTest, String> colRead;

	@FXML
	private TableColumn<ObavjestenjeTest, String> colAction;

	@FXML
	void initialize() {
		// Set cell value factories
		colDate.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getTimestamp().toString()));
		colText.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getText()));
		colRead.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().isRead() ? "Da" : "Ne"));
		colAction.setCellFactory(param -> {
			final TableCell<ObavjestenjeTest, String> cell = new TableCell<ObavjestenjeTest, String>() {

				final Button btnShow = new Button("Detaljno");

				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (empty) {
						setGraphic(null);
						setText(null);
					} else {
						ObavjestenjeTest o = getTableView().getItems().get(getIndex());
						btnShow.setOnAction(event -> handleShow(o));
						setGraphic(btnShow);
						setText(null);
					}
				}
			};
			return cell;
		});

		// Populate table
		ObservableList<ObavjestenjeTest> list = FXCollections.observableArrayList();
		list.addAll(
				new ObavjestenjeTest("Tekstualni sadrzaj", LocalDateTime.now()),
				new ObavjestenjeTest("Tekstualni sadrzaj", LocalDateTime.now()),
				new ObavjestenjeTest("Tekstualni sadrzaj", LocalDateTime.now()),
				new ObavjestenjeTest("Tekstualni sadrzaj", LocalDateTime.now()));
		table.setItems(list);
		ColumnResizer.resize(new Double[]{20.0, 60.0, 10.0, 10.0}, table);
	}

	private void handleShow(ObavjestenjeTest o) {
		o.setRead(true);
		table.refresh();
		ObavjestenjeDialog dialog = new ObavjestenjeDialog(o);
		dialog.showAndWait();
	}

	private class ObavjestenjeDialog {
		
		private Stage primaryStage;
		
		public ObavjestenjeDialog(ObavjestenjeTest obavjestenje) {

			Label lblId = new Label("ID: " + obavjestenje.getId());
			Label lblTimestamp = new Label("Vrijeme: " + obavjestenje.getTimestamp());
			
			HBox hbInfo = new HBox();
			hbInfo.setSpacing(10);
			hbInfo.getChildren().addAll(lblId, lblTimestamp);
			
			TextArea textArea = new TextArea(obavjestenje.getText());
			textArea.setEditable(false);
			textArea.setPrefWidth(400);
			textArea.setPrefHeight(300);
			
			Button btnClose = new Button("Zatvori");
			btnClose.setOnAction(e -> primaryStage.close());
			
			VBox vbContent = new VBox();
			vbContent.setPadding(new Insets(10, 10, 10, 10));
			vbContent.setSpacing(10);
			vbContent.setAlignment(Pos.CENTER);
			vbContent.getChildren().addAll(hbInfo, textArea, btnClose);
			
			Scene scene = new Scene(vbContent);
			primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.setTitle("Obavjestenje");
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
	private boolean read;
	private String text;
	private LocalDateTime timestamp;

	public ObavjestenjeTest() {
		super();
		counter++;
		id = counter;
	}

	public ObavjestenjeTest(String text, LocalDateTime timestamp) {
		this();
		this.text = text;
		this.timestamp = timestamp;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
