package application.gui.views.menubarView;

import java.net.URL;
import java.util.ResourceBundle;

import application.gui.controller.GUIController;
import application.gui.scenes.MainScene;
import application.gui.views.AppView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class MenubarViewGUIController extends GUIController {

	@FXML Button importButton, databaseButton, packsButton, repackerButton;

	@Override
	public void handleMouseEvent(MouseEvent e) {
		if (e.getSource() == importButton && e.getButton() == MouseButton.PRIMARY) {
			deactivateButton(databaseButton);
			deactivateButton(packsButton);
			deactivateButton(repackerButton);
			activateButton(importButton);

			MainScene.databaseView.hide();

			MainScene.importView.show();
		}

		if (e.getSource() == databaseButton && e.getButton() == MouseButton.PRIMARY) {
			deactivateButton(importButton);
			deactivateButton(packsButton);
			deactivateButton(repackerButton);
			activateButton(databaseButton);

			MainScene.importView.hide();

			MainScene.databaseView.show();
		}

		if (e.getSource() == packsButton && e.getButton() == MouseButton.PRIMARY) {
			deactivateButton(importButton);
			deactivateButton(databaseButton);
			deactivateButton(repackerButton);
			activateButton(packsButton);

			MainScene.importView.hide();
			MainScene.databaseView.hide();


		}

		if (e.getSource() == repackerButton && e.getButton() == MouseButton.PRIMARY) {
			deactivateButton(importButton);
			deactivateButton(databaseButton);
			deactivateButton(packsButton);
			activateButton(repackerButton);

			MainScene.importView.hide();
			MainScene.databaseView.hide();


		}
	}

	@Override
	public void handleKeyEvent(KeyEvent e) {
		if (!e.isConsumed() && e.getCode() == KeyCode.DIGIT1) {
			deactivateButton(databaseButton);
			deactivateButton(packsButton);
			deactivateButton(repackerButton);
			activateButton(importButton);
			//TODO: show view

			e.consume();
		}

		if (!e.isConsumed() && e.getCode() == KeyCode.DIGIT2) {
			deactivateButton(importButton);
			deactivateButton(packsButton);
			deactivateButton(repackerButton);
			activateButton(databaseButton);
			//TODO: show view

			e.consume();
		}

		if (!e.isConsumed() && e.getCode() == KeyCode.DIGIT3) {
			deactivateButton(importButton);
			deactivateButton(databaseButton);
			deactivateButton(repackerButton);
			activateButton(packsButton);
			//TODO: show view

			e.consume();
		}

		if (!e.isConsumed() && e.getCode() == KeyCode.DIGIT4) {
			deactivateButton(importButton);
			deactivateButton(databaseButton);
			deactivateButton(packsButton);
			activateButton(repackerButton);
			//TODO: show view

			e.consume();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		activateButton(importButton);
		deactivateButton(databaseButton);
		deactivateButton(packsButton);
		deactivateButton(repackerButton);
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

	private void activateButton(Button btn) {
		if (btn.getStyleClass().contains("mainMenuButton")) btn.getStyleClass().remove("mainMenuButton");
		if (!btn.getStyleClass().contains("mainMenuButtonActive")) btn.getStyleClass().add("mainMenuButtonActive");
		if (btn == importButton) {
			if (btn.getStyleClass().contains("importTabButton")) btn.getStyleClass().remove("importTabButton");
			if (!btn.getStyleClass().contains("importTabButtonActive")) btn.getStyleClass().add("importTabButtonActive");
		}
		if (btn == databaseButton) {
			if (btn.getStyleClass().contains("exportTabButton")) btn.getStyleClass().remove("exportTabButton");
			if (!btn.getStyleClass().contains("exportTabButtonActive")) btn.getStyleClass().add("exportTabButtonActive");
		}
		if (btn == packsButton) {
			if (btn.getStyleClass().contains("optionsTabButton")) btn.getStyleClass().remove("optionsTabButton");
			if (!btn.getStyleClass().contains("optionsTabButtonActive")) btn.getStyleClass().add("optionsTabButtonActive");
		}
	}

	private void deactivateButton(Button btn) {
		if (!btn.getStyleClass().contains("mainMenuButton")) btn.getStyleClass().add("mainMenuButton");
		if (btn.getStyleClass().contains("mainMenuButtonActive"))btn.getStyleClass().remove("mainMenuButtonActive");
		if (btn == importButton) {
			if (btn.getStyleClass().contains("importTabButtonActive")) btn.getStyleClass().remove("importTabButtonActive");
			if (!btn.getStyleClass().contains("importTabButton")) btn.getStyleClass().add("importTabButton");
		}
		if (btn == databaseButton) {
			if (btn.getStyleClass().contains("exportTabButtonActive")) btn.getStyleClass().remove("exportTabButtonActive");
			if (!btn.getStyleClass().contains("exportTabButton")) btn.getStyleClass().add("exportTabButton");
		}
		if (btn == packsButton) {
			if (btn.getStyleClass().contains("optionsTabButtonActive")) btn.getStyleClass().remove("optionsTabButtonActive");
			if (!btn.getStyleClass().contains("optionsTabButton")) btn.getStyleClass().add("optionsTabButton");
		}
	}
}
