package panel.avatar.gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JToggleButton;

import panel.avatar.core.DataLeaf;

@SuppressWarnings("serial")
public class ComponentButton extends JToggleButton{
	private DataLeaf leaf;
	private String branch_name;
	private ComponentPanel parent;
	public ComponentButton(String branch_name, DataLeaf leaf, ComponentPanel parent) {
		super(new PreviewIcon(leaf.getImage().getSubimage(0, 0, leaf.getWidth() / 4, leaf.getHeight() / 4)));
		this.branch_name = branch_name;
		this.leaf = leaf;
		this.parent = parent;
		init();
	}
	
	private void init() {
		addItemListener(new ItemListener() {
					
			@Override
			public void itemStateChanged(ItemEvent event) {
				if(event.getStateChange() == ItemEvent.SELECTED) {
					OnSelect();
					parent.OnSelect(leaf.getName());
				}
			}
		});
	}
	
	public void OnSelect() {
		PreviewPanel.getInstance().setComponentImage(branch_name, leaf.getName());
	}
}
