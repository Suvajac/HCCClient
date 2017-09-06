package net.etfbl.hcc.view.gost;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import net.etfbl.hcc.Main;

import java.io.IOException;

public class TabsGostController {
	@FXML
	private Tab uslugeTab;
	@FXML
	private Tab obavjestenjaTab;
	@FXML
	private Tab popustiTab;
	@FXML
	private Tab racunTab;
	@FXML
	private Tab knjigaUtisakaTab;
	
    

    public void initialize(){
    	try{
    		AnchorPane uslugeAnchorPane = (AnchorPane) FXMLLoader.load(Main.class.getResource("view/gost/usluga.fxml"));
    		uslugeTab.setContent(uslugeAnchorPane);
    		
    		ScrollPane knjigaUtisakaScrollPane = (ScrollPane) FXMLLoader.load(Main.class.getResource("view/gost/knjigaUtisakaGost.fxml"));
    		knjigaUtisakaTab.setContent(knjigaUtisakaScrollPane);
    	}
    	catch(IOException e){
    		e.printStackTrace();
    	}
    }
}
