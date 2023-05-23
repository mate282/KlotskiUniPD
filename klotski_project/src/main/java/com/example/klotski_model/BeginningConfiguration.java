package com.example.klotski_model;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class BeginningConfiguration {
    final int max_num_blocks = 10;
    Block[] blocks;
    String name; //configuration name

    /**
     * Getter name
     *
     * @return configuration name
     */
    public String getName(){
        return this.name;
    }
  
    /**
     * Block array --> string
     *
     * @return Block array conversion to string
     */
    public String getBlocks(){
        String out="";
        for(int i=0; i<max_num_blocks; i++){
                out=out.concat(blocks[i].toString());
        }
        return out;
    }
  
    /**
     * Configuration name --> beginning configuration
     *
     * @param name name configuration
     *
     * @return None
     */
    public void setConfig(String name) {
        blocks = new Block[max_num_blocks];
        if(name.equalsIgnoreCase("Config1")){ // livello 29 del klotski resolver
            blocks[0] = new Block(new Point2D(1,0),2,2,Color.RED);
            blocks[1] = new Block(new Point2D(0,0),1,1);
            blocks[2] = new Block(new Point2D(3,0),1,1);
            blocks[3] = new Block(new Point2D(0,1),1,1);
            blocks[4] = new Block(new Point2D(3,1),1,1);
            blocks[5] = new Block(new Point2D(0,2),1,2);
            blocks[6] = new Block(new Point2D(2,2),1,2);
            blocks[7] = new Block(new Point2D(0,3),1,2);
            blocks[8] = new Block(new Point2D(2,3),1,2);
            blocks[9] = new Block(new Point2D(1,4),1,2);
        } else if(name.equalsIgnoreCase("Config2")){ //livello 46 del klotski resolver
            blocks[0] = new Block(new Point2D(0,0),2,2,Color.RED);
            blocks[1] = new Block(new Point2D(2,0),1,2);
            blocks[2] = new Block(new Point2D(2,1),1,2);
            blocks[3] = new Block(new Point2D(2,2),1,1);
            blocks[4] = new Block(new Point2D(3,2),1,1);
            blocks[5] = new Block(new Point2D(0,3),2,1);
            blocks[6] = new Block(new Point2D(1,3),2,1);
            blocks[7] = new Block(new Point2D(2,3),1,1);
            blocks[8] = new Block(new Point2D(3,3),2,1);
            blocks[9] = new Block(new Point2D(2,4),1,1);
        } else if(name.equalsIgnoreCase("Config3")){ //livello 31 del klotski resolver
            blocks[0] = new Block(new Point2D(2,0),2,2,Color.RED);
            blocks[1] = new Block(new Point2D(0,0),1,1);
            blocks[2] = new Block(new Point2D(1,0),1,1);
            blocks[3] = new Block(new Point2D(0,1),1,2);
            blocks[4] = new Block(new Point2D(0,2),1,2);
            blocks[5] = new Block(new Point2D(2,2),1,2);
            blocks[6] = new Block(new Point2D(0,3),1,2);
            blocks[7] = new Block(new Point2D(2,3),1,2);
            blocks[8] = new Block(new Point2D(2,4),1,1);
            blocks[9] = new Block(new Point2D(3,4),1,1);
        } else if(name.equalsIgnoreCase("Config4")){ //livello 47 del klotski resolver
            blocks[0] = new Block(new Point2D(2,0),2,2,Color.RED);
            blocks[1] = new Block(new Point2D(0,0),1,2);
            blocks[2] = new Block(new Point2D(0,1),1,2);
            blocks[3] = new Block(new Point2D(0,2),1,2);
            blocks[4] = new Block(new Point2D(2,2),1,2);
            blocks[5] = new Block(new Point2D(0,3),1,2);
            blocks[6] = new Block(new Point2D(2,3),1,1);
            blocks[7] = new Block(new Point2D(3,3),1,1);
            blocks[8] = new Block(new Point2D(0,4),1,1);
            blocks[9] = new Block(new Point2D(1,4),1,1);
        }
    }
}

