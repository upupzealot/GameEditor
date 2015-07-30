package frame.core.io;

import java.io.File;
import java.util.prefs.Preferences;

import javax.swing.JFileChooser;

import panel.CommonData;
import frame.gui.window.MainFrame;

public class FolderOpener extends Opener{
	public static File OpenFolder() {
		String last_path = Preferences.userNodeForPackage(Opener.class).get("LastOpen", CommonData.CURRENT_PATH);
		JFileChooser chooser = new JFileChooser(last_path);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = chooser.showOpenDialog(MainFrame.getInstance());
        if(result == JFileChooser.APPROVE_OPTION) {
        	File selected_file = chooser.getSelectedFile();
        	Preferences.userNodeForPackage(Opener.class).put("LastOpen", selected_file.getAbsolutePath());
        	
        	return selected_file;
        } else {
        	return null;
        }
	}
}
