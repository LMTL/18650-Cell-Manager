package application.gui.scenes;

import application.gui.views.mainView.MainView;
import application.gui.views.menubarView.MenubarView;
import application.gui.views.titlebarView.TitlebarView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainScene extends AppScene {

	public static MainView mainView;
	public static TitlebarView titlebar;
	private MenubarView menubar;

	public MainScene(Stage window) {
		super(window, 900, 600, 900, 600);
		resizeable = true;
	}

	@Override
	protected void loadViews() {
		mainView = new MainView(this);
		menubar = new MenubarView(this);
		titlebar = new TitlebarView(this);

	}

	@Override
	public void show() {
		prepareMenubar();
		prepareTitlebar();
		super.show();
	}

	private void prepareTitlebar() {
		AnchorPane.setTopAnchor(titlebar.getRoot(), 1.0);
		AnchorPane.setRightAnchor(titlebar.getRoot(), 2.0);
		AnchorPane.setLeftAnchor(titlebar.getRoot(), 2.0);


		AnchorPane.setTopAnchor(titlebar.getRoot().lookup("#dekoLabel"), 0.0);
		AnchorPane.setLeftAnchor(titlebar.getRoot().lookup("#dekoLabel"), 90.0);
		AnchorPane.setRightAnchor(titlebar.getRoot().lookup("#dekoLabel"), 90.0);
		((AnchorPane)titlebar.getRoot()).setMinHeight(70.0);
		((AnchorPane)titlebar.getRoot()).setPrefHeight(70.0);
		((AnchorPane)titlebar.getRoot()).setMaxHeight(70.0);
		titlebar.getRoot().lookup("#titlebarBGLabel").setVisible(true);
		titlebar.getRoot().lookup("#headerButtonLabel").setVisible(true);
	}

	private void prepareMenubar() {
		AnchorPane.setTopAnchor(menubar.getRoot(), 71.0);
		AnchorPane.setBottomAnchor(menubar.getRoot(), 1.0);
		AnchorPane.setLeftAnchor(menubar.getRoot(), 1.0);
	}
}
