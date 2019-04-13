package application.gui.views.databaseView;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import application.core.database.DatabaseManager;
import application.core.entities.Cell;
import application.gui.controller.GUIController;
import application.gui.views.AppView;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class DatabaseViewGUIController extends GUIController {

	@FXML ListView<HBox> listView;

	@Override
	public void handleMouseEvent(MouseEvent e) {

	}

	@Override
	public void handleKeyEvent(KeyEvent e) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {


		//resetGUI();
	}

	@Override
	public void resetGUI() {
		listView.getItems().clear();


		for (Cell c : DatabaseManager.cellList) {
			HBox hBox = new HBox();
			Text id = new Text(String.valueOf(c.id));
			id.getStyleClass().add("appText");
			HBox idBox = new HBox(id);
			//idBox.setStyle("-fx-padding: 0 0 0 75");
			id.setWrappingWidth(50);
			idBox.setMaxWidth(50);
			hBox.getChildren().add(idBox);

			Text brand = new Text(c.brand);
			brand.getStyleClass().add("appText");
			HBox brandBox = new HBox(brand);
			brandBox.setStyle("-fx-padding: 0 0 0 32");
			brand.setWrappingWidth(87);
			brandBox.setMaxWidth(135);
			hBox.getChildren().add(brandBox);

			Text type = new Text(c.type);
			type.getStyleClass().add("appText");
			HBox typeBox = new HBox(type);
			typeBox.setStyle("-fx-padding: 0 0 0 52");
			type.setWrappingWidth(90);
			typeBox.setMaxWidth(140);
			hBox.getChildren().add(typeBox);

			Text capacity = new Text(String.valueOf(c.capacity));
			capacity.getStyleClass().add("appText");
			HBox capacityBox = new HBox(capacity);
			capacityBox.setStyle("-fx-padding: 0 0 0 52");
			capacity.setWrappingWidth(87);
			typeBox.setMaxWidth(135);
			hBox.getChildren().add(capacityBox);

			SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
			String dateString = "";
			if (c.testDate != null) dateString = ft.format(c.testDate);
			
			Text testDate = new Text(dateString);
			testDate.getStyleClass().add("appText");
			HBox testDateBox = new HBox(testDate);
			testDateBox.setStyle("-fx-padding: 0 0 0 19");
			testDate.setWrappingWidth(100);
			testDateBox.setMaxWidth(100);
			hBox.getChildren().add(testDateBox);


			Text packID = new Text(String.valueOf(c.packID));
			if (c.packID < 0) packID.setText("-");
			packID.getStyleClass().add("appText");
			HBox packIDBox = new HBox(packID);
			packIDBox.setStyle("-fx-padding: 0 0 0 30");
			packID.setWrappingWidth(100);
			packIDBox.setMaxWidth(100);
			hBox.getChildren().add(packIDBox);

			listView.getItems().add(hBox);
		}

	}

	@Override
	public AppView getAppView() {
		// TODO Auto-generated method stub
		return null;
	}
}
