package DSA;
import java.time.LocalDate;
public class Match {
    String id;
    String team1Id;
    String team2Id;
    String date;
    int team1Score;
    int team2Score;
    String result;// "Team1 wins", "Team2 wins", "Draw"

    Match(String id, String team1Id, String team2Id) {
        this.id = id;
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.team1Score = 0;
        this.team2Score = 0;
        date=LocalDate.now().toString();
        this.result = "Pending";
    }
}