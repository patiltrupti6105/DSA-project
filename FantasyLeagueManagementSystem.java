import java.util.*;

class Player {
    String id;
    String name;
    String position;
    int score;

    Player(String id, String name, String position) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.score = 0;
    }
}

class Team {
    String id;
    String name;
    List<Player> players;

    Team(String id, String name) {
        this.id = id;
        this.name = name;
        this.players = new ArrayList<>();
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
            totalScore += player.score;
        }
        return totalScore;
    }
}

class Match {
    String id;
    String team1Id;
    String team2Id;
    int team1Score;
    int team2Score;
    String result; // "Team1 wins", "Team2 wins", "Draw"

    Match(String id, String team1Id, String team2Id) {
        this.id = id;
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.team1Score = 0;
        this.team2Score = 0;
        this.result = "Pending";
    }
}

public class FantasyLeagueManagementSystem {
    HashMap<String, Player> players = new HashMap<>();
    HashMap<String, Team> teams = new HashMap<>();
    HashMap<String, Match> matches = new HashMap<>();
    PriorityQueue<Team> leaderboard = new PriorityQueue<>((a, b) -> b.getTotalScore() - a.getTotalScore());
    Stack<String> recentActions = new Stack<>();
    
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        FantasyLeagueManagementSystem flms = new FantasyLeagueManagementSystem();
        flms.run();
    }

    void run() {
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1: addPlayer(); break;
                case 2: removePlayer(); break;
                case 3: searchPlayer(); break;
                case 4: createTeam(); break;
                case 5: addPlayerToTeam(); break;
                case 6: scheduleMatch(); break;
                case 7: updateMatchResult(); break;
                case 8: displayLeaderboard(); break;
                case 9: undoLastAction(); break;
                case 10: return;
                default: System.out.println("Invalid choice. Try again.");
            }
        }
    }

    void displayMenu() {
        System.out.println("\nFantasy League Management System");
        System.out.println("1. Add Player");
        System.out.println("2. Remove Player");
        System.out.println("3. Search Player");
        System.out.println("4. Create Team");
        System.out.println("5. Add Player to Team");
        System.out.println("6. Schedule Match");
        System.out.println("7. Update Match Result");
        System.out.println("8. Display Leaderboard");
        System.out.println("9. Undo Last Action");
        System.out.println("10. Exit");
        System.out.print("Enter your choice: ");
    }

    void addPlayer() {
        System.out.print("Enter player ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter player name: ");
        String name = scanner.nextLine();
        System.out.print("Enter player position: ");
        String position = scanner.nextLine();
        players.put(id, new Player(id, name, position));
        recentActions.push("AddPlayer " + id);
        System.out.println("Player added successfully.");
    }

    void removePlayer() {
        System.out.print("Enter player ID to remove: ");
        String id = scanner.nextLine();
        players.remove(id);
        recentActions.push("RemovePlayer " + id);
        System.out.println("Player removed successfully.");
    }

    void searchPlayer() {
        System.out.print("Enter player ID to search: ");
        String id = scanner.nextLine();
        if (players.containsKey(id)) {
            Player player = players.get(id);
            System.out.println("Player found: " + player.name + ", Position: " + player.position + ", Score: " + player.score);
        } else {
            System.out.println("Player not found.");
        }
    }

    void createTeam() {
        System.out.print("Enter team ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter team name: ");
        String name = scanner.nextLine();
        teams.put(id, new Team(id, name));
        recentActions.push("CreateTeam " + id);
        System.out.println("Team created successfully.");
    }

    void addPlayerToTeam() {
        System.out.print("Enter team ID: ");
        String teamId = scanner.nextLine();
        System.out.print("Enter player ID: ");
        String playerId = scanner.nextLine();
        if (teams.containsKey(teamId) && players.containsKey(playerId)) {
            Team team = teams.get(teamId);
            Player player = players.get(playerId);
            team.addPlayer(player);
            recentActions.push("AddPlayerToTeam " + teamId + " " + playerId);
            System.out.println("Player added to team successfully.");
        } else {
            System.out.println("Invalid team or player ID.");
        }
    }

    void scheduleMatch() {
        System.out.print("Enter match ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter first team ID: ");
        String team1Id = scanner.nextLine();
        System.out.print("Enter second team ID: ");
        String team2Id = scanner.nextLine();
        if (teams.containsKey(team1Id) && teams.containsKey(team2Id)) {
            matches.put(id, new Match(id, team1Id, team2Id));
            recentActions.push("ScheduleMatch " + id);
            System.out.println("Match scheduled successfully.");
        } else {
            System.out.println("Invalid team IDs.");
        }
    }

    void updateMatchResult() {
        System.out.print("Enter match ID: ");
        String id = scanner.nextLine();
        if (matches.containsKey(id)) {
            Match match = matches.get(id);
            System.out.print("Enter score for Team " + match.team1Id + ": ");
            match.team1Score = scanner.nextInt();
            System.out.print("Enter score for Team " + match.team2Id + ": ");
            match.team2Score = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (match.team1Score > match.team2Score) {
                match.result = "Team " + match.team1Id + " wins";
            } else if (match.team1Score < match.team2Score) {
                match.result = "Team " + match.team2Id + " wins";
            } else {
                match.result = "Draw";
            }
            recentActions.push("UpdateMatchResult " + id);
            System.out.println("Match result updated: " + match.result);
        } else {
            System.out.println("Match not found.");
        }
    }

    void displayLeaderboard() {
        leaderboard.clear();
        leaderboard.addAll(teams.values());

        System.out.println("\nLeaderboard:");
        while (!leaderboard.isEmpty()) {
            Team team = leaderboard.poll();
            System.out.println(team.name + " - Total Score: " + team.getTotalScore());
        }
    }

    void undoLastAction() {
        if (!recentActions.isEmpty()) {
            String lastAction = recentActions.pop();
            String[] actionParts = lastAction.split(" ");
            switch (actionParts[0]) {
                case "AddPlayer":
                    players.remove(actionParts[1]);
                    System.out.println("Undo: Player removed");
                    break;
                case "RemovePlayer":
                    // Here we would re-add the player if necessary
                    System.out.println("Undo: Player addition cannot be restored in this demo");
                    break;
                case "CreateTeam":
                    teams.remove(actionParts[1]);
                    System.out.println("Undo: Team removed");
                    break;
                case "AddPlayerToTeam":
                    Team team = teams.get(actionParts[1]);
                    Player player = players.get(actionParts[2]);
                    if (team != null && player != null) {
                        team.removePlayer(player);
                        System.out.println("Undo: Player removed from team");
                    }
                    break;
                case "ScheduleMatch":
                    matches.remove(actionParts[1]);
                    System.out.println("Undo: Match unscheduled");
                    break;
                case "UpdateMatchResult":
                    Match match = matches.get(actionParts[1]);
                    if (match != null) {
                        match.result = "Pending";
                        match.team1Score = 0;
                        match.team2Score = 0;
                        System.out.println("Undo: Match result cleared");
                    }
                    break;
                default:
                    System.out.println("Unknown action.");
                    break;
            }
        } else {
            System.out.println("No actions to undo.");
        }
    }
}
