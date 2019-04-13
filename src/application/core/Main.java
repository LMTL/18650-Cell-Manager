package application.core;

import com.sun.javafx.application.LauncherImpl;

import application.gui.scenes.MainScene;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

	public static MainScene mainScene;

	public static Stage window = new Stage();
	@Override
    public void init() throws Exception {
		System.out.println("init");

		mainScene = new MainScene(window);

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
			//window.getIcons().add(new Image(getClass().getResourceAsStream("gui/res/img/logo_25x25.png")));


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
