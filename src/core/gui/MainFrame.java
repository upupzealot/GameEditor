package core.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.skin.DustSkin;
import org.pushingpixels.substance.api.skin.SubstanceDustLookAndFeel;

import panel.avatar.ComponentPanel;
import panel.avatar.PreviewPanel;

@SuppressWarnings("serial")
public class MainFrame extends StandaloneFrame{
	private static MainFrame instance = null;
    public static MainFrame getInstance() {
    	if(instance == null) {
    		instance = new MainFrame();                                         
    	}
    	return instance;
    }
    
    public static void main(String[] args) {
    	//JFrame.setDefaultLookAndFeelDecorated(true);  
        //JDialog.setDefaultLookAndFeelDecorated(true);  
        
    	java.awt.EventQueue.invokeLater(new Runnable() {
    		public void run() {
    			getInstance();
    			try {
    				UIManager.setLookAndFeel(new SubstanceDustLookAndFeel());
    				SubstanceLookAndFeel.setSkin(new DustSkin());
    			} catch (UnsupportedLookAndFeelException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    	});
    }

	@Override
	protected void Layout() {
		setTitle("游戏编辑器");
		//setUndecorated(true);
		
		setLayout(new BorderLayout());
		
		//RSyntaxTextArea codeArea = new CodeArea();
		//RTextScrollPane scrollPane = new RTextScrollPane(codeArea);
		//add(scrollPane, BorderLayout.CENTER);
		add(PreviewPanel.getInstance(), BorderLayout.NORTH);
		
		JTabbedPane tabs = new JTabbedPane();

		tabs.addTab("肤色", new ComponentPanel("body"));
		tabs.addTab("面部", new ComponentPanel("face gear"));
		tabs.addTab("发型", new ComponentPanel("hair"));
		tabs.addTab("服饰", new ComponentPanel("clothes"));
		
		add(tabs, BorderLayout.CENTER);
		//add();
	}
}
