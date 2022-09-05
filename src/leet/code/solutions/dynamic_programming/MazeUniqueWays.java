package leet.code.solutions.dynamic_programming;

import java.util.Arrays;

/*
 0 0 0    there are six ways to get from top left to bottom right
 0 0 0
 0 0 0
 */
public class MazeUniqueWays {

    public static void main(String[] args) {
        int[][] graph = new int[3][3];
        System.out.println(Arrays.deepToString(graph));
        int uniquePathsOfGraph = uniquePathsWithBacktracking(3, 3);
        System.out.println(uniquePathsOfGraph);
    }

    private static int uniquePaths(int row, int col) {
        if (row < 1 || col < 1) {//recursion base
            return 0;
        }

        if (row == 1 && col == 1) {//recursion base
            return 1;
        }

        return uniquePaths(row - 1, col) + uniquePaths(row, col - 1);
    }

    private static int uniquePathsWithBacktracking(int row, int col) {
        return uniqueWaysDP_Helper(row, col, new int[row+1][col+1]);
    }

    private static int uniqueWaysDP_Helper(int row, int col, int[][] DP) {
        if(row <1 || col < 1){//recursion base
            return 0;
        }

        if(row==1 && col == 1){//recursion base
            return 1;
        }

        if(DP[row][col] != 0){//DP already has  it
            return DP[row][col];
        }

        //aggregate to DP
        DP[row][col] = uniqueWaysDP_Helper(row-1, col, DP) + uniqueWaysDP_Helper(row, col-1, DP);

        return DP[row][col];//get from DP by end indexes of search
    }
}
