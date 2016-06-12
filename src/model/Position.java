package model;

import chessPieces.ChessPiece;
import exceptions.PositionException;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Noah on 30.04.2016.
 */
public class Position {
    //a1 == a0
    //a8 == a7
    private int xValue;
    private int yValue;

    public Position(){
    }

    public int yValue(String pos) throws PositionException {
        if(pos == null)throw new PositionException("The String pos in the method 'xValue' must not be null!");

        int returnValueX;
        if(Integer.parseInt(String.valueOf(pos.charAt(1))) == 8){
            returnValueX = 0;
        }else if(Integer.parseInt(String.valueOf(pos.charAt(1))) == 7){
            returnValueX = 1;
        }else if(Integer.parseInt(String.valueOf(pos.charAt(1))) == 6){
            returnValueX = 2;
        }else if(Integer.parseInt(String.valueOf(pos.charAt(1))) == 5){
            returnValueX = 3;
        }else if(Integer.parseInt(String.valueOf(pos.charAt(1))) == 4){
            returnValueX = 4;
        }else if(Integer.parseInt(String.valueOf(pos.charAt(1))) == 3){
            returnValueX = 5;
        }else if(Integer.parseInt(String.valueOf(pos.charAt(1))) == 2){
            returnValueX = 6;
        }else if(Integer.parseInt(String.valueOf(pos.charAt(1))) == 1){
            returnValueX = 7;
        }else {
            return -999;
        }
        return returnValueX;
    }

    public int xValue(String pos) throws PositionException {
        if(pos == null)throw new PositionException("The String pos in the method 'yValue' must not be null!");

        if(pos.charAt(0) == 'a'){
            return 0;
        }else if(pos.charAt(0) == 'b'){
            return 1;
        }else if(pos.charAt(0) == 'c'){
            return 2;
        }else if(pos.charAt(0) == 'd'){
            return 3;
        }else if(pos.charAt(0) == 'e'){
            return 4;
        }else if(pos.charAt(0) == 'f'){
            return 5;
        }else if(pos.charAt(0) == 'g'){
            return 6;
        }else if(pos.charAt(0) == 'h'){
            return 7;
        }else{
            return -123;
        }
    }

    public int getxValue() {
        return xValue;
    }

    public void setxValue(int xValue) {
        this.xValue = xValue;
    }

    public int getyValue() {
        return yValue;
    }

    public void setyValue(int yValue) {
        this.yValue = yValue;
    }
}
