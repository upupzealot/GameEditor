package panel.avatar.core;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class DataBranch extends ArrayList<DataLeaf>{
	private String name;
	public DataBranch(String name) {
		this.name = name;
	}
	
	public DataLeaf getLeaf(String leaf_name) {
		for (DataLeaf leaf : this) {
			if(leaf.getName().equals(leaf_name)) {
				return leaf;
			}
		}
		return null;
	}
	
	public BufferedImage getLeafImage(String leaf_name) {
		for (DataLeaf leaf : this) {
			if(leaf.getName().equals(leaf_name)) {
				return leaf.getImage();
			}
		}
		return null;
	}
	
	public String getName() {
		return name;
	}
}
