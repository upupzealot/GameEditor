package frame.gui.menu;

import javax.swing.JMenuBar;

import frame.gui.menu.file.FileMenu;
import frame.gui.menu.psd.PsdMenu;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar{
	public MenuBar() {
		add(new FileMenu());
		add(new PsdMenu());
	}
}
