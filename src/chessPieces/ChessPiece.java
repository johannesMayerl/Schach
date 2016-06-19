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

    private String start;
    private boolean colour;
    private boolean movedOnce;
    private String name;

    //game over
    private boolean gameOver;

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
        return actPosition;
    }

    public void setActPos(String actPosition) {
        this.actPosition = actPosition;
    }

    public boolean getMovedOnce() {
        return movedOnce;
    }

    public void setMovedOnce(boolean movedOnce) {
        this.movedOnce = movedOnce;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }
*/
    @Override
    public String toString() {
        return "ChessPiece{" + "colour=" + colour + ", actPosition='" + actPosition + '\'' + '}';
    }
}
