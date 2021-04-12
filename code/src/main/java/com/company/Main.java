package com.company;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    static File tournamentDataFile;
    static UI ui = new UI();
    static boolean systemOn = true;
    static ArrayList<Tournament> tournaments = new ArrayList<Tournament>();

    public static void main(String[] args) throws IOException{
        /*testing
            Team t1 = new Team("hoho");
            Team t2 = new Team("ohoh");
            Team[] teams = {t1, t2};
            Match match = new Match(teams);
            match.setTeam1Goals(12);
            match.setTeam2Goals(13);
            match.whoWon();
        test ends*/
        try {
            File tournamentDataFile = new File("src/tournamentData.txt");
            Scanner tournamentDataScanner = new Scanner(tournamentDataFile);
            readTournamentData();

            /*testing
                System.out.println("Testing values");
                System.out.println(tournaments.get(0).getName());
                System.out.println(tournaments.get(0).getSport());
                System.out.println(tournaments.get(0).toString());
                System.out.println(tournaments.get(1).toString());
                System.out.println("end of testing\n");
             test ends*/

            tournamentDataScanner.close();
        } catch (FileNotFoundException e) {
            File tournamentDataFile = new File("src/tournamentData.txt");
            tournamentDataFile.createNewFile();
        }

        ui.displayMsg("~ Tournament Manager ~");

        showStartMenu();
        String taskType = ui.getUserInput("\nUser input:").toLowerCase();
        handleStartMenuChoice(taskType);

        while(systemOn){
            showStartMenu();
            taskType = ui.getUserInput("\nUser input:").toLowerCase();
            handleStartMenuChoice(taskType);
        }
    }

    public static void printTounamentData(File file){
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

    public static void readTournamentData() throws IOException{
        String[] tournamentLine;
        File file = new File("src/tournamentData.txt");
        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            tournamentLine = line.split(",");

            String name = tournamentLine[1];

            // teams
            String sport = tournamentLine[3];
            String tournamentMode = tournamentLine[5];
            //String signUpDeadline = tournamentLine[3];

            tournaments.add(new Tournament(name, sport, tournamentMode));
        }

        /*
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            accountLine = line.split(":");
            new Player(accountLine[0], Integer.parseInt(accountLine[1]));
        }*/

    }

    public static void saveData(String fileName, String data){
        String fileData = "";
        fileData = data;

        try{
            File file = new File(fileName);
            FileWriter fr = new FileWriter(file, true);
            fr.write(data);
            fr.close();
        }catch (IOException e){
            System.out.println(e.getCause());
        }
    }

    public static String getTournamentData(){
        String tournamentDataAsString = "";

        for (Tournament tournament : tournaments) {
            tournamentDataAsString += tournament.toString();
        }

        return tournamentDataAsString;
    }

    public static void showStartMenu(){
        ui.displayMsg("\nWhat would you like to do?");
        ui.displayMsg("\n- Register new tournament\n (Type: new tournament)");
        ui.displayMsg("\n- Add team(s) to a tournament\n (Type: add team)");
        ui.displayMsg("\n- See data\n (Type: see data)");
        ui.displayMsg("\n- Close system\n (Type: end)");
    }

    public static void handleStartMenuChoice(String taskType){
        if(taskType.equals("new tournament")){
            registerNewTournament();
        }else if(taskType.equals("add team")){
            System.out.println("Add team");
        }else if(taskType.equals("see data")){
            System.out.println("See data");
        }else if(taskType.equals("end")){
            System.out.println("\nThe system has been turned off");
            systemOn = false;
        }else{
            System.out.println("Invalid input");
        }
    }

    public static void registerNewTournament(){
        ui.displayMsg("\nRegister new tournament");
        String name = ui.getUserInput("\nTournament name:");
        String sport = ui.getUserInput("Sport:");
        String mode = ui.getUserInput("Tournament mode (knock-out or group):");
        String signUpDeadline = ui.getUserInput("Signup deadline (d/m/yy h:mm):");

        Tournament tournament = new Tournament(name, sport, mode);
        tournaments.add(tournament);
        ui.displayMsg("\nNew tournament has been registered!");

        saveData("src/tournamentData.txt", getTournamentData());
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