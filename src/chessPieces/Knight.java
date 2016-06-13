package chessPieces;

import exceptions.ChessPieceException;
import exceptions.FieldException;
import exceptions.PositionException;
import model.PlayField;
import model.Position;

/**
 * Created by Noah on 29.04.2016.
 */
public class Knight extends ChessPiece {

    public Knight(boolean color, String start, boolean movedOnce) {
        super(color, start, movedOnce);
        setName("Knight");
        System.out.println("Knight");
    }

    public boolean specialMove(King k, Knight kn) throws ChessPieceException {
        if (k == null || kn == null)
            throw new ChessPieceException("King and Knight must not be NULL!");

        return false;
    }

    @Override
    public String toString() {
        if (getMovedOnce() == true) {
            return "g" + getActPos();
        } else {
            return "G" + getActPos();
        }
    }
}
