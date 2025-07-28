package leet.code.solutions.arrays;

/*

https://leetcode.com/problems/add-digits/description/

258. Add Digits

Given an integer num, repeatedly add all its digits until the result has only one digit, and return it.

Example 1:

Input: num = 38
Output: 2
Explanation: The process is
38 --> 3 + 8 --> 11
11 --> 1 + 1 --> 2
Since 2 has only one digit, return it.
Example 2:

Input: num = 0
Output: 0
 */
public class AddDigits {

    public static void main(String[] args) {
        int dig = 38;
        int oneDig = addDigits(dig);
        System.out.println(oneDig);
    }

    private static int addDigits(int num) {

        while(num >= 10){

            int sum = 0;

            while (num > 0){
                sum += num % 10;
                num /= 10;
            }

            num = sum;

        }

        return num;
    }
}