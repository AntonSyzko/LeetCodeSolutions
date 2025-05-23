package leet.code.solutions.arrays;

import java.util.*;

/*
https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/description/

You are given a string s consisting of lowercase English letters. A duplicate removal consists of choosing two adjacent and equal letters and removing them.

We repeatedly make duplicate removals on s until we no longer can.

Return the final string after all such duplicate removals have been made. It can be proven that the answer is unique.

Example 1:

Input: s = "abbaca"
Output: "ca"
Explanation:
For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
Example 2:

Input: s = "azxxzy"
Output: "ay"

Constraints:

1 <= s.length <= 105
s consists of lowercase English letters.
 */
public class RemoveAdjacentDuplicatesInString {
    public static void main(String[] args) {
      String s = "azxxzy";
      String dedup = removeDuplicates(s);
      System.out.println(dedup);

      String s2 = "abbaca";
        String  dedup2 = removeDuplicates(s2);
        System.out.println(dedup2);
    }

    private static String removeDuplicates(String s) {
        if (s.length() <= 1) return s;

        StringBuilder sb = new StringBuilder();

        for(char curr : s.toCharArray()) {
            if(!sb.isEmpty() && curr == sb.charAt(sb.length() - 1)) {
                sb.deleteCharAt(sb.length() - 1);
            }else{
                sb.append(curr);
            }
        }

        return sb.toString();
    }

        private static String removeDuplicatesStack(String s) {

        if (s.length() <= 1) return s;

        Stack<Character> stack = new Stack<>();

        for (char currChar : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == currChar) {
                stack.pop();
            } else {
                stack.push(currChar);
            }
        }

        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.reverse().toString();
    }
}
