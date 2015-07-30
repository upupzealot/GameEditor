package panel.avatar.gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPopupMenu;
import javax.swing.JToggleButton;

import frame.core.io.PngSaver;
import frame.gui.menu.MenuItem;
import panel.avatar.core.DataBranch;
import panel.avatar.core.DataLeaf;

@SuppressWarnings("serial")
public class ComponentButton extends JToggleButton{
	private DataLeaf leaf;
	private DataBranch branch;
	private ComponentPanel parent;
	public ComponentButton(DataBranch branch, DataLeaf leaf, ComponentPanel parent) {
		super(new PreviewIcon(leaf.getImage().getSubimage(0, 0, leaf.getWidth() / 4, leaf.getHeight() / 4)));
		this.branch = branch;
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
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON3) {
					OnRightClick();
				}
			}
		});
	}
	
	public void OnSelect() {
		PreviewPanel.getInstance().setComponentImage(branch.getName(), leaf.getName());
	}
	
	public void OnRightClick() {
		JPopupMenu popupmenu = new JPopupMenu();
		popupmenu.add(new MenuItem("µ¼³ö", "E") {
			@Override
			public void actionPerformed() {
				PngSaver.SaveImage(leaf.getImage());
			}
		});
		popupmenu.show(this, getWidth() / 2, getHeight() / 2);
	}
}
