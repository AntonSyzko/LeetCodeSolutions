package leet.code.solutions.concurrency;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class PrintEvenOdd {

    private static class ZeroEvenOdd {
        int n;
        private Semaphore even;
        private Semaphore odd;

        public ZeroEvenOdd(int n) {
            this.n = n;
            this.even = new Semaphore(1);
            this.odd =  new Semaphore(0);
        }

        public void even(IntConsumer intConsumer) throws InterruptedException {

            for(int i = 0; i <= n; i+= 2){

                even.acquire();

                intConsumer.accept(i);

                odd.release();
            }

        }
        

        public void odd(IntConsumer intConsumer) throws InterruptedException {
            
            for(int i = 1; i <= n; i+= 2){
                odd.acquire();

                intConsumer.accept(i);
                
                even.release();
            }
        }

        }

    public static void main(String[] args) {
        // Test with n = 5
        testZeroEvenOdd(4);

     //   System.out.println("\n" + "=".repeat(20));

        // Test with n = 2
     //   testZeroEvenOdd(2);
    }

    public static void testZeroEvenOdd(int n) {
        System.out.println("Testing with n = " + n);
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(n);
        StringBuilder result = new StringBuilder();

        // IntConsumer that appends to result
        IntConsumer printNumber = (x) -> {
            result.append(x);
            System.out.print(x);
        };

        Thread evenThread = new Thread(() -> {
            try {
                zeroEvenOdd.even(printNumber);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread oddThread = new Thread(() -> {
            try {
                zeroEvenOdd.odd(printNumber);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Start all threads
        evenThread.start();
        oddThread.start();

        // Wait for all threads to complete
        try {
            evenThread.join();
            oddThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\nExpected length: " + ( n + 1));
        System.out.println("Actual length: " + result.length());
        System.out.println("Result: " + result.toString());
    }
        }
    
    
    
    
    
