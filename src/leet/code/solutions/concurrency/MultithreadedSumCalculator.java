package leet.code.solutions.concurrency;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;
import java.util.List;
import java.util.ArrayList;

public class MultithreadedSumCalculator {

    // Approach 1: Using CompletableFuture with batch processing (RECOMMENDED)
    private static long calculateSumWithBatching(int numberOfTasks) {
        System.out.println("Calculating sum using batching approach for " + numberOfTasks + " numbers");

        final long startTime = System.currentTimeMillis();

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {

            // Determine optimal batch size based on available processors
            int availableProcessors = Runtime.getRuntime().availableProcessors();
            int batchSize = Math.max(1, numberOfTasks / (availableProcessors * 2));

            List<CompletableFuture<Long>> futures = new ArrayList<>();

            // Create batches and submit tasks
            for (int start = 0; start < numberOfTasks; start += batchSize) {
                final int batchStart = start;
                final int batchEnd = Math.min(start + batchSize, numberOfTasks);

                CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
                    long batchSum = 0;
                    for (int i = batchStart; i < batchEnd; i++) {
                        batchSum += i;
                    }
                    return batchSum;
                }, executor);

                futures.add(future);
            }

            // Aggregate results from all batches
            long totalSum = futures.stream()
                    .mapToLong(CompletableFuture::join)
                    .sum();

            final long endTime = System.currentTimeMillis();
            System.out.println("Batching result: " + totalSum + ", time taken: " + (endTime - startTime) + " ms");

            return totalSum;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Approach 2: Using LongAdder (good for high contention)
    private static long calculateSumWithLongAdder(int numberOfTasks) {
        System.out.println("Calculating sum using LongAdder for " + numberOfTasks + " numbers");

        final long startTime = System.currentTimeMillis();

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {

            LongAdder adder = new LongAdder();
            CountDownLatch latch = new CountDownLatch(numberOfTasks);

            IntStream.range(0, numberOfTasks).forEach(i -> {
                executor.submit(() -> {
                    try {
                        adder.add(i);
                        return null;
                    } finally {
                        latch.countDown();
                    }
                });
            });

            // Wait for all tasks to complete
            latch.await();

            long totalSum = adder.sum();

            final long endTime = System.currentTimeMillis();
            System.out.println("LongAdder result: " + totalSum + ", time taken: " + (endTime - startTime) + " ms");

            return totalSum;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Approach 3: Using Parallel Streams (simplest)
    private static long calculateSumWithParallelStream(int numberOfTasks) {
        System.out.println("Calculating sum using parallel streams for " + numberOfTasks + " numbers");

        final long startTime = System.currentTimeMillis();

        long totalSum = IntStream.range(0, numberOfTasks)
                .parallel()
                .asLongStream()
                .sum();

        final long endTime = System.currentTimeMillis();
        System.out.println("Parallel stream result: " + totalSum + ", time taken: " + (endTime - startTime) + " ms");

        return totalSum;
    }

    // Approach 4: Mathematical formula (fastest - no threading needed!)
    private static long calculateSumMathematical(int numberOfTasks) {
        System.out.println("Calculating sum using mathematical formula for " + numberOfTasks + " numbers");

        final long startTime = System.currentTimeMillis();

        // Sum of 0 to n-1 = (n-1) * n / 2
        long totalSum = (long) (numberOfTasks - 1) * numberOfTasks / 2;

        final long endTime = System.currentTimeMillis();
        System.out.println("Mathematical result: " + totalSum + ", time taken: " + (endTime - startTime) + " ms");

        return totalSum;
    }

    // Your original approach (fixed to actually return sum)
    private static long calculateSumOriginal(int numberOfTasks) {
        System.out.println("Calculating sum using original approach for " + numberOfTasks + " numbers");

        final long startTime = System.currentTimeMillis();

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {

            AtomicLong totalSum = new AtomicLong(0);
            CountDownLatch latch = new CountDownLatch(numberOfTasks);

            IntStream.range(0, numberOfTasks).forEach(i -> {
                executor.submit(() -> {
                    try {
                        totalSum.addAndGet(i);
                        return i;
                    } finally {
                        latch.countDown();
                    }
                });
            });

            // Wait for all tasks to complete
            latch.await();

            long result = totalSum.get();

            final long endTime = System.currentTimeMillis();
            System.out.println("Original approach result: " + result + ", time taken: " + (endTime - startTime) + " ms");

            return result;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int numberOfTasks = 1_000_000;

        System.out.println("=== Performance Comparison ===\n");

        // Test all approaches
        calculateSumMathematical(numberOfTasks);
        System.out.println();

        calculateSumWithParallelStream(numberOfTasks);
        System.out.println();

        calculateSumWithBatching(numberOfTasks);
        System.out.println();

        calculateSumWithLongAdder(numberOfTasks);
        System.out.println();

        calculateSumOriginal(numberOfTasks);

        System.out.println("\n=== Recommendation ===");
        System.out.println("For pure speed: Use mathematical formula");
        System.out.println("For learning multithreading: Use batching approach");
        System.out.println("For simplicity: Use parallel streams");
    }
}