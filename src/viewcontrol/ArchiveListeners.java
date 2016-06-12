package viewcontrol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArchiveListeners implements ActionListener {

	private Archive panel;

	public ArchiveListeners(Archive archive) {
		panel = archive;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

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
			}

		}

	}

}
