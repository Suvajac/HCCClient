package net.etfbl.hcc.view.gost.usluge;

import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.util.*;
import com.jfoenix.controls.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import net.etfbl.hcc.*;
import net.etfbl.hcc.model.*;
import net.etfbl.hcc.view.gost.*;

public class SportController implements Initializable{
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
	
	private Korpa korpa;
	private Map<Label, Proizvod> mapaLabelProizvod;
	private StackPane stackPane;
	private ResourceBundle rb;
	
	public void initialize(URL url, ResourceBundle rb){
		this.rb = rb;
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
		prikaziMeni();
		brojacLabel.setText("");
	}
	
	@FXML
	public void handleOprema(){
		try{
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/gost/opremaKorpa.fxml"),rb);
			AnchorPane korpaAnchorPane = (AnchorPane) loader.load();
			AnchorPane.setTopAnchor(korpaAnchorPane,5.0);
			AnchorPane.setLeftAnchor(korpaAnchorPane,383.0);
			KorpaController controller = loader.getController();
			controller.setKorpa(korpa);
			controller.prikaziProizvode();
			controller.setStackPane(stackPane);
			controller.setBrojacLabel(brojacLabel);
			
			AnchorPane parent = new AnchorPane();
			controller.setParent(parent);
			
			parent.getChildren().add(korpaAnchorPane);
			stackPane.getChildren().add(parent);
			korpaAnchorPane.toFront();
		}
		catch(IOException e){
			e.printStackTrace();
		}
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
	
	public void prikaziMeni(){
		for(Proizvod p : UslugaController.oprema){
			StringBuilder tackeSb = new StringBuilder();
			for(int i=0;i<50-p.getNaziv().length()-(p.getCijena()+"").length()-((SportskaOprema)p).getVelicina().length();i++){
				tackeSb.append(".");
			}
			Label label = new Label(p.getNaziv()+" vel. "+((SportskaOprema)p).getVelicina()+tackeSb.toString()+p.getCijena()+" EUR");
			label.getStyleClass().add("stavkaLabel");
			label.setOnMouseClicked((e) ->{
				korpa.add(mapaLabelProizvod.get(label));
				if(korpa.getListaProizvoda().size()==0){
					brojacLabel.setText("");
				}
				else{
					brojacLabel.setText(korpa.getListaProizvoda().size()+"");
				}
//				handleOprema();
			});
			mapaLabelProizvod.put(label, p);
			opremaVBox.getChildren().add(label);
		}
	}

	public void setStackPane(StackPane stackPane) {
		this.stackPane = stackPane;
	}
	
	
}
