package panel.avatar;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import panel.CommonData;

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
		
		try {
			//BufferedImage img = ImageIO.read(getClass().getResourceAsStream("/res/images/盔甲_1.png"));
			BufferedImage img = ImageIO.read(new File(CommonData.CURRENT_PATH + "/res/body/素模_1.png"));
			setImage("", img);
		}
		catch (Exception e) {
			e.printStackTrace();
			//throw(new IllegalArgumentException("无法载入图片图片\"Avatar.png\""));
		}
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
	private int frame_width = 24;
	private int frame_height = 24;
	
	public void setImage(String component_name, BufferedImage buffer) {
		if(this.buffer == null) {
			if(buffer.getWidth() % 4 != 0 || buffer.getHeight() % 4 != 0) {
				throw new RuntimeException("图片的尺寸不是4的倍数");
			} else {
				this.buffer = new BufferedImage(buffer.getWidth(), buffer.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
			}
		} else {
			if(buffer.getWidth() != this.buffer.getWidth() || buffer.getHeight() != this.buffer.getHeight()) {
				throw new RuntimeException("图片的尺寸不匹配");
			}
		}
		
		if(buffer.getWidth() % 4 != 0 || buffer.getHeight() % 4 != 0) {
			throw new RuntimeException("图片的尺寸不是4的倍数");
		} else {
			frame_width = buffer.getWidth() / 4;
			frame_height = buffer.getHeight() / 4;
			int scale_width = frame_width * CommonData.SCALE_FACTOR;
			int scale_height = frame_height * CommonData.SCALE_FACTOR;
			setPreferredSize(new Dimension(getPreferredSize().width, scale_height + CommonData.GAP * 2));
			setMinimumSize(new Dimension(scale_width * 4 + CommonData.GAP * 5, scale_height + CommonData.GAP * 2));
			
			layers.put(component_name, buffer);
			mergeLayers();
		}
	}
	
	private Map<String, BufferedImage> layers = new HashMap<String, BufferedImage>();
	private void mergeLayers() {
		Graphics2D g2d = buffer.createGraphics();
		g2d.setComposite(AlphaComposite.Clear);
		g2d.fillRect(0, 0, buffer.getWidth(), buffer.getHeight());
		g2d.setComposite(AlphaComposite.SrcOver);
		if(layers.get("body") != null) {
			g2d.drawImage(layers.get("body"), 0, 0, null);
		}
		if(layers.get("clothes") != null) {
			g2d.drawImage(layers.get("clothes"), 0, 0, null);
		}
		if(layers.get("hair") != null) {
			g2d.drawImage(layers.get("hair"), 0, 0, null);
		}
		if(layers.get("face gear") != null) {
			g2d.drawImage(layers.get("face gear"), 0, 0, null);
		}
	}
	
	private Color BG_Color = Color.WHITE;
	@Override
	public void paintComponent(Graphics g) {
		int scale_width = frame_width * CommonData.SCALE_FACTOR;
		int scale_height = frame_height * CommonData.SCALE_FACTOR;
		int gap = CommonData.GAP;
		
		//((Graphics2D)g).scale(2, 2);
		//g.translate((width - frame_width * scale_factor * 4 - gap * 3) / 2, gap);
		g.fillRect(0, 0, getWidth(), getHeight());

		g.translate(gap, gap);
		for(int i = 0; i < 4; i++) {
			g.setColor(BG_Color);
			g.fillRect(0, 0, scale_width, scale_height);
			g.translate(scale_width + gap, 0);
		}
		g.translate((scale_width + gap) * -4, 0);
		
		int x = frame_width * frame_count;
		int y = frame_height * 0;
		g.drawImage(buffer, 
				0, 0, scale_width, scale_height, 
				x, y, x + frame_width, y + frame_height,
				null);
		g.translate(scale_width + gap, 0);
		y = frame_height * 1;
		g.drawImage(buffer, 
				0, 0, scale_width, scale_height, 
				x, y, x + frame_width, y + frame_height,
				null);
		g.translate(scale_width + gap, 0);
		y = frame_height * 2;
		g.drawImage(buffer, 
				0, 0, scale_width, scale_height, 
				x, y, x + frame_width, y + frame_height,
				null);
		g.translate(scale_width + gap, 0);
		y = frame_height * 3;
		g.drawImage(buffer, 
				0, 0, scale_width, scale_height, 
				x, y, x + frame_width, y + frame_height,
				null);
	}
}
