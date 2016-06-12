package viewcontrol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * JTextFieldListener
 *
 */
public class SettingsListeners implements ActionListener {

	private Settings panel;

	public SettingsListeners(Settings settings) {
		panel = settings;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		if (source == panel.getPlusMusic()) {
			panel.increaseMusic();
		}
		if (source == panel.getMinusMusic()) {
			panel.decreaseMusic();
		}
		if (source == panel.getBack()) {
			panel.getFrame().toggleSettings(false);
		}

	}
}
