package com.company;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Tournament {
   static DateTimeFormatter myDateTimeFormat = DateTimeFormatter.ofPattern("d/M/yy H:mm");
   private String name;
   private ArrayList<Team> teams;
   private String sport;
   private String tournamentMode;
   private LocalDateTime signUpDeadline;
   //private ArrayList<LocalDateTime> dateAndTimes;

   public Tournament(String name, String sport, String tournamentMode) {
      this.name = name;
      this.sport = sport;
      this.tournamentMode = tournamentMode;
      //this.signUpDeadline = LocalDateTime.parse(signUpDeadline, myDateTimeFormat);
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public ArrayList<Team> getTeams() {
      return teams;
   }

   public void addTeam(String teamName){
      Team team = new Team(teamName);
      teams.add(new Team(name));
   }

   public String getSport() {
      return sport;
   }

   public void setSport(String sport) {
      this.sport = sport;
   }

   public String getTournamentMode() {
      return tournamentMode;
   }

   public void setTournamentMode(String tournamentMode) {
      this.tournamentMode = tournamentMode;
   }

   public LocalDateTime getSignUpDeadline() {
      return signUpDeadline;
   }

   public void setSignUpDeadline(LocalDateTime signUpDeadline) {
      this.signUpDeadline = signUpDeadline;
   }

//   public ArrayList<LocalDateTime> getDateAndTimes() {
//      return dateAndTimes;
//   }

   @Override
   public String toString(){
      return
         "Name," + this.name + "," +
         //",teams:" + this.teams.toString() + "," +
         " Sport," + this.sport + "," +
         " Tournament mode," + this.tournamentMode + "," +
         " SignUp deadline," + this.signUpDeadline +
         "\n";
   }

}
