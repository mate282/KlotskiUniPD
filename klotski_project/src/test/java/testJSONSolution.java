import com.klotski.model.*;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import org.json.JSONObject;


import java.util.ArrayList;

public class testJSONSolution {
    // level 2
    public static void main(String[] args){
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
        Board b = new Board(5,4,blocks);
        BeginningConfiguration config = new BeginningConfiguration("level2",blocks);
        GameProgress progress = new GameProgress(config);
        SavedGame savedGame = new SavedGame(b,progress);
        PersistenceDataService.saveGameData(savedGame);
        //SavedGame load = PersistenceDataService.loadGameData("saving_20230528_181059");
        //savedGame.toJSON();

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
        bm.add(new Pair<Board, Move>(b.clone(), new Move(new Block(new Point2D(2,3), 2,1), new Point2D(2,3), new Point2D(3,3))));
        b.move(                                 new Move(new Block(new Point2D(2,3), 2,1), new Point2D(2,3), new Point2D(3,3)));
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



        LevelSolution solutionToSave = new LevelSolution(bm);
        PersistenceDataService.saveSolution(solutionToSave, "level2");
        LevelSolution loadSolution = PersistenceDataService.loadSolution(config);
        JSONObject jo = loadSolution.toJSON();
        LevelSolution loadSolution1 = LevelSolution.fromJSON(jo);
    }
}
