package com.company;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
   static UI ui = new UI();
   static ArrayList<Tournament> tournaments = new ArrayList<Tournament>();
   static boolean systemOn = true;

   public static void main(String[] args){
      //Read all id counters and set to corresponding classes
      Tournament.setIdCounter(readIdCounterData("src/data/idCounters/idCounter_Tournament.txt"));

      //Read all tournament data
      readTournamentData("src/data/tournaments");

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


   public static void readTournamentData(String filePath){
      try{
         File tournamentsDir = new File(filePath);
         String tournamentsDirContent[] = tournamentsDir.list();

         for(int i = 0; i<tournamentsDirContent.length; i++) {
            File tournamentData = new File(filePath + "/" + tournamentsDirContent[i] + "/tournamentData.txt");
            Scanner scanner = new Scanner(tournamentData);
            String[] tournamentLine;

            while(scanner.hasNextLine()) {
               String line = scanner.nextLine();
               tournamentLine = line.split(",");

               int id = Integer.parseInt(tournamentLine[1]);
               String name = tournamentLine[3];
               String sport = tournamentLine[5];
               String tournamentMode = tournamentLine[7];
               String signUpDeadline = tournamentLine[9];

               tournaments.add(new Tournament(id, name, sport, tournamentMode, signUpDeadline));

               // Adds dateAndTimes data to the corresponding tournament
               readTournamentDateAndTimesData(filePath, tournaments.get(i));
            }
            scanner.close();
         }
      }catch(IOException e){
         System.out.println(e);
      }

   }

   public static void readTournamentDateAndTimesData(String filePath, Tournament tournament){
      try{
         File tournamentsDir = new File(filePath);
         String tournamentsDirContent[] = tournamentsDir.list();

         for(int i=0; i<tournamentsDirContent.length; i++) {
            File dateAndTimesData = new File(filePath + "/" + tournament.getName() + "/dateAndTime.txt");
            Scanner scanner = new Scanner(dateAndTimesData);
            String[] dateAndTimesLines;

            while(scanner.hasNextLine()) {
               String line = scanner.nextLine();
               dateAndTimesLines = line.split(",");

               for(String dateAndTimesLine : dateAndTimesLines){
                  tournament.addDateAndTimes(dateAndTimesLine);
               }
            }

            scanner.close();
         }
      }catch(IOException e){
         System.out.println(e);
      }
   }

   public static void saveTournamentData(String filePath, String fileName, String data){
      String fileData = data;

      try{
         createNewDir(filePath, fileName);

         File file = new File(filePath + "/" + fileName + "/tournamentData.txt");
         FileWriter fileWriter = new FileWriter(file, false);
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
      // todo add team-menu
      // todo add data menu
      // todo Change the "Close system" type
      ui.displayMsg("\n- Close system (Type: 10)");
   }

   public static void handleStartMenuChoice(String taskType){
      if(taskType.equals("1")){
         showTournamentMenu();
      }else if(taskType.equals("10")){
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
      ui.displayMsg("\n- Edit a tournament (Type: 2)");
      ui.displayMsg("\n- Delete a tournament (Type: 3)");
      ui.displayMsg("\n- Go back to start-menu (Type: 4)");

      String taskType = ui.getUserInput("\nUser input:");
      handleTournamentChoice(taskType);
   }

   public static void handleTournamentChoice(String taskType){
      if(taskType.equals("1")){
         registerNewTournament();
      }else if(taskType.equals("2")){
         //todo Make tournament editing available
         System.out.println("Editing option not yet available");
      }else if(taskType.equals("3")){
         deleteTournament();
      }else if(taskType.equals("4")){
         return;
      }else{
         System.out.println("Invalid input");
      }
   }

   public static void registerNewTournament(){
      ui.displayMsg("\n(REGISTER NEW TOURNAMENT)");

      String name = ui.getUserInput("\nTournament name:");
      String sport = ui.getUserInput("Sport:");
      String mode = ui.getUserInput("Tournament mode (knock-out or group):");
      String signUpDeadline = ui.getUserInput("Signup deadline (dd-MM-yy HH:mm):");

      Tournament tournament = new Tournament(name, sport, mode, signUpDeadline);
      tournaments.add(tournament);
      ui.displayMsg("\nNew tournament has been registered!");

      String willAddDateAndTimesNow = ui.getUserInput("\nWould you like to add match date and times now? (y/n):");

      saveIdCounterData("src/data/idCounters/idCounter_Tournament.txt", "ID:" + Tournament.getIdCounter());
      saveTournamentData("src/data/tournaments", name, tournament.toString());
   }

   public static void addMatchDateAndTimes(Tournament tournament){

   }

   public static void deleteTournament(){
      ui.displayMsg("\n(DELETE TOURNAMENT)");
      ui.displayMsg("\nTournaments currently in the system: ");

      if(tournaments.size() != 0){
         displayAllTournaments();

         String userInput = ui.getUserInput("\nType the id of the tournament you " +
         "would like to delete: \nType -1 to cancel");

         if(!userInput.equals("-1")){
            int tournamentId = Integer.parseInt(userInput);

            Tournament tournamentToBeDeleted = findTournament(tournamentId);

            if(tournamentToBeDeleted != null){
               File fileToBeDeleted = new File("src/data/tournaments/" + tournamentToBeDeleted.getName());
               deleteFolder(fileToBeDeleted);

               tournaments.remove(findTournament(tournamentId));

               ui.displayMsg("\nThe tournament was successfully deleted.");
            }else{
               ui.displayMsg("\nNo tournament matching the provided id could be found in the system...");
            }
         }
      }else{
         ui.displayMsg("There are currently no tournaments registered in the system.");
      }
   }

   public static void displayAllTournaments(){
      for(Tournament tournament : tournaments){
         System.out.println(tournament.getName() + " (ID: " + tournament.getId() + ")");
      }
   }

   public static Tournament findTournament(int id){
      Tournament tournamentMatch = null;

      for(Tournament tournament : tournaments){
         if(tournament.getId() == id){
            tournamentMatch = tournament;
            break;
         }
      }

      return tournamentMatch;
   }

}
