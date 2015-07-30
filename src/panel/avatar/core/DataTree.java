package panel.avatar.core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import psd.model.Layer;
import psd.model.Psd;

@SuppressWarnings("serial")
public class DataTree extends HashMap<String, DataBranch>{
	public List<String> LayerNames = new ArrayList<String>();
	
	public DataTree(File file) throws IOException {
		LayerNames.add("body");
		LayerNames.add("clothes");
		LayerNames.add("face gear");
		LayerNames.add("hair");
		
		for (String LayerName : LayerNames) {
			put(LayerName, new DataBranch(LayerName));
		}
		
		ReadFile(file);
	}
	
	public void ReadFile(File file) throws IOException {
		if(file.isFile()) {
			ReadPSD(file);
		} else {
			ReadFolder(file);
		}
	}
	
	public void ReadPSD(File psd_file) throws IOException {
		Psd psd = new Psd(psd_file);
		print("Width:" + psd.getWidth());
		print("Height:" + psd.getWidth());
		print("LayersCount:" + psd.getLayersCount());
		
		for(int i = 0; i < psd.getLayersCount(); i++) {
			Layer layer_group = psd.getLayer(i);
			String group_name = layer_group.toString();
			
			if(LayerNames.contains(group_name)) {
				print(group_name + "(" + layer_group.getLayersCount() + ")");
				Set<String> layer_names = new HashSet<String>();
				for(int j = 0; j < layer_group.getLayersCount(); j++) {
					Layer layer = layer_group.getLayer(j);
					String layer_name = layer.toString();
					if(layer_names.contains(layer_name)) {
						String ln = layer_name;
						int n = 1;
						while(layer_names.contains(ln)) {
							ln = layer_name + "(" + n + ")";
							n++;
						}
						layer_name = ln;
					}
					layer_names.add(layer_name);
					print("--" + layer_name);
					
					BufferedImage buffer = new BufferedImage(psd.getWidth(), psd.getHeight(), BufferedImage.TYPE_INT_ARGB);
					buffer.createGraphics().drawImage(layer.getImage(), layer.getX(), layer.getY(), null);
					get(group_name).add(new DataLeaf(layer_name, buffer));
				}
			}
		}
	}
	
	public void ReadFolder(File root) throws IOException {
		for (String group_name : LayerNames) {
			File folder = new File(root.getPath() + "/" + group_name);
			if(folder.exists()) {
				print(group_name);
				for (File file : folder.listFiles()) {
					if(file.isFile() && file.getPath().toLowerCase().endsWith(".png")) {
						String name = file.getName().substring(0, file.getName().length() - 4);
						print("--" + name);
						get(group_name).add(new DataLeaf(name, ImageIO.read(file)));
					}
				}
			}
		}
	}
	
	private static void print(Object o) {
		//System.out.println(o);
	}
}
