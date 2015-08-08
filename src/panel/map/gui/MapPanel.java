package panel.map.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import panel.CommonData;
import panel.WrapLayout;

@SuppressWarnings("serial")
public class MapPanel extends JPanel {
	private static MapPanel instance = null;
	public static MapPanel getInstance() {
		if(instance == null) {
			instance = new MapPanel();
		}
		return instance;
	}
	
	
	private MapPanel() {
		JPanel scroll_content = new JPanel();
		scroll_content.setLayout(new WrapLayout(FlowLayout.LEFT, 8, 16));
		
		File root = new File(CommonData.CURRENT_PATH + "/res/map");
		File[] files = root.listFiles();
		for (File file : files) {
			if(file.getName().toLowerCase().endsWith(".png")) {
				//System.out.println(file.getName());
				BufferedImage image;
				try {
					image = ImageIO.read(file);
					scroll_content.add(new JButton(new ImageIcon(image)));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		setLayout(new BorderLayout());
		JScrollPane scroll = new JScrollPane(scroll_content);
		scroll.setPreferredSize(new Dimension(400, 300));
		scroll.getVerticalScrollBar().setUnitIncrement(16);
		add(scroll, BorderLayout.CENTER);
	}
}
