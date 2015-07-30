package frame.core.io;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import panel.CommonData;
import frame.gui.window.MainFrame;

public class PngSaver extends Saver{
	public static void SaveImage(BufferedImage image) {
		String last_path = Preferences.userNodeForPackage(Saver.class).get("LastSave", CommonData.CURRENT_PATH);
		//System.out.println(last_path);
		JFileChooser chooser = new JFileChooser(last_path);
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
        	
    		Preferences.userNodeForPackage(Saver.class).put("LastSave", folder);
        	
        	//long tm = System.nanoTime();
        	try {
				
        		File output = new File(folder + "/" + file_name);
        		
        		ImageIO.write(image, "png", output);
        		//System.out.println(folder);
				//System.out.println((System.nanoTime() - tm) / 1000000 + "ms");
			} catch (IOException e) {
				//TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
}
