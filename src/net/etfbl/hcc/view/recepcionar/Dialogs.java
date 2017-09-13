package net.etfbl.hcc.view.recepcionar;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Dialogs {
	
	// Suppresses default constructor, ensuring non-instantiability.
	private Dialogs() {}
	
	private static ButtonType showDialog(AlertType type, String title, String headerText, String contentText) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.showAndWait();
		return alert.getResult();
	}

	public static ButtonType showErrorDialog(String title, String headerText, String contentText) {
		return showDialog(AlertType.ERROR, title, headerText, contentText);
	}

	public static ButtonType showInfoDialog(String title, String headerText, String contentText) {
		return showDialog(AlertType.INFORMATION, title, headerText, contentText);
	}

	public static ButtonType showWarningDialog(String title, String headerText, String contentText) {
		return showDialog(AlertType.WARNING, title, headerText, contentText);
	}

	public static ButtonType showConfirmationDialog(String title, String headerText, String contentText) {
		return showDialog(AlertType.CONFIRMATION, title, headerText, contentText);
	}
}
