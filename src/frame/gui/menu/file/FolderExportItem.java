package frame.gui.menu.file;

import java.io.File;

import panel.avatar.gui.AvartarPanel;
import frame.core.io.FolderOpener;
import frame.gui.menu.MenuItem;

@SuppressWarnings("serial")
public class FolderExportItem extends MenuItem{
	public FolderExportItem() {
		super("导出到目录", null);
	}
	
	@Override
	public void actionPerformed() {
		File selected_folder = FolderOpener.OpenFolder();
		if(!selected_folder.exists()) {
			selected_folder.mkdirs();
		}
		if(selected_folder != null) {
			AvartarPanel.getInstance().exportDataCore(selected_folder);
		}
	}
}
