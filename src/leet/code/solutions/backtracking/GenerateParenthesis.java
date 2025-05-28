package leet.code.solutions.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/generate-parentheses/description/

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:

Input: n = 1
Output: ["()"]

Constraints:

1 <= n <= 8
 */
public class GenerateParenthesis {

    public static void main(String[] args) {
        List<String> parenthesis =  generateParenthesis(3);
        System.out.println(parenthesis);
    }

    private static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        StringBuilder sbStack = new StringBuilder();

        int open = 0;
        int closed = 0;

        buildParenthesis(n, open,  closed, sbStack, result);

        return result;
    }

    /*
    Time Complexity
The time complexity is determined by the number of valid parentheses combinations for n pairs, which is the nth Catalan number:

The nth Catalan number is approximately 4nn3/2π\frac{4^n}{n^{3/2}\sqrt{\pi}}
n3/2π​4n​
This means the time complexity is O(4nn3/2\frac{4^n}{n^{3/2}}
n3/24n​), often approximated as O(4^n / √n)


Space Complexity
The space complexity consists of:

O(n) for the recursion stack (maximum depth is 2n)
O(n) for the StringBuilder
O(Catalan(n)) for storing all valid combinations in the result list
Overall space complexity is O(Catalan(n)) ≈ O(4^n / √n)
     */

    private static void buildParenthesis(int n, int open, int closed, StringBuilder sbStack, List<String> result) {
        //BASE
        if(closed == n && open == closed){
            result.add(sbStack.toString());
            return;
        }

        if(open < n){//still room to generate OPEN
            sbStack.append("(");

            buildParenthesis(n, open + 1, closed, sbStack, result);//recur with added OPEN

            sbStack.deleteCharAt(sbStack.length()-1);//backtrack
        }

        if(closed < open){//still room to generate CLOSED

            sbStack.append(")");

            buildParenthesis(n, open, closed + 1, sbStack, result);//recur with added CLOSED

            sbStack.deleteCharAt(sbStack.length()-1);//backtrack

        }
    }
}
