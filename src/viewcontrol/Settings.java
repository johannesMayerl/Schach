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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.plaf.ProgressBarUI;
import javax.swing.plaf.metal.MetalProgressBarUI;

public class Settings extends JPanel {

	private Image img;

	public Frame frame;

	private JButton plusMusic;
	private JButton minusMusic;
	private JProgressBar soundVolumMusic;

	private BackButton back;

	public Settings(String img, Frame frame) {
		this(new ImageIcon(img).getImage(), frame);
	}

	public Settings(Image img, Frame frame) {
		this.frame = frame;
		this.img = img;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(screenSize);
		setMinimumSize(screenSize);
		setMaximumSize(size);
		setSize(screenSize);
		setLayout(new GridBagLayout());

		this.frame = frame;

		if (screenSize.getWidth() > 1920 && screenSize.getHeight() > 1080) {
			addComponents4K();
		} else {
			addComponents();
		}

	}

	public void addComponents4K() {
		SettingsListeners listeners = new SettingsListeners(this);

		ImageIcon icon1 = new ImageIcon(
				"./textures/Buttons/Settings/2015_16_Semesterprojekt_POS_Settings_Plus_Button.png");
		plusMusic = new JButton("", icon1);
		plusMusic.setPreferredSize(new Dimension(100, 100));
		plusMusic.setBorderPainted(false);
		plusMusic.setFocusPainted(false);
		plusMusic.setContentAreaFilled(false);
		plusMusic.addActionListener(listeners);

		ImageIcon icon2 = new ImageIcon(
				"./textures/Buttons/Settings/2015_16_Semesterprojekt_POS_Settings_Minus_Button.png");
		minusMusic = new JButton("", icon2);
		minusMusic.setPreferredSize(new Dimension(100, 100));
		minusMusic.setBorderPainted(false);
		minusMusic.setFocusPainted(false);
		minusMusic.setContentAreaFilled(false);
		minusMusic.addActionListener(listeners);

		soundVolumMusic = new JProgressBar(10, 80);
		ProgressBarUI ui = new MetalProgressBarUI() {
			@Override
			protected Color getSelectionForeground() {
				Color c = new Color(213, 150, 92);
				return c;
			}

			@Override
			protected Color getSelectionBackground() {
				Color c = new Color(197, 90, 17);
				return c;
			}

		};
		soundVolumMusic.setUI(ui);
		soundVolumMusic.setBackground(new Color(213, 150, 92));
		soundVolumMusic.setPreferredSize(new Dimension(800, 100));
		soundVolumMusic.setBorderPainted(false);
		soundVolumMusic.setValue(50);
		soundVolumMusic.setForeground(new Color(197, 90, 17));
		soundVolumMusic.setStringPainted(true);
		soundVolumMusic.setFont(new Font("Century", 0, 55));

		back = new BackButton();
		back.addActionListener(listeners);

		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 20, 5);
		c.gridx = 1;
		c.gridy = 1;
		add(minusMusic, c);

		GridBagConstraints c2 = new GridBagConstraints();
		c2.insets = new Insets(5, 5, 20, 5);
		c2.gridx = 2;
		c2.gridy = 1;
		add(soundVolumMusic, c2);

		GridBagConstraints c3 = new GridBagConstraints();
		c3.insets = new Insets(5, 5, 20, 5);
		c3.gridx = 3;
		c3.gridy = 1;
		add(plusMusic, c3);

		GridBagConstraints c4 = new GridBagConstraints();
		c4.insets = new Insets(5, 5, 20, 5);
		c4.gridx = 2;
		c4.gridy = 2;
		add(back, c4);
	}

	public void addComponents() {
		SettingsListeners listeners = new SettingsListeners(this);

		ImageIcon icon1 = new ImageIcon(
				"./textures/Buttons/Settings/2015_16_Semesterprojekt_POS_Settings_Plus_Button_fullHD.png");
		plusMusic = new JButton("", icon1);
		plusMusic.setPreferredSize(new Dimension(50, 50));
		plusMusic.setBorderPainted(false);
		plusMusic.setFocusPainted(false);
		plusMusic.setContentAreaFilled(false);
		plusMusic.addActionListener(listeners);

		ImageIcon icon2 = new ImageIcon(
				"./textures/Buttons/Settings/2015_16_Semesterprojekt_POS_Settings_Minus_Button_fullHD.png");
		minusMusic = new JButton("", icon2);
		minusMusic.setPreferredSize(new Dimension(50, 50));
		minusMusic.setBorderPainted(false);
		minusMusic.setFocusPainted(false);
		minusMusic.setContentAreaFilled(false);
		minusMusic.addActionListener(listeners);

		soundVolumMusic = new JProgressBar(10, 80);
		ProgressBarUI ui = new MetalProgressBarUI() {
			@Override
			protected Color getSelectionForeground() {
				Color c = new Color(213, 150, 92);
				return c;
			}

			@Override
			protected Color getSelectionBackground() {
				Color c = new Color(197, 90, 17);
				return c;
			}

		};
		soundVolumMusic.setUI(ui);
		soundVolumMusic.setBackground(new Color(213, 150, 92));
		soundVolumMusic.setPreferredSize(new Dimension(400, 50));
		soundVolumMusic.setBorderPainted(false);
		soundVolumMusic.setValue(50);
		soundVolumMusic.setForeground(new Color(197, 90, 17));
		soundVolumMusic.setStringPainted(true);
		soundVolumMusic.setFont(new Font("Century", 0, 25));

		back = new BackButton();
		back.addActionListener(listeners);

		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 20, 5);
		c.gridx = 1;
		c.gridy = 1;
		add(minusMusic, c);

		GridBagConstraints c2 = new GridBagConstraints();
		c2.insets = new Insets(5, 5, 20, 5);
		c2.gridx = 2;
		c2.gridy = 1;
		add(soundVolumMusic, c2);

		GridBagConstraints c3 = new GridBagConstraints();
		c3.insets = new Insets(5, 5, 20, 5);
		c3.gridx = 3;
		c3.gridy = 1;
		add(plusMusic, c3);

		GridBagConstraints c4 = new GridBagConstraints();
		c4.insets = new Insets(5, 5, 20, 5);
		c4.gridx = 2;
		c4.gridy = 2;
		add(back, c4);
	}

	public void increaseMusic() {
		if (soundVolumMusic.getValue() < 90) {

			if (soundVolumMusic.getPercentComplete() == 0) {
				frame.startMusic();
			}

			soundVolumMusic.setValue(soundVolumMusic.getValue() + 5);
			frame.toggleVolum(2);
		}
	}

	public void decreaseMusic() {
		if (soundVolumMusic.getValue() > 10) {

			soundVolumMusic.setValue(soundVolumMusic.getValue() - 5);
			frame.toggleVolum(-2);

			if (soundVolumMusic.getPercentComplete() == 0) {
				frame.stopMusic();
			}
		}
	}

	public JButton getBack() {
		return back;
	}

	public void setBack(BackButton back) {
		this.back = back;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	public JButton getPlusMusic() {
		return plusMusic;
	}

	public void setPlusMusic(JButton plusMusic) {
		this.plusMusic = plusMusic;
	}

	public JButton getMinusMusic() {
		return minusMusic;
	}

	public void setMinusMusic(JButton minusMusic) {
		this.minusMusic = minusMusic;
	}

	public JProgressBar getSoundVolumMusic() {
		return soundVolumMusic;
	}

	public void setSoundVolumMusic(JProgressBar soundVolumMusic) {
		this.soundVolumMusic = soundVolumMusic;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(img, this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
	}

}
