package leet.code.solutions.DS;

/*
Time complexity: O(1) for add and estimate operations
Space complexity: O(2^precision) for bucket storage
 */
import java.util.Arrays;

import java.util.Arrays;

public class HyperLogLog {
    private final int[] buckets;
    private final int bucketCount;
    private final int bucketBits;

    public HyperLogLog(int precision) {
        this.bucketBits = precision;
        this.bucketCount = 1 << precision; // 2^precision buckets
        this.buckets = new int[bucketCount];
    }

    public void add(Object item) {
        int hash = item.hashCode();

        // Use lower bits to determine bucket
        int bucket = hash & (bucketCount - 1);

        // Count leading zeros in the remaining bits after bucket selection
        // Shift left to use only the upper (32 - bucketBits) bits
        int remainingBits = hash >>> bucketBits;
        int leadingZeros = Integer.numberOfLeadingZeros(remainingBits) - bucketBits + 1;

        // Keep maximum leading zeros for this bucket
        buckets[bucket] = Math.max(buckets[bucket], leadingZeros);
    }

    public long estimate() {
        double rawEstimate = 0.0;
        int zeroCount = 0;

        // Calculate harmonic mean of 2^(max leading zeros)
        for (int bucket : buckets) {
            rawEstimate += Math.pow(2, -bucket);
            if (bucket == 0) zeroCount++;
        }

        double alpha = getAlpha();
        double estimate = alpha * bucketCount * bucketCount / rawEstimate;

        // Small range correction
        if (estimate <= 2.5 * bucketCount) {
            if (zeroCount != 0) {
                estimate = bucketCount * Math.log(bucketCount / (double) zeroCount);
            }
        }
        // Large range correction would go here for very large estimates

        return (long) estimate;
    }

    private double getAlpha() {
        // Alpha constant for bias correction
        switch (bucketCount) {
            case 16: return 0.673;
            case 32: return 0.697;
            case 64: return 0.709;
            default: return 0.7213 / (1 + 1.079 / bucketCount);
        }
    }

    // Example usage
    public static void main(String[] args) {
        HyperLogLog hll = new HyperLogLog(10); // 1024 buckets

        for (int i = 0; i < 100000; i++) {
            hll.add("item" + i);
        }

        System.out.println("Estimated cardinality: " + hll.estimate());
        System.out.println("Actual cardinality: 100000");
    }
}