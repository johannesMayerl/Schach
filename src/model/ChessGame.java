package model;

import exceptions.FieldException;
import exceptions.GameExceptions;
import exceptions.PositionException;
import viewcontrol.MainGame;

import java.util.ArrayList;

/**
 * Created by Noah on 04.05.2016.
 */
public class ChessGame {

	private PlayField board;
	private MainGame mg;

	private ArrayList<PlayField> listOfMovesMade;
	private int last;

	public ChessGame(MainGame mg) {
		super();
		board = new PlayField(this);

		last = -1;
		listOfMovesMade = new ArrayList<PlayField>();
		this.mg = mg;
	}

	public PlayField getBoard() {
		return board;
	}

	public void setBoard(PlayField board) throws GameExceptions {
		if (board == null)
			throw new GameExceptions("Board in the method 'setBoard' must not be null!");
		this.board = board;
	}

	public MainGame getMg() {
		return mg;
	}

	public void back(){
		board = listOfMovesMade.get(last-1);
		mg.getFrame().getBridge().updateField();
	}

	public void setMg(MainGame mg) {
		this.mg = mg;
	}

	public ArrayList<PlayField> getListOfMovesMade() {
		return listOfMovesMade;
	}

	public void setListOfMovesMade(ArrayList<PlayField> listOfMovesMade) {
		this.listOfMovesMade = listOfMovesMade;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}
}
