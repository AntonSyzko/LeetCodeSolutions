package leet.code.solutions.matrix;

import java.util.Arrays;

/*
https://leetcode.com/problems/rotate-image/description/

You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

Example 1:

Input: matrix = [[1,2,3],
                [4,5,6],
                [7,8,9]]

Output:
                [[7,4,1],
                [8,5,2],
                [9,6,3]]
Example 2:

Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]

 */
public class RotateImage {

    public static void main(String[] args) {

        int[][] grid = {{1,2,3},
                       {4,5,6},
                       {7,8,9}};

        rotate(grid);

        System.out.println(Arrays.deepToString(grid));
    }

    private static void rotate(int[][] matrix) {
        int len = matrix.length;

        int topRow = 0;
        int lastRow = len - 1;

        //1. swap entire rows top to bottom
        while (topRow < lastRow) {
            int [] temp = matrix[topRow];//entire ROW
            matrix[topRow] = matrix[lastRow];
            matrix[lastRow] = temp;

            topRow++;
            lastRow--;
        }

        //2. swap diagonal cells /
        for (int row = 0; row < len; row++) {
            for (int rowNext = row + 1 ; rowNext < len; rowNext++) {//diagonal

                int temp = matrix[row][rowNext];
                matrix[row][rowNext] = matrix[rowNext][row];
                matrix[rowNext][row] = temp;

            }
        }
    }

    }
