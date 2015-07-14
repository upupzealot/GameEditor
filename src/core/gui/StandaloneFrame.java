package core.gui;
import java.awt.Dimension;
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
        setPreferredSize(new Dimension(400, 600));
        //setResizable(false);
        Layout();

        pack();
        MakeCenter(this);
        setVisible(true);

        setFocusable(true);
    }
    
    protected abstract void Layout();
    
    public static void MakeCenter(Window window) {
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(window.getGraphicsConfiguration());
        Rectangle screenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        window.setLocation((screenSize.width - screenInsets.left - screenInsets.right - window.getWidth()) / 2,
            (screenSize.height - screenInsets.top - screenInsets.bottom - window.getHeight()) / 2);
    }
}
