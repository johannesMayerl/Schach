package chessPieces;

import exceptions.ChessPieceException;
import exceptions.FieldException;
import exceptions.PositionException;
import model.PlayField;
import model.Position;

/**
 * Created by Noah on 29.04.2016.
 */

public abstract class ChessPiece {

    //true for black an false for white
    private boolean available;

    //like "g5"
    private String startPos;
    private Position posXY;

    public ChessPiece(boolean available, String startPos) {
        super();
        this.available = available;
        this.startPos = startPos;
        posXY = new Position();
    }

    public boolean move(String endPos, PlayField field, ChessPiece piece) throws ChessPieceException, FieldException, PositionException {
        int toX;
        int toY;
        int fromX;
        int fromY;

        if(endPos != null && piece != null) {
            toX = posXY.xValue(endPos);
            toY = posXY.yValue(endPos);
            fromX = posXY.xValue(field.getActPositionOf(piece));
            fromY = posXY.yValue(field.getActPositionOf(piece));
        } else throw new ChessPieceException("Check if the endPos String or and the ChessPiece Piece is not null! Exception thrown in ChessPiece class");

        if(fromX == toX || fromY == toY)
            return false;
        else if(toX > 8 || toX < 1 || fromX > 8 || fromX < 1 || toY < 1 || toY > 8 || fromY > 8 || fromY < 1)
            return false;
        else
            return true;
    }

    public boolean getAvailable() {

        return available;
    }

    public void setAvailable(boolean color) {

        this.available = color;
    }

    public String getActPos() {
        //e3 = x=3 y=e  e = 4
        return startPos;
    }

    public void setActPos(String startPos) {
        //e3 = x=3 y=e  e = 4
        this.startPos = startPos;
    }

    @Override
    public String toString() {
        return "ChessPiece{" +
                "available=" + available +
                ", startPos='" + startPos + '\'' +
                '}';
    }
}
