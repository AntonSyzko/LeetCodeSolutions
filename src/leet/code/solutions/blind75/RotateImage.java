package leet.code.solutions.blind75;

/*
https://leetcode.com/problems/rotate-image/

You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.



Example 1:


Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]
Example 2:


Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]


Constraints:

n == matrix.length == matrix[i].length
1 <= n <= 20
-1000 <= matrix[i][j] <= 1000
 */

public class RotateImage {

    public static void main(String[] args) {
        int[][] image = {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};

        rotate(image);
    }

/*
        Key Insight
        A 90-degree clockwise rotation can be broken down into two simpler operations:

        Transpose the matrix (swap rows with columns)
        Reverse each row

        Step-by-Step Approach
        Step 1: Transpose the matrix

        For each position (i,j) where i ≤ j, swap matrix[i][j] with matrix[j][i]
        We only process elements where i ≤ j to avoid swapping elements twice

        Step 2: Reverse each row

        For each row i, reverse all elements by swapping matrix[i][j] with matrix[i][n-1-j]
        We only need to iterate through the first half of each row
 */

    /*
    time O(N^2)
    space O(1)
     */
    private static void rotate(int[][] matrix) {
            int len = matrix.length;

            // Step 1: Transpose the matrix (swap rows with columns)
            for (int row = 0; row < len; row++) {
                for (int col = row; col < len; col++) {
                    // Swap matrix[row][col] with matrix[col][row]
                    int temp = matrix[row][col];
                    matrix[row][col] = matrix[col][row];
                    matrix[col][row] = temp;
                }
            }

            // Step 2: Reverse each row
            for (int row = 0; row < len; row++) {
                for (int col = 0; col < len / 2; col++) {//We only need to iterate through the first half of each row

                    // Swap matrix[row][col] with matrix[row][len-1-col]
                    int temp = matrix[row][col];
                    matrix[row][col] = matrix[row][len - 1 - col];
                    matrix[row][len - 1 - col] = temp;

                }
            }
        }




        /*
                    This alternative approach rotates the matrix in a single pass by directly moving elements to their final positions.
                    It processes the matrix layer by layer, from outside to inside.
            How it works:

            Layer-by-layer processing:

            We work from the outermost layer inward
            For an n×n matrix, we need to process n/2 layers


            Four-way swap:

            For each element in a layer, we perform a 4-way swap that directly moves each element to its final position
            We save the top element, then move each element counterclockwise to its new position:

            left → top
            bottom → left
            right → bottom
            top → right (using the saved value)
         */

    private static void rotate2(int[][] matrix) {
        int n = matrix.length;

        // Process the matrix in layers, from outer to inner
        for (int layer = 0; layer < n / 2; layer++) {
            int first = layer;
            int last = n - 1 - layer;

            for (int i = first; i < last; i++) {
                int offset = i - first;

                // Save top element
                int top = matrix[first][i];

                // Move left to top
                matrix[first][i] = matrix[last - offset][first];

                // Move bottom to left
                matrix[last - offset][first] = matrix[last][last - offset];

                // Move right to bottom
                matrix[last][last - offset] = matrix[i][last];

                // Move top to right
                matrix[i][last] = top;
            }
        }
    }
    }

