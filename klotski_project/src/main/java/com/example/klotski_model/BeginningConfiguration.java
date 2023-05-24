package com.example.klotski_model;

import java.util.List;

public class BeginningConfiguration {
    
    List<Block> blocks;
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
     * Getter blocks list
     *
     * @return Blocks list
     */
    public List<Block> getBlocks(){
        return this.blocks;
    }

    /**
     * Block array --> string
     *
     * @return Block array conversion to string
     */
    public String blockstoString(){
        String out="";
        for(int i=0; i<blocks.size(); i++){
            out=out.concat(blocks.get(i).toString());
        }
        return out;
    }
}
