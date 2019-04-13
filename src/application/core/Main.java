package application.core;

import java.sql.Date;
import java.time.LocalDate;

import com.sun.javafx.application.LauncherImpl;

import application.core.database.DatabaseManager;
import application.core.entities.Cell;
import application.gui.scenes.MainScene;
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
		System.out.println("init");
		dbManager = new DatabaseManager();
		mainScene = new MainScene(window);
		MainScene.importView.show();

		Cell c = new Cell("Samsung", "0815", 3200, -1, Date.valueOf(LocalDate.now()));
		//dbManager.database.addCell(c);

//		while (AppPreloader.progress.get() <= 1.0) {
//			AppPreloader.progress.set(AppPreloader.progress.get() + 0.1);
//			Thread.sleep(10);
//		}

		System.out.println("init rdy");
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
		LauncherImpl.launchApplication(Main.class, args); //AppPreloader.class,
	}
}
