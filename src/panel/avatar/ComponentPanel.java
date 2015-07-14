package panel.avatar;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

@SuppressWarnings("serial")
public class ComponentPanel extends JPanel{
	private ButtonGroup button_group = new ButtonGroup();
	public ComponentPanel(String name){
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		String path = System.getProperty("user.dir") + "/res/" + name + "/";
		String[] files = new File(path).list();
		BufferedImage[] images = new BufferedImage[files.length];
		for (int i = 0; i < files.length; i++) {
			try {
				System.out.println(files[i]);
				images[i] = ImageIO.read(new File(path + files[i]));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(images[i] != null) {
				JToggleButton button = new ComponentButton(name, images[i]);
				add(button);
				button_group.add(button);
			}
		}
	}
}
