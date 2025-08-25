package leet.code.solutions.dynamic_programming;

/*
931

https://leetcode.com/problems/minimum-falling-path-sum/description/

Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.

A falling path starts at any element in the first row and chooses the element in the next row that is either directly below or diagonally left/right. Specifically,
the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).

Example 1:

Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
Output: 13
Explanation: There are two falling paths with a minimum sum as shown.
 */
public class MaxFallingSum {

    public static void main(String[] args) {
        int[][]  matrix = {{2,1,3},
                {6,5,4},
                {7,8,9}
        };

        int minFalling =  minFallingPathSum(matrix);
        System.out.println(minFalling);
    }


    //O( n ^ 2)
    //O( n ^ 2)
    private static int minFallingPathSum(int[][] matrix) {

        int ROWS = matrix.length;
        int COLS = matrix[0].length;

        int minPathSum = Integer.MAX_VALUE;

        int[] firstRow = matrix[0];

        Integer[][] DP = new Integer[ROWS][COLS];

        for(int col = 0 ; col < firstRow.length ; col++) {

            int row = 0;//we always use cols of zero row

            int fallingSUm = dfs(matrix, row, col, DP);

            minPathSum = Math.min(minPathSum, fallingSUm);
        }

        return minPathSum;
    }

    private static int dfs(int[][] matrix, int row,  int col, Integer[][] DP) {

        int ROWS = matrix.length;
        int COLS = matrix[0].length;

        if(col < 0 || col >= COLS ) {//BASE - cols invalid

            return Integer.MAX_VALUE;
        }

        if(row == ROWS - 1  ) {//BASE last row

            return matrix[row][col];//just pass value in the cell , no more to go to recur

        }

        if(DP[row][col] != null) {//DP hit
            return DP[row][col];
        }

        int left  = dfs(matrix, row + 1, col -1, DP);
        int down =  dfs(matrix, row + 1 , col , DP);
        int right = dfs(matrix, row + 1, col +1, DP);

        DP [row][col] =  matrix[row][col] // curr
                + // plus min of 3 directions
                Math.min( left,
                 Math.min(down, right ));

        return DP[row][col];
    }
}