package logic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MariaDBDatabaseManager implements DatabaseManager {
	
	private static String dbUrl = "jdbc:mysql://localhost:3306/test";
	private static String username = "root";
	private static String pass = "password";
	
	private static MariaDBDatabaseManager ourInstance = new MariaDBDatabaseManager();

	private MariaDBDatabaseManager() {
		// Singleton		
	}
	
	public static synchronized MariaDBDatabaseManager getInstance() {
		return ourInstance;
	}

	@Override
	public Connection openConnection() throws SQLException {
		return DriverManager.getConnection(dbUrl, username, pass);
	}

	@Override
	public void closeConnection(Connection c) {
		if (c != null) {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Statement openStatement(Connection c) throws SQLException {
		return c.createStatement();
	}

	@Override
	public void closeStatement(Statement s) {
		if (s != null) {			
			try {
				s.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
