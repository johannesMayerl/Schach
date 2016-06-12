package viewcontrol;

import java.awt.Dimension;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

class MainMenu extends JPanel {

	private Image img;

	private JButton newGame;
	private JButton cotinue;
	private JButton archive;
	private JButton settings;
	private JButton leer;
	private JButton exit;
	private ArrayList<JButton> buttons;

	private ImageIcon icon1;
	private ImageIcon icon2;
	private ImageIcon icon3;
	private ImageIcon icon4;
	private ImageIcon icon5;
	private ImageIcon icon6;

	private Dimension screenSize;
	private Dimension fullHD;
	private Dimension vierK;

	private Frame frame;
	private MainMenuListeners listener;

	public MainMenu(String img, Frame frame) {
		this(new ImageIcon(img).getImage(), frame);
	}

	public MainMenu(Image img, Frame frame) {
		this.frame = frame;
		this.img = img;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setPreferredSize(screenSize);
		setMinimumSize(screenSize);
		setSize(screenSize);
		setLayout(new GridBagLayout());

		listener = new MainMenuListeners(this);

		// setting buttons and icons

		icon1 = new ImageIcon("./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_New_Game.png");
		icon2 = new ImageIcon(
				"./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_Continue_Game.png");
		icon3 = new ImageIcon("./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_Archive.png");
		icon4 = new ImageIcon("./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_Settings.png");
		icon5 = new ImageIcon("./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_Leer.png");
		icon6 = new ImageIcon("./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_Exit.png");

		// adding hover effect
		newGame = new JButton("", icon1);
		newGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				toggleNewGameButton(false);
				playSound();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				toggleNewGameButton(true);
			}
		});

		cotinue = new JButton("", icon2);
		cotinue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				toggleCotinueButton(false);
				playSound();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				toggleCotinueButton(true);
			}
		});

		archive = new JButton("", icon3);
		archive.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				toggleArchiveButton(false);
				playSound();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				toggleArchiveButton(true);
			}
		});

		settings = new JButton("", icon4);
		settings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				toggleSettingsButton(false);
				playSound();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				toggleSettingsButton(true);
			}
		});

		leer = new JButton("", icon5);
		leer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				toggleLeerButton(false);
				playSound();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				toggleLeerButton(true);
			}
		});

		exit = new JButton("", icon6);
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				toggleExitButton(false);
				playSound();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				toggleExitButton(true);
			}
		});

		// styling Buttons
		styleButtons();

		// adding Layout Manager
		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);

		// adding buttons to panel
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 20, 5);
		c.gridx = 1;
		c.gridy = 1;
		add(newGame, c);

		GridBagConstraints c2 = new GridBagConstraints();
		c2.insets = new Insets(5, 5, 20, 5);
		c2.gridx = 1;
		c2.gridy = 2;
		add(cotinue, c2);

		GridBagConstraints c3 = new GridBagConstraints();
		c3.insets = new Insets(5, 5, 20, 5);
		c3.gridx = 1;
		c3.gridy = 3;
		add(archive, c3);

		GridBagConstraints c4 = new GridBagConstraints();
		c4.insets = new Insets(5, 5, 20, 5);
		c4.gridx = 1;
		c4.gridy = 4;
		add(settings, c4);

		GridBagConstraints c5 = new GridBagConstraints();
		c5.insets = new Insets(5, 5, 20, 5);
		c5.gridx = 1;
		c5.gridy = 5;

		GridBagConstraints c6 = new GridBagConstraints();
		c6.insets = new Insets(5, 5, 20, 5);
		c6.gridx = 1;
		c6.gridy = 6;
		add(exit, c6);
	}

	/**
	 * wird bei jeder aenderung der Komponente aufgerufen und damit das image
	 * neu gezeichnet
	 */
	@Override
	public void paintComponent(Graphics g) {

		g.drawImage(img, this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
	}

	public void styleButtons() {
		buttons = new ArrayList<JButton>();
		buttons.add(newGame);
		buttons.add(cotinue);
		buttons.add(archive);
		buttons.add(settings);
		buttons.add(leer);
		buttons.add(exit);

		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		fullHD = new Dimension(1920, 1080);
		vierK = new Dimension(3840, 2160);

		if (screenSize.equals(vierK)) {
			for (JButton b : buttons) {
				Dimension d = new Dimension(850, 107);
				b.setPreferredSize(d);
				b.addActionListener(new MainMenuListeners(this));

				b.setBorderPainted(false);
				b.setFocusPainted(false);
				b.setContentAreaFilled(false);
			}
		} else if (screenSize.equals(fullHD)) {

			ImageIcon resize1 = new ImageIcon(
					"./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_New_Game_fullHD.png");
			Image img1 = resize1.getImage();
			icon1.setImage(img1);

			ImageIcon resize2 = new ImageIcon(
					"./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_Continue_Game_fullHD.png");
			Image img2 = resize2.getImage();
			icon2.setImage(img2);

			ImageIcon resize3 = new ImageIcon(
					"./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_Archive_fullHD.png");
			Image img3 = resize3.getImage();
			icon3.setImage(img3);

			ImageIcon resize4 = new ImageIcon(
					"./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_Settings_fullHD.png");
			Image img4 = resize4.getImage();
			icon4.setImage(img4);

			ImageIcon resize5 = new ImageIcon(
					"./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_Leer_fullHD.png");
			Image img5 = resize5.getImage();
			icon5.setImage(img5);

			ImageIcon resize6 = new ImageIcon(
					"./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_Exit_fullHD.png");
			Image img6 = resize6.getImage();
			icon6.setImage(img6);

			for (JButton b : buttons) {

				Dimension d = new Dimension(425, 53);
				b.setPreferredSize(d);
				b.addActionListener(new MainMenuListeners(this));

				b.setBorderPainted(false);
				b.setFocusPainted(false);
				b.setContentAreaFilled(false);
			}
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

	// adding hover effect to buttons
	public void toggleNewGameButton(boolean toggled) {
		if (screenSize.equals(vierK)) {
			if (toggled) {
				ImageIcon icon1 = new ImageIcon(
						"./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_New_Game.png");
				newGame.setIcon(icon1);
			} else {
				ImageIcon icon1 = new ImageIcon(
						"./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_New_Game_Hover.png");
				newGame.setIcon(icon1);
			}
		} else if (screenSize.equals(fullHD)) {
			if (toggled) {
				ImageIcon icon1 = new ImageIcon(
						"./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_New_Game_fullHD.png");
				newGame.setIcon(icon1);
			} else {
				ImageIcon icon1 = new ImageIcon(
						"./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_New_Game_fullHD_Hover.png");
				newGame.setIcon(icon1);
			}
		}
	}

	public void toggleCotinueButton(boolean toggled) {

		if (screenSize.equals(vierK)) {
			if (toggled) {
				ImageIcon icon1 = new ImageIcon(
						"./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_Continue_Game.png");
				cotinue.setIcon(icon1);
			} else {
				ImageIcon icon1 = new ImageIcon(
						"./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_Continue_Game_Hover.png");
				cotinue.setIcon(icon1);
			}
		} else if (screenSize.equals(fullHD)) {
			if (toggled) {
				ImageIcon icon1 = new ImageIcon(
						"./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_Continue_Game_fullHD.png");
				cotinue.setIcon(icon1);
			} else {
				ImageIcon icon1 = new ImageIcon(
						"./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_Continue_Game_fullHD_Hover.png");
				cotinue.setIcon(icon1);
			}
		}

	}

	public void toggleSettingsButton(boolean toggled) {

		if (screenSize.equals(vierK)) {
			if (toggled) {
				ImageIcon icon1 = new ImageIcon(
						"./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_Settings.png");
				settings.setIcon(icon1);
			} else {
				ImageIcon icon1 = new ImageIcon(
						"./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_Settings_Hover.png");
				settings.setIcon(icon1);
			}
		} else if (screenSize.equals(fullHD)) {
			if (toggled) {
				ImageIcon icon1 = new ImageIcon(
						"./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_Settings_fullHD.png");
				settings.setIcon(icon1);
			} else {
				ImageIcon icon1 = new ImageIcon(
						"./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_Settings_fullHD_Hover.png");
				settings.setIcon(icon1);
			}
		}
	}

	public void toggleLeerButton(boolean toggled) {

		if (screenSize.equals(vierK)) {
			if (toggled) {
				ImageIcon icon1 = new ImageIcon(
						"./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_Leer.png");
				leer.setIcon(icon1);
			} else {
				ImageIcon icon1 = new ImageIcon(
						"./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_Leer_Hover.png");
				leer.setIcon(icon1);
			}
		} else if (screenSize.equals(fullHD)) {
			if (toggled) {
				ImageIcon icon1 = new ImageIcon(
						"./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_Leer_fullHD.png");
				leer.setIcon(icon1);
			} else {
				ImageIcon icon1 = new ImageIcon(
						"./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_Leer_fullHD_Hover.png");
				leer.setIcon(icon1);
			}
		}
	}

	public void toggleArchiveButton(boolean toggled) {

		if (screenSize.equals(vierK)) {
			if (toggled) {
				ImageIcon icon1 = new ImageIcon(
						"./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_Archive.png");
				archive.setIcon(icon1);
			} else {
				ImageIcon icon1 = new ImageIcon(
						"./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_Archive_Hover.png");
				archive.setIcon(icon1);
			}
		} else if (screenSize.equals(fullHD)) {
			if (toggled) {
				ImageIcon icon1 = new ImageIcon(
						"./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_Archive_fullHD.png");
				archive.setIcon(icon1);
			} else {
				ImageIcon icon1 = new ImageIcon(
						"./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_Archive_fullHD_Hover.png");
				archive.setIcon(icon1);
			}
		}

	}

	public void toggleExitButton(boolean toggled) {

		if (screenSize.equals(vierK)) {
			if (toggled) {
				ImageIcon icon1 = new ImageIcon(
						"./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_Exit.png");
				exit.setIcon(icon1);
			} else {
				ImageIcon icon1 = new ImageIcon(
						"./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_Exit_Hover.png");
				exit.setIcon(icon1);
			}
		} else if (screenSize.equals(fullHD)) {
			if (toggled) {
				ImageIcon icon1 = new ImageIcon(
						"./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_Exit_fullHD.png");
				exit.setIcon(icon1);
			} else {
				ImageIcon icon1 = new ImageIcon(
						"./textures/Buttons/MainMenu/2015_16_Semesterprojekt_POS_MainMenu_Buttons_Exit_fullHD_Hover.png");
				exit.setIcon(icon1);
			}
		}
	}

	public void toggleMainGame() {
		frame.toggleMainGame(true);
	}

	public void toggleArchive() {
		frame.toggleArchive(true);
	}

	public void toggleSettings() {
		frame.toggleSettings(true);
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public JButton getNewGame() {
		return newGame;
	}

	public void setNewGame(JButton newGame) {
		this.newGame = newGame;
	}

	public JButton getCotinue() {
		return cotinue;
	}

	public void setCotinue(JButton cotinue) {
		this.cotinue = cotinue;
	}

	public JButton getArchive() {
		return archive;
	}

	public void setArchive(JButton archive) {
		this.archive = archive;
	}

	public JButton getSettings() {
		return settings;
	}

	public void setSettings(JButton settings) {
		this.settings = settings;
	}

	public JButton getLeer() {
		return leer;
	}

	public void setLeer(JButton leer) {
		this.leer = leer;
	}

	public JButton getExit() {
		return exit;
	}

	public void setExit(JButton exit) {
		this.exit = exit;
	}

	public ArrayList<JButton> getButtons() {
		return buttons;
	}

	public void setButtons(ArrayList<JButton> buttons) {
		this.buttons = buttons;
	}

	public ImageIcon getIcon1() {
		return icon1;
	}

	public void setIcon1(ImageIcon icon1) {
		this.icon1 = icon1;
	}

	public ImageIcon getIcon2() {
		return icon2;
	}

	public void setIcon2(ImageIcon icon2) {
		this.icon2 = icon2;
	}

	public ImageIcon getIcon3() {
		return icon3;
	}

	public void setIcon3(ImageIcon icon3) {
		this.icon3 = icon3;
	}

	public ImageIcon getIcon4() {
		return icon4;
	}

	public void setIcon4(ImageIcon icon4) {
		this.icon4 = icon4;
	}

	public ImageIcon getIcon5() {
		return icon5;
	}

	public void setIcon5(ImageIcon icon5) {
		this.icon5 = icon5;
	}

	public ImageIcon getIcon6() {
		return icon6;
	}

	public void setIcon6(ImageIcon icon6) {
		this.icon6 = icon6;
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

	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	public MainMenuListeners getListener() {
		return listener;
	}

	public void setListener(MainMenuListeners listener) {
		this.listener = listener;
	}

}
