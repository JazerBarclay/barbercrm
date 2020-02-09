package tech.tora.barbercrm;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import tech.tora.barbercrm.db.CompanyDB;
import tech.tora.barbercrm.widgets.Customer;

public class Launcher {
	
	private boolean initLookAndFeel() {
		// Set look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			return true;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		return false;
	}

	private void initMac() {
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Barber CRM");
		System.setProperty("apple.awt.fileDialogForDirectories", "true");
		System.setProperty("apple.laf.useScreenMenuBar", "true");
	}
	
	
	public Launcher() {
		
		if (System.getProperty("os.name").contains("Mac")) initMac();
		
		if (!initLookAndFeel()) {
			System.err.println("Failed look and feel in Launcher.java");
			System.exit(1);
		}
		
		// Connect to db and test all tables and fields
		CompanyDB db = new CompanyDB();
		db.testDBTables();
		db.initDefaultTables();
		
		db.listCustomerTypes();
		db.listHaircutTypes();
		
		Customer customer = new Customer();
		customer.setFirstName("John");
		customer.setLastName("Barclay");
		
		customer = db.addCustomer(customer);
		
		db.listCustomers();
		
		// Launch home screen
		new CoreFrame().setVisible(true);
	}

	public static void main(String[] args) {
		new Launcher();
	}

}
