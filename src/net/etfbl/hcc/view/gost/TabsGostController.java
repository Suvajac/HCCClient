package net.etfbl.hcc.view.gost;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
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
    		
    		ScrollPane racunScrollPane=(ScrollPane) FXMLLoader.load(Main.class.getResource("view/gost/racunGost.fxml"));
    		racunTab.setContent(racunScrollPane);
    		racunTab.setOnSelectionChanged((e) -> {
    			try{
    				if(racunTab.selectedProperty().get()){
    		    		ScrollPane scrollPane=(ScrollPane) FXMLLoader.load(Main.class.getResource("view/gost/racunGost.fxml"));
		    			racunTab.setContent(scrollPane);
    				}
    			}
    			catch(IOException ex){
    				ex.printStackTrace();
    			}
    		});
    		
    		AnchorPane popustiAnchorPane=(AnchorPane) FXMLLoader.load(Main.class.getResource("view/gost/popustiGost.fxml"));
    		popustiTab.setContent(popustiAnchorPane);
    		
    		StackPane knjigaUtisakaStackPane = (StackPane) FXMLLoader.load(Main.class.getResource("view/gost/knjigaUtisakaGost.fxml"));
    		knjigaUtisakaTab.setContent(knjigaUtisakaStackPane);
    		knjigaUtisakaTab.setOnSelectionChanged((e) -> {
    			try{
    				if(knjigaUtisakaTab.selectedProperty().get()){
		    			StackPane stackPane = (StackPane) FXMLLoader.load(Main.class.getResource("view/gost/knjigaUtisakaGost.fxml"));
		        		knjigaUtisakaTab.setContent(stackPane);
    				}
    			}
    			catch(IOException ex){
    				ex.printStackTrace();
    			}
    		});
    		
    		StackPane oglasnaTablaStackPane = (StackPane) FXMLLoader.load(Main.class.getResource("view/gost/oglasnaTablaGost.fxml"));
    		oglasnaTablaTab.setContent(oglasnaTablaStackPane);
    		oglasnaTablaTab.setOnSelectionChanged((e) -> {
    			try{
    				if(oglasnaTablaTab.selectedProperty().get()){
	    				StackPane stackPane = (StackPane) FXMLLoader.load(Main.class.getResource("view/gost/oglasnaTablaGost.fxml"));
	            		oglasnaTablaTab.setContent(stackPane);
    				}
    			}
    			catch(IOException ex){
    				ex.printStackTrace();
    			}
    		});
    	}
    	catch(IOException e){
    		e.printStackTrace();
    	}
    }
}
