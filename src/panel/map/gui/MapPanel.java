package panel.map.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
		setLayout(new BorderLayout());
		
		add(new CanvasPanel());
		
		JScrollPane scroll = new JScrollPane(new TileSelectPanel());
		scroll.setPreferredSize(new Dimension(400, 300));
		scroll.getVerticalScrollBar().setUnitIncrement(16);
		add(scroll, BorderLayout.SOUTH);
	}
}
