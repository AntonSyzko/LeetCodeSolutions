package leet.code.solutions.backtracking;

import leet.code.solutions.greedy.ChainOfPairs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/

Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

Example 1:

Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
Example 2:

Input: digits = ""
Output: []
Example 3:

Input: digits = "2"
Output: ["a","b","c"]

Constraints:

0 <= digits.length <= 4
digits[i] is a digit in the range ['2', '9'].
 */
public class LetterCombinationsOfPhoneNumber {

    public static void main(String[] args) {
      String number = "23";
      List<String> permutations = letterCombinations(number);
        System.out.println(permutations);
    }

    private static final String[] KEYPAD = {
            "",     // 0
            "",     // 1
            "abc",  // 2
            "def",  // 3
            "ghi",  // 4
            "jkl",  // 5
            "mno",  // 6
            "pqrs", // 7
            "tuv",  // 8
            "wxyz"  // 9
    };

    private static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();

        // Handle edge case
        if (digits == null || digits.length() == 0) {
            return result;
        }

        // Start backtracking with an empty combination
        backtrack(digits, 0, new StringBuilder(), result);

        return result;
    }

    /*
    Time and Space Complexity

        Time Complexity: O(4^N * N) where N is the length of the input string

        There are at most 4 letters per digit (for digits 7 and 9)
        We need to create strings of length N
        Each string creation takes O(N) time

Space Complexity: O(N)

        The recursion stack could go N levels deep
        We're using a StringBuilder which needs O(N) space
     */
    private  static  void backtrack(String digits, int index, StringBuilder sb, List<String> result) {
        //BASE
        if(sb.length() == digits.length()) {//combo of necessary len formed
            result.add(sb.toString());
            return;
        }

        if(index >= digits.length()) {//index exceeded len
            return;
        }

        int currentNumber = digits.charAt(index) - '0';
        String currKeypad = KEYPAD[currentNumber];

        for (int i = 0; i < currKeypad.length(); i++) {//iterate over all digits of a KEYPAD number from ZERO (not from index !!!!)

            sb.append(currKeypad.charAt(i));

            backtrack(digits, index + 1, sb, result);//recur to next

            sb.deleteCharAt(sb.length() - 1);//backtrack
        }
    }


    //------- ITERATIVE ------

    /*
    Time and Space Complexity

        Time Complexity: O(4^N * N)

        Same as the backtracking solution, as we're still generating all possible combinations
        String concatenation inside the loop also takes O(N) time

Space Complexity: O(4^N)

    We store all combinations at each step, which can be up to 4^N strings
     */
    public List<String> letterCombinations_Iter_BFS(String digits) {
        List<String> result = new ArrayList<>();

        // Handle edge case
        if (digits == null || digits.length() == 0) {
            return result;
        }

        // Initialize with an empty string
        result.add("");

        // Process each digit
        for (int i = 0; i < digits.length(); i++) {
            // Get the current digit and its corresponding letters
            int digit = digits.charAt(i) - '0';
            String letters = KEYPAD[digit];

            // Create a new list for the updated combinations
            List<String> newCombinations = new ArrayList<>();

            // Combine each existing result with each letter of the current digit
            for (String combination : result) {
                for (char letter : letters.toCharArray()) {
                    newCombinations.add(combination + letter);
                }
            }

            // Update the result with the new combinations
            result = newCombinations;
        }

        return result;
    }

    //----------------- BFS Q -------------------
    public List<String> letterCombinations_BFS_Q(String digits) {
        List<String> result = new ArrayList<>();

        // Handle edge case
        if (digits == null || digits.length() == 0) {
            return result;
        }

        // Use a queue for BFS
        Queue<String> queue = new LinkedList<>();
        queue.add("");

        for (int i = 0; i < digits.length(); i++) {
            int digit = digits.charAt(i) - '0';
            String letters = KEYPAD[digit];

            int size = queue.size();

            for (int qElement = 0; qElement < size; qElement++) {
                String current = queue.poll();

                for (char letter : letters.toCharArray()) {
                    queue.add(current + letter);
                }
            }
        }

        // Convert queue to list for the final result
        result.addAll(queue);

        return result;
    }

}
