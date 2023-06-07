package com.klotski.model;

import javafx.geometry.Point2D;
import javafx.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LevelSolutionTest {
    Board b, b1, b2;
    LevelSolution levelSolution;

    @BeforeEach
    void setUp() {
        ArrayList<Block> blocks= new ArrayList<Block>(0);
        blocks.add(new Block(new Point2D(0,0), 2,1));
        blocks.add(new Block(new Point2D(1,0), 2,2));
        b = new Board(5,4,blocks);
        b1 = b.clone();

        ArrayList<Pair<Board, Move>> bm = new ArrayList<Pair<Board, Move>>();
        // Step 1
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(1,0), 2,2), new Point2D(1,0), new Point2D(2,0))));
        b.move(                                 new Move(new Block(new Point2D(1,0), 2,2), new Point2D(1,0), new Point2D(2,0)));
        // Step 2
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(0,0), 2,1), new Point2D(0,0), new Point2D(0,2))));
        b.move(                                 new Move(new Block(new Point2D(0,0), 2,1), new Point2D(0,0), new Point2D(0,2)));
        b2 = b.clone();
        levelSolution = new LevelSolution(bm);
    }

    @Test
    @DisplayName("Test getBoardsMovs")
    void getBoardsMovs() {
    }

    @Test
    @DisplayName("Test toJSON")
    void toJSON() {
    }

    @Test
    @DisplayName("Test fromJSON")
    void fromJSON() {
    }

    @Test
    @DisplayName("Test getMove")
    void getMove() {
        // Check a found association board - solution
        Move m = levelSolution.getMove(b1);
        Move m1 = new Move(new Block(new Point2D(1,0), 2,2), new Point2D(1,0), new Point2D(2,0));
        assertEquals(m.getDest().getX(), m1.getDest().getX());
        assertEquals(m.getDest().getY(), m1.getDest().getY());
        assertEquals(m.getStart().getX(), m1.getStart().getX());
        assertEquals(m.getStart().getY(), m1.getStart().getY());
        assertTrue(m.getBlock().equals(m1.getBlock()));

        // Check a not found board snapshot
        Move m2 = levelSolution.getMove(b2);
        assertEquals(null, m2);
    }
}