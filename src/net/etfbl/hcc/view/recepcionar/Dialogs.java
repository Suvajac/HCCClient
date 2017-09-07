package net.etfbl.hcc.view.recepcionar;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Dialogs {
	
	// Suppresses default constructor, ensuring non-instantiability.
	private Dialogs() {}
	
	private static void showDialog(AlertType type, String title, String headerText, String contentText) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.showAndWait();
	}

	public static void showErrorDialog(String title, String headerText, String contentText) {
		showDialog(AlertType.ERROR, title, headerText, contentText);
	}

	public static void showInfoDialog(String title, String headerText, String contentText) {
		showDialog(AlertType.INFORMATION, title, headerText, contentText);
	}

	public static void showWarningDialog(String title, String headerText, String contentText) {
		showDialog(AlertType.WARNING, title, headerText, contentText);
	}

}
