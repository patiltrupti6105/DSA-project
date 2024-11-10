package DSA;
import java.time.LocalDate;

public class Match {
    String id;         // Match identifier
    String team1Id;    // First team identifier
    String team2Id;    // Second team identifier
    String date;       // Match date
    int team1Score;    // Score for team 1
    int team2Score;    // Score for team 2
    String result;     // Result of the match

    // Constructor initializes match with IDs, sets date, and default values for scores and result
    Match(String id, String team1Id, String team2Id) {
        if (id == null || id.isEmpty()) throw new IllegalArgumentException("Match ID cannot be null or empty.");
        if (team1Id == null || team1Id.isEmpty() || team2Id == null || team2Id.isEmpty()) {
            throw new IllegalArgumentException("Team IDs cannot be null or empty.");
        }
        if (team1Id.equals(team2Id)) throw new IllegalArgumentException("Teams must be different.");

        this.id = id;
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.team1Score = 0;
        this.team2Score = 0;
        this.date = LocalDate.now().toString();
        this.result = "Pending";
    }

    // Updates scores and sets result based on score comparison
    public void updateScores(int team1Score, int team2Score) {
        if (team1Score < 0 || team2Score < 0) throw new IllegalArgumentException("Scores cannot be negative.");
        this.team1Score = team1Score;
        this.team2Score = team2Score;
        
        if (team1Score > team2Score) {
            this.result = "Team1 wins";
        } else if (team1Score < team2Score) {
            this.result = "Team2 wins";
        } else {
            this.result = "Draw";
        }
    }

    // Resets result to "Pending" without changing scores
    public void clearResult() {
        this.result = "Pending";
    }
}
