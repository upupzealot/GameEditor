package panel.avatar.gui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.Timer;

import panel.CommonData;
import panel.avatar.core.DataCore;

@SuppressWarnings("serial")
public class PreviewPanel extends JPanel implements ActionListener{
	private static PreviewPanel instance = null;
	public static PreviewPanel getInstance() {
		if(instance == null) {
			instance = new PreviewPanel();
		}
		return instance;
	}
	
	private PreviewPanel() {
		Timer animationTimer = new Timer(10, this);
		animationTimer.setCoalesce(false);
		animationTimer.start();
		animate_timemark = System.nanoTime();
	}
	
	public void updatePreferredSize() {
		DataCore data = AvartarPanel.getSharedDataCore();
		int scale_width = data.getFrameWidth() * CommonData.SCALE_FACTOR;
		int scale_height = data.getFrameHeight() * CommonData.SCALE_FACTOR;
		Dimension size =  new Dimension(scale_width * 4 + CommonData.GAP * 5, scale_height + CommonData.GAP * 2);
		setSize(size);
		//System.out.println(size);
		setPreferredSize(size);
		setMinimumSize(size);
	}
	
	private int frame_count = 0;
	private long animate_count = 0;
	private long animate_timemark;
	private long animate_interval = 1000000000 / 8;
	@Override
	public void actionPerformed(ActionEvent e) {
		long currenttime = System.nanoTime();
		animate_count += currenttime - animate_timemark;
		if(animate_count >= animate_interval) {
			animate_count -= animate_interval;
			frame_count = (frame_count + 1) % 4;
			repaint();
		}
		animate_timemark = currenttime;
	}
	
	private BufferedImage buffer = null;
	public BufferedImage getBuffer() {
		DataCore data = AvartarPanel.getSharedDataCore();
		if(buffer == null || buffer.getWidth() != data.getWidth() || buffer.getHeight() != data.getHeight()) {
			buffer = new BufferedImage(data.getWidth(), data.getHeight(), BufferedImage.TYPE_INT_ARGB);
		}
		return buffer;
	}
	
	private Map<String, String> selected_leaf_name = new HashMap<String, String>();
	public void setComponentImage(String component_name, String leaf_name) {
		selected_leaf_name.put(component_name, leaf_name);
		mergeLayers();
	}
	
	private void mergeLayers() {
		Graphics2D g2d = getBuffer().createGraphics();
		g2d.setComposite(AlphaComposite.Clear);
		g2d.fillRect(0, 0, getBuffer().getWidth(), getBuffer().getHeight());
		g2d.setComposite(AlphaComposite.SrcOver);
		
		DataCore data = AvartarPanel.getSharedDataCore();
		BufferedImage img = data.getBranchLeaf("body", selected_leaf_name.get("body"));
		if(img != null) {
			g2d.drawImage(img, 0, 0, null);
		}
		img = data.getBranchLeaf("clothes", selected_leaf_name.get("clothes"));
		if(img != null) {
			g2d.drawImage(img, 0, 0, null);
		}
		img = data.getBranchLeaf("hair", selected_leaf_name.get("hair"));
		if(img != null) {
			g2d.drawImage(img, 0, 0, null);
		}
		img = data.getBranchLeaf("face gear", selected_leaf_name.get("face gear"));
		if(img != null) {
			g2d.drawImage(img, 0, 0, null);
		}
	}
	
	private Color BG_Color = Color.WHITE;
	@Override
	public void paintComponent(Graphics g) {
		DataCore data = AvartarPanel.getSharedDataCore();
		int scale_width = data.getFrameWidth() * CommonData.SCALE_FACTOR;
		int scale_height = data.getFrameHeight() * CommonData.SCALE_FACTOR;
		int gap = CommonData.GAP;
		
		g.fillRect(0, 0, getWidth(), getHeight());

		g.translate(gap, gap);
		for(int i = 0; i < 4; i++) {
			g.setColor(BG_Color);
			g.fillRect(0, 0, scale_width, scale_height);
			g.translate(scale_width + gap, 0);
		}
		g.translate((scale_width + gap) * -4, 0);
		
		int x = data.getFrameWidth() * frame_count;
		int y = data.getFrameHeight() * 0;
		g.drawImage(getBuffer(), 
				0, 0, scale_width, scale_height, 
				x, y, x + data.getFrameWidth(), y + data.getFrameHeight(),
				null);
		g.translate(scale_width + gap, 0);
		y = data.getFrameHeight() * 1;
		g.drawImage(getBuffer(), 
				0, 0, scale_width, scale_height, 
				x, y, x + data.getFrameWidth(), y + data.getFrameHeight(),
				null);
		g.translate(scale_width + gap, 0);
		y = data.getFrameHeight() * 2;
		g.drawImage(getBuffer(), 
				0, 0, scale_width, scale_height, 
				x, y, x + data.getFrameWidth(), y + data.getFrameHeight(),
				null);
		g.translate(scale_width + gap, 0);
		y = data.getFrameHeight() * 3;
		g.drawImage(getBuffer(), 
				0, 0, scale_width, scale_height, 
				x, y, x + data.getFrameWidth(), y + data.getFrameHeight(),
				null);
		
		super.paintChildren(g);
	}
}
