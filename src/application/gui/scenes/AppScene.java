package application.gui.scenes;

import java.util.LinkedList;

import application.gui.GUIConfig;
import application.gui.utils.WindowController;
import application.gui.views.AppView;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public abstract class AppScene {
	protected Stage window;
	protected Scene scene;
	protected boolean resizeable = false;
	protected AnchorPane root  = new AnchorPane();
	protected int minWidth, prefWidth, maxWidth;
	protected int minHeight, prefHeight, maxHeight;
	private LinkedList<AppView> appViewList = new LinkedList<AppView>();

	public AppScene(Stage window, int minWidth, int minHeight, int prefWidth, int prefHeight) {
		this.window = window;
		this.minWidth = minWidth;
		this.minHeight = minHeight;
		this.prefWidth = prefWidth;
		this.prefHeight = prefHeight;

		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		maxWidth = (int) primaryScreenBounds.getWidth();
		maxHeight = (int) primaryScreenBounds.getHeight();

		init();
	}

	LinkedList<AppView> tempAppViewList = new LinkedList<AppView>();
	private void init() {
		scene = new Scene(new AnchorPane(), minWidth, minHeight);
		scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Roboto");

		window.setMinWidth(minWidth);
		window.setMinHeight(minHeight);
		root.setMinSize(minWidth, minHeight);
		root.setPrefSize(prefWidth, prefHeight);
		root.setMaxSize(maxWidth, maxHeight);
		loadViews();

		root.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				tempAppViewList.clear();
				tempAppViewList.addAll(appViewList);
				for (AppView view : tempAppViewList) {
					if (!e.isConsumed()) view.getGUIController().handleKeyEvent(e);
				}
			}
		});
	}

	protected abstract void loadViews();

	public void show() {
		window.hide();
		scene = new Scene(root, minWidth, minHeight);
		loadDefaultCSS();
		window.setScene(scene);
		window.setResizable(resizeable);
		window.centerOnScreen();
		setWindowController();

		scene.setOnKeyPressed((final KeyEvent keyEvent) -> {
	        if (keyEvent.getCode() == KeyCode.WINDOWS) {
	        	window.setResizable(false);
	            keyEvent.consume();
	        }
	    });

		scene.setOnKeyReleased((final KeyEvent keyEvent) -> {
	        if (keyEvent.getCode() == KeyCode.WINDOWS) {
	        	window.setResizable(true);
	            keyEvent.consume();
	        }
	    });

		window.show();
	}

	private void setWindowController() {
		if (!resizeable) ((HBox) scene.getRoot().lookup("#titlebarButtonLabel")).getChildren().remove(1);
		new WindowController(window, scene.getRoot().lookup("#titlebar"), 	scene.getRoot().lookup("#toTaskbarButton"), scene.getRoot().lookup("#minimizeButton"),
				   			 scene.getRoot().lookup("#maximizeButton"), scene.getRoot().lookup("#closeButton"), scene.getRoot().lookup("#resizeDragButton"));
	}

	public boolean addView(AppView appView) {
		if (!root.getChildren().contains(appView.getRoot())) {
			root.getChildren().add(appView.getRoot());
			root.getStylesheets().addAll(appView.getStylesheets());
			appViewList.add(appView);
		}
		return true;
	}

	public boolean removeView(AppView appView) {
		if (root.getChildren().contains(appView.getRoot())) {
			root.getChildren().remove(appView.getRoot());
			root.getStylesheets().removeAll(appView.getStylesheets());
			appViewList.remove(appView);
		}
		return false;
	}

	public int getHeight() { return prefHeight; }

	private void loadDefaultCSS() {
		for (String css : GUIConfig.defaultGUIElementsCSS) scene.getStylesheets().add(GUIConfig.DEFAULT_GUI_RES_URL + css);
	}

	public Stage getWindow() { return window; }
}