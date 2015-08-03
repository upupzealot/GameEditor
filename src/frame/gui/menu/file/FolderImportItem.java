package frame.gui.menu.file;

import java.io.File;
import java.io.IOException;

import panel.avatar.core.DataCore;
import panel.avatar.gui.AvartarPanel;
import frame.core.io.FolderOpener;
import frame.gui.menu.MenuItem;

@SuppressWarnings("serial")
public class FolderImportItem extends MenuItem{
	public FolderImportItem() {
		super("从目录导入", null);
	}
	
	@Override
	public void actionPerformed() {
		File selected_folder = FolderOpener.OpenFolder();
		if(selected_folder != null) {
			try {
				AvartarPanel.getInstance().importDataCore(new DataCore(selected_folder));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
