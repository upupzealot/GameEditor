package frame.gui.menu.file;

import javax.swing.JMenu;

@SuppressWarnings("serial")
public class FileMenu extends JMenu{
	public FileMenu() {
		super("�ļ�");
		add(new OpenItem());
		add(new SaveItem());
	}
}
