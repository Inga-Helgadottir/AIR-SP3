package com.company;

import java.io.File;
import java.nio.file.Files;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    final static UI ui = new UI();

    static ArrayList<Tournament> tournaments = new ArrayList<Tournament>();
    static boolean systemOn = true;

    public static void main(String[] args) throws IOException{
        //Read all id counters and set to corresponding classes
        Tournament.setIdCounter(readIdCounterData("src/data/idCounters/idCounter_Tournament.txt"));
        Team.setIdCounter(readIdCounterData("src/data/idCounters/idCounter_Team.txt"));

        //Read all tournament data
        Tournament.readTournamentData("src/data/tournaments");

        /*testing
            Team t1 = new Team("hoho");
            Team t2 = new Team("ohoh");
            Team[] teams = {t1, t2};
            Match match = new Match(teams);
            match.setTeam1Goals(12);
            match.setTeam2Goals(13);
            match.whoWon();
        test ends*/

        /*testing
            System.out.println("Testing values");
            System.out.println(tournaments.get(0).getName());
            System.out.println(tournaments.get(0).getSport());
            System.out.println(tournaments.get(0).toString());
            System.out.println(tournaments.get(1).toString());
            System.out.println("end of testing\n");
         test ends*/

        tournaments.get(1).displayTeamRankings();

        ui.displayMsg("~ Tournament Manager ~");

        showStartMenu();
        String taskType = ui.getUserInput("\nUser input:");
        handleStartMenuChoice(taskType);

        while(systemOn){
            showStartMenu();
            taskType = ui.getUserInput("\nUser input:");
            handleStartMenuChoice(taskType);
        }
    }

    public static int readIdCounterData(String filePath){
        String[] idCounterLine;
        int idCounter = 0;

        try{
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            idCounterLine = line.split(":");
            idCounter = Integer.parseInt(idCounterLine[1]);
            scanner.close();
        }catch(FileNotFoundException e){
            System.out.println(e);
        }

        return idCounter;
    }

    public static void saveIdCounterData(String filePath, String data){
        String fileData = data;

        try{
            File file = new File(filePath);
            FileWriter fileWriter = new FileWriter(file, false);
            fileWriter.write(data);
            fileWriter.close();
        }catch (IOException e){
            System.out.println(e.getCause());
        }
    }

    public static void deleteFolder(File file){
        try{
            File[] allContents = file.listFiles();
            if (allContents != null) {
                for (File content : allContents) {
                    deleteFolder(content);
                }
            }
            Files.delete(file.toPath());
        }catch(IOException e){
            System.out.println(e);
        }
    }

    public static void saveData(String filePath, String data){
        String fileData = data;

        try{
            File file = new File(filePath);
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(data);
            fileWriter.close();
        }catch (IOException e){
            System.out.println(e.getCause());
        }
    }

    public static void createNewDir(String filePath, String dirName){
        File newDir = new File(filePath + "/" + dirName);
        newDir.mkdir();
    }

    public static void showStartMenu(){
        ui.displayMsg("\n(START-MENU)");

        ui.displayMsg("\nWhat would you like to do?");
        ui.displayMsg("\n- Manage tournaments (Type: 1)");
        ui.displayMsg("- Register a new team to a tournament (Type: 2)");
        ui.displayMsg("- See data\n (Type: 3)");
        ui.displayMsg("- Close system (Type: 4)"); // Change if more menu options are added
    }

    public static void handleStartMenuChoice(String taskType){
        if(taskType.equals("1")){
            showTournamentMenu();
        }else if(taskType.equals("2")){
            registerNewTeam();
        }else if(taskType.equals("3")){
            System.out.println("See data");
        }else if(taskType.equals("4")){ // Change if more menu options are added
            System.out.println("\nThe system has been turned off");
            systemOn = false;
        }else{
            System.out.println("Invalid input");
        }
    }

    public static void showTournamentMenu(){
        ui.displayMsg("\n(TOURNAMENT-MENU)");

        ui.displayMsg("\nWhat would you like to do?");
        ui.displayMsg("\n- Register new tournament (Type: 1)");
        ui.displayMsg("- Edit a tournament (Type: 2)");
        ui.displayMsg("- Delete a tournament (Type: 3)");
        ui.displayMsg("- Go back to start-menu (Type: 4)");

        String taskType = ui.getUserInput("\nUser input:");
        handleTournamentChoice(taskType);
    }

    public static void handleTournamentChoice(String taskType){
        if(taskType.equals("1")){
           Tournament.registerNewTournament();
        }else if(taskType.equals("2")){
            //todo Make tournament editing available
            ui.displayMsg("Editing option not yet available");
        }else if(taskType.equals("3")){
            Tournament.deleteTournament();
        }else if(taskType.equals("4")){
            return;
        }else{
            ui.displayMsg("Invalid input");
        }
    }

    public static void registerNewTeam(){
        ui.displayMsg("\n(REGISTER NEW TEAM)");

        if(Main.tournaments.size() != 0){
            ui.displayMsg("\nTournaments currently in the system: ");
            Tournament.displayAllTournaments();

            String userInput = ui.getUserInput("\nType the ID of the tournament the team would like to register " +
            "into \nType -1 to cancel: ");

            if(!userInput.equals("-1")){
                int tournamentId = Integer.parseInt(userInput);

                Tournament tournamentToRegisterInto = Tournament.findTournament(tournamentId);

                if(tournamentToRegisterInto != null){
                    String teamName = ui.getUserInput("\nTeam name:");

                    ui.displayMsg("\nA team must have at least 2 players to get registered");
                    ui.displayMsg("Please enter 2 or more players to complete registration");
                    ui.displayMsg("Type -1 to end");

                    ArrayList<String> playerNames = new ArrayList<String>();
                    boolean stillAdding = true;

                    while(stillAdding){
                        String playerName = ui.getUserInput("\nPlayer name: ");

                        if(!playerName.equals("-1")){
                            playerNames.add(playerName);
                        }else{
                            stillAdding = false;
                        }
                    }

                    if(playerNames.size() == 0){
                        ui.displayMsg("You need at least 2 players to register into the tournament");
                        ui.displayMsg("\nThe team was not registered...");
                    }else{
                        Team team = new Team(teamName);
                        tournamentToRegisterInto.addTeam(team);

                        for(String playerName : playerNames){
                            team.addPlayer(playerName);
                        }

                        saveIdCounterData("src/data/idCounters/idCounter_Team.txt", "ID:" + Team.getIdCounter());
                        saveData("src/data/tournaments/" + tournamentToRegisterInto.getName() + "/teamData.txt",
                        team.toString());
                        ui.displayMsg("\nThe team was successfully registered!");
                    }
                }else{
                    ui.displayMsg("\nNo tournament matching the provided id could be found in the system...");
                }
            }
        }else{
            ui.displayMsg("There are currently no tournaments registered in the system.");
        }
    }

    public static void printTournamentData(File file){
        Scanner input = null;
        try {
            input = new Scanner(file);

            while (input.hasNextLine())
            {
                System.out.println(input.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*public static void registerNewTeam(){
        ui.displayMsg("\nRegister new team");
        String name = ui.getUserInput("\n team name:");
        String id = ui.getUserInput("\n team id:");
        int inp = ui.getTeamInput();
        Team team = new Team(name);//inp

        teams.add(team);
        ui.displayMsg("\nNew team has been registered!");
        System.out.println(teams);
        //saveData("src/tournamentData.txt", getTournamentData());
    }*/
}