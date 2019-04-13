package application.core.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

import application.core.entities.Cell;


public class DatabaseManager {
	private static Connection connection;
	public static CellDatabaseTable database;
	public static LinkedList<Cell> cellList = new LinkedList<Cell>();

	public DatabaseManager() {
		try {
			String dbFilePath = "appData/database.sqlite";
			new File("appData").mkdirs();
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbFilePath);

			initTables();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Could not load userprofile. " + e.getMessage());
		}
	}

	private void initTables() {
		database = new CellDatabaseTable(connection, "18650-Database");
	}
}