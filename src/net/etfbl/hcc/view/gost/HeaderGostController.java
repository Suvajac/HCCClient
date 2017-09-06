package net.etfbl.hcc.view.gost;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HeaderGostController {
	 @FXML
	 private JFXButton rsButton;
	 @FXML
	 private JFXButton enButton;
	 @FXML
	 private Label usernameLabel;
	 
	 private Stage stage;
	 
	 public void initialize(){
	        handleRS();
	    }

	    public void handleRS(){
	        enButton.getStyleClass().remove("lanSelected");
	        if(!rsButton.getStyleClass().contains("lanSelected"))
	            rsButton.getStyleClass().add("lanSelected");
	    }

	    public void handleEN(){
	        rsButton.getStyleClass().remove("lanSelected");
	        if(!enButton.getStyleClass().contains("lanSelected"))
	            enButton.getStyleClass().add("lanSelected");
	    }

	    public void handleLogout(){
	        stage.close();
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
}