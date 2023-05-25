package com.example.klotski_model;

import java.util.ArrayList;

public class GameProgress {

    BeginningConfiguration configuration;
    ArrayList<Move> moves;

    public GameProgress(BeginningConfiguration configuration){

    }

    public BeginningConfiguration getBeginConf(){
        return null;
    }

    public int getMovesCounter(){
        return 0;
    }

    public Move undoLastMove(){
        return null;
    }

    public boolean addMove(Move move){
        return false;
    }

    public boolean resetProgress(){
        return false;
    }
}
