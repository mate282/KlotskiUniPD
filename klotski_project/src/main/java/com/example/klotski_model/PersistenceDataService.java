package com.example.klotski_model;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.json.*;

public class PersistenceDataService {

    private static final String FILES_BASEPATH = "/gameData/";
    private static final String SAVEFILE_PATH = "saves/";
    private static final String CONFIGFILE_PATH = "configs/";
    private static final String SOLFILE_PATH = "solutions/";

    private static final String SAVEFILE_NAME = "saving_";
    private static final String CONFIGFILE_NAME = "saving_";


    private static final String DATE_FORMAT = "yyyy/MM/dd_HH:mm:ss";

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
            String fileName = FILES_BASEPATH + SAVEFILE_PATH+SAVEFILE_NAME +dtf.format(now);

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
          File dirFile = new File(FILES_BASEPATH+SAVEFILE_PATH);
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

    /***
     * Load saved game
     * @param savingName saving data name
     * return saved game data*/
    public SavedGame loadGameData(String savingName) {
      try{
          //load all file in default directory
          File saveFile = new File(FILES_BASEPATH + SAVEFILE_PATH + savingName);
          Scanner reader = new Scanner(saveFile);
          String jsonData = reader.nextLine();
          reader.close();
          //Load JSONObject
          JSONObject savedGameJson = new JSONObject(jsonData);
          SavedGame savedGame = SavedGame.fromJSON(savedGameJson);
          return savedGame;
      }
      catch (IOException e)
      {
            return null;
      }
    }


    /**
     * load list of configurations available
     * @return list of configurations' names**/
    public List<String> loadAllConfigurations(){
      ArrayList<String> configs = new ArrayList<String> (0);

      try{
          //load all file in default directory
          File dirFile = new File(FILES_BASEPATH+CONFIGFILE_PATH);
          //load all files in default directory
          File[] files = dirFile.listFiles();
          //null check
          if(files==null)
              files = new File[0];

          //filer files to saving data
          for(File f : files){
              if(f.getName().contains(CONFIGFILE_NAME))
                  configs.add(f.getName());
          }
          return configs;
      }
      catch (Exception e)
      {
          return configs;
      }
   }

    /**
     * Load configuration from name
     * @param  configName name of configuration to load
     * @return game configuration**/
    public BeginningConfiguration loadConfig(String configName){
      try{
          //load all file in default directory
          File configFile = new File(FILES_BASEPATH + CONFIGFILE_PATH+ configName);
          Scanner reader = new Scanner(configFile);
          String jsonData = reader.nextLine();
          reader.close();
          //Load JSONObject
          JSONObject configJSON = new JSONObject(jsonData);
          BeginningConfiguration config = BeginningConfiguration.fromJSON(configJSON);
          return config;
      }
      catch (IOException e)
      {
          return null;
      }
  }


    /**
     * Load solution of specified configuration
     * @param beginConf configuration for which to load solution **/
    public LevelSolution loadSolution(BeginningConfiguration beginConf) {
      try{
          //load all file in default directory
          File configFile = new File(FILES_BASEPATH + SOLFILE_PATH+ beginConf.getName());
          Scanner reader = new Scanner(configFile);
          String jsonData = reader.nextLine();
          reader.close();
          //Load JSONObject
          JSONObject solutionJSON = new JSONObject(jsonData);
          LevelSolution solution  = LevelSolution.fromJSON(solutionJSON);
          return solution;
      }
      catch (IOException e)
      {
          return null;
      }
  }



}
