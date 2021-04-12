package com.company;
import java.util.Scanner;

import java.util.Scanner;

public class UI {

   public void displayMsg(String msg){
      System.out.println(msg);
   }

   public String getUserInput(String msg){
      Scanner scanner = new Scanner(System.in);
      System.out.println(msg);
      String input = scanner.nextLine();
      return input;
   }

}
