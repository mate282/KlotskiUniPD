package com.klotski.model;

import javafx.geometry.Point2D;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameProgressTest {

    BeginningConfiguration config;
    GameProgress progress;
    Move move;

    @BeforeEach
    void setUp() {
        Block b = new Block(new Point2D(0,0), 1,1);
        move = new Move(b, new Point2D(0,0), new Point2D(1,0));
        config = PersistenceDataService.loadConfig(PersistenceDataService.loadAllConfigurations().get(0));
        progress = new GameProgress(config);
    }

    @Test
    @DisplayName("Test getBeginConf")
    void getBeginConf() {
        assertEquals(config, progress.getBeginConf());
    }

    @Test
    @DisplayName("Test getMovesCounter")
    void getMovesCounter() {
        int movesCounter = progress.getMovesCounter();
        progress.addMove(move);
        assertNotEquals(movesCounter, progress.getMovesCounter());

    }

    @Test
    @DisplayName("Test undoLastMove")
    void undoLastMove() {
        assertNull(progress.undoLastMove());
        progress.addMove(move);
        int movesCounter = progress.getMovesCounter();
        assertNotNull(progress.undoLastMove());
        assertNotEquals(movesCounter, progress.getMovesCounter());
    }

    @Test
    @DisplayName("Test addMove")
    void addMove() {
        int movesCounter = progress.getMovesCounter();
        progress.addMove(move);
        assertNotEquals(movesCounter, progress.getMovesCounter());
    }

    @Test
    @DisplayName("Test resetProgress")
    void resetProgress() {

        progress.addMove(move);
        if(progress.resetProgress())
        {
            assertEquals(0,progress.getMovesCounter());
        }
    }

    @Test
    @DisplayName("Test toJSON")
    void toJSON() {
        assertNotNull(progress.toJSON());
    }

    @Test
    @DisplayName("Test fromJSON")
    void fromJSON() {
        JSONObject json = progress.toJSON();
        assertNotNull(GameProgress.fromJSON(json));
    }
}