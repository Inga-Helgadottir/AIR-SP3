import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        createData();
    }

    public static void createData(){
        ArrayList<String> players = new ArrayList<String>();
        String msg = "Enter team name or q to quit";
        String msg2 = "Enter the name of a player or q to quit";
        String whatIsSentNow = "";
        FileWriter writer = null;
        String input = "";
        int counter = 1;
        while(!input.toUpperCase().equals("Q")){
            System.out.println(msg);
            Scanner scan = new Scanner(System.in);
            input = scan.nextLine();

            while (counter == 1) {
                    System.out.println(msg2);
                    players.add(input);
                    input = scan.nextLine();
                if(input.toUpperCase().equals("Q")){
                    System.out.println("Your players have been registered");
                    counter = 3;
                }
            }

            if(input.toUpperCase().equals("Q")){

            }else if(input.equals("")){
                System.out.println(msg);
            }else if(counter == 3){
                counter = 1;
                saveData(input, players);
            }
        }
    }

    public static void readData(){}

    public static void saveData(String input, ArrayList<String> players){
        String teamName;
        boolean stillPlaying = true;
        int point = 0;
        int goalsMade = 0;
        int opposingTeamsGoals = 0;
        FileWriter fr = null;
        try {
            File file = new File("Data.txt");
            fr = new FileWriter(file, true);
            String sendToFile = "TeamName: " + input + ", Players: " + players.toString();
            fr.write(sendToFile);

        } catch (IOException e) {
            System.out.println("Couldn't instantiate the FileWriter in saveGameData()");
            e.printStackTrace();
        } finally {
            try {
                fr.close();
            } catch (NullPointerException | IOException e) {
                System.out.println("Couldn't close the FileWriter in saveGameData()");
                e.printStackTrace();
            }
        }
    }
}