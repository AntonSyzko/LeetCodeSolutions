package leet.code.solutions.blind75;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
https://leetcode.com/problems/valid-parentheses/

Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.

Example 1:
Input: s = "()"
Output: true

Example 2:
Input: s = "()[]{}"
Output: true

Example 3:
Input: s = "(]"
Output: false

Example 4:
Input: s = "([])"
Output: true

Constraints:

1 <= s.length <= 104
s consists of parentheses only '()[]{}'.
 */
public class ValidParentheses {

    public static void main(String[] args) {

        String str = "((()){}[]{{}})";
        System.out.println(isValid(str));
    }

    //time O(N)
    //space O(N) - stack
    private static boolean isValid(String str) {
        // Edge case: empty string
        if (str == null || str.isEmpty()) {
            return true;
        }

        Map<Character, Character> validCombos = new HashMap<Character, Character>();
        validCombos.put(')', '(');
        validCombos.put(']', '[');
        validCombos.put('}', '{');

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char currChar = str.charAt(i);

            // If it's a closing bracket
            if (validCombos.containsKey(currChar)) {
                // If stack is empty or top doesn't match, invalid
                if (stack.isEmpty() || stack.pop() != validCombos.get(currChar)) {
                    return false;
                }
            } else {
                // It's an opening bracket, push to stack
                stack.push(currChar);
            }
        }

        return stack.isEmpty();
    }
}
