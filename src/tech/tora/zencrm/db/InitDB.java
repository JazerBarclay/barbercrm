package tech.tora.zencrm.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class InitDB {

	public InitDB(String dbName) {

		DerbyDB db = null;
		try {
			db = new DerbyDB(dbName);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			System.out.println("Generating CUSTOMERS table...");
			db.createTable("CREATE TABLE customers(customer_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), first_name varchar(30), last_name varchar(30))");
		} catch (SQLException e) {
			if( ((SQLException)e).getSQLState().contentEquals("X0Y32") ) {
				System.out.println("Table CUSTOMERS exists...");
			} else {
				System.err.println("Failed to create table HAIRCUT_TYPE");
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("Generating HAIRCUT_TYPES table...");
			db.createTable("CREATE TABLE haircut_types(haircut_type_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), haircut_type_name varchar(20))");
		} catch (SQLException e) {
			if( ((SQLException)e).getSQLState().contentEquals("X0Y32") ) {
				System.out.println("Table HAIRCUT_TYPES exists...");
			} else {
				System.err.println("Failed to create table HAIRCUT_TYPES");
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}



		try {
			System.out.println("Generating CUSTOMER_TYPE table...");
			db.createTable("CREATE TABLE customer_type(customer_type_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), customer_type_name varchar(20))");
		} catch (SQLException e) {
			if( ((SQLException)e).getSQLState().contentEquals("X0Y32") ) {
				System.out.println("Table CUSTOMER_TYPE exists...");
			} else {
				System.err.println("Failed to create table HAIRCUT_TYPE");
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


		try {
			System.out.println("Generating HAIRCUTS table...");
			db.createTable("CREATE TABLE haircuts(haircut_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), customer_fid INTEGER, haircut_type_fid INTEGER, haircut_timestamp INTEGER)");
		} catch (SQLException e) {
			if( ((SQLException)e).getSQLState().contentEquals("X0Y32") ) {
				System.out.println("Table HAIRCUTS exists...");
			} else {
				System.err.println("Failed to create table HAIRCUTS");
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


		try {
			ResultSet rs = db.select("SELECT * FROM haircuts");
			ResultSetMetaData rsmd = rs.getMetaData();

			int colCount = rsmd.getColumnCount();
			for (int i = 1; i <= colCount; i++) {
				System.out.format("%20s", rsmd.getColumnName(i) + " | ");
			}

			while (rs.next()) {
				System.out.println();
				for (int i = 1; i <= colCount; i++) {
					System.out.format("%20s", rs.getString(i) + " | ");
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

}
