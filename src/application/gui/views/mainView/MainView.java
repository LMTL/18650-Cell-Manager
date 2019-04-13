package application.gui.views.mainView;

import application.gui.scenes.AppScene;
import application.gui.views.AppView;

public class MainView extends AppView {

	public MainView(AppScene appScene) {
		super(appScene);
	}

	@Override public MainViewGUIController getGUIController() 	{ return (MainViewGUIController) super.guiController; 	}
}
