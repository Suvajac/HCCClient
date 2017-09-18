package net.etfbl.hcc.controller.gost.usluge;

import java.net.URL;
import java.time.LocalTime;
import java.util.*;
import com.jfoenix.controls.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import net.etfbl.hcc.*;
import net.etfbl.hcc.controller.gost.*;
import net.etfbl.hcc.model.*;
import net.etfbl.hcc.util.TemporalStringConverters;

public class RestoranController extends AbstractUslugaController implements Initializable{
	@FXML
	private VBox hranaVBox;
	@FXML
	private VBox piceVBox;
	@FXML
	private JFXTimePicker vrijemeTimePicker;
	@FXML
	private JFXComboBox<Integer> brojStolicaComboBox;
	@FXML
	private Label brojacLabel;
	@FXML
	private Label restoranLabel;
	@FXML
	private Label hranaLabel;
	@FXML
	private Label piceLabel;
	@FXML
	private Label vrijemeLabel;
	@FXML
	private Label brojStolicaLabel;
	@FXML
	private Button naruciButton;

	public void initialize(URL url, ResourceBundle rb){
		this.rb = rb;
		super.brojacLabel = this.brojacLabel;
		restoranLabel.setText(rb.getString("restoranLabel"));
		hranaLabel.setText(rb.getString("hranaLabel"));
		piceLabel.setText(rb.getString("piceLabel"));
		vrijemeLabel.setText(rb.getString("vrijemeLabel"));
		brojStolicaLabel.setText(rb.getString("brojStolicaLabel"));
		naruciButton.setText(rb.getString("naruciButton"));
		
		mapaLabelProizvod = new HashMap<>();
		korpa = new Korpa();

		if(UslugaController.meni==null)
			UslugaController.meni = Client.getInstance().getProizvodi();

		brojStolicaComboBox.getItems().addAll(2,3,4,5,6,7,8,9,10);
		brojStolicaComboBox.setValue(2);
		vrijemeTimePicker.setValue(LocalTime.now().plusMinutes(30));
		prikaziMeni(hranaVBox,piceVBox);
		brojacLabel.setText("");
	}

	@FXML
	public void handleNaruci(){
		String vrijeme = TemporalStringConverters.toString(vrijemeTimePicker.getValue());
		UslugaRestorana usluga = new UslugaRestorana(0,"Usluga restorana",korpa.getUkupnaCijena(),vrijeme,brojStolicaComboBox.getValue());
		usluga.setListaProizvoda(korpa.getListaProizvoda());
	
		naruci(usluga);
	}
	
}
