package frame.gui.menu.psd;

import javax.swing.JMenu;

@SuppressWarnings("serial")
public class PsdMenu extends JMenu{
	public PsdMenu() {
		super("PSD����");
		
		add(new OpenPsd());
	}
}
