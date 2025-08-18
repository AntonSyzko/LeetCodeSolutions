package leet.code.solutions.math;

/*
https://leetcode.com/problems/palindrome-number/

Given an integer x, return true if x is palindrome integer.
An integer is a palindrome when it reads the same backward as forward.
For example, 121 is a palindrome while 123 is not.

Example 1:
Input: x = 121
Output: true
Explanation: 121 reads as 121 from left to right and from right to left.

Example 2:
Input: x = -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.

Example 3:
Input: x = 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.

Constraints:
-231 <= x <= 231 - 1

 */
public class PalindromeNumber {

    public static void main(String[] args) {
        int toCheck = 12321;
        System.out.println(isPalindrome2(toCheck));
    }

    public static boolean isPalindromeUsingHalf(int x) {
        if (x == 0) {
            return true;
        }

        if (x < 0 || x % 10 == 0) {// negatives not palindromes, 10,20 etc... not palindromes
            return false;
        }

        int reversed = 0;


        while (x > reversed) {//will stop at half
            int popTenthReminder = x % 10;
            reversed = (reversed * 10) + popTenthReminder;
            x = x / 10;//divide original by 10 - so that comparison in while strop at half
        }

        if (x == reversed //exact match
            || //OR
            x == reversed / 10) { // odd number match like 12321 -> ended reversed  half = 123 to compare with ended X = 12 , so 123/10 = 12, and 12 == 12 - all good
            return true;
        } else {
            return false;
        }
    }


    public static boolean isPalindrome2(int x) {
        if (x < 0) {//negative not palindrome
            return false;
        }

        int xCopy = x;
        int reversedX = 0;

        while (xCopy != 0) {
            int current = xCopy % 10;
            reversedX = (reversedX * 10) + current;
            xCopy = xCopy / 10;
        }

        return x == reversedX;
    }

    public static boolean isPalindromeBook(int x) {
        if (x < 0) return false;

        final int numOfDigits = (int) (Math.floor(Math.log10(x))) + 1;
        int mostSignificantDigitMask = (int) Math.pow(10, numOfDigits - 1);//to extract first digit

        for (int i = 0; i < numOfDigits / 2; i++) {
            if (x / mostSignificantDigitMask != x % 10) {//compare first and last digit
                return false;
            }
            x %= mostSignificantDigitMask;
            x /= 10;
            mostSignificantDigitMask /= 100;
        }
        return true;
    }


    public static boolean isPalindromeUsingStrings(int x) {
        if (x < 0) {
            return false;
        }

        String[] arr = String.valueOf(x).split("");

        for (int i = 0; i < arr.length / 2; i++) {
            if (!arr[i].equals(arr[arr.length - 1 - i])) {
                return false;
            }
        }
        return true;
    }

}
