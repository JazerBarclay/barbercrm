package tech.tora.barbercrm;

import java.sql.SQLException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import tech.tora.barbercrm.db.CompanyDB;
import tech.tora.tools.system.Logging;

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
		try {
			db.open();
		} catch (ClassNotFoundException | SQLException e) {
			
			if (((SQLException)e).getSQLState().equals("XJ040")) {
				Logging.errorMessage(2, null, "Database In Use", "It appears this application is already open.\nPlease close any other copies of this application and try again.");
				System.exit(2);
			}
			e.printStackTrace();
		}
		
		db.testDBTables();
		db.initDefaultTables();
		try {
			db.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Launch home screen
		new CoreFrame().setVisible(true);
	}

	public static void main(String[] args) {
		new Launcher();
	}

}
