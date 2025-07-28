package leet.code.solutions.matrix;

/*
https://leetcode.com/problems/number-of-islands/description/

Given an m x n 2D binary grid  which represents a map of '1's (land) and '0's (water), return the number of islands.

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

        char[][] grid = {
                {'0','1','0'},
                {'0','0','0'},
                {'0','1','1'},

        };

        System.out.println("\r\n\t number of islands = " + numIslands(grid));
    }

    public static int numIslands(char[][] grid) {

        if(grid == null || grid.length == 0){
            return 0;
        }

        int islandsCount = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {

                if(grid[row][col] == '1'){//until first island met -> after it will be met only when completely new isolated island is met again

                    islandsCount++;//no matter how many '1' - it is one island ,and it is already counted in

                    traverseGridBFS(grid, row, col);//traversal will stop at boundaries of the grid or meeting '0'

                }
            }
        }
        return islandsCount;
    }

    private static void traverseGridBFS(char[][] grid, int row, int col) {
        int ROWS = grid.length;
        int COLS = grid[0].length;

        //BASE case
        if(row  < 0 || row >= ROWS //row boundaries
                || col < 0 || col >= COLS //  col boundaries
                || grid[row][col] == '0'){// reached end of island - dont proceed
            return;
        }

        grid[row][col] = '0';//mark current as 0 as we have counted it in parent call

        traverseGridBFS(grid, row - 1, col);//up
        traverseGridBFS(grid, row + 1, col);//down
        traverseGridBFS(grid, row , col - 1 );//left
        traverseGridBFS(grid, row , col + 1 );//right
    }
}
