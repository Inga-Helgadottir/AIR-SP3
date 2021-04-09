package com.company;

import java.util.ArrayList;

public class Team {
   private int id;
   private String name;
   private ArrayList<String> players;
   private boolean stillInTournament = true;
   private int point = 0;
   private int goalsMade = 0;
   private int opposingTeamsGoals = 0;

   public Team(String name) {
      this.name = name;
   }

   public void addPlayer(String playerName){
      players.add(playerName);
   }

   @Override
   public String toString() {
      return "Team{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", players=" + players +
              ", stillInTournament=" + stillInTournament +
              ", point=" + point +
              ", goalsMade=" + goalsMade +
              ", opposingTeamsGoals=" + opposingTeamsGoals +
              '}';
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

   public void setPoint(int point) {
      this.point = point;
   }

   public void setGoalsMade() {
      this.goalsMade++;
   }

   public void setOpposingTeamsGoals() {
      this.opposingTeamsGoals++;
   }
}
