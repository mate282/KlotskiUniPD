package com.klotski.model;

import javafx.geometry.Point2D;
import org.json.JSONObject;

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
    private static final int min_dim_block = 1;
    private static final int max_dim_block = 2;


    private Point2D position;   // block top left corner point
    private final int height;         // block height (1 or 2)
    private final int width;          // block width  (1 or 2)
    private final boolean mainBlock;  // true is the main block, false otherwise


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
     * Getter dimension (1, 2, 4)
     *
     * @return block dimension
     */
    public int getDim() {
        return (this.width * this.height);
    }

    /**
     * Returns if block is vertical or not (only if dimension block = 2)
     *
     * @return
     * - true = it is vertical
     * - false = it is horizontal
     */
    public boolean isVertical() { return (this.height == 2); }

    /**
     * Setter top left corner point position
     *
     * @param p top left corner point
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
     * @param json object with block data
     *
     * @return
     * - null = wrong data input, no block was created
     * - otherwise block created from JSON
     */
    static public Block fromJSON(JSONObject json) {
        Block b;
        try {
            b = new Block(new Point2D(json.getInt("pos_x"),
                    json.getInt("pos_y")),
                    json.getInt("height"),
                    json.getInt("width"));

        }
        catch (Exception e) {
            throw new IllegalArgumentException("Block constructor values are wrong");
        }
        return b;
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
        out = out.concat(this.position.getX()+ " ")
                .concat(this.position.getY() + " ")
                .concat(this.height + " ")
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

    /**
     * Verifies if the block are ==
     *
     * @return
     * - true = are equal
     * - false = are not equal
     */
    public boolean equals(Block blockToCheck) {
        if((height          == blockToCheck.getHeight())        &&
           (width           == blockToCheck.getWidth())         &&
           (position.getX() == blockToCheck.getPos().getX())    &&
           (position.getY() == blockToCheck.getPos().getY())) {
            return true;
        }
        return false;
    }

    @Override
    public Block clone(){
        return new Block(new Point2D(position.getX(), position.getY()),height,width);
    }
}
