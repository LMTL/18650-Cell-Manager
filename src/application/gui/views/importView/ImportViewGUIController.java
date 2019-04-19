package application.gui.views.importView;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import application.core.Main;
import application.core.database.CellDatabaseTable;
import application.core.database.DatabaseManager;
import application.core.entities.Cell;
import application.gui.controller.GUIController;
import application.gui.views.AppView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ImportViewGUIController extends GUIController {

    @FXML Button importButton, okButton;
    @FXML TextField brandTextField, typeTextField, capacityTextField;
    @FXML DatePicker datePicker;
	@FXML VBox importLabel, cellIDLabel;
	@FXML Text cellIDText;

    @Override
    public void handleMouseEvent(MouseEvent e) {
        if (e.getSource() == importButton) {
        	Platform.runLater(() -> {
	        	if (datePicker.getValue() != null) {
	        		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
	            	Date date;
	            	try {
	            		date = ft.parse(datePicker.getValue().toString());
	            		Cell c = new Cell(brandTextField.getText(), typeTextField.getText(), Integer.valueOf(capacityTextField.getText()), -1, date);
	            		datePicker.setValue(null);
	            		cellIDText.setText("Write down ID: " + DatabaseManager.database.addCell(c) + " onto the cell!");
	            		importLabel.setVisible(false);
	            		cellIDLabel.setVisible(true);

	            	} catch (ParseException e1) {
	    				e1.printStackTrace();
	    			}
	        	} else {
	        		Cell c = new Cell(brandTextField.getText(), typeTextField.getText(), -1, Integer.valueOf(capacityTextField.getText()), -1);

            		cellIDText.setText("Write down ID: " + DatabaseManager.database.addCell(c) + " onto the cell!");

	        		importLabel.setVisible(false);
	        		cellIDLabel.setVisible(true);
	        	}
	        	capacityTextField.setText("");
	        	importButton.setDisable(true);
        	});
	    }
        if (e.getSource() == okButton) {
        	Platform.runLater(() -> {
        		cellIDLabel.setVisible(false);
        		importLabel.setVisible(true);

        	});
        }

    }

    @Override
    public void handleKeyEvent(KeyEvent e) {
    	if (brandTextField.getText().length() > 0 && typeTextField.getText().length() > 0 && capacityTextField.getText().length() > 0) {
    		importButton.setDisable(false);
    	} else importButton.setDisable(true);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	capacityTextField.textProperty().addListener((observable, oldValue, newValue) -> {
    	    if (!newValue.matches("\\d{0,4}")) {
    	    	capacityTextField.setText(oldValue);
    	    }
    	});

        resetGUI();
    }

    @Override
    public void resetGUI() {
    	cellIDLabel.setVisible(false);
        importButton.setDisable(true);
        brandTextField.setText("");
        typeTextField.setText("");
        capacityTextField.setText("");
        datePicker.setValue(null);
    }

    @Override
    public AppView getAppView() {
        // TODO Auto-generated method stub
        return null;
    }
}
