package panel.weapon.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import panel.gui.DoubleIcon;
import panel.weapon.core.WeaponData;

@SuppressWarnings("serial")
public class FrameSelectPanel extends JPanel{
	private static FrameSelectPanel instance = null;
	public static FrameSelectPanel getInstance() {
		if(instance == null) {
			instance = new FrameSelectPanel();
		}
		return instance;
	}
	
	private JToggleButton run_button;
	private FrameSelectPanel() {
		setLayout(new GridLayout(1, 5, 2, 0));
		
		run_button = new JToggleButton("²¥·Å");
		run_button.setFocusable(false);
		run_button.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					PreviewPanel.getInstance().run();
				}
				
				if(e.getStateChange() == ItemEvent.DESELECTED) {
					PreviewPanel.getInstance().stop();
				}
			}
		});
		run_button.setPreferredSize(new Dimension());
		
		SetWeaponData(WeaponPanel.getSharedWeaponData());
	}
	
	public void SetWeaponData(WeaponData data) {
		removeAll();
		ButtonGroup group = new ButtonGroup();
		//System.out.println(data == null);
		if(data == null) {
			setPreferredSize(new Dimension(0, 48));
			run_button.setEnabled(false);
			add(run_button);
			group.add(run_button);
			return;
		}
		for(int i = 0; i < 5; i++) {
			DoubleIcon icon = new DoubleIcon(data.getWeaponAnimateData().getFrames()[i]);
			JToggleButton button = new FrameSelectButton(icon, i);
			button.setFocusable(false);
			add(button);
			group.add(button);
		}
		run_button.setEnabled(true);
		add(run_button);
		group.add(run_button);
		setPreferredSize(null);
	}
}
