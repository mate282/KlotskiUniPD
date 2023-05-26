package com.example.klotski_view;


import com.example.klotski_controller.GameController;
import com.example.klotski_project.KlotskiApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;

public class HomeView {

    @FXML
    private Label welcomeText;
    @FXML
    private Button btn;
    GameController gameController;

    @FXML
    public void initialize(){
        Scene scene = btn.getScene();
        gameController = GameController.getInstance();
    }

    @FXML
    protected void onStartButtonClick() throws IOException {

        if(gameController.chooseConfiguration("level1")){
            if(gameController.startGame()){
                loadGameView((Stage)btn.getScene().getWindow());
            }
        }


    }


    private void loadGameView(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(KlotskiApp.class.getResource("Views/game-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Klotski - Game");
        stage.setScene(scene);
        stage.show();
    }


}
