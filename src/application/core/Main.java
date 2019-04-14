package application.core;

import java.sql.Date;
import java.time.LocalDate;

import com.sun.javafx.application.LauncherImpl;

import application.core.database.DatabaseManager;
import application.core.entities.Cell;
import application.gui.scenes.MainScene;
import application.gui.views.preloader.AppPreloader;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

	public static MainScene mainScene;
	public static DatabaseManager dbManager;
	public static Stage window = new Stage();

	@Override
    public void init() throws Exception {
		AppPreloader.setStatustext("loading database...");
		AppPreloader.progress.set(AppPreloader.progress.get() + 0.1);
		dbManager = new DatabaseManager();

		AppPreloader.setStatustext("loading GUI...");
		mainScene = new MainScene(window);
		AppPreloader.progress.set(AppPreloader.progress.get() + 0.1);
		MainScene.importView.show();
		AppPreloader.progress.set(AppPreloader.progress.get() + 0.1);

		while (AppPreloader.progress.get() <= 1.0) {
			AppPreloader.progress.set(AppPreloader.progress.get() + 0.1);
			Thread.sleep(50);
		}
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			window.initStyle(StageStyle.UNDECORATED);
			window.initStyle(StageStyle.TRANSPARENT);
			window.setTitle("18650-Cell-Manager");
			window.getIcons().add(new Image(getClass().getResourceAsStream("/application/res/img/logo_25x25.png")));


			mainScene.show();

			window.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void closeApp() {
		Platform.exit();
	}

	public static void main(String[] args) {
		LauncherImpl.launchApplication(Main.class, AppPreloader.class, args); //AppPreloader.class,
	}
}
