package frame.gui.menu;

import javax.swing.JMenuBar;

import frame.gui.menu.file.FileMenu;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar{
	public MenuBar() {
		add(new FileMenu());
	}
}
