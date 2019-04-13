package application.gui.views.mainView;

import java.net.URL;
import java.util.ResourceBundle;

import application.core.Main;
import application.gui.controller.GUIController;
import application.gui.views.AppView;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class MainViewGUIController extends GUIController {

    @FXML AnchorPane appBG;
    @FXML HBox footer;

    @Override
    public void handleMouseEvent(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void handleKeyEvent(KeyEvent e) {

    }



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        AnchorPane.setTopAnchor(appBG, 0.0);
        AnchorPane.setRightAnchor(appBG, 0.0);
        AnchorPane.setLeftAnchor(appBG, 0.0);
        AnchorPane.setBottomAnchor(appBG, 0.0);
    }

    @Override
    public void resetGUI() {
        // TODO Auto-generated method stub

    }

    @Override
    public AppView getAppView() {
        // TODO Auto-generated method stub
        return null;
    }
}
