package frame.gui.window;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.skin.DustSkin;
import org.pushingpixels.substance.api.skin.SubstanceDustLookAndFeel;

import panel.CommonData;
import panel.avatar.gui.AvartarPanel;
import frame.gui.menu.MenuBar;

@SuppressWarnings("serial")
public class MainFrame extends StandaloneFrame{
	private static MainFrame instance = null;
    public static MainFrame getInstance() {
    	if(instance == null) {
    		instance = new MainFrame();    
    		instance.setAlwaysOnTop(true);
    	}
    	return instance;
    }
    
    public static void main(String[] args) {
    	//javax.swing.JFrame.setDefaultLookAndFeelDecorated(true);
        //javax.swing.JDialog.setDefaultLookAndFeelDecorated(true);
        
    	java.awt.EventQueue.invokeLater(new Runnable() {
    		public void run() {
    			getInstance();
    	        
    			///*
    			try {
    				UIManager.setLookAndFeel(new SubstanceDustLookAndFeel());
    				SubstanceLookAndFeel.setSkin(new DustSkin());
    				
    				getInstance().pack();
    				MakeCenter(getInstance());
    				getInstance().setVisible(true);
    				getInstance().setMinimumSize(getInstance().getSize());

    				getInstance().setFocusable(true);
    			} catch (UnsupportedLookAndFeelException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			//*/
    		}
    	});
    }

	@Override
	protected void Layout() {
		setTitle("Ö½ÍÞÍÞ±à¼­Æ÷");
		try {
			BufferedImage img0 = ImageIO.read(new File(CommonData.CURRENT_PATH + "/res/Icon.png"));
			Image img1 = img0.getScaledInstance(32, 32, Image.SCALE_FAST);
			Image img2 = img0.getScaledInstance(64, 64, Image.SCALE_FAST);
			Image img3 = img0.getScaledInstance(128, 128, Image.SCALE_FAST);
			Image img4 = img0.getScaledInstance(256, 256, Image.SCALE_FAST);
			List<Image> Icons = new ArrayList<Image>();
			Icons.add(img0);
			Icons.add(img1);
			Icons.add(img2);
			Icons.add(img3);
			Icons.add(img4);
			setIconImages(Icons);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setJMenuBar(new MenuBar());
		
		setLayout(new BorderLayout());
		add(AvartarPanel.getInstance(), BorderLayout.CENTER);
		
		//RSyntaxTextArea codeArea = new CodeArea();
		//RTextScrollPane scrollPane = new RTextScrollPane(codeArea);
		//add(scrollPane, BorderLayout.CENTER);
	}
}
