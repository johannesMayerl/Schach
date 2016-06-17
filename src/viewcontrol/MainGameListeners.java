package viewcontrol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import chessPieces.ChessPiece;
import exceptions.FieldException;
import exceptions.PositionException;
import model.BridgeFromFrontEndToBackEnd;
import model.Position;
import model.SaveLoad;

/**
 * 
 * JTextFieldListener
 *
 */
public class MainGameListeners implements ActionListener, MouseListener {

	private MainGame panel;
	private BridgeFromFrontEndToBackEnd bridge;

	public MainGameListeners(MainGame mainMenu) {
		panel = mainMenu;
		bridge = panel.getFrame().getBridge();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		if (source == panel.getBack()) {
			panel.getFrame().toggleMainGame(false);
		} else if (source == panel.getBackMove()) {
			System.out.println("Should Move Back");
			panel.getGame().back();
		}else
		if(source == panel.getSave()){
			System.out.println("Save BTN pressed!");
			SaveLoad sl = new SaveLoad(panel.getGame(),panel);
			try {
				sl.save(panel.getSaveName().getText());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FieldException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			panel.getSaveName().setText(null);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		panel.playSound();
		Object source = e.getSource();
		Position p = getField(source);

		// System.out.println(bridge.toChessNotation(p));

		if (panel.getCurrentFigure() == null) {
			if (getFigure(p) != null) {
				panel.setCurrentField((FieldWrapper) source);
				ChessPiece cp = getFigure(p);
				panel.setCurrentFigure(cp);

				//bridge.allpossibleMoves();
			}
		} else {
			ChessPiece cp = panel.getCurrentFigure();

			String endPos = bridge.toChessNotation(p);

			boolean isPossible = false;

			try {
				isPossible = panel.getGame().getBoard().validMove(endPos, cp);
			} catch (FieldException | PositionException e1) {
				e1.printStackTrace();
			}

			if (isPossible) {
				bridge.validField(p);
				panel.setCurrentFigure(null);
				panel.setCurrentField(null);
				panel.getFrame().getBridge().updateField();
			} else {
				bridge.playUnsucssefullSound();
				panel.setCurrentFigure(null);
				panel.setCurrentField(null);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public Position getField(Object o) {
		return bridge.determinField(o);
	}

	public ChessPiece getFigure(Position p) {
		return bridge.getFigure(p);
	}
}
