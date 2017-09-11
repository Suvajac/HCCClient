package net.etfbl.hcc.view.gost;

import net.etfbl.hcc.view.gost.StavkaNaRacunu;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;

public class RacunGostController{
	
	
	
    @FXML
    public TableView<StavkaNaRacunu> tabela=new TableView<>();
    
    @FXML
    public TableColumn<StavkaNaRacunu,String> naziv;
    
    @FXML
    public TableColumn<StavkaNaRacunu,String> datum;
    
    @FXML
    public  TableColumn<StavkaNaRacunu,Double> cijena;
    
    @FXML
    public TextField ukupno;
    
    public void initialize(){
    	
    
    naziv=new TableColumn<>("Naziv");
    naziv.setMinWidth(100);
    naziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
    
    datum=new TableColumn<>("Datum");
    datum.setMinWidth(100);
    datum.setCellValueFactory(new PropertyValueFactory<>("datum"));
    
    cijena=new TableColumn<StavkaNaRacunu,Double>("Cijena");
    cijena.setMinWidth(100);
    cijena.setCellValueFactory(new PropertyValueFactory<>("cijena"));
    
    ObservableList<StavkaNaRacunu> list = getStavkeNaRacunu();
    tabela.setItems(list);

    
    tabela.getColumns().addAll(naziv,datum,cijena);
	
    }
    
    private ObservableList<StavkaNaRacunu> getStavkeNaRacunu() {
    	 
        StavkaNaRacunu stavka1=new StavkaNaRacunu("Coca Cola","30.03.2003",5.00);
        StavkaNaRacunu stavka2=new StavkaNaRacunu("Æevapi","31.03.2003",20.00);
        StavkaNaRacunu stavka3=new StavkaNaRacunu("Kobasice","01.04.2003",3.00);
        
        double suma=stavka1.getCijena()+stavka2.getCijena()+stavka3.getCijena();
        ukupno.setText(Double.toString(suma));
   
        ObservableList<StavkaNaRacunu> lista = FXCollections.observableArrayList(stavka1,stavka2,stavka3);
        return lista;
    }
}
	




