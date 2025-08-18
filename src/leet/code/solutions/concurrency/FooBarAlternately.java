package leet.code.solutions.concurrency;

import java.util.concurrent.Semaphore;

/*
1115

https://leetcode.com/problems/print-foobar-alternately/description/

Suppose you are given the following code:

class FooBar {
  public void foo() {
    for (int i = 0; i < n; i++) {
      print("foo");
    }
  }

  public void bar() {
    for (int i = 0; i < n; i++) {
      print("bar");
    }
  }
}
The same instance of FooBar will be passed to two different threads:

thread A will call foo(), while
thread B will call bar().
Modify the given program to output "foobar" n times.



Example 1:

Input: n = 1
Output: "foobar"
Explanation: There are two threads being fired asynchronously. One of them calls foo(), while the other calls bar().
"foobar" is being output 1 time.
Example 2:

Input: n = 2
Output: "foobarfoobar"
Explanation: "foobar" is being output 2 times.

 */
public class FooBarAlternately {

    private int n;

    private Semaphore fooSemaphore = new Semaphore(1);// 1 means FOO starts first
    private Semaphore barSemaphore = new Semaphore(0);


    public FooBarAlternately(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            fooSemaphore.acquire(1); // now foo semaphore is set to 0

            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();

            barSemaphore.release(1);//bar semaphore is now set to 1
        }

    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            barSemaphore.acquire(1);//bar semaphore is now set back to 0

            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();

            fooSemaphore.release(1);//foo sempahore is now set to 1
        }
    }
}