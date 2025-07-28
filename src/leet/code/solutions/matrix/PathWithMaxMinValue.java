package leet.code.solutions.matrix;

import java.util.PriorityQueue;

/*
LeetCode 1102 - Path With Maximum Minimum Value
Given an m x n integer matrix grid, return the maximum score of a path from the top-left corner (0, 0) to the bottom-right corner (m-1, n-1).
The score of a path is the minimum value along that path. You can move up, down, left, or right.
Example
Input: grid = [[5,4,5],
               [1,2,6],
               [7,4,6]]

Output: 4

Explanation: The path with maximum minimum value is:
5 → 4 → 5
    ↓
    6 → 6
The minimum value along this path is 4.
 */
public class PathWithMaxMinValue {

    public static void main(String[] args) {

        // Test case 1
        int[][] grid1 = {
                {5, 4, 5},
                {1, 2, 6},
                {7, 4, 6}
        };

        int result1_dijkstra = maximumMinimumPath_Dijkstra(grid1);
        System.out.println(result1_dijkstra);
       // int result1_binary = maximumMinimumPath_BinarySearch(grid1);

        // Both should return 4
        assert result1_dijkstra == 4 : "Dijkstra failed for test case 1";
     //   assert result1_binary == 4 : "Binary search failed for test case 1";

        // Test case 2
        int[][] grid2 = {
                {2, 2, 1, 2, 2, 2},
                {1, 2, 2, 2, 1, 2}
        };

        int result2_dijkstra = maximumMinimumPath_Dijkstra(grid2);
        System.out.println(result2_dijkstra);
    }

    /*
      Solution 1: Modified Dijkstra's Algorithm

      Time Complexity: O(mn * log(mn)) where m, n are grid dimensions
      Space Complexity: O(mn) for heap and visited set

      Key idea: Use max-heap to always process the path with highest minimum value first
     */
    private static int maximumMinimumPath_Dijkstra(int[][] grid) {
        int ROWS = grid.length;
        int COLS = grid[0].length;

        // Max-heap: stores [minimum_value_so_far, row, col]
        // Use negative values to simulate max-heap with PriorityQueue (min-heap)
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));

        // Track visited cells to avoid cycles
        boolean[][] visited = new boolean[ROWS][COLS];

        // Directions for 4-directional movement: right, down, left, up
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // Start from top-left corner
        heap.offer(new int[]{grid[0][0], 0, 0});

        while (!heap.isEmpty()) {

            int[] current = heap.poll();

            int currentMin = current[0];

            int row = current[1];
            int col = current[2];

            // Skip if already visited (important for avoiding duplicate processing)
            if (visited[row][col]) {
                continue;
            }

            // Mark current cell as visited
            visited[row][col] = true;

            // If we reached destination, return the minimum value along this path
            if (row == ROWS - 1 && col == COLS - 1) {
                return currentMin;
            }

            // Explore all 4 neighbors
            for (int[] dir : directions) {

                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Check bounds and if not visited
                if (isValid(newRow, newCol, ROWS, COLS) && !visited[newRow][newCol]) {
                    // The minimum value along this new path is the minimum of:
                    // 1. Current path's minimum value
                    // 2. The value of the new cell we're entering
                    int newMin = Math.min(currentMin, grid[newRow][newCol]);
                    heap.offer(new int[]{newMin, newRow, newCol});
                }
            }
        }

        return -1; // Should never reach here for valid input
    }

    /**
     * Helper method: Check if coordinates are within grid bounds
     */
    private static boolean isValid(int row, int col, int ROWS, int COLS) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLS;
    }
}
