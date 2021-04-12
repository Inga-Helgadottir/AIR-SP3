package com.company;

import java.util.Date;

public class Match {
    Team[] teams;
    Date gameDate;
    int team1Goals;
    int team2Goals;

    public Match(Team[] teams) {//add later: gameDate
        this.teams = teams;
    }

    public void whoWon() {
        if(team1Goals > team2Goals){
            System.out.println(teams[0].getName() + " won");
        }else if(team1Goals < team2Goals){
            System.out.println(teams[1].getName() + " won");
        }else if(team1Goals == team2Goals){
            System.out.println("It was a tie");
        }
        updateGoalsMade();
    }

    public void updateGoalsMade(){
        teams[0].setGoalsMade(team1Goals);
        teams[1].setGoalsMade(team2Goals);
        teams[0].setOpposingTeamsGoals(team2Goals);
        teams[1].setOpposingTeamsGoals(team1Goals);
    }

    public Team[] getTeams() {
        return teams;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }

    public int getTeam1Goals() {
        return team1Goals;
    }

    public void setTeam1Goals(int team1Goals) {
        this.team1Goals = team1Goals;
    }

    public int getTeam2Goals() {
        return team2Goals;
    }

    public void setTeam2Goals(int team2Goals) {
        this.team2Goals = team2Goals;
    }
}