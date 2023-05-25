package com.example.klotski_model;

import java.util.List;
import org.json.JSONObject;
import org.json.JSONString;
import org.json.JSONArray;

public class BeginningConfiguration {
    private List<Block> blocks;
    private String name; //configuration name

    /**
     * Constructor
     * @param n configuration name
     * @param b list of blocks
     */
    public BeginningConfiguration(String n, List<Block> b){
        this.name = n;
        this.blocks = b;
    }

    /**
     * Getter name
     *
     * @return configuration name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Getter blocks list
     *
     * @return Blocks list
     */
    public List<Block> getBlocks(){
        return this.blocks;
    }

    /**
     * Block list --> string
     *
     * @return Block list conversion to string
     */
    public String blockstoString(){
        String out="";
        for(int i=0; i<blocks.size(); i++){
            out=out.concat(blocks.get(i).toString());
        }
        return out;
    }

    /**
     * JSON --> BeginningConfiguration
     *
     * @return BeginningConfiguration created from JSON
     */
    static public BeginningConfiguration fromJSON(String jsonString){
        JSONObject json = new JSONObject(jsonString);
        return new BeginningConfiguration(json.getString("name"), json.getJSONArray("blocks"));
    }
    /**
     * BeginningConfiguration --> JSON
     *
     * @return BeginningConfiguration conversion to JSON
     */
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for(int i = 0; i<blocks.size(); i++){
            jsonArray.put(blocks.get(i).toString());
        }
        jsonObject.put("name", this.name);
        jsonObject.put("blocks", jsonArray);
        return jsonObject;
    }
}
