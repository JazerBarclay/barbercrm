package tech.tora.zencrm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.derby.tools.sysinfo;

import tech.tora.zencrm.db.DerbyDB;
import tech.tora.zencrm.db.InitDB;

public class Launcher {

	private static String databaseName = "zendb";

	private static String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	private static String JDBC_URL = "jdbc:derby:" + databaseName + ";create=true;";
	//	private static String JDBC_URL = "jdbc:derby:myDB;create=true;user=me;password=mine";

	private static Connection conn = null;
	
	public void flatTest() {
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

			if (statement != null) statement.close();
			if (conn != null) conn.close();

		} catch (Exception except) {
			except.printStackTrace();
		}
	}

	public void dbClassTest() {

		new InitDB(databaseName);
		
		try {
			DerbyDB db = new DerbyDB(databaseName);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
		
//		try {
//			db.createTable("CREATE TABLE customers(customer_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), first_name varchar(30), last_name varchar(30))");
//		} catch (SQLException e) {
//			if( e.getErrorCode() == 30000 ) {
//				System.out.println("Table CUSTOMERS exists...");
//		    } else {
//				System.err.println("Failed to create table CUSTOMERS");
//				e.printStackTrace();
//				System.out.println(e.getErrorCode());
//		    }
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		
//		try {
//			db.insert("INSERT INTO customers(first_name, last_name) VALUES ('Jazer', 'Barclay')");
//		} catch (ClassNotFoundException | SQLException e) {
//			System.err.println("Failed to insert JAZER BARCLAY into CUSTOMERS");
//			e.printStackTrace();
//		}
//		
//		try {
//			ResultSet rs = db.select("SELECT * FROM customers");
//			ResultSetMetaData rsmd = rs.getMetaData();
//
//			int colCount = rsmd.getColumnCount();
//			for (int i = 1; i <= colCount; i++) {
//				System.out.format("%20s", rsmd.getColumnName(i) + " | ");
//			}
//
//			while (rs.next()) {
//				System.out.println();
//				for (int i = 1; i <= colCount; i++) {
//					System.out.format("%20s", rs.getString(i) + " | ");
//				}
//			}
//			
//		} catch (ClassNotFoundException | SQLException e) {
//			System.err.println("Failed to list SELECT results");
//			e.printStackTrace();
//		}
//		
//		try {
//			db.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
	}
	
	
	public Launcher() {
		dbClassTest();
	}

	public static void main(String[] args) {
		new Launcher();
	}

}
