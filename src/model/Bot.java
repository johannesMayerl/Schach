package model;

import chessPieces.ChessPiece;
import exceptions.FieldException;
import exceptions.PositionException;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Noah on 18.06.2016.
 */
public class Bot {
    private boolean color;
    private Position pos;
    private ArrayList<ChessPiece> possiblePieces;

    private Bot(){
        this.color = false;
        pos = new Position();
        possiblePieces = new ArrayList<>();
    }

    public boolean validateMove(PlayField field) throws FieldException, PositionException {
        if(field.validMove(chooseEndPosition(), choosePiece(field))){
            return true;
        }else return false;
    }

    public ChessPiece choosePiece(PlayField field){
        for(int i = 0; i < field.getField().length; i++){
            for(int j = 0; j < field.getField()[i].length; j++){
                if(field.getField()[i][j].getColour() == color){
                    possiblePieces.add(field.getField()[i][j]);
                }
            }
        }

        Random rn = new Random();
        int piece = rn.nextInt(possiblePieces.size());

        return possiblePieces.get(piece);
    }

    public String chooseEndPosition(){
        String xy;

        Random xyValue = new Random();
        int xV = xyValue.nextInt(8);
        int yV = xyValue.nextInt(8);

        xy = pos.fromINTtoString(xV,yV);
        return xy;
    }

    public void move(PlayField field) throws FieldException, PositionException {
        if(!validateMove(field)){
            move(field);
        }else{
            field.move(chooseEndPosition(), choosePiece(field));
        }
    }

    public ArrayList<ChessPiece> getPossiblePieces() {
        return possiblePieces;
    }

    public void setPossiblePieces(ArrayList<ChessPiece> possiblePieces) {
        this.possiblePieces = possiblePieces;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }
}
