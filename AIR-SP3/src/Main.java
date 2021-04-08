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
        boolean doneAdding = false;
        while(doneAdding == false){
            Scanner scan = new Scanner(System.in);
            input = scan.nextLine();

            if(input.toUpperCase().equals("Q")) {
                break;
            }else if(input.toUpperCase().equals("")){
                System.out.println("Enter team name or q to quit");
            }else{
                System.out.println("Enter team name or q to quit");
                saveData(input);
            }
        }
        System.out.println("Thanks the team name has been saved");
    }

    public static void readData(){}

    public static void saveData(String input){
        FileWriter writer = null;
        try {
            writer = new FileWriter("data.txt");
//            writer.write(getGameDataFromSession());
        } catch (IOException e) {
            System.out.println("Couldn't instantiate the FileWriter in saveGameData()");
            e.printStackTrace();
        } finally {
            try {
                writer.close();
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