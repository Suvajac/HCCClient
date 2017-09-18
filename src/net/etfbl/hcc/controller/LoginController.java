package net.etfbl.hcc.controller;

import net.etfbl.hcc.Client;
import net.etfbl.hcc.ClientMulticast;
import net.etfbl.hcc.Main;
import net.etfbl.hcc.controller.gost.RootGostController;
import net.etfbl.hcc.controller.recepcionar.RootRecepcionarController;
import net.etfbl.hcc.model.Gost;
import net.etfbl.hcc.model.Korisnik;
import net.etfbl.hcc.model.Recepcionar;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController {
	public static boolean en;
	
	@FXML
	private JFXTextField usernameTextField;
	@FXML
	private JFXPasswordField passwordPasswordField;
	@FXML
	private Label nevalidanLoginLabel;

	private ResourceBundle rb;
	private Stage primaryStage;

	@FXML
	private void initialize() {
		setRS();
		nevalidanLoginLabel.setVisible(false);
	}

	public void handleLogin() throws IOException {
		Korisnik k = new Korisnik(usernameTextField.getText(), null, null, null, passwordPasswordField.getText().hashCode()+"");
		Korisnik ulogovanKorisnik = Client.getInstance().login(k);
		if(ulogovanKorisnik!=null && ulogovanKorisnik instanceof Gost){
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/gost/rootGost.fxml"),rb);
			StackPane stackPane = (StackPane) loader.load();
			Stage stage = new Stage();

			RootGostController controller = loader.getController();
			RootGostController.gost = (Gost) ulogovanKorisnik;
			controller.setStage(stage);
			controller.setLoginController(this);
			controller.init();

			Scene scene = new Scene(stackPane);

			stage.setScene(scene);
			stage.setFullScreen(true);
			stage.show();
			nevalidanLoginLabel.setVisible(false);
		}
		else if(ulogovanKorisnik!=null && ulogovanKorisnik instanceof Recepcionar){
			new ClientMulticast();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/net/etfbl/hcc/view/recepcionar/RootRecepcionarView.fxml"));
			Parent root = loader.load();

			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);

			RootRecepcionarController controller = loader.getController();
			controller.setTrenutniKorisnik(ulogovanKorisnik.getUsername());
			controller.setPrimaryStage(stage);

			stage.show();
			nevalidanLoginLabel.setVisible(false);
		}
		else {
			nevalidanLoginLabel.setVisible(true);
		}

	}

	public void setEN(){
		Locale locale = new Locale("en", "EN");
		rb= ResourceBundle.getBundle("net/etfbl/hcc/util/MessagesBundle",locale);
		en=true;
	}

	public void setRS(){
		Locale locale = new Locale("rs", "RS");
		rb = ResourceBundle.getBundle("net/etfbl/hcc/util/MessagesBundle",locale);
		en=false;
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
