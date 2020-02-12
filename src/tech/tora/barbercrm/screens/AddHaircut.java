package tech.tora.barbercrm.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import tech.tora.barbercrm.widgets.CustomerType;
import tech.tora.tools.swing.frame.AdvancedFrame;

public class AddHaircut extends AdvancedFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Font txtFont = new Font("Arial", Font.PLAIN, 16);
	
	private JFrame parentFrame = null;
	
	public AddHaircut(JFrame parent) {
		this.parentFrame = parent;
		
		JPanel rootPane = new JPanel(new BorderLayout());
		rootPane.setBackground(Color.LIGHT_GRAY);

		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(Color.GRAY);
		topPanel.setMinimumSize(new Dimension(Integer.MAX_VALUE, 80));
		topPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 80));

		JPanel centerPanel = new JPanel();
		centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
//		centerPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		
		centerPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		

		JPanel botPanel = new JPanel(new BorderLayout());
		botPanel.setMinimumSize(new Dimension(Integer.MAX_VALUE, 40));
		botPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 40));
		botPanel.setBackground(Color.LIGHT_GRAY);
		botPanel.setBorder(new EmptyBorder(8, 5, 8, 5));
		
		rootPane.add(topPanel, BorderLayout.NORTH);
		rootPane.add(centerPanel, BorderLayout.CENTER);
		rootPane.add(botPanel, BorderLayout.SOUTH);

		JLabel titleLabel = new JLabel("Add New Haircut", SwingConstants.CENTER);
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

		JTextField fNametxt = new JTextField();
		JTextField lNametxt = new JTextField();
		JComboBox<CustomerType> custTypecmb = new JComboBox<CustomerType>(CustomerType.values());
		custTypecmb.setSelectedItem(CustomerType.ADULT);
		JSpinner streakint = new JSpinner();
		JTextField teltxt = new JTextField();
		
		c.insets = new Insets(5,5,5,5);
		
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.1;
		c.fill = GridBagConstraints.HORIZONTAL;
		centerPanel.add(fNamelbl, c);
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.9;
		c.fill = GridBagConstraints.HORIZONTAL;
		centerPanel.add(fNametxt, c);
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.1;
		c.fill = GridBagConstraints.HORIZONTAL;
		centerPanel.add(lNamelbl, c);
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0.9;
		c.fill = GridBagConstraints.HORIZONTAL;
		centerPanel.add(lNametxt, c);
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0.1;
		c.fill = GridBagConstraints.HORIZONTAL;
		centerPanel.add(custTypelbl, c);
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 0.9;
		c.fill = GridBagConstraints.HORIZONTAL;
		centerPanel.add(custTypecmb, c);
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 0.1;
		c.fill = GridBagConstraints.HORIZONTAL;
		centerPanel.add(streaklbl, c);
		c.gridx = 1;
		c.gridy = 3;
		c.weightx = 0.9;
		c.fill = GridBagConstraints.HORIZONTAL;
		centerPanel.add(streakint, c);
		
		
		setSize(350, 450);
		setTitle("Add Haircut");
		setContentPane(rootPane);
		setLocationRelativeTo(null);
	}
	
	@Override
	public void openAction() {}

	@Override
	public void closeAction() {
		this.dispose();
		parentFrame.setEnabled(true);
		parentFrame.requestFocus();
	}

	@Override
	public void minimiseAction() {}

	@Override
	public void maximiseAction() {}

	@Override
	public void gainFocusAction() {}

	@Override
	public void loseFocusAction() {}

}
