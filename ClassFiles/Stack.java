package DSA;

public class Stack {
    // Node class represents each element in the stack
    class Node {
        String data;  // Data stored in the node
        Node next;    // Reference to the next node in the stack

        // Constructor to create a new node with the specified data
        Node(String data) {
            this.data = data;
            next = null;
        }
    }

    Node head;  // Head of the stack (top element)

    // Constructor to initialize an empty stack
    Stack() {
        head = null;
    }

    // Method to check if the stack is empty
    Boolean isEmpty() {
        return head == null;
    }

    // Method to add (push) an element onto the stack
    void push(String str) {
        if (str == null) throw new IllegalArgumentException("Cannot push null onto the stack.");
        
        Node temp = new Node(str);  // Create a new node with the given data
        temp.next = head;           // Link the new node to the current top node
        head = temp;                // Update head to point to the new top node
    }

    // Method to remove (pop) the top element from the stack
    String pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty. Cannot pop.");
            return null;
        }

        String str = head.data;  // Retrieve the data of the top node
        head = head.next;        // Move head to the next node in the stack
        return str;              // Return the popped data
    }
}
