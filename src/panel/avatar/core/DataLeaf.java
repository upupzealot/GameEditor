package panel.avatar.core;

import java.awt.image.BufferedImage;
import java.io.File;

public class DataLeaf {
	private BufferedImage image;
	private String name;
	private File file;
	public DataLeaf(String name, BufferedImage image) {
		this.image = image;
		this.name = name;
		file = null;
	}
	
	public DataLeaf(String name, BufferedImage image, File file) {
		this.image = image;
		this.name = name;
		this.file = file;
	}
	
	public String getName() {
		return name;
	}
	
	public int getWidth() {
		return image.getWidth();
	}
	
	public int getHeight() {
		return image.getHeight();
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public File getFile() {
		return file;
	}
}
