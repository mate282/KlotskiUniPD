package com.klotski.model;

import javafx.geometry.Point2D;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    Board board = null;

    @BeforeEach
    void setUp() {
        ArrayList<Block> blocks= new ArrayList<Block>(0);
        blocks.add(new Block(new Point2D(0,0), 2,1));
        blocks.add(new Block(new Point2D(1,0), 2,2));
        blocks.add(new Block(new Point2D(3,0), 2,1));
        board = new Board(5,4,blocks);
    }

    @Test
    @DisplayName("Test getWidth")
    void getWidth() {
        assertEquals(4, board.getWidth());
    }

    @Test
    @DisplayName("Test getHeight")
    void getHeight() {
        assertEquals(5, board.getHeight());
    }

    @Test
    @DisplayName("Test getBlocks")
    void getBlocks() {
        ArrayList<Block> blocks= new ArrayList<Block>(0);
        blocks.add(new Block(new Point2D(0,0), 2,1));
        blocks.add(new Block(new Point2D(1,0), 2,2));
        blocks.add(new Block(new Point2D(3,0), 2,1));
        Board b = new Board(5,4,blocks);

        assertTrue(b.equals(board));
    }

    @Test
    @DisplayName("Test fromJSON")
    void fromJSON() {
        String s = new String("{"+"blocks"+":["+
                "{"+"pos_y"+":0,"+"pos_x"+":0,"+"width"+":1,"+"height"+":2}, "+
                "{"+"pos_y"+":0,"+"pos_x"+":1,"+"width"+":2,"+"height"+":2}, "+
                "{"+"pos_y"+":0,"+"pos_x"+":3,"+"width"+":1,"+"height"+":2}], "+"width"+":4,"+"height"+":5}");
        JSONObject jsonObject = new JSONObject(s);
        Board b = Board.fromJSON(jsonObject);
        assertTrue(b.equals(board));

        String s1 = new String("{"+"blocks"+":["+
                "{"+"pos_y"+":0,"+"pos_x"+":0,"+"width"+":1,"+"height"+":2}, "+
                "{"+"pos_y"+":0,"+"pos_x"+":1,"+"width"+":2,"+"height"+":2}, "+
                "{"+"posy"+":0,"+"pos_x"+":3,"+"width"+":1,"+"height"+":2}], "+"width"+":4,"+"height"+":5}");
        JSONObject jsonObject1 = new JSONObject(s1);
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Board bTestErr = Board.fromJSON(jsonObject1);
        });
        Assertions.assertEquals("Board constructor values is wrong", thrown.getMessage());
    }

    @Test
    @DisplayName("Test toJSON")
    void toJSON() {
        String s1 = new String("{\"blocks\":[{\"pos_y\":0,\"pos_x\":0,\"width\":1,\"height\":2},{\"pos_y\":0,\"pos_x\":1,\"width\":2,\"height\":2},{\"pos_y\":0,\"pos_x\":3,\"width\":1,\"height\":2}],\"width\":4,\"height\":5}");
        String s2 = board.toJSON().toString();
        assertTrue(s1.equals(s2));
    }

    @Test
    @DisplayName("Test findBlockByPosition")
    void findBlockByPosition() {
        Point2D p = new Point2D(0,0);
        Point2D p1 = new Point2D(0,1);
        Point2D p2 = new Point2D(0,2);
        Block b = new Block(new Point2D(0,0), 2,1);
        assertTrue(board.findBlockByPosition(p).equals(b));
        assertTrue(board.findBlockByPosition(p1).equals(b));
        assertEquals(null, board.findBlockByPosition(p2));
    }

    @Test
    @DisplayName("Test move")
    void move() {
    }

    @Test
    @DisplayName("Test checkWin")
    void checkWin() {
        ArrayList<Block> blocks= new ArrayList<Block>(0);
        blocks.add(new Block(new Point2D(1,3), 2,2));
        Board b = new Board(5,4,blocks);
        assertTrue(b.checkWin());

        ArrayList<Block> blocks1= new ArrayList<Block>(0);
        blocks1.add(new Block(new Point2D(0,3), 2,2));
        Board b1 = new Board(5,4,blocks1);
        assertFalse(b1.checkWin());
    }

    @Test
    @DisplayName("Test testEquals&Clone")
    void testEqualsClone() {
        Board b = board.clone();
        assertTrue(b.equals(board));
        assertTrue(board.equals(b));
        assertTrue(b.equals(b));

        ArrayList<Block> blocks= new ArrayList<Block>(0);
        blocks.add(new Block(new Point2D(0,0), 2,1));
        blocks.add(new Block(new Point2D(1,0), 2,2));
        blocks.add(new Block(new Point2D(3,0), 2,1));
        blocks.add(new Block(new Point2D(3,1), 1,1));
        Board b1 = new Board(5,4, blocks);
        assertFalse(b1.equals(board));
        assertFalse(board.equals(b1));

        ArrayList<Block> blocks2 = new ArrayList<Block>(0);
        Board b2 = new Board(5,4, blocks2);
        assertFalse(b2.equals(board));
        assertFalse(board.equals(b2));
    }
}