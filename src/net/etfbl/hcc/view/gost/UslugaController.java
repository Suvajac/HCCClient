package net.etfbl.hcc.view.gost;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import net.etfbl.hcc.Main;

public class UslugaController {
	@FXML
    private JFXButton meniButton;
    @FXML
    private JFXButton sobaButton;
    @FXML
    private JFXButton  sportButton;
    @FXML
    private JFXButton wellnessButton;
    @FXML
    private AnchorPane infoAnchorPane;
    
    public void handleMeniButton() throws Exception{
    	AnchorPane menuAncorPane = (AnchorPane) FXMLLoader.load(Main.class.getResource("view/gost/usluge/menuAnchorPane.fxml"));
    	infoAnchorPane.getChildren().clear();
    	infoAnchorPane.getChildren().add(menuAncorPane);
    	addClassOnButton(meniButton);
  }

  public void handleSportButton() throws Exception{
      	AnchorPane sportAnchorPane = (AnchorPane) FXMLLoader.load(Main.class.getResource("view/gost/usluge/sportAnchorPane.fxml"));
      	infoAnchorPane.getChildren().clear();
      	infoAnchorPane.getChildren().add(sportAnchorPane);
      	addClassOnButton(sportButton);
  }

  public void handleWellnessButton() throws Exception{
      AnchorPane sportAnchorPane = (AnchorPane) FXMLLoader.load(Main.class.getResource("view/gost/usluge/wellnessAnchorPane.fxml"));
      infoAnchorPane.getChildren().clear();
      infoAnchorPane.getChildren().add(sportAnchorPane);
      addClassOnButton(wellnessButton);
  }

  public void handleSobaButton() throws Exception{
      AnchorPane sportAnchorPane = (AnchorPane) FXMLLoader.load(Main.class.getResource("view/gost/usluge/sobaAnchorPane.fxml"));
      infoAnchorPane.getChildren().clear();
      infoAnchorPane.getChildren().add(sportAnchorPane);
      addClassOnButton(sobaButton);
  }

  public void addClassOnButton(JFXButton button){
      meniButton.getStyleClass().remove("selectedButton");
      sportButton.getStyleClass().remove("selectedButton");
      sobaButton.getStyleClass().remove("selectedButton");
      wellnessButton.getStyleClass().remove("selectedButton");

      button.getStyleClass().add("selectedButton");
  }
}
