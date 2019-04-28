package application.core.database;

import java.io.BufferedWriter;
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
	public static SimpleStringProperty exportTextProgress = new SimpleStringProperty();

	public CellDatabaseTable(Connection connection, String name) {
		super(connection, name);
	}

	@Override
	protected void initTable() {
		try {
			Statement stat = connection.createStatement();
			String cmd = "CREATE TABLE IF NOT EXISTS '" + TABLE_NAME + "' (" + "id INTEGER PRIMARY KEY, "
					+ "brand 				TEXT, " + "type 				TEXT, " + "capacity				INTEGER, "
					+ "testDate 			TEXT, " + "packID	 			INTEGER" + ")";
			stat.executeUpdate(cmd);

		} catch (SQLException e) {
			System.out.println("Could not initialize CellDatabaseTable. Errormessage:" + e.getMessage());
			System.exit(0);
		}
		selectAll();
	}

	public int addCell(Cell c) {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = "";
		if (c.testDate != null)
			dateString = ft.format(c.testDate);
		String sql = "INSERT INTO '" + TABLE_NAME + "' (brand, type, capacity, testDate, packID) " + "VALUES ('"
				+ c.brand + "', '" + c.type + "', " + c.capacity + ", '" + dateString + "', " + c.packID + ");";

		try {
			connection.setAutoCommit(false);
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(sql);
			connection.commit();

			ResultSet rs = stmt.getGeneratedKeys();

			lastAddedCellID = rs.getInt(1);
			System.out.println(lastAddedCellID);

			stmt.close();

			selectAll();
			return lastAddedCellID;
		} catch (SQLException e) {
			System.out.println("Error while writing to database: " + e.getMessage());
		}
		return -1;
	}

	public void deleteCell(int id) {
		String sql = "DELETE FROM '" + TABLE_NAME + "' WHERE id=" + id + ";";

		try {
			connection.setAutoCommit(false);
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(sql);
			connection.commit();
			stmt.close();
			selectAll();
		} catch (SQLException e) {
			System.out.println("Error while writing to database: " + e.getMessage());
		}
	}

	public void selectAll() {
		String sql = "SELECT id, brand, type, capacity, testDate, packID FROM '" + TABLE_NAME + "'";
		try {
			DatabaseManager.cellList.clear();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

			while (rs.next()) {
				Date date = null;
				if (!rs.getString("testDate").equals(""))
					date = ft.parse(rs.getString("testDate"));
				DatabaseManager.cellList.add(new Cell(rs.getString("brand"), rs.getString("type"), rs.getInt("id"),
						rs.getInt("capacity"), rs.getInt("packID"), date));
			}

			if (MainScene.databaseView != null)
				MainScene.databaseView.getGUIController().resetGUI();
		} catch (SQLException e) {
			System.out.println("Error while writing to database: " + e.getMessage());
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
			System.out.println("Could not reset CellDatabaseTable. Errormessage:" + e.getMessage());
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

			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			exportProgress.set(counter / cellInDatabase);
			exportTextProgress.set(((int) counter) + " / " + ((int) cellInDatabase));
		}

		if (bw != null) bw.close();
	}
}