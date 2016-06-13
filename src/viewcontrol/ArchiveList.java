package viewcontrol;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JList;
import javax.swing.ListModel;

public class ArchiveList extends JList {
	private BufferedImage background;

	public ArchiveList(ListModel values) {
		super(values);
		try {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			if (screenSize.getWidth() <= 1920 && screenSize.getHeight() <= 1080) {
				background = ImageIO.read(new File("./textures/Archive/woodenPlanks.jpg"));
			} else {
				background = ImageIO.read(new File("./textures/Archive/woodenPlanks_4k.png"));
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		setOpaque(false);
		setBackground(new Color(0, 0, 0, 0));
		setForeground(Color.WHITE);
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (background != null) {
			Graphics2D g2d = (Graphics2D) g.create();
			int x = getWidth() - background.getWidth();
			int y = getHeight() - background.getHeight();
			g2d.drawImage(background, x, y, this);
			g2d.dispose();
		}
		super.paintComponent(g);
	}

}
