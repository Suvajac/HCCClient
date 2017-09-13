package net.etfbl.hcc.view.gost;

import java.io.IOException;

import com.jfoenix.controls.JFXTabPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import net.etfbl.hcc.Main;
import net.etfbl.hcc.model.Gost;

public class RootGostController {
	public static Gost gost;
	
	@FXML
	private StackPane stackPane;
	
	@FXML
	private BorderPane borderPane;
	
	private Stage stage;
	
	public void init(){
		try{
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/gost/headerGost.fxml"));
			AnchorPane headerAnchorPane = (AnchorPane) loader.load();
			
			HeaderGostController headerController = loader.getController();
			headerController.setStage(stage);
			headerController.setStackPane(stackPane);
			borderPane.setTop(headerAnchorPane);
			
			FXMLLoader tabLoader = new FXMLLoader(Main.class.getResource("view/gost/tabsGost.fxml"));
			JFXTabPane tabPane = (JFXTabPane) tabLoader.load();
			
			borderPane.setCenter(tabPane);
			borderPane.toFront();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void setStage(Stage stage){
		this.stage = stage;
	}
}
