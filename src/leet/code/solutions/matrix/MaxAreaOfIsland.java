package leet.code.solutions.matrix;

/*
https://leetcode.com/problems/max-area-of-island/

You are given an m x n binary matrix grid. An island is a group of 1's (representing land)
connected 4-directionally (horizontal or vertical.)
You may assume all four edges of the grid are surrounded by water.

The area of an island is the number of cells with a value 1 in the island.

Return the maximum area of an island in grid. If there is no island, return 0.


Example 1:


Input: grid =   [[0,0,1,0,0,0,0,1,0,0,0,0,0],
                [0,0,0,0,0,0,0,1,1,1,0,0,0],
                [0,1,1,0,1,0,0,0,0,0,0,0,0],
                [0,1,0,0,1,1,0,0,1,0,1,0,0],
                [0,1,0,0,1,1,0,0,1,1,1,0,0],
                [0,0,0,0,0,0,0,0,0,0,1,0,0],
                [0,0,0,0,0,0,0,1,1,1,0,0,0],
                [0,0,0,0,0,0,0,1,1,0,0,0,0]]
Output: 6
Explanation: The answer is not 11, because the island must be connected 4-directionally.

Example 2:

Input: grid = [[0,0,0,0,0,0,0,0]]
Output: 0

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 50
grid[i][j] is either 0 or 1.

 */
public class MaxAreaOfIsland {

    public static void main(String[] args) {
        int[][] grid = {
                {1,1,0,0},
                {1,0,0,0},
                {0,0,1,1},//this island max area = 4
                {0,0,1,1}
        };

        int maxArea = maxAreaOfIsland(grid);
        System.out.println(maxArea);
    }


    private static int maxAreaOfIsland(int[][] grid) {

        if(grid==null || grid.length==0) return 0;

        int maxArea = 0;

        int ROWS = grid.length;
        int COLS = grid[0].length;

        boolean[][] visited = new boolean[ROWS][COLS];

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {

                if (grid[row][col] == 1 && !visited[row][col]) {
                    int area = calculateAreaDFS(grid, visited, row, col);
                    maxArea = Math.max(area, maxArea);
                }

            }
        }

        return maxArea;
    }

    private static int calculateAreaDFS(int[][] grid, boolean[][] visited,int  row, int col) {
        int ROWS = grid.length;
        int COLS = grid[0].length;

        if(!isValidCell(grid, row, col, ROWS, COLS, visited)){
            return 0;
        }

        visited[row][col] = true;

        int area = 1;

        //up
        area +=  calculateAreaDFS(grid, visited, row+ 1, col)  ;

        //down
        area +=    calculateAreaDFS(grid,  visited, row - 1, col) ;

        //left
        area +=  calculateAreaDFS(grid, visited,   row , col - 1) ;

        //right
        area +=  calculateAreaDFS(grid,  visited, row , col + 1) ;

        return area;
    }

    private static boolean isValidCell(int[][] grid, int row, int col, int ROWS, int COLS, boolean[][] visited) {

        if(row < 0 || row >= ROWS
                ||  col < 0 || col >= COLS
                || visited[row][col]
                || grid[row][col] == 0) {

            return false;

        }

        return true;

    }
}
