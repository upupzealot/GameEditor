package frame.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class MenuItem extends JMenuItem implements ActionListener{
	public MenuItem(String name, String shortcut) {
		super(name);
		setAccelerator(KeyStroke.getKeyStroke(shortcut));
		addActionListener(this);
	}
	
	public MenuItem(String name) {
		super(name);
		addActionListener(this);
	}
	
	public void actionPerformed() {
		
	}

	@Override
	public final void actionPerformed(ActionEvent arg0) {
		actionPerformed();
	}
}
