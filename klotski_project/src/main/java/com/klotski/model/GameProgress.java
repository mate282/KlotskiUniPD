package com.klotski.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class GameProgress {

    BeginningConfiguration configuration;
    ArrayList<Board> boards; //DEBUG
    ArrayList<Move> moves;

    /**Constructor
     * @param configuration beginning configuration of game
     * **/
    public GameProgress(BeginningConfiguration configuration){
        this.configuration=configuration;
        moves=new ArrayList<Move>(0);

        //DEBUG
        boards = new ArrayList<>();
        boards.add(new Board(5,4,configuration.getBlocks()));
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

    //DEBUG
    public void addMove(Move move, Board board){
        moves.add(move);
        boards.add(board);
    }
    public ArrayList<Board> getBoards(){
        return boards;
    }
    public ArrayList<Move> getMoves(){
        return moves;
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
        JSONArray jsonMoves= new JSONArray();
        for(int i = 0; i<moves.size(); i++){
            jsonMoves.put(moves.get(i).toJSON());
        }
        jsonObject.put("conf",configuration.toJSON());
        jsonObject.put("moves",jsonMoves);
        return jsonObject;
    }

    public static GameProgress fromJSON(JSONObject jsonObject) {
        BeginningConfiguration b = BeginningConfiguration.fromJSON(jsonObject.getJSONObject("conf"));
        JSONArray moveArray = jsonObject.getJSONArray("moves");
        ArrayList<Move> moves = new ArrayList<Move>(0);

        for (int i = 0; i < moveArray.length(); i++) {
            moves.add(Move.fromJSON(moveArray.getJSONObject(i)));
        }
        GameProgress progress=new GameProgress(b);
        progress.moves=moves;
        return progress;
    }
}