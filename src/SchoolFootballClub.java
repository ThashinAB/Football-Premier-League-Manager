import java.util.Objects;

public class SchoolFootballClub extends FootballClub{

    private int totalChampionships;

    public SchoolFootballClub(String clubName, String clubLocation) {
        super(clubName, clubLocation);
    }

    public int getTotalChampionships() {
        return totalChampionships;
    }

    public void setTotalChampionships(int totalChampionships) {
        this.totalChampionships = totalChampionships;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SchoolFootballClub that = (SchoolFootballClub) o;
        return totalChampionships == that.totalChampionships;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), totalChampionships);
    }

    @Override
    public String toString() {
        return "SchoolFootballClub{" +
                "totalChampionships=" + totalChampionships +
                '}';
    }
}