package com.klotski.model;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersistenceDataServiceTest {

    SavedGame savedGame;
    Board board;

    BeginningConfiguration config;

    GameProgress gameProgress;
    @BeforeEach
    void setUp() {
        ArrayList<Block> blocks= new ArrayList<Block>(0);

        blocks.add(new Block(new Point2D(0,0), 1,1));
        blocks.add(new Block(new Point2D(0,1), 1,1));

        blocks.add(new Block(new Point2D(3,0), 1,1));
        blocks.add(new Block(new Point2D(3,1), 1,1));

        blocks.add(new Block(new Point2D(0,2), 2,1));
        blocks.add(new Block(new Point2D(3,2), 2,1));

        blocks.add(new Block(new Point2D(1,2), 1,1));
        blocks.add(new Block(new Point2D(1,3), 1,1));

        blocks.add(new Block(new Point2D(2,2), 1,1));
        blocks.add(new Block(new Point2D(2,3), 1,1));

        blocks.add(new Block(new Point2D(0,4), 1,1));
        blocks.add(new Block(new Point2D(3,4), 1,1));


        blocks.add(new Block(new Point2D(1,0), 2,2));

        board= new Board(5,4,blocks);
        config = new BeginningConfiguration("level1",blocks);
        gameProgress = new GameProgress(config);
        savedGame = new SavedGame(board,gameProgress);
    }

    @Test
    @DisplayName("Test Saving Data")
    void saveGameData() {
        if(PersistenceDataService.saveGameData(savedGame)){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(PersistenceDataService.DATE_FORMAT);
            String fileName = System.getProperty("user.home")+PersistenceDataService.SAVEFILE_PATH+PersistenceDataService.SAVEFILE_NAME +dtf.format(savedGame.getSaveDate())+PersistenceDataService.FILE_EXT;
            assertFalse(Files.exists(Path.of(fileName)));

        }
    }

    @Test
    @DisplayName("Test Loading List of Data")
    void loadAllGameData() {
        List<String> files = PersistenceDataService.loadAllGameData();
        assertFalse(files.isEmpty());
    }

    @Test
    @DisplayName("Test Loading Data")
    void loadGameData() {
        String fileName = (PersistenceDataService.loadAllGameData()).get(0);
        assertNotNull(PersistenceDataService.loadGameData(fileName));
    }

    @Test
    @DisplayName("Test loading List of configs")
    void loadAllConfigurations() {
        List<String> files = PersistenceDataService.loadAllConfigurations();
        assertFalse(files.isEmpty());
    }

    @Test
    @DisplayName("Test loading config")
    void loadConfig() {
        List<String> files = PersistenceDataService.loadAllConfigurations();
        for(String file: files){
            assertNotNull(PersistenceDataService.loadConfig(file));
        }
    }

    @Test
    @DisplayName("Test loading solution")
    void loadSolution() {
        List<String> files = PersistenceDataService.loadAllConfigurations();
        for(String file: files){
            BeginningConfiguration config = PersistenceDataService.loadConfig(file);
            assertNotNull(PersistenceDataService.loadSolution(config));
        }
    }
}