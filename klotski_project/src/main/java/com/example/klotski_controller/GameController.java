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
    Game game;
    BeginningConfiguration config;

    HomeView homeView;
    GameView gameView;
    Stage stage;


    public GameController(Stage stage) {
        game = new Game();
        this.stage = stage;
    }


    public boolean startGame() {
        if (!game.isGameStarted() && config!=null) {
            LevelSolution sol = PersistenceDataService.loadSolution(config);
            if(game.startGame(config,sol)){
                notify();
                return true;
            }
        }
        return false;
    }

    public boolean chooseConfiguration(String configName){
        config = PersistenceDataService.loadConfig(configName);
        return config!=null;
    }



    public void loadHomeView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(KlotskiApp.class.getResource("Views/home-view.fxml"));
        homeView = fxmlLoader.getController();
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Klotski");
        stage.setScene(scene);
        stage.show();


    }
}
