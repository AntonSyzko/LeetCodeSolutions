package leet.code.solutions.DS;

public class BloomFilterBits {

    private long[] bitArray;
    private int size;
    private int numHashFunctions;
    private static final int BITS_PER_LONG = 64;

    public BloomFilterBits(int size, int numHashFunctions) {
        this.size = size;
        this.numHashFunctions = numHashFunctions;
        // Calculate number of longs needed to store 'size' bits
        int arraySize = (size + BITS_PER_LONG - 1) / BITS_PER_LONG;
        this.bitArray = new long[arraySize];
    }

    public void add(String element) {

        for (int i = 0; i < numHashFunctions; i++) {
            int hash = hash(element, i);
            int bitIndex = Math.abs(hash) % size;
            setBit(bitIndex);
        }

    }

    public boolean contains(String element) {
        for (int i = 0; i < numHashFunctions; i++) {
            int hash = hash(element, i);
            int bitIndex = Math.abs(hash) % size;
            if (!getBit(bitIndex)) {
                return false;
            }
        }
        return true;
    }

    // Set bit at given index
    private void setBit(int bitIndex) {
        int longIndex = bitIndex / BITS_PER_LONG;
        int bitPosition = bitIndex % BITS_PER_LONG;
        bitArray[longIndex] |= (1L << bitPosition);
    }

    // Get bit at given index
    private boolean getBit(int bitIndex) {
        int longIndex = bitIndex / BITS_PER_LONG;
        int bitPosition = bitIndex % BITS_PER_LONG;
        return (bitArray[longIndex] & (1L << bitPosition)) != 0;
    }

    private int hash(String element, int seed) {
        int hash = element.hashCode();
        return hash ^ (seed * 31);
    }

    public static void main(String[] args) {
        // Create bloom filter: 1000 bits, 3 hash functions
        BloomFilterBits filter = new BloomFilterBits(1000, 3);

        System.out.println("=== Bloom Filter with Bit Manipulation ===");
        System.out.println("Memory usage: " + filter.bitArray.length + " longs (" + (filter.bitArray.length * 8) + " bytes) for " + filter.size + " bits");

        // Test basic functionality
        String[] items = {"apple", "banana", "cherry"};

        System.out.println("\nAdding items:");
        for (String item : items) {
            filter.add(item);
            System.out.println("Added: " + item);
        }

        System.out.println("\nTesting:");
        for (String item : items) {
            System.out.println(item + " exists: " + filter.contains(item));
        }

        System.out.println("grape exists: " + filter.contains("grape") + " (not added)");
        System.out.println("orange exists: " + filter.contains("orange") + " (not added)");
    }
}
