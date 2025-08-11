package leet.code.solutions.math;

import java.util.HashSet;
import java.util.Set;

/*
https://leetcode.com/problems/happy-number/

Write an algorithm to determine if a number n is happy.

A happy number is a number defined by the following process:

Starting with any positive integer, replace the number by the sum of the squares of its digits.
Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
Those numbers for which this process ends in 1 are happy.
Return true if n is a happy number, and false if not.

Example 1:     Input: n = 19   Output: true
Explanation:   12 + 92 = 82  82 + 22 = 68   62 + 82 = 100   12 + 02 + 02 = 1

Example 2:  Input: n = 2   Output: false

Constraints:  1 <= n <= 231 - 1
 */
public class HappyNumber {

    public static void main(String[] args) {
        int n = 19;
        boolean isHappyNumber = isHappy2(n);
        System.out.println(isHappyNumber);
    }


    public static boolean isHappy(int n) {
        Set<Integer> alreadySeenMemoization = new HashSet<>();

        while (n != 1) {//iterate untill the number is NOT  happy

            int currentNumberForThisIteration = n;//copy to extract digit and NOT to overrwrite N
            int sum = 0;

            while (currentNumberForThisIteration != 0) {//iterate to extract every digit of NUM and add it to the sum
                int lastDigit = currentNumberForThisIteration % 10;// MOD 10 - get last digit
                sum += lastDigit * lastDigit;//add it's square to sum
                currentNumberForThisIteration = currentNumberForThisIteration / 10;//divide by 10 to move to next digit
                //and repet it untill all the digits are added to sum
            }

            if (alreadySeenMemoization.contains(sum)) {//check if already met - to identify  CYCLE LOOP
                return false;
            }

            alreadySeenMemoization.add(sum);//add to memoization set

            n = sum;//update N to newly generated sum

        }

        return true;//got here - happy number

    }

    private static boolean isHappy2(int n) {

        Set<Integer> seen = new HashSet<>();

        while(n != 1){

            int sum = 0;

            while(n > 0){
                int rightMostDigit = n % 10;
                sum += ( rightMostDigit * rightMostDigit);
                n = n /10;
            }

            if(sum == 1){
                return true;
            }

            if(seen.contains(sum)){
                return false;
            }

            seen.add(sum);
            n = sum;

        }

        return false;
    }
}