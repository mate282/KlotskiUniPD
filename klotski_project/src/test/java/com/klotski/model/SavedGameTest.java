package com.klotski.model;

import javafx.geometry.Point2D;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SavedGameTest {

    SavedGame savedGame;
    Board lastBoard;
    GameProgress gameProgress;
    LocalDateTime savedDate;

    @BeforeEach
    void setUp() {
        ArrayList<Block> blocks = new ArrayList<Block>(0);
        blocks.add(new Block(new Point2D(0, 0), 2, 1));
        blocks.add(new Block(new Point2D(1, 0), 2, 2));
        blocks.add(new Block(new Point2D(3, 0), 2, 1));
        BeginningConfiguration config = new BeginningConfiguration("level1", blocks);
        savedDate = LocalDateTime.now();
        lastBoard = new Board(5, 4, blocks);
        gameProgress = new GameProgress(config);
        savedGame = new SavedGame(lastBoard, gameProgress, savedDate);
    }

    @Test
    @DisplayName("Test getLastBoard")
    void getLastBoard() {
        assertTrue(lastBoard.equals(savedGame.getLastBoard()));
    }

    @Test
    @DisplayName("Test getGameProgress")
    void getGameProgress() {
        assertTrue(gameProgress.equals(savedGame.getGameProgress()));
    }

    @Test
    @DisplayName("Test getSaveDate")
    void getSaveDate() {
        assertTrue(savedDate.equals(savedGame.getSaveDate()));
    }

    @Test
    @DisplayName("Test toJSON and fromJSON")
    void fromToJSON() {
        JSONObject jsonObject = savedGame.toJSON();
        SavedGame g = SavedGame.fromJSON(jsonObject);
        assertEquals(g.toJSON().toString(), savedGame.toJSON().toString());
        assertEquals(g.toJSON().toString(), jsonObject.toString());
    }

}
