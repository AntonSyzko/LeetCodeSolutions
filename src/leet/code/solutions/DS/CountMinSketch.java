package leet.code.solutions.DS;

/*
Time Complexity:

increment(): O(d) where d is depth
estimate(): O(d)

Space Complexity: O(width × depth)
 */
public class CountMinSketch {

    private final int[][] TABLE;
    private final int DEPTH_ROW;  // ROWS - number of hash functions
    private final int WIDTH_COL;  // COLS - number of buckets per hash function

    public CountMinSketch(int width, int depth) {
        this.DEPTH_ROW = depth;
        this.WIDTH_COL = width;
        this.TABLE = new int[depth][width];
    }

    // Add element to sketch (increment count)
    public void increment(String element) {

        increment(element, 1);
    }

    // Add element with custom count
    public void increment(String element, int count) {

        for (int row = 0; row < DEPTH_ROW; row++) {//depth = No of hash functions applied

            int hash = hash(element, row);
            int index = Math.abs(hash) % WIDTH_COL;
            TABLE[row][index] += count;//row -> hash functions call, index - the actual hash % width
        }

    }

    // Estimate frequency of element
    public int estimate(String element) {

        int minCount = Integer.MAX_VALUE;

        for (int row = 0; row < DEPTH_ROW; row++) {//depth = No of hash functions applied
            
            int hash = hash(element, row);
            int index = Math.abs(hash) % WIDTH_COL;

            minCount = Math.min(minCount, TABLE[row][index]);
        }

        return minCount;
    }

    // Generate hash for different rows
    private int hash(String element, int seed) {
        int hash = element.hashCode();
        return hash ^ (seed * 31);
    }

    // Get total elements processed
    public long getTotalCount() {
        long total = 0;
        // Use first row to avoid overcounting
        for (int count : TABLE[0]) {//value is in (depth) times rows, but we should not add em up, first one is an approximation enough for our count
            total += count;
        }

        return total;
    }

    public static void main(String[] args) {
        // Create sketch: 100 buckets, 4 hash functions
        CountMinSketch sketch = new CountMinSketch(100, 4);

        System.out.println("=== Count-Min Sketch Demo ===");

        // Simulate stream of data
        String[] stream = {"apple", "banana", "apple", "cherry", "apple", "banana", "date", "apple", "cherry", "apple"};

        System.out.println("\nProcessing stream:");
        for (String item : stream) {
            sketch.increment(item);
            System.out.println("Added: " + item);
        }

        // Test frequency estimates
        System.out.println("\nFrequency estimates:");
        String[] testItems = {"apple", "banana", "cherry", "date", "elderberry"};

        for (String item : testItems) {
            int estimated = sketch.estimate(item);
            int actual = countActual(stream, item);
            System.out.println(item + " - Estimated: " + estimated + ", Actual: " + actual + (estimated >= actual ? " ✓" : " ✗"));
        }

        System.out.println("\nTotal elements processed: " + sketch.getTotalCount());

        // Demonstrate with larger dataset
        System.out.println("\n=== Heavy Hitters Demo ===");
        CountMinSketch bigSketch = new CountMinSketch(200, 5);

        // Add many items with different frequencies
        for (int i = 0; i < 1000; i++) {
            bigSketch.increment("common");
        }
        for (int i = 0; i < 500; i++) {
            bigSketch.increment("frequent");
        }
        for (int i = 0; i < 50; i++) {
            bigSketch.increment("rare");
        }
        // Add noise
        for (int i = 0; i < 100; i++) {
            bigSketch.increment("noise" + (i % 20));
        }

        System.out.println("Heavy hitters:");
        System.out.println("'common' estimated: " + bigSketch.estimate("common") + " (actual: 1000)");
        System.out.println("'frequent' estimated: " + bigSketch.estimate("frequent") + " (actual: 500)");
        System.out.println("'rare' estimated: " + bigSketch.estimate("rare") + " (actual: 50)");
        System.out.println("'nonexistent' estimated: " + bigSketch.estimate("nonexistent") + " (actual: 0)");
    }

    // Helper method to count actual occurrences
    private static int countActual(String[] array, String target) {
        int count = 0;
        for (String item : array) {
            if (item.equals(target)) {
                count++;
            }
        }
        return count;
    }
}