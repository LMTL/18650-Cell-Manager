package application.gui.views.titlebarView;

import java.net.URL;
import java.util.ResourceBundle;

import application.gui.controller.GUIController;
import application.gui.views.AppView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class TitlebarViewGUIController extends GUIController {

	public static HBox buttonLabel;

	@FXML Button maximizeButton;
	@FXML HBox headerButtonLabel;

	@Override
	public void handleMouseEvent(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleKeyEvent(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		buttonLabel = headerButtonLabel;
	}

	@Override
	public void resetGUI() {
		// TODO Auto-generated method stub

	}

	@Override
	public AppView getAppView() {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeMaximizeButton() {
		((AnchorPane) appView.getRoot()).getChildren().remove(maximizeButton);
	}
}