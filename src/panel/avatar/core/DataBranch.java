package panel.avatar.core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import frame.core.io.PngSaver;

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
	
	public void replaceLeaf(String leaf_name, DataLeaf new_leaf) {
		for (DataLeaf leaf : this) {
			if(leaf.getName().equals(leaf_name)) {
				set(indexOf(leaf), new_leaf);
				
				File file = leaf.getFile();
				if(file != null) {
					PngSaver.SaveImage(leaf.getImage(), file);
				}
			}
		}
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
