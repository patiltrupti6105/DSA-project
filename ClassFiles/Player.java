package DSA;

public class Player{
    String id;
    String name;
    String position;
    int score;
    int fantasyPoints; 

    Player(String id, String name, String position) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.score = 0;
        this.fantasyPoints = 0;
    }
    void addFantasyPoints(int points) {
        this.fantasyPoints += points;  // Update fantasy points
    }
}

