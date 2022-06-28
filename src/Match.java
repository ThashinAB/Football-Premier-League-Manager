import java.io.Serializable;

public class Match implements Serializable {
    private FootballClub teamA;
    private FootballClub teamB;
    private int teamAScore;
    private int teamBScore;
    private String date;

    public Match(FootballClub teamA, FootballClub teamB, int teamAScore, int teamBScore, String date) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.teamAScore = teamAScore;
        this.teamBScore = teamBScore;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Match{" +
                "teamA=" + teamA.getClubName() +
                ", teamB=" + teamB.getClubName() +
                ", teamAScore=" + teamAScore +
                ", teamBScore=" + teamBScore +
                ", date=" + date +
                '}';
    }

    public FootballClub getTeamA() {
        return teamA;
    }

    public void setTeamA(FootballClub teamA) {
        this.teamA = teamA;
    }

    public FootballClub getTeamB() {
        return teamB;
    }

    public void setTeamB(FootballClub teamB) {
        this.teamB = teamB;
    }

    public int getTeamAScore() {
        return teamAScore;
    }

    public void setTeamAScore(int teamAScore) {
        this.teamAScore = teamAScore;
    }

    public int getTeamBScore() {
        return teamBScore;
    }

    public void setTeamBScore(int teamBScore) {
        this.teamBScore = teamBScore;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}


