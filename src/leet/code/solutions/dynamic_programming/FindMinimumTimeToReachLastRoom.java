package leet.code.solutions.dynamic_programming;

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
    }


    public static int minTimeToReach(int[][] moveTime) {
        if (moveTime == null || moveTime.length == 0 || moveTime[0].length == 0) {
            return 0;
        }

        int rows = moveTime.length;
        int cols = moveTime[0].length;

        // Memoization array to avoid recalculating the same states
        Integer[][] memo = new Integer[rows][cols];

        return minTimeRecursive(moveTime, 0, 0, rows, cols, memo, moveTime[0][0]);
    }

    private static int minTimeRecursive(int[][] moveTime, int row, int col, int rows, int cols, Integer[][] memo, int currentTime) {
        // Base case: reached the destination
        if (row == rows - 1 && col == cols - 1) {
            return currentTime;
        }

        // If we've already computed this state, return the cached result
        if (memo[row][col] != null) {
            return memo[row][col];
        }

        // Define possible directions: right, down, left, up
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        int minTime = Integer.MAX_VALUE;

        // Try all possible directions
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            // Check if the new position is valid
            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                // Calculate new time: max(current time + 1, minimum moveTime for the new room)
                int timeAfterMove = currentTime + 1;

                int timeToEnterNewRoom = Math.max(timeAfterMove, moveTime[newRow][newCol]);

                // Recursive call to find minimum time from new position
                int timeFromNewPos = minTimeRecursive(moveTime, newRow, newCol, rows, cols, memo, timeToEnterNewRoom);

                // Update minimum time
                minTime = Math.min(minTime, timeFromNewPos);
            }
        }

        // Cache the result
        memo[row][col] = minTime;

        return minTime;
    }
}
