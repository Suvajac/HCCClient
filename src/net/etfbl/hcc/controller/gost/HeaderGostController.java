package net.etfbl.hcc.controller.gost;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import net.etfbl.hcc.*;
import net.etfbl.hcc.controller.LoginController;

public class HeaderGostController implements Initializable{
	@FXML
    private JFXButton rsButton;
    @FXML
    private JFXButton enButton;
    @FXML
    private Label usernameLabel;

    private Stage stage;
    private StackPane stackPane;
    private LoginController loginController;
    private ResourceBundle rb;

    public void initialize(URL url,ResourceBundle rb){
        this.rb = rb;
        usernameLabel.setText(RootGostController.gost.getUsername());
    }

    public void init(){
        setActiveLocale();
    }
    
    @FXML
    public void handleRS(){
        if(LoginController.en){
            try{
                handleLogout();
                loginController.setRS();
                loginController.handleLogin();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void handleEN(){
        if(!LoginController.en){
            try{
                handleLogout();
                loginController.setEN();
                loginController.handleLogin();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
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
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/gost/settings.fxml"),rb);
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

    public void setActiveLocale(){
        if(LoginController.en){
            rsButton.getStyleClass().remove("lanSelected");
            if(!enButton.getStyleClass().contains("lanSelected"))
                enButton.getStyleClass().add("lanSelected");
        }
        else{
            enButton.getStyleClass().remove("lanSelected");
            if(!rsButton.getStyleClass().contains("lanSelected"))
                rsButton.getStyleClass().add("lanSelected");
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
    
    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
}
