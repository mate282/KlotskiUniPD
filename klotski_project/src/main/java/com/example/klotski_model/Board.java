package com.example.klotski_model;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import org.json.JSONObject;

import java.util.List;

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
        if (w < min_height_block || h < min_width_block || w > max_height_block || h > max_width_block)
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
    //static public Board fromJSON(String jsonString) {
    //    JSONObject json = new JSONObject(jsonString);
    //    return new Board(json.getInt("height"), json.getInt("width"), );
    //}

    /**
     * Board --> JSON
     *
     * @return Board conversion to JSON
     */
    //public JSONObject toJSON() {
    //    JSONObject jsonObject = new JSONObject();
    //    jsonObject.put("height", this.height);
    //    jsonObject.put("width", this.width);
    //    jsonObject.put("blocks", this.blocks);
    //    return jsonObject;
    //}
}
