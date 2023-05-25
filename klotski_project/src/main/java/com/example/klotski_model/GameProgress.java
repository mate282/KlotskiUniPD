package com.example.klotski_model;

import javafx.geometry.Point2D;
import org.json.JSONObject;

import java.util.ArrayList;

public class GameProgress {

    BeginningConfiguration configuration;
    ArrayList<Move> moves;

    /**Constructor
     * @param configuration beginning configuration of game
     * **/
    public GameProgress(BeginningConfiguration configuration){
        this.configuration=configuration;
        moves=new ArrayList<Move>(0);

    }

    /**returns the start configuration
     * @return beginning configuration
     * **/
    public BeginningConfiguration getBeginConf(){
        return configuration;
    }

    /**returns moves counter
     * @return  number of moves
     * **/
    public int getMovesCounter(){
        return moves.size();
    }

    /**return undo move
     * @return undo last move else return null
     * **/
    public Move undoLastMove(){
        int lastIndex=moves.size()-1;
        if(lastIndex>-1)
            return moves.remove(lastIndex);
        return null;
    }

    /**add new move
     * @param move new move to add
     * **/
    public void addMove(Move move){
        moves.add(move);
    }
    /**reset game progress
     * @return if the progress is done else return false
     * **/
    public boolean resetProgress() {
        if (getMovesCounter() > 0) {
            moves = new ArrayList<Move>(0);
            return true;
        }
        return false;
    }

    public JSONObject toJSON(){
        JSONObject jsonObject=new JSONObject();
        ArrayList<JSONObject> jsonMoves=new ArrayList<JSONObject>(0);
        for(Move m:moves){
            jsonMoves.add(m.toJSON());
        }
        jsonObject.put("conf",configuration.toJSON());
        jsonObject.put("moves",jsonMoves);
        return jsonObject;
    }

    public static Move fromJSON(JSONObject jsonObject){
       BeginningConfiguration b=BeginningConfiguration.fromJSON(jsonObject.getJSONObject("conf"));
        double start_x=jsonObject.getDouble("start_x");
        double start_y=jsonObject.getDouble("start_y");
        double end_x=jsonObject.getDouble("end_x");
        double end_y=jsonObject.getDouble("end_y");
        Point2D start=new Point2D(start_x,start_y);
        Point2D end=new Point2D(end_x,end_y);
        return new Move(b,start,end);
    }
}
