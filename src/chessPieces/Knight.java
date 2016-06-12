package chessPieces;

import exceptions.ChessPieceException;
import exceptions.FieldException;
import exceptions.PositionException;
import model.PlayField;
import model.Position;

/**
 * Created by Noah on 29.04.2016.
 */
public class Knight extends ChessPiece{


    private Position posXY;
    //moved once important for the special move with the king
    private boolean movedOnce;

    public Knight(boolean color, String start, boolean movedOnce) {
        super(color, start);
        posXY = new Position();
        setMovedOnce(movedOnce);
        System.out.println("Knight");
    }

    public boolean move (String endPosition, PlayField field, Knight kt) throws FieldException, ChessPieceException, PositionException {
        if(kt == null)throw new ChessPieceException("Knight must not be null!");
        if(field == null)throw new FieldException("Chessfield must not be null!");

        int xStart = posXY.xValue(field.getActPositionOf(kt));
        int yStart = posXY.yValue(field.getActPositionOf(kt));

        int xEnd =   posXY.xValue(endPosition);
        int yEnd =   posXY.yValue(endPosition);

        if(super.move(endPosition, field, kt) == false){
            return false;
        }
        else if(xEnd+2 == xStart && yEnd+1 == yStart || xEnd+2 == xStart && yEnd-1 == yStart ||
                xEnd-2 == xStart && yEnd+1 == yStart ||xEnd-2 == xStart && yEnd-1 == yStart ||
                xEnd+1 == xStart && yEnd+2 == yStart ||xEnd+1 == xStart && yEnd-2 == yStart ||
                xEnd-1 == xStart && yEnd-2 == yStart ||xEnd-1== xStart && yEnd+2 == yStart ){
                setMovedOnce(true);
                return true;

        }
        return false;
    }

    public boolean specialMove(King k, Knight kn) throws ChessPieceException {
        if(k == null || kn == null) throw new ChessPieceException("King and Knight must not be NULL!");


        return false;
    }

    public boolean isMovedOnce() {
        return movedOnce;
    }

    public void setMovedOnce(boolean movedOnce) {
        this.movedOnce = movedOnce;
    }

    @Override
    public String toString()
    {
        if(isMovedOnce() == true) {
            return "g" + getActPos();
        }else{
            return "G" + getActPos();
        }
    }
}
