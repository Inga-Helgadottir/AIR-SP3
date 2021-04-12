package com.company;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Tournament {
   static final DateTimeFormatter myDateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");
   static private int idCounter;

   private int id;
   private String name;
   private String sport;
   private String tournamentMode;
   private LocalDateTime signUpDeadline;
   private ArrayList<LocalDateTime> dateAndTimes = new ArrayList<LocalDateTime>();
   private ArrayList<Team> teams = new ArrayList<Team>();
   private ArrayList<Match> matches = new ArrayList<Match>();


   // For tournaments created by user
   public Tournament(String name, String sport, String tournamentMode, String signUpDeadline) {
      this.id = idCounter;
      this.name = name;
      this.sport = sport;
      this.tournamentMode = tournamentMode;
      this.signUpDeadline = LocalDateTime.parse(signUpDeadline, myDateTimeFormat);
      idCounter++;
   }

   // For tournaments created from data
   public Tournament(int id, String name, String sport, String tournamentMode, String signUpDeadline) {
      this.id = id;
      this.name = name;
      this.sport = sport;
      this.tournamentMode = tournamentMode;
      this.signUpDeadline = LocalDateTime.parse(signUpDeadline, myDateTimeFormat);
   }


   public static int getIdCounter(){
      return idCounter;
   }

   public static void setIdCounter(int idCounterFromData){
      idCounter = idCounterFromData;
   }

   public int getId(){
      return id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
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

   public ArrayList<LocalDateTime> getDateAndTimes(){
      return dateAndTimes;
   }

   public void addDateAndTimes(String dateAndTimeToAdd){
      dateAndTimes.add(LocalDateTime.parse(dateAndTimeToAdd, myDateTimeFormat));
   }

   public ArrayList<Team> getTeams() {
      return teams;
   }

   public void addTeam(String teamName){
      Team team = new Team(teamName);
      teams.add(new Team(name));
   }


   @Override
   public String toString(){
      return
      "ID," + this.id + "," +
      "Name," + this.name + "," +
      "Sport," + this.sport + "," +
      "Tournament mode," + this.tournamentMode + "," +
      "SignUp deadline," + this.signUpDeadline.format(myDateTimeFormat);
   }

}
