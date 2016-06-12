package model;

import chessPieces.*;
import exceptions.FieldException;
import exceptions.PositionException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Noah on 01.05.2016.
 */
public class PlayField {

    private    ChessPiece[][]actPosition;
    private Position pos;

    public PlayField() {
        //initializes the array
        actPosition = new ChessPiece[8][8];
        putPiecesOnStart();
        pos = new Position();
    }

    public boolean checkSpots(String start, String end) throws FieldException, PositionException {
        if(start == null)throw new FieldException("Start position must not be NULL!");
        if(end == null)throw new FieldException("End position must not be NULL!");

        Position pos = new Position();

        int  xStart = pos.xValue(start);
        System.out.println("X-START: " + xStart);

        int yStart = pos.yValue(start);
        System.out.println("y-START: " + yStart);

        int xEnd = pos.xValue(end);
        System.out.println("X-END: " + xEnd);

        int yEnd = pos.yValue(end);
        System.out.println("Y-END: " + yEnd);

        if(xStart == xEnd && yStart > yEnd){
            //just to the left
            System.out.println("X Werte sind gleich, y start wert ist größer als y end wert");
            for(int w = yStart-1; w >= yEnd+1; w--){
                if(actPosition[xStart][w] != null){
                    System.out.println("X: " + xStart + "Y: " + w + " is occupied");
                    return false;
                }else{
                    System.out.println("X: " + w + "Y: " + yStart + " is not occupied");
                }
            }
            return true;
        }else if(yStart == yEnd && xStart < xEnd){
            //just to the bottom
            System.out.println("Y werte Sind gleich, x start wert ist kleiner als x end wert");
            for(int w = xStart+1; w <= xEnd-1; w++){
                if(actPosition[w][yStart] != null){
                    System.out.println("X: " + w + "Y: " + yStart + " is occupied");
                    return false;
                }else{
                    System.out.println("X: " + w + "Y: " + yStart + " is not occupied");
                }
            }
            return true;
        }else if(xStart == xEnd && yStart < yEnd){
            //just the right
            System.out.println("X werte Sind gleich, y start wert ist kleiner als y end wert");
            for(int w = yStart+1; w <= yEnd-1; w++){
                if(actPosition[xStart][w] != null){
                    System.out.println("X: " + xStart + "Y: " + w + " is occupied");
                    return false;
                }else{
                    System.out.println("X: " + xStart + "Y: " + w + " is not occupied");
                }
            }
            return true;

        }else if(yStart == yEnd && xStart > xEnd){
            //just to the top
            System.out.println("Y Werte sind gleich, x Start Wert ist größer als x End Wert");
            for(int w = xStart-1; w >= xEnd+1; w--){
                if(actPosition[w][yStart] != null){
                    System.out.println("X: " + w + "Y: " + yStart + " is occupied");
                    return false;
                }else{
                    System.out.println("X: " + w + "Y: " + yStart + " is not occupied");
                }
            }
            return true;
        }else if(xStart > xEnd && yStart > yEnd){
            //to the top and to the left
            System.out.println("xStart > xEnd && yStart > yEnd");
            int i = xStart-1;
            int j = yStart-1;
            while(i >= xEnd+1 && j >= yEnd+1){
                if(actPosition[i][j] != null){
                    System.out.println("X: " + i + "Y: " + j + " is occupied");
                    return false;
                }else{
                    System.out.println("X: " + i + "Y: " + j + " is not occupied");
                }
                i--;
                j--;
            }
            return true;
        }else if(yStart > yEnd && xStart < xEnd){
            //to the left and to the bottom
            System.out.println("xStart > xEnd && yStart < yEnd");
            int i = xStart+1;
            int j = yStart-1;
            while(i <= xEnd-1 && j >= yEnd+1){
                if(actPosition[i][j] != null){
                    System.out.println("X: " + i + "Y: " + j + " is occupied");
                    return false;
                }else{
                    System.out.println("X: " + i + "Y: " + j + " is not occupied");
                }
                i++;
                j--;
            }
            return true;
        }else if(xStart < xEnd && yStart < yEnd){
            //to the bottom and to the right
            System.out.println("xStart < xEnd && yStart < yEnd");
            int i = xStart+1;
            int j = yStart+1;
            while(i <= xEnd-1 && j >= yEnd-1){
                if(actPosition[i][j] != null){
                    System.out.println("X: " + i + "Y: " + j + " is occupied");
                    return false;
                }else{
                    System.out.println("X: " + i + "Y: " + j + " is not occupied");
                }
                i++;
                j++;
            }
        }else if(yStart < yEnd && xStart > xEnd){
            //to the top and to the right
            System.out.println("xStart < xEnd && yStart > yEnd");
            int i = xStart-1;
            int j = yStart+1;
            while(i >= xEnd+1 && j >= yEnd-1){
                if(actPosition[i][j] != null){
                    System.out.println("X: " + i + "Y: " + j + " is occupied");
                    return false;
                }else{
                    System.out.println("X: " + i + "Y: " + j + " is not occupied");
                }
                i--;
                j++;
            }
            return true;
        }
        System.out.println("Did not move");
        return false;
    }

