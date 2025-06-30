package leet.code.solutions.stack;

import java.util.Stack;

/*

https://leetcode.com/problems/score-of-parentheses/

856. Score of Parentheses
Medium
Topics
premium lock icon
Companies
Given a balanced parentheses string s, return the score of the string.

The score of a balanced parentheses string is based on the following rule:

"()" has score 1.
AB has score A + B, where A and B are balanced parentheses strings.
(A) has score 2 * A, where A is a balanced parentheses string.


Example 1:

Input: s = "()"
Output: 1
Example 2:

Input: s = "(())"
Output: 2
Example 3:

Input: s = "()()"
Output: 2


Constraints:

2 <= s.length <= 50
s consists of only '(' and ')'.
s is a balanced parentheses string.
 */
public class ScoreOfParenthesis {

    public static void main(String[] args) {
        String pars = "(()(()))";
        int score = scoreOfParentheses(pars);
        System.out.println(score);
    }


    /*
    Time Complexity: O(n)

        Single pass through the string
        Each character is processed once with O(1) stack operations

Space Complexity: O(n)

        Stack can grow up to n/2 elements in worst case (all opening parentheses)
        In practice, space is proportional to maximum nesting depth
     */
    private static int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0); // Base level score

        for (char c : s.toCharArray()) {
            if (c == '(') {
                // Start new nesting level
                stack.push(0);
            } else { // c == ')'
                // End current level, calculate score
                int currentScore = stack.pop();
                int parentScore = stack.pop();//previous to above popped

                // Rule: "()" = 1, "(A)" = 2 * A
                int scoreToAdd = Math.max(2 * currentScore, 1);
                stack.push(parentScore + scoreToAdd);
            }
        }

        return stack.pop();
    }
    /**
    Key Rules Summary

        Empty parentheses () → Score = 1

        Concatenation AB → Score = A + B

        Nesting (A) → Score = 2 × A
    * */

    /**
    Learning Points

        Stack for nesting: Perfect for problems with nested structures

        Score calculation: Math.max(2 * current, 1) handles both () and (A) cases

        Balanced parentheses: Stack naturally handles the matching process

        Base case: Always initialize stack with base value to avoid edge cases

        This pattern is useful for any problem involving nested structures with scoring or evaluation rules!
     **/
}
