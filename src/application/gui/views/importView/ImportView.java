package application.gui.views.importView;

import application.gui.scenes.AppScene;
import application.gui.views.AppView;
import javafx.scene.layout.AnchorPane;

public class ImportView extends AppView {

	public ImportView(AppScene appScene) {
		super(appScene);

		AnchorPane.setLeftAnchor(root, 110.0);
		AnchorPane.setRightAnchor(root, 2.0);
		AnchorPane.setTopAnchor(root, 70.0);
		AnchorPane.setBottomAnchor(root, 2.0);
		hide();
	}

	@Override
	public ImportViewGUIController getGUIController() { return (ImportViewGUIController) guiController; }
}