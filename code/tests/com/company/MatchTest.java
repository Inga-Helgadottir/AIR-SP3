package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {
    Team t1 = new Team("hihi");
    Team t2 = new Team("haha");
    Team[] teams = {t1, t2};
    Match match = new Match(teams);

    @Test
    void whoWon() {
        match.setTeam1Goals(10);
        match.setTeam2Goals(15);
        match.whoWon();
        int t1Goals = t1.getGoalsMade();
        int t2Goals = t2.getGoalsMade();
        int t1OGoals = t1.getOpposingTeamsGoals();
        int t2OGoals = t2.getOpposingTeamsGoals();

        assertEquals(10, t1Goals);
        assertEquals(15, t2Goals);

        assertEquals(15, t1OGoals);
        assertEquals(10, t2OGoals);
    }
}