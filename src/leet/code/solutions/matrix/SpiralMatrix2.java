package leet.code.solutions.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    private static List<Integer> spiralOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return Collections.emptyList();
        }

        List<Integer> result = new ArrayList<>();

        int ROWS = matrix.length;
        int COLS = matrix[0].length;

        int topRow = 0;
        int bottomRow = ROWS - 1;

        int leftCol = 0;
        int rightCol = COLS - 1;

        int matrixSize = ROWS * COLS;

        while (result.size() < matrixSize ){//while result set is less than matrix boundaries

            //right
            for (int col = leftCol; col <= rightCol && result.size() < matrixSize; col++) {
                result.add(matrix[topRow][col]);
            }
            topRow++;

            //down
            for(int row = topRow; row <= bottomRow && result.size() < matrixSize; row++){
                result.add(matrix[row][rightCol]);
            }
            rightCol--;

            //left

            for(int col = rightCol; col >= leftCol && result.size() < matrixSize; col--){
                result.add(matrix[bottomRow][col]);
            }
            bottomRow--;

            //up

            for(int row = bottomRow; row >= topRow && result.size() < matrixSize; row--){
                result.add(matrix[row][leftCol]);
            }
            leftCol++;

        }
        return result;
    }

}
