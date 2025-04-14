package leet.code.solutions.matrix;

import java.util.Arrays;

public class ReverseMatrixRows {

    public static void main(String[] args) {
        int [][] matrix = {{1,2,3,4},
                {5,6,7,8}};

        reverseRows(matrix);

        System.out.println(Arrays.deepToString(matrix));
    }


    private static void reverseRows(int[][] matrix) {
        int ROWS = matrix.length;
        int COLS = matrix[0].length;


        for (int row = 0; row < ROWS; row++) {

            //we use COLUMNS to run through a ROW - hence only column manipulations needed to change ROW values

            for (int col = 0; col < COLS / 2; col++) {//we need only COLS / 2

                int temp = matrix[row][col];
                matrix[row][col] = matrix[row][COLS - 1 - col];
                matrix[row][COLS - 1 - col] = temp;

            }
        }
    }
}
