package com.klotski.model;

import javafx.geometry.Point2D;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Board of some blocks
 *
 *                  width
 *   xy
 *   00 ---- 10 ---- 20 ---- 30 ----
 *   |       |       |       |      |
 *   01 ---- 11 ---- 21 ---- 31 ----
 *   |       |       |       |      |
 *   02 ---- 12 ---- 22 ---- 32 ----
 *   |       |       |       |      |   height
 *   03 ---- 13 ---- 23 ---- 33 ----
 *   |       |       |       |      |
 *   04 ---- 14 ---- 24 ---- 34 ----
 *   |       |       |       |      |
 *   -------------------------------
 */
public class Board {
    private final int min_height_block = 5;
    private final int max_height_block = 5;
    private final int min_width_block = 4;
    private final int max_width_block = 4;
    private final int x_win_pos = 1;
    private final int y_win_pos = 3;
    private int height;         // board height (5)
    private int width;          // board width  (4)
    private List<Block> blocks;

    /**
     * Verifies if the point is in the board or not
     *
     * @param p point to be checked
     *
     * @return true = it is in the board
     */
    private boolean isInBoard(Point2D p) {
        return ((p.getX() >= 0) && (p.getY() >= 0) && (p.getX() < this.width) && (p.getY() < this.height));
    }

