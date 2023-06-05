package com.klotski.model;

import javafx.geometry.Point2D;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.management.InvalidApplicationException;

import static org.junit.jupiter.api.Assertions.*;

class BlockTest {

    Block b1011 = null;
    Block b1021 = null;
    Block b1012 = null;
    Block b1022 = null;
    Block b0312 = null;

    @BeforeEach
    void setUp() {
        b1011 = new Block(new Point2D(1,0), 1, 1);
        b1021 = new Block(new Point2D(1,0), 2, 1);
        b1012 = new Block(new Point2D(1,0), 1, 2);
        b1022 = new Block(new Point2D(1,0), 2, 2);
        b0312 = new Block(new Point2D(0,3), 1, 2);
    }

    @Test
    @DisplayName("Test isMainBlock")
    void isMainBlock() {
        assertTrue(b1022.isMainBlock());
        assertFalse(b1011.isMainBlock());
        assertFalse(b1012.isMainBlock());
    }

    @Test
    @DisplayName("Test getHeight")
    void getHeight() {
        assertEquals(1, b1011.getHeight());
        assertEquals(2, b1021.getHeight());
    }

    @Test
    @DisplayName("Test getWidth")
    void getWidth() {
        assertEquals(1, b1011.getWidth());
        assertEquals(1, b1021.getWidth());
        assertEquals(2, b1012.getWidth());
    }

    @Test
    @DisplayName("Test getPos")
    void getPos() {
        assertEquals(new Point2D(1, 0), b1011.getPos());
        assertEquals(new Point2D(1, 0), b1021.getPos());
        assertEquals(new Point2D(1, 0), b1012.getPos());
        assertEquals(new Point2D(1, 0), b1022.getPos());
        assertEquals(new Point2D(0, 3), b0312.getPos());
    }

    @Test
    @DisplayName("Test getDim")
    void getDim() {
        assertEquals(1, b1011.getDim());
        assertEquals(2, b1021.getDim());
        assertEquals(2, b1012.getDim());
        assertEquals(4, b1022.getDim());
        assertEquals(2, b0312.getDim());
    }

    @Test
    @DisplayName("Test isVertical")
    void isVertical() {
        assertEquals(true, b1021.isVertical());
        assertEquals(false, b1012.isVertical());
        assertEquals(false, b0312.isVertical());
        // special case (no meaning blocks of 1 or 4 dimension)
        assertEquals(false, b1011.isVertical());
        assertEquals(true, b1022.isVertical());
    }

    @Test
    @DisplayName("Test setPos")
    void setPos() {
        b1021.setPos(new Point2D(0,1));
        assertEquals(new Point2D(0,1), b1021.getPos());

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            b1021.setPos(new Point2D(-1,1));
        });
        Assertions.assertEquals("Block constructor values are wrong", thrown.getMessage());

        b1021.setPos(new Point2D(1,0));
        assertEquals(new Point2D(1,0), b1021.getPos());
    }

    @Test
    @DisplayName("Test fromJSON")
    void fromJSON() {
        Block b1011test = Block.fromJSON(new JSONObject(new String("{"+"pos_x"+":1, "+"pos_y"+":0,"+"width"+":1,"+"height"+":1}")));
        Block b1022test = Block.fromJSON(new JSONObject(new String("{"+"pos_x"+":1, "+"pos_y"+":0,"+"width"+":2,"+"height"+":2}")));
        assertTrue(b1011.equals(b1011test));
        assertTrue(b1022.equals(b1022test));

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Block b1011testErr = Block.fromJSON(new JSONObject(new String("{"+"posx"+":1, "+"pos_y"+":0,"+"width"+":1,"+"height"+":1}")));
        });
        Assertions.assertEquals("Block constructor values are wrong", thrown.getMessage());
    }

    @Test
    @DisplayName("Test toJSON")
    void toJSON() {
        JSONObject jsonObject1 = new JSONObject(new String("{"+"pos_x"+":1.0, "+"pos_y"+":0.0,"+"width"+":1,"+"height"+":1}"));
        JSONObject jsonObject2 = b1011.toJSON();
        assertEquals(jsonObject1.get("pos_x").toString(), jsonObject2.get("pos_x").toString());
        assertEquals(jsonObject1.get("pos_y").toString(), jsonObject2.get("pos_y").toString());
        assertEquals(jsonObject1.get("width").toString(), jsonObject2.get("width").toString());
        assertEquals(jsonObject1.get("height").toString(), jsonObject2.get("height").toString());
    }

    @Test
    @DisplayName("Test testToString")
    void testToString() {
        String s1 = new String("1.0 0.0 1 1");
        String s2 = b1011.toString();
        assertEquals(s1, s2);
    }

    @Test
    @DisplayName("Test pointIsIn")
    void pointIsIn() {
        Point2D p1 = new Point2D(1,2);
        assertFalse(b1011.pointIsIn(p1));
        assertFalse(b1021.pointIsIn(p1));
        assertFalse(b1012.pointIsIn(p1));
        assertFalse(b1022.pointIsIn(p1));
        assertFalse(b0312.pointIsIn(p1));
        Point2D p2 = new Point2D(1,1);
        assertFalse(b1011.pointIsIn(p2));
        assertTrue(b1021.pointIsIn(p2));
        assertFalse(b1012.pointIsIn(p2));
        assertTrue(b1022.pointIsIn(p2));
        assertFalse(b0312.pointIsIn(p2));
    }

    @Test
    void testEquals() {
    }

    @Test
    void testClone() {
    }
}