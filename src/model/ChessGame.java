package model;

import exceptions.FieldException;
import exceptions.GameExceptions;
import exceptions.PositionException;

/**
 * Created by Noah on 04.05.2016.
 */
public class ChessGame {

	private Player white;
	private Player black;
	private PlayField board;

	public ChessGame() {
		super();
		board = new PlayField();
	}

	public Player getWhite() {
		return white;
	}

	public void setWhite(Player player) throws GameExceptions {
		if (player == null)
			throw new GameExceptions("Player in the method 'setBlack' must not be null!");
		this.white = player;
	}

	public Player getBlack() {

		return black;
	}

	public void setBlack(Player player) throws GameExceptions {
		if (player == null)
			throw new GameExceptions("Player in the method 'setBlack' must not be null!");
		this.black = black;
	}

	public PlayField getBoard() {

		return board;
	}

	public void setBoard(PlayField board) throws GameExceptions {
		if (board == null)
			throw new GameExceptions("Board in the method 'setBoard' must not be null!");
		this.board = board;
	}

	public boolean initializeGame(boolean startBlack) throws FieldException, PositionException {
		boolean winner = false;
		if (black == null || white == null) {
			return false;
		}

		if (startBlack == true) {
			// black starts

		} else if (startBlack == false) {
			// white starts

		}
		return true;
	}

	public String saveData() {
		String important = null;
		important = black.saveOutPut() + "|" + white.saveOutPut();
		return important;
	}
}
