package model;

import chessPieces.*;
import exceptions.FieldException;
import exceptions.PositionException;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.synth.Region;
import java.awt.*;
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

    private ChessGame cg;

    public PlayField(ChessGame cg) {
        actPosition = new ChessPiece[8][8];
        putPiecesOnStart();
        pos = new Position();

        this.cg = cg;
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
                                if ((yEnd - yStart) == 1 && xEnd == xStart) {
                                    if (getPiece(xEnd, yEnd) == null) {
                                        return true;
                                    } else {
                                        return false;
                                    }
                                } else if ((yEnd - yStart) == 1 && (xEnd - xStart) == -1 && getPiece(xEnd, yEnd) != null) {
                                    if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                        return true;
                                    } else {
                                        return false;
                                    }
                                } else if ((yEnd - yStart) == 1 && (xEnd - xStart) == 1 && getPiece(xEnd, yEnd) != null) {
                                    if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                        return true;
                                    } else {
                                        return false;
                                    }
                                } else if (!piece.getMovedOnce() && checkSpots(piece.getActPos(), end)) {
                                    if ((yEnd - yStart) == 2 && xEnd == xStart) {
                                        if(actPosition[xEnd][yEnd] == null) {
                                            return true;
                                        }else{
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                }
                                return false;
                            } else if (piece.getColour() == true) {
                                if ((yEnd - yStart) == -1 && xEnd == xStart) {
                                    if (getPiece(xEnd, yEnd) == null) {
                                        return true;
                                    } else {
                                        return false;
                                    }
                                } else if ((yEnd - yStart) == -1 && (xEnd - xStart) == -1 && getPiece(xEnd, yEnd) != null) {
                                    if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                        return true;
                                    } else {
                                        return false;
                                    }
                                } else if ((yEnd - yStart) == -1 && (xEnd - xStart) == 1 && getPiece(xEnd, yEnd) != null) {
                                    if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                        return true;
                                    } else {
                                        return false;
                                    }
                                } else if (!piece.getMovedOnce() && checkSpots(piece.getActPos(), end)) {
                                    if ((yEnd - yStart) == -2 && xEnd == xStart) {
                                        if(actPosition[xEnd][yEnd] == null) {
                                            return true;
                                        }else{
                                            return false;
                                        }
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
                                if (getPiece(xEnd, yEnd) != null) {
                                    if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                        return true;
                                    } else {
                                        return false;
                                    }
                                } else {
                                    return true;
                                }
                            } else {
                                return false;
                            }

                        case "Bishop":
                            if (xEnd - xStart == yEnd - yStart) {
                                if (getPiece(xEnd, yEnd) != null) {
                                    if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                        if(checkSpots(piece.getActPos(), end)){
                                            return true;
                                        }else{
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                } else {
                                    if(checkSpots(piece.getActPos(), end)){
                                        return true;
                                    }else{
                                        return false;
                                    }
                                }
                            } else if (xEnd - xStart == -(yEnd - yStart)) {
                                if (getPiece(xEnd, yEnd) != null) {
                                    if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                        if(checkSpots(piece.getActPos(), end)){
                                            return true;
                                        }else{
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                } else {
                                    if(checkSpots(piece.getActPos(), end)){
                                        return true;
                                    }else{
                                        return false;
                                    }
                                }
                            }
                            break;

                        case "Rook":
                            if (xStart == xEnd || yStart == yEnd) {
                                if (getPiece(xEnd, yEnd) != null) {

                                    if((piece instanceof King && actPosition[xEnd][yEnd] instanceof Rook && piece.getColour() == actPosition[xEnd][yEnd].getColour()) ||
                                            (piece instanceof Rook && actPosition[xEnd][yEnd] instanceof King && piece.getColour() == actPosition[xEnd][yEnd].getColour() )){
                                        return true;
                                    }

                                    if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                        if(checkSpots(piece.getActPos(), end)){
                                            return true;
                                        }else{
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                } else {
                                    if(checkSpots(piece.getActPos(), end)){
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
                                if (getPiece(xEnd, yEnd) != null) {
                                    if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                        if(checkSpots(piece.getActPos(), end)){
                                            return true;
                                        }else{
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                } else {
                                    if(checkSpots(piece.getActPos(), end)){
                                        return true;
                                    }else{
                                        return false;
                                    }
                                }
                            } else if (yStart == yEnd) {
                                if (getPiece(xEnd, yEnd) != null) {
                                    if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                        if(checkSpots(piece.getActPos(), end)){
                                            return true;
                                        }else{
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                } else {
                                    if(checkSpots(piece.getActPos(), end)){
                                        return true;
                                    }else{
                                        return false;
                                    }
                                }
                            } else if (xEnd - xStart == yEnd - yStart) {
                                if (getPiece(xEnd, yEnd) != null) {
                                    if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                        if(checkSpots(piece.getActPos(), end)){
                                            return true;
                                        }else{
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                } else {
                                    if(checkSpots(piece.getActPos(), end)){
                                        return true;
                                    }else{
                                        return false;
                                    }
                                }
                            } else if (xEnd - xStart == -(yEnd - yStart)) {
                                if (getPiece(xEnd, yEnd) != null) {
                                    if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                        if(checkSpots(piece.getActPos(), end)){
                                            return true;
                                        }else{
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                } else {
                                    if(checkSpots(piece.getActPos(), end)){
                                        return true;
                                    }else{
                                        return false;
                                    }
                                }
                            }
                        case "King":
                            if((piece instanceof King && actPosition[xEnd][yEnd] instanceof Rook && piece.getColour() == actPosition[xEnd][yEnd].getColour()) ||
                                    (piece instanceof Rook && actPosition[xEnd][yEnd] instanceof King && piece.getColour() == actPosition[xEnd][yEnd].getColour() )){
                               return true;
                            }

                            if (xStart == xEnd) {
                                if (yEnd + 1 == yStart) {
                                    if (getPiece(xEnd, yEnd) != null) {
                                        if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                            return true;
                                        } else {
                                            return false;
                                        }
                                    }else{
                                        return true;
                                    }
                                } else if (yEnd - 1 == yStart) {
                                    if (getPiece(xEnd, yEnd) != null) {
                                        if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                            return true;
                                        } else {
                                            return false;
                                        }
                                    } else {
                                        return true;
                                    }
                                }
                            } else if (yStart == yEnd) {
                                if (xEnd + 1 == xStart) {
                                    if (getPiece(xEnd, yEnd) != null) {
                                        if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                            return true;
                                        } else {
                                            return false;
                                        }
                                    } else {
                                        return true;
                                    }
                                } else if (xEnd - 1 == xStart) {
                                    if (getPiece(xEnd, yEnd) != null) {
                                        if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                            return true;
                                        } else {
                                            return false;
                                        }
                                    } else {
                                        return true;
                                    }
                                }
                            } else if (yEnd + 1 == yStart && xEnd + 1 == xStart) {
                                if (getPiece(xEnd, yEnd) != null) {
                                    if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                        return true;
                                    } else {
                                        return false;
                                    }
                                } else {
                                    return true;
                                }
                            } else if (yEnd - 1 == yStart && xEnd + 1 == xStart) {
                                if (getPiece(xEnd, yEnd) != null) {
                                    if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                        return true;
                                    } else {
                                        return false;
                                    }
                                } else {
                                    return true;
                                }
                            } else if (yEnd - 1 == yStart && xEnd - 1 == xStart) {
                                if (getPiece(xEnd, yEnd) != null) {
                                    if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                        return true;
                                    } else {
                                        return false;
                                    }
                                } else {
                                    return true;
                                }
                            } else if (yEnd + 1 == yStart && xEnd - 1 == xStart) {
                                if (getPiece(xEnd, yEnd) != null) {
                                    if (getPiece(xEnd, yEnd).getColour() != piece.getColour()) {
                                        return true;
                                    } else {
                                        return false;
                                    }
                                } else {
                                    return true;
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
        return false;
    }

    public boolean checkSpots(String start, String end) throws FieldException, PositionException {
        if (start == null)
            throw new FieldException("Start position must not be NULL!");
        if (end == null)
            throw new FieldException("End position must not be NULL!");

        int xStart = pos.xValue(start);
        int yStart = pos.yValue(start);
        int xEnd = pos.xValue(end);
        int yEnd = pos.yValue(end);


        if (!(xEnd - xStart == 1 || xEnd - xStart == -1 && yEnd - yStart == -1 || yEnd - yStart == 1)) {
            if (xStart == xEnd && yStart > yEnd) {
                for (int w = yStart - 1; w >= yEnd + 1; w--) {
                    if (actPosition[xStart][w] != null) {
                        return false;
                    }
                }
                return true;
            } else if (yStart == yEnd && xStart < xEnd) {
                for (int w = xStart + 1; w <= xEnd - 1; w++) {
                    if (actPosition[w][yStart] != null) {
                        return false;
                    }
                }
                return true;
            } else if (xStart == xEnd && yStart < yEnd) {
                for (int w = yStart + 1; w <= yEnd - 1; w++) {
                    if (actPosition[xStart][w] != null) {
                        return false;
                    }
                }
                return true;

            } else if (yStart == yEnd && xStart > xEnd) {
                for (int w = xStart - 1; w >= xEnd + 1; w--) {
                    if (actPosition[w][yStart] != null) {
                        return false;
                    }
                }
                return true;
            } else if (xStart > xEnd && yStart > yEnd) {

                int i = xStart - 1;
                int j = yStart - 1;
                while (i > xEnd + 1 && j >= yEnd + 1) {
                    if (actPosition[i][j] != null) {
                        return false;
                    }
                    i--;
                    j--;
                }
                return true;
            } else if (yStart > yEnd && xStart < xEnd) {
                int i = xStart + 1;
                int j = yStart - 1;
                while (i <= xEnd - 1 && j >= yEnd + 1) {
                    if (actPosition[i][j] != null) {
                        return false;
                    }
                    i++;
                    j--;
                }
                return true;
            } else if (xStart < xEnd && yStart < yEnd) {
                int i = xStart + 1;
                int j = yStart + 1;
                while (i < xEnd  && j < yEnd ) {
                    if (actPosition[i][j] != null) {
                        return false;
                    }
                    i++;
                    j++;
                }
                return true;
            } else if (yStart < yEnd && xStart > xEnd) {
                int i = xStart - 1;
                int j = yStart + 1;
                while (i > xEnd  && j < yEnd ) {
                    if (actPosition[i][j] != null) {
                        return false;
                    }
                    i--;
                    j++;
                }
                return true;
            }
            return false;
        } else {
            return true;
        }
    }

    public void putPiecesOnStart() {
        setColorChange(false);
        setToNULL();

        actPosition[0][1] = new Pawn(false, "a7", false);
        actPosition[1][1] = new Pawn(false, "b7", false);
        actPosition[2][1] = new Pawn(false, "c7", false);
        actPosition[3][1] = new Pawn(false, "d7", false);
        actPosition[4][1] = new Pawn(false, "e7", false);
        actPosition[5][1] = new Pawn(false, "f7", false);
        actPosition[6][1] = new Pawn(false, "g7", false);
        actPosition[7][1] = new Pawn(false, "h7", false);
        actPosition[0][0] = new Rook(false, "a8", false);
        actPosition[7][0] = new Rook(false, "h8", false);
        actPosition[1][0] = new Knight(false, "b8", false);
        actPosition[6][0] = new Knight(false, "g8", false);
        actPosition[2][0] = new Bishop(false, "c8", false);
        actPosition[5][0] = new Bishop(false, "f8", false);
        actPosition[3][0] = new Queen(false, "d8", false);
        actPosition[4][0] = new King(false, "e8", false);


        actPosition[0][6] = new Pawn(true, "a2", false);
        actPosition[1][6] = new Pawn(true, "b2", false);
        actPosition[2][6] = new Pawn(true, "c2", false);
        actPosition[3][6] = new Pawn(true, "d2", false);
        actPosition[4][6] = new Pawn(true, "e2", false);
        actPosition[5][6] = new Pawn(true, "f2", false);
        actPosition[6][6] = new Pawn(true, "g2", false);
        actPosition[7][6] = new Pawn(true, "h2", false);
        actPosition[0][7] = new Rook(true, "a1", false);
        actPosition[7][7] = new Rook(true, "h1", false);
        actPosition[1][7] = new Knight(true, "b1", false);
        actPosition[6][7] = new Knight(true, "g1", false);
        actPosition[2][7] = new Bishop(true, "c1", false);
        actPosition[5][7] = new Bishop(true, "f1", false);
        actPosition[3][7] = new Queen(true, "d1", false);
        actPosition[4][7] = new King(true, "e1", false);
    }

    public void move(String position, ChessPiece piece) throws PositionException, FieldException {
        CHECK:
        if (piece != null) {
            if (position != null) {
                int xEnd = pos.xValue(position);
                int yEnd = pos.yValue(position);

                //game Over situation
                if( actPosition[xEnd][yEnd] instanceof King){
                    System.out.println("Game Over");
                    if(actPosition[xEnd][yEnd].getColour()) {
                        gameOver(true);
                    }else{
                        gameOver(false);
                    }
                    break CHECK;
                }

                if((piece instanceof King && actPosition[xEnd][yEnd] instanceof Rook && piece.getColour() == actPosition[xEnd][yEnd].getColour()) ||
                        (piece instanceof Rook && actPosition[xEnd][yEnd] instanceof King && piece.getColour() == actPosition[xEnd][yEnd].getColour() )){
                    rochade(piece, actPosition[xEnd][yEnd]);
                    setColorChange(!getColorChange());
                    break CHECK;
                }


                actPosition[pos.xValue(piece.getActPos())][pos.yValue(piece.getActPos())] = null;
                actPosition[xEnd][yEnd] = piece;

                piece.setMovedOnce(true);
                setColorChange(!getColorChange());
                piece.setActPos(position);

                //saving last move
                cg.getListOfMovesMade().add(this);
                cg.setLast(+1);

            } else
                throw new FieldException("The position String in the method 'setActualPosition' must not be null!");
        } else
            throw new FieldException("The chess piece in the method 'setActualPosition' must not be null!");
    }

    public ChessPiece getPiece(int x, int y) throws FieldException {
        return actPosition[x][y];
    }

    public ChessPiece[][] getField() {
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

    //noch einbaun
    public void pawnChange(ChessPiece changePiece, String end) throws PositionException {
        int yEnd = pos.yValue(end);
        int xEnd = pos.yValue(end);
        if (changePiece instanceof Queen || changePiece instanceof Rook || changePiece instanceof Bishop || changePiece instanceof Knight) {
            changePiece.setActPos(end);
            actPosition[xEnd][yEnd] = changePiece;

            if (yEnd == 0) {
                changePiece.setColour(false);
            } else if (yEnd == 7) {
                changePiece.setColour(true);
            }
        }


    }
    //als condition für pawnChange
    public boolean pawnChangeCondition(String end) throws PositionException {
        int yEnd = pos.yValue(end);

        if(yEnd == 0 || yEnd == 7 ){
            return true;
        }else return false;
    }

    public boolean rochade(ChessPiece k, ChessPiece r) throws PositionException, FieldException {
        int xRook;
        int xKing;
        int yCommon = pos.yValue(r.getActPos());
        if(k.getColour() == r.getColour()) {
            if (!k.getMovedOnce() && !r.getMovedOnce()) {
                if (k instanceof King && r instanceof Rook) {
                    xRook = pos.xValue(r.getActPos());
                    xKing = pos.xValue(k.getActPos());

                    if(xRook == 7){
                        actPosition[xRook-2][yCommon] = r;
                        r.setMovedOnce(true);
                        actPosition[xRook][yCommon] = null;

                        actPosition[xKing+2][yCommon] = k;
                        k.setMovedOnce(true);
                        actPosition[xKing][yCommon] = null;
                        return true;
                    }else if(xRook == 0){
                        actPosition[xRook+3][yCommon] = r;
                        r.setMovedOnce(true);
                        actPosition[xRook][yCommon] = null;

                        actPosition[xKing-2][yCommon] = k;
                        k.setMovedOnce(true);
                        actPosition[xKing][yCommon] = null;
                        return true;
                    }else{
                        return false;
                    }
                } else if (k instanceof Rook && r instanceof King) {
                    xRook = pos.xValue(k.getActPos());
                    xKing = pos.xValue(r.getActPos());

                    if(xRook == 7){
                        actPosition[xRook-2][yCommon] = k;
                        k.setMovedOnce(true);
                        actPosition[xRook][yCommon] = null;

                        actPosition[xKing+2][yCommon] = r;
                        r.setMovedOnce(true);
                        actPosition[xKing][yCommon] = null;
                        return true;
                    }else if(xRook == 0){
                        actPosition[xRook+3][yCommon] = k;
                        k.setMovedOnce(true);
                        actPosition[xRook][yCommon] = null;

                        actPosition[xKing-2][yCommon] = r;
                        r.setMovedOnce(true);
                        actPosition[xKing][yCommon] = null;
                        return true;
                    }else{
                        return false;
                    }

                }else return false;
            }else return false;
        }else return false;
    }

    public void addPiece(int x, int y, ChessPiece piece){
        actPosition[x][y] = piece;
    }

    public void setToNULL(){
        for(int i = 0; i < actPosition.length; i++){
            for(int j = 0; j < actPosition[i].length; j++){
                actPosition[i][j] = null;
            }
        }
    }


    public void gameOver(boolean b){
        getCg().getMg().gameOver(b);
    }

    public boolean getColorChange() {
        return colorChange;
    }

    public void setColorChange(boolean colorChange) {
        this.colorChange = colorChange;
    }

    public ChessGame getCg() {
        return cg;
    }

    public void setCg(ChessGame cg) {
        this.cg = cg;
    }
}

