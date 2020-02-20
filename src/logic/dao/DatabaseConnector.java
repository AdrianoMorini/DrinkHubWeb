package logic.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseConnector {
	public Connection openConnection() throws SQLException;
	public void closeConnection(Connection connection);
}
