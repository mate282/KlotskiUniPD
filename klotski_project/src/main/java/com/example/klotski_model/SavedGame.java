package com.example.klotski_model;
import org.json.JSONObject;

public class SavedGame {
    public JSONObject toJSON(){
        JSONObject savedJSON = new JSONObject();
        return savedJSON;
    }

    static public SavedGame fromJSON(JSONObject jsonObject)
    {
        return new SavedGame();
    }
}
