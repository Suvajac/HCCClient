package net.etfbl.hcc.controller.gost.usluge;

import java.net.URL;
import java.time.*;
import java.util.*;
import com.jfoenix.controls.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import net.etfbl.hcc.model.*;

public class WellnessController extends AbstractUslugaController implements Initializable{
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
		
		naruci(usluga);
	}

	public void setStackPane(StackPane stackPane) {
		this.stackPane = stackPane;
	}
	
	
}
