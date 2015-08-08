package panel.weapon.gui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import panel.CommonData;
import panel.QuickButton;
import panel.weapon.core.WeaponData;

@SuppressWarnings("serial")
public class SaveButton extends QuickButton{
	public SaveButton() {
		super("±£´æ");
		setFocusable(false);
	}

	@Override
	public void OnClick() {
		WeaponData data = WeaponPanel.getSharedWeaponData();

		try {
			File date_file = new File(CommonData.CURRENT_PATH + "/res/avatar/weapon/1°Ñµ¶.data");
			FileWriter writer = new FileWriter(date_file);
			writer.write(data.toJSONString());
			System.out.println(data.toJSONString());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
