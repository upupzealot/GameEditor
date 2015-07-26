package frame.gui.menu.file;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.swing.JFileChooser;

import panel.CommonData;
import panel.avatar.gui.AvartarPanel;
import frame.gui.menu.MenuItem;
import frame.gui.window.MainFrame;

@SuppressWarnings("serial")
public class OpenItem extends MenuItem {
	public OpenItem() {
		super("´ò¿ª", "control O");
	}
	
	@Override
	public void actionPerformed() {
		String last_path = Preferences.userNodeForPackage(getClass()).get("LastOpen", CommonData.CURRENT_PATH);
		FileChooser chooser = new FileChooser(last_path);
        int result = chooser.showOpenDialog(MainFrame.getInstance());
        if(result == JFileChooser.APPROVE_OPTION) {
        	File selected_file = chooser.getSelectedFile();
        	
        	long tm = System.nanoTime();
        	try {
				AvartarPanel.getInstance().setDataFile(selected_file);
				Preferences.userNodeForPackage(getClass()).put("LastOpen", selected_file.getAbsolutePath());
				System.out.print((System.nanoTime() - tm) / 1000000 + "ms");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
}
