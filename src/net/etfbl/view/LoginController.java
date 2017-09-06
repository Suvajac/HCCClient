package net.etfbl.view;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import main.Main;

public class LoginController {
    @FXML
    private JFXTextField usernameTextField;
    @FXML
    private JFXPasswordField passwordPasswordField;

    private Stage primaryStage;

    public void handleLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/rootLayout.fxml"));
        BorderPane borderPane = loader.load();
        Stage stage = new Stage();

        RootLayoutController controller = loader.getController();
        controller.setStage(stage);
        controller.setUsername(usernameTextField.getText());

        FXMLLoader tabLoader = new FXMLLoader(Main.class.getResource("/view/tabPane.fxml"));
        JFXTabPane tabPane = tabLoader.load();


        borderPane.setCenter(tabPane);

        Scene scene = new Scene(borderPane);

        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public void handleCancel(){
        primaryStage.close();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
