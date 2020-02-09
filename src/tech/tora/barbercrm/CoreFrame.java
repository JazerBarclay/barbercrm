package tech.tora.barbercrm;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import tech.tora.tools.swing.frame.AdvancedFrame;

public class CoreFrame extends AdvancedFrame {

	public CoreFrame() {
		setSize(800,600);
		setTitle("Home");
		updateContent(new JPanel(), false);
		setLocationRelativeTo(null);
		
		setupContent();
		setupMenu();
		
		setVisible(true);
		
	}
	
	@Override
	public void closeAction() {
//		this.dispose();
		System.exit(0);
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

	
	public void setupContent() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		updateContent(panel, false);
	}
	
	public void setupMenu() {
		
//		updateMenu(activeLayout.getMenu());
	}
	
}
