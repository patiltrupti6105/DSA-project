package DSA;

import java.util.ArrayList;
import java.util.List;

public class Team {
    String id;                  // Unique identifier for the team
    String name;                // Name of the team
    List<Player> players;       // List to store the players in the team
    int fantasyPoints;          // Total fantasy points for the team

    // Constructor to initialize a new team with ID and name
    Team(String id, String name) {
        if (id == null || id.isEmpty() || name == null || name.isEmpty()) {
            throw new IllegalArgumentException("ID and name cannot be null or empty.");
        }
        this.id = id;
        this.name = name;
        this.players = new ArrayList<>();
        this.fantasyPoints = 0;
    }

    // Method to add a player to the team
    void addPlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Cannot add a null player to the team.");
        }
        players.add(player);
    }

    // Method to remove a player from the team
    void removePlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Cannot remove a null player from the team.");
        }
        players.remove(player);
    }

    // Method to calculate and return the total score of all players in the team
    int getTotalScore() {
        int totalScore = 0;
        for (Player player : players) {
            totalScore += player.score;
        }
        return totalScore;
    }

    // Method to calculate and return the total fantasy points for the team
    int getTotalFantasyPoints() {
        int totalFantasyPoints = 0;
        for (Player player : players) {
            totalFantasyPoints += player.fantasyPoints;
        }
        this.fantasyPoints = totalFantasyPoints;  // Update team's fantasy points
        return totalFantasyPoints;
    }
}
