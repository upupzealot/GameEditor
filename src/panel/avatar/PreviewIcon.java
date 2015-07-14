package panel.avatar;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.Icon;

import panel.CommonData;

public class PreviewIcon implements Icon{
	private BufferedImage image;
	public PreviewIcon(BufferedImage image) {
		this.image = image;
	}
	@Override
	public int getIconHeight() {
		return image.getHeight() * CommonData.SCALE_FACTOR;
	}

	@Override
	public int getIconWidth() {
		// TODO Auto-generated method stub
		return image.getWidth() * CommonData.SCALE_FACTOR;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		g.drawImage(image, x, y, getIconWidth(), getIconHeight(), null);
	}

}
