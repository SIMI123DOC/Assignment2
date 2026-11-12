package ECommerceCatalogue.SKULookupComparison;
import java.util.HashMap;

public class HashTable {

    // Node class for chaining
    static class Entry {
        String key;
        int value;
        Entry next;

        Entry(String key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    Entry[] table;
    int size;

    // Constructor
    HashTable(int size) {
        this.size = size;
        table = new Entry[size];
    }

    // Calculate hash index
    int hash(String key) {
        return Math.floorMod(key.hashCode(), size);
    }

    // Insert SKU
    void put(String key, int value) {

        int index = hash(key);

        Entry current = table[index];

        // Check if key already exists
        while (current != null) {

            if (current.key.equals(key)) {
                current.value = value;
                return;
            }

            current = current.next;
        }

        // Insert new entry at beginning
        Entry newEntry = new Entry(key, value);

        newEntry.next = table[index];
        table[index] = newEntry;
    }

    // Search SKU
    Integer get(String key) {

        int index = hash(key);

        Entry current = table[index];

        while (current != null) {

            if (current.key.equals(key)) {
                return current.value;
            }

            current = current.next;
        }

        return null;
    }

    // Display bucket to demonstrate collision
    void displayBucket(String key) {

        int index = hash(key);

        Entry current = table[index];

        System.out.print("Bucket " + index + ": ");

        while (current != null) {

            System.out.print(current.key + " -> ");

            current = current.next;
        }

        System.out.println("null");
    }

    public static void main(String[] args) {

        // Custom Hash Table
        System.out.println("CUSTOM HASH TABLE");

        HashTable custom = new HashTable(5);

        custom.put("SKU-101", 100);
        custom.put("SKU-106", 200);

        System.out.println("SKU-101 value: "
                + custom.get("SKU-101"));

        System.out.println("SKU-106 value: "
                + custom.get("SKU-106"));

        // Collision demonstration
        System.out.println("\nCollision Demonstration:");

        System.out.println("SKU-101 Index: "
                + custom.hash("SKU-101"));

        System.out.println("SKU-106 Index: "
                + custom.hash("SKU-106"));

        custom.displayBucket("SKU-101");


        // Compare lookup time for 100000 SKUs
        System.out.println("\n100000 SKU LOOKUP COMPARISON");

        int n = 100000;

        HashTable customLarge = new HashTable(200003);

        HashMap<String, Integer> builtIn = new HashMap<>();

        // Insert 100000 SKUs
        for (int i = 0; i < n; i++) {

            String sku = "SKU-" + i;

            customLarge.put(sku, i);

            builtIn.put(sku, i);
        }

        // Warm-up
        for (int i = 0; i < 10000; i++) {

            customLarge.get("SKU-" + i);

            builtIn.get("SKU-" + i);
        }

        // Custom Hash Table lookup time
        long start = System.nanoTime();

        for (int i = 0; i < n; i++) {

            customLarge.get("SKU-" + i);
        }

        long customTime = System.nanoTime() - start;


        // Built-in HashMap lookup time
        start = System.nanoTime();

        for (int i = 0; i < n; i++) {

            builtIn.get("SKU-" + i);
        }

        long mapTime = System.nanoTime() - start;


        // Display comparison
        System.out.println("Custom Hash Table Time: "
                + customTime / 1000000.0 + " ms");

        System.out.println("Built-in HashMap Time: "
                + mapTime / 1000000.0 + " ms");
    }
}
