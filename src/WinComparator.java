import java.util.Comparator;

public class WinComparator implements Comparator<FootballClub> {
    @Override
    public int compare(FootballClub o1, FootballClub o2) {
        return o1.getWinCount() - o2.getWinCount();
    }
}
