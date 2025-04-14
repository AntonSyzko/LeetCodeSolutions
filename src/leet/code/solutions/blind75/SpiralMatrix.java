package leet.code.solutions.blind75;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/spiral-matrix/

Given an m x n matrix, return all elements of the matrix in spiral order.

Example 1:
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output:         [1,2,3,6,9,8,7,4,5]

Example 2:


Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
Output:         [1,2,3,4,8,12,11,10,9,5,6,7]


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 10
-100 <= matrix[i][j] <= 100
 */
public class SpiralMatrix {

    public static void main(String[] args) {
           int[][] matrix = {{1,2,3},
                             {4,5,6},
                             {7,8,9}};

           List<Integer> spriarl = spiralOrder(matrix);
           System.out.println(spriarl);
    }

      /*
        For each loop iteration, we traverse the matrix in 4 directions:

        Left to right along the top row
        Top to bottom along the rightmost column
        Right to left along the bottom row
        Bottom to top along the leftmost column

        -----------

        Boundary Updates:

After traversing the top row, increment top
After traversing the rightmost column, decrement right
After traversing the bottom row, decrement bottom
After traversing the leftmost column, increment left

Edge Cases:

We check top <= bottom before traversing left to handle cases where we have a single row left
We check left <= right before traversing up to handle cases where we have a single column left
         */

    /*
    Time Complexity: O(m×n)
    Space Complexity: O(1)
     */
    private static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        if (matrix == null || matrix.length == 0) {
            return result;
        }

        int ROWS = matrix.length;
        int COLS = matrix[0].length;

        int topRow = 0;//first row
        int bottomRow = ROWS - 1;//last row

        int leftCol = 0;//first col
        int rightCol = COLS - 1;//last col


        while (topRow <= bottomRow && leftCol <= rightCol) {

            /// Traverse rightCol
            for (int rightMove = leftCol; rightMove <= rightCol; rightMove++) {//move from leftCol to rightCol
                result.add(matrix[topRow][rightMove]);
            }
            topRow++;//raise topRow - shrink it - for not to visit in next iteration

            /// Traverse down
            for (int downMove = topRow; downMove <= bottomRow; downMove++) {//move from topRow to down
                result.add(matrix[downMove][rightCol]);
            }
            rightCol--;

            // /Traverse leftCol (if there are still ROWS to traverse)
            if (topRow <= bottomRow) {//additional necessary check

                for (int leftMove = rightCol; leftMove >= leftCol; leftMove--) {//move from rightCol to leftCol
                    result.add(matrix[bottomRow][leftMove]);
                }
                bottomRow--;
            }

            /// Traverse up (if there are still columns to traverse)
            if (leftCol <= rightCol) {//additional necessary check

                for (int upMove = bottomRow; upMove >= topRow; upMove--) {//move from bottomRow to topRow
                    result.add(matrix[upMove][leftCol]);
                }
                leftCol++;
            }
        }

        return result;
    }

    /*
            Initialize direction vectors:

            dr = [0, 1, 0, -1] (row changes for moving right, down, left, up)
            dc = [1, 0, -1, 0] (column changes for moving right, down, left, up)


            Initialize tracking variables:

            visited[][]: A boolean matrix to track visited cells
            r, c: Current position (starting at top-left [0, 0])
            di: Current direction (starting with 0 for "move right")


            Traversal loop:

            Loop rows × cols times to visit every cell exactly once
            For each cell:

            Add the current element to the result list
            Mark the current cell as visited
            Calculate the next position
            If the next position would be out of bounds or already visited, change direction
            Update current position to the new position

            Direction changing logic:

            Use modulo operation (di + 1) % 4 to cycle through directions: right → down → left → up → right...
     */
    private  static List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        if (matrix == null || matrix.length == 0) {
            return result;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int total = rows * cols;

        // Direction vectors - right, down, left, up
        int[] directionRow = {0, 1, 0, -1};
        int[] directionCol = {1, 0, -1, 0};

        boolean[][] visited = new boolean[rows][cols];

        int row = 0, col = 0; // Start at the top-left corner
        int di = 0;        // Start by moving right

        for (int i = 0; i < total; i++) {
            result.add(matrix[row][col]);
            visited[row][col] = true;

            // Calculate next position
            int newRow = row + directionRow[di];
            int newCOl = col + directionCol[di];

            // Change direction if we would go out of bounds or visit a cell we've already seen
            if (newRow < 0 || newRow >= rows || newCOl < 0 || newCOl >= cols //boundaries
                    || visited[newRow][newCOl]) { // or already visited

                di = (di + 1) % 4; // Change direction (right -> down -> left -> up -> right...)

                newRow = row + directionRow[di];
                newCOl = col + directionCol[di];
            }

            row = newRow;
            col = newCOl;
        }

        return result;
    }
}
