package leet.code.solutions.matrix;

import java.util.HashSet;
import java.util.Set;

/*
https://leetcode.com/problems/set-matrix-zeroes/

Given an m x n integer matrix , if an element is 0, set its entire row and column to 0's.

You must do it in place.



Example 1:


Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]


 Thoughts about This Problem

This problem can solve by following 4 steps:

• check if first row and column are zero or not

• mark zeros on first row and column

• use mark to set elements

• set first column and row by using marks in step 1

 */
public class SetMatrixZeroes {

    public static void main(String[] args) {

    }

    private static void setZeroes(int[][] matrix) {
        int ROWS = matrix.length;
        int COLS = matrix[0].length;

        boolean firstRowZero = false;
        boolean firstColumnZero = false;

            //set first  column zeroes or not
        for(int row = 0; row < ROWS; row++){

            if(matrix[row][0] == 0){//zero column here
                firstColumnZero = true;
                break;
            }

        }

        //set first row  zeroes or not
        for(int i = 0; i < COLS; i++){

            if(matrix[0][i] == 0){//zero row here
                firstRowZero = true;
                break;
            }

        }

       //mark zeros on first row and column
        for(int row = 1; row < ROWS; row++){//starting from 1 row and col !!!!!!!!!!!!
            for(int col = 1; col < COLS; col++){

                if(matrix[row][col] == 0){
                    matrix[row][0] = 0;
                    matrix[0][col] = 0;
                }

            }
        }

       //use mark to set elements
        for(int row = 1; row < ROWS; row++){
            for(int col = 1; col < COLS; col++){
                if(matrix[row][0] == 0 || matrix[0][col] == 0){
                    matrix[row][col] = 0;
                }
            }
        }

        //set first column and row
        if(firstColumnZero){
            for(int i=0; i<ROWS; i++)
                matrix[i][0] = 0;
        }
        if(firstRowZero){
            for(int i=0; i<COLS; i++)
                matrix[0][i] = 0;
        }
    }

    //time O(m*n).
    //space O(m * n )
    private static void setZeroesHashSet(int[][] matrix) {
        int ROWS = matrix.length;
        int COLS = matrix[0].length;

        // Use HashSets to keep track of rows and columns that need to be zeroed
        Set<Integer> zeroRows = new HashSet<>();
        Set<Integer> zeroCols = new HashSet<>();

        // First pass: Mark rows and columns that contain zeros
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (matrix[i][j] == 0) {
                    zeroRows.add(i);
                    zeroCols.add(j);
                }
            }
        }

        // Second pass: Set rows to zero
        for (int row : zeroRows) {//itrerate ROS set
            for (int j = 0; j < COLS; j++) {//for each column of that row
                matrix[row][j] = 0;
            }
        }

        // Third pass: Set columns to zero
        for (int col : zeroCols) {//iterate set of cols
            for (int i = 0; i < ROWS; i++) {//for each row of that column
                matrix[i][col] = 0;
            }
        }
    }
}
