import com.klotski.model.*;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;


import java.util.ArrayList;

public class testJSON {
    private static void main(String[] args){

        ArrayList<Block> blocks= new ArrayList<Block>(0);

        blocks.add(new Block(new Point2D(0,0), 1,1));
        blocks.add(new Block(new Point2D(0,1), 1,1));

        blocks.add(new Block(new Point2D(3,0), 1,1));
        blocks.add(new Block(new Point2D(3,1), 1,1));

        blocks.add(new Block(new Point2D(0,2), 2,1));
        blocks.add(new Block(new Point2D(3,2), 2,1));

        blocks.add(new Block(new Point2D(1,2), 1,1));
        blocks.add(new Block(new Point2D(1,3), 1,1));

        blocks.add(new Block(new Point2D(2,2), 1,1));
        blocks.add(new Block(new Point2D(2,3), 1,1));

        blocks.add(new Block(new Point2D(0,4), 1,1));
        blocks.add(new Block(new Point2D(3,4), 1,1));


        blocks.add(new Block(new Point2D(1,0), 2,2));



        Board b = new Board(5,4,blocks);
        BeginningConfiguration config = new BeginningConfiguration("level1",blocks);
        GameProgress progress = new GameProgress(config);




        SavedGame savedGame = new SavedGame(b,progress);


        PersistenceDataService.saveGameData(savedGame);

        SavedGame load = PersistenceDataService.loadGameData("saving_20230528_181059");
        savedGame.toJSON();
    }
}
