package com.example.klotski_model;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.json.*;

public class PersistenceDataService {

    static  String FILES_BASEPATH = "/gameData/";
    static  String SAVEFILE_NAME = "saving_";
    static  String DATE_FORMAT = "yyyy/MM/dd_HH:mm:ss";

    /**
     * Save game data to persistent location.
     * @param gameToSave game data to save
     * @return save result
     * **/
    public boolean saveGameData(SavedGame gameToSave) {

        try{
            //generate file name for saving : filePath+baseName+saveDate
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_FORMAT);
            LocalDateTime now = LocalDateTime.now();
            String fileName = FILES_BASEPATH + SAVEFILE_NAME +dtf.format(now);

            //Create file
            File file = new File(fileName);
            if(file.createNewFile()){

                //write game data
                FileWriter fileWriter = new FileWriter(fileName);
                fileWriter.write(gameToSave.toString());
                fileWriter.close();
                return true;
            }
            return false;
        }
        catch (IOException e){
            return false;
        }


    }


    /**
     * Load list of game savings
     * If there is no save return empty list
     * @return list of savings name.
     * **/
    public List<String> loadAllGameData(){
      ArrayList<String> savings = new ArrayList<String> (0);

      try{
          //load all file in default directory
          File dirFile = new File(FILES_BASEPATH);
          //load all files in default directory
          File[] files = dirFile.listFiles();
          //null check
          if(files==null)
              files = new File[0];

          //filer files to saving data
          for(File f : files){
              if(f.getName().contains(SAVEFILE_NAME))
                savings.add(f.getName());
          }
          return savings;
      }
      catch (Exception e)
      {
          return savings;
      }
    }


     public SavedGame loadGameData(String savingName) {

          try{
              //load all file in default directory
              File saveFile = new File(FILES_BASEPATH + savingName);
              Scanner reader = new Scanner(saveFile);
              String jsonData = reader.nextLine();
              reader.close();

              JSONObject savedGameJson = new JSONObject(jsonData);

              SavedGame savedGame = new SavedGame();
              return savedGame;
          }
          catch (IOException e)
          {
                return null;
          }
     }

  /*

  public List<String> loadAllConfigurations(){
        return ArrayList<String>(0);
  }
  public BeginningConfiguration loadConfig(String configName){
        return null;
  }
  public List<Board> loadSolution(BeginningConfiguration beginConf) {
        return ArrayList<Board>(0);
  }

  */

}
