package com.company;

import java.util.ArrayList;

public class Team {
    private int id;
    private String name;
    private ArrayList<Player> players;
    private boolean stillInTournament = true;
    private int point = 0;
    private int goalsMade = 0;
    private int opposingTeamsGoals = 0;

    public Team(String name, int id) {
        this.name = name;
        this.id= id;
    }

    public void addPlayer(Player playerName){
        players.add(playerName);
    }
}