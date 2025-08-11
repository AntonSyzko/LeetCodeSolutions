package leet.code.solutions.dynamic_programming;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
https://leetcode.com/problems/find-minimum-time-to-reach-last-room-i/description/

There is a dungeon with n x m rooms arranged as a grid.

You are given a 2D array moveTime of size n x m, where moveTime[i][j] represents the minimum time in seconds when you can start moving to that room.
You start from the room (0, 0) at time t = 0 and can move to an adjacent room.
 Moving between adjacent rooms takes exactly one second.

Return the minimum time to reach the room (n - 1, m - 1).

Two rooms are adjacent if they share a common wall, either horizontally or vertically.



Example 1:

Input: moveTime = [[0,4],[4,4]]

Output: 6

Explanation:

The minimum time required is 6 seconds.

At time t == 4, move from room (0, 0) to room (1, 0) in one second.
At time t == 5, move from room (1, 0) to room (1, 1) in one second.
Example 2:

Input: moveTime = [[0,0,0],[0,0,0]]

Output: 3

Explanation:

The minimum time required is 3 seconds.

At time t == 0, move from room (0, 0) to room (1, 0) in one second.
At time t == 1, move from room (1, 0) to room (1, 1) in one second.
At time t == 2, move from room (1, 1) to room (1, 2) in one second.
Example 3:

Input: moveTime = [[0,1],[1,2]]

Output: 3


 */
public class FindMinimumTimeToReachLastRoom {

    // For testing
    public static void main(String[] args) {
        // Example 1
        int[][] moveTime1 = {{0, 4}, {4, 4}};
        System.out.println("Example 1: " + minTimeToReach(moveTime1)); // Expected: 6

        // Example 2
        int[][] moveTime2 = {{0, 0, 0}, {0, 0, 0}};
        System.out.println("Example 2: " + minTimeToReach(moveTime2)); // Expected: 3

        // Example 3
        int[][] moveTime3 = {{0, 1}, {1, 2}};
        System.out.println("Example 3: " + minTimeToReach(moveTime3)); // Expected: 3

        // Test the examples
        System.out.println(minTimeToReach(new int[][]{{0,4},{4,4}})); // Output: 6
        System.out.println(minTimeToReach(new int[][]{{0,1},{2,3}})); // Output: 3
        System.out.println(minTimeToReach(new int[][]{{0,0,0},{0,0,0}})); // Output: 2
    }


    public static int minTimeToReach(int[][] moveTime) {
        int rows = moveTime.length;
        int cols = moveTime[0].length;

        // Can't move from start? Return -1
        if (moveTime[0][1] > 1 && moveTime[1][0] > 1) {
            return -1;
        }

        // Memo: state -> minimum time to reach destination
        Map<String, Integer> memo = new HashMap<>();

        // Track current path to prevent cycles
        Set<String> visiting = new HashSet<>();

        int startRow = 0;
        int startCol = 0;
        int currentTime = 0;

        return dfs(moveTime, startRow, startCol, currentTime, rows, cols, memo, visiting);
    }

    private static int dfs(int[][] moveTime, int row, int col, int currentTime, int ROWS, int COLS, Map<String, Integer> memo, Set<String> visiting) {

        // Base case: reached destination
        if (row == ROWS - 1 && col == COLS - 1) {
            return currentTime;
        }

        // Calculate actual time we can be at this position
        int actualTime = currentTime;

        if (currentTime < moveTime[row][col]) {
            int waitTime = moveTime[row][col] - currentTime;
            actualTime = moveTime[row][col] + (waitTime % 2);
        }

        // Create state key for this exact situation
        String state = row + "," + col + "," + actualTime;

        // Cycle detection: if we're currently exploring this state, return MAX to avoid it
        if (visiting.contains(state)) {
            return Integer.MAX_VALUE;
        }

        // Check memo
        if (memo.containsKey(state)) {
            return memo.get(state);
        }

        // Mark as currently visiting
        visiting.add(state);

        int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        int minTime = Integer.MAX_VALUE;

        // Try all 4 directions
        for (int[] dir : dirs) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if (newRow >= 0 && newRow < ROWS && newCol >= 0 && newCol < COLS) {

                int result = dfs(moveTime, newRow, newCol, actualTime + 1, ROWS, COLS, memo, visiting);

                if (result != Integer.MAX_VALUE) {
                    minTime = Math.min(minTime, result);
                }
            }
        }

        // Unmark visiting and cache result
        visiting.remove(state);
        memo.put(state, minTime);
        return minTime;
    }
}