    public void setActPosition(int x, int y, ChessPiece piece) throws FieldException {
        if(piece == null)throw new FieldException("Piece in the method 'setActPosition' must not be null!");
        if(x > 0 && x > 7) throw new FieldException("X must not be lower than 0 and higher than 7!");
        if(y > 0 && y > 7) throw new FieldException("Y must not be lower than 0 and higher than 7!");

        actPosition[x][y] = piece;
    }

    public void putPiecesOnStart(){
        //Pawn_black
        actPosition[0][1] = new Pawn(true, "a7", false);
        actPosition[1][1] = new Pawn(true, "b7", false);
        actPosition[2][1] = new Pawn(true, "c7", false);
        actPosition[3][1] = new Pawn(true, "d7", false);
        actPosition[4][1] = new Pawn(true, "e7", false);
        actPosition[5][1] = new Pawn(true, "f7", false);
        actPosition[6][1] = new Pawn(true, "g7", false);
        actPosition[7][1] = new Pawn(true, "h7", false);
        //Rook_black
        actPosition[0][0] = new Rook(true, "a8");
        actPosition[7][0] = new Rook(true, "h8");
        //Knight_black
        actPosition[1][0] = new Knight(true, "b8", false);
        actPosition[6][0] = new Knight(true, "g8", false);
        //Bishop_black
        actPosition[2][0] = new Bishop(true, "c8");
        actPosition[5][0] = new Bishop(true, "f8");
        //Queen_black
        actPosition[3][0] = new Queen(true, "d8");
        //King_black
        actPosition[4][0] = new King(true, "e8", false);

        //Pawn_white
        actPosition[0][6] = new Pawn(true, "a2", false);
        actPosition[1][6] = new Pawn(true, "b2", false);
        actPosition[2][6] = new Pawn(true, "c2", false);
        actPosition[3][6] = new Pawn(true, "d2", false);
        actPosition[4][6] = new Pawn(true, "e2", false);
        actPosition[5][6] = new Pawn(true, "f2", false);
        actPosition[6][6] = new Pawn(true, "g2", false);
        actPosition[7][6] = new Pawn(true, "h2", false);
        //Rook_white
        actPosition[0][7] = new Rook(true, "a1");
        actPosition[7][7] = new Rook(true, "h1");
        //Knight_white
        actPosition[1][7] = new Knight(true, "b1", false);
        actPosition[6][7] = new Knight(true, "g1", false);
        //Bishop_white
        actPosition[2][7] = new Bishop(true, "c1");
        actPosition[5][7] = new Bishop(true, "f1");
        //Queen_white
        actPosition[3][7] = new Queen(true, "d1");
        //King_whit
        actPosition[4][7] = new King(true, "e1", false);
    }

    public String getActPositionOf(ChessPiece piece) throws FieldException {
        if(piece == null)throw new FieldException("Piece in the method 'getActPositionOf' must not be NULL!");

        String position = null;

        for(int i = 0; i < actPosition.length; i++){
            for(int j = 0; j < actPosition[i].length; j++){
                if(actPosition[i][j] == piece){
                    if(j == 0){
                        position = "a" + String.valueOf(i);
                    }else if(j == 1){
                        position = "b" + String.valueOf(i);
                    }else if(j == 2){
                        position = "c" + String.valueOf(i);
                    }else if(j == 3){
                        position = "d" + String.valueOf(i);
                    }else if(j == 4){
                        position = "e" + String.valueOf(i);
                    }else if(j == 5){
                        position = "f" + String.valueOf(i);
                    }else if(j == 6){
                        position = "g" + String.valueOf(i);
                    }else if(j == 7){
                        position = "h" + String.valueOf(i);
                    }
                }
            }
        }

        return position;
    }

    /**
     * @param piece
     * @throws PositionException
     * @throws FieldException
     * needed for initializing the board (Class: ChessGame, Player, PlayField)
     */
    public void setStringActPosition(ChessPiece piece) throws PositionException, FieldException {
        if(piece == null)throw new FieldException("Piece in the method 'setActPosition' must not be null!");
        int x = pos.xValue(piece.getActPos());
        int y = pos.yValue(piece.getActPos());

        actPosition[x][y] = piece;
    }

    public ChessPiece getPiece(int x, int y) throws FieldException {
        if(actPosition[x][y] == null) throw new FieldException("The position x: " + x + " ,y: " + y + "is empty!");
        return actPosition[x][y];
    }

    public ChessPiece[][] getField(){
        //returns the array
        return actPosition;
    }
}
