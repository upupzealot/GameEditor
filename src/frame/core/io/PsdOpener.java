package frame.core.io;

import java.io.File;
import java.util.prefs.Preferences;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import panel.CommonData;
import frame.gui.window.MainFrame;

public class PsdOpener extends Opener {
	public static File OpenPsd() {
		String last_path = Preferences.userNodeForPackage(Opener.class).get("LastOpen", CommonData.CURRENT_PATH);
		JFileChooser chooser = new JFileChooser(last_path);
		chooser.setFileFilter(new FileFilter() {
			@Override
			public String getDescription() {
				return "PSD нд╪Ч";
			}
			
			@Override
			public boolean accept(File file) {
				return file.isDirectory() || file.getName().toLowerCase().endsWith(".psd");
			}
		});
        int result = chooser.showOpenDialog(MainFrame.getInstance());
        if(result == JFileChooser.APPROVE_OPTION) {
        	File selected_file = chooser.getSelectedFile();
        	Preferences.userNodeForPackage(Opener.class).put("LastOpen", selected_file.getParent());
        	
        	return selected_file;
        } else {
        	return null;
        }
	}
}
