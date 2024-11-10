package DSA;

public class PlayerLinkedList {
    // Node class represents each element in the linked list
    class Node {
        Player player;  // Player object stored in the node
        Node next;      // Reference to the next node

        // Constructor to create a new node with a player
        Node(Player player) {
            this.player = player;
            this.next = null;
        }
    }

    Node head;  // Head of the linked list

    // Insert a player at the end of the linked list
    void insert(Player player) {
        if (player == null) throw new IllegalArgumentException("Player cannot be null.");
        
        Node newNode = new Node(player);  // Create a new node with the player
        if (head == null) {
            head = newNode;  // If list is empty, new node becomes the head
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;  // Traverse to the end of the list
            }
            current.next = newNode;  // Insert new node at the end
        }
    }

    // Sort the linked list in descending order based on player scores
    void sort() {
        if (head == null || head.next == null) return;  // No need to sort if list is empty or has one node

        // Bubble sort based on player scores in descending order
        for (Node i = head; i != null; i = i.next) {
            for (Node j = i.next; j != null; j = j.next) {
                if (i.player.score < j.player.score) {
                    // Swap the players between nodes
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
        
        // Traverse and display each player's rank, name, and score
        while (current != null) {
            System.out.println(rank + ". " + current.player.name + " - Score: " + current.player.score);
            rank++;
            current = current.next;
        }
    }
}

