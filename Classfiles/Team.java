package DSA;

import java.util.ArrayList;
import java.util.List;

public class Team {
    String id;
    String name;
    List<Player> players;//list of player in team
    int fantasyPoints;

    Team(String id, String name) {
        this.id = id;
        this.name = name;
        this.players = new ArrayList<>();
        this.fantasyPoints = 0;
    }
    
    void addPlayer(Player player) {
        players.add(player);
    }

    void removePlayer(Player player) {
        players.remove(player);
    }

    int getTotalScore() {
        int totalScore = 0;
        for (Player player : players) {
            totalScore += player.score;// total score of team= sum of scores of players
        }
        return totalScore;
    }
    int getTotalFantasyPoints() {
        int totalFantasyPoints = 0;
        for (Player player : players) {
            totalFantasyPoints += player.fantasyPoints;// total fantasy point of team= sum of scores of players
        }
        this.fantasyPoints = totalFantasyPoints;
        return totalFantasyPoints;
    }
}
