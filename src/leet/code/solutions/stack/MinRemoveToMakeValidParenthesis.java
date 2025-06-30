package leet.code.solutions.stack;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/*
https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/description/

Given a string s of '(' , ')' and lowercase English characters.

Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:

It is the empty string, contains only lowercase characters, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.

Example 1:

Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
Example 2:

Input: s = "a)b(c)d"
Output: "ab(c)d"
Example 3:

Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.
 */
public class MinRemoveToMakeValidParenthesis {

    public static void main(String[] args) {
        String s = "a)b(c)d";

        String res = minRemoveToMakeValid(s);
        System.out.println(res);

        s = "lee(t(c)o)de)";
        res = minRemoveToMakeValid(s);
        System.out.println(res);

        s = "))((";
        res = minRemoveToMakeValid(s);
        System.out.println(res);
    }

    /*
    O(n)
    O(n)
     */
    private static String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            if(c == '(' || c == ')'){
                if(!stack.isEmpty() && s.charAt(stack.peek()) == '(' && c == ')'){
                    stack.pop();
                }else{
                    stack.push(i);
                }
            }
        }

        Set<Integer> toRemove = new HashSet<>(stack);//O(1) to avoid String Builder .deleteCharAt() which will be O(N^2)
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if(toRemove.contains(i)){
                continue;
            }
            sb.append(s.charAt(i));
        }

        return sb.toString();
    }
}
