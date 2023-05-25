package com.example.klotski_project;

import com.example.klotski_controller.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class KlotskiApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GameController controller = new GameController(stage);
        controller.loadHomeView();
    }

    public static void main(String[] args) {
        launch();
    }
}