package leet.code.solutions.matrix;

import java.util.Arrays;

/*130

https://leetcode.com/problems/surrounded-regions/description/

You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are surrounded:

Connect: A cell is connected to adjacent cells horizontally or vertically.
Region: To form a region connect every 'O' cell.
Surround: The region is surrounded with 'X' cells if you can connect the region with 'X' cells and none of the region cells are on the edge of the board.
To capture a surrounded region, replace all 'O's with 'X's in-place within the original board. You do not need to return anything.

Example 1:

Input: board =
[
["X","X","X","X"],
["X","O","O","X"],
["X","X","O","X"],
["X","O","X","X"]
]

Output:
 [
 ["X","X","X","X"],
 ["X","X","X","X"],
 ["X","X","X","X"],
 ["X","O","X","X"]
 ]

Explanation:

In the above diagram, the bottom region is not captured because it is on the edge of the board and cannot be surrounded.

Example 2:

Input: board = [["X"]]

Output: [["X"]]



Constraints:

m == board.length
n == board[i].length
1 <= m, n <= 200
board[i][j] is 'X' or 'O'.
 */
public class SurroundedRegions {

    public static void main(String[] args) {
        char[][] board = {
                {'X','X','X','X'},
                {'X','0','0','X'},
                {'X','X','0','X'},
                {'X','0','X','X'},
        };
        System.out.println("board at start ");
        System.out.println(Arrays.deepToString(board));

        solve(board);

        System.out.println("board at end ");
        System.out.println(Arrays.deepToString(board));

    }

/*

Time & Space Complexity
    Time complexity:

        O(m∗n)
    Space complexity:

        O(m∗n)
    Where m is the number of rows and  n is the number of columns of the board.
 */
    private static void solve(char[][] board) {

        int ROWS = board.length;
        int COLS = board[0].length;

    // 1. Capture unsurrounded regions ( 0s that are not all 4 directionally surrounded by X, i.e. border cells 0s // 0 -> T
        //basically identify BORDER cells with 0(zero) and mark them
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {

                if(board[row][col] == '0'//cell is 0
                               && // AND here's important cause it's about '0' AND border cells
                    (
                            (row == 0 || row == ROWS - 1) // border row
                               || // or
                             (col == 0  || col == COLS - 1)// border col
                    )
                ) {

                             captureUnsurrounded(board, row, col);//DFS

                }
            }
        }
    //now border cells with 0 are marked with 'T'

        // 2. Capture surrounded regions ( change remaining 0s tro X ) 0 -> X
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col] == '0') {//unsurrounded ( border ones)  are marked T ( remember)
                    board[row][col] = 'X';
                }
            }
        }

      // now non-border cells with '0' marked as 'X' , whilst border cells with ex-0 remain marked as 'T'

        //3. Uncapture previously surrounded regions // T -> 0
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col] == 'T') {
                    board[row][col] = '0';//unmark back
                }
            }
        }
    }

    //DFS
    private static void captureUnsurrounded(char[][] board, int row, int col) {
        //BASE
        if(row < 0 || col < 0 || row >= board.length || col >= board[0].length // out of bounds on grid
            ||  board[row][col] != '0'){ // not 0 cell -> ignore
            return;
        }

        board[row][col] = 'T'; // mark border '0' cell as 'T'

        captureUnsurrounded(board, row - 1, col);//up
        captureUnsurrounded(board, row + 1, col);//down
        captureUnsurrounded(board, row, col - 1);//left
        captureUnsurrounded(board, row, col + 1);//right
    }

    private static void solve2(char[][] board) {
        if (board.length == 0)
            return;

        final int ROWS = board.length;
        final int COLS = board[0].length;

        for (int row = 0; row < ROWS; ++row){
            for (int col = 0; col < COLS; ++col) {
                if (row * col == 0 //row * coll == 0 means either row = 0 OR col = 0 (top row OR left column)
                        ||
                        row == ROWS - 1 // means bottom row
                        ||
                        col == COLS - 1) {//means right column

                    dfsMarkBorderCellsAsStar(board, row, col);//marks border cells with '*'

                }
            }
        }

        for (char[] row : board) {//iterate through ALL rows
            for (int col = 0; col < row.length; ++col) {//iterate over each col og the row -> so throughout all cells in this row
                if (row[col] == '*'){
                    row[col] = 'O';
                } else if (row[col] == 'O') {
                    row[col] = 'X';
                }
            }
        }

    }

    // Marks the grids with 'O' that stretch from the four sides to '*'.
    private  static void dfsMarkBorderCellsAsStar(char[][] board, int i, int j) {
        //BASE
        if (i < 0 || i == board.length || j < 0 || j == board[0].length)//boundaries
            return;

        if (board[i][j] != 'O')//not a zero -> do not touch
            return;

        board[i][j] = '*';

        dfsMarkBorderCellsAsStar(board, i + 1, j);
        dfsMarkBorderCellsAsStar(board, i - 1, j);
        dfsMarkBorderCellsAsStar(board, i, j + 1);
        dfsMarkBorderCellsAsStar(board, i, j - 1);
    }
}

/*
1. mark border 0 cells as T ( recursively all 4 directions)
2. mark remaining ( non-border) 0 cells as X
3. unmark T-marked border cells as 0 back
 */
