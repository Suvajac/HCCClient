package net.etfbl.hcc.controller;

import net.etfbl.hcc.Main;
import net.etfbl.hcc.view.gost.RootGostController;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {
	@FXML
	private JFXTextField usernameTextField;
	@FXML
	private JFXPasswordField passwordPasswordField;

	private Stage primaryStage;

	public void handleLogin() throws IOException {
		
		if(usernameTextField.getText().equals("g")){
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/gost/rootGost.fxml"));
			BorderPane borderPane = loader.load();
			Stage stage = new Stage();

			RootGostController controller = loader.getController();
			controller.setStage(stage);
			controller.init();
			
			
			Scene scene = new Scene(borderPane);

			stage.setScene(scene);
			stage.setFullScreen(true);
			stage.show();
		}
		else if(usernameTextField.getText().equals("r")){
			Parent root = FXMLLoader.load(getClass().getResource("/net/etfbl/hcc/view/recepcionar/RootRecepcionarView.fxml"));
			Scene scene = new Scene(root);
			
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		}
		
	}

	public void handleCancel() {
		primaryStage.close();
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
}
