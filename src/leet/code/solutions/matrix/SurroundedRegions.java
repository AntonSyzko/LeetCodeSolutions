package leet.code.solutions.matrix;

import java.util.Arrays;

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

    // 1. Capture unsurrounded regions ( 0s that are not all 4 directionally surrounded by X, i.e. border cell 0s // 0 -> T
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {

                if(board[row][col] == '0'//cell is 0
                               && // AND here's important cause it's about '0' AND border cells
                    ((row == 0 || row == ROWS - 1) // border row
                               || // or
                    (col == 0  || col == COLS - 1)) ) { // border col

                             captureUnsurrounded(board, row, col);//DFS

                }
            }
        }
    //now border cells with 0 are marked with 'T'

        // 2. Capture surrounded regions ( change remaining 0s tro X ) 0 -> X
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col] == '0') {//unsurrounded are marked T
                    board[row][col] = 'X';
                }
            }
        }

      // now non-border cells with '0' marked as 'X' , whilst border cells with ex-0 remain marked as 'T'

        //3. Uncapture previously surrounded regions // T -> 0
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col] == 'T') {
                    board[row][col] = '0';
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

        board[row][col] = 'T'; // mark brder '0' cell as 'T'

        captureUnsurrounded(board, row - 1, col);//up
        captureUnsurrounded(board, row + 1, col);//down
        captureUnsurrounded(board, row, col - 1);//left
        captureUnsurrounded(board, row, col + 1);//right

    }
}