    /**
     * Verify if all points of the list are free and in the board
     *
     * @param pl list of points to be checked (can be empty)
     *
     * @return
     * - true = all the points are free and in the board
     * - false = otherwise
     */
    private boolean routeIsValid(List<Point2D> pl) {
        if (pl.isEmpty()) {
            return false;
        }
        for(Point2D p : pl) {
            if(!isInBoard(p)) {
                return false;
            }
            for(Block b : blocks) {
                if (b.pointIsIn(p)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Vertical, Down Vertical, Horizontal, Right Horizontal movement verify
     *
     * @param start beginning point
     * @param dest ending point
     *
     * @return true/false
     */
    private boolean verticalMovement(Point2D start, Point2D dest) { return (start.getX() == dest.getX());}
    private boolean downVerticalMovement(Point2D start, Point2D dest) { return (start.getY() < dest.getY());}

    private boolean horizontalMovement(Point2D start, Point2D dest) { return (start.getY() == dest.getY()); }
    private boolean rightHorizontalMovement(Point2D start, Point2D dest) { return (start.getX() < dest.getX()); }

    /**
     * Verify if there is a free route from start point --> dest point
     *
     * @param move movement to be done
     *
     * @return
     * - true = there is a free route
     * - false = otherwise
     */
    private boolean checkValidMove(Move move){
        Point2D dest = move.getDest();
        Point2D start = move.getStart();
        Block b = move.getBlock();

        boolean result = false;
        // List of points that must be free, and in the board
        List<Point2D> pl = new ArrayList<Point2D>();
        if(dest.distance(start) > 0) {
            if (b.getDim() == 4) {
                // Master block
                if (dest.distance(start) == 1) {
                    if(verticalMovement(start, dest)) {
                        // vertical movement
                        if(downVerticalMovement(start, dest)) {
                            pl.add(new Point2D(start.getX(), start.getY() + 2));
                            pl.add(new Point2D(start.getX() + 1, start.getY() + 2));
                        } else {
                            pl.add(new Point2D(start.getX(), start.getY() - 1));
                            pl.add(new Point2D(start.getX() + 1, start.getY() - 1));
                        }
                    }
                    if(horizontalMovement(start, dest)) {
                        // horizontal movement
                        if(rightHorizontalMovement(start, dest)) {
                            pl.add(new Point2D(start.getX() + 2, start.getY()));
                            pl.add(new Point2D(start.getX() + 2, start.getY() + 1));
                        } else {
                            pl.add(new Point2D(start.getX() - 1, start.getY()));
                            pl.add(new Point2D(start.getX() - 1, start.getY() + 1));
                        }
                    }
                }
            }
            if (b.getDim() == 2) {
                if (dest.distance(start) <= 2) {
                    if (verticalMovement(start, dest)) {
                        // vertical movement
                        if(downVerticalMovement(start, dest)) {
                            if(b.isVertical()) {
                                pl.add(new Point2D(start.getX(), start.getY() + 2));
                                if(dest.distance(start) == 2) {
                                    pl.add(new Point2D(start.getX(), start.getY() + 3));
                                }
                            } else {
                                pl.add(new Point2D(start.getX(), start.getY() + 1));
                                pl.add(new Point2D(start.getX() + 1, start.getY() + 1));
                            }
                        } else {
                            if(b.isVertical()) {
                                pl.add(new Point2D(start.getX(), start.getY() - 1));
                                if(dest.distance(start) == 2) {
                                    pl.add(new Point2D(start.getX(), start.getY() - 2));
                                }
                            } else {
                                pl.add(new Point2D(start.getX(), start.getY() - 1));
                                pl.add(new Point2D(start.getX() + 1, start.getY() - 1));
                            }
                        }
                    }
                    if (horizontalMovement(start, dest)) {
                        // horizontal movement
                        if(rightHorizontalMovement(start, dest)) {
                            if(!b.isVertical()) {
                                pl.add(new Point2D(start.getX() + 2, start.getY()));
                                if(dest.distance(start) == 2) {
                                    pl.add(new Point2D(start.getX() + 3, start.getY()));
                                }
                            } else {
                                pl.add(new Point2D(start.getX() + 1, start.getY()));
                                pl.add(new Point2D(start.getX() + 1, start.getY() + 1));
                            }
                        } else {
                            if(!b.isVertical()) {
                                pl.add(new Point2D(start.getX() - 1, start.getY()));
                                if(dest.distance(start) == 2) {
                                    pl.add(new Point2D(start.getX() - 2, start.getY()));
                                }
                            } else {
                                pl.add(new Point2D(start.getX() - 1, start.getY()));
                                pl.add(new Point2D(start.getX() - 1, start.getY() + 1));
                            }
                        }
                    }
                }
            }
            if (b.getDim() == 1) {
                if (dest.distance(start) <= 2) {
                    if (verticalMovement(start, dest)) {
                        // vertical movement
                        if (downVerticalMovement(start, dest)) {
                            pl.add(new Point2D(start.getX(), start.getY() + 1));
                            if(dest.distance(start) == 2) {
                                pl.add(new Point2D(start.getX(), start.getY() + 2));
                            }
                        } else {
                            pl.add(new Point2D(start.getX(), start.getY() - 1));
                            if(dest.distance(start) == 2) {
                                pl.add(new Point2D(start.getX(), start.getY() - 2));
                            }
                        }
                    }
                    if (horizontalMovement(start, dest)) {
                        // horizontal movement
                        if (rightHorizontalMovement(start, dest)) {
                            pl.add(new Point2D(start.getX() + 1, start.getY()));
                            if(dest.distance(start) == 2) {
                                pl.add(new Point2D(start.getX() + 2, start.getY()));
                            }
                        } else {
                            pl.add(new Point2D(start.getX() - 1, start.getY()));
                            if(dest.distance(start) == 2) {
                                pl.add(new Point2D(start.getX() - 2, start.getY()));
                            }
                        }
                    }
                }
            }
        }
        if(routeIsValid(pl)) {
            result = true;
        }
        return result;
    }

    /**
     * Constructor
     * @param h board vertical height
     * @param w board horizontal width
     * @param b list of blocks
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
     * @param jsonObject with board data
     *
     * @return
     * - null = wrong data input, no block was created
     * - otherwise board created from JSON
     */
    static public Board fromJSON(JSONObject jsonObject) {
        Board bo = null;
        try {
            JSONArray jsonBlocks = jsonObject.getJSONArray("blocks");
            ArrayList<Block> blocks = new ArrayList<Block>(0);
            for(int i = 0; i < jsonBlocks.length(); i++){
                blocks.add(Block.fromJSON(jsonBlocks.getJSONObject(i)));
            }
            bo = new Board(jsonObject.getInt("height"), jsonObject.getInt("width"),blocks);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Block constructor values are wrong");
        }
        return bo;
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
        JSONArray jsonBlocks = new JSONArray();
        for(Block b : blocks){
            jsonBlocks.put(b.toJSON());
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("height", this.height);
        jsonObject.put("width", this.width);
        jsonObject.put("blocks", jsonBlocks);
        return jsonObject;
    }

    /**
     * Return a block searching by position
     *
     * @param position point to checked
     *
     * @return
     * - null = there is no block in this point
     * - otherwise block that is in this point
     */
    public Block findBlockByPosition(Point2D position){
        for(Block b : blocks){
            if (b.pointIsIn(position)){
                return b;
            }
        }
        return null;
    }

    /**
     * Execute a block movement
     *
     * @param move movement to be done
     *
     * @return
     * - true = movement is done with success
     * - false = movement is not possible, nothing is done
     */
    public boolean move(Move move){
        Point2D dest = move.getDest();
        Point2D start = move.getStart();
        Block b = move.getBlock();
        // check if block is in start position
        if((b != null) && (b == this.findBlockByPosition(start))) {
            // dest - start route must be free
            if(checkValidMove(move)) {
                b.setPos(dest);
                return true;
            }
        }
        return false;
    }

    /**
     * Verifies if game is over
     *
     * @return
     * - true = win
     * - false = otherwise
     */
    public boolean checkWin() {
        for(Block b : blocks){
            if (b.isMainBlock()){
                Point2D p = new Point2D(x_win_pos,y_win_pos);
                if(b.getPos().getX() == x_win_pos && b.getPos().getY()==y_win_pos) {
                    return true;
                }
            }
        }
        return false;
    }
}
