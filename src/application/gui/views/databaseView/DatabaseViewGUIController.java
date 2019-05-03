package application.gui.views.databaseView;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import application.core.database.DatabaseManager;
import application.core.entities.Cell;
import application.gui.controller.GUIController;
import application.gui.views.AppView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class DatabaseViewGUIController extends GUIController {

	@FXML GridPane gridPane;

	@Override
	public void handleMouseEvent(MouseEvent e) {

	}

	@Override
	public void handleKeyEvent(KeyEvent e) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		resetGUI();
	}

	@Override
	public void resetGUI() {
		initGridPane();
		int rowCounter = 1;
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
		String dateString = "";

		for (Cell cell : DatabaseManager.cellList) {

			Text id = new Text(String.valueOf(cell.id));
			id.getStyleClass().add("appText");

			Text brand = new Text(cell.brand);
			brand.getStyleClass().add("appText");

			Text type = new Text(cell.type);
			type.getStyleClass().add("appText");

			Text capacity = new Text(String.valueOf(cell.capacity));
			capacity.getStyleClass().add("appText");

			if (cell.testDate != null) dateString = ft.format(cell.testDate);
			else dateString = "-";

			Text testDate = new Text(dateString);
			testDate.getStyleClass().add("appText");

			Text packID = new Text(String.valueOf(cell.packID));
			if (cell.packID < 0) packID.setText("-");
			packID.getStyleClass().add("appText");

			Button del = new Button();
			del.setId(String.valueOf(cell.id));
			del.getStyleClass().add("deleteButton");
			del.setOnMouseClicked((event)->{
				Platform.runLater(() -> {
					DatabaseManager.database.deleteCell(Integer.valueOf(((Node) event.getSource()).getId()));
				});
			});
			del.setMaxHeight(20);
			del.setMaxWidth(20);
			del.setMinHeight(20);
			del.setMinWidth(20);

			gridPane.addRow(rowCounter, id, brand, type, capacity, testDate, packID, del);
			GridPane.setHalignment(del, javafx.geometry.HPos.RIGHT);

			rowCounter++;
			dateString = null;
		}

		gridPane.setHgap(5);
		gridPane.setVgap(5);
		gridPane.setPadding(new Insets(5, 10, 5, 10));

		System.out.println(gridPane.getRowConstraints().get(0));

	}

	private void initGridPane() {
		gridPane.getChildren().clear();

		Text id = new Text("#");
		id.getStyleClass().add("appText");

		Text brand = new Text("Brand");
		brand.getStyleClass().add("appText");

		Text type = new Text("Type");
		type.getStyleClass().add("appText");

		Text capacity = new Text("Capacity");
		capacity.getStyleClass().add("appText");


		Text testDate = new Text("Testdate");
		testDate.getStyleClass().add("appText");

		Text packID = new Text("PackID");
		packID.getStyleClass().add("appText");

		gridPane.addRow(0, id, brand, type, capacity, testDate, packID);
	}
	@Override
	public AppView getAppView() {
		// TODO Auto-generated method stub
		return null;
	}
}
