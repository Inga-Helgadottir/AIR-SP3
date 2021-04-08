import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        createData();
    }

    public static void createData(){
        FileWriter writer = null;
        String input = "";
        System.out.println("Enter team name or q to quit");
        while(!input.toUpperCase().equals("Q")){
            Scanner scan = new Scanner(System.in);
            input = scan.nextLine();

            if(input.toUpperCase().equals("Q")){

            }else if(input.equals("")){
                System.out.println("Enter team name or q to quit");
            }else{
                System.out.println("Enter team name or q to quit");
                saveData(input);
            }
        }
        System.out.println("Thanks the team names has been saved");
    }

    public static void readData(){}
//    File file = new File("append.txt");
//    FileWriter fr = new FileWriter(file, true);
//    BufferedWriter br = new BufferedWriter(fr);
//br.write("data");
//
//br.close();
//fr.close();

    public static void saveData(String input){
//        FileWriter writer = null;
        FileWriter fr = null;
//        BufferedWriter br;
        try {
//            writer = new FileWriter("data.txt");
//            fr = new FileWriter(String.valueOf(writer), true);
//            br = new BufferedWriter(fr);
//            br.write(input);
//            writer.write(input);
            File file = new File("Data.txt");
            fr = new FileWriter(file, true);
            fr.write(input);

        } catch (IOException e) {
            System.out.println("Couldn't instantiate the FileWriter in saveGameData()");
            e.printStackTrace();
        } finally {
            try {
//                writer.close();
                fr.close();
//                br.close();
            } catch (NullPointerException | IOException e) {
                System.out.println("Couldn't close the FileWriter in saveGameData()");
                e.printStackTrace();
            }
        }
    }
}
/*
    public void registerPlayers(){
        String input="";
        System.out.println("Skriv navnene på spillets deltagere");
        System.out.println("tast Q for quitte");
        while(Main.players.size() < maxPlayers ){ //Tjekker om brugeren har tastet Q eller om der er registret 6 spillere
            input = getUserInput("Skriv navnet på spiller nr "+(Main.players.size()+1));

            if(input.toUpperCase().equals("Q")) {
                break;
            }else{
                Player player = new Player(input, startBalance);
                Main.players.add(player);
            }

        }
        System.out.println("Tak, spillets deltagere er registeret");
    }
 */