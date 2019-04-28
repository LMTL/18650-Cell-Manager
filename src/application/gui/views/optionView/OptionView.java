package application.gui.views.optionView;

import application.gui.scenes.AppScene;
import application.gui.views.AppView;
import javafx.scene.layout.AnchorPane;

public class OptionView extends AppView {

	public OptionView(AppScene appScene) {
		super(appScene);

		AnchorPane.setLeftAnchor(root, 110.0);
		AnchorPane.setRightAnchor(root, 2.0);
		AnchorPane.setTopAnchor(root, 70.0);
		AnchorPane.setBottomAnchor(root, 2.0);
		hide();
	}

	@Override
	public OptionViewGUIController getGUIController() { return (OptionViewGUIController) guiController; }
}