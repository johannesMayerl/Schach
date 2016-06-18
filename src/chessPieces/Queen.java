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

    public Queen(boolean color, String start, boolean movedOnce) {
        super(color, start, movedOnce);
        setName("Queen");
    }

    @Override
    public String toString() {

        return "Q"+ "/" + getActPos()+ "/";
    }

}
