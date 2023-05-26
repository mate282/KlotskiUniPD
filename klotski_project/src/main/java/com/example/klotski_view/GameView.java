package com.example.klotski_view;

import com.example.klotski_controller.GameController;
import com.example.klotski_model.Game;
import com.example.klotski_project.KlotskiApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.GridView;

import java.io.IOException;

public class GameView {
    @FXML
    private Button btn;
    GameController gameController;

    @FXML
    public void initialize() {
        gameController = GameController.getInstance();

    }


    @FXML
    protected void onBackButtonClick() throws IOException {
        loadHomeView((Stage)btn.getScene().getWindow());
    }


    private  void loadHomeView(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(KlotskiApp.class.getResource("Views/home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Klotski - Menu");
        stage.setScene(scene);
        stage.show();

    }

}
