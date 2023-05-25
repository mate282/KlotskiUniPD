package com.example.klotski_view;


import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeView {

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


}
