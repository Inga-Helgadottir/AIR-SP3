package com.company;

import java.util.ArrayList;

public class Team {
   static private int idCounter;

   private int id;
   private String name;
   private ArrayList<String> players = new ArrayList<String>();
   private boolean stillInTournament = true;
   private int point = 0;
   private int goalsMade = 0;
   private int opposingTeamsGoals = 0;

   // For tournaments created by user
   public Team(String name) {
      this.id = idCounter;
      this.name = name;
      idCounter++;
   }

   // For tournaments created from data
   public Team(int id, String name) {
      this.name = name;
   }


   public static int getIdCounter(){
      return idCounter;
   }

   public static void setIdCounter(int idCounterFromData){
      idCounter = idCounterFromData;
   }

   public void addPlayer(String playerName){
      players.add(playerName);
   }

   public String getName() {
      return name;
   }

   public int getGoalsMade() {
      return goalsMade;
   }

   public int getOpposingTeamsGoals() {
      return opposingTeamsGoals;
   }

   public void setGoalsMade(int goals) {
      this.goalsMade += goals;
   }

   public void setOpposingTeamsGoals(int goals) {
      this.opposingTeamsGoals += goals;
   }

   public void joinTournament(Tournament tournamentToJoin){
      tournamentToJoin.addTeam(this);
   }


//   @Override
//   public String toString() {
//      return "Team{" +
//              "id=" + id +
//              ", name='" + name + '\'' +
//              ", players=" + players +
//              ", stillInTournament=" + stillInTournament +
//              ", point=" + point +
//              ", goalsMade=" + goalsMade +
//              ", opposingTeamsGoals=" + opposingTeamsGoals +
//              '}';
//   }

   @Override
   public String toString() {
      return
      "ID," + this.id + "," +
      "Name," + this.name + "," +
//      "Players," + players + "," +
      "Still in tournament," + stillInTournament + "," +
      "Point," + point + "," +
      "Goals made," + goalsMade + "," +
      "Opposing team's goals," + opposingTeamsGoals + "\n";
   }
}
