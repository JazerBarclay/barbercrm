package tech.tora.tools.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DerbyDB {

	public static String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";

	public String databaseName;
	public String JDBC_URL;

	private Connection conn;
	private Statement statement;

	public DerbyDB(String dbName) throws ClassNotFoundException, SQLException {
		this.databaseName = dbName;
		JDBC_URL = "jdbc:derby:" + dbName + ";create=true;";
		connect();
	}

	public Connection connect() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		conn =  DriverManager.getConnection(JDBC_URL);
		return conn;
	}

	public void close() throws SQLException {
		if (statement != null) statement.close();
		if (conn != null) conn.close();
	}

	public void createTable(String tableSQL) throws SQLException, ClassNotFoundException {
		conn.createStatement().execute(tableSQL);
	}

	public ResultSet select(String selectSQL) throws ClassNotFoundException, SQLException {
		statement = conn.createStatement();
		ResultSet res = statement.executeQuery(selectSQL);
		return res;
	}

	public void insert(String insertSQL) throws ClassNotFoundException, SQLException {
		conn.createStatement().execute(insertSQL);
	}

	public void delete(String deleteSQL) throws ClassNotFoundException, SQLException {
		conn.createStatement().execute(deleteSQL);
	}

}
