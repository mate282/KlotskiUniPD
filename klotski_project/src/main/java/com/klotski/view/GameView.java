package com.klotski.view;

import com.klotski.controller.GameController;
import com.klotski.klotski_project.KlotskiApp;
import com.klotski.model.Block;
import com.klotski.model.Board;
import com.klotski.model.Observer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class GameView implements Observer {

    private enum MOVEMENT{
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    static final int MIN_BLOCK_DIM = 70;
    public static final int WINDOW_WIDTH = 700;
    public static final int WINDOW_HEIGHT=500;

    private double mouseStartX;
    private double mouseStartY;




    @FXML
    private Button btn;
    @FXML
    private GridPane gridBoard;

    GameController gameController;

    @FXML
    public void initialize() {
        gameController = GameController.getInstance();
        gameController.getGameObservable().addListener(this);
        Board board = gameController.getActualBoard();
        showBoard(board.getBlocks());
    }


    @FXML
    protected void onBackButtonClick() throws IOException {
        gameController.stopGame();
        KlotskiApp.navigateToHome((Stage)btn.getScene().getWindow());
    }


    private void showBoard(List<Block> blockList){
        for(Block b: blockList){
            gridBoard.add(setRectBlock(b),(int)b.getPos().getX(), (int)b.getPos().getY());
        }
    }

    private Rectangle setRectBlock(Block b){
        Rectangle rect = new Rectangle();
        rect.setWidth(b.getWidth()*MIN_BLOCK_DIM);
        rect.setHeight(b.getHeight()*MIN_BLOCK_DIM);


        rect.fillProperty().set(b.getColor());

        GridPane.setValignment(rect, VPos.TOP);
        GridPane.setHalignment(rect, HPos.LEFT);

        //save start position of block
        rect.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouseStartX = mouseEvent.getX();
                mouseStartY = mouseEvent.getY();
            }
        });
        rect.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
               blockMovementHandler(rect,mouseEvent);
            }
        });

        return rect;
    }

    private void blockMovementHandler(Rectangle rect, MouseEvent mouseEvent){
        double endX = mouseEvent.getX();
        double endY = mouseEvent.getY();
        double deltaX = endX-mouseStartX;
        double deltaY = endY-mouseStartY;

        Point2D startPos = new Point2D(GridPane.getColumnIndex(rect),GridPane.getRowIndex(rect));

        //Check main movement direction

        //LEFT/RIGHT
        if(Math.abs(deltaX)>Math.abs(deltaY)){
            //Move right
            if(deltaX>0){
                moveBlock(rect,startPos,MOVEMENT.RIGHT);
            }
            //Move left;
            else{
                moveBlock(rect,startPos,MOVEMENT.LEFT);
            }
        }
        //UP/DOWN
        else{
            //Move Down
            if(deltaY>0){
                moveBlock(rect,startPos,MOVEMENT.DOWN);
            }
            //Move Up
            else{
                moveBlock(rect,startPos,MOVEMENT.UP);
            }
        }
    }

    private void moveBlock(Rectangle rect, Point2D startPos, MOVEMENT moveDirection ){
        switch (moveDirection) {
            case UP -> {
                if (startPos.getY() > 0) {
                    Point2D endPos = new Point2D(startPos.getX(), startPos.getY() - 1);
                    //ask to move
                    if (gameController.makeMove(startPos, endPos)) {
                        GridPane.setRowIndex(rect, (int) endPos.getY());
                    } else {
                        //Error Animation
                    }
                }
            }
            case DOWN -> {
                if (startPos.getY() < gridBoard.getRowCount() - 1) {
                    Point2D endPos = new Point2D(startPos.getX(), startPos.getY() + 1);
                    //ask to move
                    if (gameController.makeMove(startPos, endPos)) {
                        GridPane.setRowIndex(rect, (int) endPos.getY());
                    } else {
                        //Error Animation
                    }
                }
            }
            case LEFT -> {
                if (startPos.getX() > 0) {
                    Point2D endPos = new Point2D(startPos.getX() - 1, startPos.getY());
                    //ask to move
                    if (gameController.makeMove(startPos, endPos)) {
                        GridPane.setColumnIndex(rect, (int) endPos.getX());
                    } else {
                        //Error Animation
                    }
                }
            }
            case RIGHT -> {
                if (startPos.getX() < gridBoard.getColumnCount() -1 ) {
                    Point2D endPos = new Point2D(startPos.getX() + 1, startPos.getY());
                    //ask to move
                    if (gameController.makeMove(startPos, endPos)) {
                        GridPane.setColumnIndex(rect, (int) endPos.getX());
                    }
                } else {
                    //Error Animation
                }
            }
        }
    }


    public void update(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winning");
        alert.setHeaderText("Congratulations");
        alert.setContentText("You passed this level");
        alert.showAndWait();
    }

}
