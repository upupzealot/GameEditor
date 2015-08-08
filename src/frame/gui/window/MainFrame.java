package frame.gui.window;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;

@SuppressWarnings("serial")
public class MainFrame extends StandaloneFrame{
	private static MainFrame instance = null;
	private static boolean initing = false;
    public static MainFrame getInstance() {
    	if(instance == null) {
    		if(!initing) {
    			initing = true;
    			instance = new MainFrame(); 
        		initing = false;
    		}
    		//instance.setAlwaysOnTop(true);
    	}
    	return instance;
    }
    
    public static boolean isIniting() {
    	return initing;
    }
    
    public static void main(String[] args) {
    	//javax.swing.JFrame.setDefaultLookAndFeelDecorated(true);
        //javax.swing.JDialog.setDefaultLookAndFeelDecorated(true);
        
    	java.awt.EventQueue.invokeLater(new Runnable() {
    		public void run() {
    			getInstance();
    			///*
    			try {
    				String LookAndFeelName = "Dust";
    				//String LookAndFeelName = "Twilight";
    				UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.Substance" + LookAndFeelName + "LookAndFeel");
    				SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin." + LookAndFeelName + "Skin");
    				//System.err.close();
    				
    				getInstance().pack();
    				MakeCenter(getInstance());
    				getInstance().setVisible(true);
    				getInstance().setMinimumSize(getInstance().getSize());

    				getInstance().setFocusable(true);
    			} catch (UnsupportedLookAndFeelException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	});
    }

	@Override
	protected void Layout() {
		setTitle("ÎäÆ÷±à¼­Æ÷");
		try {
			BufferedImage img0 = ImageIO.read(getClass().getResourceAsStream("/res/Icon.png"));
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
		
		//setJMenuBar(new frame.gui.menu.MenuBar());
		//setLayout(new BorderLayout());
		//add(panel.avatar.gui.AvartarPanel.getInstance(), BorderLayout.CENTER);
		
		//add(new panel.importer.gui.ImportPanel(), BorderLayout.CENTER);
		//setResizable(false);
		
		add(panel.weapon.gui.WeaponPanel.getInstance(), BorderLayout.CENTER);
		
		//add(panel.map.gui.MapPanel.getInstance(), BorderLayout.CENTER);
		
		//RSyntaxTextArea codeArea = new CodeArea();
		//RTextScrollPane scrollPane = new RTextScrollPane(codeArea);
		//add(scrollPane, BorderLayout.CENTER);
	}
}
