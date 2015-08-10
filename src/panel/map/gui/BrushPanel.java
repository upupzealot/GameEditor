package panel.map.gui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import panel.CommonData;

@SuppressWarnings("serial")
public class BrushPanel extends JPanel{
	public BrushPanel() {
		setPreferredSize(new Dimension(CommonData.GRID_SIZE * 8 + 9, CommonData.GRID_SIZE * 8 + 9));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
	}
}
