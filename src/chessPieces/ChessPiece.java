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

    // true for black an false for white
    private boolean colour;
    private boolean movedOnce;

    // like "g5"
    private String actPosition;

    public ChessPiece(boolean color, String actPosition, boolean movedOnce) {
        super();
        this.colour = color;
        this.actPosition = actPosition;
        setMovedOnce(movedOnce);
    }

    public boolean getColour() {

        return colour;
    }

    public void setColour(boolean color) {

        this.colour = color;
    }

    public String getActPos() {
        // e3 = x=3 y=e e = 4
        return actPosition;
    }

    public void setActPos(String actPosition) {
        // e3 = x=3 y=e e = 4
        this.actPosition = actPosition;
    }

    public boolean getMovedOnce() {
        return movedOnce;
    }

    public void setMovedOnce(boolean movedOnce) {
        this.movedOnce = movedOnce;
    }

    @Override
    public String toString() {
        return "ChessPiece{" + "colour=" + colour + ", actPosition='" + actPosition + '\'' + '}';
    }
}
