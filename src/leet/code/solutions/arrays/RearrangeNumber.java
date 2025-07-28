package leet.code.solutions.arrays;

import java.util.Arrays;

/*2165

https://leetcode.com/problems/smallest-value-of-the-rearranged-number/description/

You are given an integer num. Rearrange the digits of num such that its value is minimized and it does not contain any leading zeros.

Return the rearranged number with minimal value.

Note that the sign of the number does not change after rearranging the digits.

Example 1:

Input: num = 310
Output: 103
Explanation: The possible arrangements for the digits of 310 are 013, 031, 103, 130, 301, 310.
The arrangement with the smallest value that does not contain any leading zeros is 103.
Example 2:

Input: num = -7605
Output: -7650
Explanation: Some possible arrangements for the digits of -7605 are -7650, -6705, -5076, -0567.
The arrangement with the smallest value that does not contain any leading zeros is -7650.

Constraints:

-1015 <= num <= 1015
 */
public class RearrangeNumber {

    public static void main(String[] args) {
        long a = 301;
        long smallest = smallestNumber(a);
        System.out.println(smallest);

        a = -7605;
        smallest = smallestNumber(a);
        System.out.println(smallest);
    }

    /*
     Time Complexity:

        O(d log d) where d is number of digits (due to sorting)

    Space Complexity:
        O(d) for storing digits
     */
    private static long smallestNumber(long num) {
        if(num == 0) return 0;

        boolean isNegative = num < 0;

        if(isNegative) {
            num = Math.abs(num); // Work with absolute value
        }

        // Convert to character array for easy manipulation
        char[] digits = String.valueOf(num).toCharArray();

        if(isNegative) {
            // For negative numbers: arrange in descending order (larger absolute value makes smaller negative number)
            Arrays.sort(digits);
            //reverse for descending order
            reverseDigits(digits);

            return -Long.parseLong(new String(digits));
        }else{

            // For positive numbers: arrange in ascending order
            Arrays.sort(digits);

            // Handle leading zeros by finding first non-zero digit and swapping it to the front
            for (int i = 0; i < digits.length; i++) {

                if(digits[i] != '0'){//first non 0 met -> move it to the from of num ( e.g. 01 -> becomes 10)
                    char temp = digits[0];
                    digits[0] = digits[i];
                    digits[i] = temp;
                    break;
                }

            }

            return Long.parseLong(new String(digits));
        }
}

    private static void reverseDigits(char[] digits) {

        int start = 0;
        int end = digits.length - 1;

        while (start < end) {

            char temp = digits[start];
            digits[start] = digits[end];
            digits[end] = temp;

            start++;
            end--;
        }
    }
}
