package viewcontrol;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * StartFrameTest
 *
 */
public class FrameTest {
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Frame frame;
				try {
					frame = new Frame();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
