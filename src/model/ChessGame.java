package model;

import exceptions.FieldException;
import exceptions.GameExceptions;
import exceptions.PositionException;

/**
 * Created by Noah on 04.05.2016.
 */
public class ChessGame {

	private PlayField board;

	public ChessGame() {
		super();
		board = new PlayField();
	}

	public PlayField getBoard() {

		return board;
	}
	public void setBoard(PlayField board) throws GameExceptions {
		if (board == null)
			throw new GameExceptions("Board in the method 'setBoard' must not be null!");
		this.board = board;
	}

}
