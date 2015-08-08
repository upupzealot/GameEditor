package panel.weapon.gui.dialog;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class SplitPreviewPanel extends JPanel{
	private static BufferedImage NOTE;
	static {
		try {
			NOTE = ImageIO.read(SplitPreviewPanel.class.getResourceAsStream("/res/no_image.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private BufferedImage image;
	private int frame_count;
	public SplitPreviewPanel(String name, BufferedImage img, int count) {
		setBorder(new TitledBorder(name));
		this.image = img;
		this.frame_count = count;
		
		setLayout(new BorderLayout());
		JPanel content_panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D)g;
				if(image == null) {
					g2d.translate(getWidth() / 2, getHeight() / 2);
					g2d.scale(2, 2);
					g2d.drawImage(NOTE, -NOTE.getWidth() / 2, -NOTE.getHeight() / 2, null);
					return;
				}
				g2d.translate(getWidth() / 2 - image.getWidth(), getHeight() / 2 - image.getHeight());
				g2d.scale(2, 2);
				
				g2d.drawImage(image, 0, 0, null);
				
				g2d.setStroke( new BasicStroke(1,BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{3, 3}, 0f));
				int frame_width = image.getWidth() / frame_count;
				for(int i = 0; i < frame_count; i++) {
					g2d.drawRect(frame_width * i, 0, frame_width, image.getHeight());
				}
			}
		};
		if(image != null) {
			content_panel.setPreferredSize(new Dimension(image.getWidth() * 2 + 2, image.getHeight() * 2 + 2));
		}
		add(content_panel , BorderLayout.CENTER);
	}
	
	public void setFrameCount(int frame_count) {
		this.frame_count = frame_count;
		repaint();
	}
}
