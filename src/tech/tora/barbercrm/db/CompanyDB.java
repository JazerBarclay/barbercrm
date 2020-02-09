package tech.tora.barbercrm.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import tech.tora.barbercrm.widgets.Customer;
import tech.tora.barbercrm.widgets.CustomerType;
import tech.tora.barbercrm.widgets.HaircutType;
import tech.tora.tools.database.DerbyDB;

public class CompanyDB {

	private String companyName = "Zen";
	private DerbyDB db = null;

	/**
	 * CompanyDB - A simple interface between DerbyDB and
	 * company relevant functions for interaction
	 * 
	 * @author Jazer
	 */
	public CompanyDB() {
		db = openDB(companyName+"DB");
	}

	/**
	 * Insert haircut type record into database
	 * @param type - Haircut Type Name
	 */
	public void addHaircutType(HaircutType type) {
		try {
			db.insert("INSERT INTO haircut_types (haircut_type_name) (SELECT '"+type.name()+"' FROM haircut_types WHERE haircut_type_name = '"+type.name()+"'HAVING count(*)=0)");
			System.out.println("Added to haircut types " + type.name());
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("Failed to add haircut type " + type.name());
			System.err.println(((SQLException)e).getSQLState());
			e.printStackTrace();
		}
	}

	/**
	 * Prints all haircut types to terminal
	 */
	public void listHaircutTypes() {
		try {
			ResultSet res = db.select("SELECT * FROM haircut_types");
			ResultSetMetaData rs = res.getMetaData();

			int colCount = rs.getColumnCount();

			System.out.println();
			for (int i = 1; i <= colCount; i++) System.out.format("%26s", rs.getColumnName(i) + " | ");
			while (res.next()) { System.out.println(); for (int i = 1; i <= colCount; i++) System.out.format("%26s", res.getString(i) + " | "); }
			System.out.println();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Insert customer type record into database
	 * @param type - Customer Type Name
	 */
	public void addCustomerTypes(CustomerType type) {
		try {
			db.insert("INSERT INTO customer_types (customer_type_name) (SELECT '"+type.name()+"' FROM customer_types WHERE customer_type_name = '"+type.name()+"'HAVING count(*)=0)");
			System.out.println("Added to customer types " + type.name());
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("Failed to add customer type " + type.name());
			System.err.println(((SQLException)e).getSQLState());
			e.printStackTrace();
		}
	}

	/**
	 * Prints all customer types to terminal
	 */
	public void listCustomerTypes() {
		try {
			ResultSet res = db.select("SELECT * FROM customer_types");
			ResultSetMetaData rs = res.getMetaData();

			System.out.println();
			int colCount = rs.getColumnCount();
			for (int i = 1; i <= colCount; i++) System.out.format("%26s", rs.getColumnName(i) + " | ");
			while (res.next()) { System.out.println(); for (int i = 1; i <= colCount; i++) System.out.format("%26s", res.getString(i) + " | "); }

			System.out.println();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Insert a new customer into the database
	 * @param customer - New Customer Record 
	 */
	public void addCustomer(Customer customer) {
		try {
			db.insert("INSERT INTO customers (first_name, last_name) VALUES ('"+customer.getFirstName()+"', '"+customer.getLastName()+"')");
			System.out.println("// Added customer " + customer.getFirstName() + " " + customer.getLastName());
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("// Failed to add customer " + customer.getFirstName() + " " + customer.getLastName());
			e.printStackTrace();
		}
	}

	/**
	 * Returns a list of all customers in the database
	 * @return
	 */
	public Customer[] getAllCustomers() {
		return null;
	}

	/**
	 * Returns a customer matching the record ID in the database
	 * Returns null if no record exists with the given ID
	 * @param customerID - Customer Record ID
	 * @return Customer or null if no record found
	 */
	public Customer getCustomer(int customerID) {
		Customer customer = null;
		try {
			ResultSet res = db.select("SELECT * FROM customers WHERE customer_id = " + customerID + "");
			ResultSetMetaData rs = res.getMetaData();

			int colCount = rs.getColumnCount();

			customer = new Customer();

			while (res.next()) {
				customer.setID(Integer.parseInt(res.getString(1)));
				customer.setFirstName(res.getString(2));
				customer.setLastName(res.getString(3));
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}

	public void removeCustomer(Customer customer) {
		int customerID = customer.getID();
	}


	public void addHaircut(int customerID) {
  
	}

	/**
	 * Creates a new connection to the database with the given name
	 * 
	 * @param dbName - Name of the database
	 * @return Database connection
	 */
	private DerbyDB openDB(String dbName) {
		DerbyDB database = null;
		try {
			database = new DerbyDB(dbName);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return database;
	}

	/**
	 * Inserts default records based on ENUM values
	 */
	public void initDefaultTables() {
		System.out.println("// Setting up static table values...");
		for (HaircutType ht : HaircutType.values()) addHaircutType(ht);
		for (CustomerType ct : CustomerType.values()) addCustomerTypes(ct);
	}

	
	/**
	 * Tests all tables and creates them if no table is found
	 */
	public void testDBTables() {
		try {
			System.out.println("// Generating CUSTOMERS table...");
			db.createTable("CREATE TABLE customers(customer_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), first_name varchar(30), last_name varchar(30))");
		} catch (SQLException e) {
			if( ((SQLException)e).getSQLState().contentEquals("X0Y32") ) {
				System.out.println("// Table CUSTOMERS exists...");
			} else {
				System.err.println("// Failed to create table HAIRCUT_TYPE");
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			System.out.println("// Generating HAIRCUT_TYPES table...");
			db.createTable("CREATE TABLE haircut_types(haircut_type_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), haircut_type_name varchar(20))");
		} catch (SQLException e) {
			if( ((SQLException)e).getSQLState().contentEquals("X0Y32") ) {
				System.out.println("// Table HAIRCUT_TYPES exists...");
			} else {
				System.err.println("// Failed to create table HAIRCUT_TYPES");
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


		try {
			System.out.println("// Generating CUSTOMER_TYPES table...");
			db.createTable("CREATE TABLE customer_types(customer_type_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), customer_type_name varchar(20))");
		} catch (SQLException e) {
			if( ((SQLException)e).getSQLState().contentEquals("X0Y32") ) {
				System.out.println("// Table CUSTOMER_TYPES exists...");
			} else {
				System.err.println("// Failed to create table HAIRCUT_TYPES");
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


		try {
			System.out.println("// Generating HAIRCUTS table...");
			db.createTable("CREATE TABLE haircuts(haircut_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), customer_fid INTEGER, haircut_type_fid INTEGER, haircut_timestamp INTEGER)");
		} catch (SQLException e) {
			if( ((SQLException)e).getSQLState().contentEquals("X0Y32") ) {
				System.out.println("// Table HAIRCUTS exists...");
			} else {
				System.err.println("// Failed to create table HAIRCUTS");
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


}
