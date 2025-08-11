package leet.code.solutions.backtracking;

/*
Write a program to solve a Sudoku puzzle by filling the empty cells.
A sudoku solution must satisfy all of the following rules:
1. Each of the digits 1-9 must occur exactly once in each row.
2. Each of the digits 1-9 must occur exactly once in each column.
3. Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
The '.' character indicates empty cells.

Example 1:

Input: board =
[["5","3",".",".","7",".",".",".","."],
["6",".",".","1","9","5",".",".","."],
[".","9","8",".",".",".",".","6","."],
["8",".",".",".","6",".",".",".","3"],
["4",".",".","8",".","3",".",".","1"],
["7",".",".",".","2",".",".",".","6"],
[".","6",".",".",".",".","2","8","."],
[".",".",".","4","1","9",".",".","5"],
[".",".",".",".","8",".",".","7","9"]]

Output:
[["5","3","4","6","7","8","9","1","2"],
["6","7","2","1","9","5","3","4","8"],
["1","9","8","3","4","2","5","6","7"],
["8","5","9","7","6","1","4","2","3"],
["4","2","6","8","5","3","7","9","1"],
["7","1","3","9","2","4","8","5","6"],
["9","6","1","5","3","7","2","8","4"],
["2","8","7","4","1","9","6","3","5"],
["3","4","5","2","8","6","1","7","9"]]
 */
public class SudokuSolver2 {

    // Test method
    public static void main(String[] args) {
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };

        solveSudoku(board);

        // Print result
        for(char[] row : board) {
            for(char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

        /*
    Time Complexity: O(9^(empty_cells)) worst case, but pruning makes it much faster in practice
    Space Complexity: O(1) extra space (modifies input), O(depth) recursion stack
     */

    private static void solveSudoku(char[][] board) {
        solve(board);  // Wrapper method to match expected signature
    }

    private static final int SIZE = 9;
    private static final char EMPTY = '.';

    private static boolean solve(char[][] board) {

        // Find the next empty cell
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {

                if (board[row][col] == EMPTY) {

                    // Try numbers 1-9
                    for(char num = '1'; num <= '9'; num++) {

                        if(isValid(board, num,row, col)){

                            board[row][col] = num;//after validation set !

                            if(solve(board)){//recur
                                return true;
                            }

                            // Backtrack if solution not found
                            board[row][col] = EMPTY;// ✅ Backtrack
                        }
                    }

                    // ✅ No valid number found for this cell
                    return false;//won't reach here is line 84 hits TRUE

                }
            }
        }
        // ✅ All cells filled successfully
        return true;
    }


    private static boolean isValid(char[][] board, char num, int row, int col) {

        //1. Check row
        for(int c = 0; c < SIZE; c++) {//iterate cols of the ROW
            if(board[row][c] == num){
                return false;
            }
        }

        //1. Check col
        for(int r = 0; r < SIZE; r++) {//iterate rows of the COL
            if(board[r][col] == num){
                return false;
            }
        }

        //3. Check 3x3 box - :
        int boxRow = (row / 3) * 3;//(row/3)*3 gives the top-left corner
        int boxCOl = ( col / 3) * 3;

        for(int bxr = boxRow; bxr < boxRow + 3; bxr++) {//checks 0 -> 1 -> 2, then next iteration 3 -> 4 -> 5 , lats iteration 6 -> 7 -> 8 , resulting in all 9 checks
            for(int bxc = boxCOl; bxc < boxCOl + 3; bxc++) {

                if(board[bxr][bxc] == num){//this 3 X 3 sub_board has the input NUM already
                    return false;
                }
            }
        }

        return true;
    }
}