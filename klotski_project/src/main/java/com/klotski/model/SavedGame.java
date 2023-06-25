package com.klotski.model;

import org.json.JSONObject;

import java.time.LocalDateTime;

public class SavedGame {

    private LocalDateTime gameDate;
    private GameProgress gameData;
    private Board lastboard;

    /**
     * Constructor
     * @param b last board
     * @param g game progress
     */
    public SavedGame(Board b, GameProgress g){
        this.lastboard = b;
        this.gameData = g;
        this.gameDate = LocalDateTime.now();
    }

    /**
     * Constructor
     * @param b last board
     * @param g game progress
     * @param d save date
     */
    public SavedGame(Board b, GameProgress g, LocalDateTime d){
        this.lastboard = b;
        this.gameData = g;
        this.gameDate = d;
    }

    /**
     * Getter last board
     *
     * @return last board
     */
    public Board getLastBoard(){
        return this.lastboard;
    }

    /**
     * Getter game progress
     *
     * @return game progress
     */
    public GameProgress getGameProgress() {
        return this.gameData;
    }

    /**
     * Getter save date
     *
     * @return save date
     */
    public LocalDateTime getSaveDate() {
        return this.gameDate;
    }

    /**
     * JSON --> SavedGame
     *
     * @return SavedGame created from JSON
     */
    static public SavedGame fromJSON(JSONObject jsonObject) {
        SavedGame savedGame = new SavedGame(Board.fromJSON(jsonObject.getJSONObject("board")),GameProgress.fromJSON(jsonObject.getJSONObject("gameProgress")));
        savedGame.gameDate = LocalDateTime.parse(jsonObject.getString("saveDate"));
        return savedGame;
    }

    /**
     * SavedGame --> JSON
     *
     * {"saveDate":"2023-06-25T09:23:35.302030600",
     * "gameProgress":
     * {"moves":[{"start_x":1,"end_x":1,"start_y":3,"end_y":4,"block":{"pos_y":4,"pos_x":2,"width":1,"height":1}},..],                      --> moves list
     * "conf":
     * {"blocks":[{"pos_y":0,"pos_x":0,"width":1,"height":2},...{"pos_y":0,"pos_x":1,"width":2,"height":2}], "name":"level2"}},             --> 1st configuration
     * "board":{"blocks":[{"pos_y":0,"pos_x":0,"width":1,"height":2},...{"pos_y":0,"pos_x":1,"width":2,"height":2}], "width":4,"height":5}} --> actual board
     *
     * @return SavedGame conversion to JSON
     */
    public JSONObject toJSON(){
        JSONObject savedJSON = new JSONObject();
        savedJSON.put("saveDate", this.gameDate.toString());
        savedJSON.put("gameProgress", this.gameData.toJSON());
        savedJSON.put("board", this.lastboard.toJSON());
        return savedJSON;
    }

}
