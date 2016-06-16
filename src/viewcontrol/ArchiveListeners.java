package viewcontrol;

import exceptions.GameExceptions;
import model.SaveLoad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ArchiveListeners implements ActionListener {

	private Archive panel;
	private SaveLoad sv;

	public ArchiveListeners(Archive archive) {
		panel = archive;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		sv = new SaveLoad(panel.getFrame().getPanel4().getGame(), panel.getFrame().getPanel4());

		Object source = e.getSource();

		if (source == panel.getBack()) {
			System.out.println("Back BTN pressed!");
			panel.getFrame().toggleArchive(false);
		} else if (source == panel.getLoad()) {
			System.out.println("Load BTN pressed!");
			if (panel.getList().getSelectedValue() == null) {
				System.out.println("Nothing selected to load!");
			} else {
				System.out.println(panel.getList().getSelectedValue().toString());
				try {
					sv.load(panel.getList().getSelectedValue().toString());
					panel.getFrame().toggleArchive(false);
					panel.getFrame().toggleMainGame(true);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (GameExceptions gameExceptions) {
					gameExceptions.printStackTrace();
				}
			}

		}

	}

}
