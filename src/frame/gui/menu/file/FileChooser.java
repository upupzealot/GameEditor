package frame.gui.menu.file;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

@SuppressWarnings("serial")
public class FileChooser extends JFileChooser{
    FileChooser(String start_directory) {
        super(start_directory);
        setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    return file.isDirectory() || file.getName().toLowerCase().endsWith(".psd");
                }

                @Override
                public String getDescription() {
                    return "PSD File or Workspace Folder";
                }
            });
    }
}