package com.example.klotski_project;


import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeViewController {

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


}