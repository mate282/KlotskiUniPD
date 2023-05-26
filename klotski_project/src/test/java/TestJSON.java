import com.example.klotski_model.BeginningConfiguration;
import com.example.klotski_model.Block;
import com.example.klotski_model.Board;
import com.example.klotski_model.PersistenceDataService;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import org.json.JSONObject;

import java.awt.*;
import java.util.ArrayList;

public class TestJSON {
    public static void main(String[] args) {
        ArrayList<Block> blocks= new ArrayList<Block>(0);

        blocks.add(new Block(new Point2D(0,0), 1,1, Color.ORANGE));
        blocks.add(new Block(new Point2D(0,1), 1,1, Color.ORANGE));

        blocks.add(new Block(new Point2D(3,0), 1,1, Color.ORANGE));
        blocks.add(new Block(new Point2D(3,1), 1,1, Color.ORANGE));

        blocks.add(new Block(new Point2D(0,2), 2,1, Color.ORANGE));
        blocks.add(new Block(new Point2D(3,2), 2,1, Color.ORANGE));

        blocks.add(new Block(new Point2D(1,2), 1,1, Color.ORANGE));
        blocks.add(new Block(new Point2D(1,3), 1,1, Color.ORANGE));

        blocks.add(new Block(new Point2D(2,2), 1,1, Color.ORANGE));
        blocks.add(new Block(new Point2D(2,3), 1,1, Color.ORANGE));

        blocks.add(new Block(new Point2D(0,4), 1,1, Color.ORANGE));
        blocks.add(new Block(new Point2D(3,4), 1,1, Color.ORANGE));


        blocks.add(new Block(new Point2D(1,0), 2,2, Color.BLACK));

        BeginningConfiguration configuration = new BeginningConfiguration("level1",blocks);

        BeginningConfiguration loaded = PersistenceDataService.loadConfig("level1");
        configuration.getName();

    }

}
