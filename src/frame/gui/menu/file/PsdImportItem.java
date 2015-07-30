package frame.gui.menu.file;

import java.io.File;
import java.io.IOException;

import panel.avatar.core.DataCore;
import panel.avatar.gui.AvartarPanel;
import frame.core.io.PsdOpener;
import frame.gui.menu.MenuItem;

@SuppressWarnings("serial")
public class PsdImportItem extends MenuItem{
	public PsdImportItem() {
		super("¥”PSDµº»Î", null);
	}
	
	@Override
	public void actionPerformed() {
		File selected_file = PsdOpener.OpenPsd();
		if(selected_file != null) {
			try {
				DataCore data = new DataCore(selected_file);
				AvartarPanel.getInstance().importDataCore(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
