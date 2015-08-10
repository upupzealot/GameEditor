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
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import panel.QuickButton;
import panel.weapon.core.AnimateData;
import panel.weapon.core.WeaponData;
import frame.gui.window.MainFrame;
import frame.gui.window.StandaloneFrame;

@SuppressWarnings("serial")
public class WeaponDataImportDialog extends JDialog{
	private int frame_count = 1;
	
	private BufferedImage back_fx_image = null;
	private BufferedImage weapon_image;
	private BufferedImage front_fx_image = null;
	
	public WeaponDataImportDialog(final File weapon_file) throws IOException {
		super(MainFrame.getInstance(), "帧数设置", true);
		
		String file_name_path = weapon_file.getName();
		file_name_path = weapon_file.getParent() + "/" + file_name_path.substring(0, file_name_path.length() - 4);
		
		JPanel preview_panel = new JPanel();
		preview_panel.setLayout(new GridLayout(3, 1, 8, 8));
		
		final SplitPreviewPanel back_fx;
		File back_fx_file = new File(file_name_path + ".fx.back.png");
		if(back_fx_file != null && back_fx_file.exists()) {
			back_fx_image = ImageIO.read(back_fx_file);
			back_fx = new SplitPreviewPanel("后特效", back_fx_image, frame_count);
		} else {
			back_fx = new SplitPreviewPanel("后特效", null, frame_count);
		}
		preview_panel.add(back_fx);
		
		weapon_image = ImageIO.read(weapon_file);
		final SplitPreviewPanel weapon = new SplitPreviewPanel("武器", weapon_image, frame_count);
		preview_panel.add(weapon);
		
		final SplitPreviewPanel front_fx;
		File front_fx_file = new File(file_name_path + ".fx.front.png");
		if(front_fx_file != null && front_fx_file.exists()) {
			front_fx_image = ImageIO.read(front_fx_file);
			front_fx = new SplitPreviewPanel("前特效", front_fx_image, frame_count);
		} else {
			front_fx = new SplitPreviewPanel("前特效", null, frame_count);
		}
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
				//try {
				//	WeaponPanel.setSharedWeaponData(new WeaponData(new AnimateData(WeaponFile, getFrameCount()), null, null));
				//} catch (IOException e) {
					// TODO Auto-generated catch block
				//	e.printStackTrace();
				//}
				action_id = JFileChooser.APPROVE_OPTION;
				dispose();
			}
		}, gbc);
		
		choice_panel.setPreferredSize(new Dimension(0, 64 + 15));
		add(choice_panel, BorderLayout.SOUTH);
		
		pack();
		MakeCenter();
	}
	
	private int action_id;
	public int showImportDialog() {
		action_id = JFileChooser.CANCEL_OPTION;
		setVisible(true);
		while(isVisible()) {
			//阻塞线程
		}
		return action_id;
	}
	
	public WeaponData getWeaponData() {
		try {
			AnimateData weapon_data = new AnimateData(weapon_image, frame_count);
			
			AnimateData back_fx_data = null;
			if(back_fx_image != null) {
				back_fx_data = new AnimateData(back_fx_image, frame_count);
			}
			
			AnimateData front_fx_data = null;
			if(front_fx_image != null) {
				front_fx_data = new AnimateData(front_fx_image, frame_count);
			}
			
			return new WeaponData(weapon_data, back_fx_data, front_fx_data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
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
