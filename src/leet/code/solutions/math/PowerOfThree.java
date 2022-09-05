package leet.code.solutions.math;

/*
https://leetcode.com/problems/power-of-three/
Given an integer n, return true if it is a power of three. Otherwise, return false.

An integer n is a power of three, if there exists an integer x such that n == 3x.
Example 1:
Input: n = 27
Output: true

Example 2:
Input: n = 0
Output: false

Example 3:
Input: n = 9
Output: true

Constraints:-231 <= n <= 231 - 1

Follow up: Could you solve it without loops/recursion?
 */
public class PowerOfThree {

    public static void main(String[] args) {
        boolean isPowerOfTwo = isPowerOfThree(27);
        System.out.println(isPowerOfTwo);

        isPowerOfTwo = isPowerOfThree(0);
        System.out.println(isPowerOfTwo);

        isPowerOfTwo = isPowerOfThree(9);
        System.out.println(isPowerOfTwo);

        isPowerOfTwo = isPowerOfThreeRecursive(27);
        System.out.println(isPowerOfTwo);

        isPowerOfTwo = isPowerOfThreeRecursive(9);
        System.out.println(isPowerOfTwo);

        isPowerOfTwo = isPowerOfThreeIterativeModulo(6);
        System.out.println(isPowerOfTwo);
    }

    public static boolean isPowerOfThree(int n) {
        long toGeneratePowersOfThree = 1; // starting at 1 - since 3 ^0 = 1; 3 to the zero power of 0 = 1

        while (toGeneratePowersOfThree < n) {//itearet through all powers of 3 until N
            toGeneratePowersOfThree = toGeneratePowersOfThree * 3; //generate powers of three for all powers up to N
        }
//when while loop breaks our power of three is the max up to N
        return toGeneratePowersOfThree == n; //compare if it is equal to N
    }


//
    public static boolean isPowerOfThreeRecursive(int n) {
        if (n == 1) { //base cases
            return true;
        } else if (n == 0) {
            return false;
        }

        if (n % 3 != 0) { // not MODULO 3
            return false;
        }
        n = n / 3; // divide by 3 for next recursive check
        return isPowerOfThreeRecursive(n);//checks each time n/3 until base case hit
    }


    //
    public static boolean isPowerOfThreeIterativeModulo(int n) {
        if (n == 1) {
            return true;
        }

        boolean result = false;

        while (n >= 2) {

            if (n % 3 == 0) { //if MODULO 3 = 0
                result = true;
            } else { // if MODULO 3  not 0
                return false;  //exit with false straight away
            }
            n = n / 3;//divide by 3 - to compare next and move while loop
        }

        return result;
    }

}
