package viewcontrol;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class BlackField extends FieldWrapper {

	private ImageIcon img;

	public BlackField() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		if (screenSize.getWidth() > 1920 && screenSize.getHeight() > 1080) {
			blackFieldBig("./textures/MainGame/EmptyBlackField.png");
		} else {
			blackFieldSmall("./textures/MainGame/EmptyBlackField.png");
		}
	}

	public BlackField(String path) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		if (screenSize.getWidth() > 1920 && screenSize.getHeight() > 1080) {
			blackFieldBig(path);
		} else {
			blackFieldSmall(path);
		}
	}

	public void blackFieldSmall(String path) {

		if (path.contains("fullHD")) {
			this.setPreferredSize(new Dimension(100, 100));
			img = new ImageIcon(path);
			this.setIcon(img);

			this.setBorderPainted(false);
			this.setFocusPainted(false);
			this.setContentAreaFilled(false);
		} else {
			path = makeFullHD(path);

			this.setPreferredSize(new Dimension(100, 100));
			img = new ImageIcon(path);
			this.setIcon(img);

			this.setBorderPainted(false);
			this.setFocusPainted(false);
			this.setContentAreaFilled(false);
		}

	}

	private String makeFullHD(String path) {
		String[] parts = path.split("[/]");
		String[] fileName = parts[parts.length - 1].split("[.]");

		fileName[0] += "_fullHD.";

		String newFileName = "";

		for (int i = 0; i < parts.length - 1; i++) {
			newFileName += parts[i] + "/";
		}

		for (int i = 0; i < fileName.length; i++) {
			newFileName += fileName[i];
		}

		return newFileName;

	}

	public void blackFieldBig(String path) {
		System.out.println("Test");
		this.setPreferredSize(new Dimension(170, 170));
		img = new ImageIcon(path);
		this.setIcon(img);

		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);

	}

	@Override
	public String toString() {
		return img.getDescription();
	}

}
