package chessPieces;

import exceptions.ChessPieceException;
import exceptions.FieldException;
import exceptions.PositionException;
import model.PlayField;
import model.Position;

/**
 * Created by Noah on 29.04.2016.
 */
public class Pawn extends ChessPiece {

    public Pawn(boolean color, String start, boolean movedOnce) {
        super(color, start, movedOnce);
        setName("Pawn");
    }

    @Override
    public String toString() {
        if (getMovedOnce() == true) {
            return "p"+ "/" + getActPos()+ "/";
        } else {
            return "P"+ "/" + getActPos()+ "/";
        }
    }
}
