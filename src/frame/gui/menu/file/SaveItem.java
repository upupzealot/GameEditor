package frame.gui.menu.file;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import panel.CommonData;
import panel.avatar.gui.PreviewPanel;
import frame.gui.menu.MenuItem;
import frame.gui.window.MainFrame;

@SuppressWarnings("serial")
public class SaveItem extends MenuItem{
	public SaveItem() {
		super("Αν΄ζ",  "control S");
	}
	
	@Override
	public void actionPerformed() {
		String last_path = Preferences.userNodeForPackage(getClass()).get("LastSave", CommonData.CURRENT_PATH);
		System.out.println(last_path);
		JFileChooser chooser = new JFileChooser(last_path);
		//chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setFileFilter(new FileFilter() {
			@Override
			public String getDescription() {
				return "PNG Image File";
			}
			
			@Override
			public boolean accept(File file) {
				return file.getName().toLowerCase().endsWith(".png") || file.isDirectory();
			}
		});
        int result = chooser.showSaveDialog(MainFrame.getInstance());
        if(result == JFileChooser.APPROVE_OPTION) {
        	File selected_file = chooser.getSelectedFile();
        	String folder = selected_file.getParent();
        	String file_name = selected_file.getName();
        	if(!file_name.toLowerCase().endsWith(".png")) {
        		file_name += ".png";
        	}
        	
    		Preferences.userNodeForPackage(getClass()).put("LastSave", folder);
        	
        	long tm = System.nanoTime();
        	try {
				
        		File output = new File(folder + "/" + file_name);
        		//ImageOutputStream stream = ImageIO.createImageOutputStream(output);
        		ImageIO.write(PreviewPanel.getInstance().getBuffer(), "png", output);
        		System.out.println(folder);
				System.out.println((System.nanoTime() - tm) / 1000000 + "ms");
			} catch (IOException e) {
				//TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
}
