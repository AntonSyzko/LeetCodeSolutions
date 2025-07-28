package leet.code.solutions.blind75;

import java.util.Arrays;

/*
https://leetcode.com/problems/unique-paths/description/

There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The test cases are generated so that the answer will be less than or equal to 2 * 109.

Input: m = 3, n = 7
Output: 28
Example 2:

Input: m = 3, n = 2
Output: 3
Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down
 */
public class UniquePaths {

    public static void main(String[] args) {
       int bottomRightRow = 3;
       int bottomRightCol = 3;

       int uniquePath = uniquePaths(bottomRightRow, bottomRightCol);
       System.out.println(uniquePath);
    }

    private static int uniquePaths(int bottomRightRow, int bottomRightCol) {
       int[][] dp = new int[bottomRightRow][bottomRightCol];


       for(int[] row : dp){ //fill just every row with 1 ( or could have been COL for that matter , same
           Arrays.fill(row, 1);
       }
       //or fill this way
//       for (int row = 0; row < bottomRightRow; row++) {//fill all ROWS of first colmn with 1  - so all left wall ROWS with 1 as there is just 1 way to get there anyways
//           dp[row][0] = 1;
//       }
//
//        for (int col = 0; col < bottomRightCol; col++) {//fill all COLS of first row with 1  - so all top wall COLS with 1 as there is just 1 way to get there anyways
//            dp[0][col] = 1;
//        }


       for (int row = 1; row < bottomRightRow; row++) {//start from 1 !!!!
           for (int col = 1; col < bottomRightCol; col++) {//stert from 1 !!!!

               dp[row][col] = dp[row - 1][col]  //row at top
                       + //add !!!
                       dp[row][col - 1];//column at left

           }
       }

       return dp[bottomRightRow - 1][bottomRightCol - 1];//last cell contains result of how many ways is to get there
    }
}
