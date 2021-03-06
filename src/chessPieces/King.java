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
        setName("King");
    }

    @Override
    public String toString() {
        if (getMovedOnce() == true) {
            return "k" + "/" + getActPos()+ "/";
        } else {
            return "K"+ "/" + getActPos()+ "/";
        }
    }
}
