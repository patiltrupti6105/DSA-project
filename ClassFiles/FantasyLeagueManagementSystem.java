package DSA;
import java.util.*;

public class FantasyLeagueManagementSystem {
    // Data structures for storing players, teams, matches, and recent actions
    PlayerHashtable players = new PlayerHashtable(15); // HashTable for players
    HashMap<String, Team> teams = new HashMap<>(); // HashMap for teams
    HashMap<String, Match> matches = new HashMap<>(); // HashMap for matches
    CustomPriorityQueue teamsleaderboard = new CustomPriorityQueue(); // Priority queue for leaderboard
    Stack<String> recentActions = new Stack<>(); // Stack to track recent actions for undo feature

    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        FantasyLeagueManagementSystem flms = new FantasyLeagueManagementSystem();
        flms.run(); // Start the system
    }

    // Main run loop for the Fantasy League Management System
    void run() {
        while (true) {
            try {
                displayMenu();
                int choice = Integer.parseInt(scanner.nextLine().trim()); // Parse user input
                switch (choice) {
                    case 1: addPlayer(); break;
                    case 2: removePlayer(); break;
                    case 3: searchPlayer(); break;
                    case 4: createTeam(); break;
                    case 5: addPlayerToTeam(); break;
                    case 6: removePlayerFromTeam(); break;
                    case 7: displayTeam(); break;
                    case 8: scheduleMatch(); break;
                    case 9: updateMatchResult(); break;
                    case 10: displayTeamsLeaderboard(); break;
                    case 11: displayPlayerLeaderBoard(); break;
                    case 12: displayFantasyLeaderboard(); break;
                    case 13: undoLastAction(); break;
                    case 14: displayMatchHistory(); break;
                    case 15:
                        System.out.println("Exiting the system. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    // Display menu options
    void displayMenu() {
        System.out.println("\nFantasy League Management System");
        System.out.println("1. Add Player");
        System.out.println("2. Remove Player");
        System.out.println("3. Search Player");
        System.out.println("4. Create Team");
        System.out.println("5. Add Player to Team");
        System.out.println("6. Remove Player from Team");
        System.out.println("7. Display Teams");
        System.out.println("8. Schedule Match");
        System.out.println("9. Update Match Result");
        System.out.println("10. Display Teams Leaderboard");
        System.out.println("11. Display Player Leaderboard");
        System.out.println("12. Display Fantasy Board");
        System.out.println("13. Undo Last Action");
        System.out.println("14. Display Match History");
        System.out.println("15. Exit");
        System.out.print("Enter your choice: ");
    }

    // Adds a player to the system
    void addPlayer() {
        try {
            System.out.print("Enter player ID: ");
            String id = scanner.nextLine();
            System.out.print("Enter player name: ");
            String name = scanner.nextLine();
            System.out.print("Enter player position: ");
            String position = scanner.nextLine();
            players.put(id, new Player(id, name, position));
            recentActions.push("AddPlayer " + id); // Record action for undo
            System.out.println("✔ Player added successfully.");
        } catch (Exception e) {
            System.out.println("✘ Error adding player: " + e.getMessage());
        }
    }

    // Removes a player from the system
    void removePlayer() {
        try {
            System.out.print("Enter player ID to remove: ");
            String id = scanner.nextLine();
            if (players.containsKey(id)) {
                Player temp = players.get(id);
                players.remove(id);
                recentActions.push("RemovePlayer " + id + " " + temp.name + " " + temp.position + " " + temp.score); // Record action for undo
                System.out.println("✔ Player removed successfully.");
            } else {
                System.out.println("✘ Player not found.");
            }
        } catch (Exception e) {
            System.out.println("✘ Error removing player: " + e.getMessage());
        }
    }

    // Searches for a player by ID
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

    // Creates a team in the system
    void createTeam() {
        try {
            System.out.print("Enter team ID: ");
            String id = scanner.nextLine();
            System.out.print("Enter team name: ");
            String name = scanner.nextLine();
            teams.put(id, new Team(id, name));
            recentActions.push("CreateTeam " + id); // Record action for undo
            System.out.println("✔ Team created successfully.");
        } catch (Exception e) {
            System.out.println("✘ Error creating team: " + e.getMessage());
        }
    }

    // Adds a player to a team
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
                recentActions.push("AddPlayerToTeam " + teamId + " " + playerId); // Record action for undo
                System.out.println("✔ Player added to team successfully.");
            } else {
                System.out.println("✘ Invalid team or player ID.");
            }
        } catch (Exception e) {
            System.out.println("✘ Error adding player to team: " + e.getMessage());
        }
    }

    // Removes a player from a team
    void removePlayerFromTeam() {
        try {
            System.out.print("Enter team ID: ");
            String teamId = scanner.nextLine();
            System.out.print("Enter player ID: ");
            String playerId = scanner.nextLine();
            Team team = teams.get(teamId);
            Player player = players.get(playerId);
            if (team != null && player != null) {
                team.removePlayer(player);
                recentActions.push("RemovePlayerFromTeam " + teamId + " " + playerId); // Record action for undo
                System.out.println("✔ Player removed from team successfully.");
            } else {
                System.out.println("✘ Invalid team or player ID.");
            }
        } catch (Exception e) {
            System.out.println("✘ Error removing player from team: " + e.getMessage());
        }
    }

    // Displays details of all teams
    void displayTeam() {
        if (teams.isEmpty()) {
            System.out.println("No teams available.");
            return;
        }
        System.out.println("\nTeams:");
        for (Team team : teams.values()) {
            System.out.println("Team ID: " + team.id);
            System.out.println("Name: " + team.name);
            System.out.println("Total Score: " + team.getTotalScore());
            if (team.players.isEmpty()) {
                System.out.println("Players: No players in this team.");
            } else {
                System.out.println("Players:");
                for (Player player : team.players) {
                    System.out.println(" - ID: " + player.id + ", Name: " + player.name + ", Position: " + player.position + ", Score: " + player.score);
                }
            }
        }
    }

    // Schedules a match between two teams
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
                recentActions.push("ScheduleMatch " + id); // Record action for undo
                System.out.println("✔ Match scheduled successfully.");
            } else {
                System.out.println("✘ Invalid team IDs.");
            }
        } catch (Exception e) {
            System.out.println("✘ Error scheduling match: " + e.getMessage());
        }
    }

    // Updates the result of a match
    void updateMatchResult() {
        try {
            System.out.print("Enter match ID: ");
            String id = scanner.nextLine();
            Match match = matches.get(id);
            if (match != null) {
                // Get and update scores for each player on each team, and update the match result
                updateTeamScore(match, match.team1Id);
                updateTeamScore(match, match.team2Id);
                System.out.print("Enter winning team ID: ");
                String winnerId = scanner.nextLine();
                match.setWinner(winnerId); // Set the winner team ID
                recentActions.push("UpdateMatchResult " + id); // Record action for undo
                System.out.println("✔ Match result updated successfully.");
            } else {
                System.out.println("✘ Match not found.");
            }
        } catch (Exception e) {
            System.out.println("✘ Error updating match result: " + e.getMessage());
        }
    }

    private void updateTeamScore(Match match, String teamId) {
        try {
            System.out.println("Enter scores for team ID " + teamId);
            for (Player player : teams.get(teamId).players) {
                System.out.print("Score for player " + player.name + " (ID: " + player.id + "): ");
                player.score += Integer.parseInt(scanner.nextLine().trim());
            }
        } catch (NumberFormatException e) {
            System.out.println("✘ Invalid score format. Please enter numeric values.");
        } catch (Exception e) {
            System.out.println("✘ Error updating team score: " + e.getMessage());
        }
    }

    // Displays the leaderboard of all teams
    void displayTeamsLeaderboard() {
        if (teamsleaderboard.isEmpty()) {
            System.out.println("No teams on the leaderboard.");
            return;
        }
        System.out.println("\nTeams Leaderboard:");
        teamsleaderboard.display();
    }

    // Displays the leaderboard of all players
    void displayPlayerLeaderBoard() {
        players.display();
    }

    // Displays the fantasy leaderboard
    void displayFantasyLeaderboard() {
        // Placeholder for Fantasy leaderboard functionality
        System.out.println("Fantasy Leaderboard - coming soon!");
    }

    // Undoes the last action
    void undoLastAction() {
        if (recentActions.isEmpty()) {
            System.out.println("No actions to undo.");
            return;
        }
        String[] action = recentActions.pop().split(" ");
        switch (action[0]) {
            case "AddPlayer":
                players.remove(action[1]);
                System.out.println("✔ Undo add player successful.");
                break;
            case "RemovePlayer":
                players.put(action[1], new Player(action[1], action[2], action[3]));
                players.get(action[1]).score = Integer.parseInt(action[4]);
                System.out.println("✔ Undo remove player successful.");
                break;
            case "CreateTeam":
                teams.remove(action[1]);
                System.out.println("✔ Undo create team successful.");
                break;
            case "AddPlayerToTeam":
                teams.get(action[1]).removePlayer(players.get(action[2]));
                System.out.println("✔ Undo add player to team successful.");
                break;
            case "RemovePlayerFromTeam":
                teams.get(action[1]).addPlayer(players.get(action[2]));
                System.out.println("✔ Undo remove player from team successful.");
                break;
            case "ScheduleMatch":
                matches.remove(action[1]);
                System.out.println("✔ Undo schedule match successful.");
                break;
            case "UpdateMatchResult":
                matches.get(action[1]).clearWinner();
                System.out.println("✔ Undo update match result successful.");
                break;
            default:
                System.out.println("✘ Invalid action to undo.");
                break;
        }
    }

    // Displays match history
    void displayMatchHistory() {
        if (matches.isEmpty()) {
            System.out.println("No matches available.");
            return;
        }
        System.out.println("\nMatch History:");
        for (Match match : matches.values()) {
            System.out.println("Match ID: " + match.id + ", Teams: " + match.team1Id + " vs " + match.team2Id + ", Winner: " + match.winner);
        }
    }
}
