package com.company;
import java.util.ArrayList;

public class Team {
   static private int idCounter;
   private int id;
   private String name;
   private ArrayList<String> players = new ArrayList<String>();

   // For teams created by user
   Team(String name){
      this.id = idCounter;
      this.name = name;
      idCounter++;
   }

   // For teams created from data
   Team(int id, String name){
      this.id = id;
      this.name = name;
   }

   public String getName(){
      return name;
   }

}
