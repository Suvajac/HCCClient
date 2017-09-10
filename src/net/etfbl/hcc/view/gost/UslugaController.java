package net.etfbl.hcc.view.gost;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import net.etfbl.hcc.Main;
import net.etfbl.hcc.view.gost.usluge.RestoranController;
import net.etfbl.hcc.view.gost.usluge.SobaController;
import net.etfbl.hcc.view.gost.usluge.SportController;

public class UslugaController {
	@FXML
	public StackPane stackPane;
	@FXML
    private JFXButton restoranButton;
    @FXML
    private JFXButton sobaButton;
    @FXML
    private JFXButton  sportButton;
    @FXML
    private JFXButton wellnessButton;
    @FXML
    private AnchorPane infoAnchorPane;
    
    public void initialize(){
    	try{
    		handleRestoranButton();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    public void handleRestoranButton() throws Exception{
    	FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/gost/usluge/restoran.fxml"));
    	AnchorPane restoranAnchorPane = (AnchorPane) loader.load();
    	
    	RestoranController controller = loader.getController();
    	controller.setStackPane(stackPane);
    	
    	infoAnchorPane.getChildren().clear();
    	infoAnchorPane.getChildren().add(restoranAnchorPane);
    	addClassOnButton(restoranButton);
  }

  public void handleSportButton() throws Exception{
//      	AnchorPane sportAnchorPane = (AnchorPane) FXMLLoader.load(Main.class.getResource("view/gost/usluge/sport.fxml"));
//      	infoAnchorPane.getChildren().clear();
//      	infoAnchorPane.getChildren().add(sportAnchorPane);
//      	addClassOnButton(sportButton);
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/gost/usluge/sport.fxml"));
	  	AnchorPane sportAnchorPane = (AnchorPane) loader.load();
	  	
	  	SportController controller = loader.getController();
	  	controller.setStackPane(stackPane);
	  	
	  	infoAnchorPane.getChildren().clear();
	  	infoAnchorPane.getChildren().add(sportAnchorPane);
	  	addClassOnButton(sportButton);
  }

  public void handleWellnessButton() throws Exception{
      AnchorPane sportAnchorPane = (AnchorPane) FXMLLoader.load(Main.class.getResource("view/gost/usluge/wellness.fxml"));
      infoAnchorPane.getChildren().clear();
      infoAnchorPane.getChildren().add(sportAnchorPane);
      addClassOnButton(wellnessButton);
  }

  public void handleSobaButton() throws Exception{
//      AnchorPane sportAnchorPane = (AnchorPane) FXMLLoader.load(Main.class.getResource("view/gost/usluge/soba.fxml"));
//      infoAnchorPane.getChildren().clear();
//      infoAnchorPane.getChildren().add(sportAnchorPane);
//      addClassOnButton(sobaButton);
	  
	  	FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/gost/usluge/soba.fxml"));
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
