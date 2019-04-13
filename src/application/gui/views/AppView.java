package application.gui.views;

import java.util.LinkedList;

import application.gui.GUIConfig;
import application.gui.controller.GUIController;
import application.gui.scenes.AppScene;
//import application.gui.views.preloader.AppPreloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public abstract class AppView {
	protected AppScene appScene;
	protected Node root;
	protected GUIController guiController;
	protected LinkedList<String> stylesheets = new LinkedList<>();
	protected boolean visible = false;
	protected String name = null;
	public FXMLLoader loader;

	public AppView(AppScene appScene) {
		this.appScene = appScene;
		loadResources();

//		AnchorPane.setLeftAnchor(root, 0.0);
//		AnchorPane.setRightAnchor(root, 0.0);
//		AnchorPane.setTopAnchor(root, 0.0);
//		AnchorPane.setBottomAnchor(root, 0.0);


//		AppPreloader.progress.set(AppPreloader.progress.get() + 0.1f);
	}

	public abstract GUIController getGUIController();

	public String getName()  					{ return name;								}
	public Node getRoot() 						{ return root; 								}
	public LinkedList<String> getStylesheets() 	{ return stylesheets;						}
	public void show() 							{ visible = appScene.addView(this);			}
	public void hide()							{ visible = !appScene.removeView(this); 	}
	public boolean isVisible() 					{ return visible;							}

	protected void loadResources() {
		try {
			String appViewFileName = this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".") + 1);
			//System.out.println(GUIConfig.DEFAULT_GUI_RES_URL + "fxml/" + appViewFileName + ".fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/res/fxml/" + appViewFileName + ".fxml"));// i18n App.sceneManager.getResourceBundle()
			root = (Node) fxmlLoader.load();
			guiController = fxmlLoader.<GUIController>getController();
			guiController.setAppView(this);

			stylesheets.add(GUIConfig.DEFAULT_GUI_RES_URL + "css/views/" + appViewFileName  + ".css");
			appScene.addView(this);
		} catch (Exception e) {
			System.out.println("could not initialize " + this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".") + 1) + ".\n" + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}
}
