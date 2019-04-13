package application.core.database;

import java.sql.Connection;

public abstract class DatabaseTable {

	protected String TABLE_NAME;
	protected static Connection connection;

	public DatabaseTable(Connection connection, String name) {
		DatabaseTable.connection = connection;
		TABLE_NAME = name;
		initTable();
	}

	protected abstract void initTable();
}