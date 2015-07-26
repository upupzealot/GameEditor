package panel.avatar.core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

@SuppressWarnings("serial")
public class DataCore extends DataTree{
	public DataCore(File file) throws IOException {
		super(file);
		
		CheckFormat();
	}
	
	private int width = -1;
	public int getWidth() {
		return width;
	}
	
	private int height = -1;
	public int getHeight() {
		return height;
	}
	
	private int frame_width = -1;
	public int getFrameWidth() {
		return frame_width;
	}
	
	private int frame_height = -1;
	public int getFrameHeight() {
		return frame_height;
	}
	
	private void CheckFormat() throws IOException {
		DataLeaf leaf0 = null;
		for(Iterator<DataBranch> iterator = values().iterator(); iterator.hasNext();) {
			DataBranch branch = iterator.next();
			for (DataLeaf leaf : branch) {
				if(leaf0 == null) {
					leaf0 = leaf;
					if(leaf.getWidth() % 4 != 0 || leaf.getHeight() % 4 != 0) {
						throw new IOException("Í¼ÏñµÄ³ß´ç²»ÊÇ4µÄ±¶Êý");
					} else {
						width = leaf.getWidth();
						height = leaf.getHeight();
						frame_width = leaf.getWidth() / 4;
						frame_height = leaf.getHeight() / 4;
					}
					continue;
				}
				if(leaf.getWidth() != leaf0.getWidth() || leaf.getHeight() != leaf0.getHeight()) {
					throw new IOException("Í¼Ïñ " + leaf.getName() + " µÄ³ß´ç²»Æ¥Åä");
				}
			}
		}
	}
	
	public BufferedImage getBranchLeaf(String branch_name, String leaf_name) {
		return get(branch_name).getLeafImage(leaf_name);
	}
}
