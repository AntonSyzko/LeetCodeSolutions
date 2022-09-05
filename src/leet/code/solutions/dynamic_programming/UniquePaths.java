package leet.code.solutions.dynamic_programming;

/*
https://leetcode.com/problems/unique-paths/

There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner
(i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The test cases are generated so that the answer will be less than or equal to 2 * 109.

https://cheonhyangzhang.gitbooks.io/leetcode-solutions/content/62_unique_paths__medium.html

A robot is located at the top-left corner of a m x n grid (marked ‘Start’ in the diagram below).
The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked ‘Finish’ in the diagram below).
How many possible unique paths are there?
Above is a 3 x 7 grid. How many possible unique paths are there?
Note: m and n will be at most 100.

Thoughts
This is a very basic and easy dynamic programming problem.

Each time the robot needs to make a choice is either go down or go right. Do the optimized solution is the better one from these two choices.

d[i][j] is the number of unique paths to reach point(i,j)

d[0][0] = 1

d[i][j] = d[i-1][j] +d[i][j-1]
 */
public class UniquePaths {
    public static void main(String[] args) {
        int[][] grid = {{0, 0, 0},
                        {0, 0, 0}};
        //   System.out.println(Arrays.deepToString(grid));
        int uniquePaths = uniquePathsOptimized(3, 3);
        System.out.println(uniquePaths);
    }

    private static int uniquePathsNonDP(int row, int col) {//row and col are grid params
        //recursion base
        if (row < 0 || col < 0) { // left or below the grid - no moves there
            return 0;
        }

        //recursion base
        if (col == 1 && row == 1) {//at the VERY point of search = target
            return 1;
        }

        return uniquePathsNonDP(row - 1, col) + uniquePathsNonDP(row, col - 1);
    }

    private static int uniquePathsOptimized(int row, int col) {//row and col are grid params
        return uniquePathDP_Helper(row, col, new int[row + 1][col + 1]); //DP array initially filled with zeroes
    }

    private static int uniquePathDP_Helper(int row, int col, int[][] pathsDParray) {
        //recursion base
        if (row < 0 || col < 0) { // left or below grid no value ( 0 )
            return 0;
        }
        //recursion base
        if (row == 1 && col == 1) {//filled cell  ( at the very target)
            return 1;
        }

        if (pathsDParray[row][col] != 0) {// not 0 - ALREADY calculated !!!
            return pathsDParray[row][col];//return since it has been previously calculated already
        }

        //DP recursion, will fill DP array as  it goes
        pathsDParray[row][col] = uniquePathDP_Helper(row - 1, col, pathsDParray) + uniquePathDP_Helper(row, col - 1, pathsDParray);

        return pathsDParray[row][col];//dynamicly calculated res
    }

    //DP
    private static int uniquePathsNoRecursion(int m, int n) {//m and n are grid params
        int[][] paths = new int[m][n];

        //edge rows fill
        for (int row = 0; row < m; row++) {
            paths[row][0] = 1;
        }

        //edge cols fill
        for (int col = 0; col < n; col++) {
            paths[0][col] = 1;
        }

        // System.out.println(Arrays.deepToString(paths));
        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                paths[row][col] = paths[row - 1][col] + paths[row][col - 1];
            }
        }
        
        return paths[m - 1][n - 1];
    }
}
