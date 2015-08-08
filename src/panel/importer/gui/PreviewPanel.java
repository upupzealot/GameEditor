package panel.importer.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import panel.CommonData;
import frame.core.io.PngSaver;
import frame.gui.menu.MenuItem;

@SuppressWarnings("serial")
public class PreviewPanel extends JPanel{
	
	private BufferedImage image;
	
	public PreviewPanel() {
		try {
			image = ImageIO.read(new File(CommonData.CURRENT_PATH + "/res/roguelike_sheet.png/"));
			setPreferredSize(new Dimension(image.getWidth() + 2, image.getHeight() + 2));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					dragging = false;
					x1 = Math.max(0, Math.min(getWidth() - 2, e.getX() - 1)) / 17;
					y1 = Math.max(0, Math.min(getHeight() - 2, e.getY() - 1)) / 17;
					repaint();
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					dragging = true;
					x0 = Math.max(0, e.getX() - 1) / 17;
					y0 = Math.max(0, e.getY() - 1) / 17;
					x1 = x0;
					y1 = y0;
					repaint();
				}
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
					final int x = Math.min(x0, x1) * 17;
					final int y = Math.min(y0, y1) * 17;
					final int w = Math.abs(x0 - x1) * 17 + 16;
					final int h = Math.abs(y0 - y1) * 17 + 16;
					
					if(x < e.getX() && e.getX() < x + w && y < e.getY() && e.getY() < y + h) {
						JPopupMenu menu = new JPopupMenu();
						menu.add(new MenuItem("Ñ¡ÇøÁí´æ", "E") {
							@Override
							public void actionPerformed() {
								PngSaver.SaveImage(image.getSubimage(x, y, w, h));
							}
						});
						menu.show(PreviewPanel.this, e.getX(), e.getY());
					}
				}
			}
		});
		addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				if(dragging) {
					x1 = Math.max(0, Math.min(getWidth() - 2, e.getX() - 1)) / 17;
					y1 = Math.max(0, Math.min(getHeight() - 2, e.getY() - 1)) / 17;
					repaint();
				}
			}
		});
	}
	
	boolean dragging = false;
	int x0 = 0, y0 = 0;
	int x1 = 0, y1 = 0;
	@Override
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		g.drawImage(image, 1, 1, null);
		
		int x = Math.min(x0, x1) * 17;
		int y = Math.min(y0, y1) * 17;
		int w = Math.abs(x0 - x1) * 17 + 17;
		int h = Math.abs(y0 - y1) * 17 + 17;
		g.drawRect(x, y, w, h);
		g.drawRect(x + 1, y + 1, w - 2, h - 2);
	}
}
