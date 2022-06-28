import java.util.Comparator;

public class ScoreComparator implements Comparator<FootballClub> {


    @Override
    public int compare(FootballClub o1, FootballClub o2) {
        return o1.getGoalsScoredCount() - o2.getGoalsScoredCount();
    }
}
