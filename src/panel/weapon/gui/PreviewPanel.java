package panel.weapon.gui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventPostProcessor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.Timer;

import panel.CommonData;
import panel.weapon.core.AnimateData;
import panel.weapon.core.WeaponData;

@SuppressWarnings("serial")
public class PreviewPanel extends JPanel implements ActionListener {
	private static PreviewPanel instance = null;
	public static PreviewPanel getInstance() {
		if(instance == null) {
			instance = new PreviewPanel();
		}
		return instance;
	}
	
	private BufferedImage body;
	private BufferedImage note;
	public static final int BODY_ANCHOR_X = 7;
	public static final int BODY_ANCHOR_Y = 16;
	private LayerButton[] layer_buttons;
	private LayerButton back_fx_button;
	private LayerButton weapon_button;
	private LayerButton front_fx_button;
	private int selected_layer_index;
	private PreviewPanel() {
		try {
			body = ImageIO.read(new File(CommonData.CURRENT_PATH + "/res/body.png"));
			note = ImageIO.read(getClass().getResourceAsStream("/res/use_wasd_or_uldr.png"));
			setPreferredSize(new Dimension(body.getWidth() * 3 * 2,body.getHeight() * 3 * 2));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Timer animationTimer = new Timer(10, this);
		animationTimer.setCoalesce(false);
		animationTimer.start();
		animate_timemark = System.nanoTime();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		back_fx_button = new LayerButton("后特效") {
			@Override
			public void OnSelect() {
				selected_layer_index = 0;
				PreviewPanel.this.repaint();
			}
		};
		weapon_button = new LayerButton("武器") {
			@Override
			public void OnSelect() {
				selected_layer_index = 1;
				PreviewPanel.this.repaint();
			}
		};
		front_fx_button = new LayerButton("前特效") {
			@Override
			public void OnSelect() {
				selected_layer_index = 2;
				PreviewPanel.this.repaint();
			}
		};
		
		layer_buttons = new LayerButton[3];
		ButtonGroup group = new ButtonGroup();
		layer_buttons[0] = back_fx_button;
		group.add(back_fx_button);
		layer_buttons[1] = weapon_button;
		selected_layer_index = 1;
		group.add(weapon_button);
		layer_buttons[2] = front_fx_button;
		group.add(front_fx_button);
		
		add(Box.createRigidArea(new Dimension(0, 5)));
		add(back_fx_button);
		add(Box.createRigidArea(new Dimension(0, 5)));
		add(weapon_button);
		add(Box.createRigidArea(new Dimension(0, 5)));
		add(front_fx_button);
		
		weapon_button.setSelected(true);
	}
	
	public void SetWeaponData(WeaponData data) {
		back_fx_button.setEnabled(data != null && data.getBackFxAnimateData() != null);
		front_fx_button.setEnabled(data != null && data.getFrontFxAnimateData() != null);
	}
	
	private boolean running = false;
	private int frame_count = 0;
	private long animate_count = 0;
	private long animate_timemark;
	private long animate_interval = 1000000000 / 8;
	@Override
	public void actionPerformed(ActionEvent e) {
		//requestFocus();
		if(!running) {
			return;
		}
		long currenttime = System.nanoTime();
		animate_count += currenttime - animate_timemark;
		if(animate_count >= animate_interval) {
			animate_count -= animate_interval;
			frame_count = (frame_count + 1) % 4;
			repaint();
		}
		animate_timemark = currenttime;
	}
	
	public void setFrameCount(int i) {
		frame_count = i;
		repaint();
	}
	
	public void run() {
		running = true;
		animate_timemark = System.nanoTime();
	}
	
	public void stop() {
		running = false;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform at0 = g2d.getTransform();
		g2d.setColor(getBackground());
		g2d.fillRect(0, 0, getWidth(), getHeight());
		
		WeaponData data = WeaponPanel.getSharedWeaponData();
		
		if(!running && data != null) {
			g2d.drawImage(note, 
				getWidth() - note.getWidth() * 2 - 5, 
				getHeight() - note.getHeight() * 2 - 5, 
				note.getWidth() * 2,
				note.getHeight() * 2,
				null);
		}
		
		g2d.translate((getWidth() - getPreferredSize().width) / 2, (getHeight() - getPreferredSize().height) / 2);
		g2d.scale(2, 2);
		g2d.translate(body.getWidth(), body.getHeight());
		
		g2d.drawImage(body, 0, 0, null);
		
		if(data != null) {
			AnimateData back_fx_date = data.getBackFxAnimateData();
			if(back_fx_date != null && (running || selected_layer_index == 0)) {
				g2d.drawImage(
				back_fx_date.getFrames()[frame_count], 
				BODY_ANCHOR_X - back_fx_date.getXOffset()[frame_count], BODY_ANCHOR_Y - back_fx_date.getYOffset()[frame_count], 
				null);
			}
			
			
			
			AnimateData weapon_date = data.getWeaponAnimateData();
			if(selected_layer_index == 0 && !running) {
				g2d.setComposite(AlphaComposite.SrcOver.derive(0.5f));
			}
			g2d.drawImage(
			weapon_date.getFrames()[frame_count], 
			BODY_ANCHOR_X - weapon_date.getXOffset()[frame_count], BODY_ANCHOR_Y - weapon_date.getYOffset()[frame_count], 
			null);
			if(selected_layer_index == 0 && !running) {
				g2d.setComposite(AlphaComposite.SrcOver);
			}
			
			AnimateData font_fx_date = data.getFrontFxAnimateData();
			if(font_fx_date != null && (running || selected_layer_index == 2)) {
				g2d.drawImage(
				font_fx_date.getFrames()[frame_count], 
				BODY_ANCHOR_X - font_fx_date.getXOffset()[frame_count], BODY_ANCHOR_Y - font_fx_date.getYOffset()[frame_count], 
				null);
			}
		}
		
		g2d.setColor(Color.RED);
		g2d.fillRect(BODY_ANCHOR_X, BODY_ANCHOR_Y, 1, 1);
		
		g2d.setTransform(at0);
	}
	
	private KeyEventProcessor processor = new KeyEventProcessor();
	public KeyEventPostProcessor getKeyEventPostProcessor () {
		return processor;
	}
	private class KeyEventProcessor implements KeyEventPostProcessor {
		@Override
		public boolean postProcessKeyEvent(KeyEvent e) {
			AnimateData data = null;
			switch (selected_layer_index) {
			case 0:
				data = WeaponPanel.getSharedWeaponData().getBackFxAnimateData();
				break;
			case 1:
				data = WeaponPanel.getSharedWeaponData().getWeaponAnimateData();
				break;
			case 2:
				data = WeaponPanel.getSharedWeaponData().getFrontFxAnimateData();
				break;

			default:
				break;
			}
			
			if(!running && data!= null && e.getID() == KeyEvent.KEY_PRESSED) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_A:
					data.getXOffset()[frame_count]++;
					repaint();
					return true;
					
				case KeyEvent.VK_RIGHT:
				case KeyEvent.VK_D:
					data.getXOffset()[frame_count]--;
					repaint();
					return true;
					
				case KeyEvent.VK_UP:
				case KeyEvent.VK_W:
					data.getYOffset()[frame_count]++;
					repaint();
					return true;
					
				case KeyEvent.VK_DOWN:
				case KeyEvent.VK_S:
					data.getYOffset()[frame_count]--;
					repaint();
					return true;
					
				case KeyEvent.VK_TAB:
					do {
						selected_layer_index = ++selected_layer_index % layer_buttons.length;
					} while (!layer_buttons[selected_layer_index].isEnabled());
					
					layer_buttons[selected_layer_index].setSelected(true);
				default:
					return false;
				}
			}
			return false;
		}
	}
}
