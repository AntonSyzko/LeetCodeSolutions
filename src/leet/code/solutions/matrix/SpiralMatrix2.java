package leet.code.solutions.matrix;

import java.util.Arrays;

/*
https://leetcode.com/problems/spiral-matrix-ii/

Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.

Example 1:
Input: n = 3
Output: [[1,2,3],[8,9,4],[7,6,5]]

Example 2:
Input: n = 1
Output: [[1]]

Constraints:

1 <= n <= 20
 */
public class SpiralMatrix2 {

    public static void main(String[] args) {
         int[][] res = generateMatrix(3);
        System.out.println(Arrays.deepToString(res));
    }


    private static int[][] generateMatrix(int n) {
         int[][] matrix = new int[n][n];

         int ROWS = matrix.length;
         int COLS = matrix[0].length;

         int startRow = 0;
         int startCol = 0;
         int endRow = ROWS - 1;
         int endCol = COLS - 1;

         int numberToInsert = 1;

         while(startRow <= endRow && startCol <= endCol){

             //right
             for(int i = startCol; i <= endCol; i++){
                 matrix[startRow][i] = numberToInsert++;
             }
             startRow++;

             //down
             for(int i = startRow; i <= endRow; i++){
                 matrix[i][endCol] = numberToInsert++;
             }
             endCol--;

            //left
             if(startRow <= endRow ){
                 for(int i = endCol; i >= startCol; i--){
                     matrix[endRow][i] = numberToInsert++;
                 }
                 endRow--;
             }

             //up
             if(startCol <= endCol ){
                 for(int i = endRow; i >= startRow; i--){
                     matrix[i][startCol] = numberToInsert++;
                 }
                 startCol++;
             }
         }

         return matrix;
    }
}
