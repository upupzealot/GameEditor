package panel.weapon.gui;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFileChooser;

import frame.gui.window.MainFrame;
import panel.QuickButton;
import panel.weapon.gui.dialog.WeaponDataImportDialog;

@SuppressWarnings("serial")
public class ModifyButton extends QuickButton{
	public ModifyButton() {
		super("ÐÞ¸ÄÖ¡ÎÄ¼þ");
	}

	@Override
	public void OnClick() {
		if(WeaponPanel.current_file != null) {
			try {
				WeaponDataImportDialog dialog = new WeaponDataImportDialog(WeaponPanel.current_file);
				int result = dialog.showModifyDialog();
				if(result == JFileChooser.APPROVE_OPTION) {
					WeaponPanel.getInstance().setSharedWeaponData(dialog.getWeaponData());
					
					MainFrame.getInstance().setMinimumSize(new Dimension());
					MainFrame.getInstance().pack();
					MainFrame.getInstance().setMinimumSize(getSize());
					MainFrame.getInstance().repaint();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
