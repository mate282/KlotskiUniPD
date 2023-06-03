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



    //GETTERS

    /**Check if game is started
     * @return true => game is started
     * **/
    public boolean isGameStarted(){
        return gameStarted;
    }
    /**Return actual game board
     * @return game board
     **/
    public Board getBoard(){
        return board;
    }
    /**Return actual game progress
     * @return game progress
     **/
    public GameProgress getProgress(){
        return progress;
    }



    //GAME MANAGEMENT

    /** Start a new game with passed configuration and associated solution
     * @param config Beginning Configuration to play
     * @param solution LevelSolution associated to beginning
     * @return true => game is started correctly
     **/
    public boolean startGame(BeginningConfiguration config, LevelSolution solution){
        //create game progress with passed configuration
        progress = new GameProgress(config);
        //Copy list of block to avoid duplicate references
        ArrayList<Block> blocks = new ArrayList<>(0);
        for(Block b:progress.getBeginConf().getBlocks() ){
            blocks.add(b.clone());
        }

        board = new Board(BOARD_HEIGHT,BOARD_WIDTH,blocks);
        helper = new NextBestMove(solution);
        //set game started
        gameStarted = true;
        //notify listener of new status
        notifyListener(progress.getMovesCounter(),false);
        return gameStarted;
    }

    /** Start a game loading a saved one.
     * @param saving saved game to load
     * @param solution LevelSolution associated to beginning
     * @return true => game is started correctly, false => otherwise
     **/
    public boolean loadGame(SavedGame saving, LevelSolution solution){
        //get progess and board from saving
        progress = saving.getGameProgress();
        board = saving.getLastBoard();
        //helper = new Helper(solution);
        //check for correct data
        gameStarted = board!=null && progress!=null;
        //if game is started than notify all listeners
        if(gameStarted)
            notifyListener(progress.getMovesCounter(),false);
        return gameStarted;
    }

    /**Save actual game
     *@return saved game if game is started, null otherwise
     * **/
    public SavedGame saveGame(){
        if(gameStarted){
            return new SavedGame(board, progress);
        }
        return null;
    }

    /**Reset actual game progress to the beginning status
     * @return game is reset correctly or not
     * **/
    public boolean resetGame() {
        if(progress.resetProgress()) {
            //Copy list of block to avoid duplicate references
            ArrayList<Block> blocks = new ArrayList<>(0);
            for(Block b:progress.getBeginConf().getBlocks() ){
                blocks.add(b.clone());
            }
            //create a new board to return initial situation
            board = new Board(BOARD_HEIGHT,BOARD_WIDTH,blocks);
            //notify listeners of actual game status
            notifyListener(progress.getMovesCounter(),false);
            return true;
        }
        return false;
    }

    /**stop actual game if there is one
     * **/
    public void stopGame(){
        gameStarted= false;
    }


    //MOVES


    //public boolean getHelp(){
    //    Move nextMove = helper.suggestMove(board);
    //    return makeMove(nextMove);
    //}

    /**make a move in the game
     *@param move move to do
     * @return true => move accepted, false => otherwise **/
    public boolean makeMove(Move move){
        //ask board to make the move
        if(board.move(move)){
            //add move to progress
            progress.addMove(move);
            //check for winning condition
            if(board.checkWin()){
                //notify listener of winning
                notifyListener(progress.getMovesCounter(),true);
            }
            //notify listener of actual game status
            notifyListener(progress.getMovesCounter(),false);
            return true;
        }
       return false;
    }

    /**undo last move done
     * @return true => move undo correctly**/
    public boolean undoMove(){
        //get last move from progress
       Move lastMove = progress.undoLastMove();
       //if it's not null there is a move to undo
        if(lastMove!=null){
            //create the inverted move from dest to start
            Move invertedMove = new Move(lastMove.getBlock().clone(),lastMove.getDest(), lastMove.getStart());
            //ask board to make the move
            if(board.move(invertedMove)){
                //notify all listener
                notifyListener(progress.getMovesCounter(),false);
                return true;
            }
        }
        return false;
    }


    //OBSERVER


    /**Add a listener to list of observers
     * @param obs listener to add**/
    public void addListener(Observer obs){
        if(!observers.contains(obs))
            observers.add(obs);
    }
    /**Remove a listener to list of observers
     * @param obs listener to remove**/
    public void removeListener(Observer obs){
        observers.remove(obs);
    }
    /**Notify all listeners in the list
     * @param movesCounter actual moves number
     * @param win win status
     * **/
    public void notifyListener(int movesCounter, boolean win){
        for(Observer obs: observers){
            obs.update(movesCounter,win);
        }
    }
}