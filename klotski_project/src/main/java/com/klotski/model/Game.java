package com.klotski.model;

import java.util.ArrayList;

public class Game implements Observable{

    static final int BOARD_HEIGHT = 5;
    static final int BOARD_WIDTH = 4;
    private Board board;
    private GameProgress progress;
    private boolean gameStarted;
    private NextBestMove helper;

    ArrayList<Observer> observers;

    public Game(){
        observers=new ArrayList<Observer>(0);
    }

    public boolean isGameStarted(){
        return gameStarted;
    }

    public Board getBoard(){
        return board;
    }

    public GameProgress getProgress(){
        return progress;
    }

    public boolean startGame(BeginningConfiguration config, LevelSolution solution){
        progress = new GameProgress(config);
        ArrayList<Block> blocks = new ArrayList<Block>(0);
        for(Block b:progress.getBeginConf().getBlocks() ){
            blocks.add(b.clone());
        }

        board = new Board(BOARD_HEIGHT,BOARD_WIDTH,blocks);
        helper = new NextBestMove(config,solution);
        gameStarted = true;
        return gameStarted;
    }

    public boolean loadGame(SavedGame saving, LevelSolution solution){
        progress = saving.getGameProgress();
        board = saving.getLastBoard();
        //helper = new Helper(solution);
        gameStarted =board!=null && progress!=null;
        return gameStarted;
    }

    public SavedGame saveGame(){
        if(gameStarted){
            SavedGame saving = new SavedGame(board, progress);
            return saving;
        }
        return null;
    }

    public boolean resetGame() {
        if(progress.resetProgress()) {

            ArrayList<Block> blocks = new ArrayList<Block>(0);
            for(Block b:progress.getBeginConf().getBlocks() ){
                blocks.add(b.clone());
            }

            board = new Board(BOARD_HEIGHT,BOARD_WIDTH,blocks);
            notifyListener(progress.getMovesCounter(),false);
            return true;
        }
        return false;
    }

    //public boolean getHelp(){
    //    Move nextMove = helper.suggetMove(board);
    //    return makeMove(nextMove);
    //}

    public boolean makeMove(Move move){
        if(board.move(move)){
            progress.addMove(move);
            if(board.checkWin()){
                notifyListener(progress.getMovesCounter(),true);
            }
            notifyListener(progress.getMovesCounter(),false);
            return true;
        }
       return false;
    }

    public boolean undoMove(){
       Move lastMove = progress.undoLastMove();
        if(lastMove!=null){
            Move invertedMove = new Move(lastMove.getBlock(), lastMove.getDest(), lastMove.getStart());
            notifyListener(progress.getMovesCounter(),false);
            return board.move(invertedMove);
        }
        return false;
    }

    public void stopGame(){
        gameStarted= false;
    }



    public void addListener(Observer obs){
        if(!observers.contains(obs))
            observers.add(obs);
    }

    public void removeListener(Observer obs){
        observers.remove(obs);
    }

    public void notifyListener(int movesCounter, boolean win){
        for(Observer obs: observers){
            obs.update(movesCounter,win);
        }
    }
}