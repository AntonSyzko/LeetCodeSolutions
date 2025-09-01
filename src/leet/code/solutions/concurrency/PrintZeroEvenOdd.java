package leet.code.solutions.concurrency;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/*
You have a function printNumber that can be called with an integer parameter and prints it to the console.

For example, calling printNumber(7) prints 7 to the console.
You are given an instance of the class ZeroEvenOdd that has three functions: zero, even, and odd. The same instance of ZeroEvenOdd will be passed to three different threads:

Thread A: calls zero() that should only output 0's.
Thread B: calls even() that should only output even numbers.
Thread C: calls odd() that should only output odd numbers.
Modify the given class to output the series "010203040506..." where the length of the series must be 2n.

Implement the ZeroEvenOdd class:

ZeroEvenOdd(int n) Initializes the object with the number n that represents the numbers that should be printed.
void zero(printNumber) Calls printNumber to output one zero.
void even(printNumber) Calls printNumber to output one even number.
void odd(printNumber) Calls printNumber to output one odd number.


Example 1:

Input: n = 2
Output: "0102"
Explanation: There are three threads being fired asynchronously.
One of them calls zero(), the other calls even(), and the last one calls odd().
"0102" is the correct output.
Example 2:

Input: n = 5
Output: "0102030405"
 */
public class PrintZeroEvenOdd {

   private static class ZeroEvenOdd {
        private int n;

        private Semaphore zero;
        private Semaphore even;
        private Semaphore odd;

        public ZeroEvenOdd(int n) {
            this.n = n;
            zero = new Semaphore(1);// Zero starts first
            even = new Semaphore(0);
            odd = new Semaphore(0);
        }

       public void zero(IntConsumer printNumber) throws InterruptedException {

            for(int i = 0; i < n; i++){

                zero.acquire();// Wait for permission to print zero

                printNumber.accept(0);

                // After printing zero, decide whether to wake up odd or even

                // MIIIND !!!!!!!!!! i is ITERATION number meaning if i is ODD -> next number is EVEN and vice versa
                if(i % 2 == 0){           // If iteration is even (0,2,4...)

                    odd.release();        // Next number is odd (1,3,5...) -> release odd semaphore

                }else {                   // If iteration is odd (1,3,5...)

                    even.release();       // Next number is even (2,4,6...) -> release even semaphore

                }
            }
       }

       public void even(IntConsumer printNumber) throws InterruptedException {

            for(int i = 2; i <= n; i +=2){ // Even numbers: 2, 4, 6,

                even.acquire();// Wait for permission to print even

                printNumber.accept(i);

                zero.release();//Wake up zero thread
            }
       }

       public void odd(IntConsumer printNumber) throws InterruptedException {
                for(int i = 1 ; i <= n; i+= 2){ // Odd numbers: 1, 3, 5, ...

                    odd.acquire();// Wait for permission to print odd

                    printNumber.accept(i);

                    zero.release(); // Wake up zero thread
                }
       }

       }

    public static void main(String[] args) {
        // Test with n = 5
        testZeroEvenOdd(5);

        System.out.println("\n" + "=".repeat(20));

        // Test with n = 2
        testZeroEvenOdd(2);
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

        // Create and start threads
        Thread zeroThread = new Thread(() -> {
            try {
                zeroEvenOdd.zero(printNumber);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

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
        zeroThread.start();
        evenThread.start();
        oddThread.start();

        // Wait for all threads to complete
        try {
            zeroThread.join();
            evenThread.join();
            oddThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\nExpected length: " + (2 * n));
        System.out.println("Actual length: " + result.length());
        System.out.println("Result: " + result.toString());
    }
}