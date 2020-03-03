package tech.tora.barbercrm.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import tech.tora.barbercrm.db.CompanyDB;
import tech.tora.barbercrm.widgets.Customer;
import tech.tora.barbercrm.widgets.CustomerType;
import tech.tora.tools.swing.frame.AdvancedFrame;
import tech.tora.tools.system.Logging;

public class AddCustomer extends AdvancedFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Font txtFont = new Font("Arial", Font.PLAIN, 16);
	
	private JFrame parentFrame = null;
	
	private GridBagConstraints c = new GridBagConstraints();
	private JPanel centerPanel;
	
	
	private JTextField fNametxt;
	private JTextField lNametxt;
	private JComboBox<CustomerType> custTypecmb;
	private JSpinner streakint;
	private JTextField emailtxt;
	private JTextArea commentsArea;
	
	
	public AddCustomer(JFrame parent) {
		this.parentFrame = parent;
		
		JPanel rootPane = new JPanel(new BorderLayout());
		rootPane.setBackground(Color.LIGHT_GRAY);

		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(Color.GRAY);
		topPanel.setMinimumSize(new Dimension(Integer.MAX_VALUE, 80));
		topPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 80));

		centerPanel = new JPanel();
		centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
//		centerPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		centerPanel.setLayout(new GridBagLayout());
		

		JPanel botPanel = new JPanel(new BorderLayout());
		botPanel.setMinimumSize(new Dimension(Integer.MAX_VALUE, 40));
		botPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 40));
		botPanel.setBackground(Color.LIGHT_GRAY);
		botPanel.setBorder(new EmptyBorder(8, 20, 8, 20));
		
		rootPane.add(topPanel, BorderLayout.NORTH);
		rootPane.add(centerPanel, BorderLayout.CENTER);
		rootPane.add(botPanel, BorderLayout.SOUTH);

		JLabel titleLabel = new JLabel("Add New Customer", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Arial", Font.PLAIN, 32));
		titleLabel.setBorder(new EmptyBorder(0, 20, 0, 20));
		topPanel.add(titleLabel, BorderLayout.CENTER);
		
		JLabel fNamelbl = new JLabel("First Name: ", SwingConstants.RIGHT);
		fNamelbl.setFont(txtFont);
		
		JLabel lNamelbl = new JLabel("Last Name: ", SwingConstants.RIGHT);
		lNamelbl.setFont(txtFont);
		
		JLabel custTypelbl = new JLabel("Type: ", SwingConstants.RIGHT);
		custTypelbl.setFont(txtFont);
		
		JLabel streaklbl = new JLabel("Current Streak: ", SwingConstants.RIGHT);
		streaklbl.setFont(txtFont);
		
		JLabel freeCutslbl = new JLabel("Total Free: ", SwingConstants.RIGHT);
		freeCutslbl.setFont(txtFont);
		
		JLabel emaillbl = new JLabel("Email", SwingConstants.RIGHT);
		emaillbl.setFont(txtFont);
		
		JLabel commentslbl = new JLabel("Comments", SwingConstants.RIGHT);
		commentslbl.setFont(txtFont);

		fNametxt = new JTextField();
		lNametxt = new JTextField();
		custTypecmb = new JComboBox<CustomerType>(CustomerType.values());
		custTypecmb.setSelectedItem(CustomerType.ADULT);
		streakint = new JSpinner();
		emailtxt = new JTextField();
		commentsArea = new JTextArea();

//		JButton btnCancel = new JButton("Cancel");
//		btnCancel.setPreferredSize(new Dimension(80, 40));
//		botPanel.add(btnCancel, BorderLayout.WEST);
		
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
				
				if (fNametxt.getText().equals("") && lNametxt.getText().equals("")) {
					System.err.println("Name field(s) empty!");
				} else {
					
					// Connect to db
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
					
					Customer customer = new Customer();
					customer.setFirstName(fNametxt.getText());
					customer.setLastName(lNametxt.getText());
					customer.setCustomerType((CustomerType) custTypecmb.getSelectedItem());
					customer.setCurrentStreak((int) streakint.getValue());
					customer.setEmail(emailtxt.getText());
					customer.setComments(commentsArea.getText());
					
					db.addCustomer(customer);
					
					try {
						db.close();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
					
					Logging.infoMessage(AddCustomer.this, "Customer Added", "Added new customer");
					
					AddCustomer.this.closeAction();
					
				}
				
			}
		});
		botPanel.add(btnSave, BorderLayout.EAST);
		
		// Padding
		c.insets = new Insets(5,5,5,5);
		
		c.fill = GridBagConstraints.HORIZONTAL;

		double lblWeight = 0.1;
		double txtWeight = 0.9;
		
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = lblWeight;
		centerPanel.add(fNamelbl, c);
		
		c.gridx = 1;
		c.weightx = txtWeight;
		centerPanel.add(fNametxt, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = lblWeight;
		centerPanel.add(lNamelbl, c);
		
		c.gridx = 1;
		c.weightx = txtWeight;
		centerPanel.add(lNametxt, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = lblWeight;
		centerPanel.add(custTypelbl, c);
		
		c.gridx = 1;
		c.weightx = txtWeight;
		centerPanel.add(custTypecmb, c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = lblWeight;
		centerPanel.add(streaklbl, c);
		
		c.gridx = 1;
		c.weightx = txtWeight;
		centerPanel.add(streakint, c);
		
		c.gridx = 0;
		c.gridy = 4;
		c.weightx = lblWeight;
		centerPanel.add(emaillbl, c);
		
		c.gridx = 1;
		c.weightx = txtWeight;
		centerPanel.add(emailtxt, c);
		
		c.gridx = 0;
		c.gridy = 5;
		c.weightx = lblWeight;
		centerPanel.add(commentslbl, c);
		
		c.gridx = 1;
		c.weightx = txtWeight;
		c.weighty = 3;
		c.gridheight = 2;
		c.fill = GridBagConstraints.BOTH;
//		commentsArea.setPreferredSize(new Dimension(Integer.MAX_VALUE, 300));
		centerPanel.add(commentsArea, c);
		
		setSize(400, 450);
		setTitle("Add Customer");
		setContentPane(rootPane);
		setLocationRelativeTo(null);
		setResizable(false);
		
		setType(javax.swing.JFrame.Type.UTILITY);
	}
	
	@Override
	public void openAction() {}

	@Override
	public void closeAction() {
		parentFrame.setEnabled(true);
		parentFrame.requestFocus();
		this.dispose();
	}

	@Override
	public void minimiseAction() {}

	@Override
	public void maximiseAction() {}

	@Override
	public void gainFocusAction() {}

	@Override
	public void loseFocusAction() {
		AddCustomer.this.requestFocus();
	}
	
}
