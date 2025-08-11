package leet.code.solutions.BFS;

import java.util.LinkedList;
import java.util.Queue;

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


        int result1_binary = maximumMinimumPath_BinarySearch(grid1);
        System.out.println(result1_binary);

        // Both should return 4
        assert result1_binary == 4 : "Binary search failed for test case 1";

        // Test case 2
        int[][] grid2 = {
                {2, 2, 1, 2, 2, 2},
                {1, 2, 2, 2, 1, 2}
        };

        int result2_binary = maximumMinimumPath_BinarySearch(grid2);
        System.out.println(result2_binary);

        // Both should return 2
        assert result2_binary == 2 : "Binary search failed for test case 2";
    }

    /**
     * Solution 2: Binary Search + BFS
     *
     * Time Complexity: O(m*n * log(max_value)) where max_value is largest grid value
     * Space Complexity: O(m*n) for BFS queue and visited array
     *
     * Key idea: Binary search on answer + BFS to check if path exists
     */
    private static int maximumMinimumPath_BinarySearch(int[][] grid) {
        // Binary search bounds
        int left = 1;  // Minimum possible answer
        int right = findMaxValue(grid);  // Maximum possible answer

        int result = 0;

        // Binary search on the answer
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Check if we can reach destination with minimum value >= mid
            if (canReachDestination(grid, mid)) {
                result = mid;          // This value works, try for higher
                left = mid + 1;
            } else {
                right = mid - 1;       // This value doesn't work, try lower
            }
        }

        return result;
    }

    /**
     * Helper method: Check if we can reach destination using only cells >= minValue
     * Uses BFS to explore all reachable cells
     */
    private static boolean canReachDestination(int[][] grid, int minValue) {
        int ROWS = grid.length;
        int COLS = grid[0].length;

        // If starting cell doesn't meet minimum requirement, impossible
        if (grid[0][0] < minValue) {
            return false;
        }

        // BFS setup
        Queue<int[]> queue = new LinkedList<>();//int[] is {row, col}
        boolean[][] visited = new boolean[ROWS][COLS];
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // Start BFS from top-left cell
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] currentCellFromQ = queue.poll();
            int row = currentCellFromQ[0];
            int col = currentCellFromQ[1];

            // Check if we reached destination
            if (row == ROWS - 1 && col == COLS - 1) {
                return true;//exit fast
            }

            // Explore all 4 neighbors
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Check if neighbor is valid, unvisited, and meets minimum requirement
                if (isValid(newRow, newCol, ROWS, COLS) &&
                        !visited[newRow][newCol] &&
                        grid[newRow][newCol] >= minValue) {

                    visited[newRow][newCol] = true;
                    queue.offer(new int[]{newRow, newCol});
                }
            }//for end
        }//while end

        return false; // Destination not reachable
    }

    /**
     * Helper method: Check if coordinates are within grid bounds
     */
    private  static boolean isValid(int row, int col, int rows, int cols) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    /**
     * Helper method: Find maximum value in the grid for binary search upper bound
     */
    private static int findMaxValue(int[][] grid) {
        int maxVal = 0;
        for (int[] row : grid) {
            for (int val : row) {
                maxVal = Math.max(maxVal, val);
            }
        }
        return maxVal;
    }
}
