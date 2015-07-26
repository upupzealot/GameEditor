package panel.avatar.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.prefs.Preferences;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import panel.CommonData;
import panel.avatar.core.DataCore;
import frame.gui.window.MainFrame;

@SuppressWarnings("serial")
public class AvartarPanel extends JPanel{
	public ComponentPanel Body_Panel;
	public ComponentPanel Face_Panel;
	public ComponentPanel Hair_Panel;
	public ComponentPanel Clothes_Panel;
	
	private static AvartarPanel instance = null;
    public static AvartarPanel getInstance() {
    	if(instance == null) {
    		instance = new AvartarPanel();    
    	}
    	return instance;
    }
    
    private static DataCore SharedDataCore;
    public static DataCore getSharedDataCore() {
    	return SharedDataCore;
    }
	
    public JTabbedPane tabs;
	private AvartarPanel() {
		//DataTree data = new DataTree(new File(CommonData.CURRENT_PATH + "/res/"));
		File PSDFile = new File(Preferences.userNodeForPackage(getClass()).get("LastOpen", CommonData.CURRENT_PATH + "/res/Avatar.psd"));
		try {
			SharedDataCore = new DataCore(PSDFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setWatchingPSD(PSDFile);
		
		setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(PreviewPanel.getInstance(), BorderLayout.CENTER);
		panel.setBackground(PreviewPanel.getInstance().getBackground());
		panel.add(ZoomBox.getInstance(), BorderLayout.EAST);
		add(panel, BorderLayout.NORTH);
		tabs = new JTabbedPane();
		
		BufferedImage icon = null;
		try {
			Body_Panel = new ComponentPanel("body");
			icon = ImageIO.read(getClass().getResourceAsStream("/res/body.png"));
			JScrollPane sp = new JScrollPane(Body_Panel);
			sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			sp.setMaximumSize(new Dimension(100, 100));
			sp.setBorder(null);
			tabs.addTab("肤色", new ImageIcon(icon), Body_Panel);
			//tabs.addTab("肤色", Body_Panel);
			
			Face_Panel = new ComponentPanel("face gear");
			icon = ImageIO.read(getClass().getResourceAsStream("/res/face gear.png"));
			tabs.addTab("面部", new ImageIcon(icon), Face_Panel);
			//tabs.addTab("面部", Face_Panel);
			Hair_Panel = new ComponentPanel("hair");
			icon = ImageIO.read(getClass().getResourceAsStream("/res/hair.png"));
			tabs.addTab("发型", new ImageIcon(icon), Hair_Panel);
			//tabs.addTab("发型", Hair_Panel);
			Clothes_Panel = new ComponentPanel("clothes");
			icon = ImageIO.read(getClass().getResourceAsStream("/res/clothes.png"));
			tabs.addTab("服饰", new ImageIcon(icon), Clothes_Panel);
			//tabs.addTab("服饰", Clothes_Panel);
		} catch (IOException e) {
			e.printStackTrace();
		}
		add(tabs, BorderLayout.CENTER);
		setDataCore(SharedDataCore);
	}
	
	public void setDataFile(File data_file) throws IOException {
		setDataFile(data_file, true);
	}
	
	private void setDataFile(File data_file, boolean rewatch) throws IOException {
		setDataCore(new DataCore(data_file));
		if(data_file.isFile() && rewatch) {
			setWatchingPSD(data_file);
		}
	}
	
	public void setDataCore(DataCore data) {
		SharedDataCore = data;
		
		Body_Panel.setImages(data.get("body"));
		Face_Panel.setImages(data.get("face gear"));
		Hair_Panel.setImages(data.get("hair"));
		Clothes_Panel.setImages(data.get("clothes"));
	}
	
	private File watch_file = null;
	private Timer watcher = null;
	private long update_mark = 0;
	public void setWatchingPSD(File file) {
		watch_file = file;
		update_mark = file.lastModified();
		
		if(watcher == null) {
			watcher = new Timer();
			watcher.schedule(new TimerTask() {
				@Override
				public void run() {
					if(watch_file.exists() && watch_file.lastModified() != update_mark) {
						System.out.println(watch_file.getName() + " Modified!");
						SwingUtilities.invokeLater(new Runnable() {  
			                public void run() {  
								try {
									setDataFile(watch_file, false);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								MainFrame.getInstance().repaint();
			                }  
			            });
						update_mark = watch_file.lastModified();
					}
				}
			}, 0, 100);
		}
	}
}
