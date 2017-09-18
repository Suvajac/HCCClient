package net.etfbl.hcc.controller.gost.usluge;

import java.net.URL;
import java.util.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import net.etfbl.hcc.*;
import net.etfbl.hcc.controller.gost.*;
import net.etfbl.hcc.model.*;

public class SobaController extends AbstractUslugaController implements Initializable{
	@FXML
	private VBox hranaVBox;
	@FXML
	private VBox piceVBox;
	@FXML
	private Label brojacLabel;
	@FXML
	private Label dostavaUSobuLabel;
	@FXML
	private Label hranaLabel;
	@FXML
	private Label piceLabel;
	@FXML
	private Button pospremanjeSobeButton;
	@FXML
	private Button veserajButton;
	@FXML
	private Button naruciDostavuButton;
	
	public void initialize(URL url,ResourceBundle rb){
		this.rb = rb;
		super.brojacLabel = this.brojacLabel;
		dostavaUSobuLabel.setText(rb.getString("dostavaUSobuLabel"));
		hranaLabel.setText(rb.getString("hranaLabel"));
		piceLabel.setText(rb.getString("piceLabel"));
		veserajButton.setText(rb.getString("veserajButton"));
		pospremanjeSobeButton.setText(rb.getString("pospremanjeSobeButton"));
		naruciDostavuButton.setText(rb.getString("naruciDostavuButton"));

		mapaLabelProizvod = new HashMap<>();
		korpa = new Korpa();
		
		if(UslugaController.meni==null)
			UslugaController.meni = Client.getInstance().getProizvodi();
		
		prikaziMeni(hranaVBox,piceVBox);
		brojacLabel.setText("");
	}
	
	@FXML
	public void handlePospremanjeSobe(){
		SobnaUsluga usluga = new SobnaUsluga(0,"Sobna usluga",0,"Pospremanje sobe");
		naruci(usluga);
	}
	
	@FXML
	public void handleVeseraj(){
		SobnaUsluga usluga = new SobnaUsluga(0,"Sobna usluga",0,"Veseraj");
		naruci(usluga);
	}
	
	@FXML
	public void handleNaruciDostavu(){
		SobnaUsluga usluga = new SobnaUsluga(0,"Sobna usluga",korpa.getUkupnaCijena(),"Dostava");
		usluga.setListaProizvoda(korpa.getListaProizvoda());
		naruci(usluga);
	}	
}
