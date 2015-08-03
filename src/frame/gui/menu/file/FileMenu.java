package frame.gui.menu.file;

import javax.swing.JMenu;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class FileMenu extends JMenu{
	public FileMenu() {
		super("�ļ�");
		
		add(new OpenItem());
		add(new FolderImportItem());
		
		add(new JSeparator());
		
		add(new SaveItem());
		add(new FolderExportItem());
	}
}
