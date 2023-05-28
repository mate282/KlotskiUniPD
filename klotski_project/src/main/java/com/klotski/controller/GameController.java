package com.klotski.controller;

import com.klotski.model.*;

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
    BeginningConfiguration config;


    public GameController() {
        game = new Game();
    }


    public boolean startGame() {
        if (!game.isGameStarted() && config!=null) {
            LevelSolution sol = PersistenceDataService.loadSolution(config);
            if(game.startGame(config,sol)){
                return true;
            }
        }
        return false;
    }

    public boolean chooseConfiguration(String configName){
        config = PersistenceDataService.loadConfig(configName);
        return config!=null;
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





}
