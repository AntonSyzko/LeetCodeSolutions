package leet.code.solutions.stack;

import java.util.ArrayList;
import java.util.List;
/*
https://neetcode.io/problems/generate-parentheses

You are given an integer n. Return all well-formed parentheses strings that you can generate with n pairs of parentheses.

Example 1:

Input: n = 1

Output: ["()"]
Example 2:

Input: n = 3

Output: ["((()))","(()())","(())()","()(())","()()()"]
You may return the answer in any order.

Constraints:

1 <= n <= 7
 */
public class GenerateParenthesis {

    public static void main(String[] args) {
      int n = 3;
      List<String> res  = generateParenthesis(n);
      System.out.println(res);
    }

    private static List<String> generateParenthesis(int allowedCount) {
        List<String> res = new ArrayList<>();
        StringBuilder stack = new StringBuilder();//acts like a stack

        int open = 0;
        int closed = 0;

        constructParetnhesis(allowedCount, open, closed, stack, res);

        return res;
    }

    private static void constructParetnhesis(int allowedCount, int open, int closed, StringBuilder stack, List<String> res) {
        //BASE -> same open and closed AND  allowed closed count exceeded
        if(open == closed && closed == allowedCount){
            res.add(stack.toString());
            return;
        }

        //still room for opening brackets
        if(open < allowedCount){
            stack.append('(');
            constructParetnhesis(allowedCount, open +1, closed, stack, res);//recur with open bracket count +1
            stack.deleteCharAt(stack.length()-1);//backtrack from stack
        }

        //as long as closed is LESS than opening
        if(closed < open){
            stack.append(')');
            constructParetnhesis(allowedCount, open,  closed +1, stack, res);//recur with closing bracket count +1
            stack.deleteCharAt(stack.length()-1);//backtrack from stack
        }
    }
}
