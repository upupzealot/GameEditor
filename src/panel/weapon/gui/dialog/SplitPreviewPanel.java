package panel.weapon.gui.dialog;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import panel.QuickButton;
import frame.core.io.PngOpener;

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
	
	private File working_file;
	private BufferedImage image = null;
	private int frame_count;
	public SplitPreviewPanel(String name, File working_file, int count, boolean can_modify) throws IOException {
		setBorder(new TitledBorder(name));
		
		this.working_file = working_file;
		if(working_file!= null && working_file.exists()) {
			image = ImageIO.read(working_file);
		} else {
			//image = null;
		}
		this.frame_count = count;
		
		setLayout(new BorderLayout(8, 8));
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
		add(content_panel, BorderLayout.CENTER);
		
		if(can_modify) {
			QuickButton modify_button = new QuickButton(null) {
				@Override
				public void OnClick() {
					File png_file = PngOpener.OpenPng();
					if(png_file != null) {
						try {
							setImage(ImageIO.read(png_file));
							SplitPreviewPanel.this.working_file = png_file;
							setText("ÐÞ¸Ä");
							SplitPreviewPanel.this.repaint();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			};
			if(image != null) {
				modify_button.setText("ÐÞ¸Ä");
			} else {
				modify_button.setText("Ìí¼Ó");
			}
			
			//modify_button.setEnabled(image != null);
			add(modify_button, BorderLayout.EAST);
		}
	}
	
	public File getWorkingFile() {
		return working_file;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public void setFrameCount(int frame_count) {
		this.frame_count = frame_count;
		repaint();
	}
}
