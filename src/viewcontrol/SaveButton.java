package viewcontrol;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class SaveButton extends JButton {

	private ImageIcon img;
	private boolean is4K;

	public SaveButton() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		if (screenSize.getWidth() > 1920 && screenSize.getHeight() > 1080) {
			is4K = true;
			backButtonBig("./textures/Archive/2015_16_Semesterprojekt_POS_Archive_Save_Button.png");
		} else {
			is4K = false;
			backButtonSmall("./textures/Archive/2015_16_Semesterprojekt_POS_Archive_Save_Button_FullHD.png");
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
				img = new ImageIcon("./textures/Archive/2015_16_Semesterprojekt_POS_Archive_Save_Button.png");
				this.setIcon(img);
			} else {
				img = new ImageIcon("./textures/Archive/2015_16_Semesterprojekt_POS_Archive_Save_Button_Hover.png");
				this.setIcon(img);
			}
		} else {
			if (isToggled) {
				img = new ImageIcon("./textures/Archive/2015_16_Semesterprojekt_POS_Archive_Save_Button.png");
				this.setIcon(img);
			} else {
				img = new ImageIcon("./textures/Archive/2015_16_Semesterprojekt_POS_Archive_Save_Button_fullHD_Hover.png");
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
