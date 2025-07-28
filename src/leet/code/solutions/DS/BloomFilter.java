package leet.code.solutions.DS;

/*
Time Complexity:

add(): O(k) where k is number of hash functions
contains(): O(k)

Space Complexity: O(m) where m is the bit array size
 */

public class BloomFilter {

    private final boolean[] bitArray;
    private final int bitArraySize;
    private final int numHashFunctions;

    public BloomFilter(int bitArraySize, int numHashFunctions) {
        this.bitArraySize = bitArraySize;
        this.numHashFunctions = numHashFunctions;
        this.bitArray = new boolean[bitArraySize];
    }

    // Add element to the filter
    public void add(String element) {

        for (int i = 0; i < numHashFunctions; i++) {//hashing times hash function
            int hash = hash(element, i);
            int index = Math.abs(hash) % bitArraySize;
            bitArray[index] = true;
        }

    }

    // Check if element might be in the set
    public boolean contains(String element) {

        for (int i = 0; i < numHashFunctions; i++) {//time hash functions
            int hash = hash(element, i);
            int index = Math.abs(hash) % bitArraySize;

            if (!bitArray[index]) {
                return false; // Definitely not in set, fail fast
            }
        }

        return true; // Might be in set
    }

    // Generate hash using seed to simulate multiple hash functions
    private int hash(String element, int seed) {
        int hash = element.hashCode();
        // Simple way to create different hash functions
        return hash ^ (seed * 31);
    }



    public static void main(String[] args) {
        // Create bloom filter: 1000 bits, 3 hash functions
        BloomFilter filter = new BloomFilter(1000, 3);

        System.out.println("=== Bloom Filter Demo ===");

        // Add some elements
        String[] addedItems = {"apple", "banana", "cherry", "date", "elderberry"};
        System.out.println("\nAdding items:");
        for (String item : addedItems) {
            filter.add(item);
        }

        // Test elements that were added (should all return true)
        System.out.println("\nTesting added items:");
        for (String item : addedItems) {
            boolean exists = filter.contains(item);
            System.out.println(item + " exists: " + exists + " (expected: true)");
        }

        // Test elements that were NOT added
        String[] notAddedItems = {"grape", "kiwi", "mango", "orange", "pear"};
        System.out.println("\nTesting items NOT added:");
        for (String item : notAddedItems) {
            boolean exists = filter.contains(item);
            System.out.println(item + " exists: " + exists + " (expected: false, but false positives possible)");
        }

        // Demonstrate false positive scenario
        System.out.println("\nFalse Positive Demo:");
        System.out.println("Adding many items to increase false positive chance...");

        // Add more items to increase collision probability
        for (int i = 0; i < 200; i++) {
            filter.add("item" + i);
        }

        // Test some random strings
        String[] testItems = {"xyz123", "hello", "world", "test", "random"};
        int falsePositives = 0;
        for (String item : testItems) {
            if (filter.contains(item)) {
                System.out.println("FALSE POSITIVE: " + item + " reported as existing");
                falsePositives++;
            } else {
                System.out.println(item + " correctly reported as not existing");
            }
        }

        System.out.println("\nSummary: " + falsePositives + " false positives out of " + testItems.length + " tests");
        System.out.println("Note: Results may vary due to hash function randomness");
    }
}