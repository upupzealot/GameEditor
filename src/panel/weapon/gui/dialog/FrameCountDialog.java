package panel.weapon.gui.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import panel.QuickButton;
import panel.weapon.core.AnimateData;
import panel.weapon.core.WeaponData;
import panel.weapon.gui.WeaponPanel;
import frame.gui.window.MainFrame;
import frame.gui.window.StandaloneFrame;;

@SuppressWarnings("serial")
public class FrameCountDialog extends JDialog{
	private int frame_count = 1;
	public int getFrameCount() {
		return frame_count;
	}
	
	public FrameCountDialog(final File WeaponFile) throws IOException {
		super(MainFrame.getInstance(), "帧数设置", true);
		
		JPanel preview_panel = new JPanel();
		preview_panel.setLayout(new GridLayout(3, 1, 8, 8));
		
		BufferedImage weapon_image = ImageIO.read(WeaponFile);
		
		final SplitPreviewPanel back_fx = new SplitPreviewPanel("后特效", null, frame_count);
		preview_panel.add(back_fx);
		final SplitPreviewPanel weapon = new SplitPreviewPanel("武器", weapon_image, frame_count);
		preview_panel.add(weapon);
		final SplitPreviewPanel front_fx = new SplitPreviewPanel("前特效", null, frame_count);
		preview_panel.add(front_fx);
		
		setLayout(new BorderLayout());
		add(preview_panel, BorderLayout.CENTER);
		
		JPanel choice_panel = new JPanel();
		choice_panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(4, 4, 4, 4);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 1;
		
		choice_panel.add(new JLabel("  帧数"), gbc);
		
		gbc.gridx++;
		gbc.weightx = 1;
		final JComboBox<Integer> SplitBox = new JComboBox<Integer>();
		SplitBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				frame_count = (Integer)SplitBox.getSelectedItem();
				back_fx.setFrameCount(frame_count);
				weapon.setFrameCount(frame_count);
				front_fx.setFrameCount(frame_count);
			}
		});
		int index = -1;
		int selected_index = -1;
		float ratio = Float.MAX_VALUE;
		for(int i = 1; i < weapon_image.getWidth() && i < 20; i++) {
			if(weapon_image.getWidth() % i == 0) {
				SplitBox.addItem(i);
				index++;
				float r = weapon_image.getWidth() / (float)i / weapon_image.getHeight();
				if(r < 1) {
					r = 1 / r;
				}
				if(Math.abs(r - 1) < Math.abs(ratio - 1)) {
					ratio = r;
					selected_index = index;
				}
			}
		}
		SplitBox.setSelectedIndex(selected_index);
		choice_panel.add(SplitBox, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 2;
		choice_panel.add(new QuickButton("确定") {
			@Override
			public void OnClick() {
				try {
					WeaponPanel.setSharedWeaponData(new WeaponData(new AnimateData(WeaponFile, getFrameCount()), null, null));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dispose();
			}
		}, gbc);
		
		choice_panel.setPreferredSize(new Dimension(0, 64 + 15));
		add(choice_panel, BorderLayout.SOUTH);
		
		pack();
		MakeCenter();
	}
	
	public void MakeCenter() {
		Window main_frame = getOwner();
		if(MainFrame.isIniting()) {
			StandaloneFrame.MakeCenter(this);
		} else {
			setLocation(
				main_frame.getX() + main_frame.getWidth() / 2 - getWidth() / 2,
				main_frame.getY() + main_frame.getHeight() / 2 - getHeight() / 2
			);
		}
	}
}
