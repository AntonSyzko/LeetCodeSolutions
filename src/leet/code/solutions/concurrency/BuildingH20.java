package leet.code.solutions.concurrency;

import java.util.concurrent.Semaphore;

/*
1117

https://leetcode.com/problems/building-h2o/description/

There are two kinds of threads: oxygen and hydrogen. Your goal is to group these threads to form water molecules.

There is a barrier where each thread has to wait until a complete molecule can be formed. Hydrogen and oxygen threads will be given releaseHydrogen and releaseOxygen methods respectively, which will allow them to pass the barrier. These threads should pass the barrier in groups of three, and they must immediately bond with each other to form a water molecule. You must guarantee that all the threads from one molecule bond before any other threads from the next molecule do.

In other words:

If an oxygen thread arrives at the barrier when no hydrogen threads are present, it must wait for two hydrogen threads.
If a hydrogen thread arrives at the barrier when no other threads are present, it must wait for an oxygen thread and another hydrogen thread.
We do not have to worry about matching the threads up explicitly; the threads do not necessarily know which other threads they are paired up with. The key is that threads pass the barriers in complete sets; thus, if we examine the sequence of threads that bind and divide them into groups of three, each group should contain one oxygen and two hydrogen threads.

Write synchronization code for oxygen and hydrogen molecules that enforces these constraints.

Example 1:

Input: water = "HOH"
Output: "HHO"
Explanation: "HOH" and "OHH" are also valid answers.
Example 2:

Input: water = "OOHHHH"
Output: "HHOHHO"
Explanation: "HOHHHO", "OHHHHO", "HHOHOH", "HOHHOH", "OHHHOH", "HHOOHH", "HOHOHH" and "OHHOHH" are also valid answers.

Constraints:

3 * n == water.length
1 <= n <= 20
water[i] is either 'H' or 'O'.
There will be exactly 2 * n 'H' in water.
There will be exactly n 'O' in water.
 */
public class BuildingH20 {

    private Semaphore h ;//for hydrogen

    private Semaphore o ;

    public BuildingH20() {
        h = new Semaphore(2); // Initial state: h semaphore has 2 permits
        o = new Semaphore(0);// Initial state: 0 semaphore has NO permits
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

        h.acquire(); // take 1 permit - basically each entering thread will get 1 permit ( h semaphore starts with Semaphore(2) so 2 threads will acquire each 1 permit )

        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();

        o.release();//give 1 permit to 0
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {

        o.acquire(2);//get 2 permits from h ( so both H threads will be finished and then only O starts proceeding)

        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();

        h.release(2);//give 2 permits back to h
    }
}