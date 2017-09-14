package net.etfbl.hcc.view.gost;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import net.etfbl.hcc.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TabsGostController implements Initializable{
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
	
	@Override
	public void initialize(URL url, ResourceBundle rb){
    	try{
    		StackPane uslugeStackPane = (StackPane) FXMLLoader.load(Main.class.getResource("view/gost/usluga.fxml"),rb);
    		uslugeTab.setText(rb.getString("uslugeTab"));
    		uslugeTab.setContent(uslugeStackPane);
    		
    		ScrollPane racunScrollPane=(ScrollPane) FXMLLoader.load(Main.class.getResource("view/gost/racunGost.fxml"),rb);
    		racunTab.setContent(racunScrollPane);
    		racunTab.setText(rb.getString("racunTab"));
    		racunTab.setOnSelectionChanged((e) -> {
    			try{
    				if(racunTab.selectedProperty().get()){
    		    		ScrollPane scrollPane=(ScrollPane) FXMLLoader.load(Main.class.getResource("view/gost/racunGost.fxml"),rb);
		    			racunTab.setContent(scrollPane);
    				}
    			}
    			catch(IOException ex){
    				ex.printStackTrace();
    			}
    		});
    		
    		AnchorPane popustiAnchorPane=(AnchorPane) FXMLLoader.load(Main.class.getResource("view/gost/popustiGost.fxml"),rb);
    		popustiTab.setContent(popustiAnchorPane);
    		popustiTab.setText(rb.getString("popustiTab"));
    		
    		StackPane knjigaUtisakaStackPane = (StackPane) FXMLLoader.load(Main.class.getResource("view/gost/knjigaUtisakaGost.fxml"),rb);
    		knjigaUtisakaTab.setContent(knjigaUtisakaStackPane);
    		knjigaUtisakaTab.setText(rb.getString("knjigaUtisakaTab"));
    		knjigaUtisakaTab.setOnSelectionChanged((e) -> {
    			try{
    				if(knjigaUtisakaTab.selectedProperty().get()){
		    			StackPane stackPane = (StackPane) FXMLLoader.load(Main.class.getResource("view/gost/knjigaUtisakaGost.fxml"),rb);
		        		knjigaUtisakaTab.setContent(stackPane);
    				}
    			}
    			catch(IOException ex){
    				ex.printStackTrace();
    			}
    		});
    		
    		StackPane oglasnaTablaStackPane = (StackPane) FXMLLoader.load(Main.class.getResource("view/gost/oglasnaTablaGost.fxml"),rb);
    		oglasnaTablaTab.setContent(oglasnaTablaStackPane);
    		oglasnaTablaTab.setText(rb.getString("oglasnaTablaTab"));
    		oglasnaTablaTab.setOnSelectionChanged((e) -> {
    			try{
    				if(oglasnaTablaTab.selectedProperty().get()){
	    				StackPane stackPane = (StackPane) FXMLLoader.load(Main.class.getResource("view/gost/oglasnaTablaGost.fxml"),rb);
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
