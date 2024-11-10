package DSA;


public class PlayerLinkedList {
	class Node {
	    Player player;
	    Node next;

	    Node(Player player) {
	        this.player = player;
	        this.next = null;
	    }
	}
    Node head;

    // Insert a player at the end of the list
    void insert(Player player) {
        Node newNode = new Node(player);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Sort the linked list in descending order based on player scores
    void sort() {
        if (head == null || head.next == null) return;

        for (Node i = head; i != null; i = i.next) {
            for (Node j = i.next; j != null; j = j.next) {
                if (i.player.score < j.player.score) {
                    // Swap the players
                    Player temp = i.player;
                    i.player = j.player;
                    j.player = temp;
                }
            }
        }
    }

    // Display the leaderboard
    void displayLeaderboard() {
        Node current = head;
        int rank = 1;
        System.out.println("\nPlayer Leaderboard:");
        while (current != null) {
            System.out.println(rank + ". " + current.player.name + " - Score: " + current.player.score);
            rank++;
            current = current.next;
        }
    }
}

