package frame.core.io;

import java.io.File;
import java.util.prefs.Preferences;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import panel.CommonData;
import frame.gui.window.MainFrame;

public class PngOpener {
	public static File OpenPng() {
		String last_path = Preferences.userNodeForPackage(Opener.class).get("LastOpen", CommonData.CURRENT_PATH);
		JFileChooser chooser = new JFileChooser(last_path);
		chooser.setFileFilter(new FileFilter() {
			@Override
			public String getDescription() {
				return "PNG 文件";
			}
			
			@Override
			public boolean accept(File file) {
				return file.isDirectory() || file.getName().toLowerCase().endsWith(".png");
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
	
	public static File OpenPng(File path) {
		JFileChooser chooser = new JFileChooser(path);
		chooser.setFileFilter(new FileFilter() {
			@Override
			public String getDescription() {
				return "PNG 文件";
			}
			
			@Override
			public boolean accept(File file) {
				return file.isDirectory() || file.getName().toLowerCase().endsWith(".png");
			}
		});
        int result = chooser.showOpenDialog(MainFrame.getInstance());
        if(result == JFileChooser.APPROVE_OPTION) {
        	File selected_file = chooser.getSelectedFile();
        	return selected_file;
        } else {
        	return null;
        }
	}
}
