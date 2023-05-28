package com.example.klotski_controller;

import com.example.klotski_model.*;
import com.example.klotski_project.KlotskiApp;
import com.example.klotski_view.GameView;
import com.example.klotski_view.HomeView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

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

    public Board getActualBoard(){
        if(game.isGameStarted()){
            return game.getBoard();
        }
        return null;
    }





}
