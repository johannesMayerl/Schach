package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

import chessPieces.*;
import exceptions.FieldException;
import exceptions.GameExceptions;
import viewcontrol.MainGame;

/**
 * Created by Noah on 02.06.2016.
 * Erweitert bzw. komplett gemacht von Thomas Görlich
 */
public class SaveLoad {

	private ChessGame currentGame;
	private MainGame mainGame;
	private PlayField field;
	private Position pos;

	public SaveLoad(ChessGame currentGame, MainGame mainGame) {
		this.currentGame = currentGame;
		this.mainGame = mainGame;
		pos = new Position();
		field = new PlayField(currentGame);
		field.setToNULL();
	}

	public void save(String savename) throws IOException, FieldException {
		int lengthOfPf = currentGame.getBoard().getField().length;
		File file = new File("./Saves/"+savename+".save");
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		for (int i=0; i<lengthOfPf; i++){
			for (int j =0; j<lengthOfPf; j++){
				ChessPiece[][] cp = currentGame.getBoard().getField();
				if(cp[i][j]!=null){
					bw.write(cp[i][j].toString()+cp[i][j].getColour()+";");
				}
			}
		}

		if(currentGame.getBoard().getColorChange()){
			bw.write("true");
		}else{
			bw.write("false");
		}

		bw.close();
	}

	/*
	 *
	 * Wenn man die geladenen Sachen aus der Datei hat ist das erste der Name der Figur, das
	 * zweite die Position, das dritte die Farbe. Ob der Buchstabe klein oder groß ist hängt
	 * davon ab ob die Figur schon gefahren ist oder nicht!
	 * Bsp.: P/e4/true oder
	 * 		 p/e4/true
	 *
	 * */

	public void loadGame(String savedGame) throws IOException, GameExceptions {
		String []namePosition;
		ChessGame load = new ChessGame(mainGame);
		int lengthPlayField = load.getBoard().getField().length;

		String nameWithPath = savedGame + ".save";
		File file = new File("./Saves/"+nameWithPath);

		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		br.close();

		namePosition = line.split(";");
		ChessPiece[][] pieces;
		pieces = load.getBoard().getField();

		for(int i = 0; i < lengthPlayField; i++){
			for(int j = 0; j < lengthPlayField; j++){
				pieces[i][j] = null;
			}
		}

			int x;
			int y;
			boolean color;

		for(int i = 0;i < namePosition.length;i++){
			x = pos.xValueLoad(namePosition[i]);
			y = pos.yValueLoad(namePosition[i]);

			switch (namePosition[i].charAt(0)){
				case 'P':
					color = namePosition[i].charAt(5) != 't';
					field.addPiece(x,y, new Pawn(color, pos.fromINTtoString(x,y), false));
					break;
				case 'p':
					color = namePosition[i].charAt(5) != 't';
					field.addPiece(x,y, new Pawn(color, pos.fromINTtoString(x,y), true));
					break;
				case 'R':
					color = namePosition[i].charAt(5) != 't';
					field.addPiece(x,y, new Rook(color, pos.fromINTtoString(x,y), false));
					break;
				case 'r':
					color = namePosition[i].charAt(5) != 't';
					field.addPiece(x,y, new Rook(color, pos.fromINTtoString(x,y), true));
					break;
				case 'K':
					color = namePosition[i].charAt(5) != 't';
					field.addPiece(x,y, new King(color, pos.fromINTtoString(x,y), false));
					break;
				case 'k':
					color = namePosition[i].charAt(5) != 't';
					field.addPiece(x,y, new King(color, pos.fromINTtoString(x,y), true));
					break;
				case 'G':
					color = namePosition[i].charAt(5) != 't';
					field.addPiece(x,y, new Knight(color, pos.fromINTtoString(x,y), false));
					break;
				case 'Q':
					color = namePosition[i].charAt(5) != 't';
					field.addPiece(x,y, new Queen(color, pos.fromINTtoString(x,y), false));
					break;
				case 'B':
					color = namePosition[i].charAt(5) != 't';
					field.addPiece(x,y, new Bishop(color, pos.fromINTtoString(x,y), false));
					break;
				default:
					System.err.println("none of them used");
					break;
			}

			if(i == namePosition.length){
				if(Objects.equals(namePosition[i], "true")){
					load.getBoard().setColorChange(true);
				}else{
					load.getBoard().setColorChange(false);
				}
			}
		}
			load.setBoard(field);

			mainGame.setGame(null);
			mainGame.setGame(load);
			mainGame.getFrame().getBridge().updateField();
	}

	public ChessGame getCurrentGame() {
		return currentGame;
	}

	public void setCurrentGame(ChessGame currentGame) {
		this.currentGame = currentGame;
	}

	public MainGame getMainGame() {
		return mainGame;
	}

	public void setMainGame(MainGame mainGame) {
		this.mainGame = mainGame;
	}
}
