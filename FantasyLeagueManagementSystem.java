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
            try {
                displayMenu();
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1 -> addPlayer();
                    case 2 -> removePlayer();
                    case 3 -> searchPlayer();
                    case 4 -> createTeam();
                    case 5 -> addPlayerToTeam();
                    case 6 -> scheduleMatch();
                    case 7 -> updateMatchResult();
                    case 8 -> displayLeaderboard();
                    case 9 -> undoLastAction();
                    case 10 -> {
                        System.out.println("Exiting the system. Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
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
        try {
            System.out.print("Enter player ID: ");
            String id = scanner.nextLine();
            System.out.print("Enter player name: ");
            String name = scanner.nextLine();
            System.out.print("Enter player position: ");
            String position = scanner.nextLine();
            players.put(id, new Player(id, name, position));
            recentActions.push("AddPlayer " + id);
            System.out.println("✔ Player added successfully.");
        } catch (Exception e) {
            System.out.println("✘ Error adding player: " + e.getMessage());
        }
    }

    void removePlayer() {
        try {
            System.out.print("Enter player ID to remove: ");
            String id = scanner.nextLine();
            if (players.remove(id) != null) {
                recentActions.push("RemovePlayer " + id);
                System.out.println("✔ Player removed successfully.");
            } else {
                System.out.println("✘ Player not found.");
            }
        } catch (Exception e) {
            System.out.println("✘ Error removing player: " + e.getMessage());
        }
    }

    void searchPlayer() {
        try {
            System.out.print("Enter player ID to search: ");
            String id = scanner.nextLine();
            Player player = players.get(id);
            if (player != null) {
                System.out.println("Player found: " + player.name + ", Position: " + player.position + ", Score: " + player.score);
            } else {
                System.out.println("✘ Player not found.");
            }
        } catch (Exception e) {
            System.out.println("✘ Error searching player: " + e.getMessage());
        }
    }

    void createTeam() {
        try {
            System.out.print("Enter team ID: ");
            String id = scanner.nextLine();
            System.out.print("Enter team name: ");
            String name = scanner.nextLine();
            teams.put(id, new Team(id, name));
            recentActions.push("CreateTeam " + id);
            System.out.println("✔ Team created successfully.");
        } catch (Exception e) {
            System.out.println("✘ Error creating team: " + e.getMessage());
        }
    }

    void addPlayerToTeam() {
        try {
            System.out.print("Enter team ID: ");
            String teamId = scanner.nextLine();
            System.out.print("Enter player ID: ");
            String playerId = scanner.nextLine();
            Team team = teams.get(teamId);
            Player player = players.get(playerId);
            if (team != null && player != null) {
                team.addPlayer(player);
                recentActions.push("AddPlayerToTeam " + teamId + " " + playerId);
                System.out.println("✔ Player added to team successfully.");
            } else {
                System.out.println("✘ Invalid team or player ID.");
            }
        } catch (Exception e) {
            System.out.println("✘ Error adding player to team: " + e.getMessage());
        }
    }

    void scheduleMatch() {
        try {
            System.out.print("Enter match ID: ");
            String id = scanner.nextLine();
            System.out.print("Enter first team ID: ");
            String team1Id = scanner.nextLine();
            System.out.print("Enter second team ID: ");
            String team2Id = scanner.nextLine();
            if (teams.containsKey(team1Id) && teams.containsKey(team2Id)) {
                matches.put(id, new Match(id, team1Id, team2Id));
                recentActions.push("ScheduleMatch " + id);
                System.out.println("✔ Match scheduled successfully.");
            } else {
                System.out.println("✘ Invalid team IDs.");
            }
        } catch (Exception e) {
            System.out.println("✘ Error scheduling match: " + e.getMessage());
        }
    }

    void updateMatchResult() {
        try {
            System.out.print("Enter match ID: ");
            String id = scanner.nextLine();
            Match match = matches.get(id);
            if (match != null) {
                System.out.print("Enter score for Team " + match.team1Id + ": ");
                match.team1Score = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter score for Team " + match.team2Id + ": ");
                match.team2Score = Integer.parseInt(scanner.nextLine());

                if (match.team1Score > match.team2Score) {
                    match.result = "Team " + match.team1Id + " wins";
                } else if (match.team1Score < match.team2Score) {
                    match.result = "Team " + match.team2Id + " wins";
                } else {
                    match.result = "Draw";
                }
                recentActions.push("UpdateMatchResult " + id);
                System.out.println("✔ Match result updated: " + match.result);
            } else {
                System.out.println("✘ Match not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("✘ Invalid score input. Please enter an integer.");
        } catch (Exception e) {
            System.out.println("✘ Error updating match result: " + e.getMessage());
        }
    }

    void displayLeaderboard() {
        try {
            leaderboard.clear();
            leaderboard.addAll(teams.values());

            System.out.println("\nLeaderboard:");
            while (!leaderboard.isEmpty()) {
                Team team = leaderboard.poll();
                System.out.println(team.name + " - Total Score: " + team.getTotalScore());
            }
        } catch (Exception e) {
            System.out.println("✘ Error displaying leaderboard: " + e.getMessage());
        }
    }

    void undoLastAction() {
        try {
            if (!recentActions.isEmpty()) {
                String lastAction = recentActions.pop();
                String[] actionParts = lastAction.split(" ");
                switch (actionParts[0]) {
                    case "AddPlayer" -> {
                        players.remove(actionParts[1]);
                        System.out.println("✔ Last action undone: Added player removed.");
                    }
                    case "RemovePlayer" -> {
                        // Here you would ideally re-add the removed player
                        System.out.println("✘ Undo for removing player not implemented.");
                    }
                    case "CreateTeam" -> {
                        teams.remove(actionParts[1]);
                        System.out.println("✔ Last action undone: Created team removed.");
                    }
                    case "AddPlayerToTeam" -> {
                        Team team = teams.get(actionParts[1]);
                        Player player = players.get(actionParts[2]);
                        if (team != null && player != null) {
                            team.removePlayer(player);
                            System.out.println("✔ Last action undone: Player removed from team.");
                        }
                    }
                    case "ScheduleMatch" -> {
                        matches.remove(actionParts[1]);
                        System.out.println("✔ Last action undone: Scheduled match removed.");
                    }
                    case "UpdateMatchResult" -> {
                        Match match = matches.get(actionParts[1]);
                        if (match != null) {
                            match.team1Score = 0;
                            match.team2Score = 0;
                            match.result = "Pending";
                            System.out.println("✔ Last action undone: Match result reset.");
                        }
                    }
                    default -> System.out.println("✘ Unknown action. Cannot undo.");
                }
            } else {
                System.out.println("✘ No recent actions to undo.");
            }
        } catch (Exception e) {
            System.out.println("✘ Error undoing last action: " + e.getMessage());
        }
    }
}
