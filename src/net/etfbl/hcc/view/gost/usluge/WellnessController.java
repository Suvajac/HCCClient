package net.etfbl.hcc.view.gost.usluge;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import net.etfbl.hcc.Client;
import net.etfbl.hcc.Main;
import net.etfbl.hcc.model.WellnessTermin;
import net.etfbl.hcc.model.WellnessUsluga;
import net.etfbl.hcc.view.gost.PotvrdaAlertController;
import net.etfbl.hcc.view.gost.RootGostController;
import net.etfbl.hcc.view.recepcionar.Dialogs;

public class WellnessController implements Initializable{
	@FXML
	private JFXDatePicker datumDatePicker;
	@FXML
	private ComboBox<String> vrijemeComboBox;
	@FXML
	private Label wellnessTerminLabel;
	@FXML
	private Label datumLabel;
	@FXML
	private Label vrijemeLabel;
	@FXML
	private Button naruciButton;
	
	private StackPane stackPane;
	private ResourceBundle rb;
	
	public void initialize(URL url,ResourceBundle rb){
		this.rb = rb;
		wellnessTerminLabel.setText(rb.getString("wellnessTerminLabel"));
		datumLabel.setText(rb.getString("datumLabel"));
		vrijemeLabel.setText(rb.getString("vrijemeLabel"));
		naruciButton.setText(rb.getString("naruciButton"));
		
		datumDatePicker.setValue(LocalDate.now());
		vrijemeComboBox.getItems().addAll("09:00","10:00","11:00","12:00","13:00","14:00","15:00",
				"16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00");
		vrijemeComboBox.setValue("09:00");
	}
	
	@FXML
	private void handleNaruci(){
		Date date = Date.from(datumDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		WellnessTermin termin = new WellnessTermin(0,date,vrijemeComboBox.getValue());
		WellnessUsluga usluga = new WellnessUsluga(0,"Wellness usluga",40);
		usluga.setWellnessTermin(termin);
		
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/gost/potvrdaAlert.fxml"),rb);
			AnchorPane alertAnchorPane;
			alertAnchorPane = (AnchorPane) loader.load();
			
			PotvrdaAlertController controller = loader.getController();
			controller.setStackPane(stackPane);
			controller.setAnchorPane(alertAnchorPane);
			controller.setUsluga(usluga);
			
			stackPane.getChildren().add(alertAnchorPane);
			alertAnchorPane.toFront();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setStackPane(StackPane stackPane) {
		this.stackPane = stackPane;
	}
	
	
}
