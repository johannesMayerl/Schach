package viewcontrol;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class WhiteField extends FieldWrapper {

	private ImageIcon img;

	public WhiteField() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		if (screenSize.getWidth() > 1920 && screenSize.getHeight() > 1080) {
			whiteFieldBig("./textures/MainGame/EmptyWhiteField.png");
		} else {
			whiteFieldSmall("./textures/MainGame/EmptyWhiteField.png");
		}
	}

	public WhiteField(String path) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		if (screenSize.getWidth() > 1920 && screenSize.getHeight() > 1080) {
			whiteFieldBig(path);
		} else {
			whiteFieldSmall(path);
		}
	}

	public void whiteFieldBig(String path) {
		this.setPreferredSize(new Dimension(170, 170));
		img = new ImageIcon(path);
		this.setIcon(img);

		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);

	}

	public void whiteFieldSmall(String path) {

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

	@Override
	public String toString() {
		return img.getDescription();
	}
}
