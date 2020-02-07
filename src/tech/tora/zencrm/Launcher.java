package tech.tora.zencrm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.derby.tools.sysinfo;

public class Launcher {

	private static String databaseName = "zendb";

	private static String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	private static String JDBC_URL = "jdbc:derby:" + databaseName + ";create=true;";
	//	private static String JDBC_URL = "jdbc:derby:myDB;create=true;user=me;password=mine";

	private static Connection conn = null;

	public Launcher() {
		System.out.println("Hello World! Zen CRM here");

		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(JDBC_URL);

			conn.createStatement().execute("create table users(username varchar(20), password varchar(20))");
			conn.createStatement().execute("insert into users values " + 
					"('jazer', 'pass123')," + 
					"('linux', 'admin000')");

			Statement statement = conn.createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM users");
			ResultSetMetaData rs = res.getMetaData();

			int colCount = rs.getColumnCount();
			for (int i = 1; i <= colCount; i++) {
				System.out.format("%20s", rs.getColumnName(i) + " | ");
			}

			while (res.next()) {
				System.out.println();
				for (int i = 1; i <= colCount; i++) {
					System.out.format("%20s", res.getString(i) + " | ");
				}
			}

			if (statement != null) {statement.close();}

			if (conn != null) {conn.close();}

		} catch (Exception except) {
			except.printStackTrace();
		}
	}


	public static void main(String[] args) {
		new Launcher();
	}

}
