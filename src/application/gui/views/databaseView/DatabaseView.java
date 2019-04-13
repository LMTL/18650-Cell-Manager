package application.gui.views.databaseView;

import application.gui.scenes.AppScene;
import application.gui.views.AppView;
import javafx.scene.layout.AnchorPane;

public class DatabaseView extends AppView {

	public DatabaseView(AppScene appScene) {
		super(appScene);

		AnchorPane.setLeftAnchor(root, 110.0);
		AnchorPane.setRightAnchor(root, 2.0);
		AnchorPane.setTopAnchor(root, 70.0);
		AnchorPane.setBottomAnchor(root, 2.0);
		hide();
	}

	@Override
	public DatabaseViewGUIController getGUIController() { return (DatabaseViewGUIController) guiController; }

	@Override
	public void show() {
		visible = appScene.addView(this);
		getGUIController().resetGUI();
	}
}