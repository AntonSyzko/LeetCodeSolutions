package leet.code.solutions.dynamic_programming;

/*
https://www.techiedelight.com/find-minimum-cost-reach-last-cell-matrix-first-cell/

Given an M × N matrix of integers where each cell has a cost associated with it,
find the minimum cost to reach the last cell (M-1, N-1) of the matrix from its first cell (0, 0).

 We can only move one unit right or one unit down from any cell, i.e.,

 from cell (i, j), we can move to (i, j+1) or (i+1, j).

For example,
                        { 4, 7, 8, 6, 4 },
                        { 6, 7, 3, 9, 2 },
                        { 3, 8, 1, 2, 4 },
                        { 7, 1, 7, 3, 7 },
                        { 2, 9, 8, 9, 3 }

The highlighted path shows the minimum cost path having a cost of 36.

 */
public class FindMinimumCostToReachLastCellOfMatrix {

    public static void main(String[] args) {
        int[][] cost =
                {
                        { 4, 7, 8, 6, 4 },
                        { 6, 7, 3, 9, 2 },
                        { 3, 8, 1, 2, 4 },
                        { 7, 1, 7, 3, 7 },
                        { 2, 9, 8, 9, 3 }
                };

        System.out.println("The minimum cost is " + findMinCostRecursive(cost));
    }


    // Recursive approach (start to end)
    public static int findMinCostRecursive(int[][] cost) {
        if (cost == null || cost.length == 0) {
            return 0;
        }

        int rows = cost.length;
        int cols = cost[0].length;

        return findMinCostRecursiveHelper(cost, 0, 0, rows, cols);
    }

    private static int findMinCostRecursiveHelper(int[][] cost, int row, int col, int ROWS, int COLS) {
        // Base case: reached the destination
        if (row == ROWS - 1 && col == COLS - 1) {
            return cost[row][col];//cost of last cell itself is it's value
        }

        //EDGE locations on grid !!!!!!!!!!!!!!!
        // If we're at the bottom edge, can only move right
        if (row == ROWS - 1) {
            return cost[row][col] + findMinCostRecursiveHelper(cost, row, col + 1, ROWS, COLS);//we can only move RIGHT -> col +1
        }

        // If we're at the right edge, can only move down
        if (col == COLS - 1) {
            return cost[row][col] + findMinCostRecursiveHelper(cost, row + 1, col, ROWS, COLS);// we can only move DOWN -> row + 1
        }

        // Otherwise, find minimum of going right or going down
        int rightCost = findMinCostRecursiveHelper(cost, row, col + 1, ROWS, COLS);// RIGHT
        int downCost = findMinCostRecursiveHelper(cost, row + 1, col, ROWS, COLS);//DOWN

        return cost[row][col] //current cell cost
                +
                Math.min(rightCost, downCost); // MIN of going EITHER right OR down
    }

    // Dynamic Programming approach (much more efficient)
    public static int findMinCostDP(int[][] cost) {
        if (cost == null || cost.length == 0) {
            return 0;
        }

        int rows = cost.length;
        int cols = cost[0].length;

        // DP table to store minimum cost to reach each cell
        int[][] dp = new int[rows][cols];

        // Fill the DP table
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // Include current cell cost
                dp[row][col] = cost[row][col];

                if (row == 0 && col == 0) {
                    // Starting cell, just use its cost
                    continue;
                }

                // For first row, can only come from left
                if (row == 0) {
                    dp[row][col] += dp[row][col - 1];
                }
                // For first column, can only come from above
                else if (col == 0) {
                    dp[row][col] += dp[row - 1][col];
                }
                // Otherwise, choose the minimum cost path
                else {
                    dp[row][col] += Math.min(dp[row - 1][col], dp[row][col - 1]);
                }
            }
        }

        // Return the minimum cost to reach the bottom-right cell
        return dp[rows - 1][cols - 1];
    }
}
