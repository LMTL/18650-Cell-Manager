package application.gui.views.preloader;

import java.net.URL;
import java.util.ResourceBundle;

import application.gui.GUIConfig;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AppPreloader extends Preloader implements Initializable {

	public static SimpleDoubleProperty progress = new SimpleDoubleProperty();

    private static final double WIDTH = 350;
    private static final double HEIGHT = 150;

    private Stage window;
    private Scene scene;
    private static AnchorPane root;
    //private Label progress;

    @FXML ProgressBar progressbar;

    @Override
    public void init() throws Exception {
        Platform.runLater(() -> {
        	try {
        		//root = (AnchorPane)FXMLLoader.load(new URL(GUIConfig.DEFAULT_GUI_RES_URL + "fxml/Preloader.fxml"));

        		String appViewFileName = this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".") + 1);
        		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/res/fxml/" + appViewFileName + ".fxml"));
        		root = (AnchorPane) fxmlLoader.load();

        		scene = new Scene(root, WIDTH, HEIGHT);
        		scene.setFill(null);
        		
        		scene.getStylesheets().add(GUIConfig.DEFAULT_GUI_RES_URL + "css/views/" + appViewFileName  + ".css");
        		scene.getStylesheets().add(GUIConfig.DEFAULT_GUI_RES_URL + "css/progressbar.css");
        		scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Roboto");
        		
        		for (String css : GUIConfig.defaultGUIElementsCSS) scene.getStylesheets().add(GUIConfig.DEFAULT_GUI_RES_URL + css);

        	} catch (Exception e) {
        		System.out.println("failed to load Preloader.");
        		e.printStackTrace();
        		System.exit(1);
        	}
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.window = primaryStage;
        //window.getIcons().add(new Image(GUIConfig.DEFAULT_GUI_RES_URL + "/img/logo_25x25.png"));
		window.setTitle("AzubiStatistik 2.0");
        window.initStyle(StageStyle.UNDECORATED);
        window.initStyle(StageStyle.TRANSPARENT);
        window.setResizable(false);
        window.setScene(scene);
        window.show();
    }

    public static void setStatustext(String text) {
    	((Text) root.lookup("#statusText")).setText(text);
    }

    @Override
    public void handleApplicationNotification(PreloaderNotification info) {
    	Platform.runLater(() -> {
    		if (info instanceof ProgressNotification) {
    			((ProgressBar) root.lookup("#progressbar")).setProgress(((ProgressNotification) info).getProgress() / 100);
    		}
    	});
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {
        // Handle state change notifications.
        StateChangeNotification.Type type = info.getType();
        switch (type) {
            case BEFORE_LOAD:
                // Called after MyPreloader#start is called.
                break;
            case BEFORE_INIT:
                // Called before MyApplication#init is called.
                break;
            case BEFORE_START:
                // Called after MyApplication#init and before MyApplication#start is called.
                window.hide();
                break;
        }
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		progressbar.progressProperty().bind(progress);
	}
}