

import java.io.IOException;
import java.util.Scanner;

public class ConsoleApplication  {
    private static PremierLeagueManager footballLeagueManager = new PremierLeagueManager();


    public static void main(String[] args) throws IOException {
        footballLeagueManager.loadData("textFile.txt","textFile2.txt");
        displayMenu();
    }

    //menu
    public static void displayMenu() throws IOException {

        while (true){
            System.out.println("\nPress 1: to add  a Club");
            System.out.println("Press 2: to delete  a Club");
            System.out.println("Press 3: to display Club statistics");
            System.out.println("Press 4: to display the Premier League Table");
            System.out.println("Press 5: to Add a played match");
            System.out.println("Press 6: to save data to a file");
            System.out.println("Press 7: to start GUI\n");

            Scanner sc = new Scanner(System.in);
            int userChoice;

            System.out.println("Enter your Choice!");
            while (!sc.hasNextInt()) {
                System.out.println("That's not a number! please Re-enter");
                sc.next(); // this is important!
            }
            userChoice = sc.nextInt();

            switch (userChoice) {
                case 1:
                    footballLeagueManager.addClub();
                    break;
                case 2:
                    deleteClub();
                    break;
                case 3:
                    displayStats();
                    break;
                case 4:
                   footballLeagueManager.displayLeagueTable();
                    break;
                case 5:
                    footballLeagueManager.addMatch();
                    break;
                case 6:
                    footballLeagueManager.saveFile("textFile.txt","textFile2.txt");
                    System.exit(0);//save and exit
                case 7:
                    GUI_Application.footballLeagueManager=footballLeagueManager;//othwerwise object wont work in gui
                    GUI_Application.launch(GUI_Application.class); //launch application from another class
                    break;

                default:
                    System.out.println("Choice invalid, Please enter again...");
            }
        }
    }


    private static void displayStats() {
        System.out.println("Please enter the name of the club :");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine().toUpperCase();
        footballLeagueManager.displayStats(name);
    }

    private static void deleteClub() {
        System.out.println("Please enter the name of the club you want to remove:");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine().toUpperCase();
        footballLeagueManager.deleteClub(name);
    }

}



