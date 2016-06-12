package viewcontrol;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BackButton extends JButton {

	private ImageIcon img;
	private boolean is4K;

	public BackButton() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		if (screenSize.getWidth() > 1920 && screenSize.getHeight() > 1080) {
			is4K = true;
			backButtonBig("./textures/Buttons/Settings/2015_16_Semesterprojekt_POS_Settings_Back_Button.png");
		} else {
			is4K = false;
			backButtonSmall("./textures/Buttons/Settings/2015_16_Semesterprojekt_POS_Settings_Back_Button_fullHD.png");
		}

	}

	public void backButtonBig(String path) {
		this.setPreferredSize(new Dimension(440, 107));
		img = new ImageIcon(path);
		this.setIcon(img);

		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		this.setVisible(true);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				toggleHover(false);
				playSound();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				toggleHover(true);
			}
		});

	}

	public void backButtonSmall(String path) {
		this.setPreferredSize(new Dimension(220, 53));
		img = new ImageIcon(path);
		this.setIcon(img);

		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		this.setVisible(true);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				toggleHover(false);
				playSound();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				toggleHover(true);
			}
		});

	}

	public void toggleHover(boolean isToggled) {
		if (is4K) {
			if (isToggled) {
				img = new ImageIcon("./textures/Buttons/Settings/2015_16_Semesterprojekt_POS_Settings_Back_Button.png");
				this.setIcon(img);
			} else {
				img = new ImageIcon(
						"./textures/Buttons/Settings/2015_16_Semesterprojekt_POS_Settings_Back_Button_Hover.png");
				this.setIcon(img);
			}
		} else {
			if (isToggled) {
				img = new ImageIcon(
						"./textures/Buttons/Settings/2015_16_Semesterprojekt_POS_Settings_Back_Button_fullHD.png");
				this.setIcon(img);
			} else {
				img = new ImageIcon(
						"./textures/Buttons/Settings/2015_16_Semesterprojekt_POS_Settings_Back_Button_Hover_fullHD.png");
				this.setIcon(img);
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
}
