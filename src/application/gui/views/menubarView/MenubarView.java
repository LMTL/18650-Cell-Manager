package application.gui.views.menubarView;

import application.gui.scenes.AppScene;
import application.gui.views.AppView;

public class MenubarView extends AppView {

	public MenubarView(AppScene appScene) {
		super(appScene);
		// TODO Auto-generated constructor stub
	}

	@Override
	public MenubarViewGUIController getGUIController() 	{ return (MenubarViewGUIController) super.guiController; 	}
}