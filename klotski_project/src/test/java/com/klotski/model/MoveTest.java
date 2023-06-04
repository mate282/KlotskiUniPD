package com.klotski.model;

import javafx.geometry.Point2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {

    Move move;
    Block b;
    Point2D blockPos;
    Point2D startPos ;
    Point2D endPos;

    @BeforeEach
    void setUp() {
        blockPos = new Point2D(2,2);
        startPos= new Point2D(0,0);
        endPos = new Point2D(1,1);
        b = new Block(blockPos, 1, 1);
        move = new Move(b,startPos,endPos);
    }

    @Test
    void getBlock() {
        assertTrue(b.equals(move.getBlock()));
    }

    @Test
    void getStart() {
        assertTrue(startPos.getX()==move.getStart().getX() && startPos.getY()==move.getStart().getY());
    }

    @Test
    void getDest() {
        assertTrue(endPos.getX()==move.getDest().getX() && endPos.getY()==move.getDest().getY());
    }

    @Test
    void toJSON() {
        assertNotNull(move.toJSON());
    }

    @Test
    void fromJSON() {
        assertNotNull(Move.fromJSON(move.toJSON()));
    }
}