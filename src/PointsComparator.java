import java.util.Comparator;

class PointsComparator implements Comparator<FootballClub> {

    @Override
    public int compare(FootballClub team1,FootballClub team2){
        if (team1.getPoints()> team2.getPoints())
            return -1;
        else
            if (team1.getPoints()< team2.getPoints())// arranging clubs in Descending order according to  points
            return 1;
            else {
                int goalDif = team1.getGoalsScoredCount()-team2.getGoalsReceivedCount();
                int goalDif1 = team2.getGoalsScoredCount()-team2.getGoalsReceivedCount();
                if(goalDif>goalDif1)
                    return -1; //goal difference
                else if (goalDif<goalDif1)
                    return 1;
                else return 0;

            }

    }

}
