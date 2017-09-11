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
	private Tab oglasnaTablaTab;
	@FXML
	private Tab popustiTab;
	@FXML
	private Tab racunTab;
	@FXML
	private Tab knjigaUtisakaTab;
	
    

    public void initialize(){
    	try{
    		StackPane uslugeStackPane = (StackPane) FXMLLoader.load(Main.class.getResource("view/gost/usluga.fxml"));
    		uslugeTab.setContent(uslugeStackPane);
    		
    		AnchorPane racunAnchorPane=(AnchorPane) FXMLLoader.load(Main.class.getResource("view/gost/racunGost.fxml"));
    		racunTab.setContent(racunAnchorPane);
    		
    		AnchorPane popustiAnchorPane=(AnchorPane) FXMLLoader.load(Main.class.getResource("view/gost/popustiGost.fxml"));
    		popustiTab.setContent(popustiAnchorPane);
    		
    		StackPane knjigaUtisakaStackPane = (StackPane) FXMLLoader.load(Main.class.getResource("view/gost/knjigaUtisakaGost.fxml"));
    		knjigaUtisakaTab.setContent(knjigaUtisakaStackPane);
    		
    		StackPane oglasnaTablaStackPane = (StackPane) FXMLLoader.load(Main.class.getResource("view/gost/oglasnaTablaGost.fxml"));
    		oglasnaTablaTab.setContent(oglasnaTablaStackPane);
    	}
    	catch(IOException e){
    		e.printStackTrace();
    	}
    }
}
