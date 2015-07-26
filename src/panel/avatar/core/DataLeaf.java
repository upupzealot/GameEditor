package panel.avatar.core;

import java.awt.image.BufferedImage;

public class DataLeaf {
	private BufferedImage image;
	private String name;
	public DataLeaf(String name, BufferedImage image) {
		this.image = image;
		this.name = name;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public int getWidth() {
		return image.getWidth();
	}
	
	public int getHeight() {
		return image.getHeight();
	}
	
	public String getName() {
		return name;
	}
}
