package tech.tora.zencrm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteLauncher {

	public static String dbName = "zen";

	public static String url = "jdbc:sqlite:" + dbName + ".db";

	public static Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection(url);
		System.out.println("Connection to SQLite has been established.");
		return conn;
	}

	public void selectAll(){
		String sql = "SELECT id, name, capacity FROM warehouses";

		try (Connection conn = connect();
				Statement stmt  = conn.createStatement();
				ResultSet rs    = stmt.executeQuery(sql)){

			// loop through the result set
			while (rs.next()) {
				System.out.println(rs.getInt("id") +  "\t" + 
						rs.getString("name") + "\t" +
						rs.getDouble("capacity"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insert(String name, double capacity) {
		String sql = "INSERT INTO warehouses(name,capacity) VALUES(?,?)";

		try (Connection conn = connect();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, name);
			pstmt.setDouble(2, capacity);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
    public void update(int id, String name, double capacity) {
        String sql = "UPDATE warehouses SET name = ? , "
                + "capacity = ? "
                + "WHERE id = ?";
 
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set the corresponding param
            pstmt.setString(1, name);
            pstmt.setDouble(2, capacity);
            pstmt.setInt(3, id);
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

	public SQLiteLauncher() {

		Connection conn = null;

		try {
			conn = connect();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}

		// SQL statement for creating a new table
		String sql = "CREATE TABLE IF NOT EXISTS warehouses (\n"
				+ "    id integer PRIMARY KEY,\n"
				+ "    name text NOT NULL,\n"
				+ "    capacity real\n"
				+ ");";

		try (Connection conn1 = DriverManager.getConnection(url);
				Statement stmt = conn1.createStatement()) {
			// create a new table
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}


//		insert("test", 2);
//		insert("test2", 4);

		selectAll();

	}

	public static void main(String[] args) {
		new SQLiteLauncher();
	}

}
