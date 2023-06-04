package com.klotski.model;

import javafx.geometry.Point2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.PipedInputStream;
import java.net.http.WebSocket;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    Game game;
    BeginningConfiguration configuration;
    LevelSolution levelSolution;


    @BeforeEach
    void setUp() {
        configuration = PersistenceDataService.loadConfig("level2");
        levelSolution = PersistenceDataService.loadSolution(configuration);
        game = new Game();
    }

    @Test
    @DisplayName("Test isGameStarted")
    void isGameStarted() {
        assertFalse(game.isGameStarted());
    }

    @Test
    @DisplayName("Test getBoard")
    void getBoard() {
        if(game.isGameStarted()){
            assertNotNull(game.getBoard());
        }
    }

    @Test
    @DisplayName("Test getProgress")
    void getProgress() {
        if(game.isGameStarted()){
            assertNotNull(game.getProgress());
        }
    }

    @Test
    @DisplayName("Test start new game")
    void startGame() {
        game.stopGame();
        assertFalse(game.isGameStarted());
        game.startGame(configuration,levelSolution);
        assertNotNull(game.getBoard());
        assertNotNull(game.getProgress());
        assertTrue(game.isGameStarted());
    }

    @Test
    @DisplayName("Test load game")
    void loadGame() {
        game.stopGame();

        game.startGame(configuration,levelSolution);
        SavedGame save = game.saveGame();
        game.stopGame();

        assertTrue(game.loadGame(save,levelSolution));
        assertNotNull(game.getBoard());
        assertNotNull(game.getProgress());
        assertTrue(game.isGameStarted());
    }

    @Test
    @DisplayName("Test save game")
    void saveGame() {
        if(game.isGameStarted()){
            assertNotNull(game.saveGame());
        }
    }

    @Test
    @DisplayName("Test reset game")
    void resetGame() {
        if(game.isGameStarted()){
            Move move = new Move(new Block(new Point2D(0,4),1,1),new Point2D(0,4),new Point2D(1,4));
            game.makeMove(move);
            assertTrue(game.resetGame());
            assertEquals(0,game.getProgress().getMovesCounter());
            assertNotNull(game.getBoard().findBlockByPosition(new Point2D(0,4)));

        }

    }

    @Test
    @DisplayName("Test stop game")
    void stopGame() {
        game.startGame(configuration,levelSolution);
        game.stopGame();
        assertFalse(game.isGameStarted());
    }

    @Test
    @DisplayName("Test help")
    void getHelp() {
        if(game.isGameStarted()){
            assertTrue(game.getHelp());
        }
    }

    @Test
    @DisplayName("Test make move")
    void makeMove() {
        if(game.isGameStarted()){
            Move move = new Move(new Block(new Point2D(0,4),1,1),new Point2D(0,4),new Point2D(1,4));
            assertTrue(game.makeMove(move));
            assertTrue(game.getProgress().getMovesCounter()>0);
            assertNotNull(game.getBoard().findBlockByPosition(new Point2D(1,4)));
        }
    }

    @Test
    @DisplayName("Test undo move")
    void undoMove() {
        if(game.isGameStarted()){
            Move move = new Move(new Block(new Point2D(0,4),1,1),new Point2D(0,4),new Point2D(1,4));
            game.makeMove(move);
            assertTrue(game.undoMove());
            assertEquals(0,game.getProgress().getMovesCounter());
            assertNotNull(game.getBoard().findBlockByPosition(new Point2D(0,4)));
        }
    }


}