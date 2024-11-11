package DSA;
import java.util.*;

public class FantasyLeagueManagementSystem {
    PlayerHashtable players = new PlayerHashtable(15);//Player hashtable
    HashMap<String, Team> teams = new HashMap<>();// Hashmap for teams
    HashMap<String, Match> matches = new HashMap<>(); //hashmap for matches
    CustomPriorityQueue teamsleaderboard = new CustomPriorityQueue(); //Priority Queue to display Leaderboard
    Stack recentActions = new Stack();// stack to access recent action
    
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        FantasyLeagueManagementSystem flms = new FantasyLeagueManagementSystem();
        flms.run();// run method
    }

    void run() {
        while (true) {
            try {
                displayMenu();
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1 :
                    	addPlayer();
                    	break;
                    case 2 :
                    	removePlayer();
                    	break;
                    case 3 :
                    	searchPlayer();
                    	break;
                    case 4 :
                    	createTeam();
                    	break;
                    case 5 :
                    	addPlayerToTeam();
                    	break;
                    case 6:
                    	removePlayerFromTeam();
                    	break;
                    case 7:
                    	displayTeam();
                    	break;
                    case 8 :
                    	scheduleMatch();
                    	break;
                    case 9 :
                    	updateMatchResult();
                    	break;
                    case 10 : 
                    	displayTeamsLeaderboard();
                    	break;
                    case 11 :
                    	displayPlayerLeaderBoard();
                    	break;
                    case 12 :
                    	displayFantasyLeaderboard();
                    	break;
                    case 13 :
                    	undoLastAction();
                    	break;
                    case 14 :
                    	displayMatchHistory();
                    	break;
                    case 15 :
                        System.out.println("Exiting the system. Goodbye!");
                        return;
                    default :
                    	System.out.println("Invalid choice. Try again.");
                    	break;
                }
            } 
            catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            } 
            catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }
    //Menu
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
    //1.Method to add Player in League
    void addPlayer() {
        try {
            System.out.print("Enter player ID: ");
            String id = scanner.nextLine();
            System.out.print("Enter player name: ");
            String name = scanner.nextLine();
            System.out.print("Enter player position: ");
            String position = scanner.nextLine();
            players.put(id, new Player(id, name, position));
            recentActions.push("AddPlayer " + id);//pushing action into stack along with id
            System.out.println("✔ Player added successfully.");
        } 
        catch (Exception e) {
            System.out.println("✘ Error adding player: " + e.getMessage());
        }
    }
    //2.Method to remove Player from League
    void removePlayer() {
        try {
            System.out.print("Enter player ID to remove: ");
            String id = scanner.nextLine();
            if (players.containsKey(id)) {
            	Player temp=players.get(id);
            	players.remove(id);
            	//pushing action into stack along with player details
                recentActions.push("RemovePlayer " + id+" "+temp.name+" "+temp.position+" "+temp.score);
                System.out.println("✔ Player removed successfully.");
            } 
            else {
                System.out.println("✘ Player not found.");
            }
        }
        catch (Exception e) {
            System.out.println("✘ Error removing player: " + e.getMessage());
        }
    }
    //3.Method to search Player in League
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
        } 
        catch (Exception e) {
            System.out.println("✘ Error searching player: " + e.getMessage());
        }
    }
    //4.Method to create Team
    void createTeam() {
        try {
            System.out.print("Enter team ID: ");
            String id = scanner.nextLine();
            System.out.print("Enter team name: ");
            String name = scanner.nextLine();
            teams.put(id, new Team(id, name));
            recentActions.push("CreateTeam " + id);//Pushing action to stack
            System.out.println("✔ Team created successfully.");
        } 
        catch (Exception e) {
            System.out.println("✘ Error creating team: " + e.getMessage());
        }
    }
    //5.Method to add Player to Team
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
                recentActions.push("AddPlayerToTeam " + teamId + " " + playerId);//pushing action to stack
                System.out.println("✔ Player added to team successfully.");
            } 
            else {
                System.out.println("✘ Invalid team or player ID.");
            }
        } 
        catch (Exception e) {
            System.out.println("✘ Error adding player to team: " + e.getMessage());
        }
    }
    //6.Method to remove Player from Team
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
                recentActions.push("RemovePlayerFromTeam " + teamId + " " + playerId);//pushing action to stack
                System.out.println("✔ Player removed from team successfully.");
            } 
            else {
                System.out.println("✘ Invalid team or player ID.");
            }
        } 
        catch (Exception e) {
            System.out.println("✘ Error adding player to team: " + e.getMessage());
        }
    }	
    //7.Method to display team
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
	        } 
	        else {
	            System.out.println("Players:");
	            for (Player player : team.players) {
	                System.out.println(" - ID: " + player.id + ", Name: " + player.name + ", Position: " + player.position + ", Score: " + player.score);
	            }
	        }
	        
	    }
    	

    }
  //8.Method to schedule a match
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
                recentActions.push("ScheduleMatch " + id);// push action to stack
                System.out.println("✔ Match scheduled successfully.");
            }
            else {
                System.out.println("✘ Invalid team IDs.");
            }
        } 
        catch (Exception e) {
            System.out.println("✘ Error scheduling match: " + e.getMessage());
        }
    }
  //9. method to update match result
    void updateMatchResult() {
        try {
            System.out.print("Enter match ID: ");
            String id = scanner.nextLine();
            Match match = matches.get(id);
            if (match != null) {
                Team team1 = teams.get(match.team1Id);
                System.out.print("Enter score for Team " + match.team1Id + ": ");
                match.team1Score = 0;

                
                for (Player player : team1.players) {
                    System.out.print("Score for " + player.name + " (ID: " + player.id + "): ");
                    int playerScore = Integer.parseInt(scanner.nextLine());
                    player.score += playerScore;
                    match.team1Score += playerScore;
                    
                    if (playerScore == 1) {
                        player.addFantasyPoints(2);
                    } else if (playerScore > 2) {
                        player.addFantasyPoints(5);
                    } else if (playerScore > 3) {
                        player.addFantasyPoints(7);
                    }
                }

                Team team2 = teams.get(match.team2Id);
                System.out.print("Enter score for Team " + match.team2Id + ": ");
                match.team2Score=0;

                
                for (Player player : team2.players) {
                    System.out.print("Score for " + player.name + " (ID: " + player.id + "): ");
                    int playerScore = Integer.parseInt(scanner.nextLine());
                    player.score += playerScore;
                    match.team2Score+=playerScore;
                    
                    if (playerScore == 1) {
                        player.addFantasyPoints(2);
                    } else if (playerScore > 2) {
                        player.addFantasyPoints(5);
                    } else if (playerScore > 3) {
                        player.addFantasyPoints(7);
                    }
                }

                // Set match result
                if (match.team1Score > match.team2Score) {
                    match.result = "Team " + match.team1Id + " wins";
                } else if (match.team1Score < match.team2Score) {
                    match.result = "Team " + match.team2Id + " wins";
                } else {
                    match.result = "Draw";
                }

                
                team1.getTotalFantasyPoints();
                team2.getTotalFantasyPoints();

                recentActions.push("UpdateMatchResult " + id);//pushing action to stack
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

    //10.Method to display Team LeaderBoard
    void displayTeamsLeaderboard() {
        try {
            teamsleaderboard.clear();
            teamsleaderboard.enqueue(teams.values());

            System.out.println("\nLeaderboard:");
            while (!teamsleaderboard.isEmpty()) {
                Team team = teamsleaderboard.dequeue();
                System.out.println(team.name + " - Total Score: " + team.getTotalScore());
            }
        } 
        catch (Exception e) {
            System.out.println("✘ Error displaying leaderboard: " + e.getMessage());
        }
    }
    //12.Display Fantasy Leaderboard
    void displayFantasyLeaderboard() {
        List<Team> fantasyLeaderboard = new ArrayList<>(teams.values());

        // Quick sort 
        int n = fantasyLeaderboard.size();
        quicksort(fantasyLeaderboard,0,n-1);
        System.out.println("\nFantasy Leaderboard:");
        for (Team team : fantasyLeaderboard) {
            System.out.println(team.name + " - Fantasy Points: " + team.getTotalFantasyPoints());
        }
    }
    //sorting 
        
    public void quicksort(List<Team> fantasyLeaderboard, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(fantasyLeaderboard, low, high);
            quicksort(fantasyLeaderboard, low, pivotIndex - 1);
            quicksort(fantasyLeaderboard, pivotIndex + 1, high);
        }
    }

    private int partition(List<Team> fantasyLeaderboard, int low, int high) {
        Team pivot = fantasyLeaderboard.get(high);
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (fantasyLeaderboard.get(j).getTotalFantasyPoints() > pivot.getTotalFantasyPoints()) {
                i++;
                Team temp = fantasyLeaderboard.get(i);
                fantasyLeaderboard.set(i, fantasyLeaderboard.get(j));
                fantasyLeaderboard.set(j, temp);
            }
        }
        
        Team temp = fantasyLeaderboard.get(i + 1);
        fantasyLeaderboard.set(i + 1, fantasyLeaderboard.get(high));
        fantasyLeaderboard.set(high, temp);
        
        return i + 1;
    }


  //11.Display Player Leader Board
    void displayPlayerLeaderBoard() {
        // Step 1: Create an empty custom linked list
        PlayerLinkedList playerList = new PlayerLinkedList();

        // Step 2: Traverse the hashtable and add each player to the linked list
        for (int i = 0; i < PlayerHashtable.capacity; i++) {
            PlayerHashtable.Entry entry = PlayerHashtable.table[i];
            while (entry != null) {
                playerList.insert(entry.value);
                entry = entry.next;
            }
        }

        // Step 3: Sort the linked list by player scores in descending order
        playerList.sort();

        // Step 4: Display the sorted leaderboard
        playerList.displayLeaderboard();
        playerList.head=null;
    }
    
    //13. Method Undo Last Action
    void undoLastAction() {
        try {
            if (!recentActions.isEmpty()) {
                String lastAction = recentActions.pop();
                String[] actionParts = lastAction.split(" ");//splitting string into String array
                switch (actionParts[0]) {
                    case "AddPlayer" :
                        players.remove(actionParts[1]);
                        System.out.println("✔ Last action undone: Added player removed.");
                        break;
                    case "RemovePlayer" :
                    	Player temp=new Player(actionParts[1],actionParts[2],actionParts[3]);
                    	temp.score=Integer.parseInt(actionParts[4]);
                    	players.put(temp.id, temp);
                    	System.out.println("✔ Last action undone: Removed player added.");
                    	break;
                    case "CreateTeam" :
                        teams.remove(actionParts[1]);
                        System.out.println("✔ Last action undone: Created team removed.");
                        break;
                        	
                    case "AddPlayerToTeam" :
                        Team team = teams.get(actionParts[1]);
                        Player player = players.get(actionParts[2]);
                        if (team != null && player != null) {
                            team.removePlayer(player);
                            System.out.println("✔ Last action undone: Player removed from team.");
                        }
                        break;
                    case "RemovePlayerFromTeam" :
                    	Team team1 = teams.get(actionParts[1]);
                        Player player1 = players.get(actionParts[2]);
                        if (team1 != null && player1 != null) {
                            team1.addPlayer(player1);
                            System.out.println("✔ Last action undone: Player added to team.");
                        }
                        break;
                    case "ScheduleMatch" :
                        matches.remove(actionParts[1]);
                        System.out.println("✔ Last action undone: Scheduled match removed.");
                        break;
                    case "UpdateMatchResult" :
                        Match match = matches.get(actionParts[1]);
                        if (match != null) {
                            match.team1Score = 0;
                            match.team2Score = 0;
                            match.result = "Pending";
                            System.out.println("✔ Last action undone: Match result reset.");
                        }
                        break;
                    default :
                    	System.out.println("✘ Unknown action. Cannot undo.");
                    	break;
            }
            }
            else {
                System.out.println("✘ No recent actions to undo.");
            }
        } 
        catch (Exception e) {
            System.out.println("✘ Error undoing last action: " + e.getMessage());
        }
    }
    //14.Method Display Match History
     void displayMatchHistory() {
		    if (matches.isEmpty()) {
		        System.out.println("No match history available.");
		        return;
		    }

		    System.out.println("\nMatch History:");
		    for (Match match : matches.values()) {
		        System.out.println("Match ID: " + match.id);
		        System.out.println("Date: " + match.date);
		        System.out.println("Teams: " + match.team1Id + " vs " + match.team2Id);
		        System.out.println("Scores: " + match.team1Id + " - " + match.team1Score + ", " + match.team2Id + " - " + match.team2Score);
		        System.out.println("Result: " + match.result);
		    }
    		

     }

}