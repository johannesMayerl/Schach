package chessPieces;

import exceptions.ChessPieceException;
import exceptions.FieldException;
import exceptions.PositionException;
import model.PlayField;
import model.Position;

/**
 * Created by Noah on 29.04.2016.
 */
public class Queen extends ChessPiece {
    private Position posXY;

    public Queen(boolean color, String start) {
        super(color, start);
        posXY = new Position();
        System.out.println("Queen");
    }

    public boolean move(String endPosition, PlayField field, Queen q) throws FieldException, ChessPieceException, PositionException {
        if(q == null)throw new ChessPieceException("Queen must not be null!");
        if(field == null)throw new FieldException("Chess field must not be nul!");

        int xStart = posXY.xValue(field.getActPositionOf(q));
        int yStart = posXY.yValue(field.getActPositionOf(q));

        int xEnd = posXY.xValue(endPosition);
        int yEnd = posXY.yValue(endPosition);

        //Queen can move in any direction, but she cant jump over someone
        if(super.move(endPosition, field, q) == false){
            return false;
        }
        else if(xEnd-xStart == yEnd-yStart || xStart == xEnd || yStart == yEnd) {
            if(field.checkSpots(getActPos(), endPosition) == true) {
                return true;
            }
        }

            return false;
        }

        @Override
        public String toString () {

            return "Q" + getActPos() ;
        }
    }

