package com.klotski.model;

import javafx.util.Pair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LevelSolution {
    private ArrayList<Pair<Board, Move>> boardMovs;

    /**
     * Constructor
     * @param bm array list of pair blocks-movements
     */
    public LevelSolution(ArrayList<Pair<Board, Move>> bm) {
        this.boardMovs = bm;
    }

    /**
     * Getter list of boards-movements
     *
     * @return boards-movement list
     */
    public ArrayList<Pair<Board, Move>> getBoardsMovs() {
        return this.boardMovs;
    }

    /**
     * Level Solution --> JSON
     * {"boardMovs":[
     * {"blocks":[{"pos_y":0,"pos_x":0,"width":1,"height":1},{...},...
     * ,{"pos_y":0,"pos_x":1,"width":2,"height":2}],"width":4,"height":5},                               --> board
     * {"start_x":1,"end_x":1,"start_y":3,"end_y":4,"block":{"pos_y":3,"pos_x":1,"width":1,"height":1}}, --> move
     * {"blocks":[...                                                                                    --> board/move couples series
     * ]}
     * @return Level Solution conversion to JSON
     */
    public JSONObject toJSON(){
        JSONArray jsonBoards = new JSONArray();
        for(Pair<Board, Move> bm : boardMovs){
            jsonBoards.put(bm.getKey().toJSON()); // Board
            jsonBoards.put(bm.getValue().toJSON()); // Movement associated to this Board
        }

        JSONObject solJSON = new JSONObject();
        solJSON.put("boardMovs", jsonBoards);
        return solJSON;
    }

    /**
     * JSON --> LevelSolution
     *
     * @param jsonObject with solution data
     *
     * @return
     * - null = wrong data input, no solution was created
     * - otherwise board created from JSON
     */
    static public LevelSolution fromJSON(JSONObject jsonObject) {
        LevelSolution ls = null;
        try {
            JSONArray jsonBoards = jsonObject.getJSONArray("boardMovs");
            ArrayList<Pair<Board, Move>> bm = new ArrayList();
            for(int i = 0; i < jsonBoards.length(); i+=2){
                Board b = Board.fromJSON(jsonBoards.getJSONObject(i));
                Move m = Move.fromJSON(jsonBoards.getJSONObject(i+1));
                bm.add(new Pair<Board, Move>(b, m));
            }
            ls = new LevelSolution(bm);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Level Solution constructor values are wrong");
        }
        return ls;
    }

    /**
     * Return move associated at this Board
     *
     * @param board = actual state of board
     *
     * @return
     * - null = board is not present
     * - otherwise = move
     */
    public Move getMove(Board board) {
        for (int i = 0; i < boardMovs.size(); i++){
            Board b = boardMovs.get(i).getKey();
            if (board.equals(b)){
                return boardMovs.get(i).getValue();
            }
        }
        return null;
    }
}