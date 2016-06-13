package viewcontrol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * JTextFieldListener
 *
 */
public class MainMenuListeners implements ActionListener {

	private MainMenu panel;

	public MainMenuListeners(MainMenu mainMenu) {
		panel = mainMenu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		if (source == panel.getExit()) {
			System.exit(0);
		} else if (source == panel.getArchive()) {
			panel.toggleArchive();
		} else if (source == panel.getSettings()) {
			panel.toggleSettings();
		} else if (source == panel.getNewGame()) {
			panel.toggleMainGame();
			panel.getFrame().getPanel4().getGame().getBoard().putPiecesOnStart();
			panel.getFrame().getBridge().updateField();
		} else if (source == panel.getCotinue()) {
			panel.toggleMainGame();
			panel.getFrame().getBridge().updateField();
		}

	}
}
