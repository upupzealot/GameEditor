package panel.weapon.gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Icon;
import javax.swing.JToggleButton;

@SuppressWarnings("serial")
public class FrameSelectButton extends JToggleButton{
	private int ID;
	
	public FrameSelectButton(Icon icon, int ID) {
		super(icon);
		this.ID = ID;
		
		addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					PreviewPanel.getInstance().setFrameCount(FrameSelectButton.this.ID);
				}
			}
		});
	}
}
