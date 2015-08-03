package frame.gui.menu.file;

import panel.avatar.gui.PreviewPanel;
import frame.core.io.PngSaver;
import frame.gui.menu.MenuItem;

@SuppressWarnings("serial")
public class SaveItem extends MenuItem{
	public SaveItem() {
		super("Áí´æµ±Ç°Í¼Ïñ",  "control S");
	}
	
	@Override
	public void actionPerformed() {
		PngSaver.SaveImage(PreviewPanel.getInstance().getBuffer());
	}
}
