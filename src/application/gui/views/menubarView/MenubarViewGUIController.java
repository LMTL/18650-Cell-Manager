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

	@FXML Button importButton, databaseButton, packsButton, repackerButton, optionsButton;

	@Override
	public void handleMouseEvent(MouseEvent e) {
		if (e.getSource() == importButton && e.getButton() == MouseButton.PRIMARY) {
			deactivateButton(databaseButton);
			deactivateButton(packsButton);
			deactivateButton(repackerButton);
			deactivateButton(optionsButton);
			activateButton(importButton);

			MainScene.databaseView.hide();
			MainScene.importView.show();
		}

		if (e.getSource() == databaseButton && e.getButton() == MouseButton.PRIMARY) {
			deactivateButton(importButton);
			deactivateButton(packsButton);
			deactivateButton(repackerButton);
			deactivateButton(optionsButton);
			activateButton(databaseButton);

			MainScene.importView.hide();
			MainScene.databaseView.show();
		}

		if (e.getSource() == packsButton && e.getButton() == MouseButton.PRIMARY) {
			deactivateButton(importButton);
			deactivateButton(databaseButton);
			deactivateButton(repackerButton);
			deactivateButton(optionsButton);
			activateButton(packsButton);

			MainScene.importView.hide();
			MainScene.databaseView.hide();


		}

		if (e.getSource() == repackerButton && e.getButton() == MouseButton.PRIMARY) {
			deactivateButton(importButton);
			deactivateButton(databaseButton);
			deactivateButton(packsButton);
			deactivateButton(optionsButton);
			activateButton(repackerButton);

			MainScene.importView.hide();
			MainScene.databaseView.hide();


		}
		
		if (e.getSource() == optionsButton && e.getButton() == MouseButton.PRIMARY) {
			deactivateButton(importButton);
			deactivateButton(databaseButton);
			deactivateButton(packsButton);
			deactivateButton(repackerButton);
			activateButton(optionsButton);

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
			deactivateButton(optionsButton);
			activateButton(importButton);
			MainScene.databaseView.hide();
			MainScene.importView.show();
			//TODO: show view
			
			e.consume();
		}

		if (!e.isConsumed() && e.getCode() == KeyCode.DIGIT2) {
			deactivateButton(importButton);
			deactivateButton(packsButton);
			deactivateButton(repackerButton);
			deactivateButton(optionsButton);
			activateButton(databaseButton);
			MainScene.importView.hide();
			MainScene.databaseView.show();
			//TODO: show view
			
			e.consume();
		}

		if (!e.isConsumed() && e.getCode() == KeyCode.DIGIT3) {
			deactivateButton(importButton);
			deactivateButton(databaseButton);
			deactivateButton(repackerButton);
			deactivateButton(optionsButton);
			activateButton(packsButton);
			MainScene.importView.hide();
			MainScene.databaseView.hide();
			//TODO: show view
			
			e.consume();
		}

		if (!e.isConsumed() && e.getCode() == KeyCode.DIGIT4) {
			deactivateButton(importButton);
			deactivateButton(databaseButton);
			deactivateButton(packsButton);
			deactivateButton(optionsButton);
			activateButton(repackerButton);
			MainScene.importView.hide();
			MainScene.databaseView.hide();
			//TODO: show view
			
			e.consume();
		}
		
		if (!e.isConsumed() && e.getCode() == KeyCode.DIGIT5) {
			deactivateButton(importButton);
			deactivateButton(databaseButton);
			deactivateButton(packsButton);
			deactivateButton(repackerButton);
			activateButton(optionsButton);
			MainScene.importView.hide();
			MainScene.databaseView.hide();
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
			if (btn.getStyleClass().contains("databaseTabButtonButton")) btn.getStyleClass().remove("databaseTabButtonButton");
			if (!btn.getStyleClass().contains("databaseTabButtonActive")) btn.getStyleClass().add("databaseTabButtonActive");
		}
		if (btn == packsButton) {
			if (btn.getStyleClass().contains("packsButton")) btn.getStyleClass().remove("packsButton");
			if (!btn.getStyleClass().contains("packsButtonActive")) btn.getStyleClass().add("packsButtonActive");
		}
		if (btn == repackerButton) {
			if (btn.getStyleClass().contains("repackerButton")) btn.getStyleClass().remove("repackerButton");
			if (!btn.getStyleClass().contains("repackerButtonActive")) btn.getStyleClass().add("repackerButtonActive");
		}
		if (btn == optionsButton) {
			if (btn.getStyleClass().contains("optionsButton")) btn.getStyleClass().remove("optionsButton");
			if (!btn.getStyleClass().contains("optionsButtonActive")) btn.getStyleClass().add("optionsButtonActive");
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
			if (btn.getStyleClass().contains("databaseTabButtonActive")) btn.getStyleClass().remove("databaseTabButtonActive");
			if (!btn.getStyleClass().contains("databaseTabButtonButton")) btn.getStyleClass().add("databaseTabButtonButton");
		}
		if (btn == packsButton) {
			if (btn.getStyleClass().contains("packsButtonActive")) btn.getStyleClass().remove("packsButtonActive");
			if (!btn.getStyleClass().contains("packsButton")) btn.getStyleClass().add("packsButton");
		}
		if (btn == repackerButton) {
			if (btn.getStyleClass().contains("repackerButtonActive")) btn.getStyleClass().remove("repackerButtonActive");
			if (!btn.getStyleClass().contains("repackerButton")) btn.getStyleClass().add("repackerButton");
		}
		if (btn == optionsButton) {
			if (btn.getStyleClass().contains("optionsButtonActive")) btn.getStyleClass().remove("optionsButtonActive");
			if (!btn.getStyleClass().contains("optionsButton")) btn.getStyleClass().add("optionsButton");
		}
	}
}
