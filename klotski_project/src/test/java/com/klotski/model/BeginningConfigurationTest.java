package com.klotski.model;

import javafx.geometry.Point2D;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BeginningConfigurationTest {
    BeginningConfiguration config;

    @BeforeEach
    void setUp() {
        ArrayList<Block> blocks = new ArrayList<Block>(0);
        blocks.add(new Block(new Point2D(0, 0), 2, 1));
        blocks.add(new Block(new Point2D(1, 0), 2, 2));
        blocks.add(new Block(new Point2D(3, 0), 2, 1));

        config = new BeginningConfiguration("level1", blocks);
    }

    @Test
    @DisplayName("Test getName")
    void getName() {
        assertEquals("level1", config.getName());
    }

    @Test
    @DisplayName("Test getBlocks")
    void getBlocks() {
        ArrayList<Block> blocks = new ArrayList<Block>(0);
        blocks.add(new Block(new Point2D(0, 0), 2, 1));
        blocks.add(new Block(new Point2D(1, 0), 2, 2));
        blocks.add(new Block(new Point2D(3, 0), 2, 1));
        BeginningConfiguration c = new BeginningConfiguration("level1", blocks);
        assertTrue(c.equals(config));

    }

    @Test
    @DisplayName("Test fromJSON")
    void fromJSON() {
        String s = new String("{"+"blocks"+":["+
                "{"+"pos_y"+":0,"+"pos_x"+":0,"+"width"+":1,"+"height"+":2}, "+
                "{"+"pos_y"+":0,"+"pos_x"+":1,"+"width"+":2,"+"height"+":2}, "+
                "{"+"pos_y"+":0,"+"pos_x"+":3,"+"width"+":1,"+"height"+":2}], "+"name"+":level1}");
        JSONObject jsonObject = new JSONObject(s);
        BeginningConfiguration c = BeginningConfiguration.fromJSON(jsonObject);
        assertTrue(c.equals(config));
        assertNotNull(BeginningConfiguration.fromJSON(config.toJSON()));

    }

    @Test
    @DisplayName("Test toJSON")
    void toJSON() {
        String s1 = new String("{\"blocks\":[{\"pos_y\":0,\"pos_x\":0,\"width\":1,\"height\":2},{\"pos_y\":0,\"pos_x\":1,\"width\":2,\"height\":2},{\"pos_y\":0,\"pos_x\":3,\"width\":1,\"height\":2}],\"name\":\"level1\"}");
        String s2 = config.toJSON().toString();
        assertTrue(s1.equals(s2));
        assertNotNull(config.toJSON());
    }

    @Test
    @DisplayName("Test equals")
    void equals() {
        ArrayList<Block> blocks = new ArrayList<Block>(0);
        blocks.add(new Block(new Point2D(0, 0), 2, 1));
        blocks.add(new Block(new Point2D(1, 0), 2, 2));
        blocks.add(new Block(new Point2D(3, 0), 2, 1));
        BeginningConfiguration config1 = new BeginningConfiguration("level1", blocks);
        BeginningConfiguration config2 = new BeginningConfiguration("level2", blocks);
        assertTrue(config1.equals(config));
        assertTrue(config.equals(config1));
        assertFalse(config2.equals(config));
        assertFalse(config.equals(config2));

        ArrayList<Block> blocks2= new ArrayList<Block>(0);
        blocks2.add(new Block(new Point2D(0,0), 2,1));
        blocks2.add(new Block(new Point2D(1,0), 2,2));
        blocks2.add(new Block(new Point2D(3,0), 2,1));
        blocks2.add(new Block(new Point2D(3,1), 1,1));
        BeginningConfiguration config3 = new BeginningConfiguration("level1", blocks2);
        assertFalse(config3.equals(config));
        assertFalse(config.equals(config3));
    }
}

