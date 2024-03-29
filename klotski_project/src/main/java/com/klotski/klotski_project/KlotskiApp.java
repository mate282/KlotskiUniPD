package com.klotski.klotski_project;

import com.klotski.view.GameView;
import com.klotski.view.HomeView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class KlotskiApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        navigateToHome(stage);
    }

    public static void main(String[] args) {
        launch();
    }



    public static void navigateToHome(Stage stage)throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(KlotskiApp.class.getResource("Views/Home/home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        //Load CSS
        String mainCss = KlotskiApp.class.getResource("Views/Home/Style/main-style.css").toExternalForm();
        scene.getStylesheets().add(mainCss);

        stage.setTitle("Klotski - Menu");
        stage.setScene(scene);
        stage.resizableProperty().set(false);
        stage.show();
    }
    public static void navigateToGame(Stage stage)throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(KlotskiApp.class.getResource("Views/Game/game-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), GameView.WINDOW_WIDTH, GameView.WINDOW_HEIGHT);
        //Load CSS
        String mainCss = KlotskiApp.class.getResource("Views/Game/Style/main-style.css").toExternalForm();
        scene.getStylesheets().add(mainCss);
        stage.setTitle("Klotski - Menu");
        stage.setScene(scene);
        stage.resizableProperty().set(false);
        stage.show();
    }

}
