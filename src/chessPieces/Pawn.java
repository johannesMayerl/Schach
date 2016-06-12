package chessPieces;

import exceptions.ChessPieceException;
import exceptions.FieldException;
import exceptions.PositionException;
import model.PlayField;
import model.Position;

/**
 * Created by Noah on 29.04.2016.
 */
public class Pawn extends ChessPiece{
    private Position posXY;
    private boolean movedOnce;

    public Pawn(boolean color, String start, boolean movedOnce) {
        super(color, start);
        posXY = new Position();
        setMovedOnce(movedOnce);
        System.out.println("Pawn");
    }

    public boolean moveWhite (String endPosition, PlayField field, Pawn p) throws FieldException, ChessPieceException, PositionException {
        if(p == null)throw new ChessPieceException("Pawn must not be null!");
        if(field == null) throw new FieldException("Chess field must not be null!");
        if(endPosition == null) throw new FieldException("String endPosition must not bew null!");

        int xStart = posXY.xValue(field.getActPositionOf(p));
        int yStart = posXY.yValue(field.getActPositionOf(p));
        int xEnd =   posXY.xValue(endPosition);
        int yEnd =   posXY.yValue(endPosition);

            if(super.move(endPosition, field, p) == false){
                    return false;
            } else if((xEnd-xStart) == 1 && (yEnd-yStart) == 0) {
                    setMovedOnce(true);
                    return true;
            } else if ((xEnd - xStart) == 1 && (yEnd - yStart) == 1 && field.getPiece(xEnd, yEnd) != null) {
                    setMovedOnce(true);
                    return true;
            }else if ((xEnd - xStart) == 1 && (yStart - yEnd) == -1 && field.getPiece(xEnd, yEnd) != null) {
                    setMovedOnce(true);
                    return true;
            }else if(getMovedOnce()== false && field.checkSpots(getActPos(), endPosition) == true){
                if((xEnd - xStart) == 2 && (yEnd-yStart) == 0){
                    setMovedOnce(true);
                    return true;
                }
            }
        return false;
    }

    public boolean moveBlack (String endPosition, PlayField field, Pawn p) throws FieldException, ChessPieceException, PositionException {
        if(p == null)throw new ChessPieceException("Pawn must not be null!");
        if(field == null) throw new FieldException("Chess field must not be null!");
        if(endPosition == null) throw new FieldException("String endPosition must not bew null!");

        int xStart = posXY.xValue(field.getActPositionOf(p));
        int yStart = posXY.yValue(field.getActPositionOf(p));
        int xEnd =   posXY.xValue(endPosition);
        int yEnd =   posXY.yValue(endPosition);

        if(super.move(endPosition, field, p) == false){
            return false;
        } else if((xEnd-xStart) == -1 && (yEnd-yStart) == 0) {
            setMovedOnce(true);
            return true;
        } else if ((xEnd - xStart) == -1 && (yEnd - yStart) == -1 && field.getPiece(xEnd, yEnd) != null) {
            setMovedOnce(true);
            return true;
        }else if ((xEnd - xStart) == -1 && (yStart - yEnd) == 1 && field.getPiece(xEnd, yEnd) != null) {
            setMovedOnce(true);
            return true;
        }else if(getMovedOnce()== false && field.checkSpots(getActPos(), endPosition) == true){
            if((xEnd - xStart) == -2 && (yEnd-yStart) == 0){
                setMovedOnce(true);
                return true;
            }
        }
        return false;
    }

    public boolean specialMove(String choose, boolean color, PlayField field) throws FieldException, PositionException {
        //if pawn reaches end
        //player can choose a new chess piece (Queen, Bishop, Knight, Rook)
        int counter = 1;

        switch (choose){
            case "Queen":

                counter++;
                break;
            case "Rook":

                counter++;
                break;
            case "Knight":

                counter++;
                break;
            case "Bishop":

                counter++;
                break;
        }
        return false;
    }

    public boolean getMovedOnce() {
        return movedOnce;
    }

    public void setMovedOnce(boolean movedOnce){
        this.movedOnce = movedOnce;
    }

    @Override
    public String toString() {
        if(getMovedOnce() == true) {
            return "p" + getActPos();
        }else{
            return "P" + getActPos();
        }
    }
}
