package frame.gui.window;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
abstract class StandaloneFrame extends JFrame {
    protected StandaloneFrame() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            //e.printStackTrace();
        }

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //setPreferredSize(new java.awt.Dimension(400, 600));
        //setResizable(false);
        Layout();
    }
    
    protected abstract void Layout();
    
    //protected abstract void LayoutAfterAll();
    
    public static void MakeCenter(Window window) {
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(window.getGraphicsConfiguration());
        Rectangle screenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        window.setLocation((screenSize.width - screenInsets.left - screenInsets.right - window.getWidth()) / 2,
            (screenSize.height - screenInsets.top - screenInsets.bottom - window.getHeight()) / 2);
    }
}
