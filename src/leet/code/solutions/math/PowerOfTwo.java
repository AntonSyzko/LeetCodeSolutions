package leet.code.solutions.math;

/*
https://leetcode.com/problems/power-of-two/
Given an integer n, return true if it is a power of two. Otherwise, return false.
An integer n is a power of two, if there exists an integer x such that n == 2x.

Example 1:
Input: n = 1
Output: true
Explanation: 2^0 = 1

Example 2:
Input: n = 16
Output: true
Explanation: 2^4 = 16

Example 3:
Input: n = 3
Output: false

Constraints:-231 <= n <= 231 - 1

Follow up: Could you solve it without loops/recursion?
 */
public class PowerOfTwo {

    public static void main(String[] args) {
        boolean isPowerOfTwo = isPowerOfTwo(16);
        System.out.println(isPowerOfTwo);

        isPowerOfTwo = isPowerOfTwo(10);
        System.out.println(isPowerOfTwo);

        isPowerOfTwo = isPowerOfTwo(1);
        System.out.println(isPowerOfTwo);

        isPowerOfTwo = isPowerOfTwoRecursive(-16);
        System.out.println(isPowerOfTwo);

        isPowerOfTwo = isPowerOfTwoRecursive(0);
        System.out.println(isPowerOfTwo);

        isPowerOfTwo = isPowerOfTwoRecursive(5);
        System.out.println(isPowerOfTwo);
    }

    public static boolean isPowerOfTwo(int n) {
        long toGeneratePowersOfTwo = 1; // starting at 1 - since 2 ^0 = 1; 2 to the zero power of two = 1

        while (toGeneratePowersOfTwo < n) {
            toGeneratePowersOfTwo = toGeneratePowersOfTwo * 2; //generate powers of two for all powers up to N
        }
//when while loop breaks our power of two is the max up to N
        return toGeneratePowersOfTwo == n; //compare if it is equal to N
    }


    public static boolean isPowerOfTwoModulo(int n) {
        if (n == 1) {
            return true;
        }

        boolean result = false;

        while (n >= 2) {
            if (n % 2 == 0) { //if MODULO 2 = 0
                result = true;
            } else { // if MODULO 2  not 0
                return false;  //exit with false straight away
            }
            n = n / 2;//divide by 2 - to compare next
        }
        return result;
    }

    public static boolean isPowerOfTwoRecursive(int n) {
        if (n == 1) {
            return true;
        } else if (n == 0) {
            return false;
        }

        if (n % 2 != 0) {
            return false;
        }
        n = n / 2;
        return isPowerOfTwoRecursive(n);

    }

}
