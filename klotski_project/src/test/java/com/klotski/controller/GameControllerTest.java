package com.klotski.controller;

import com.klotski.model.Block;
import com.klotski.model.Move;
import javafx.geometry.Point2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    GameController controller;
    @BeforeEach
    void setUp() {
        controller = GameController.getInstance();
    }

    @Test
    void getInstance() {
        assertNotNull(GameController.getInstance());
    }

    @Test
    void startNewGame() {
        controller.stopGame();
        assertTrue(controller.startNewGame("level2"));
        controller.stopGame();
        assertFalse(controller.startNewGame("level"));
    }

    @Test
    void startSavedGame() {
        controller.stopGame();
        String saveToLoad = controller.loadGameSaves().get(0);
        assertTrue(controller.startSavedGame(saveToLoad));
    }


    @Test
    void saveGame() {
        controller.stopGame();
        controller.startNewGame("level2");
        assertTrue(controller.saveGame());

    }

    @Test
    void loadAllConfigurations() {
        assertFalse(controller.loadAllConfigurations().isEmpty());
    }

    @Test
    void getActualBoard() {
        controller.stopGame();
        controller.startNewGame("level2");
        assertNotNull(controller.getActualBoard());
    }

    @Test
    void getMovesCount() {
        controller.stopGame();
        controller.startNewGame("level2");
        assertNotNull(controller.getMovesCount());
    }

    @Test
    void loadGameSaves() {
        assertFalse(controller.loadGameSaves().isEmpty());
    }

    @Test
    void makeMove() {
        controller.stopGame();
        controller.startNewGame("level2");
        assertTrue(controller.makeMove(new Point2D(0,4),new Point2D(1,4)));
        assertTrue(controller.getMovesCount()>0);
    }

    @Test
    void undoMove() {
        controller.stopGame();
        controller.startNewGame("level2");
        controller.makeMove(new Point2D(0,4),new Point2D(1,4));
        assertTrue(controller.undoMove());
        assertEquals(0,controller.getMovesCount());
    }

    @Test
    void getHelp() {
        controller.stopGame();
        controller.startNewGame("level2");
        assertTrue(controller.getHelp());
        assertTrue(controller.getMovesCount()>0);
    }

    @Test
    void resetGame() {
        controller.stopGame();
        controller.startNewGame("level2");
        controller.getHelp();
        assertTrue(controller.resetGame());
        assertEquals(0,controller.getMovesCount());
    }
}