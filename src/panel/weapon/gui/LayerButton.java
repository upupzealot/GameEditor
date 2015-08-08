package panel.weapon.gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JToggleButton;

@SuppressWarnings("serial")
public abstract class LayerButton extends JToggleButton{
	public LayerButton(String name) {
		super(name);
		setFocusable(false);
		
		addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					OnSelect();
				}
			}
		});
	}
	
	public abstract void OnSelect();
}
