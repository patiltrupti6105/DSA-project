package DSA;

public class Player {
    String id;            // Unique identifier for the player
    String name;          // Player's name
    String position;      // Position of the player on the team
    int score;            // Player's score in the game
    int fantasyPoints;    // Points in a fantasy league context

    // Constructor initializes player with ID, name, and position; sets initial scores to 0
    Player(String id, String name, String position) {
        if (id == null || id.isEmpty()) throw new IllegalArgumentException("Player ID cannot be null or empty.");
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Player name cannot be null or empty.");
        if (position == null || position.isEmpty()) throw new IllegalArgumentException("Player position cannot be null or empty.");
        
        this.id = id;
        this.name = name;
        this.position = position;
        this.score = 0;
        this.fantasyPoints = 0;
    }

    // Adds points to player's fantasyPoints
    void addFantasyPoints(int points) {
        if (points < 0) throw new IllegalArgumentException("Points to add cannot be negative.");
        this.fantasyPoints += points;
    }
}

