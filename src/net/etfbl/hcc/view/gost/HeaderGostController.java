package net.etfbl.hcc.view.gost;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import net.etfbl.hcc.Client;
import net.etfbl.hcc.Main;

public class HeaderGostController {
	 @FXML
	 private JFXButton rsButton;
	 @FXML
	 private JFXButton enButton;
	 @FXML
	 private Label usernameLabel;
	 
	 private Stage stage;
	 private StackPane stackPane;
	 
	 public void initialize(){
		 	usernameLabel.setText(RootGostController.gost.getUsername());
	        handleRS();
	    }
	 	@FXML
	    public void handleRS(){
	        enButton.getStyleClass().remove("lanSelected");
	        if(!rsButton.getStyleClass().contains("lanSelected"))
	            rsButton.getStyleClass().add("lanSelected");
	    }
	    @FXML
	    public void handleEN(){
	        rsButton.getStyleClass().remove("lanSelected");
	        if(!enButton.getStyleClass().contains("lanSelected"))
	            enButton.getStyleClass().add("lanSelected");
	    }

	    @FXML
	    public void handleLogout(){
	    	UslugaController.meni = null;
	    	UslugaController.oprema = null;
	    	Client.getInstance().logout();
	        stage.close();
	    }
	    
	    @FXML
	    public void handleSettings(){
	    	try{
	    		FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/gost/settings.fxml"));
	    		AnchorPane settingsAnchorPane = (AnchorPane) loader.load();
	    		
	    		SettingsController controller = loader.getController();
	    		controller.setStackPane(stackPane);
	    		controller.setAnchorPane(settingsAnchorPane);
	    		
	    		stackPane.getChildren().add(settingsAnchorPane);
	    		settingsAnchorPane.toFront();
	    	}
	    	catch(IOException e){
	    		e.printStackTrace();
	    	}
	    }

	    public Stage getStage() {
	        return stage;
	    }

	      public void setUsername(String username){
	      usernameLabel.setText(username);
	    }

	    public void setStage(Stage stage) {
	        this.stage = stage;
	    }

		public void setStackPane(StackPane stackPane) {
			this.stackPane = stackPane;
		}
	    
	    
}
