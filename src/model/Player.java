package model;

import chessPieces.*;

import java.util.Arrays;

/**
 * Created by Noah on 04.05.2016.
 * has to be changed when chess piece was moved
 *  +String position of piece
 *  +delete when "killed"
 *  +change when pawn reaches end
 */
public class Player {

    private boolean playerColor;
    private ChessPiece[] piecesPlayer;


    public Player(boolean player) {
        this.playerColor = player;
        piecesPlayer = new ChessPiece[16];

    }

    public void initializePlayersPieces() {
        if(playerColor == true){

            piecesPlayer[0] = new Pawn(true, "a7", false);
            piecesPlayer[1] = new Pawn(true, "b7", false);
            piecesPlayer[2] = new Pawn(true, "c7", false);
            piecesPlayer[3] = new Pawn(true, "d7", false);
            piecesPlayer[4] = new Pawn(true, "e7", false);
            piecesPlayer[5] = new Pawn(true, "f7", false);
            piecesPlayer[6] = new Pawn(true, "g7", false);
            piecesPlayer[7] = new Pawn(true, "h7", false);
            //Rook_black
            piecesPlayer[8] = new Rook(true, "a8");
            piecesPlayer[9] = new Rook(true, "h8");
            //Knight_black
            piecesPlayer[10] = new Knight(true, "b8", false);
            piecesPlayer[11] = new Knight(true, "g8", false);
            //Bishop_black
            piecesPlayer[12] = new Bishop(true, "c8");
            piecesPlayer[13] = new Bishop(true, "f8");
            //Queen_black
            piecesPlayer[14] = new Queen(true, "d8");
            //King_black
            piecesPlayer[15] = new King(true, "e8", false);

        }else {

            piecesPlayer[0] = new Pawn(true, "a1", false);
            piecesPlayer[1] = new Pawn(true, "b1", false);
            piecesPlayer[2] = new Pawn(true, "c1", false);
            piecesPlayer[3] = new Pawn(true, "d1", false);
            piecesPlayer[4] = new Pawn(true, "e1", false);
            piecesPlayer[5] = new Pawn(true, "f1", false);
            piecesPlayer[6] = new Pawn(true, "g1", false);
            piecesPlayer[7] = new Pawn(true, "h1", false);
            //Rook_white
            piecesPlayer[8] = new Rook(true, "a0");
            piecesPlayer[9] = new Rook(true, "h0");
            //Knight_white
            piecesPlayer[10] = new Knight(true, "b0", false);
            piecesPlayer[11] = new Knight(true, "g0", false);
            //Bishop_white
            piecesPlayer[12] = new Bishop(true, "c0");
            piecesPlayer[13] = new Bishop(true, "f0");
            //Queen_white
            piecesPlayer[14] = new Queen(true, "d0");
            //King_white
            piecesPlayer[15] = new King(true, "e0", false);

        }

    }

    public ChessPiece getPiece(int position){
        //returns the chess piece at given position
        return piecesPlayer[position];
    }

    public ChessPiece[] getPieces(){
        //returns the array with the pieces
        return piecesPlayer;
    }

    public String getPieceInfoAsString(int i){
        String piece_INFO = null;
        piecesPlayer[i].toString();
        return piece_INFO;
    }

    public String saveOutPut(){
        String output = null;
        if(playerColor == true){
            //add positions of all existing pieces
            output ="B;";
            for(int i = 0; i < piecesPlayer.length; i++){
                output += piecesPlayer[i].toString() + ";";
            }
        }else if(playerColor == false){
            //add positions of all existing pieces
            output ="W;";
            for(int i = 0; i < piecesPlayer.length; i++){
                output += piecesPlayer[i].toString() + ";";
            }
        }
        return output;
    }
}
