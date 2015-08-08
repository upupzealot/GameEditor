package panel.weapon.gui;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import panel.CommonData;
import panel.QuickButton;
import panel.weapon.core.WeaponData;
import frame.core.io.PngOpener;
import frame.gui.window.MainFrame;

@SuppressWarnings("serial")
public class OpenButton extends QuickButton{
	public OpenButton() {
		super("´ò¿ª");
		setFocusable(false);
	}

	@Override
	public void OnClick() {
		File selected_file = PngOpener.OpenPng(new File(CommonData.CURRENT_PATH + "/res/avatar/weapon"));
		if(selected_file != null) {
			try {
				WeaponData data = new WeaponData(selected_file);
				//new AnimateData(selected_file);
				WeaponPanel.setSharedWeaponData(data);

				MainFrame.getInstance().setMinimumSize(new Dimension());
				MainFrame.getInstance().pack();
				MainFrame.getInstance().setMinimumSize(getSize());
				MainFrame.getInstance().repaint();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
