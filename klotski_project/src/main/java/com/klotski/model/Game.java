package com.klotski.model;

public class Game {

    static final int BOARD_HEIGHT = 5;
    static final int BOARD_WIDTH = 4;
    private Board board;
    private GameProgress progress;
    private boolean gameStarted;
    private NextBestMove helper;


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
        board = new Board(BOARD_HEIGHT,BOARD_WIDTH,config.getBlocks());
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

    public SavedGame savedGame(){
        SavedGame saving = new SavedGame(board, progress);
        return saving;
    }

    public boolean resetGame() {
        if(progress.resetProgress()) {
            board = new Board(BOARD_HEIGHT,BOARD_WIDTH,progress.getBeginConf().getBlocks());
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
            return true;
        }
       return false;
    }

    public boolean undoMove(){
       Move lastMove = progress.undoLastMove();
        if(lastMove!=null){
            Move invertedMove = new Move(lastMove.getBlock(), lastMove.getDest(), lastMove.getStart());
            return board.move(invertedMove);
        }
        return false;
    }

    public void stopGame(){
        gameStarted= false;
    }
}