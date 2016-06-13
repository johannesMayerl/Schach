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

    private ChessPiece[][] actPosition;
    private Position pos;
    private boolean colorChange;

    public PlayField() {
        // initializes the array
        actPosition = new ChessPiece[8][8];
        putPiecesOnStart();
        pos = new Position();
    }

    public boolean validMove(String end, ChessPiece piece) throws FieldException, PositionException {
        int xStart = pos.xValue(piece.getActPos());
        int yStart = pos.yValue(piece.getActPos());

        int xEnd = pos.xValue(end);
        int yEnd = pos.yValue(end);

        if (piece != null) {
            if (xStart != -999 || xEnd != -999 || yStart != -999 || yEnd != -999) {
                if (getColorChange() == piece.getColour()) {
                    switch (piece.getName()) {
                        case "Pawn":
                            if (piece.getColour() == false) {
                                // checked in theorie
                                if ((yEnd - yStart) == 1 && xEnd == xStart) {
                                    if (getPiece(xEnd, yEnd) == null) {
                                        piece.setMovedOnce(true);
                                        setColorChange(!getColorChange());
                                        return true;
                                    } else {
                                        return false;
                                    }
                                } else if ((yEnd - yStart) == 1 && (xEnd - xStart) == -1 && getPiece(xEnd, yEnd) != null) {
                                    // checked in theorie
                                    if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                        piece.setMovedOnce(true);
                                        setColorChange(!getColorChange());
                                        return true;
                                    } else {
                                        return false;
                                    }
                                } else if ((yEnd - yStart) == 1 && (xEnd - xStart) == 1 && getPiece(xEnd, yEnd) != null) {
                                    // checked in theorie
                                    if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                        piece.setMovedOnce(true);
                                        setColorChange(!getColorChange());
                                        return true;
                                    } else {
                                        return false;
                                    }
                                } else if (!piece.getMovedOnce() && checkSpots(piece.getActPos(), end)) {
                                    // checked in theorie
                                    if ((yEnd - yStart) == 2 && xEnd == xStart) {
                                        piece.setMovedOnce(true);
                                        setColorChange(!getColorChange());
                                        return true;
                                    } else {
                                        return false;
                                    }

                                }
                                return false;
                            } else if (piece.getColour() == true) {
                                // check move of white
                                if ((yEnd - yStart) == -1 && xEnd == xStart) {
                                    // checked in theorie
                                    if (getPiece(xEnd, yEnd) == null) {
                                        piece.setMovedOnce(true);
                                        setColorChange(!getColorChange());
                                        return true;
                                    } else {
                                        return false;
                                    }
                                } else if ((yEnd - yStart) == -1 && (xEnd - xStart) == -1 && getPiece(xEnd, yEnd) != null) {
                                    // checked in theorie
                                    if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                        piece.setMovedOnce(true);
                                        setColorChange(!getColorChange());
                                        return true;
                                    } else {
                                        return false;
                                    }
                                } else if ((yEnd - yStart) == -1 && (xEnd - xStart) == 1 && getPiece(xEnd, yEnd) != null) {
                                    // checked in theorie
                                    if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                        piece.setMovedOnce(true);
                                        setColorChange(!getColorChange());
                                        return true;
                                    } else {
                                        return false;
                                    }
                                } else if (!piece.getMovedOnce() && checkSpots(piece.getActPos(), end)) {
                                    // checked in theorie
                                    if ((yEnd - yStart) == -2 && xEnd == xStart) {
                                        // two fields down
                                        piece.setMovedOnce(true);
                                        setColorChange(!getColorChange());
                                        return true;
                                    } else {
                                        return false;
                                    }
                                } else {
                                    return false;
                                }
                            }

                        case "Knight":
                            if (xEnd + 2 == xStart && yEnd + 1 == yStart || xEnd + 2 == xStart && yEnd - 1 == yStart
                                    || xEnd - 2 == xStart && yEnd + 1 == yStart || xEnd - 2 == xStart && yEnd - 1 == yStart
                                    || xEnd + 1 == xStart && yEnd + 2 == yStart || xEnd + 1 == xStart && yEnd - 2 == yStart
                                    || xEnd - 1 == xStart && yEnd - 2 == yStart || xEnd - 1 == xStart && yEnd + 2 == yStart) {
                                // checked in theorie
                                if (getPiece(xEnd, yEnd) != null) {
                                    if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                        piece.setMovedOnce(true);
                                        setColorChange(!getColorChange());
                                        return true;
                                    } else {
                                        return false;
                                    }
                                } else {
                                    piece.setMovedOnce(true);
                                    setColorChange(!getColorChange());
                                    return true;
                                }
                            } else {
                                return false;
                            }


                        case "Bishop":
                            if (xEnd - xStart == yEnd - yStart) {
                                // checked in theorie
                                if (getPiece(xEnd, yEnd) != null) {
                                    if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                        if(checkSpots(piece.getActPos(), end)){
                                            setColorChange(!getColorChange());
                                            return true;
                                        }else{
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                } else {
                                    if(checkSpots(piece.getActPos(), end)){
                                        setColorChange(!getColorChange());
                                        return true;
                                    }else{
                                        return false;
                                    }
                                }
                            } else if (xEnd - xStart == -(yEnd - yStart)) {
                                // checked in theorie
                                if (getPiece(xEnd, yEnd) != null) {
                                    if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                        if(checkSpots(piece.getActPos(), end)){
                                            setColorChange(!getColorChange());
                                            return true;
                                        }else{
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                } else {
                                    if(checkSpots(piece.getActPos(), end)){
                                        setColorChange(!getColorChange());
                                        return true;
                                    }else{
                                        return false;
                                    }
                                }
                            }
                            break;

                        case "Rook":
                            if (xStart == xEnd || yStart == yEnd) {
                                // checked in theorie
                                if (getPiece(xEnd, yEnd) != null) {
                                    if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                        if(checkSpots(piece.getActPos(), end)){
                                            setColorChange(!getColorChange());
                                            return true;
                                        }else{
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                } else {
                                    if(checkSpots(piece.getActPos(), end)){
                                        setColorChange(!getColorChange());
                                        return true;
                                    }else{
                                        return false;
                                    }
                                }
                            } else {
                                return false;
                            }

                        case "Queen":
                            if (xStart == xEnd) {
                                // checked in theorie
                                if (getPiece(xEnd, yEnd) != null) {
                                    if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                        if(checkSpots(piece.getActPos(), end)){
                                            setColorChange(!getColorChange());
                                            return true;
                                        }else{
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                } else {
                                    if(checkSpots(piece.getActPos(), end)){
                                        setColorChange(!getColorChange());
                                        return true;
                                    }else{
                                        return false;
                                    }
                                }
                            } else if (yStart == yEnd) {
                                // checked in theorie
                                if (getPiece(xEnd, yEnd) != null) {
                                    if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                        if(checkSpots(piece.getActPos(), end)){
                                            setColorChange(!getColorChange());
                                            return true;
                                        }else{
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                } else {
                                    if(checkSpots(piece.getActPos(), end)){
                                        setColorChange(!getColorChange());
                                        return true;
                                    }else{
                                        return false;
                                    }
                                }
                            } else if (xEnd - xStart == yEnd - yStart) {
                                // checked in theorie
                                if (getPiece(xEnd, yEnd) != null) {
                                    if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                        if(checkSpots(piece.getActPos(), end)){
                                            setColorChange(!getColorChange());
                                            return true;
                                        }else{
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                } else {
                                    if(checkSpots(piece.getActPos(), end)){
                                        setColorChange(!getColorChange());
                                        return true;
                                    }else{
                                        return false;
                                    }
                                }
                            } else if (xEnd - xStart == -(yEnd - yStart)) {
                                // checked in theorie
                                if (getPiece(xEnd, yEnd) != null) {
                                    if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                        if(checkSpots(piece.getActPos(), end)){
                                            setColorChange(!getColorChange());
                                            return true;
                                        }else{
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                } else {
                                    if(checkSpots(piece.getActPos(), end)){
                                        setColorChange(!getColorChange());
                                        return true;
                                    }else{
                                        return false;
                                    }
                                }
                            }
                        case "King":
                            if (xStart == xEnd) {
                                if (yEnd + 1 == yStart) {
                                    // checked in theorie
                                    if (getPiece(xEnd, yEnd) != null) {
                                        if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                            piece.setMovedOnce(true);
                                            return true;
                                        } else {
                                            return false;
                                        }
                                    }
                                } else if (yEnd - 1 == yStart) {
                                    // check in theorie
                                    if (getPiece(xEnd, yEnd) != null) {
                                        if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                            piece.setMovedOnce(true);
                                            return true;
                                        } else {
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                }
                            } else if (yStart == yEnd) {
                                //check in theorie
                                if (xEnd + 1 == xStart) {
                                    if (getPiece(xEnd, yEnd) != null) {
                                        if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                            piece.setMovedOnce(true);
                                            return true;
                                        } else {
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                } else if (xEnd - 1 == xStart) {
                                    if (getPiece(xEnd, yEnd) != null) {
                                        if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                            piece.setMovedOnce(true);
                                            return true;
                                        } else {
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                }
                            } else if (yEnd + 1 == yStart && xEnd + 1 == xStart) {
                                //check in theorie
                                if (getPiece(xEnd, yEnd) != null) {
                                    if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                        piece.setMovedOnce(true);
                                        return true;
                                    } else {
                                        return false;
                                    }
                                } else {
                                    return false;
                                }
                            } else if (yEnd - 1 == yStart && xEnd + 1 == xStart) {
                                //check in theorie
                                if (getPiece(xEnd, yEnd) != null) {
                                    if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                        piece.setMovedOnce(true);
                                        return true;
                                    } else {
                                        return false;
                                    }
                                } else {
                                    return false;
                                }
                            } else if (yEnd - 1 == yStart && xEnd - 1 == xStart) {
                                //check in theorie
                                if (getPiece(xEnd, yEnd) != null) {
                                    if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                        piece.setMovedOnce(true);
                                        return true;
                                    } else {
                                        return false;
                                    }
                                } else {
                                    return false;
                                }
                            } else if (yEnd + 1 == yStart && xEnd - 1 == xStart) {
                                //check in theorie
                                if (getPiece(xEnd, yEnd) != null) {
                                    if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                        piece.setMovedOnce(true);
                                        return true;
                                    } else {
                                        return false;
                                    }
                                } else {
                                    return false;
                                }
                            } else {
                                return false;
                            }
                    }
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }
        System.out.println("Last false statement was used");
        return false;
    }

    public boolean checkSpots(String start, String end) throws FieldException, PositionException {
        if (start == null)
            throw new FieldException("Start position must not be NULL!");
        if (end == null)
            throw new FieldException("End position must not be NULL!");

        int xStart = pos.xValue(start);
        System.out.println("X-START: " + xStart);

        int yStart = pos.yValue(start);
        System.out.println("y-START: " + yStart);

        int xEnd = pos.xValue(end);
        System.out.println("X-END: " + xEnd);

        int yEnd = pos.yValue(end);
        System.out.println("Y-END: " + yEnd);

        if (!(xEnd - xStart == 1 || xEnd - xStart == -1 && yEnd - yStart == -1 || yEnd - yStart == 1)) {
            if (xStart == xEnd && yStart > yEnd) {
                // just to the top __CHECKED (theoretically)
                System.out.println("X Werte sind gleich, y start wert ist größer als y end wert");
                for (int w = yStart - 1; w >= yEnd + 1; w--) {
                    if (actPosition[xStart][w] != null) {
                        System.out.println("X: " + xStart + "Y: " + w + " is occupied");
                        return false;
                    } else {
                        System.out.println("X: " + w + "Y: " + yStart + " is not occupied");
                    }
                }
                return true;
            } else if (yStart == yEnd && xStart < xEnd) {
                // just to the right __CHECKED (theoretically)
                System.out.println("Y werte Sind gleich, x start wert ist kleiner als x end wert");
                for (int w = xStart + 1; w <= xEnd - 1; w++) {
                    if (actPosition[w][yStart] != null) {
                        System.out.println("X: " + w + "Y: " + yStart + " is occupied");
                        return false;
                    } else {
                        System.out.println("X: " + w + "Y: " + yStart + " is not occupied");
                    }
                }
                return true;
            } else if (xStart == xEnd && yStart < yEnd) {
                // just the bottom __CHECKED (theoretically)
                System.out.println("X werte Sind gleich, y start wert ist kleiner als y end wert");
                for (int w = yStart + 1; w <= yEnd - 1; w++) {
                    if (actPosition[xStart][w] != null) {
                        System.out.println("X: " + xStart + "Y: " + w + " is occupied");
                        return false;
                    } else {
                        System.out.println("X: " + xStart + "Y: " + w + " is not occupied");
                    }
                }
                return true;

            } else if (yStart == yEnd && xStart > xEnd) {
                // just to the left __CHECKED (theoretically)
                System.out.println("Y Werte sind gleich, x Start Wert ist größer als x End Wert");
                for (int w = xStart - 1; w >= xEnd + 1; w--) {
                    if (actPosition[w][yStart] != null) {
                        System.out.println("X: " + w + "Y: " + yStart + " is occupied");
                        return false;
                    } else {
                        System.out.println("X: " + w + "Y: " + yStart + " is not occupied");
                    }
                }
                return true;
            } else if (xStart > xEnd && yStart > yEnd) {
                // to the top and to the left __CHECKED (theoretically)
                System.out.println("xStart > xEnd && yStart > yEnd");
                int i = xStart - 1;
                int j = yStart - 1;
                while (i >= xEnd + 1 && j >= yEnd + 1) {
                    if (actPosition[i][j] != null) {
                        System.out.println("X: " + i + "Y: " + j + " is occupied");
                        return false;
                    } else {
                        System.out.println("X: " + i + "Y: " + j + " is not occupied");
                    }
                    i--;
                    j--;
                }
                return true;
            } else if (yStart > yEnd && xStart < xEnd) {
                // to the right and to the top __CHECKED (theoretically)
                System.out.println("xStart > xEnd && yStart < yEnd");
                int i = xStart + 1;
                int j = yStart - 1;
                while (i <= xEnd - 1 && j >= yEnd + 1) {
                    if (actPosition[i][j] != null) {
                        System.out.println("X: " + i + "Y: " + j + " is occupied");
                        return false;
                    } else {
                        System.out.println("X: " + i + "Y: " + j + " is not occupied");
                    }
                    i++;
                    j--;
                }
                return true;
            } else if (xStart < xEnd && yStart < yEnd) {
                // to the bottom and to the right __CHECKED (theoretically)
                System.out.println("xStart < xEnd && yStart < yEnd");
                int i = xStart + 1;
                int j = yStart + 1;
                while (i <= xEnd - 1 && j >= yEnd - 1) {
                    if (actPosition[i][j] != null) {
                        System.out.println("X: " + i + "Y: " + j + " is occupied");
                        return false;
                    } else {
                        System.out.println("X: " + i + "Y: " + j + " is not occupied");
                    }
                    i++;
                    j++;
                }
                return true;
            } else if (yStart < yEnd && xStart > xEnd) {
                // to the bottom and to the left __CHECKED (theoretically)
                System.out.println("xStart < xEnd && yStart > yEnd");
                int i = xStart - 1;
                int j = yStart + 1;
                while (i >= xEnd + 1 && j >= yEnd - 1) {
                    if (actPosition[i][j] != null) {
                        System.out.println("X: " + i + "Y: " + j + " is occupied");
                        return false;
                    } else {
                        System.out.println("X: " + i + "Y: " + j + " is not occupied");
                    }
                    i--;
                    j++;
                }
                return true;
            }
            System.out.println("Did not move");
            return false;
        } else {
            return true;
        }
    }

    public void putPiecesOnStart() {

        for (int i = 0; i < actPosition.length; i++) {
            for (int j = 0; j < actPosition[i].length; j++) {
                actPosition[i][j] = null;
            }
        }

        // Pawn_black
        actPosition[0][1] = new Pawn(false, "a7", false);
        actPosition[1][1] = new Pawn(false, "b7", false);
        actPosition[2][1] = new Pawn(false, "c7", false);
        actPosition[3][1] = new Pawn(false, "d7", false);
        actPosition[4][1] = new Pawn(false, "e7", false);
        actPosition[5][1] = new Pawn(false, "f7", false);
        actPosition[6][1] = new Pawn(false, "g7", false);
        actPosition[7][1] = new Pawn(false, "h7", false);
        // Rook_black
        actPosition[0][0] = new Rook(false, "a8", false);
        actPosition[7][0] = new Rook(false, "h8", false);
        // Knight_black
        actPosition[1][0] = new Knight(false, "b8", false);
        actPosition[6][0] = new Knight(false, "g8", false);
        // Bishop_black
        actPosition[2][0] = new Bishop(false, "c8", false);
        actPosition[5][0] = new Bishop(false, "f8", false);
        // Queen_black
        actPosition[3][0] = new Queen(false, "d8", false);
        // King_black
        actPosition[4][0] = new King(false, "e8", false);

        // Pawn_white
        actPosition[0][6] = new Pawn(true, "a2", false);
        actPosition[1][6] = new Pawn(true, "b2", false);
        actPosition[2][6] = new Pawn(true, "c2", false);
        actPosition[3][6] = new Pawn(true, "d2", false);
        actPosition[4][6] = new Pawn(true, "e2", false);
        actPosition[5][6] = new Pawn(true, "f2", false);
        actPosition[6][6] = new Pawn(true, "g2", false);
        actPosition[7][6] = new Pawn(true, "h2", false);
        // Rook_white
        actPosition[0][7] = new Rook(true, "a1", false);
        actPosition[7][7] = new Rook(true, "h1", false);
        // Knight_white
        actPosition[1][7] = new Knight(true, "b1", false);
        actPosition[6][7] = new Knight(true, "g1", false);
        // Bishop_white
        actPosition[2][7] = new Bishop(true, "c1", false);
        actPosition[5][7] = new Bishop(true, "f1", false);
        // Queen_white
        actPosition[3][7] = new Queen(true, "d1", false);
        // King_whit
        actPosition[4][7] = new King(true, "e1", false);
    }

    public void move(String position, ChessPiece piece) throws PositionException, FieldException {
        if (piece != null) {
            if (position != null) {
                int xEnd = pos.xValue(position);
                int yEnd = pos.yValue(position);

                actPosition[pos.xValue(piece.getActPos())][pos.yValue(piece.getActPos())] = null;
                actPosition[xEnd][yEnd] = piece;

                piece.setActPos(position);
            } else
                throw new FieldException("The position String in the method 'setActualPosition' must not be null!");
        } else
            throw new FieldException("The chess piece in the method 'setActualPosition' must not be null!");
    }

    public ChessPiece getPiece(int x, int y) throws FieldException {
        return actPosition[x][y];
    }

    public ChessPiece[][] getField() {
        // returns the array
        return actPosition;
    }

    public void printArray() throws FieldException {
        if(actPosition == null)throw new FieldException("The Array must not be null!");
        for(int i = 0; i < actPosition.length; i++){
            for(int j = 0; j < actPosition.length; j++){
                if(j == 7){
                    System.out.print(actPosition[i][j] + "\n");
                }else {
                    System.out.print(actPosition[i][j]);
                }
            }
        }
    }

    public boolean getColorChange() {
        return colorChange;
    }

    public void setColorChange(boolean colorChange) {
        this.colorChange = colorChange;
    }
}

/*
 * System.out.println("Bishop block"); System.out.println("Yend: " + yEnd);
 * System.out.println("Ystart: " + yStart); System.out.println("Xend: " + xEnd);
 * System.out.println("Xstart: " + xStart);
 *
 */
