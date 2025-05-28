package leet.code.solutions.matrix;

import java.util.*;

/*
https://neetcode.io/problems/pacific-atlantic-water-flow

You are given a rectangular island heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).

The islands borders the Pacific Ocean from the top and left sides, and borders the Atlantic Ocean from the bottom and right sides.

Water can flow in four directions (up, down, left, or right) from a cell to a neighboring cell with height equal or lower. Water can also flow into the ocean from cells adjacent to the ocean.

Find all cells where water can flow from that cell to both the Pacific and Atlantic oceans. Return it as a 2D list where each element is a list [r, c] representing the row and column of the cell. You may return the answer in any order.

Example 1:

Input: heights = [
  [4,2,7,3,4],
  [7,4,6,4,7],
  [6,3,5,3,6]
]

Output: [[0,2],[0,4],[1,0],[1,1],[1,2],[1,3],[1,4],[2,0]]
Example 2:

Input: heights = [[1],[1]]

Output: [[0,0],[0,1]]
Constraints:

1 <= heights.length, heights[r].length <= 100
0 <= heights[r][c] <= 1000
 */
public class PacificAndAtlantic {
    public static void main(String[] args) {
        int[][] heights = {
                {4, 2, 7, 3, 4},
                {7, 4, 6, 4, 7},
                {6, 3, 5, 3, 6}
        };

        System.out.println("DFS Result: " + pacificAtlantic(heights));

        // Reset for BFS test
        int[][] heights2 = {
                {4, 2, 7, 3, 4},
                {7, 4, 6, 4, 7},
                {6, 3, 5, 3, 6}
        };

        System.out.println("BFS Result: " + pacificAtlanticBFS(heights2));
    }
    /*
    Time and Space Complexity:

            Time Complexity: O(m × n)

            where m and n are grid dimensions
            Each cell is visited at most once for each ocean


            Space Complexity: O(m × n)

            Two boolean arrays of size m × n
            Recursion stack depth up to m × n in worst case
     */

    private static List<List<Integer>> pacificAtlantic(int[][] heights) {
        if(heights.length == 0) return new ArrayList<List<Integer>>();

        List<List<Integer>> res = new ArrayList<>();

        int ROWS = heights.length;
        int COLS = heights[0].length;

        boolean[][] pacificReachable = new boolean[ROWS][COLS];
        boolean[][] atlanticReachable = new boolean[ROWS][COLS];

        //PACIFIC
        for (int col = 0; col < COLS; col++) {//all cols of first row
            dfs(heights, pacificReachable, 0, col, heights[0][col]);
        }

        for (int row = 0; row < ROWS; row++) {//all rows of first col
            dfs(heights, pacificReachable, row, 0, heights[row][0]);
        }

        //ATLANTIC
        for (int col = 0; col < COLS; col++) {//all cols of last row
            dfs(heights, atlanticReachable, ROWS-1, col, heights[0][col]);
        }

        for (int row = 0; row < ROWS; row++) {//all rows of last col
            dfs(heights, atlanticReachable, row, COLS-1, heights[row][0]);
        }

        // Find intersection: cells reachable by both oceans
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if(pacificReachable[row][col] && atlanticReachable[row][col]){
                    res.add(List.of(row,col));
                }
            }
        }

        return res;
    }


    private static void dfs(int[][] heights, boolean[][] reacheable, int row, int col, int prevHeight) {
        if(row < 0 || row >= heights.length ||  col < 0 || col >= heights[0].length //out of  bounds
        || reacheable[row][col] // already Reachable
        || heights[row][col] < prevHeight ) { // height is less , we need higher for water to flow  down
            return;
        }

        reacheable[row][col] = true;//we succeeded reaching this cell

        dfs(heights, reacheable, row+1, col, heights[row][col]);//up
        dfs(heights, reacheable, row-1, col, heights[row][col]);//down
        dfs(heights, reacheable, row, col+1, heights[row][col]);//right
        dfs(heights, reacheable, row, col-1, heights[row][col]);//left
    }

    // Alternative BFS implementation
    private static List<List<Integer>> pacificAtlanticBFS(int[][] heights) {
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return new ArrayList<>();
        }

        int ROWS = heights.length;
        int COLS = heights[0].length;

        boolean[][] pacificReachable = new boolean[ROWS][COLS];
        boolean[][] atlanticReachable = new boolean[ROWS][COLS];

        Queue<int[]> pacificQueue = new LinkedList<>();
        Queue<int[]> atlanticQueue = new LinkedList<>();

        // Add Pacific border cells
        for (int col = 0; col < COLS; col++) {
            pacificQueue.offer(new int[]{0, col});
            pacificReachable[0][col] = true;
        }
        for (int row = 1; row < ROWS; row++) { // Start from 1 to avoid duplicate corner//can be ignored
            pacificQueue.offer(new int[]{row, 0});
            pacificReachable[row][0] = true;
        }

        // Add Atlantic border cells
        for (int col = 0; col < COLS; col++) {
            atlanticQueue.offer(new int[]{ROWS - 1, col});
            atlanticReachable[ROWS - 1][col] = true;
        }
        for (int row = 0; row < ROWS - 1; row++) { // End before ROWS-1 to avoid duplicate corner//can be ignored
            atlanticQueue.offer(new int[]{row, COLS - 1});
            atlanticReachable[row][COLS - 1] = true;
        }

        // BFS from both oceans
        bfs(heights, pacificQueue, pacificReachable);
        bfs(heights, atlanticQueue, atlanticReachable);

        // Find intersection
        List<List<Integer>> result = new ArrayList<>();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (pacificReachable[row][col] && atlanticReachable[row][col]) {
                    result.add(Arrays.asList(row, col));
                }
            }
        }

        return result;
    }

    private static void bfs(int[][] heights, Queue<int[]> queue, boolean[][] reachable) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow >= 0 && newRow < heights.length &&
                        newCol >= 0 && newCol < heights[0].length &&
                        !reachable[newRow][newCol] &&
                        heights[newRow][newCol] >= heights[row][col]) {

                    reachable[newRow][newCol] = true;
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }
    }
}
