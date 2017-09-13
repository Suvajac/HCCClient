package net.etfbl.hcc.view.gost.usluge;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.jfoenix.controls.JFXDatePicker;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import net.etfbl.hcc.Client;
import net.etfbl.hcc.model.WellnessTermin;
import net.etfbl.hcc.model.WellnessUsluga;
import net.etfbl.hcc.view.gost.RootGostController;
import net.etfbl.hcc.view.recepcionar.Dialogs;

public class WellnessController {
	@FXML
	private JFXDatePicker datumDatePicker;
	@FXML
	private ComboBox<String> vrijemeComboBox;
	
	public void initialize(){
		datumDatePicker.setValue(LocalDate.now());
		vrijemeComboBox.getItems().addAll("09:00","10:00","11:00","12:00","13:00","14:00","15:00",
				"16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00");
	}
	
	@FXML
	private void handleNaruci(){
		Date date = Date.from(datumDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		WellnessTermin termin = new WellnessTermin(0,date,vrijemeComboBox.getValue());
		WellnessUsluga usluga = new WellnessUsluga(0,"Wellness usluga",40);
		usluga.setWellnessTermin(termin);
		int returnValue = -1;
		if(Dialogs.showConfirmationDialog("Conf", "asdasd", "asdas").equals(ButtonType.OK)){
			returnValue = Client.getInstance().dodajUslugu(usluga,RootGostController.gost.getRacun());
		}
		if(returnValue>0){
			System.out.println(returnValue);
		}
		else{
			System.out.println("Pogresan termin");		
		}
	}
}
