package chessPieces;

import exceptions.ChessPieceException;
import exceptions.FieldException;
import exceptions.PositionException;
import model.PlayField;
import model.Position;

/**
 * Created by Noah on 29.04.2016.
 */
public class King extends ChessPiece{

    private Position posXY;
    //moved once important for the special move with the knight
    private boolean movedOnce;


    public King(boolean color, String start, boolean movedOnce) {
        super(color, start);
        posXY = new Position();
        setMovedOnce(movedOnce);
        System.out.println("King");
    }

    public boolean move(String endPosition, PlayField field, King k) throws FieldException, ChessPieceException, PositionException {
        if(k == null)throw new ChessPieceException("King must not be null!");
        if(field == null) throw new FieldException("Chess field must not be null!");

        int xStart = posXY.xValue(field.getActPositionOf(k));
        int yStart = posXY.yValue(field.getActPositionOf(k));

        int xEnd =   posXY.xValue(endPosition);
        int yEnd =   posXY.yValue(endPosition);

        if(super.move(endPosition, field, k) == false){
            return false;
        }else if(xStart == xEnd)
            if(yStart+1 == yEnd) {
                setMovedOnce(true);
                return true;
            }else if(yStart-1 == yEnd){
                setMovedOnce(true);
                return true;
        }else if(yStart == yEnd){
                if(xStart+1 == xEnd){
                    setMovedOnce(true);
                    return true;
                }else if (xStart-1 == xEnd){
                    setMovedOnce(true);
                    return true;
                }
            }else if(yStart+1 == yEnd && xStart+1 == xEnd){
                setMovedOnce(true);
                return true;
            }else if(yStart-1 == yEnd && xStart+1 == xEnd){
                setMovedOnce(true);
                return true;
            }else if(yStart-1 == yEnd && xStart-1 == xEnd){
                setMovedOnce(true);
                return true;
            }else if(yStart+1 == yEnd && xStart-1 == xEnd){
                setMovedOnce(true);
                return true;
            }

        return false;
    }

    public boolean specialMove(King k, Knight kn) throws ChessPieceException {
        if(k == null || kn == null) throw new ChessPieceException("King and Knight must not be NULL!");

        return true;
    }

    public boolean isMovedOnce() {
        return movedOnce;
    }

    public void setMovedOnce(boolean movedOnce) {
        this.movedOnce = movedOnce;
    }

    @Override
    public String toString() {
        if(isMovedOnce() == true) {
            return "k" + getActPos();
        }else{
            return "K" + getActPos();
        }
    }

    }





