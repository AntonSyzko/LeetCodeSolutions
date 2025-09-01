package leet.code.solutions.concurrency;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class MultithreadedPrint {

    public static void main(String[] args) {
        print(1000);
    }


    private static void print(int numberOfTasks) {
        int BLOCKING_CALL_DELAY = 1;

        System.out.println( "Number of tasks executed by executor " + numberOfTasks + " ");

        final long startTime = System.currentTimeMillis();

        try ( var executor = Executors.newVirtualThreadPerTaskExecutor() ) {

            IntStream.range(0, numberOfTasks).forEach( i -> {
                executor.submit(() -> {
                    Thread.sleep(Duration.ofSeconds(BLOCKING_CALL_DELAY));//simulate long I/O or DB action
                    return i;
                });
            });

        } catch (Exception e) {
             throw new RuntimeException(e);
        }

        final long endTime = System.currentTimeMillis();

        System.out.println(" time taken " + (endTime - startTime)  + " milliseconds");

    }
}