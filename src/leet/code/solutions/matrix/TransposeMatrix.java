package leet.code.solutions.matrix;

import java.util.Arrays;

/*
https://leetcode.com/problems/transpose-matrix/description/

Given a 2D integer array matrix, return the transpose of matrix.
The transpose of a matrix is the matrix flipped over its main diagonal, switching the matrix's row and column indices.

Example 1:

Input: matrix =
[[1,2,3],
[4,5,6],
[7,8,9]]

Output:
[[1,4,7],
[2,5,8],
[3,6,9]]
Example 2:

Input: matrix =
[[1,2,3],
[4,5,6]]

Output:
[[1,4],
[2,5],
[3,6]]


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 1000
1 <= m * n <= 105
-109 <= matrix[i][j] <= 109
 */
public class TransposeMatrix {

    public static void main(String[] args) {
        int[][] grid = {
                {1,2,3},
                {4,5,6}
               // {7,8,9}// even vs uneven rows cols
        };

        int [][] transposed = transpose(grid);
        System.out.println(Arrays.deepToString(transposed));
    }


    private static int[][] transpose(int[][] matrix) {
        if( matrix == null || matrix.length == 0 ) return matrix;

        int ROWS = matrix.length;
        int COLS = matrix[0].length;

        int[][] transposed = new int[COLS][ROWS];//mind swapped COLS vs ROWS in new RESULT matrix

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {

                transposed[col][row] = matrix[row][col];// in res and original order of ROW and COL is swapped

            }
        }
        return transposed;
    }

    private static int[][] transposeMy(int[][] matrix) {
        if( matrix == null || matrix.length == 0 ) return matrix;

        int ROWS = matrix.length;
        int COLS = matrix[0].length;

        int[][] transposed = new int[COLS][ROWS];//mind swapped COLS vs ROWS in new RESULT matrix

        for (int row = 0; row < COLS; row++) {// mind rows iterate COLS times
            for (int col = 0; col < ROWS; col++) {//mind cols iterate ROWS time

                if(row == col){//if same no need to swap - like row =1 and col = 1
                    transposed[row][col] = matrix[row][col];
                }else{
                    transposed[row][col] = matrix[col][row];
                }
            }
        }
        return transposed;
    }

}
