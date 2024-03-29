package com.klotski.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BeginningConfiguration {
    private final List<Block> blocks;
    private final String name; //configuration name

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
     * JSON --> BeginningConfiguration
     *
     * @return BeginningConfiguration created from JSON
     */
    static public BeginningConfiguration fromJSON(JSONObject json){
        JSONArray blockArray = json.getJSONArray("blocks");
        ArrayList<Block> blocks = new ArrayList<>(0);

        for(int i = 0; i < blockArray.length();i++){
            blocks.add(Block.fromJSON(blockArray.getJSONObject(i)));
        }
        return new BeginningConfiguration(json.getString("name"),blocks );
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
            jsonArray.put(blocks.get(i).toJSON());
        }
        jsonObject.put("name", this.name);
        jsonObject.put("blocks", jsonArray);
        return jsonObject;
    }

    /**
     * Verifies if the beginning configuration are ==
     *
     * @return
     * - true = are equal
     * - false = are not equal
     */
    public boolean equals(BeginningConfiguration configToCheck) {
        boolean result = false;
        if(!name.equals(configToCheck.getName())) result=false;
        else{
            for(int i=0; i<blocks.size();i++){
                boolean find = false;
                for(int j=0; j<configToCheck.getBlocks().size(); j++){
                    if(blocks.get(i).equals(configToCheck.getBlocks().get(j))){
                        find=true;
                    }
                }
                if(!find) return false;
            }
            for(int i =0; i<configToCheck.getBlocks().size(); i++){
                boolean find = false;
                for(int j=0; j<blocks.size(); j++){
                    if(configToCheck.getBlocks().get(i).equals(blocks.get(j))){
                        find=true;
                    }
                }
                if(!find) return false;
                result=true;
            }
        }
        return result;
    }
}
