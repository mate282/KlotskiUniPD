package com.klotski.view;

import com.klotski.controller.GameController;
import com.klotski.klotski_project.KlotskiApp;
import com.klotski.model.Block;
import com.klotski.model.Board;
import com.klotski.model.Observer;
import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.security.Key;
import java.security.KeyPair;
import java.util.List;

public class GameView implements Observer {

    private enum MOVEMENT{
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public static final int WINDOW_WIDTH = 750;
    public static final int WINDOW_HEIGHT= 550;

    private double mouseStartX;
    private double mouseStartY;



    @FXML
    private Label movesCounterLbl;
    @FXML
    private Button backBtn;
    @FXML
    private GridPane gridBoard;


    GameController gameController;

    @FXML
    public void initialize() {
        gameController = GameController.getInstance();
        gameController.getGameObservable().addListener(this);
        Board board = gameController.getActualBoard();
        movesCounterLbl.setText("Moves Counter: " + gameController.getMovesCount());
        showBoard(board.getBlocks());
    }


    @FXML
    protected void onBackButtonClick() throws IOException {
        gameController.stopGame();
        KlotskiApp.navigateToHome((Stage)backBtn.getScene().getWindow());
    }

    @FXML
    protected void onSaveButtonClick(){
        if(gameController.saveGame())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game saved");
            alert.setHeaderText("Game saved correctly");
            alert.setContentText("Now you can exit without loosing progress");

            alert.show();
        }
    }

    @FXML
    protected void onUndoButtonClick(){
        if(gameController.undoMove()){
            showBoard(gameController.getActualBoard().getBlocks());
        }
        else{
            int a = 3;
        }
    }

    @FXML
    protected void onResetButtonClick(){
        if(gameController.resetGame()){
            showBoard(gameController.getActualBoard().getBlocks());
        }
    }


    private void showBoard(List<Block> blockList){
        //reset board
        gridBoard.getChildren().clear();
        //show actual board
        for(Block b: blockList){
            gridBoard.add(setPaneBlock(b),(int)b.getPos().getX(), (int)b.getPos().getY());
        }
    }

    private Pane setPaneBlock(Block b){
        Pane pane = new Pane();
        pane.getStyleClass().add("gridBlock");

        if(b.getDim()==1){
            pane.getStyleClass().add("gridBlockSingle");
        }else if(b.getDim()==2){
            pane.getStyleClass().add("gridBlockDouble");
        }else if(b.getDim()==4){
            pane.getStyleClass().add("gridBlockMax");
        }

        GridPane.setRowSpan(pane,b.getHeight());
        GridPane.setColumnSpan(pane,b.getWidth());




        //save start position of block
        pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouseStartX = mouseEvent.getX();
                mouseStartY = mouseEvent.getY();
            }
        });
        pane.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
               blockMovementHandler(pane,mouseEvent);
            }
        });

        return pane;
    }

    private void blockMovementHandler(Pane pane, MouseEvent mouseEvent){
        double endX = mouseEvent.getX();
        double endY = mouseEvent.getY();
        double deltaX = endX-mouseStartX;
        double deltaY = endY-mouseStartY;

        Point2D startPos = new Point2D(GridPane.getColumnIndex(pane),GridPane.getRowIndex(pane));

        //Check main movement direction

        //LEFT/RIGHT
        if(Math.abs(deltaX)>Math.abs(deltaY)){
            //Move right
            if(deltaX>0){
                moveBlock(pane,startPos,MOVEMENT.RIGHT);
            }
            //Move left;
            else{
                moveBlock(pane,startPos,MOVEMENT.LEFT);
            }
        }
        //UP/DOWN
        else{
            //Move Down
            if(deltaY>0){
                moveBlock(pane,startPos,MOVEMENT.DOWN);
            }
            //Move Up
            else{
                moveBlock(pane,startPos,MOVEMENT.UP);
            }
        }
    }

    private void moveBlock(Pane pane, Point2D startPos, MOVEMENT moveDirection ){
        switch (moveDirection) {
            case UP -> {
                if (startPos.getY() > 0) {
                    Point2D endPos = new Point2D(startPos.getX(), startPos.getY() - 1);
                    //ask to move
                    if (gameController.makeMove(startPos, endPos)) {
                        GridPane.setRowIndex(pane, (int) endPos.getY());
                    } else {
                        //Error Animation
                        setInvalidMoveAnimation(pane).play();
                    }
                }
            }
            case DOWN -> {
                if (startPos.getY() < gridBoard.getRowCount() - 1) {
                    Point2D endPos = new Point2D(startPos.getX(), startPos.getY() + 1);
                    //ask to move
                    if (gameController.makeMove(startPos, endPos)) {
                        GridPane.setRowIndex(pane, (int) endPos.getY());
                    } else {
                        //Error Animation
                        setInvalidMoveAnimation(pane).play();
                    }
                }
            }
            case LEFT -> {
                if (startPos.getX() > 0) {
                    Point2D endPos = new Point2D(startPos.getX() - 1, startPos.getY());
                    //ask to move
                    if (gameController.makeMove(startPos, endPos)) {
                        GridPane.setColumnIndex(pane, (int) endPos.getX());
                    } else {
                        //Error Animation
                        setInvalidMoveAnimation(pane).play();
                    }
                }
            }
            case RIGHT -> {
                if (startPos.getX() < gridBoard.getColumnCount() -1 ) {
                    Point2D endPos = new Point2D(startPos.getX() + 1, startPos.getY());
                    //ask to move
                    if (gameController.makeMove(startPos, endPos)) {
                        GridPane.setColumnIndex(pane, (int) endPos.getX());
                    }
                    else {
                        //Error Animation
                        setInvalidMoveAnimation(pane).play();
                    }
                }
            }
        }
    }


    public void update(int movesCounter, boolean win){
        movesCounterLbl.setText("Moves Counter: " + movesCounter);
        if(win){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Winning");
            alert.setHeaderText("Congratulations");
            alert.setContentText("You passed this level");

            alert.show();
        }
    }

    private Animation setInvalidMoveAnimation(Node node){
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(15));
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(15);
        rotateTransition.setAutoReverse(true);
        rotateTransition.setNode(node);
        rotateTransition.setCycleCount(10);
        return rotateTransition;
    }



}
