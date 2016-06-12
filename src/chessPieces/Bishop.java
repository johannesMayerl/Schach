package chessPieces;

import exceptions.ChessPieceException;
import exceptions.FieldException;
import exceptions.PositionException;
import model.PlayField;
import model.Position;

/**
 * Created by Noah on 29.04.2016.
 */
public class Bishop extends ChessPiece {

    private Position posXY;

    public Bishop(boolean color, String start) {
        super(color, start);
        posXY = new Position();
        System.out.println("Bishop");
    }

    public boolean move(String endPosition, PlayField field, Bishop b) throws FieldException, ChessPieceException, PositionException {
        if(b == null) throw new ChessPieceException("Bishop must not be null!");
        if(field == null) throw new FieldException("Chess field must not be null!");

        int xStart = posXY.xValue(field.getActPositionOf(b));
        int yStart = posXY.yValue(field.getActPositionOf(b));

        int xEnd =   posXY.xValue(endPosition);
        int yEnd =   posXY.yValue(endPosition);

        if(super.move(endPosition, field, b) == false){
            return false;
        } else if (yStart-yEnd == xStart-xEnd){
            if(field.checkSpots(getActPos(), endPosition) == true) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString(){

        return "B" + getActPos();
    }
}
