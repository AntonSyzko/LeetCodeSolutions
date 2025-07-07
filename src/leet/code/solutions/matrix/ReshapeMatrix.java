package leet.code.solutions.matrix;

import java.util.Arrays;

/*
566

https://leetcode.com/problems/reshape-the-matrix/description/

In MATLAB, there is a handy function called reshape which can reshape an m x n matrix into a new one with a different size r x c keeping its original data.

You are given an m x n matrix mat and two integers r and c representing the number of rows and the number of columns of the wanted reshaped matrix.

The reshaped matrix should be filled with all the elements of the original matrix in the same row-traversing order as they were.

If the reshape operation with given parameters is possible and legal, output the new reshaped matrix; Otherwise, output the original matrix.

Example 1:

Input: mat = [[1,2],[3,4]], r = 1, c = 4
Output: [[1,2,3,4]]
Example 2:

Input: mat = [[1,2],[3,4]], r = 2, c = 4
Output: [[1,2],[3,4]]

Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 100
        -1000 <= mat[i][j] <= 1000
        1 <= r, c <= 300
 */
public class ReshapeMatrix {

    public static void main(String[] args) {
        int[][] mat = {{1,2},
                        {3,4}};

        System.out.println(Arrays.deepToString(mat));

        int[][] reshaped = matrixReshape(mat, 2, 4);
        System.out.println(Arrays.deepToString(reshaped));
    }

    private static int[][] matrixReshape(int[][] mat, int row, int col) {
        int ROWS = mat.length;
        int COLS = mat[0].length;

        if((ROWS * COLS) != (row * col)){
            return mat;// not reshepeable, size is different
        }

        int[][] reshaped = new int[row][col];

        int newInsertRow = 0;
        int newInsertCol = 0;

        for (int r = 0; r < mat.length; r++) {
            for (int c = 0; c < mat[0].length; c++) {

                    reshaped[newInsertRow][newInsertCol] = mat[r][c];

                    newInsertCol++;//within same row insert in next COLUMN until COLs are exceeded , then move to next ROW

                    if(newInsertCol == col){//cols exceeded new allowed

                        newInsertCol = 0;//reset cols back to 0

                        newInsertRow++;//increase new row as we start inserting from COL=0 but from NEW ROW

                    }
            }
        }

        return reshaped;
    }
}