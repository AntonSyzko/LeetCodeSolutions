package leet.code.solutions.matrix;

/*
https://www.techiedelight.com/count-all-paths-matrix-from-first-cell-to-last-cell/

Count all paths in a matrix from the first cell to the last cell
Given an M × N rectangular grid, efficiently count all paths starting from the first cell (0, 0) to the last cell (M-1, N-1).
We can either move down or move towards right from a cell.
 */
public class CountPaths {

    public static void main(String[] args)
    {
        // `M × N` matrix
        int ROWS = 3;
        int COLS = 3;

        int totalPaths = countPaths(0, 0, ROWS, COLS);
        System.out.println("The total number of paths is " + totalPaths);
    }

    // Top-down recursive function to count all paths from cell (col, row)
    // to the last cell (M-1, N-1) in a given `M × N` rectangular grid
    public static int countPaths(int col, int row, int ROWS, int COLS) {
        // there is only one way to reach the last cell
        // when we are at the last row or the last column
        if (col == ROWS - 1 || row == COLS - 1) {//here LAST col-row
            return 1;//and there's only ONE way to get to last, = 1
        }

        return countPaths(col + 1, row, ROWS, COLS)     // move down
            + countPaths(col, row + 1, ROWS, COLS);     // move right
    }

    /*
    The time complexity of the proposed solution is exponential since it exhibits overlapping subproblems, i.e.,
    it computes solutions to the same subproblems repeatedly.
     The problem also has an optimal substructure as the solution to the problem can be derived using a solution to its subproblems.
     Since both dynamic programming properties are satisfied, we can use it to optimize the code.

We can either store results of the function calls and return those results when the same input occurs again or
construct an auxiliary matrix to store results of the smaller subproblems. The following code follows the later approach:
     */

    // Bottom-up function to count all paths from the first cell (0, 0)
    // to the last cell (M-1, N-1) in a given `M × N` rectangular grid
    //O(M * N)
    public static int countPaths(int column, int row) {
        // `DP[i][j]` stores the number of paths from cell (0, 0) to cell (i, j)
        int[][] DP = new int[column][row];//DP

        // There is only one way to reach any cell in the first column, i.e.,
        // to move down
        for (int c = 0; c < column; c++) {
            DP[c][0] = 1;
        }

        // There is only one way to reach any cell in the first row, i.e.,
        // to move right
        for (int r = 0; r < row; r++) {
            DP[0][r] = 1;
        }

        // fill `DP[][]` in a bottom-up manner
        for (int c = 1; c < column; c++) {//start with 1 in both row and col - since it's already calculated above
            for (int r = 1; r < row; r++) {
                DP[c][r] = DP[c-1][r] + DP[c][r-1];//prev col + prev row
            }
        }

        // last cell of `DP[][]` stores the count of paths from cell (0, 0) to
        // cell (i, j)
        return DP[column-1][row-1];
    }

    /*
    The time complexity of the proposed solution is O(M × N) for an M × N matrix. The auxiliary space required by the program is O(M × N).
    The space complexity of the solution can be improved up to O(N) as we are only reading data of the previous row for filling the current row.
    Following is the space-optimized solution using only a single array:
     */

    // Bottom-up space-efficient function to count all paths from the first
    // cell (0, 0) to the last cell (M-1, N-1) in a given `M × N` rectangular grid
    //O(N)
    public static int countPathsO_N(int col, int row) {
        int[] DP = new int[row];
        DP[0] = 1;//start with

        // fill `DP[][]` in a bottom-up manner
        for (int c = 0; c < col; c++) {
            for (int r = 1; r < row; r++) {//start from 1 row - since we have filled it above with 1 already
                DP[r] += DP[r - 1];//fill just this row with previous
            }
        }
        // return the last cell
        return DP[row-1];
    }
}
