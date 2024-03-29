package com.klotski.model;

import com.klotski.klotski_project.KlotskiApp;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersistenceDataService {

    /**Folder for game data in the user directory**/
    public static final String FILES_BASEPATH = "KlotskiGame/";
    /**Folder for savings**/
    public static final String SAVEFILE_PATH = "Saves/";
    /**Folder for beginning configuration in program resources**/
    public static final String CONFIGFILE_PATH = "Configurations/";
    /**Folder for solution in program resources**/
    public static final String SOLFILE_PATH = "Solutions/";
    /**Base name for savings files**/
    public static final String SAVEFILE_NAME = "saving_";
    /**File with list of levels available**/
    public static final String LISTFILE_NAME = "levelList";
    /**Files extension**/
    public static final String FILE_EXT = ".json";

    /**Date format used for saving files**/
    public static final String DATE_FORMAT = "yyyyMMdd_HHmmss";



    //SAVINGS

    /**
     * Save game data to persistent location.
     * @param gameToSave game data to save
     * @return save result
     * **/
    public static boolean saveGameData(SavedGame gameToSave) {

        try{

            String path = System.getProperty("user.home")+File.separator;
            Files.createDirectories(Path.of(path+FILES_BASEPATH+SAVEFILE_PATH));

            //generate file name for saving : filePath+baseName+saveDate
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_FORMAT);
            LocalDateTime now = LocalDateTime.now();
            String fileName = path + FILES_BASEPATH + SAVEFILE_PATH+SAVEFILE_NAME +dtf.format(gameToSave.getSaveDate())+FILE_EXT;

            //Create file
            File file = new File(fileName);
            if(file.createNewFile()){

                //write game data
                FileWriter fileWriter = new FileWriter(fileName);
                fileWriter.write(gameToSave.toJSON().toString());
                fileWriter.close();
                return true;
            }
            return false;
        }
        catch (Exception e){
            return false;
        }


    }

    /**
     * Load list of game savings
     * If there is no save return empty list
     * @return list of savings name.
     * **/
    public static List<String> loadAllGameData(){
        ArrayList<String> savings = new ArrayList<String> (0);
        String path = System.getProperty("user.home")+File.separator;

        try{
            //load all file in default directory
            File dirFile = new File(path+FILES_BASEPATH + SAVEFILE_PATH );
            //load all files in default directory
            File[] files = dirFile.listFiles();
            //null check
            if(files==null)
                files = new File[0];

            //filer files to saving data
            for(File f : files){
                savings.add(f.getName());
            }
            return savings;
        }
        catch (Exception e)
        {
            return savings;
        }
    }

    /**Load saved game
     * @param savingName saving data name
     * return saved game data*/
    public static SavedGame loadGameData(String savingName) {
        String path = System.getProperty("user.home")+File.separator;
        String fileName = savingName;
        try{
            if(!savingName.contains(FILE_EXT)){
                fileName+=FILE_EXT;
            }
            //load all file in default directory
            File saveFile = new File(path+FILES_BASEPATH + SAVEFILE_PATH + fileName);
            Scanner reader = new Scanner(saveFile);
            String content = "";

            while (reader.hasNextLine()){
                content+=reader.nextLine();
            }

            reader.close();
            //Load JSONObject
            JSONObject savedGameJson = new JSONObject(content);
            SavedGame savedGame = SavedGame.fromJSON(savedGameJson);
            return savedGame;
        }
        catch (Exception e)
        {
            return null;
        }
    }



    //CONFIGURATIONS

    /**
     * load list of configurations available
     * @return list of configurations' names**/
    public static List<String> loadAllConfigurations(){
        ArrayList<String> configs = new ArrayList<String> (0);

        try{
            InputStreamReader inputReader = new InputStreamReader(PersistenceDataService.class.getResource(CONFIGFILE_PATH+LISTFILE_NAME+FILE_EXT).openStream());
            BufferedReader reader = new BufferedReader(inputReader);

            String line = reader.readLine();
            String content="";
            while (line!=null){
                content+=line;
                line = reader.readLine();
            }

            reader.close();
            inputReader.close();

            JSONObject jsonObject = new JSONObject(content);
            JSONArray jsonArray = jsonObject.getJSONArray("levels");

            for(int i = 0; i < jsonArray.length(); i++){
                configs.add(jsonArray.get(i).toString());
            }

            return  configs;
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
    public static BeginningConfiguration loadConfig(String configName){
        String fileName = configName;
        try{
            if(!configName.contains(FILE_EXT)){
                fileName+=FILE_EXT;
            }
            //load all file in default directory
            InputStreamReader inputReader = new InputStreamReader(PersistenceDataService.class.getResource(CONFIGFILE_PATH+fileName).openStream());
            BufferedReader reader = new BufferedReader(inputReader);

            String line = reader.readLine();
            String content="";

            while (line!=null){
                content+=line;
                line = reader.readLine();
            }

            reader.close();
            inputReader.close();


            //Load JSONObject
            JSONObject configJSON = new JSONObject(content);
            BeginningConfiguration config = BeginningConfiguration.fromJSON(configJSON);
            return config;
        }
        catch (Exception e)
        {
            return null;
        }
    }



    //SOLUTIONS

    /**
     * Load solution of specified configuration
     * @param beginConf configuration for which to load solution **/
    public static LevelSolution loadSolution(BeginningConfiguration beginConf) {
        try{
            //load all file in default directory
            InputStreamReader inputReader = new InputStreamReader(PersistenceDataService.class.getResource(SOLFILE_PATH+beginConf.getName()+FILE_EXT).openStream());
            BufferedReader reader = new BufferedReader(inputReader);

            String line = reader.readLine();
            String content="";
            while (line!=null){
                content+=line;
                line = reader.readLine();
            }

            reader.close();
            inputReader.close();

            //Load JSONObject
            JSONObject solutionJSON = new JSONObject(content);
            LevelSolution solution  = LevelSolution.fromJSON(solutionJSON);
            return solution;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * Save solutions to persistent location (used only to create solution files).
     * @param solutionToSave level solution to save
     * @param solutionName level solution name
     * @return save result
     * **/
    public static boolean saveSolution(LevelSolution solutionToSave, String solutionName) {

        try{

            String path = System.getProperty("user.home")+File.separator;
            Files.createDirectories(Path.of(path+FILES_BASEPATH+SOLFILE_PATH));

            //generate file name for saving : filePath+baseName+saveDate
            String fileName = path + FILES_BASEPATH + SOLFILE_PATH + solutionName + FILE_EXT;

            //Create file
            File file = new File(fileName);
            if(file.createNewFile()){
                //write solution data
                FileWriter fileWriter = new FileWriter(fileName);
                fileWriter.write(solutionToSave.toJSON().toString());
                fileWriter.close();
                return true;
            }
            return false;
        }
        catch (IOException e){
            return false;
        }
    }
}