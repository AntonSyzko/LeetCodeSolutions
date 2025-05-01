package leet.code.solutions.arrays;

import java.util.HashSet;
import java.util.Set;

/*
https://leetcode.com/problems/happy-number/description/

Write an algorithm to determine if a number n is happy.

A happy number is a number defined by the following process:

Starting with any positive integer, replace the number by the sum of the squares of its digits.
Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
Those numbers for which this process ends in 1 are happy.
Return true if n is a happy number, and false if not.



Example 1:

Input: n = 19
Output: true
Explanation:
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
Example 2:

Input: n = 2
Output: false

 */
public class HappyNumber {

    public static void main(String[] args) {
        int num = 19;

        System.out.println(isHappy(num));

        num = 7;

        System.out.println(isHappy(num));

        num = 2;

        System.out.println(isHappy(num));


        num = 1;

        System.out.println(isHappy(num));
    }

    private static boolean isHappy(int number) {
        Set<Integer> seen = new HashSet<>();

        while(number != 1) {
            int sum = 0;

            int current = number;

            while (current != 0) {

                sum += (current % 10) * (current % 10);
                current /=   10;

            }

            if(seen.contains(sum)) {//if we've seen - it's a CYCLE - it will loop forever
                return false;
            }
            seen.add(sum);

            number = sum;//update original input number to new SUM of it's squared digits
        }

        //if we broke the while loop - number became 1
        return true;
    }

}
