package viewcontrol;

import java.awt.*;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;

import model.BridgeFromFrontEndToBackEnd;

public class Frame extends JFrame {

	private BridgeFromFrontEndToBackEnd bridge;

	// reference to the frame itself
	private Frame frame;

	private MainMenu panel1;
	private Archive panel2;
	private Settings panel3;
	private MainGame panel4;
	JPanel glass;
	SavePopup saveDialog;

	private boolean musicPlays;
	private Clip clip;
	private FloatControl gainControl;

	public Frame() throws UnsupportedLookAndFeelException {
		frame = this;
		glass = (JPanel) frame.getGlassPane();

		glass.setVisible(true);
		glass.setLayout(new GridBagLayout());
		saveDialog = new SavePopup(this);
		saveDialog.setVisible(false);
		glass.add(saveDialog);


		bridge = new BridgeFromFrontEndToBackEnd(this);

		playMusic();

		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		UIManager.setLookAndFeel(new javax.swing.plaf.metal.MetalLookAndFeel());

		setTitle("Chess");

		frame.setUndecorated(true);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		setSize(width, height);
		setMaximizedBounds(new Rectangle(screenSize));
		BorderLayout b = new BorderLayout();
		setLayout(b);

		panel4 = new MainGame("textures/Background/2015_16_Semesterprojekt_POS_MainMenu_Background.png", this);
		panel1 = new MainMenu("textures/Background/2015_16_Semesterprojekt_POS_MainMenu_Background.png", this);
		panel2 = new Archive("textures/Background/2015_16_Semesterprojekt_POS_MainMenu_Background.png", this);
		panel3 = new Settings("textures/Background/2015_16_Semesterprojekt_POS_MainMenu_Background.png", this);


		this.getContentPane().add(panel1);
		this.getContentPane().add(panel2);
		this.getContentPane().add(panel3);
		this.getContentPane().add(panel4);

		panel2.setVisible(false);
		panel3.setVisible(false);
		panel4.setVisible(false);

		setResizable(false);
		setVisible(true);

	}

	public BridgeFromFrontEndToBackEnd getBridge() {
		return bridge;
	}

	public void setBridge(BridgeFromFrontEndToBackEnd bridge) {
		this.bridge = bridge;
	}

	public Frame getThisSimpleFrame() {
		return frame;
	}

	public void setThisSimpleFrame(Frame thisSimpleFrame) {
		this.frame = thisSimpleFrame;
	}

	public MainMenu getPanel1() {
		return panel1;
	}

	public void setPanel1(MainMenu panel1) {
		this.panel1 = panel1;
	}

	public Archive getPanel2() {
		return panel2;
	}

	public void setPanel2(Archive panel2) {
		this.panel2 = panel2;
	}

	public Settings getPanel3() {
		return panel3;
	}

	public void setPanel3(Settings panel3) {
		this.panel3 = panel3;
	}

	public MainGame getPanel4() {
		return panel4;
	}

	public void setPanel4(MainGame panel4) {
		this.panel4 = panel4;
	}

	public boolean isMusicPlays() {
		return musicPlays;
	}

	public void setMusicPlays(boolean musicPlays) {
		this.musicPlays = musicPlays;
	}

	public Clip getClip() {
		return clip;
	}

	public void setClip(Clip clip) {
		this.clip = clip;
	}

	public FloatControl getGainControl() {
		return gainControl;
	}

	public void setGainControl(FloatControl gainControl) {
		this.gainControl = gainControl;
	}

	public void toggleMainGame(boolean isMainMenu) {
		if (isMainMenu) {
			panel1.setVisible(false);
			panel4.setVisible(true);
		} else {
			panel1.setVisible(true);
			panel4.setVisible(false);
		}
	}

	public void toggleArchive(boolean isMainMenu) {
		if (isMainMenu) {
			panel1.setVisible(false);
			panel2.setVisible(true);
		} else {
			panel1.setVisible(true);
			panel2.setVisible(false);
		}
	}

	public void toggleSettings(boolean isMainMenu) {
		if (isMainMenu) {
			panel1.setVisible(false);
			panel3.setVisible(true);
		} else {
			panel1.setVisible(true);
			panel3.setVisible(false);
		}
	}

	public void playMusic() {

		try {
			clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem
					.getAudioInputStream(new File("sounds/MainMenu_Background_Music_01.au"));
			clip.open(inputStream);
			gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-10.0f);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

		musicPlays = true;
	}

	public void toggleVolum(float a) {
		gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(gainControl.getValue() + a);
	}

	public void updateArchive() {
		panel2 = null;
		panel2 = new Archive("textures/Background/2015_16_Semesterprojekt_POS_MainMenu_Background.png", this);
		add(panel2);
	}
	public void stopMusic() {
		clip.stop();
	}

	public void startMusic() {
		clip.start();
	}

	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	public SavePopup getSaveDialog() {
		return saveDialog;
	}

	public void setSaveDialog(SavePopup saveDialog) {
		this.saveDialog = saveDialog;
	}

	public JPanel getGlass() {
		return glass;
	}

	public void setGlass(JPanel glass) {
		this.glass = glass;
	}

	public void gameOver(String message){
		getGlass().add(new GameOverPopup(this, message));
		repaint();
	}
}
