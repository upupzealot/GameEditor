package panel.avatar.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import panel.avatar.core.DataBranch;

@SuppressWarnings("serial")
public class ComponentPanel extends JPanel{
	private ButtonGroup button_group = new ButtonGroup();
	
	private FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
	public ComponentPanel(){
		setLayout(layout);
		//setPreferredSize(new Dimension(400, getPreferredSize().height));
	}
	
	public void setImages(DataBranch branch) {
		button_group = new ButtonGroup();
		removeAll();
		
		for(int i = branch.size() - 1; i >= 0; i--) {
			JToggleButton button = new ComponentButton(branch, branch.get(i), this);
			
			if(branch.get(i).getName().equals(selected_leaf_name)) {
				button.setSelected(true);
			}
			add(button);
			button_group.add(button);
		}
		repaint();
	}
	
	private String selected_leaf_name = null;
	public void OnSelect(String leaf_name) {
		selected_leaf_name = leaf_name;
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(0 , super.getPreferredSize().height);
	}
}
