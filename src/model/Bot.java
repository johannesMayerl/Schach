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
    private ChessPiece chosenPiece;
    private String chosenEND;

    private ArrayList<ChessPiece> possiblePieces;

    private Bot(PlayField field){
        this.color = false;
        pos = new Position();
        possiblePieces = new ArrayList<>();

        for(int i = 0; i < field.getField().length; i++){
            for(int j = 0; j < field.getField()[i].length; j++){
                if(field.getField()[i][j].getColour() == color){
                    possiblePieces.add(field.getField()[i][j]);
                }
            }
        }
    }

    public boolean validateMove(PlayField field) throws FieldException, PositionException {
        return  field.validMove(chooseEndPosition(),choosePiece());
    }

    public ChessPiece choosePiece(){
        Random rn = new Random();
        int piece = rn.nextInt(possiblePieces.size());
        setChosenPiece(possiblePieces.get(piece));

        return possiblePieces.get(piece);
    }

    public String chooseEndPosition(){
        String xy;
        Random xyValue = new Random();

        int xV = xyValue.nextInt(8);
        int yV = xyValue.nextInt(8);

        xy = pos.fromINTtoString(xV,yV);
        setChosenEND(xy);
        return xy;
    }

    public void moveBot(PlayField field) throws FieldException, PositionException {
        if(!validateMove(field)){
            moveBot(field);
        }else{
            field.move(getChosenEND(), getChosenPiece());
        }
    }

    public ChessPiece calculatePiece(PlayField field) throws FieldException, PositionException {
        if(field.validMove(getChosenEND(), getChosenPiece())){
            return  getChosenPiece();
        }else if(!field.validMove(getChosenEND(), getChosenPiece())){
            for(int i = 0; i < 64; i++){
                chooseEndPosition();
                if(field.validMove(getChosenEND(), getChosenPiece())){
                    return getChosenPiece();
                }
            }
        }else {
            choosePiece();
            calculatePiece(field);
        }
        return null;
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

    public ChessPiece getChosenPiece() {
        return chosenPiece;
    }

    public void setChosenPiece(ChessPiece chosenPiece) {
        this.chosenPiece = chosenPiece;
    }

    public String getChosenEND() {
        return chosenEND;
    }

    public void setChosenEND(String chosenEND) {
        this.chosenEND = chosenEND;
    }
}
