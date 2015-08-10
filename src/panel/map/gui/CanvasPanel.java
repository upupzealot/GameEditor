package panel.map.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CanvasPanel extends JPanel{
	public CanvasPanel() {
		setLayout(new BorderLayout());
		
		add(new BrushPanel(), BorderLayout.WEST);
		add(new MapCanvas(10, 10), BorderLayout.CENTER);
	}
}
