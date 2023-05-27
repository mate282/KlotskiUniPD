package com.example.klotski_view;

import com.example.klotski_controller.GameController;
import com.example.klotski_model.Block;
import com.example.klotski_model.Board;
import com.example.klotski_model.Game;
import com.example.klotski_project.KlotskiApp;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.controlsfx.control.GridView;
import org.controlsfx.control.spreadsheet.Grid;

import java.io.IOException;
import java.util.List;

public class GameView {

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
        Board board = gameController.getActualBoard();
        showBoard(board.getBlocks());
    }


    @FXML
    protected void onBackButtonClick() throws IOException {
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
        rect.fillProperty().set(Paint.valueOf(b.getColor().toString()));

        GridPane.setValignment(rect, VPos.TOP);
        GridPane.setHalignment(rect, HPos.LEFT);

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
                double endX = mouseEvent.getX();
                double endY = mouseEvent.getY();
                double deltaX = endX-mouseStartX;
                double deltaY = endY-mouseStartY;



                //Check main movement direction
                if(Math.abs(deltaX)>Math.abs(deltaY)){
                    int index = GridPane.getColumnIndex(rect);
                    //Move right
                    if(deltaX>0){
                        if(index < gridBoard.getColumnCount()){
                            index++;
                        }

                        GridPane.setColumnIndex(rect,index);
                    }
                    //Move left;
                    else{
                        if(index > 0){
                            index--;
                        }

                        GridPane.setColumnIndex(rect,index);
                    }

                }else{
                    int index = GridPane.getRowIndex(rect);
                    //Move Down
                    if(deltaY>0){
                        if(index < gridBoard.getRowCount()){
                            index++;
                        }
                        GridPane.setRowIndex(rect,index);
                    }
                    //Move Up
                    else{
                        if(index > 0){
                            index--;
                        }

                        GridPane.setRowIndex(rect,index);
                    }
                }
            }
        });

        return rect;
    }



}
