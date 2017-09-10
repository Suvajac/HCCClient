package net.etfbl.hcc.controller.recepcionar.tabs;

import java.time.LocalDate;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.etfbl.hcc.util.ColumnResizer;
import net.etfbl.hcc.util.TemporalStringConverters;

public class OglasiRecepcionarController {
	
	private ObservableList<OglasTest> list;

    @FXML
    private TableView<OglasTest> table;

    @FXML
    private TableColumn<OglasTest, String> colDatum;

    @FXML
    private TableColumn<OglasTest, String> colTekst;

    @FXML
    private Button btnKreiraj;

    @FXML
    private Button btnObrisi;
    
    @FXML
    void initialize() {
		// Set cell value factories
		colDatum.setCellValueFactory(
				param -> new SimpleStringProperty(TemporalStringConverters.toString(param.getValue().getDatum())));
		colTekst.setCellValueFactory(
				param -> new SimpleStringProperty(param.getValue().getTekst()));

		// Populate table
		list = FXCollections.observableArrayList();
		list.addAll(
				new OglasTest(LocalDate.now(), "Tekstualni sadrzaj"),
				new OglasTest(LocalDate.now(), "Tekstualni sadrzaj"),
				new OglasTest(LocalDate.now(), "Tekstualni sadrzaj"),
				new OglasTest(LocalDate.now(), "Tekstualni sadrzaj"),
				new OglasTest(LocalDate.now(), "Tekstualni sadrzaj"));
		table.setItems(list);
		ColumnResizer.resize(new Double[] {30.0, 70.0}, table);
    }

    @FXML
    void handleKreiraj(ActionEvent event) {
    	OglasDialog dialog = new OglasDialog();
    	dialog.showAndWait();
    }

    @FXML
    void handleObrisi(ActionEvent event) {
    	OglasTest oglas = table.getSelectionModel().getSelectedItem();
    	if (oglas != null) {
    		table.getItems().remove(oglas);
    	}
    }
    
	private class OglasDialog {

		private Stage primaryStage;

		public OglasDialog() {

			TextArea textArea = new TextArea();
			textArea.setPrefWidth(400);
			textArea.setPrefHeight(300);
			textArea.setWrapText(true);

			Button btnConfirm = new Button("Potvrdi");
			btnConfirm.setOnAction(e -> {
				OglasTest oglas = new OglasTest(LocalDate.now(), textArea.getText());
				table.getItems().add(oglas);
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

class OglasTest {
	
	private static int counter;
	private Integer id;
	private LocalDate datum;
	private String tekst;
	
	public OglasTest() {
		super();
		counter++;
		id = counter;
	}

	public OglasTest(LocalDate datum, String tekst) {
		this();
		this.datum = datum;
		this.tekst = tekst;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		OglasTest other = (OglasTest) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
}
