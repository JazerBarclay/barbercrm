package tech.tora.barbercrm.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import tech.tora.barbercrm.db.CompanyDB;
import tech.tora.barbercrm.widgets.Customer;
import tech.tora.barbercrm.widgets.HaircutType;
import tech.tora.tools.swing.frame.AdvancedFrame;
import tech.tora.tools.system.Logging;

public class AddHaircut extends AdvancedFrame {


	private static final long serialVersionUID = 1L;

	private Font txtFont = new Font("Arial", Font.PLAIN, 16);

	private ArrayList<Customer> customersList = new ArrayList<Customer>();

	private JFrame parentFrame = null;

	private JPanel centerPanel;
	private GridBagConstraints c = new GridBagConstraints();

	private JComboBox<Customer> customercmb;
	private JComboBox<HaircutType> haircutTypecmb;


	public AddHaircut(JFrame parent) {
		this.parentFrame = parent;


		CompanyDB db = new CompanyDB();

		try {
			db.open();
		} catch (ClassNotFoundException | SQLException ex) {

			if (((SQLException)ex).getSQLState().equals("XJ040")) {
				Logging.errorMessage(2, null, "Database In Use", "It appears this application is already open.\nPlease close any other copies of this application and try again.");
				System.exit(2);
			}
			ex.printStackTrace();
		}

		customersList = db.getAllCustomers();

		try {
			db.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}


		JPanel rootPane = new JPanel(new BorderLayout());
		rootPane.setBackground(Color.LIGHT_GRAY);

		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(Color.GRAY);
		topPanel.setMinimumSize(new Dimension(Integer.MAX_VALUE, 80));
		topPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 80));

		centerPanel = new JPanel();
		centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		centerPanel.setLayout(new GridBagLayout());


		JPanel botPanel = new JPanel(new BorderLayout());
		botPanel.setMinimumSize(new Dimension(Integer.MAX_VALUE, 40));
		botPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 40));
		botPanel.setBackground(Color.LIGHT_GRAY);
		botPanel.setBorder(new EmptyBorder(8, 20, 8, 20));

		rootPane.add(topPanel, BorderLayout.NORTH);
		rootPane.add(centerPanel, BorderLayout.CENTER);
		rootPane.add(botPanel, BorderLayout.SOUTH);

		JLabel titleLabel = new JLabel("Add New Haircut", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Arial", Font.PLAIN, 32));
		titleLabel.setBorder(new EmptyBorder(0, 20, 0, 20));
		topPanel.add(titleLabel, BorderLayout.CENTER);


		JLabel dateLbl = new JLabel("Date/Time : ", SwingConstants.RIGHT);
		dateLbl.setFont(txtFont);

		JLabel nameLbl = new JLabel("Customer: ", SwingConstants.RIGHT);
		nameLbl.setFont(txtFont);

		JLabel typeLbl = new JLabel("Haircut: ", SwingConstants.RIGHT);
		typeLbl.setFont(txtFont);

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String dateString = formatter.format(date);
		JFormattedTextField formatText = new JFormattedTextField(formatter);
		formatText.setText(dateString);
		formatText.setEnabled(false);


		Customer[] customers = new Customer[customersList.size()];
		for (int i = 0; i < customersList.size(); i++) customers[i] = customersList.get(i);

		customercmb = new JComboBox<Customer>(customers);
		customercmb.setEditable(true);
		
		((JTextField) customercmb.getEditor().getEditorComponent()).setText("");
		
		customercmb.getEditor().getEditorComponent().addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				String value = customercmb.getEditor().getItem().toString();

				ArrayList<Customer> filtered = new ArrayList<Customer>();
				for (int i = 0; i < customersList.size(); i++) {
					if (customersList.get(i).toString().toLowerCase().contains(value.toLowerCase())) {
						filtered.add(customersList.get(i));
					}
				}

				if (filtered.size() > 0) {
					DefaultComboBoxModel<Customer> model = (DefaultComboBoxModel<Customer>) customercmb.getModel();
					model.removeAllElements();
					for (Customer s: filtered) model.addElement(s);

					customercmb.showPopup();

					JTextField textfield = (JTextField) customercmb.getEditor().getEditorComponent();
					textfield.setText(value);
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyTyped(KeyEvent e) {}
		});

		haircutTypecmb = new JComboBox<HaircutType>(HaircutType.values());
		haircutTypecmb.setSelectedItem(HaircutType.ADULT);


		// Padding
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.HORIZONTAL;

		double lblWeight = 0.1;
		double txtWeight = 0.9999;

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = lblWeight;
		centerPanel.add(dateLbl, c);

		c.gridy = 1;
		centerPanel.add(nameLbl, c);

		c.gridy = 2;
		centerPanel.add(typeLbl, c);

		c.gridx = 1;
		c.gridy = 0;
		c.weightx = txtWeight;
		centerPanel.add(formatText, c);

		c.gridy = 1;
		centerPanel.add(customercmb, c);

		c.gridy = 2;
		centerPanel.add(haircutTypecmb, c);


		JButton btnSave = new JButton("Save");
		btnSave.setPreferredSize(new Dimension(80, 40));
		btnSave.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) { }

			@Override
			public void mousePressed(MouseEvent e) { }

			@Override
			public void mouseExited(MouseEvent e) { }

			@Override
			public void mouseEntered(MouseEvent e) { }

			@Override
			public void mouseClicked(MouseEvent e) {

				System.out.println("Save Clicked");
				System.out.println(""+System.currentTimeMillis());
				try { 
					System.out.println(""+((Customer)customercmb.getSelectedItem()).getID());
				} catch (ClassCastException ex) {
					System.err.println("ERR");
				}
				
			}
		});
		botPanel.add(btnSave, BorderLayout.EAST);

		setSize(400, 300);
		setTitle("Add New Haircut");
		setContentPane(rootPane);
		setLocationRelativeTo(null);
		setResizable(true);
		
		setType(javax.swing.JFrame.Type.UTILITY);

	}

	@Override
	public void closeAction() {
		parentFrame.setEnabled(true);
		parentFrame.requestFocus();
		this.dispose();
	}

	@Override
	public void openAction() {}

	@Override
	public void minimiseAction() {}

	@Override
	public void maximiseAction() {}

	@Override
	public void gainFocusAction() {}

	@Override
	public void loseFocusAction() {
		AddHaircut.this.requestFocus();
	}

}
