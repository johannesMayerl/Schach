package chessPieces;

import exceptions.ChessPieceException;
import exceptions.FieldException;
import exceptions.PositionException;
import model.PlayField;
import model.Position;

/**
 * Created by Noah on 29.04.2016.
 */
public class King extends ChessPiece {

    public King(boolean color, String start, boolean movedOnce) {
        super(color, start, movedOnce);
        System.out.println("King");
    }

    public boolean specialMove(King k, Knight kn) throws ChessPieceException {
        if (k == null || kn == null)
            throw new ChessPieceException("King and Knight must not be NULL!");

        return true;
    }

    @Override
    public String toString() {
        if (getMovedOnce() == true) {
            return "k" + getActPos();
        } else {
            return "K" + getActPos();
        }
    }

}
