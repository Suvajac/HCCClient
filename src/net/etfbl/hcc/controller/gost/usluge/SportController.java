package net.etfbl.hcc.controller.gost.usluge;

import java.net.URL;
import java.time.*;
import java.util.*;
import com.jfoenix.controls.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import net.etfbl.hcc.*;
import net.etfbl.hcc.controller.gost.*;
import net.etfbl.hcc.model.*;

public class SportController extends AbstractUslugaController implements Initializable{
	@FXML
	private VBox opremaVBox;
	@FXML
	private JFXDatePicker datumDatePicker;
	@FXML
	private JFXComboBox<String> vrijemeComboBox;
	@FXML
	private Label brojacLabel;
	@FXML
	private Label sportUslugeLabel;
	@FXML
	private Label opremaLabel;
	@FXML
	private Label datumLabel;
	@FXML
	private Label vrijemeLabel;
	@FXML
	private Button naruciButton;
	
	public void initialize(URL url, ResourceBundle rb){
		this.rb = rb;
		super.brojacLabel = this.brojacLabel;
		sportUslugeLabel.setText(rb.getString("sportUslugeLabel"));
		opremaLabel.setText(rb.getString("opremaLabel"));
		datumLabel.setText(rb.getString("datumLabel"));
		vrijemeLabel.setText(rb.getString("vrijemeLabel"));
		naruciButton.setText(rb.getString("naruciButton"));
		
		mapaLabelProizvod = new HashMap<>();
		korpa = new Korpa();
				
		if(UslugaController.oprema==null)
			UslugaController.oprema = Client.getInstance().getSportskaOprema();
		
		datumDatePicker.setValue(LocalDate.now());
		vrijemeComboBox.getItems().addAll("09:00","10:00","11:00","12:00","13:00","14:00","15:00",
				"16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00");
		vrijemeComboBox.setValue("09:00");
		prikaziMeniOpreme(opremaVBox);
		brojacLabel.setText("");
	}
	
	@FXML
	public void handleNaruci(){
		Date date = Date.from(datumDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		SportTermin termin = new SportTermin(0,date,vrijemeComboBox.getValue());
		SportUsluga usluga = new SportUsluga(0,"Sport usluga",korpa.getUkupnaCijena()+10);
		ArrayList<SportskaOprema> listaOpreme = new ArrayList<>();
		for(Proizvod p : korpa.getListaProizvoda()){
			listaOpreme.add((SportskaOprema) p);
		}
		usluga.setListaOpreme(listaOpreme);
		usluga.setSportTermin(termin);
		
		naruci(usluga);
	}	
}
