package com.company;

import java.util.Date;

public class Match {
    Team[] teams;
    Date gameDate;
    int team1Goals;
    int team2Goals;
    boolean gameOver = false;

    public Match(Team[] teams) {//add later: gameDate
        this.teams = teams;
    }
    /* testing match
        public void testing(){
            for (int i = 0; i < teams.length-1; i++) {
                System.out.println(teams[i].toString());
            }
    }*/

    public void giveWinnerTeamPoints() {
        if(gameOver){
            if(team1Goals < team2Goals){
                System.out.println(teams[0] + " won");
                updateGoalsMade(teams[0]);
            }else if(team1Goals > team2Goals){
                System.out.println(teams[1] + " won");
                updateGoalsMade(teams[1]);
            }else if(team1Goals == team2Goals){
                System.out.println("It was a tie");
                updateGoalsMade(teams[0]);
                updateGoalsMade(teams[1]);
            }
        }
    }

    public void updateGoalsMade(Team team){
        team.setGoalsMade();
        String goalBy = team.getName();
        if(goalBy != teams[0].getName()){
            teams[0].setOpposingTeamsGoals();
        }else{
            teams[1].setOpposingTeamsGoals();
        }
    }
}