package chessPieces;

import exceptions.ChessPieceException;
import exceptions.FieldException;
import exceptions.PositionException;
import model.PlayField;
import model.Position;

/**
 * Created by Noah on 29.04.2016.
 */
public class Rook extends ChessPiece {

    public Rook(boolean color, String start, boolean movedOnce) {
        super(color, start, movedOnce);
        setName("Rook");
    }

    @Override
    public String toString() {

        return "R"+ "/" + getActPos()+ "/";
    }
}
