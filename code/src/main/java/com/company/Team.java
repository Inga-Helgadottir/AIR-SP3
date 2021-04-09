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
}
