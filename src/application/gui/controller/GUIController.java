package application.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.gui.views.AppView;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public abstract class GUIController implements Initializable {

	protected AppView appView;

	public abstract void handleMouseEvent(MouseEvent e);
	public abstract void handleKeyEvent(KeyEvent e);
	@Override public abstract void initialize(URL arg0, ResourceBundle arg1);
	public abstract void resetGUI();
	public abstract AppView getAppView();

	public void setAppView(AppView appView) { this.appView = appView; }
}