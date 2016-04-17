package jmycka.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import jmycka.image.ImageWrapper;
import jmycka.image.Thumbnail;
import jmycka.storage.ImageStorage;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class MainWindow {

	private static final int MAIN_FRAME_WIDTH = 1366;
	private static final int MAIN_FRAME_HEIGHT = 768;
	private static final int LEFT_SCROLL_PANE_WIDTH = 300;
	private static final int LEFT_SCROLL_PANE_HEIGHT = 1366;
	private static final String IMAGE_FILE_DESCRIPTION = "Images";
	private static final String[] IMAGE_FILE_EXTENSION = new String[] {"png", "jpg"};
	
	private JFrame frame;
	private JPanel thumbnailPanel = new JPanel();
	private JScrollPane leftScrollPane = new JScrollPane(thumbnailPanel);
	
	//Lists
	ArrayList<Thumbnail> thumbnailList = new ArrayList<Thumbnail>();
	
	//Storages
	private ImageStorage imageStorage = ImageStorage.getInstance();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.thumbnailPanel.setLayout(new BoxLayout(this.thumbnailPanel, BoxLayout.Y_AXIS));
		this.frame = new JFrame();
		this.frame.setBounds(100, 100, MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menuBar = new JMenuBar();
		this.frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmAddImage = new JMenuItem("Add image");
		mntmAddImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter(IMAGE_FILE_DESCRIPTION, IMAGE_FILE_EXTENSION);
				String userDir = System.getProperty("user.home");
				JFileChooser chooser = new JFileChooser(userDir + "/Desktop");
				chooser.setFileFilter(filter);
				int retVal = chooser.showOpenDialog(null);
				
				if (retVal == JFileChooser.APPROVE_OPTION)
				{
					File file = chooser.getSelectedFile();
					try {
						BufferedImage image = ImageIO.read(file);
						addImage(image);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		mnFile.add(mntmAddImage);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmRestart = new JMenuItem("Restart");
		mnFile.add(mntmRestart);
		
		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		this.frame.getContentPane().setLayout(new BorderLayout(0, 0));
		this.leftScrollPane.setPreferredSize(new Dimension(LEFT_SCROLL_PANE_WIDTH, LEFT_SCROLL_PANE_HEIGHT));
		this.frame.getContentPane().add(this.leftScrollPane, BorderLayout.WEST);

	}

	@SuppressWarnings("unused")
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	private void addImage(BufferedImage image)
	{
		ImageWrapper imageWrapper = new ImageWrapper(image);
		this.imageStorage.addItem(imageWrapper);		
		this.thumbnailPanel.add(imageWrapper.getThumbnail());
		this.thumbnailPanel.revalidate();
		this.thumbnailPanel.repaint();

	}

}
