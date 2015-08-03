package panel.avatar.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import frame.gui.window.MainFrame;
import panel.CommonData;

@SuppressWarnings("serial")
public class ZoomBox extends JComboBox<ZoomBox.ZoomItem>{
	//public static final Integer[] ZOOMS = {1, 2, 3, 4, 5, 6, 7, 8};
	public static final Integer[] ZOOMS = {1, 2, 4, 8};
	
	private static ZoomBox instance = null;
	public static ZoomBox getInstance() {
		if(instance == null) {
			instance = new ZoomBox();
		}
		return instance;
	}
	private ZoomBox() {
		for(int i = 0; i < ZOOMS.length; i++) {
			addItem(new ZoomItem(ZOOMS[i]));
		}
		setSelectedIndex(1);
		PreviewPanel.getInstance().updatePreferredSize();
		//setPreferredSize(new Dimension(67, 22));
		
		
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println(((ZoomItem)getSelectedItem()).zoom_facter);
				//System.out.println(getPreferredSize());
				CommonData.SCALE_FACTOR = ((ZoomItem)getSelectedItem()).zoom_facter;
				PreviewPanel.getInstance().updatePreferredSize();
				MainFrame.getInstance().setMinimumSize(new Dimension(0, 0));
				MainFrame.getInstance().pack();
				MainFrame.getInstance().setMinimumSize(MainFrame.getInstance().getSize());
			}
		});
	}
	
	public class ZoomItem extends JLabel{
		private int zoom_facter;
		public ZoomItem(int zoom_facter) {
			super(zoom_facter * 100 + "%");
			this.zoom_facter = zoom_facter;
		}
		
		@Override
		public String toString() {
			return getText();
		}
	}
}
