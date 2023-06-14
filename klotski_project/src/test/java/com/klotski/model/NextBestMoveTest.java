package com.klotski.model;

import javafx.geometry.Point2D;
import javafx.util.Pair;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class NextBestMoveTest {
    BeginningConfiguration config;
    Board b, b1;

    @BeforeEach
    void setUp() {
        ArrayList<Block> blocks= new ArrayList<Block>(0);
        blocks.add(new Block(new Point2D(0,0), 2,1));
        blocks.add(new Block(new Point2D(1,0), 2,2));
        blocks.add(new Block(new Point2D(3,0), 2,1));
        blocks.add(new Block(new Point2D(0,2), 2,1));
        blocks.add(new Block(new Point2D(1,2), 1,2));
        blocks.add(new Block(new Point2D(3,2), 2,1));
        blocks.add(new Block(new Point2D(1,3), 1,1));
        blocks.add(new Block(new Point2D(2,3), 1,1));
        blocks.add(new Block(new Point2D(0,4), 1,1));
        blocks.add(new Block(new Point2D(3,4), 1,1));
        b = new Board(5,4,blocks);
        config = new BeginningConfiguration("level2",blocks);
        GameProgress progress = new GameProgress(config);
        SavedGame savedGame = new SavedGame(b,progress);
        PersistenceDataService.saveGameData(savedGame);
        //SavedGame load = PersistenceDataService.loadGameData("saving_20230528_181059");
        //savedGame.toJSON();
        b1 = b.clone();

        ArrayList<Pair<Board, Move>> bm = new ArrayList<Pair<Board, Move>>();
        // Step 1
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(1,3), 1,1), new Point2D(1,3), new Point2D(1,4))));
        b.move(                                 new Move(new Block(new Point2D(1,3), 1,1), new Point2D(1,3), new Point2D(1,4)));
        // Step 2
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(3,4), 1,1), new Point2D(3,4), new Point2D(2,4))));
        b.move(                                 new Move(new Block(new Point2D(3,4), 1,1), new Point2D(3,4), new Point2D(2,4)));
        // Step 3
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(3,2), 2,1), new Point2D(3,2), new Point2D(3,3))));
        b.move(                                 new Move(new Block(new Point2D(3,2), 2,1), new Point2D(3,2), new Point2D(3,3)));
        // Step 4
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(1,2), 1,2), new Point2D(1,2), new Point2D(2,2))));
        b.move(                                 new Move(new Block(new Point2D(1,2), 1,2), new Point2D(1,2), new Point2D(2,2)));
        // Step 5
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(0,2), 2,1), new Point2D(0,2), new Point2D(1,2))));
        b.move(                                 new Move(new Block(new Point2D(0,2), 2,1), new Point2D(0,2), new Point2D(1,2)));
        // Step 6
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(0,4), 1,1), new Point2D(0,4), new Point2D(0,3))));
        b.move(                                 new Move(new Block(new Point2D(0,4), 1,1), new Point2D(0,4), new Point2D(0,3)));
        // Step 7
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(1,4), 1,1), new Point2D(1,4), new Point2D(0,4))));
        b.move(                                 new Move(new Block(new Point2D(1,4), 1,1), new Point2D(1,4), new Point2D(0,4)));
        // Step 8
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(1,2), 2,1), new Point2D(1,2), new Point2D(1,3))));
        b.move(                                 new Move(new Block(new Point2D(1,2), 2,1), new Point2D(1,2), new Point2D(1,3)));
        // Step 9
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(2,2), 1,2), new Point2D(2,2), new Point2D(0,2))));
        b.move(                                 new Move(new Block(new Point2D(2,2), 1,2), new Point2D(2,2), new Point2D(0,2)));
        // Step 10
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(2,3), 1,1), new Point2D(2,3), new Point2D(2,2))));
        b.move(                                 new Move(new Block(new Point2D(2,3), 1,1), new Point2D(2,3), new Point2D(2,2)));
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(2,2), 1,1), new Point2D(2,2), new Point2D(3,2))));
        b.move(                                 new Move(new Block(new Point2D(2,2), 1,1), new Point2D(2,2), new Point2D(3,2)));
        // Step 11
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(2,4), 1,1), new Point2D(2,4), new Point2D(2,2))));
        b.move(                                 new Move(new Block(new Point2D(2,4), 1,1), new Point2D(2,4), new Point2D(2,2)));
        // Step 12
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(1,3), 2,1), new Point2D(1,3), new Point2D(2,3))));
        b.move(                                 new Move(new Block(new Point2D(1,3), 2,1), new Point2D(1,3), new Point2D(2,3)));
        // Step 13
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(0,3), 1,1), new Point2D(0,3), new Point2D(1,3))));
        b.move(                                 new Move(new Block(new Point2D(0,3), 1,1), new Point2D(0,3), new Point2D(1,3)));
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(1,3), 1,1), new Point2D(1,3), new Point2D(1,4))));
        b.move(                                 new Move(new Block(new Point2D(1,3), 1,1), new Point2D(1,3), new Point2D(1,4)));
        // Step 14
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(0,2), 1,2), new Point2D(0,2), new Point2D(0,3))));
        b.move(                                 new Move(new Block(new Point2D(0,2), 1,2), new Point2D(0,2), new Point2D(0,3)));
        // Step 15
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(2,2), 1,1), new Point2D(2,2), new Point2D(0,2))));
        b.move(                                 new Move(new Block(new Point2D(2,2), 1,1), new Point2D(2,2), new Point2D(0,2)));
        // Step 16
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(3,2), 1,1), new Point2D(3,2), new Point2D(1,2))));
        b.move(                                 new Move(new Block(new Point2D(3,2), 1,1), new Point2D(3,2), new Point2D(1,2)));
        // Step 17
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(2,3), 2,1), new Point2D(2,3), new Point2D(2,2))));
        b.move(                                 new Move(new Block(new Point2D(2,3), 2,1), new Point2D(2,3), new Point2D(2,2)));
        // Step 18
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(3,3), 2,1), new Point2D(3,3), new Point2D(3,2))));
        b.move(                                 new Move(new Block(new Point2D(3,3), 2,1), new Point2D(3,3), new Point2D(3,2)));
        // Step 19
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(1,4), 1,1), new Point2D(1,4), new Point2D(3,4))));
        b.move(                                 new Move(new Block(new Point2D(1,4), 1,1), new Point2D(1,4), new Point2D(3,4)));
        // Step 20
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(0,4), 1,1), new Point2D(0,4), new Point2D(2,4))));
        b.move(                                 new Move(new Block(new Point2D(0,4), 1,1), new Point2D(0,4), new Point2D(2,4)));
        // Step 21
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(0,3), 1,2), new Point2D(0,3), new Point2D(0,4))));
        b.move(                                 new Move(new Block(new Point2D(0,3), 1,2), new Point2D(0,3), new Point2D(0,4)));
        // Step 22
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(1,2), 1,1), new Point2D(1,2), new Point2D(1,3))));
        b.move(                                 new Move(new Block(new Point2D(1,2), 1,1), new Point2D(1,2), new Point2D(1,3)));
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(1,3), 1,1), new Point2D(1,3), new Point2D(0,3))));
        b.move(                                 new Move(new Block(new Point2D(1,3), 1,1), new Point2D(1,3), new Point2D(0,3)));
        // Step 23
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(2,2), 2,1), new Point2D(2,2), new Point2D(1,2))));
        b.move(                                 new Move(new Block(new Point2D(2,2), 2,1), new Point2D(2,2), new Point2D(1,2)));
        // Step 24
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(3,2), 2,1), new Point2D(3,2), new Point2D(2,2))));
        b.move(                                 new Move(new Block(new Point2D(3,2), 2,1), new Point2D(3,2), new Point2D(2,2)));
        // Step 25
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(3,0), 2,1), new Point2D(3,0), new Point2D(3,2))));
        b.move(                                 new Move(new Block(new Point2D(3,0), 2,1), new Point2D(3,0), new Point2D(3,2)));
        // Step 26
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(1,0), 2,2), new Point2D(1,0), new Point2D(2,0))));
        b.move(                                 new Move(new Block(new Point2D(1,0), 2,2), new Point2D(1,0), new Point2D(2,0)));
        // Step 27
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(0,0), 2,1), new Point2D(0,0), new Point2D(1,0))));
        b.move(                                 new Move(new Block(new Point2D(0,0), 2,1), new Point2D(0,0), new Point2D(1,0)));
        // Step 28
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(0,2), 1,1), new Point2D(0,2), new Point2D(0,0))));
        b.move(                                 new Move(new Block(new Point2D(0,2), 1,1), new Point2D(0,2), new Point2D(0,0)));
        // Step 29
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(0,3), 1,1), new Point2D(0,3), new Point2D(0,1))));
        b.move(                                 new Move(new Block(new Point2D(0,3), 1,1), new Point2D(0,3), new Point2D(0,1)));
        // Step 30
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(1,2), 2,1), new Point2D(1,2), new Point2D(0,2))));
        b.move(                                 new Move(new Block(new Point2D(1,2), 2,1), new Point2D(1,2), new Point2D(0,2)));
        // Step 31
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(1,0), 2,1), new Point2D(1,0), new Point2D(1,2))));
        b.move(                                 new Move(new Block(new Point2D(1,0), 2,1), new Point2D(1,0), new Point2D(1,2)));
        // Step 32
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(2,0), 2,2), new Point2D(2,0), new Point2D(1,0))));
        b.move(                                 new Move(new Block(new Point2D(2,0), 2,2), new Point2D(2,0), new Point2D(1,0)));
        // Step 33
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(3,2), 2,1), new Point2D(3,2), new Point2D(3,0))));
        b.move(                                 new Move(new Block(new Point2D(3,2), 2,1), new Point2D(3,2), new Point2D(3,0)));
        // Step 34
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(2,2), 2,1), new Point2D(2,2), new Point2D(3,2))));
        b.move(                                 new Move(new Block(new Point2D(2,2), 2,1), new Point2D(2,2), new Point2D(3,2)));
        // Step 35
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(2,4), 1,1), new Point2D(2,4), new Point2D(2,2))));
        b.move(                                 new Move(new Block(new Point2D(2,4), 1,1), new Point2D(2,4), new Point2D(2,2)));

        LevelSolution solutionToSave = new LevelSolution(bm);
        PersistenceDataService.saveSolution(solutionToSave, "level2");
        LevelSolution loadSolution = PersistenceDataService.loadSolution(config);
        JSONObject jo = loadSolution.toJSON();
        LevelSolution loadSolution1 = LevelSolution.fromJSON(jo);
    }

    @Test
    void suggestMove() {
        LevelSolution loadSolution = PersistenceDataService.loadSolution(config);
        JSONObject jo = loadSolution.toJSON();
        LevelSolution loadSolution1 = LevelSolution.fromJSON(jo);
        assertEquals(loadSolution.toJSON().toString(),loadSolution1.toJSON().toString() );
        NextBestMove nextBestMove = new NextBestMove(loadSolution);
        Move m1 = nextBestMove.suggestMove(b1);
        Move m2 = new Move(new Block(new Point2D(1,3), 1,1), new Point2D(1,3), new Point2D(1,4));
        assertEquals(m1.getStart().toString(), m2.getStart().toString());
        assertEquals(m1.getDest().toString(),  m2.getDest().toString());
        assertEquals(m1.toJSON().toString(), m2.toJSON().toString());
    }
}