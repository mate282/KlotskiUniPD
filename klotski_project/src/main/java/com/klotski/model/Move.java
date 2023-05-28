package com.klotski.model;

import javafx.geometry.Point2D;
import org.json.JSONObject;

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

    public JSONObject toJSON(){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("block",block.toJSON());
        jsonObject.put("start_x",startPos.getX());
        jsonObject.put("start_y",startPos.getY());
        jsonObject.put("end_x",destPos.getX());
        jsonObject.put("end_y",destPos.getY());
        return jsonObject;
    }

    public static Move fromJSON(JSONObject jsonObject){
        Block b=Block.fromJSON(jsonObject.getJSONObject("block"));
        double start_x=jsonObject.getDouble("start_x");
        double start_y=jsonObject.getDouble("start_y");
        double end_x=jsonObject.getDouble("end_x");
        double end_y=jsonObject.getDouble("end_y");
        Point2D start=new Point2D(start_x,start_y);
        Point2D end=new Point2D(end_x,end_y);
        return new Move(b,start,end);
    }
}

