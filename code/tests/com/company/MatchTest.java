package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {
    Team t1 = new Team("hihi");
    Team t2 = new Team("haha");
    Team[] teams = {t1, t2};
    Match match = new Match(teams);

    @Test
    void giveWinnerTeamPoints() {
        match.setTeam1Goals(10);
        match.setTeam2Goals(15);
        match.giveWinnerTeamPoints();
        int t1Goals = t1.getGoalsMade();
        int t2Goals = t2.getGoalsMade();

        assertEquals(10, t1Goals);
        assertEquals(15, t2Goals);
    }
}