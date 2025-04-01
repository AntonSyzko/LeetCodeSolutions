package leet.code.solutions.blind75;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
https://leetcode.com/problems/set-matrix-zeroes/

Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.

You must do it in place.



Example 1:


Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]
Example 2:


Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]


Constraints:

m == matrix.length
n == matrix[0].length
1 <= m, n <= 200
-231 <= matrix[i][j] <= 231 - 1
 */
public class SetMatrixZeroes {

    public static void main(String[] args) {
         int[][] matrix = {
                 {1,1,1},
                 {1,0,1},
                 {1,1,1}
         };

            System.out.println(Arrays.deepToString(matrix));

            setZeroes(matrix);

            System.out.println(Arrays.deepToString(matrix));

    }

    /// Time Complexity: O(m*n), where m is the number of rows and n is the number of columns.
    ///
    /// We traverse the matrix a constant number of times, with each traversal taking O(m*n) time.
    ///
    /// Space Complexity: O(1)
    ///
    /// We're using only a constant amount of extra space (two boolean variables) regardless of matrix size.
    /// All operations are done in-place.
    private static void setZeroes(int[][] matrix) {

            int ROWS = matrix.length;
            int COLS = matrix[0].length;

            /// Use First Row and Column as Markers:
            ///
            /// Instead of using additional arrays to keep track of which rows and columns need to be zeroed, we'll use the first row and first column of the matrix itself as markers.
            /// Before doing this, we need to separately remember if the first row and first column themselves originally  contained zeros. booleans are for that

            // Use first row and first column as markers
            boolean firstRowHasZero = false;
            boolean firstColHasZero = false;

            // Check if first row has any zeros
            for (int col = 0; col < COLS; col++) {
                if (matrix[0][col] == 0) {
                    firstRowHasZero = true;
                    break;
                }
            }

            // Check if first column has any zeros
            for (int row = 0; row < ROWS; row++) {
                if (matrix[row][0] == 0) {
                    firstColHasZero = true;
                    break;
                }
            }

            /// Mark Rows and Columns:
            ///
            /// Iterate through the matrix (excluding first row and column)
            /// If a cell matrix[i][j] is 0, mark matrix[i][0] and matrix[0][j] as 0 to indicate this row and column need to be zeroed.

            // Use first row and first column as markers for cells that need to be set to zero
            for (int row = 1; row < ROWS; row++) {//start from 1 - exclude forst row and col
                for (int col = 1; col < COLS; col++) {

                    if (matrix[row][col] == 0) {
                        matrix[row][0] = 0; // Mark the row with zeroes ( so all zeros to leftmost column )
                        matrix[0][col] = 0; // Mark the column with zeroes ( so all zeros to upmost row)
                    }
                }
            }

            /// Zero Out Rows and Columns:
            ///
            /// Iterate through the matrix again (excluding first row and column)
            /// If either matrix[i][0] or matrix[0][j] is 0, set matrix[i][j] to 0.

            // Set ROWS to zero based on markers in first column
            for (int row = 1; row < ROWS; row++) {//from 1 - excluding first row and column)

                if (matrix[row][0] == 0) {//if first column == 0
                    for (int col = 1; col < COLS; col++) {
                        matrix[row][col] = 0;
                    }
                }
            }

            // Set columns to zero based on markers in first row
            for (int col = 1; col < COLS; col++) {
                if (matrix[0][col] == 0) {
                    for (int row = 1; row < ROWS; row++) {
                        matrix[row][col] = 0;
                    }
                }
            }

            /// Handle First Row and Column:
            ///
            /// If we found zeros in the first row initially, set the entire first row to zero.
            /// If we found zeros in the first column initially, set the entire first column to zero.

            // Set first row to zero if needed
            if (firstRowHasZero) {
                for (int j = 0; j < COLS; j++) {
                    matrix[0][j] = 0;
                }
            }

            // Set first column to zero if needed
            if (firstColHasZero) {
                for (int i = 0; i < ROWS; i++) {
                    matrix[i][0] = 0;
                }
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
