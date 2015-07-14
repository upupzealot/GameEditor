package panel.avatar;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;

import javax.swing.JToggleButton;

@SuppressWarnings("serial")
public class ComponentButton extends JToggleButton{
	private BufferedImage image;
	private String name;
	public ComponentButton(String name, BufferedImage image) {
		super(new PreviewIcon(image.getSubimage(0, 0, image.getWidth() / 4, image.getHeight() / 4)));
		this.name = name;
		this.image = image;
		init();
	}
	
	private void init() {
		addItemListener(new ItemListener() {
					
			@Override
			public void itemStateChanged(ItemEvent event) {
				if(event.getStateChange() == ItemEvent.SELECTED) {
					//System.out.print("Change");
					setImage(image);
				}
			}
		});
	}
	
	public void setImage(BufferedImage image) {
		PreviewPanel.getInstance().setImage(name, image);
	}
}
