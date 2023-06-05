package com.klotski.view;

import com.klotski.controller.GameController;
import com.klotski.klotski_project.KlotskiApp;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HomeView {

    @FXML
    private VBox vBoxHome;
    @FXML
    private Label welcomeText;
    @FXML
    private Button btnStart;
    @FXML
    private Button btnChoose;
    @FXML
    private Button btnLoad;
    @FXML
    private ComboBox<String> cmbChoose;
    @FXML
    private ComboBox<String> cmbLoad;
    GameController gameController;

    /**
     * Reset thet combo boxes state
     */
    private void resetComboBox() {
        cmbChoose.hide();
        cmbChoose.setVisible(false);
        cmbLoad.hide();
        cmbLoad.setVisible(false);
    }

    /**
     * Initializer function
     */
    @FXML
    public void initialize(){
        Scene scene = btnStart.getScene();
        gameController = GameController.getInstance();
    }

    /**
     * Method called when the home-view is clicked
     */
    @FXML
    protected void onVBoxHomeMouseClicked() throws IOException {
        resetComboBox();
    }

    /**
     * Called when Start button is clicked
     */
    @FXML
    protected void onStartButtonClick() throws IOException {
        resetComboBox();
        if(gameController.startNewGame(gameController.loadAllConfigurations().get(0))){
            KlotskiApp.navigateToGame((Stage)btnStart.getScene().getWindow());
        }
    }

    /**
     * Called when Choose Config button is clicked
     */
    @FXML
    protected void onChooseConfigButtonClick() throws IOException {
        List<String> ls = gameController.loadAllConfigurations();
        resetComboBox();
        cmbChoose.getItems().clear();;
        cmbChoose.getItems().addAll(ls);
        if(ls.isEmpty()) {
            cmbChoose.setPromptText("No config");
        } else {
            cmbChoose.setPromptText("Please select config");
            cmbChoose.show();
            cmbChoose.setVisibleRowCount(3);
        }
        cmbChoose.setVisible(true);
    }

    /**
     * Called when an item of Choose Combo Box is clicked
     */
    @FXML
    protected void onChooseConfigurationComboBoxClick() throws IOException {
        String conf = cmbChoose.getValue().toString();

        if(gameController.startNewGame(conf)){
            KlotskiApp.navigateToGame((Stage)btnChoose.getScene().getWindow());
        }
        resetComboBox();
        cmbChoose.hide();
        cmbChoose.setVisible(false);
    }

    /**
     * Called when Load Game button is clicked
     */
    @FXML
    protected void onLoadGameButtonClick() throws IOException {
        List<String> ls = gameController.loadGameSaves();
        resetComboBox();
        cmbLoad.getItems().clear();;
        cmbLoad.getItems().addAll(ls);
        if(ls.isEmpty()) {
            cmbLoad.setPromptText("No games");
        } else {
            cmbLoad.setPromptText("Please select game");
            cmbLoad.show();
            cmbLoad.setVisibleRowCount(3);
        }
        cmbLoad.setVisible(true);
    }

    /**
     * Called when an item of Load Game Combo Box is clicked
     */
    @FXML
    protected void onLoadGameComboBoxClick() throws IOException {
        String game = cmbLoad.getValue().toString();

        if(gameController.startSavedGame(game)){
            KlotskiApp.navigateToGame((Stage)btnLoad.getScene().getWindow());
        }
        resetComboBox();
        cmbLoad.hide();
        cmbLoad.setVisible(false);
    }
}
