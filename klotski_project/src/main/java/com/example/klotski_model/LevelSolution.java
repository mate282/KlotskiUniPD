package com.example.klotski_model;

import org.json.JSONObject;

public class LevelSolution {
    public JSONObject toJSON(){
        JSONObject solJSON = new JSONObject();
        return solJSON;
    }

    static public LevelSolution fromJSON(JSONObject jsonObject) {
        return new LevelSolution();
    }
}
