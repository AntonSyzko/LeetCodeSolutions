package leet.code.solutions.matrix;

import java.util.Arrays;

/*
https://leetcode.com/problems/minimum-path-sum/description/

Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example 1:
Input: grid =   [[1,3,1],
                [1,5,1],
                [4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.

Example 2:
Input: grid = [[1,2,3],[4,5,6]]
Output: 12

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 200
0 <= grid[i][j] <= 200
 */
public class MinPathSum {

    public static void main(String[] args) {
        int[][] grid = {
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };

        int minPathSum = minPathSum(grid);
        System.out.println(minPathSum);//7

        int[][] grid2 = {
                {1,2,3},
                {4,5,6}
        };

        int minPathSum2 = minPathSum(grid2);
        System.out.println(minPathSum2);//12

    }

    // Time O(m * n)
    //Space O(m * n)

    private static int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0) return 0;

        int ROWS = grid.length;
        int COLS = grid[0].length;

        int[][] DP = new int[ROWS][COLS];

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {

                DP[row][col] += grid[row][col];

                if(row > 0 && col >0){
                    DP[row][col] += Math.min(DP[row-1][col], DP[row][col-1]);
                }else if(row >0){
                    DP[row][col] += DP[row-1][col];
                }else if ( col >0){
                    DP[row][col] += DP[row][col-1];
                }
            }
        }

        return DP[ROWS-1][COLS-1];

    }


    // Time O(m * n)
    //Space O(m * n)
    private static int minPathSumMy(int[][] grid) {
        int [][] DP = new int[grid.length][grid[0].length];
        DP[0][0] = grid[0][0];

        for (int col = 1; col < grid[0].length; col++) {
            DP[0][col] = DP[0][col - 1] + grid[0][col];
        }

        for (int row = 1; row < grid.length; row++) {
           DP[row][0] = DP[row - 1][0] + grid[row][0];
        }

        for (int row = 1; row < grid.length; row++) {
            for (int col = 1; col < grid[row].length; col++) {
                System.out.println("iteration for row " + row + " col " + col);
                dfsPath(grid, row ,col, DP);
            }
        }

       return DP[grid.length - 1][grid[0].length - 1];

    }

    private static int dfsPath(int[][] grid, int row, int col, int [][] DP) {

        int valueFromLeftCol = DP[row][col - 1] + grid[row][col] ;
        int valueFromAboveRow =  DP[row - 1][col] +grid[row][col];

        DP[row][col] = Math.min( valueFromLeftCol , valueFromAboveRow);

        return DP[row][col];
    }
}
