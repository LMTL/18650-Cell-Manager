package application.core.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import application.core.entities.Cell;
import application.gui.scenes.MainScene;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class CellDatabaseTable extends DatabaseTable {

	public static int lastAddedCellID = 1;
	public static String csvColumnTitles = "id;brand;type;capacity;testDate;packID";
	public static SimpleDoubleProperty exportProgress = new SimpleDoubleProperty();
	public static SimpleDoubleProperty importProgress = new SimpleDoubleProperty();
	public static SimpleStringProperty exportTextProgress = new SimpleStringProperty();
	public static SimpleStringProperty importTextProgress = new SimpleStringProperty();

	public CellDatabaseTable(Connection connection, String name) {
		super(connection, name);
	}

	@Override
	protected void initTable() {
		try {
			Statement stat = connection.createStatement();
			String cmd = "CREATE TABLE IF NOT EXISTS '" + TABLE_NAME + "' (" +
						 "id 		INTEGER PRIMARY KEY, "	+
						 "cellID 	INTEGER, " +
						 "brand 	TEXT, " +
						 "type 		TEXT, " +
						 "capacity	INTEGER, " +
						 "testDate 	TEXT, " +
						 "packID	INTEGER" +
						 ")";
			stat.executeUpdate(cmd);

		} catch (SQLException e) {
			System.out.println("initTable: Could not initialize CellDatabaseTable. Errormessage:" + e.getMessage());
			System.exit(0);
		}
		selectAll();
	}

	public int addCell(Cell c) {
		String dateString = "";

		if (c.testDate != null)
			dateString = Cell.cellDateFormater.format(c.testDate);
			String sql = "INSERT INTO '" + TABLE_NAME + "' (cellID, brand, type, capacity, testDate, packID) " + "VALUES (" +
				c.id + ", '" + c.brand + "', '" + c.type + "', " + c.capacity + ", '" + dateString + "', " + c.packID + ");";

		try {
			connection.setAutoCommit(false);
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(sql);
			connection.commit();

			ResultSet rs = stmt.getGeneratedKeys();
			lastAddedCellID = rs.getInt(1);

			stmt.close();

			selectAll();
			return lastAddedCellID;
		} catch (SQLException e) {
			System.out.println("addCell: Error while writing to database: " + e.getMessage());
		}
		return -1;
	}

	public void deleteCell(int cellID) {
		String sql = "DELETE FROM '" + TABLE_NAME + "' WHERE id=" + cellID + ";";

		try {
			connection.setAutoCommit(false);
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(sql);
			connection.commit();
			stmt.close();
			selectAll();
		} catch (SQLException e) {
			System.out.println("deleteCell:Error while writing to database: " + e.getMessage());
		}
	}

	public void selectAll() {
		String sql = "SELECT id, cellID, brand, type, capacity, testDate, packID FROM '" + TABLE_NAME + "'";
		try {
			DatabaseManager.cellList.clear();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			int cellID = -1;
			while (rs.next()) {
				Date date = null;
				if (!rs.getString("testDate").equals("")) date = Cell.cellDateFormater.parse(rs.getString("testDate"));
				cellID = rs.getInt("cellID");
				if (cellID < 0) cellID = rs.getInt("id");

				DatabaseManager.cellList.add(new Cell(rs.getString("brand"), rs.getString("type"), cellID, rs.getInt("capacity"), rs.getInt("packID"), date));
			}

			if (MainScene.databaseView != null)
				MainScene.databaseView.getGUIController().resetGUI();
		} catch (SQLException e) {
			System.out.println("selectAll: selectAllselectAllError while writing to database: " + e.getMessage());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void resetDatabase() {
		try {
			Statement stat = connection.createStatement();
			String cmd = "DROP TABLE IF EXISTS '" + TABLE_NAME + "'";
			stat.executeUpdate(cmd);
		} catch (SQLException e) {
			System.out.println("resetDatabase: Could not reset CellDatabaseTable. Errormessage:" + e.getMessage());
			System.exit(0);
		}
		initTable();
	}

	public void exportDatabaseToCSV(String filePath) throws IOException {
		BufferedWriter bw = null;
		exportProgress.set(0.0);
		exportTextProgress.set("");

		bw = new BufferedWriter(new FileWriter(filePath));
		bw.write(CellDatabaseTable.csvColumnTitles);
		bw.newLine();
		double counter = 0, cellInDatabase = DatabaseManager.cellList.size();

		for (Cell c : DatabaseManager.cellList) {
			bw.write(c.toCSV());
			bw.newLine();
			counter++;

			exportProgress.set(counter / cellInDatabase);
			exportTextProgress.set(((int) counter) + " / " + ((int) cellInDatabase));
		}

		if (bw != null)
			bw.close();
	}

	public void importDatabase(String filePath) throws IOException {
		resetDatabase();
		double lineCounter = getLines(filePath), currentLine = 1;
		String line = null;
		String[] headline = null, cellData;

		BufferedReader br = null;

		importProgress.set(0.0);
		importTextProgress.set("");

		br = new BufferedReader(new FileReader(filePath));
		headline = br.readLine().split(";");

		if (headline == null || headline.length != 6 ||
			!headline[0].toLowerCase().equals("id") ||
			!headline[1].toLowerCase().equals("brand") ||
			!headline[2].toLowerCase().equals("type") ||
			!headline[3].toLowerCase().equals("capacity") ||
			!headline[4].toLowerCase().equals("testdate") ||
			!headline[5].toLowerCase().equals("packid")
			) {
			br.close();
			throw new IOException("File does not seem to be a valid Databasefile.");
		}
		//String brand, String type, int id, int capacity, int packID, Date date
		while((line = br.readLine()) != null) {
			Date date = null;
			importProgress.set(currentLine / lineCounter);
			importTextProgress.set(((int) currentLine) + " / " + ((int) lineCounter));
			cellData = line.split(";");
			if (!cellData[4].equals("-1")) {
				try { date = Cell.cellDateFormater.parse(cellData[4]); }
				catch (ParseException e) { date = null; }
			}

			if (date != null) {
				addCell(new Cell(cellData[1], cellData[2], Integer.valueOf(cellData[0]), Integer.valueOf(cellData[3]), Integer.valueOf(cellData[5]), date));
			}
			else {
				addCell(new Cell(cellData[1], cellData[2], Integer.valueOf(cellData[0]), Integer.valueOf(cellData[3]), Integer.valueOf(cellData[5]), null));
			}
			//date = null;
			currentLine++;
		}
		br.close();
	}

	private double getLines(String filePath) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		double lineCounter = 0;

		while(br.readLine() != null) lineCounter++;
		br.close();

		return lineCounter;
	}
}