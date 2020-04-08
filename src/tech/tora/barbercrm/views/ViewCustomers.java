package tech.tora.barbercrm.views;

import javax.swing.JFrame;

import tech.tora.tools.swing.frame.AdvancedFrame;

public class ViewCustomers extends AdvancedFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame parentFrame = null;
	
	public ViewCustomers(JFrame parent) {
		this.parentFrame = parent;
		
		setSize(400, 450);
		setTitle("Customers");
		setContentPane(rootPane);
		setLocationRelativeTo(null);
		setResizable(false);
		
	}
	
	
	@Override
	public void closeAction() {
		this.dispose();
		parentFrame.setEnabled(true);
		parentFrame.requestFocus();
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
	public void loseFocusAction() {}

}
