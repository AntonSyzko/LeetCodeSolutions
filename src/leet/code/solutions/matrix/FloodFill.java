package leet.code.solutions.matrix;

import java.util.Arrays;

/*
Leetcode 733

An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).

Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.

To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally
to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally
to those pixels (also with the same color as the starting pixel), and so on.
Replace the color of all of the aforementioned pixels with the newColor.

At the end, return the modified image.
 */
public class FloodFill {

    public static void main(String[] args) {
       int[][] image = new int[][]{
           {1,1,1},
           {1,1,0},
           {1,0,1}
       };

        System.out.println(Arrays.deepToString(image));

        floodFill(image, 1,1 ,2);

        System.out.println(Arrays.deepToString(image));
    }

    private static int[][] floodFill(int[][] image, int startRow, int startCol, int newColor) {

        int oldColor = image[startRow][startCol];

        if (oldColor == newColor) {//recursion base case
            return image;
        }


        // 4 directions - {row, col} - always two ways in each direction
        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        image[startRow][startCol] = newColor;//colour current cell with new  colour

        for (int[] direction : directions) {//traverse all 4 directions
            int nextRow = startRow + direction[0];//current row + all rows of 4 directions as we iterate in for loop
            int nextCol = startCol + direction[1];//current col + all columns of 4 directions as we  iterate in for loop

            //check boundaries of the grid
            if (nextRow < 0 || nextRow >= image.length
                || nextCol < 0 || nextCol >= image[0].length) {
                continue;
            }
//not a colour to fill ( already filled or not the colour we have started with and we do not need to colour this cell thus)
            if (image[nextRow][nextCol] != oldColor) {
                continue;
            }
//recursively continue colouring starting from next row and col
            floodFill(image, nextRow, nextCol, newColor);
        }
        
        return image;
    }
}
