package net.etfbl.hcc.view.gost;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import net.etfbl.hcc.Main;

public class UslugaController {
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
    	AnchorPane restoranAncorPane = (AnchorPane) FXMLLoader.load(Main.class.getResource("view/gost/usluge/restoran.fxml"));
    	infoAnchorPane.getChildren().clear();
    	infoAnchorPane.getChildren().add(restoranAncorPane);
    	addClassOnButton(restoranButton);
  }

  public void handleSportButton() throws Exception{
      	AnchorPane sportAnchorPane = (AnchorPane) FXMLLoader.load(Main.class.getResource("view/gost/usluge/sport.fxml"));
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
      AnchorPane sportAnchorPane = (AnchorPane) FXMLLoader.load(Main.class.getResource("view/gost/usluge/soba.fxml"));
      infoAnchorPane.getChildren().clear();
      infoAnchorPane.getChildren().add(sportAnchorPane);
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
