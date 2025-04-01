package leet.code.solutions.blind75;

/*
https://leetcode.com/problems/number-of-islands/

Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.



Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.

 */
public class NumberOfIslands {

    public static void main(String[] args) {
       char [][] grid = {
               {'0','0','0'},
               {'0','1','0'},
               {'0','0','0'}
       };

       int numOfIslands =  numIslands(grid);
       System.out.println(numOfIslands);
    }


    private static int numIslands(char[][] grid) {

        if(grid==null || grid.length==0) return 0;

        int numberOfIslands = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    numberOfIslands++;

                    dfs(grid, i, j);
                }
            }
        }

        return numberOfIslands;
    }

    private static void dfs(char[][] grid, int row, int col){
         int ROWS = grid.length;
         int COLS = grid[0].length;

         if(row < 0  || row >= ROWS || col < 0  || col >= COLS // within boundaries
         || grid[row][col] == '0') {// or is not island
             return;
         }

         grid[row][col] = '0'; // mark as non asland as we have checked it already in this recursive iteration

         dfs(grid, row - 1, col);
         dfs(grid, row + 1, col);
         dfs(grid, row , col + 1);
         dfs(grid, row , col - 1 );
    }

}
