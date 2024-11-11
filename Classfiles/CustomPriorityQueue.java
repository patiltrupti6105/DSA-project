package DSA;
import java.util.Collection;
class CustomPriorityQueue {
	class ListNode {
	    Team team;
	    ListNode next;

	    ListNode(Team team) {
	        this.team = team;
	        this.next = null;
	    }
	}

    private ListNode head;

     CustomPriorityQueue() {
        this.head = null;
    }
     //clearing queue
     void clear() {
    	 head=null;
     }

    // Insert a team at the beginning of the list
    public void enqueue(Collection<Team> teams) {
    	for(Team i: teams) {
	        ListNode newNode = new ListNode(i);
	        newNode.next = head;
	        head = newNode;
    	}
    }
    
    // Remove and return the team with the highest score
    public Team dequeue() {
        if (isEmpty()) {
            return null;
        }

        ListNode maxNode = head;
        ListNode maxPrev = null;
        ListNode current = head;
        ListNode prev = null;

        // Traverse to find the max node and keep track of its previous node
        while (current != null) {
            if (current.team.getTotalScore() > maxNode.team.getTotalScore()) {
                maxNode = current;
                maxPrev = prev;
            }
            prev = current;
            current = current.next;
        }

        // Remove maxNode from the list
        if (maxPrev == null) { // maxNode is head
            head = head.next;
        }
        else {
            maxPrev.next = maxNode.next;
        }
        return maxNode.team;
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return head == null;
    }
}
