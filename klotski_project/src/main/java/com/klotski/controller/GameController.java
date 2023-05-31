package com.klotski.controller;

import com.klotski.model.*;
import javafx.geometry.Point2D;

import java.util.List;

public class GameController {

    private static GameController gameController;

    public static GameController getInstance(){
        if(gameController == null){
            gameController = new GameController();
        }
        return gameController;
    }



    Game game;


    public GameController() {
        game = new Game();
    }


    public Observable getGameObservable(){
        return game;
    }


    public boolean startNewGame(String configName) {
        BeginningConfiguration config = PersistenceDataService.loadConfig(configName);
        if (!game.isGameStarted() && config!=null) {
            LevelSolution sol = PersistenceDataService.loadSolution(config);
            return game.startGame(config,sol);
        }
        return false;
    }
    public boolean startSavedGame(String saveName){
        if(!game.isGameStarted()){
            SavedGame savedGame = PersistenceDataService.loadGameData(saveName);
            if(savedGame!=null){
                LevelSolution sol = PersistenceDataService.loadSolution(savedGame.getGameProgress().getBeginConf());
                return game.loadGame(savedGame,sol);
            }
        }

        return false;
    }
    public void stopGame(){
        game.stopGame();
    }
    public boolean saveGame(){
        if(game.isGameStarted()){
            SavedGame save = game.saveGame();
            return PersistenceDataService.saveGameData(save);
        }
        return false;
    }



    public List<String> loadAllConfigurations(){
        return PersistenceDataService.loadAllConfigurations();
    }
    public Board getActualBoard(){
        if(game.isGameStarted()){
            return game.getBoard();
        }
        return null;
    }
    public List<String> loadGameSaves(){
        return PersistenceDataService.loadAllGameData();
    }
    public boolean makeMove(Point2D start, Point2D end){
        if(game.isGameStarted()){
            Block block = game.getBoard().findBlockByPosition(start);
            Move move = new Move(block,start,end);
            return game.makeMove(move);
        }
        return false;
    }
    public boolean undoMove(){
        if(game.isGameStarted()){
            return game.undoMove();
        }
        return false;
    }

    public boolean resetGame(){
        if(game.isGameStarted()){
            return game.resetGame();
        }
        return false;
    }



}
