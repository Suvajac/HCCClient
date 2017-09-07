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
import javafx.scene.control.cell.PropertyValueFactory;

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
		colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		colText.setCellValueFactory(new PropertyValueFactory<>("text"));

		colRead.setCellValueFactory(param -> {
			SimpleStringProperty property = new SimpleStringProperty();
			String value = param.getValue().read ? "Da" : "Ne";
			property.setValue(value);
			return property;
		});
		colAction.setCellValueFactory(new PropertyValueFactory<>("dummy"));
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
				new ObavjestenjeTest(LocalDateTime.now(), "Najkrace moguce obavjestenje ikada"),
				new ObavjestenjeTest(LocalDateTime.now(), "Najkrace moguce obavjestenje ikada"),
				new ObavjestenjeTest(LocalDateTime.now(), "Najkrace moguce obavjestenje ikada"),
				new ObavjestenjeTest(LocalDateTime.now(), "Najkrace moguce obavjestenje ikada"));
		table.setItems(list);
	}

	private void handleShow(ObavjestenjeTest o) {
		o.read = true;
		table.refresh();
	}

}

class ObavjestenjeTest {

	LocalDateTime timestamp;
	String text;
	boolean read;

	public ObavjestenjeTest() {
		
	}

	public ObavjestenjeTest(LocalDateTime timestamp, String text) {
		this.timestamp = timestamp;
		this.text = text;
	}

}
