package leet.code.solutions.dynamic_programming;

/*
221

https://leetcode.com/problems/maximal-square/description/

Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Example 1:


Input: matrix = [["1","0","1","0","0"],
                ["1","0","1","1","1"],
                ["1","1","1","1","1"],
                ["1","0","0","1","0"]]

Output: 4

Example 2:
Input: matrix = [["0","1"],
                ["1","0"]]
Output: 1

Example 3:
Input: matrix = [["0"]]
Output: 0

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 300
matrix[i][j] is '0' or '1'.
 */
public class MaxSquare {

    public static void main(String[] args) {
        char[][] matrix  = {
                {'0','1','1','1'},
                {'0','1','1','1'},
                {'0','1','1','1'},
                {'0','1','1','0'},
        };

        int maxSq = maximalSquare(matrix);
        System.out.println(maxSq);
    }


    //O( m * n )
    // Optimized DP approach - O(m*n) time, O(m*n) space
    public static int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int ROWS = matrix.length;
        int COLS = matrix[0].length;

        int[][] DP = new int[ROWS][COLS];

        int maxSide = 0;

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {

                if (matrix[row][col] == '1') {//we only expand from 1 cells

                    if (row == 0 || col == 0) {//and we are talking about '1' cell already
                        // First row or first column can only form 1 x 1 squares
                        DP[row][col] = 1;

                    } else {

                        // DP recurrence: min of three neighbors + 1
                        DP[row][col] = Math.min(
                                DP[row-1][col-1],//diagonal
                                Math.min(DP[row-1][col], //left
                                        DP[row][col-1])//  above
                                )
                                + 1;//+1 is increased results of 3 minimums

                    }

                    maxSide = Math.max(maxSide, DP[row][col]);
                }
                // If matrix[row][col] == '0', DP[row][col] remains 0 (default)
            }
        }

        return maxSide * maxSide; // Return area (side^2)
    }

    /*

    Why this works:

            dp[i][j] represents the side length of the largest square ending at position (i,j)
            To form a square of side k at (i,j), we need:

            A square of side k-1 ending at (i-1,j) (above)
            A square of side k-1 ending at (i,j-1) (left)
            A square of side k-1 ending at (i-1,j-1) (diagonal)
            Current cell must be '1'

            Example walkthrough:
For matrix [["1","1","1"],
            ["1","1","1"],
            ["1","1","1"]]:

DP table progression:

Step 1: [1,1,1]    Step 2: [1,1,1]    Final: [1,1,1]
        [1,?,?]            [1,2,?]           [1,2,3]
        [1,?,?]            [1,?,?]           [1,2,3]
     */

}
