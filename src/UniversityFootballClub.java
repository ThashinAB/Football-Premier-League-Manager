import java.util.Objects;

public class UniversityFootballClub extends FootballClub {

    private int totalChampionships;


    public UniversityFootballClub(String clubName, String clubLocation) {
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
        UniversityFootballClub that = (UniversityFootballClub) o;
        return totalChampionships == that.totalChampionships;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), totalChampionships);
    }

    @Override
    public String toString() {
        return "UniversityFootballClub{" +
                "totalChampionships=" + totalChampionships +
                '}';
    }
}