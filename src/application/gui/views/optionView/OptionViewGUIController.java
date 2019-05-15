package application.gui.views.optionView;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.core.Main;
import application.core.database.CellDatabaseTable;
import application.core.database.DatabaseManager;
import application.core.entities.Cell;
import application.gui.controller.GUIController;
import application.gui.views.AppView;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;

public class OptionViewGUIController extends GUIController {

	@FXML Button exportDatabaseButton, importDatabaseButton, resetDatabaseButton, dialogOKButton, dialogCancleButton;
	@FXML VBox dialogLabel, progressLabel;
	@FXML HBox dialogButtonLabe;
	@FXML Text dialogHeadline, progressHeadline, progressText;
	@FXML TextFlow dialogText;
	@FXML ProgressBar progressbar;

	private final FileChooser fileChooser = new FileChooser();


	@Override
	public void handleMouseEvent(MouseEvent e) {
		if (e.getSource() == resetDatabaseButton && e.getButton() == MouseButton.PRIMARY) {
			resetDatabaseButton.setDisable(true);
			dialogOKButton.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					Platform.runLater(() -> {
						DatabaseManager.database.resetDatabase();
					});
					resetGUI();
				}
			});

			dialogHeadline.setText("Reset Database");
			Text t = new Text("Are you sure you want to reset the Database?");
			t.getStyleClass().add("appText");
			dialogText.getChildren().clear();
			dialogText.getChildren().add(t);
			dialogLabel.setVisible(true);
		}
	}

	@Override
	public void handleKeyEvent(KeyEvent e) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dialogLabel.setVisible(false);
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV", "*.CSV"));

		importDatabaseButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				importDatabaseButton.setDisable(true);
				exportDatabaseButton.setDisable(true);
				resetDatabaseButton.setDisable(true);
				fileChooser.setTitle("Import Database");
				File file = fileChooser.showOpenDialog(Main.window);
				if (file != null) {
					importDatabase(file.getAbsolutePath());
				} else resetGUI();
			}
		});

		exportDatabaseButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Platform.runLater(() -> {
				exportDatabaseButton.setDisable(true);
				importDatabaseButton.setDisable(true);
				resetDatabaseButton.setDisable(true);
				fileChooser.setTitle("Export Database");
				File file = fileChooser.showSaveDialog(Main.window);
				if (file != null) {
					exportDatabase(file.getAbsolutePath());
				} else resetGUI();
				});
			}
		});



		dialogCancleButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				resetGUI();
			}
		});

		resetGUI();
	}

	private void exportDatabase(String filePath) {
		Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
    			try {
    				progressbar.progressProperty().bind(CellDatabaseTable.exportProgress);
    				progressText.textProperty().bind(CellDatabaseTable.exportTextProgress);
    				progressHeadline.setText("Exporting Database");
    				progressLabel.setVisible(true);
    				DatabaseManager.database.exportDatabaseToCSV(filePath);
    				progressLabel.setVisible(false);
    				Platform.runLater(() -> {
    					showDialogLabel("Database export", "Successfully exported to:", filePath);
    				});

    			} catch(IOException e) {
    				Platform.runLater(() -> {
    					String t = e.getMessage();
    					if (t.length() >= 245) t = t.substring(t.indexOf("(", 0) + 1, t.indexOf(")", 1));
    					showDialogLabel("Error while exporting database", t, null);
    				});
    			}
            }
        });
        thread.setDaemon(true);
        thread.start();
	}

	private void importDatabase(String filePath) {
		Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
    			progressbar.progressProperty().bind(CellDatabaseTable.importProgress);
				progressText.textProperty().bind(CellDatabaseTable.importTextProgress);
				progressHeadline.setText("Importing Database");
				progressLabel.setVisible(true);
				try {
					DatabaseManager.database.importDatabase(filePath);
					progressLabel.setVisible(false);
					Platform.runLater(() -> {
						showDialogLabel("Database import", "Successfully imported!", "");
					});
				} catch (IOException e) {
					Platform.runLater(() -> {
						progressLabel.setVisible(false);
						showDialogLabel("Database import", e.getMessage(), "");
					});
				}
            }
        });
        thread.setDaemon(true);
        thread.start();
	}
	@Override
	public void resetGUI() {
		dialogLabel.setVisible(false);
		progressLabel.setVisible(false);
		dialogLabel.setVisible(false);
		importDatabaseButton.setDisable(false);
		exportDatabaseButton.setDisable(false);
		resetDatabaseButton.setDisable(false);
		if (!dialogButtonLabe.getChildren().contains(dialogCancleButton)) dialogButtonLabe.getChildren().add(dialogCancleButton);
		dialogCancleButton.setVisible(true);
		dialogOKButton.setOnAction(null);
	}

	@Override
	public AppView getAppView() {
		// TODO Auto-generated method stub
		return null;
	}


	private void showDialogLabel(String headline, String text, String filePath) {
		dialogHeadline.setText(headline);

		Hyperlink l = new Hyperlink();
		String fileName = filePath;

		if (fileName.contains("/")) {
			fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
		} else {
			fileName = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length());
		}

		l.setText(fileName);
		l.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
            	try {
					Desktop.getDesktop().open(new File(filePath));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

		dialogText.getChildren().clear();
		Text t = new Text(text);
		t.getStyleClass().add("appText");
		dialogText.getChildren().add(t);
		if (filePath != null) dialogText.getChildren().add(l);

		dialogButtonLabe.getChildren().remove(dialogCancleButton);
		dialogOKButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				resetGUI();
			}
		});
		dialogLabel.setVisible(true);
	}
}