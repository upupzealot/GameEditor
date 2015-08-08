package panel.weapon.gui;

import java.awt.BorderLayout;
import java.awt.KeyboardFocusManager;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

import panel.CommonData;
import panel.weapon.core.WeaponData;

@SuppressWarnings("serial")
public class WeaponPanel extends JPanel{
	private static WeaponPanel instance = null;
	public static WeaponPanel getInstance() {
		if(instance == null) {
			instance = new WeaponPanel();
		}
		return instance;
	}
	
	private static WeaponData SharedWeaponData = null;
	public static WeaponData getSharedWeaponData() {
		return SharedWeaponData;
	}
	
	public static void setSharedWeaponData(WeaponData data) {
		SharedWeaponData = data;
		FrameSelectPanel.getInstance().SetWeaponData(data);
		PreviewPanel.getInstance().SetWeaponData(data);
		if(instance != null) {
			instance.save_button.setEnabled(data != null);
		}
	}
	
	private JButton open_button;
	private JButton save_button;
	private WeaponPanel() {
		try {
			setSharedWeaponData(new WeaponData(new File(CommonData.CURRENT_PATH + "/res/avatar/weapon/1°Ñµ¶.png")));
			//setSharedWeaponData(null);
		} catch (IOException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setLayout(new BorderLayout());
		add(FrameSelectPanel.getInstance(), BorderLayout.NORTH);
		add(PreviewPanel.getInstance(), BorderLayout.CENTER);

		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventPostProcessor(PreviewPanel.getInstance().getKeyEventPostProcessor());
		
		JPanel menu_panel = new JPanel();
		menu_panel.setLayout(new BorderLayout());
		open_button = new OpenButton();
		menu_panel.add(open_button, BorderLayout.WEST);
		save_button = new SaveButton();
		menu_panel.add(save_button, BorderLayout.EAST);
		save_button.setEnabled(getSharedWeaponData() != null);
		add(menu_panel, BorderLayout.SOUTH);
	} 
}