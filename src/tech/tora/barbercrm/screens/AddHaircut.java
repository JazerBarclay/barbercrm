package tech.tora.barbercrm.screens;

import javax.swing.JFrame;
import javax.swing.JPanel;

import tech.tora.tools.swing.frame.AdvancedFrame;

public class AddHaircut extends AdvancedFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame parentFrame = null;
	
	public AddHaircut(JFrame parent) {
		this.parentFrame = parent;
		
		setSize(400, 600);
		setTitle("Add Haircut");
		setContentPane(new JPanel());
		setLocationRelativeTo(null);
	}
	
	@Override
	public void openAction() {}

	@Override
	public void closeAction() {
		this.dispose();
		parentFrame.enable(true);
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
