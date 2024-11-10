package DSA;
import java.util.Collection;

class CustomPriorityQueue {

    // Inner class to represent a node in the linked list
    class ListNode {
        Team team;
        ListNode next;

        // Constructor to initialize the node with a team
        ListNode(Team team) {
            this.team = team;
            this.next = null;
        }
    }

    private ListNode head;

    // Constructor to initialize an empty priority queue
    CustomPriorityQueue() {
        this.head = null;
    }

    /**
     * Clears the queue by setting the head to null.
     * Effectively removes all elements from the queue.
     */
    void clear() {
        head = null;
    }

    /**
     * Inserts a collection of teams into the queue.
     * The teams are added to the front of the list, not in sorted order.
     * 
     * @param teams A collection of teams to be added to the queue.
     */
    public void enqueue(Collection<Team> teams) {
        if (teams == null || teams.isEmpty()) {
            System.out.println("No teams to enqueue.");
            return;
        }

        for (Team team : teams) {
            ListNode newNode = new ListNode(team);
            newNode.next = head; // Insert at the beginning
            head = newNode;
        }
    }

    /**
     * Removes and returns the team with the highest score from the queue.
     * If multiple teams have the same highest score, the first encountered is returned.
     * 
     * @return The team with the highest score, or null if the queue is empty.
     */
    public Team dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty. Cannot dequeue.");
            return null;
        }

        ListNode maxNode = head;     // Node with the maximum score
        ListNode maxPrev = null;     // Previous node of maxNode
        ListNode current = head;
        ListNode prev = null;

        // Traverse the list to find the node with the highest score
        while (current != null) {
            if (current.team.getTotalScore() > maxNode.team.getTotalScore()) {
                maxNode = current;
                maxPrev = prev;
            }
            prev = current;
            current = current.next;
        }

        // Remove the maxNode from the list
        if (maxPrev == null) {       // If maxNode is the head
            head = head.next;
        } else {
            maxPrev.next = maxNode.next;
        }

        return maxNode.team;         // Return the team with the highest score
    }

    /**
     * Checks if the queue is empty.
     * 
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return head == null;
    }
}
