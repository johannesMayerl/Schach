package viewcontrol;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Archive extends JPanel {

	private Image img;
	private BackButton back;
	public Frame frame;

	private Dimension screenSize;
	private Dimension fullHD;
	private Dimension vierK;

	private ArchiveListeners alisteners;
	private ArchiveList list;
	private JScrollPane listScrollPane;
	private JButton load;
	private ImageIcon icon1;

	private ArrayList<String> arl = new ArrayList<String>();

	private void addActionListeners() {
		back.addActionListener(alisteners);
		load.addActionListener(alisteners);
	}

	public Archive(String img, Frame frame) {
		this(new ImageIcon(img).getImage(), frame);
	}

	public Archive(Image img, Frame frame) {
		this.frame = frame;
		this.img = img;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(screenSize);
		setMinimumSize(screenSize);
		setMaximumSize(size);
		setSize(screenSize);

		addFiles();

	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(img, this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
	}

	public void addFiles() {

		alisteners = new ArchiveListeners(this);

		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		fullHD = new Dimension(1920, 1080);
		vierK = new Dimension(3840, 2160);

		back = new BackButton();

		icon1 = new ImageIcon("./textures/Buttons/Settings/2015_16_Semesterprojekt_POS_Settings_Load_Button.png");
		load = new JButton(icon1);
		styleButtons();

		load.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				toggleLoadButton(false);
				playSound();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				toggleLoadButton(true);
			}
		});

		File folder = new File("./saves");
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			if (file.isFile()) {
				String[] a = file.getName().split("[.]");
				arl.add(a[0]);
			}
		}

		DefaultListModel<String> model = new DefaultListModel<String>();

		for (String a : arl) {
			model.addElement(a);
		}

		list = new ArchiveList(model);
		list.setForeground(Color.WHITE);
		list.setSelectionBackground(Color.white);
		list.setSelectionForeground(Color.BLACK);
		list.setFont(new Font("Century", Font.BOLD, 35));
		list.setOpaque(false);

		listScrollPane = new JScrollPane();
		listScrollPane.getViewport().setView(list);

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		if (screenSize.getWidth() <= 1920 && screenSize.getHeight() <= 1080) {
			listScrollPane.setPreferredSize(new Dimension(1200, 500));
			list.setFont(new Font("Century", Font.BOLD, 35));
		} else{
			listScrollPane.setPreferredSize(new Dimension(2400, 1000));
			list.setFont(new Font("Century", Font.BOLD, 55));
		}

		listScrollPane.setOpaque(false);
		listScrollPane.getViewport().setOpaque(false);

		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 20, 5);
		c.gridwidth = 3;
		c.gridx = 2;
		c.gridy = 1;
		add(listScrollPane, c);

		GridBagConstraints c2 = new GridBagConstraints();
		c2.insets = new Insets(5, 5, 20, 0);
		c2.gridx = 2;
		c2.gridy = 3;
		add(load, c2);

		GridBagConstraints c3 = new GridBagConstraints();
		c3.insets = new Insets(5, 0, 20, 5);
		c3.gridx = 4;
		c3.gridy = 3;
		add(back, c3);

		addActionListeners();

	}

	public void toggleLoadButton(boolean toggled) {

		if (screenSize.equals(vierK)) {
			if (toggled) {
				ImageIcon icon1 = new ImageIcon(
						"./textures/Buttons/Settings/2015_16_Semesterprojekt_POS_Settings_Load_Button.png");
				load.setIcon(icon1);
			} else {
				ImageIcon icon1 = new ImageIcon(
						"./textures/Buttons/Settings/2015_16_Semesterprojekt_POS_Settings_Load_Button_Hover.png");
				load.setIcon(icon1);
			}
		} else if (screenSize.equals(fullHD)) {
			if (toggled) {
				ImageIcon icon1 = new ImageIcon(
						"./textures/Buttons/Settings/2015_16_Semesterprojekt_POS_Settings_Load_Button_fullHD.png");
				load.setIcon(icon1);
			} else {
				ImageIcon icon1 = new ImageIcon(
						"./textures/Buttons/Settings/2015_16_Semesterprojekt_POS_Settings_Load_Button_Hover_fullHD.png");
				load.setIcon(icon1);
			}
		}
	}

	public void styleButtons() {

		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		fullHD = new Dimension(1920, 1080);
		vierK = new Dimension(3840, 2160);

		if (screenSize.equals(vierK)) {
			Dimension d = new Dimension(850, 107);
			load.setPreferredSize(d);

			load.setBorderPainted(false);
			load.setFocusPainted(false);
			load.setContentAreaFilled(false);

		} else if (screenSize.equals(fullHD)) {

			ImageIcon resize1 = new ImageIcon(
					"./textures/Buttons/Settings/2015_16_Semesterprojekt_POS_Settings_Load_Button_fullHD.png");
			Image img1 = resize1.getImage();
			icon1.setImage(img1);

			Dimension d = new Dimension(425, 53);
			load.setPreferredSize(d);

			load.setBorderPainted(false);
			load.setFocusPainted(false);
			load.setContentAreaFilled(false);

		}
	}

	public void playSound() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem
							.getAudioInputStream(new File("sounds/MainMenu_Hover_Click.wav"));
					clip.open(inputStream);
					clip.start();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}).start();
	}

	public ArrayList<String> getArl() {
		return arl;
	}

	public void setArl(ArrayList<String> arl) {
		this.arl = arl;
	}

	public ArchiveList getList() {
		return list;
	}

	public void setList(ArchiveList list) {
		this.list = list;
	}

	public JScrollPane getListScrollPane() {
		return listScrollPane;
	}

	public void setListScrollPane(JScrollPane listScrollPane) {
		this.listScrollPane = listScrollPane;
	}

	public Image getImg() {
		return img;
	}

	public JButton getLoad() {
		return load;
	}

	public void setLoad(JButton load) {
		this.load = load;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public BackButton getBack() {
		return back;
	}

	public void setBack(BackButton back) {
		this.back = back;
	}

	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	public Dimension getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(Dimension screenSize) {
		this.screenSize = screenSize;
	}

	public Dimension getFullHD() {
		return fullHD;
	}

	public void setFullHD(Dimension fullHD) {
		this.fullHD = fullHD;
	}

	public Dimension getVierK() {
		return vierK;
	}

	public void setVierK(Dimension vierK) {
		this.vierK = vierK;
	}

}
