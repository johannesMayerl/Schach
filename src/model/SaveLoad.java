package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

	public void load(String nameOfGame) throws IOException, GameExceptions {
		String[] nameWithPos;
		ChessGame chessLoaded = new ChessGame(mainGame);
		int lengthOfPf = chessLoaded.getBoard().getField().length;

		String nameWithPath = new String(nameOfGame+".save");
		File file = new File("./Saves/"+nameWithPath);

		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		br.close();

		nameWithPos = line.split(";");
		ChessPiece[][] cp;
		cp = chessLoaded.getBoard().getField();

		for (int i=0; i<lengthOfPf; i++){
			for (int j =0; j<lengthOfPf; j++){
				cp[i][j]=null;
			}
		}

		int posX = 0;
		int posY = 0;
		boolean color = false;

		for(int i = 0;i < nameWithPos.length;i++){

			if(nameWithPos[i].charAt(0)=='p'){
				//pawn moved
				if(nameWithPos[i].charAt(2)=='a'){
					posX = 0;
				}else if(nameWithPos[i].charAt(2)=='b'){
					posX = 1;
				}else if(nameWithPos[i].charAt(2)=='c'){
					posX = 2;
				}else if(nameWithPos[i].charAt(2)=='d'){
					posX = 3;
				}else if(nameWithPos[i].charAt(2)=='e'){
					posX = 4;
				}else if(nameWithPos[i].charAt(2)=='f'){
					posX = 5;
				}else if(nameWithPos[i].charAt(2)=='g'){
					posX = 6;
				}else if(nameWithPos[i].charAt(2)=='h'){
					posX = 7;
				}

				posY = Character.getNumericValue(nameWithPos[i].charAt(3))-1;

				if(nameWithPos[i].charAt(5)=='t'){
					color = false;
				}else{
					color = true;
				}

				String xy = String.valueOf(nameWithPos[i].charAt(2))+/*String.valueOf(*/posY+1/*)*/;
				field.addPiece(posX,posY, new Pawn(color, xy, true));
				//cp[posX][posY] = new Pawn(color, xy, true);

			}else
			if(nameWithPos[i].charAt(0)=='P'){
				//pawn moved
				if(nameWithPos[i].charAt(2)=='a'){
					posX = 0;
				}else if(nameWithPos[i].charAt(2)=='b'){
					posX = 1;
				}else if(nameWithPos[i].charAt(2)=='c'){
					posX = 2;
				}else if(nameWithPos[i].charAt(2)=='d'){
					posX = 3;
				}else if(nameWithPos[i].charAt(2)=='e'){
					posX = 4;
				}else if(nameWithPos[i].charAt(2)=='f'){
					posX = 5;
				}else if(nameWithPos[i].charAt(2)=='g'){
					posX = 6;
				}else if(nameWithPos[i].charAt(2)=='h'){
					posX = 7;
				}

				posY = Character.getNumericValue(nameWithPos[i].charAt(3))-1;

				if(nameWithPos[i].charAt(5)=='t'){
					color = false;
				}else{
					color = true;
				}

				String xy = String.valueOf(nameWithPos[i].charAt(2))+/*String.valueOf(*/posY+1/*)*/;
				field.addPiece(posX, posY, new Pawn(color, xy, false));
				//cp[posX][posY] = new Pawn(color, xy, false);


			}else
			if(nameWithPos[i].charAt(0)=='k'){
				//pawn moved
				if(nameWithPos[i].charAt(2)=='a'){
					posX = 0;
				}else if(nameWithPos[i].charAt(2)=='b'){
					posX = 1;
				}else if(nameWithPos[i].charAt(2)=='c'){
					posX = 2;
				}else if(nameWithPos[i].charAt(2)=='d'){
					posX = 3;
				}else if(nameWithPos[i].charAt(2)=='e'){
					posX = 4;
				}else if(nameWithPos[i].charAt(2)=='f'){
					posX = 5;
				}else if(nameWithPos[i].charAt(2)=='g'){
					posX = 6;
				}else if(nameWithPos[i].charAt(2)=='h'){
					posX = 7;
				}

				posY = Character.getNumericValue(nameWithPos[i].charAt(3))-1;

				if(nameWithPos[i].charAt(5)=='t'){
					color = false;
				}else{
					color = true;
				}

				String xy = String.valueOf(nameWithPos[i].charAt(2))+/*String.valueOf(*/posY+1/*)*/;
				field.addPiece(posX,posY, new King(color, xy, true));
				//cp[posX][posY] = new King(color, xy, true);


			}else
			if(nameWithPos[i].charAt(0)=='K'){
				//pawn moved
				if(nameWithPos[i].charAt(2)=='a'){
					posX = 0;
				}else if(nameWithPos[i].charAt(2)=='b'){
					posX = 1;
				}else if(nameWithPos[i].charAt(2)=='c'){
					posX = 2;
				}else if(nameWithPos[i].charAt(2)=='d'){
					posX = 3;
				}else if(nameWithPos[i].charAt(2)=='e'){
					posX = 4;
				}else if(nameWithPos[i].charAt(2)=='f'){
					posX = 5;
				}else if(nameWithPos[i].charAt(2)=='g'){
					posX = 6;
				}else if(nameWithPos[i].charAt(2)=='h'){
					posX = 7;
				}

				posY = Character.getNumericValue(nameWithPos[i].charAt(3))-1;

				if(nameWithPos[i].charAt(5)=='t'){
					color = false;
				}else{
					color = true;
				}

				String xy = String.valueOf(nameWithPos[i].charAt(2))+/*String.valueOf(*/posY+1/*)*/;
				field.addPiece(posX,posY, new King(color, xy, false));
				//cp[posX][posY] = new King(color, xy, false);


			}else
			if(nameWithPos[i].charAt(0)=='g'){
				//pawn moved
				if(nameWithPos[i].charAt(2)=='a'){
					posX = 0;
				}else if(nameWithPos[i].charAt(2)=='b'){
					posX = 1;
				}else if(nameWithPos[i].charAt(2)=='c'){
					posX = 2;
				}else if(nameWithPos[i].charAt(2)=='d'){
					posX = 3;
				}else if(nameWithPos[i].charAt(2)=='e'){
					posX = 4;
				}else if(nameWithPos[i].charAt(2)=='f'){
					posX = 5;
				}else if(nameWithPos[i].charAt(2)=='g'){
					posX = 6;
				}else if(nameWithPos[i].charAt(2)=='h'){
					posX = 7;
				}

				posY = Character.getNumericValue(nameWithPos[i].charAt(3))-1;

				if(nameWithPos[i].charAt(5)=='t'){
					color = false;
				}else{
					color = true;
				}

				String xy = String.valueOf(nameWithPos[i].charAt(2))+/*String.valueOf(*/posY+1/*)*/;
				field.addPiece(posX,posY, new Knight(color, xy, true));
				//cp[posX][posY] = new Knight(color, xy, true);


			}else
			if(nameWithPos[i].charAt(0)=='G'){
				//pawn moved
				if(nameWithPos[i].charAt(2)=='a'){
					posX = 0;
				}else if(nameWithPos[i].charAt(2)=='b'){
					posX = 1;
				}else if(nameWithPos[i].charAt(2)=='c'){
					posX = 2;
				}else if(nameWithPos[i].charAt(2)=='d'){
					posX = 3;
				}else if(nameWithPos[i].charAt(2)=='e'){
					posX = 4;
				}else if(nameWithPos[i].charAt(2)=='f'){
					posX = 5;
				}else if(nameWithPos[i].charAt(2)=='g'){
					posX = 6;
				}else if(nameWithPos[i].charAt(2)=='h'){
					posX = 7;
				}

				posY = Character.getNumericValue(nameWithPos[i].charAt(3))-1;

				if(nameWithPos[i].charAt(5)=='t'){
					color = false;
				}else{
					color = true;
				}

				String xy = String.valueOf(nameWithPos[i].charAt(2))+/*String.valueOf(*/posY+1/*)*/;
				field.addPiece(posX,posY, new Knight(color, xy, false));
				//cp[posX][posY] = new Knight(color, xy, false);


			}else
			if(nameWithPos[i].charAt(0)=='Q'){
				//pawn moved
				if(nameWithPos[i].charAt(2)=='a'){
					posX = 0;
				}else if(nameWithPos[i].charAt(2)=='b'){
					posX = 1;
				}else if(nameWithPos[i].charAt(2)=='c'){
					posX = 2;
				}else if(nameWithPos[i].charAt(2)=='d'){
					posX = 3;
				}else if(nameWithPos[i].charAt(2)=='e'){
					posX = 4;
				}else if(nameWithPos[i].charAt(2)=='f'){
					posX = 5;
				}else if(nameWithPos[i].charAt(2)=='g'){
					posX = 6;
				}else if(nameWithPos[i].charAt(2)=='h'){
					posX = 7;
				}

				posY = Character.getNumericValue(nameWithPos[i].charAt(3))-1;

				if(nameWithPos[i].charAt(5)=='t'){
					color = false;
				}else{
					color = true;
				}

				String xy = String.valueOf(nameWithPos[i].charAt(2))+/*String.valueOf(*/posY+1/*)*/;
				field.addPiece(posX,posY, new Queen(color, xy, false));
				//cp[posX][posY] = new Queen(color, xy, false);

			}else
			if(nameWithPos[i].charAt(0)=='B'){
				//pawn moved
				if(nameWithPos[i].charAt(2)=='a'){
					posX = 0;
				}else if(nameWithPos[i].charAt(2)=='b'){
					posX = 1;
				}else if(nameWithPos[i].charAt(2)=='c'){
					posX = 2;
				}else if(nameWithPos[i].charAt(2)=='d'){
					posX = 3;
				}else if(nameWithPos[i].charAt(2)=='e'){
					posX = 4;
				}else if(nameWithPos[i].charAt(2)=='f'){
					posX = 5;
				}else if(nameWithPos[i].charAt(2)=='g'){
					posX = 6;
				}else if(nameWithPos[i].charAt(2)=='h'){
					posX = 7;
				}

				posY = Character.getNumericValue(nameWithPos[i].charAt(3))-1;

				if(nameWithPos[i].charAt(5)=='t'){
					color = false;
				}else{
					color = true;
				}

				String xy = String.valueOf(nameWithPos[i].charAt(2))+/*String.valueOf(*/posY+1/*)*/;
				field.addPiece(posX,posY, new Bishop(color, xy, false));
				//cp[posX][posY] = new Bishop(color, xy, false);

			}else
			if(nameWithPos[i].charAt(0)=='r'){
				if(nameWithPos[i].charAt(2)=='a'){
					posX = 0;
				}else if(nameWithPos[i].charAt(2)=='b'){
					posX = 1;
				}else if(nameWithPos[i].charAt(2)=='c'){
					posX = 2;
				}else if(nameWithPos[i].charAt(2)=='d'){
					posX = 3;
				}else if(nameWithPos[i].charAt(2)=='e'){
					posX = 4;
				}else if(nameWithPos[i].charAt(2)=='f'){
					posX = 5;
				}else if(nameWithPos[i].charAt(2)=='g'){
					posX = 6;
				}else if(nameWithPos[i].charAt(2)=='h'){
					posX = 7;
				}

				posY = Character.getNumericValue(nameWithPos[i].charAt(3))-1;

				if(nameWithPos[i].charAt(5)=='t'){
					color = false;
				}else{
					color = true;
				}

				String xy = String.valueOf(nameWithPos[i].charAt(2))+/*String.valueOf(*/posY+1/*)*/;
				field.addPiece(posX,posY, new Rook(color, xy, true));
				//cp[posX][posY] = new Rook(color, xy, true);
			}else
			if(nameWithPos[i].charAt(0)=='R'){
				//pawn moved
				if(nameWithPos[i].charAt(2)=='a'){
					posX = 0;
				}else if(nameWithPos[i].charAt(2)=='b'){
					posX = 1;
				}else if(nameWithPos[i].charAt(2)=='c'){
					posX = 2;
				}else if(nameWithPos[i].charAt(2)=='d'){
					posX = 3;
				}else if(nameWithPos[i].charAt(2)=='e'){
					posX = 4;
				}else if(nameWithPos[i].charAt(2)=='f'){
					posX = 5;
				}else if(nameWithPos[i].charAt(2)=='g'){
					posX = 6;
				}else if(nameWithPos[i].charAt(2)=='h'){
					posX = 7;
				}

				posY = Character.getNumericValue(nameWithPos[i].charAt(3))-1;

				if(nameWithPos[i].charAt(5)=='t'){
					color = false;
				}else{
					color = true;
				}

				String xy = String.valueOf(nameWithPos[i].charAt(2))+/*String.valueOf(*/posY+1/*)*/;
				field.addPiece(posX,posY, new Rook(color, xy, false));
				//cp[posX][posY] = new Rook(color, xy, false);
			}

			if(i == nameWithPos.length){
				if(nameWithPos[i] == "true"){
					chessLoaded.getBoard().setColorChange(true);
				}else{
					chessLoaded.getBoard().setColorChange(false);
				}
			}


		}

		chessLoaded.setBoard(field);
		mainGame.setGame(null);
		mainGame.setGame(chessLoaded);

		mainGame.getFrame().getBridge().updateField();

		for (int i=0; i<lengthOfPf; i++){
			for (int j =0; j<lengthOfPf; j++){
				System.out.println(mainGame.getGame().getBoard().getField()[i][j]+"\n"+"------------------------------");
			}
		}
	}

	public void loadGame(String savedGame) throws IOException, GameExceptions {
		String []namePosition;
		ChessGame load = new ChessGame(mainGame);
		int lengthPlayField = load.getBoard().getField().length;

		String nameWithPath = new String(savedGame+".save");
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

		for(int i = 0; i < namePosition.length; i++){
			x = pos.xValueLoad(namePosition[i]);
			y = pos.yValueLoad(namePosition[i]);
			color = pos.trueOrFalse(namePosition[i]);

			switch (namePosition[i].charAt(0)){
				case 'P':
					field.addPiece(x,y, new Pawn(color, pos.fromINTtoString(x,y), false));
					break;
				case 'p':
					field.addPiece(x,y, new Pawn(color, pos.fromINTtoString(x,y), true));
					break;
				case 'R':
					field.addPiece(x,y, new Rook(color, pos.fromINTtoString(x,y), false));
					break;
				case 'r':
					field.addPiece(x,y, new Rook(color, pos.fromINTtoString(x,y), true));
					break;
				case 'K':
					field.addPiece(x,y, new King(color, pos.fromINTtoString(x,y), false));
					break;
				case 'k':
					field.addPiece(x,y, new King(color, pos.fromINTtoString(x,y), true));
					break;
				case 'G':
					field.addPiece(x,y, new Knight(color, pos.fromINTtoString(x,y), false));
					break;
				case 'Q':
					field.addPiece(x,y, new Queen(color, pos.fromINTtoString(x,y), false));
					break;
				case 'B':
					field.addPiece(x,y, new Bishop(color, pos.fromINTtoString(x,y), false));
					break;
			}

			if(i == namePosition.length){
				if(namePosition[i] == "true"){
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
