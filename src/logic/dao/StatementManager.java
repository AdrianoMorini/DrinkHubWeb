package logic.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public interface StatementManager {
	public Statement openStatement(Connection connection) throws SQLException;
	public void closeStatement(Statement s);
}
