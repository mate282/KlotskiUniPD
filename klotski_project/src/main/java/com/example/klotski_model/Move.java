package com.example.klotski_model;

import javafx.geometry.Point2D;

public class Move {
    Block block;
    Point2D startPos;
    Point2D destPos;

    /**Return block moved
     * @return block
     * **/
    public Block getBlock() {
        return block;
    }
    /**Return start position of block moved
     * @return start position
     * **/
    public Point2D getStart(){
        return startPos;
    }
    /**Return end position of block moved
     * @return end position
     * **/
    public Point2D getDest(){
        return destPos;
    }
    /**Return constructor
     * @param b block moved
     * @param start start position of block moved
     * @param dest end position of block moved
     * **/
    public Move(Block b,Point2D start,Point2D dest){
        block=b;
        startPos=start;
        destPos=dest;
    }
}
