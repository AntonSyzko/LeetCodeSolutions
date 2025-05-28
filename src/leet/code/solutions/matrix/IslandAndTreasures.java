package leet.code.solutions.matrix;

import java.util.*;

/*
https://neetcode.io/problems/islands-and-treasure

You are given  m×n 2D grid initialized with these three possible values:

-1 - A water cell that can not be traversed.
0 - A treasure chest.
INF - A land cell that can be traversed. We use the integer 2^31 - 1 = Integer.MAX_VALUE to represent INF.
Fill each land cell with the distance to its nearest treasure chest. If a land cell cannot reach a treasure chest than the value should remain INF.

Assume the grid can only be traversed up, down, left, or right.

Modify the grid in-place.

Example 1:

Input: [
  [Integer.MAX_VALUE,-1,0,Integer.MAX_VALUE],
  [Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,-1],
  [Integer.MAX_VALUE,-1,Integer.MAX_VALUE,-1],
  [0,-1,Integer.MAX_VALUE,Integer.MAX_VALUE]
]

Output: [
  [3,-1,0,1],
  [2,2,1,-1],
  [1,-1,2,-1],
  [0,-1,3,4]
]
Example 2:

Input: [
  [0,-1],
  [Integer.MAX_VALUE,Integer.MAX_VALUE]
]

Output: [
  [0,-1],
  [1,2]
]
Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 100
grid[i][j] is one of {-1, 0, Integer.MAX_VALUE}
 */
public class IslandAndTreasures {

    // Test method to demonstrate the solution
    public static void main(String[] args) {
        int INF = 2147483647;

        int[][] grid = {
                {INF, -1, 0, INF},
                {INF, INF, INF, -1},
                {INF, -1, INF, -1},
                {0, -1, INF, INF}
        };

        System.out.println("Original Grid:");
        printGrid(grid);

        islandsAndTreasure(grid);

        System.out.println("\nAfter BFS:");
        printGrid(grid);
    }

    /*
     Multi Source BFS

    Time and Space Complexity:

            Time Complexity: O(m × n) where m and n are grid dimensions

            Each cell is visited at most once during BFS


            Space Complexity: O(m × n) in the worst case

            Queue can hold up to all cells in extreme cases
            If using visited array: additional O(m × n) space
     */

    /**
     How Multi-Source BFS Works:

     Initialize: Add all treasure chests (value 0) to the queue
     BFS: Process all cells at distance 1, then distance 2, etc.
     Update: Each land cell gets the minimum distance to any treasure chest
     Guarantee: First time we reach a cell gives the shortest distance

     This approach is optimal because it explores all shortest paths simultaneously from all treasure chests, ensuring each land cell gets updated with the minimum possible distance.
     **/

    private static void islandsAndTreasure ( int[][] grid){
            int ROWS = grid.length;
            int COLS = grid[0].length;

            int INF = Integer.MAX_VALUE;

            Queue<int[]> bfsQ = new LinkedList<>();

            for (int row = 0; row < ROWS; row++) {
                for (int col = 0; col < COLS; col++) {

                    if (grid[row][col] == 0) {
                        bfsQ.offer(new int[]{row, col});//fill in Q with positions of 0 cells
                    }

                }
            }

            // Directions: up, down, left, right
            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

            while (!bfsQ.isEmpty()) {//traverse the Q while smth is in Q

                    int[] curr = bfsQ.poll();

                    int rowFromQ = curr[0];
                    int colFromQ = curr[1];

                    for (int[] direction : directions) {//all 4 directions

                        int newRow = rowFromQ + direction[0];
                        int newCol = colFromQ + direction[1];

                        if (newRow >= 0 && newRow < ROWS && newCol >= 0 && newCol < COLS // valid boundaries
                                && // AND
                                grid[newRow][newCol] == INF) { // valid cell - cell with INF

                            grid[newRow][newCol] = grid[rowFromQ][colFromQ] + 1;//pit in new cell increased val  from what is already there

                            bfsQ.offer(new int[]{newRow, newCol});//add new cell( after increment) to q to reprocess

                        }
                    }
               }
        }


    // Alternative implementation with separate visited array (if you prefer)
    private static void islandsAndTreasureWithVisited(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return;
        }

        int ROWS = grid.length;
        int COLS = grid[0].length;
        int INF = 2147483647;

        boolean[][] visited = new boolean[ROWS][COLS];
        Queue<int[]> queue = new LinkedList<>();

        // Add all treasure chests to queue and mark as visited
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (grid[row][col] == 0) {
                    queue.offer(new int[]{row, col});
                    visited[row][col] = true;
                }
            }
        }

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow >= 0 && newRow < ROWS &&
                        newCol >= 0 && newCol < COLS && // valid boundaries
                        !visited[newRow][newCol] && // not yet visisted
                        grid[newRow][newCol] == INF) {// valid cell with INF

                    grid[newRow][newCol] = grid[row][col] + 1;//update with incremented from prev
                    visited[newRow][newCol] = true;//mark visisted
                    queue.offer(new int[]{newRow, newCol});// add to Q
                }
            }
        }
    }

    private static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            for (int cell : row) {
                if (cell == 2147483647) {
                    System.out.print("INF ");
                } else {
                    System.out.printf("%3d ", cell);
                }
            }
            System.out.println();
        }
    }



}
