package net.etfbl.hcc.view.gost;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import net.etfbl.hcc.Main;
import net.etfbl.hcc.model.*;
import net.etfbl.hcc.view.gost.usluge.*;

public class UslugaController implements Initializable{
	public static ArrayList<Proizvod> meni;
	public static List<SportskaOprema> oprema;
	
	@FXML
	public StackPane stackPane;
	@FXML
    private Label uslugaRestoranaLabel;
	@FXML
    private JFXButton restoranButton;
	@FXML
    private Label sobneUslugeLabel;
    @FXML
    private JFXButton sobaButton;
    @FXML
    private Label sportUslugeLabel;
    @FXML
    private JFXButton  sportButton;
    @FXML
    private Label wellnesUslugeLabel;
    @FXML
    private JFXButton wellnessButton;
    @FXML
    private AnchorPane infoAnchorPane;
  	
	private ResourceBundle rb;
	
    public void initialize(URL url,ResourceBundle rb){
    	try{
    		this.rb = rb;
    		uslugaRestoranaLabel.setText(rb.getString("uslugaRestoranaLabel"));
    		sobneUslugeLabel.setText(rb.getString("sobneUslugeLabel"));
    		sportUslugeLabel.setText(rb.getString("sportUslugeLabel"));
    		wellnesUslugeLabel.setText(rb.getString("wellnesUslugeLabel"));
    		handleRestoranButton();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    public void handleRestoranButton() throws Exception{
    	FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/gost/usluge/restoran.fxml"),rb);
    	AnchorPane restoranAnchorPane = (AnchorPane) loader.load();
    	
    	RestoranController controller = loader.getController();
    	controller.setStackPane(stackPane);
    	
    	infoAnchorPane.getChildren().clear();
    	infoAnchorPane.getChildren().add(restoranAnchorPane);
    	addClassOnButton(restoranButton);
  }

  public void handleSportButton() throws Exception{
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/gost/usluge/sport.fxml"),rb);
	  	AnchorPane sportAnchorPane = (AnchorPane) loader.load();
	  	
	  	SportController controller = loader.getController();
	  	controller.setStackPane(stackPane);
	  	
	  	infoAnchorPane.getChildren().clear();
	  	infoAnchorPane.getChildren().add(sportAnchorPane);
	  	addClassOnButton(sportButton);
  }

  public void handleWellnessButton() throws Exception{      
      FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/gost/usluge/wellness.fxml"),rb);
	  AnchorPane wellnessAnchorPane = (AnchorPane) loader.load();
	  	
	  WellnessController controller = loader.getController();
	  controller.setStackPane(stackPane);
	  
	  infoAnchorPane.getChildren().clear();
      infoAnchorPane.getChildren().add(wellnessAnchorPane);
      addClassOnButton(wellnessButton);
  }

  public void handleSobaButton() throws Exception{
	  	FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/gost/usluge/soba.fxml"),rb);
	  	AnchorPane sobaAnchorPane = (AnchorPane) loader.load();
	  	
	  	SobaController controller = loader.getController();
	  	controller.setStackPane(stackPane);
	  	
	  	infoAnchorPane.getChildren().clear();
	  	infoAnchorPane.getChildren().add(sobaAnchorPane);
	  	addClassOnButton(sobaButton);
  }

  public void addClassOnButton(JFXButton button){
      restoranButton.getStyleClass().remove("selectedButton");
      sportButton.getStyleClass().remove("selectedButton");
      sobaButton.getStyleClass().remove("selectedButton");
      wellnessButton.getStyleClass().remove("selectedButton");
      button.getStyleClass().add("selectedButton");
  }
}
