package net.etfbl.hcc.controller.recepcionar.tabs;

import java.time.LocalDateTime;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
				new ObavjestenjeTest("Najkrace moguce obavjestenje ikada", LocalDateTime.now()),
				new ObavjestenjeTest("Najkrace moguce obavjestenje ikada", LocalDateTime.now()),
				new ObavjestenjeTest("Najkrace moguce obavjestenje ikada", LocalDateTime.now()),
				new ObavjestenjeTest("Najkrace moguce obavjestenje ikada", LocalDateTime.now()));
		table.setItems(list);
		ColumnResizer.resize(new Double[]{40.0, 40.0, 10.0, 10.0}, table);
	}

	private void handleShow(ObavjestenjeTest o) {
		o.setRead(false);
		table.refresh();
	}

}

class ObavjestenjeTest {

	private boolean read;
	private String text;
	private LocalDateTime timestamp;

	public ObavjestenjeTest() {
		super();
	}

	public ObavjestenjeTest(String text, LocalDateTime timestamp) {
		super();
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

}
