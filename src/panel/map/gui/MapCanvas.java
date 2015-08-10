package panel.map.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import panel.CommonData;

@SuppressWarnings("serial")
public class MapCanvas extends JPanel{
	
	private int grid_width;
	private int grid_height;
	private int grid_size = CommonData.GRID_SIZE;
	
	private int width;
	private int height;
	
	public MapCanvas(int grid_width, int grid_height) {
		this.grid_width = grid_width;
		this.grid_height = grid_height;
		
		width = grid_width * grid_size;
		height = grid_height * grid_size;
		
		setPreferredSize(new Dimension(width * 2 + 16, height * 2 + 16));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.translate((getWidth() - width * 2) / 2, (getHeight() - height * 2) / 2);
		g2d.scale(2, 2);
		
		g2d.drawRect(0, 0, width, height);
		
		for(int i = 1; i < grid_width; i++) {
			g2d.drawLine(i * grid_size, 0, i * grid_size, height);
		}
		for(int j = 1; j < grid_height; j++) {
			g2d.drawLine(0, j * grid_size, width, j * grid_size);
		}
	}
}
