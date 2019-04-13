package application.gui.views.menubarView;

import java.net.URL;
import java.util.ResourceBundle;

import application.gui.controller.GUIController;
import application.gui.views.AppView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class MenubarViewGUIController extends GUIController {

	@FXML Button importButton, statistikButton, optionButton;

	@Override
	public void handleMouseEvent(MouseEvent e) {
		if (e.getSource() == importButton && e.getButton() == MouseButton.PRIMARY) {
			deactivateButton(statistikButton);
			deactivateButton(optionButton);
			activateButton(importButton);

		}

		if (e.getSource() == statistikButton && e.getButton() == MouseButton.PRIMARY) {
			deactivateButton(importButton);
			deactivateButton(optionButton);
			activateButton(statistikButton);

		}

		if (e.getSource() == optionButton && e.getButton() == MouseButton.PRIMARY) {
			deactivateButton(importButton);
			deactivateButton(statistikButton);
			activateButton(optionButton);

		}
	}

	@Override
	public void handleKeyEvent(KeyEvent e) {
		if (!e.isConsumed() && e.getCode() == KeyCode.DIGIT1) {
			deactivateButton(statistikButton);
			deactivateButton(optionButton);
			activateButton(importButton);


			e.consume();
		}

		if (!e.isConsumed() && e.getCode() == KeyCode.DIGIT2) {
			deactivateButton(importButton);
			deactivateButton(optionButton);
			activateButton(statistikButton);


			e.consume();
		}

		if (!e.isConsumed() && e.getCode() == KeyCode.DIGIT3) {
			deactivateButton(importButton);
			deactivateButton(statistikButton);
			activateButton(optionButton);


			e.consume();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		activateButton(importButton);
		deactivateButton(statistikButton);
		deactivateButton(optionButton);
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
		if (btn == statistikButton) {
			if (btn.getStyleClass().contains("exportTabButton")) btn.getStyleClass().remove("exportTabButton");
			if (!btn.getStyleClass().contains("exportTabButtonActive")) btn.getStyleClass().add("exportTabButtonActive");
		}
		if (btn == optionButton) {
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
		if (btn == statistikButton) {
			if (btn.getStyleClass().contains("exportTabButtonActive")) btn.getStyleClass().remove("exportTabButtonActive");
			if (!btn.getStyleClass().contains("exportTabButton")) btn.getStyleClass().add("exportTabButton");
		}
		if (btn == optionButton) {
			if (btn.getStyleClass().contains("optionsTabButtonActive")) btn.getStyleClass().remove("optionsTabButtonActive");
			if (!btn.getStyleClass().contains("optionsTabButton")) btn.getStyleClass().add("optionsTabButton");
		}
	}
}
