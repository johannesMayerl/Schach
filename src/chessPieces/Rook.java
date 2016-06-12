package chessPieces;

import exceptions.ChessPieceException;
import exceptions.FieldException;
import exceptions.PositionException;
import model.PlayField;
import model.Position;

/**
 * Created by Noah on 29.04.2016.
 */
public class Rook extends ChessPiece{
    private Position posXY;

    public Rook(boolean color, String start) {
        super(color, start);
        posXY = new Position();
        System.out.println("Rook");
    }

    public boolean move (String endPosition, PlayField field, Rook r) throws FieldException, ChessPieceException, PositionException {
        if(r == null)throw new ChessPieceException("Rook must not be null!");
        if(field == null)throw new FieldException("Chess field must not be null!");

        int xStart = posXY.xValue(field.getActPositionOf(this));
        int yStart = posXY.yValue(field.getActPositionOf(this));

        int xEnd =   posXY.xValue(endPosition);
        int yEnd =   posXY.yValue(endPosition);
        if(super.move(endPosition, field, r) == false){
            return false;
        }
        else if(xStart == xEnd){
            if(field.checkSpots(getActPos(), endPosition)) {
                return true;
            }
        }else if(yStart == yEnd){
            if(field.checkSpots(getActPos(), endPosition)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {

        return "R" + getActPos();
    }
}
