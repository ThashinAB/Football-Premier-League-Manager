import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PremierLeagueManager implements LeagueManager{
      List<FootballClub> league = new ArrayList<>();
      List<Match> playedMatch = new ArrayList<>();
    private static final int MAX_COUNT = 20;
    private int clubSlots = 20;

    //adding a club method
    @Override
    public void addClub() {

        if(league.size() == clubSlots) {
            System.out.println("No more slots available");
            return;
        }

        Scanner sc =new Scanner(System.in);

        System.out.println("Enter the Club Name to be added: ");
        String name = sc.nextLine().toUpperCase();//uppercasing all the club names

        for(FootballClub club1: league) {
            if(name.equals(club1.getClubName())) {
                System.out.printf("Club %s already exists\n",name);//checking if the club already exists
                return;

            }
        }
        System.out.println("Enter Location of the Club: ");
        String location = sc.nextLine().toLowerCase();

        FootballClub club = new FootballClub(name,location);

        league.add(club);
        System.out.printf("%s was added to Premier League Successfully!\n", club.getClubName());
    }

    @Override
    public void deleteClub(String name) {
        boolean foundClub = false;
        for (SportsClub club: league) {
            if(club.getClubName().equals(name)) {//checking if the club already exists
                league.remove(club);
                foundClub = true;
                System.out.printf("%s was removed Successfully!\n", club.getClubName());
                break;
            }
        }
        if (!foundClub) {
            System.out.printf("Club with %s is not found in the League\n", name);//to avoid printing it many times
        }
    }
    //to display stats of a club
    @Override
    public void displayStats(String name) {

        boolean foundClub = false;
        for (FootballClub club : league) {
            if(club.getClubName().equals(name)){
                foundClub = true;
                System.out.println("Club " + club.getClubName()+" stats : ");
                System.out.println("  Matches Won    : " +club.getWinCount());
                System.out.println("  Matches Tied   : " +club.getDrawCount());
                System.out.println("  Matches Lost   : " +club.getDefeatCount());
                System.out.println("  Scored Goals   : " +club.getGoalsScoredCount());
                System.out.println("  Received Goals : " +club.getGoalsReceivedCount());
                System.out.println("  Points         : " +club.getPoints());
                System.out.println("  Matches Played : " +club.getMatchesPlayed());
                break;
            }
        }
        if (!foundClub) {
            System.out.printf("Club with %s is not available\n", name);//to avoid printing it many times
        }
    }
    //to display the league table
    @Override
    public void displayLeagueTable(){
        if (league.isEmpty()){
            System.out.println("THE PREMIER LEAGUE TABLE is empty");
            return;
        }

        System.out.println("------------- THE PREMIER LEAGUE TABLE ------------------             ");
        Collections.sort(league, new PointsComparator());
        for (FootballClub club: league) {
            System.out.println("Club: " + club.getClubName()+" Played: "+ club.getMatchesPlayed()+" Won: "+ club.getWinCount()+" Tied: "+ club.getDrawCount()+" Lost: "+club.getDefeatCount()+" Points: "+club.getPoints());
        }
    }
    //to add a played match
    @Override
    public void addMatch() {
        Scanner sc = new Scanner(System.in);


        System.out.println("Enter date (format yyyy-mm-dd): ");
        String date;
        Date d = null;
        while (true) {
            date = sc.nextLine();

            try {//validating the date if its a text or thr wrong dateformat
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                d = simpleDateFormat.parse(date);
                if (date.equals(simpleDateFormat.format(d))) {
                    break;
                }
            } catch (ParseException e) {
                System.out.println("You have to enter date in this format(yyyy-mm-dd): ");

            }
        }

        System.out.println("Enter team A : ");
        String a = sc.nextLine().toUpperCase();
        FootballClub team1 = null;

        boolean teamOne = false;
        for (FootballClub club : league) {
            if (club.getClubName().equals(a)) {
                team1 = club;
                teamOne = true;
                break;
            }
        }
        if (!teamOne) {
            System.out.printf("Club with %s is not available\n", a);//to avoid printing it many times
            return;
        }

        System.out.println("Enter team B");
        String b = sc.nextLine().toUpperCase();
        FootballClub team2 = null;

        boolean teamTwo = false;
        for (FootballClub club : league) {
            if (club.getClubName().equals(b)) {
                team2 = club;
                teamTwo = true;
                break;
            }
        }
        if (!teamTwo) {
            System.out.printf("Club with %s is not available\n", b);//to avoid printing it many times
            return;
        }

        int teamAScore = 0;

        while (true) {//validating if the score is in integer and should be a positive number
            System.out.printf("Enter %s goal score : ", team1.getClubName());
            String line = sc.nextLine();

            try {
                teamAScore = Integer.parseInt(line);
                if (teamAScore > 0) {
                    break;
                }
                else{
                    System.out.println("You have to enter a positive number!");
                }
            } catch (Exception e) {
                System.out.println("It has to be a number!");
            }

        }


        int teamBScore = 0;

        while (true) {//validating if the score is in integer and should be a positive number
            System.out.printf("Enter %s goal score : ", team2.getClubName());
            String line = sc.nextLine();

            try {
                teamBScore = Integer.parseInt(line);
                if (teamBScore > 0) {
                    break;
                }
                else{
                    System.out.println("You have to enter a positive number!");
                }
            } catch (Exception e) {
                System.out.println("It has to be a number! ");
            }
        }


            Match match = new Match(team1,team2,teamAScore,teamBScore,date);
            playedMatch.add(match);
            team1.setGoalsScoredCount(team1.getGoalsScoredCount()+teamAScore);
            team2.setGoalsScoredCount(team2.getGoalsScoredCount()+teamBScore);
            team1.setGoalsReceivedCount(team1.getGoalsReceivedCount()+teamBScore);
            team2.setGoalsReceivedCount(team2.getGoalsReceivedCount()+teamAScore);
            team1.setMatchesPlayed(team1.getMatchesPlayed()+1);
            team2.setMatchesPlayed(team2.getMatchesPlayed()+1);

            if (match.getTeamAScore() > match.getTeamBScore()){
                team1.setWinCount(team1.getWinCount()+1);
                team2.setDefeatCount(team2.getDefeatCount()+1);
                team1.setPoints(team1.getPoints()+3);

            }
            else if (match.getTeamAScore() < match.getTeamBScore()){
                team2.setWinCount(team2.getWinCount()+1);
                team2.setPoints(team2.getPoints()+3);
                team1.setDefeatCount(team1.getDefeatCount()+1);
            }
            else {
               team1.setDrawCount(team1.getDrawCount()+1);
               team2.setDrawCount(team2.getDrawCount()+1);
               team1.setPoints(team1.getPoints()+1);
               team2.setPoints(team2.getPoints()+1);
            }
        System.out.println("Played match was succesfully added");

    }

    public void saveFile(String fileName,String fileName2) throws IOException {
        //writing Object to file

        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        // Write objects to file
        for (FootballClub f1:league){
            oos.writeObject(f1);
        }

        oos.flush();
        oos.close();
        fos.close();

        FileOutputStream fos2 = new FileOutputStream(fileName2);
        ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
        // Write objects to file
        for (Match m1: playedMatch){
            oos2.writeObject(m1);
        }

        oos2.flush();
        oos2.close();
        fos2.close();
        System.out.println("Data has been saved to file successfully");

    }

    @Override
    public void loadData(String fileName,String fileName2) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);

        for(;;) {
            try {
                FootballClub f1 = (FootballClub) ois.readObject();
                league.add(f1);
            }
            catch (EOFException | ClassNotFoundException e) {
               // System.out.println("Files are empty, Please save first!");
                break;
            }
        }

        fis.close();
        ois.close();

        FileInputStream fis2 = new FileInputStream(fileName2);
        ObjectInputStream ois2 = new ObjectInputStream(fis2);

        for(;;) {
            try {
                Match m2 = (Match) ois2.readObject();
                playedMatch.add(m2);
            }
            catch (EOFException | ClassNotFoundException e) {
                //System.out.println("Files are empty, Please save first!");
                break;
            }
        }

        fis2.close();
        ois2.close();

        System.out.println("Data has been Successfully Loaded from the File");
    }


}



