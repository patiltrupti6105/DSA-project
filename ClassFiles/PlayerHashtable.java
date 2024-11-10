package DSA;

public class PlayerHashtable {
    // Entry class to store key-value pairs in the hashtable
    class Entry {
        String key;            // Key for the entry
        Player value;          // Value associated with the key
        Entry next;            // Next entry in the chain (for collision resolution)

        // Constructor for Entry
        Entry(String key, Player value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    static Entry[] table;      // Array of Entry chains (hashtable storage)
    static int capacity;       // Size of the table

    // Constructor for hashtable with specified capacity
    PlayerHashtable(int capacity1) {
        if (capacity1 <= 0) throw new IllegalArgumentException("Capacity must be positive.");
        capacity = capacity1;
        table = new Entry[capacity1];
    }

    // Hash function to compute index based on key
    private int hash(String key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null.");
        return Math.abs(key.hashCode() % capacity);
    }

    // Insert or update a key-value pair in the hashtable
    void put(String key, Player value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key and value cannot be null.");
        }
        
        int index = hash(key);  // Compute index for the key
        Entry current = table[index];

        // Traverse the chain to check if the key already exists
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;  // Update value if key already exists
                return;
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
        if (key == null) throw new IllegalArgumentException("Key cannot be null.");
        
        int index = hash(key);  // Compute index for the key
        Entry current = table[index];

        // Traverse the chain to find the key
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;  // Return the value if found
            }
            current = current.next;
        }
        return null; // Return null if key is not found
    }

    // Remove a key-value pair by key from the hashtable
    void remove(String key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null.");
        
        int index = hash(key);  // Compute index for the key
        Entry current = table[index];
        Entry previous = null;

        // Traverse the chain to find the key to remove
        while (current != null) {
            if (current.key.equals(key)) {
                if (previous == null) {
                    table[index] = current.next;  // Remove head of the chain
                } else {
                    previous.next = current.next;  // Bypass the removed entry
                }
                return;
            }
            previous = current;
            current = current.next;
        }
    }

    // Check if a key exists in the hashtable
    boolean containsKey(String key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null.");
        return get(key) != null;
    }
}
