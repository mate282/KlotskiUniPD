package com.example.klotski_model;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Board of some blocks
 */
public class Board {
    final int min_height_block = 5;
    final int max_height_block = 5;
    final int min_width_block = 4;
    final int max_width_block = 4;
    int height;         // board height (5)
    int width;          // board width  (4)
    List<Block> blocks;

    /**
     * Constructor
     * @param h board vertical height
     * @param w board horizontal width
     */
    public Board(int h, int w, List<Block> b) {
        if (h < min_height_block || w < min_width_block || h > max_height_block || w > max_width_block)
            throw new IllegalArgumentException("Board constructor values are wrong");
        this.height = h;
        this.width = w;
        this.blocks = b;
    }

    /**
     * Getter width
     * @return board width
     */
    public int getWidth() { return this.width; }

    /**
     * Getter height
     * @return this board's height
     */
    public int getHeight() { return this.height; }

    /**
     * Getter list of blocks
     *
     * @return blocks list
     */
    public List<Block> getBlocks() {
        return this.blocks;
    }

    /**
     * JSON --> Board
     *
     * @return Board created from JSON
     */
    static public Board fromJSON(JSONObject jsonObj) {
        JSONArray ja_data = jsonObj.getJSONArray("blocks");
        List<Block> bl = new ArrayList<>();
        for(int i=0; i<jsonObj.length(); i++)
        {
            bl.add(Block.fromJSON(ja_data.getJSONObject(i)));
        }
        return new Board(jsonObj.getInt("height"), jsonObj.getInt("width"), bl);
    }

    /**
     * Board --> JSON
     * {"blocks":[
     * {"pos_y":2,"pos_x":1,"width":1,"height":1},
     * {"pos_y":2,"pos_x":1,"width":2,"height":2},
     * {"pos_y":2,"pos_x":1,"width":2,"height":1}],
     * "width":4,"height":5}
     *
     * @return Board conversion to JSON
     */
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("height", this.height);
        jsonObject.put("width", this.width);
        JSONArray array = new JSONArray();
        for(int i = 0; i < this.blocks.size(); i++) {
            array.put(this.blocks.get(i).toJSON());
        }
        try {
            jsonObject.put("blocks", array);
        } catch(JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
