package net.etfbl;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.LoginController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/login.fxml"));
        AnchorPane anchorPane = loader.load();

        LoginController loginController = loader.getController();
        loginController.setPrimaryStage(primaryStage);

        Scene scene = new Scene(anchorPane);
        primaryStage.setTitle("Hotel - Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
