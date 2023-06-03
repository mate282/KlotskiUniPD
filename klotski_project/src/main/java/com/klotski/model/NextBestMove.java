package com.klotski.model;

import javafx.geometry.Point2D;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class NextBestMove {
    private LevelSolution levelSolution;

    /**
     * Constructor
     * @param solution = level solution
     */
    public NextBestMove(LevelSolution solution){
        this.levelSolution = solution;
    }

    /**
     * Suggest next best move
     *
     * @param board = actual state of board
     *
     * @return
     * - null = there is no data to suggest
     * - otherwise = move
     */
    public Move suggestMove(Board board) {
        return levelSolution.getMove(board);
    }
}
