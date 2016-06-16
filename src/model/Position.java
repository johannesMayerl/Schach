package model;

import chessPieces.ChessPiece;
import exceptions.GameExceptions;
import exceptions.PositionException;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Noah on 30.04.2016.
 */
public class Position {
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

    public int yValueLoad(String value){
        int returnValueX;
        if(value.charAt(3) == '8'){
            returnValueX = 0;
        }else if(value.charAt(3) == '7'){
            returnValueX = 1;
        }else if(value.charAt(3) == '6'){
            returnValueX = 2;
        }else if(value.charAt(3) == '5'){
            returnValueX = 3;
        }else if(value.charAt(3) == '4'){
            returnValueX = 4;
        }else if(value.charAt(3) == '3'){
            returnValueX = 5;
        }else if(value.charAt(3) == '2'){
            returnValueX = 6;
        }else if(value.charAt(3) == '1'){
            returnValueX = 7;
        }else {
            return -999;
        }
        return returnValueX;
    }

    public int xValueLoad(String value){
        if(value.charAt(2) == 'a'){
            return 0;
        }else if(value.charAt(2) == 'b'){
            return 1;
        }else if(value.charAt(2) == 'c'){
            return 2;
        }else if(value.charAt(2) == 'd'){
            return 3;
        }else if(value.charAt(2) == 'e'){
            return 4;
        }else if(value.charAt(2) == 'f'){
            return 5;
        }else if(value.charAt(2) == 'g'){
            return 6;
        }else if(value.charAt(2) == 'h'){
            return 7;
        }else{
            return -123;
        }
    }

    public boolean trueOrFalse(String value) throws GameExceptions {
        if(value.charAt(5) == 't'){
            return true;
        }else if(value.charAt(5) == 'f'){
            return false;
        }else throw new GameExceptions("Saved data is wrong");
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

    public String fromINTtoString(int x, int y){

        String returnValue = null;
        if(x == 0){
            returnValue = "a";
        }else if(x == 1){
            returnValue = "b";
        }else if(x == 2){
            returnValue = "c";
        }else if(x == 3){
            returnValue = "d";
        }else if(x == 4){
            returnValue = "e";
        }else if(x == 5){
            returnValue = "f";
        }else if(x == 6){
            returnValue = "g";
        }else if(x == 7){
            returnValue = "h";
        }

        String yV = null;

        if(y == 0){
            yV = "8";
        }else if(y == 1){
            yV = "7";
        } else if(y == 2){
            yV = "6";
        } else if(y == 3){
            yV = "5";
        } else if(y == 4){
            yV = "4";
        } else if(y == 5){
            yV = "3";
        } else if(y == 6){
            yV = "2";
        } else if(y == 7){
            yV = "1";
        }

        return returnValue + yV;
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
