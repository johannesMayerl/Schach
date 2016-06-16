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
    }

    @Override
    public String toString() {
        return "G"+ "/" + getActPos()+ "/";
    }
}
