package panel.importer.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImportPanel extends JPanel{
	public ImportPanel() {
		setLayout(new BorderLayout());
		add(new PreviewPanel(), BorderLayout.CENTER);
	}
}
