package leet.code.solutions.BFS;

import java.util.LinkedList;
import java.util.Queue;

/*

https://leetcode.com/problems/rotting-oranges/description/

You are given an m x n grid where each cell can have one of three values:

0 representing an empty cell,
1 representing a fresh orange, or
2 representing a rotten orange.
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.

Example 1:

Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4

Example 2:

Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.

Example 3:

Input: grid = [[0,2]]
Output: 0
Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 10
grid[i][j] is 0, 1, or 2.
 */
public class RottingOranges {

    public static void main(String[] args) {
        int[][] oranges = {
                {2,1,1},
                {1,1,0},
                {0,1,1}
        };

        int minutes = orangesRotting(oranges);
        System.out.println(minutes);
    }

    /*
    Time and Space Complexity

        Time Complexity: O(m * n), where m is the number of rows and n is the number of columns. We visit each cell at most once.

        Space Complexity: O(m * n) in the worst case when all oranges are rotten and stored in the queue.

     */
    private static int orangesRotting(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return 0;

        int freshOranges = 0;

        int ROWS = grid.length;
        int COLS = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();//int[] is array of two coordinates {row, col}

        // Count fresh oranges and add rotten oranges to queue
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {

                if(grid[row][col] == 1) {
                    freshOranges++;
                }else if (grid[row][col] == 2) {
                    queue.add(new int[]{row, col});
                }

            }
        }

        // If no fresh oranges, return 0
        if(freshOranges == 0) return 0;

        int minutesRes = 0;

        int[][] fourDirections = {{-1,0},{1,0},{0,-1},{0,1}};

        // BFS to rot oranges
        while(!queue.isEmpty() && freshOranges > 0) {

            int levelSize = queue.size();

            // Process all oranges at the current level
            for (int i = 0; i < levelSize; i++) {

                int[] currentCell = queue.poll();

                int currentRow = currentCell[0];
                int currentCol = currentCell[1];

                // Check all 4 adjacent cells
                for(int[] direction : fourDirections) {
                    int newRow = currentRow + direction[0];
                    int newCol = currentCol + direction[1];

                    // If adjacent cell is in bounds and has a fresh orange
                    if(newRow >= 0 && newRow < ROWS && newCol >= 0 && newCol < COLS
                            && grid[newRow][newCol] == 1 ) {
                        // Make it rotten
                        queue.add(new int[]{newRow, newCol});
                        grid[newRow][newCol] = 2;
                        freshOranges--;
                    }
                }
            }//for ends

            minutesRes++;
        }

        // If there are still fresh oranges, it's impossible
        return freshOranges == 0 ?  minutesRes : -1 ;
    }


    private static int orangesRotting2(int[][] grid) {
        int ROWS = grid.length;
        int COLS = grid[0].length;

        int minutes = 0;

        Queue<int[]> queue = new LinkedList<>();

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (grid[row][col] == 2) {//rotten cell
                    queue.add(new int[]{row, col});
                }
            }
        }

        int[][] dirs = {{-1,0},{1,0},{0,1},{0,-1}};

        while (!queue.isEmpty()) {

            int qSize = queue.size();

            boolean isRotten = false;

            for (int i = 0; i < qSize; i++) {

                int[] rottenCell = queue.poll();

                int row = rottenCell[0];
                int col = rottenCell[1];

                for (int[] dir : dirs) {

                    int newRow = row + dir[0];
                    int newCol = col + dir[1];

                    if(newRow >=0 && newRow < ROWS && newCol >=0 && newCol < COLS//within boundaries
                            && grid[newRow][newCol] == 1) {//and fresh orange in this new cell

                        grid[newRow][newCol] = 2;//mark as rotten

                        isRotten = true;//set flag

                        queue.add(new int[]{newRow, newCol});

                    }
                }
            }

            if(isRotten){
                minutes++;
            }

        }

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {

                if (grid[row][col] == 1) {//some cell STILL has FRESH orange
                    return -1;//fail fast
                }

            }
        }
        return minutes ;
    }
}
