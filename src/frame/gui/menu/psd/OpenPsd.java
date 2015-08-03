package frame.gui.menu.psd;

import java.io.File;
import java.io.IOException;

import panel.avatar.gui.AvartarPanel;
import frame.core.io.PsdOpener;
import frame.gui.menu.MenuItem;

@SuppressWarnings("serial")
public class OpenPsd extends MenuItem{
	public OpenPsd() {
		super("´ò¿ªPSD", "control shift O");
	}
	
	@Override
	public void actionPerformed() {
		File selected_file = PsdOpener.OpenPsd();
		if(selected_file != null) {
			try {
				AvartarPanel.getInstance().setDataFile(selected_file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
