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
        System.out.println("Pawn");
    }

    public boolean specialMove(String choose, boolean color, PlayField field) throws FieldException, PositionException {
        // if pawn reaches end
        // player can choose a new chess piece (Queen, Bishop, Knight, Rook)
        int counter = 1;

        switch (choose) {
            case "Queen":

                counter++;
                break;
            case "Rook":

                counter++;
                break;
            case "Knight":

                counter++;
                break;
            case "Bishop":

                counter++;
                break;
        }
        return false;
    }

    @Override
    public String toString() {
        if (getMovedOnce() == true) {
            return "p" + getActPos();
        } else {
            return "P" + getActPos();
        }
    }
}
