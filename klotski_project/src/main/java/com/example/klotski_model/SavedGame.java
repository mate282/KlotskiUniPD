
package com.example.klotski_model;

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
    public Board getLastboard(){
        return this.lastboard;
    }

    /**
     * Getter game progress
     *
     * @return game progress
     */
    public GameProgress getGameProgress() {
        return gameData;
    }

    /**
     * Getter save date
     *
     * @return save date
     */
    public LocalDateTime getSaveDate() {
        return gameDate;
    }

    /**
     * JSON --> SavedGame
     *
     * @return SavedGame created from JSON
     */
    static public SavedGame fromJSON(JSONObject jsonObject) {
        JSONObject json = new JSONObject(jsonString);
        return new SavedGame(json.getObject("saveDate"), json.getObject("gameProgress"),json.getObject("board"));
    }

    /**
     * SavedGame --> JSON
     *
     * @return SavedGame conversion to JSON
     */
    public JSONObject toJSON(){
        JSONObject savedJSON = new JSONObject();
        jsonObject.put("saveDate", this.gameDate);
        jsonObject.put("gameProgress", this.gameData);
        jsonObject.put("board", this.lastboard);
        return savedJSON;
    }

    

}