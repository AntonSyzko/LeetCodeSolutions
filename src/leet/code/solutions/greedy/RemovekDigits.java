package leet.code.solutions.greedy;

import java.util.Stack;

/*
https://leetcode.com/problems/remove-k-digits/description/

Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer after removing k digits from num.

Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.


Constraints:

1 <= k <= num.length <= 105
num consists of only digits.
num does not have any leading zeros except for the zero itself.
 */
public class RemovekDigits {

    public static void main(String[] args) {
        // Test cases
        System.out.println(removeKdigits("1432219", 3)); // Output: "1219"
        System.out.println(removeKdigits("10200", 1));   // Output: "200"
        System.out.println(removeKdigits("10", 2));      // Output: "0"
    }

    /*
    Time and Space Complexity

            Time Complexity: O(n) where n is the length of the input string

            We process each digit exactly once
            Each digit can be pushed and popped at most once

            Space Complexity: O(n)

            In the worst case, our stack might contain most of the digits
     */
    public static String removeKdigits(String num, int k) {
        // Edge cases
        if (k >= num.length()) {
            return "0";
        }
        if (k == 0) {
            return num;
        }

        // Use a stack to keep track of the result
        Stack<Character> stack = new Stack<>();

        // Process each digit in the number
        for (char digit : num.toCharArray()) {
            // If the current digit is smaller than the last digit in the stack
            // and we still need to remove digits, remove the last digit from stack
            while (!stack.isEmpty() && k > 0 && stack.peek() > digit) {
                stack.pop();
                k--;
            }

            // Push current digit to the stack (skip leading zeros)
            if (stack.isEmpty() && digit == '0') {
                continue;
            }
            stack.push(digit);
        }

        // If we still need to remove digits, remove from the end
        while (!stack.isEmpty() && k > 0) {
            stack.pop();
            k--;
        }

        // Build the result string from the stack
        StringBuilder result = new StringBuilder();
        for (char digit : stack) {
            result.append(digit);
        }

        // Handle the empty result case (all digits were removed)
        if (result.isEmpty()) {
            return "0";
        }

        return result.toString();
    }
}
