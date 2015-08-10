package panel.map.gui;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import panel.CommonData;
import panel.WrapLayout;

@SuppressWarnings("serial")
public class TileSelectPanel extends JPanel{
	public TileSelectPanel() {
		WrapLayout layout = new WrapLayout(FlowLayout.LEFT, 8, 16);
		layout.setAlignOnBaseline(true);
		setLayout(layout);
		
		File root = new File(CommonData.CURRENT_PATH + "/res/map");
		File[] files = root.listFiles();
		ButtonGroup group = new ButtonGroup();
		for (File file : files) {
			if(file.getName().toLowerCase().endsWith(".png")) {
				//System.out.println(file.getName());
				BufferedImage image;
				try {
					image = ImageIO.read(file);
					JToggleButton button = new JToggleButton(new ImageIcon(image)) {
						@Override
						public int getBaseline(int width, int height) {
							return height;
						}
					};
					group.add(button);
					add(button);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
