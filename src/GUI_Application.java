import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class GUI_Application extends Application {

    static PremierLeagueManager footballLeagueManager = new PremierLeagueManager();
    Stage stage;
    String sRandomMatch, sM;
    Scene scene, scene1, scene2;
    TableView<FootballClub> leagueTable;
    TableView<Match> playedMatchTable;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("THE PREMIER LEAGUE TABLE");

        FlowPane flowPane = new FlowPane();

        Label heading = new Label("Premier League Table");
        heading.setFont(Font.font("Arial", FontPosture.ITALIC, 29));
        heading.setTranslateY(10);
        heading.setTranslateX(300);

        Label sortByName = new Label("Sort By:");
        sortByName.setFont(Font.font("Arial",15));


        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().add("Points");
        comboBox.getItems().add("Goals");
        comboBox.getItems().add("Wins");


        //Name column
        TableColumn<FootballClub, String> clubNameColumn = new TableColumn<>("Club Name");
        clubNameColumn.setMinWidth(120);
        clubNameColumn.setCellValueFactory(new PropertyValueFactory<>("clubName"));

        //no of matches played column
        TableColumn<FootballClub, String> matchesPlayedColumn = new TableColumn<>("Matches Played");
        matchesPlayedColumn.setMinWidth(120);
        matchesPlayedColumn.setCellValueFactory(new PropertyValueFactory<>("matchesPlayed"));

        //no of matches won
        TableColumn<FootballClub, String> totalWinColumn = new TableColumn<>("Won");
        totalWinColumn.setMinWidth(90);
        totalWinColumn.setCellValueFactory(new PropertyValueFactory<>("winCount"));

        //no of matches won
        TableColumn<FootballClub, String> tiedColumn = new TableColumn<>("Tied");
        tiedColumn.setMinWidth(90);
        tiedColumn.setCellValueFactory(new PropertyValueFactory<>("drawCount"));

        //no of matches lost
        TableColumn<FootballClub, String> lostColumn = new TableColumn<>("Lost");
        lostColumn.setMinWidth(90);
        lostColumn.setCellValueFactory(new PropertyValueFactory<>("defeatCount"));

        //no of goals scored
        TableColumn<FootballClub, String> goalsScoredColumn = new TableColumn<>("Goals Scored");
        goalsScoredColumn.setMinWidth(100);
        goalsScoredColumn.setCellValueFactory(new PropertyValueFactory<>("goalsScoredCount"));

        //no of goals received
        TableColumn<FootballClub, String> goalsReceivedColumn = new TableColumn<>("Goals Received");
        goalsReceivedColumn.setMinWidth(100);
        goalsReceivedColumn.setCellValueFactory(new PropertyValueFactory<>("goalsReceivedCount"));

        //total points of  club
        TableColumn<FootballClub, String> pointsColumn = new TableColumn<>("Total Points");
        pointsColumn.setMinWidth(100);
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));

        leagueTable = new TableView<>();
        leagueTable.setItems(getFootballClub(comboBox));
        leagueTable.getColumns().addAll(clubNameColumn,matchesPlayedColumn,totalWinColumn,tiedColumn,lostColumn,goalsScoredColumn,goalsReceivedColumn,pointsColumn);



        Label generatePlayedMatchLabel = new Label();
        //button for search match

        Label heading2 = new Label("Played Matches");
        heading2.setFont(Font.font("Arial", FontPosture.ITALIC, 30));
        heading2.setTranslateY(10);
        heading2.setTranslateX(300);

        FlowPane flowPane1 = new FlowPane();

        TableColumn<Match, String> teamAColumn = new TableColumn<>("Team A");
        teamAColumn.setMinWidth(140);
        teamAColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTeamA().getClubName()));

        //no of matches played column
        TableColumn<Match, String> teamBColumn = new TableColumn<>("Team B");
        teamBColumn.setMinWidth(140);
        teamBColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTeamB().getClubName()));

        //no of matches won
        TableColumn<Match, String> teamAScoreColumn = new TableColumn<>("Team A Score");
        teamAScoreColumn.setMinWidth(140);
        teamAScoreColumn.setCellValueFactory(new PropertyValueFactory<>("teamAScore"));

        //no of matches won
        TableColumn<Match, String> teamBScoreColumn = new TableColumn<>("Team B score");
        teamBScoreColumn.setMinWidth(140);
        teamBScoreColumn.setCellValueFactory(new PropertyValueFactory<>("teamBScore"));

        //no of matches lost
        TableColumn<Match, String> dateColumn = new TableColumn<>("Match Date");
        dateColumn.setMinWidth(190);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        playedMatchTable = new TableView<>();
        playedMatchTable.setItems(getMatchByDate());
        playedMatchTable.getColumns().addAll(teamAColumn,teamBColumn,teamAScoreColumn,teamBScoreColumn,dateColumn);

        //button to generate random played matches
        Button generatePlayedMatchButton = new Button("Generate played matches");
        generatePlayedMatchButton.setOnAction(event -> {
            generateRandom();
            leagueTable.refresh();
            playedMatchTable.getItems().clear();
            generatePlayedMatchLabel.setText(sRandomMatch);

        });

        Button sortMatchButton = new Button("Sort by date");
        sortMatchButton.setTranslateY(270);
        sortMatchButton.setTranslateX(-60);
        sortMatchButton.setOnAction(event ->{
            dateColumn.setSortType(TableColumn.SortType.DESCENDING);
            playedMatchTable.getSortOrder().addAll(dateColumn);
            playedMatchTable.sort();
        });

        //creating the matches played table
        Button displayMatchButton = new Button("Display played matches");
        displayMatchButton.setOnAction(event -> {
            playedMatchTable.setItems(getMatchByDate());
            stage.setScene(scene1);
        });

        Button goBack = new Button("Go back");
        goBack.setOnAction(event -> {
            stage.setScene(scene);
        });

        FlowPane flowPane2 = new FlowPane();

        TextField textField = new TextField("Date(yyyy-mm-dd)");

        Label searchDateLabel = new Label();
        searchDateLabel.setMinWidth(200);
        searchDateLabel.setMaxHeight(200);


        Button click = new Button("Click");

        Button searchDateMatch = new Button("Search Match");
        searchDateMatch.setOnAction(event -> {
            stage.setScene(scene2);


        });

        click.setOnAction(event -> {
            searchDate(textField);
            searchDateLabel.setText(sM);

        });

        Button goBackSearchButton = new Button("Go Back");


        goBackSearchButton.setOnAction(event -> {
           stage.setScene(scene);

        });

        //for Premier League Table(1st scene)
        VBox vBox = new VBox();
        vBox.getChildren().addAll(leagueTable);
        vBox.setTranslateY(40);
        vBox.setTranslateX(5);

        //for played match table (2nd scene)
        VBox vBox1 = new VBox();
        vBox1.getChildren().addAll(playedMatchTable,goBack);
        vBox1.setTranslateY(70);
        vBox1.setTranslateX(50);

        // search match(3rd scene)
        HBox hBox1 = new HBox();
        hBox1.getChildren().addAll(textField,click);
        hBox1.setSpacing(10);

        // search match(3rd scene)
        VBox vBox4 = new VBox();
        vBox4.getChildren().addAll(hBox1,searchDateLabel,goBackSearchButton);
        vBox4.setSpacing(10);
        vBox4.setTranslateY(20);
        vBox4.setTranslateX(20);

        //for sort by combo box and text(1st scene)
        HBox hBox = new HBox();
        hBox.getChildren().addAll(sortByName,comboBox,searchDateMatch);
        hBox.setSpacing(20);
        hBox.setTranslateY(487);
        hBox.setTranslateX(90);

        //
        VBox vBox2 = new VBox();
        vBox2.getChildren().addAll(displayMatchButton);
        vBox2.setTranslateY(50);
        vBox2.setTranslateX(15);

        VBox vBox3 = new VBox();
        vBox3.getChildren().addAll(generatePlayedMatchButton,generatePlayedMatchLabel);
        vBox3.setSpacing(10);
        vBox3.setTranslateY(50);
        vBox3.setTranslateX(20);


        flowPane.getChildren().addAll(heading, hBox, vBox,vBox2,vBox3); //Display table(scene1)
        flowPane1.getChildren().addAll(heading2,vBox1,sortMatchButton);  //Display played matches(scene2)
        flowPane2.getChildren().addAll(vBox4);


        scene = new Scene(flowPane,820,620);
        scene1 = new Scene(flowPane1,840,600);
        scene2 = new Scene(flowPane2,400,300);

        stage.setScene(scene);
        stage.show();
    }

    //get all the football clubs
    public  ObservableList<FootballClub> getFootballClub(ComboBox <String> comboBox) {
        ObservableList<FootballClub> footballClubData = FXCollections.observableArrayList();
        for (FootballClub c:footballLeagueManager.league){
            footballClubData.add(c);
        }
        comboBox.setOnAction(event -> {//sorting
            if (comboBox.getValue().equals("Points")) {
                leagueTable.getItems().clear();// to remove all data
                footballLeagueManager.league.sort(new PointsComparator());
            }
            if (comboBox.getValue().equals("Goals")) {
                leagueTable.getItems().clear();
                footballLeagueManager.league.sort(new ScoreComparator().reversed());
            }
            else if (comboBox.getValue().equals("Wins")) {
                leagueTable.getItems().clear();
                footballLeagueManager.league.sort(new WinComparator().reversed());

            }
            for (FootballClub c:footballLeagueManager.league){
                footballClubData.add(c);
            }
        });return footballClubData;

    }



    public ObservableList<Match> getMatchByDate(){

        ObservableList<Match> matchByDateData = FXCollections.observableArrayList();
        for (Match m:footballLeagueManager.playedMatch){
            matchByDateData.add(m);
        }
        return matchByDateData;
    }


    public void generateRandom()  {
        LocalDate startDate = LocalDate.of(2020, 1, 1); //start date
        long start = startDate.toEpochDay();

        LocalDate endDate = LocalDate.of(2021,12,1); //end date
        long end = endDate.toEpochDay();

        long randomDate = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
        //System.out.println(LocalDate.ofEpochDay(randomDate)); //random date between the range(start and end)
        String s=String.valueOf(LocalDate.ofEpochDay(randomDate));
        //System.out.println(s);

        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date);
        int teamAScore = (int)(Math.random()*8);
        int teamBScore = (int)(Math.random()*8);

        int teamA = (int) (Math.random() * footballLeagueManager.league.size());//generate random index using Math.random method

        int teamB;
        while (true) {
            teamB =(int)(Math.random() * footballLeagueManager.league.size()); //randomly generating an index number
            if (teamB!= teamA){
                break;
            }
        }

        // getting the element of the specific index
        FootballClub team1 = footballLeagueManager.league.get(teamA);
        FootballClub team2 = footballLeagueManager.league.get(teamB);

        Match randomMatch = new Match(team1,team2,teamAScore,teamBScore,s);
        System.out.println(randomMatch);
        footballLeagueManager.playedMatch.add(randomMatch);

        sRandomMatch = String.valueOf(randomMatch.getTeamA().getClubName()+" vs "+randomMatch.getTeamB().getClubName()+"\n\n"
                                            +randomMatch.getTeamA().getClubName()+" score: "+randomMatch.getTeamAScore()+"\n"
                                            +randomMatch.getTeamB().getClubName()+" score: "+randomMatch.getTeamBScore()+"\nDate: "
                                            +randomMatch.getDate());

        team1.setGoalsScoredCount(team1.getGoalsScoredCount()+teamAScore);
        team2.setGoalsScoredCount(team2.getGoalsScoredCount()+teamBScore);
        team1.setGoalsReceivedCount(team1.getGoalsReceivedCount()+teamBScore);
        team2.setGoalsReceivedCount(team2.getGoalsReceivedCount()+teamAScore);
        team1.setMatchesPlayed(team1.getMatchesPlayed()+1);
        team2.setMatchesPlayed(team2.getMatchesPlayed()+1);

        if (randomMatch.getTeamAScore() > randomMatch.getTeamBScore()){
            team1.setWinCount(team1.getWinCount()+1);
            team2.setDefeatCount(team2.getDefeatCount()+1);
            team1.setPoints(team1.getPoints()+3);

        }
        else if (randomMatch.getTeamAScore() < randomMatch.getTeamBScore()){
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

    }

    public void searchDate(TextField textField){

        Date d = null;

            String sDate = textField.getText();

            try {
                Date simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);

            } catch (ParseException e) {
                System.out.println("You have to enter date in this format(yyyy-mm-dd): ");


            }


        StringBuilder myString = new StringBuilder();

        for (Match match: footballLeagueManager.playedMatch){
            if (match.getDate().equals(sDate)){
                myString.append("Date: "+match.getDate()+"\n"+match.getTeamA().getClubName()+" vs "+match.getTeamB().getClubName()+"\n\n"
                        +match.getTeamA().getClubName()+" score : "+match.getTeamBScore()+"\n"
                        +match.getTeamB().getClubName()+" score : "+match.getTeamBScore()+"\n"
                        +"\n\n");

            }
        }
        sM = myString.toString();
        System.out.println(sM);

    }
}
