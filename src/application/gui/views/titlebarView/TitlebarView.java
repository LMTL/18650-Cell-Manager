package application.gui.views.titlebarView;

import application.gui.scenes.AppScene;
import application.gui.views.AppView;

public class TitlebarView extends AppView {

	public TitlebarView(AppScene appScene) {
		super(appScene);
	}

	@Override
	public TitlebarViewGUIController getGUIController() { return (TitlebarViewGUIController) super.guiController; 	}
}