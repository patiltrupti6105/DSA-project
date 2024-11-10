package DSA;

public class PlayerHashtable {
    // Node class to store key-value pairs in the hashtable
     class Entry {
        String key;
        Player value;
        Entry next; // For separate chaining

        Entry(String key, Player value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

     static Entry[] table; // Array of Entry chains
     static int capacity;   // Size of the table

    // Constructor for hashtable with default capacity
    PlayerHashtable(int capacity1) {
        capacity = capacity1;
        table = new Entry[capacity1];
    }

    // Hash function to determine index based on key
    private int hash(String key) {
        return Math.abs(key.hashCode() % capacity);
    }

    // Insert or update a key-value pair in the hashtable
     void put(String key, Player value) {
        int index = hash(key);  // Determine the index for the key
        Entry current = table[index];

        // If an entry exists, check if we need to update an existing key
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value; // Update existing key's value
                return; // return if updation done
            }
            current = current.next;
        }

        // If key is new, insert at the head of the chain for the index
        Entry newEntry = new Entry(key, value);
        newEntry.next = table[index];
        table[index] = newEntry;
    }

    // Retrieve a value by key from the hashtable
    Player get(String key) {
        int index = hash(key);
        Entry current = table[index];

        // Traverse the chain to find the key
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value; // Return the value if found
            }
            current = current.next;
        }
        return null; // Return null if key not found
    }

    // Remove a key-value pair by key from the hashtable
     void remove(String key) {
        int index = hash(key);
        Entry current = table[index];
        Entry previous = null;

        // Traverse the chain to find the key to remove
        while (current != null) {
            if (current.key.equals(key)) {
                if (previous == null) {
                    table[index] = current.next; // Remove head of chain
                } else {
                    previous.next = current.next; // Bypass the removed entry
                }
                return;
            }
            previous = current;
            current = current.next;
        }
    }

    // Check if a key exists in the hashtable
     boolean containsKey(String key) {
        return get(key) != null;
    }
}