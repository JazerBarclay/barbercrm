package tech.tora.barbercrm.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import tech.tora.tools.swing.frame.AdvancedFrame;

public class CoreFrame extends AdvancedFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CoreFrame() {
		JPanel rootPane = new JPanel(new BorderLayout());
		rootPane.setBackground(Color.LIGHT_GRAY);

		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(Color.GRAY);
		topPanel.setMinimumSize(new Dimension(Integer.MAX_VALUE,120));
		topPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 120));
		
//		JLabel thumb = new JLabel(new ImageIcon(System.getProperty("user.dir").toString() + "/res/logo1.png"));

		JPanel centerPanel = new JPanel(new GridLayout(2,2,20,20));
		centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

		JPanel botPanel = new JPanel(new BorderLayout());
		botPanel.setMinimumSize(new Dimension(Integer.MAX_VALUE, 40));
		botPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 40));
		botPanel.setBackground(Color.LIGHT_GRAY);
		botPanel.setBorder(new EmptyBorder(8, 5, 8, 5));
		
		// Items
		
		JLabel titleLabel = new JLabel("Sidley Street Barbers", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Arial", Font.PLAIN, 32));
		titleLabel.setBorder(new EmptyBorder(0, 20, 0, 20));
		
		JPanel btn1 = new JPanel(new BorderLayout());
		btn1.setBackground(new Color(230,230,230));
		btn1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		btn1.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {btn1.setBackground(new Color(230, 230, 230));}
			
			@Override
			public void mouseEntered(MouseEvent e) {btn1.setBackground(new Color(200, 200, 200));}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				CoreFrame.this.setEnabled(false);
				new AddHaircut(CoreFrame.this).setVisible(true);
			}
		});
		JLabel lbl1 = new JLabel("Add Haircut", SwingConstants.CENTER);
		lbl1.setFont(new Font("Arial", Font.PLAIN, 24));
		btn1.add(lbl1, BorderLayout.CENTER);
		
		
		JPanel btn2 = new JPanel(new BorderLayout());
		btn2.setBackground(new Color(230,230,230));
		btn2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		btn2.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {btn2.setBackground(new Color(230, 230, 230));}
			
			@Override
			public void mouseEntered(MouseEvent e) {btn2.setBackground(new Color(200, 200, 200));}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				CoreFrame.this.setEnabled(false);
				new AddCustomer(CoreFrame.this).setVisible(true);
			}
		});
		
		JLabel lbl2 = new JLabel("Add Customer", SwingConstants.CENTER);
		lbl2.setFont(new Font("Arial", Font.PLAIN, 24));
		btn2.add(lbl2, BorderLayout.CENTER);
		
		
		JPanel btn3 = new JPanel(new BorderLayout());
		btn3.setBackground(new Color(230,230,230));
		btn3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		btn3.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {btn3.setBackground(new Color(230, 230, 230));}
			
			@Override
			public void mouseEntered(MouseEvent e) {btn3.setBackground(new Color(200, 200, 200));}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Clicked BTN_3");
			}
		});
		JLabel lbl3 = new JLabel("View Customers", SwingConstants.CENTER);
		lbl3.setFont(new Font("Arial", Font.PLAIN, 24));
		btn3.add(lbl3, BorderLayout.CENTER);
		
		
		JPanel btn4 = new JPanel(new BorderLayout());
		btn4.setBackground(new Color(230,230,230));
		btn4.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		btn4.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {btn4.setBackground(new Color(230, 230, 230));}
			
			@Override
			public void mouseEntered(MouseEvent e) {btn4.setBackground(new Color(200, 200, 200));}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Clicked BTN_4");
			}
		});
		JLabel lbl4 = new JLabel("Admin", SwingConstants.CENTER);
		lbl4.setFont(new Font("Arial", Font.PLAIN, 24));
		btn4.add(lbl4, BorderLayout.CENTER);

		
		rootPane.add(topPanel, BorderLayout.NORTH);
		rootPane.add(centerPanel, BorderLayout.CENTER);
		rootPane.add(botPanel, BorderLayout.SOUTH);

		centerPanel.add(btn1);
		centerPanel.add(btn2);
		centerPanel.add(btn3);
		centerPanel.add(btn4);

		topPanel.add(titleLabel);


		setSize(800, 600);
		setTitle("Barbers");
		setContentPane(rootPane);
		setLocationRelativeTo(null);

		JMenuBar menu = new JMenuBar();
		menu.setVisible(false);
		JMenu file = new JMenu("File");

		JMenuItem test = new JMenuItem("Test");
		test.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(test);
			}
		});

//		setAlwaysOnTop(true);
		setResizable(false);
		file.add(test);
		menu.add(file);
		updateMenu(menu);
	}

	@Override
	public void closeAction() {
		this.dispose();
		System.exit(0);
	}

	@Override
	public void minimiseAction() {}

	@Override
	public void openAction() {}

	@Override
	public void maximiseAction() {}

	@Override
	public void gainFocusAction() {}

	@Override
	public void loseFocusAction() {}
}
