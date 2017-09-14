package net.etfbl.hcc.controller;

import net.etfbl.hcc.Client;
import net.etfbl.hcc.ClientMulticast;
import net.etfbl.hcc.Main;
import net.etfbl.hcc.controller.recepcionar.RootRecepcionarController;
import net.etfbl.hcc.model.Gost;
import net.etfbl.hcc.model.Korisnik;
import net.etfbl.hcc.model.Recepcionar;
import net.etfbl.hcc.view.gost.RootGostController;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController {
	@FXML
	private JFXTextField usernameTextField;
	@FXML
	private JFXPasswordField passwordPasswordField;
	@FXML
	private Label nevalidanLoginLabel;

	private ResourceBundle rb;
	private String localeString;

	@FXML
	private void initialize() {
		setRS();
		nevalidanLoginLabel.setVisible(false);
	}

	private Stage primaryStage;

	public void handleLogin() throws IOException {
		if(usernameTextField.getText().equals("g")){
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/gost/rootGost.fxml"),rb);
			BorderPane borderPane = loader.load();
			Stage stage = new Stage();

			RootGostController controller = loader.getController();
			RootGostController.gost = new Gost("username","ime","prezime","asd","asd");
			controller.setStage(stage);
			controller.setLoginController(this);
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

	public void setEn(){
		localeString = "en";
		Locale locale = new Locale(localeString, "EN");
		rb= ResourceBundle.getBundle("net/etfbl/hcc/util/MessagesBundle",locale);
	}

	public void setRS(){
		localeString = "rs";
		Locale locale = new Locale(localeString, "RS");
		rb = ResourceBundle.getBundle("net/etfbl/hcc/util/MessagesBundle",locale);
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

	public String getLocaleString() {
		return localeString;
	}

	public void setLocaleString(String localeString) {
		this.localeString = localeString;
	}

}
