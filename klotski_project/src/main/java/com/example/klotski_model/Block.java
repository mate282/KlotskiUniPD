package com.example.klotski_model;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import org.json.JSONObject;
import org.json.JSONString;
import org.json.JSONStringer;

/**
 * Block of klotski board
 *     p_______                          p______________
 *     |       |                         |              |
 *     |       | height = 1              |              | height = 1
 *     |_______|                         |______________|
 *      width = 1                         width = 2
 *
 *     p_______                          p______________
 *     |       |                         |              |
 *     |       | height = 2              |              | height = 2
 *     |       |                         |              |
 *     |       |                         |              |
 *     |_______|                         |______________|
 *      width = 1                            width = 2
 */
public class Block {
    final int min_dim_block = 1;
    final int max_dim_block = 2;
    final Color def_block_color = Color.BLUE;

    Point2D position;   // block top left corner point
    int height;         // block height (1 or 2)
    int width;          // block width  (1 or 2)
    Color color;        // block color (black, blue, cyan...)
    boolean mainBlock;  // true is the main block, false otherwise

    /**
     * Constructor
     * @param p top left corner point
     * @param h block vertical height
     * @param w block horizontal width
     * @param c block color
     */
    public Block(Point2D p, int h, int w, Color c) {
        this(p, h, w);
        this.color = c;
    }

    /**
     * Constructor
     * @param p top left corner point
     * @param h block vertical height
     * @param w block horizontal width
     */
    public Block(Point2D p, int h, int w) {
        if (p.getX() < 0 || p.getY() < 0 || w < min_dim_block || h < min_dim_block || w > max_dim_block || h > max_dim_block)
            throw new IllegalArgumentException("Block constructor values are wrong");
        this.position = p;
        this.height = h;
        this.width = w;
        this.color = def_block_color;
        this.mainBlock = ((this.height == max_dim_block) && (this.width == max_dim_block));
    }

    /**
     * Return if it is main or normal block
     *
     * @return
     * - true = main block
     * - false = normal block
     */
    public boolean isMainBlock() {
        return this.mainBlock;
    }

    /**
     * Getter color
     *
     * @return block color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Getter height
     *
     * @return block height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Getter width
     *
     * @return block width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Getter top left corner point position
     *
     * @return block position
     */
    public Point2D getPos() {
        return this.position;
    }

    /**
     * Setter top left corner point position
     *
     * @param p top left corner point
     *
     * @return None
     */
    public void setPos(Point2D p) {
        if ((p.getX() < 0) || (p.getY() < 0))
            throw new IllegalArgumentException("Block constructor values are wrong");
        this.position = p;
    }

    /**
     * JSON --> Block
     * ad es. {"pos_x":1, "pos_y":2,"width":1,"height":1} --> Block(Point2D(1,2), 1, 1)
     *
     * @return Block created from JSON
     */
    static public Block fromJSON(String jsonString) {
        JSONObject json = new JSONObject(jsonString);
        return new Block(new Point2D(json.getInt("pos_x"), json.getInt("pos_y")),
                json.getInt("height"), json.getInt("width"));
    }

    /**
     * Block --> JSON
     * ad es. Point2D(1,2),1,1 --> {"pos_x":1, "pos_y":2,"width":1,"height":1}
     *
     * @return Block conversion to JSON
     */
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pos_x", this.position.getX());
        jsonObject.put("pos_y", this.position.getY());
        jsonObject.put("height", this.height);
        jsonObject.put("width", this.width);
        return jsonObject;
    }

    /**
     * Block --> string "posx posy height width"
     * ad es. Point2D(1,2),1,1 --> "1.0 2.0 1 1"
     *
     * @return Block conversion to string
     */
    public String toString() {
        String out = "";
        out = out.concat(Double.toString(this.position.getX()) + " ")
                 .concat(Double.toString(this.position.getY()) + " ")
                 .concat(Integer.toString(this.height) + " ")
                 .concat(Integer.toString(this.width));
        return out;
    }

    /**
     * Verifies if the point p is in the block
     *
     * @param p point to checked
     *
     * @return
     * - true = point is in
     * - false = point is not in
     */
    public boolean pointIsIn(Point2D p) {
        return ((p.getX() >= this.position.getX()) &&
                (p.getY() >= this.position.getY()) &&
                (p.getX() < (this.position.getX() + this.width)) &&
                (p.getY() < (this.position.getY() + this.height)));
    }
}