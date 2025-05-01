package leet.code.solutions.matrix;

/*
https://leetcode.com/problems/battleships-in-a-board/

Given an m x n matrix board where each cell is a battleship 'X' or empty '.', return the number of the battleships on board.

Battleships can only be placed horizontally or vertically on board. In other words, they can only be made of the shape 1 x k (1 row, k columns) or k x 1 (k rows, 1 column),
where k can be of any size. At least one horizontal or vertical cell separates between two battleships (i.e., there are no adjacent battleships).

Example 1:
Input: board = [["X",".",".","X"],[".",".",".","X"],[".",".",".","X"]]
Output: 2

Example 2:
Input: board = [["."]]
Output: 0

Constraints:

m == board.length
n == board[i].length
1 <= m, n <= 200
board[i][j] is either '.' or 'X'.

Follow up: Could you do it in one-pass, using only O(1) extra memory and without modifying the values board?
 */
public class BattleshipsOnBoard {

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'X', '.', '.', 'X'},
                {'.', '.', '.', 'X'},
                {'.', '.', '.', 'X'},
        };

        int ships = countBattleships(board);
        System.out.println(ships);
    }


    private static int countBattleships(char[][] board) {

        int count = 0;

        int ROWS = board.length;
        int COLS = board[0].length;

        boolean[][] visited = new boolean[ROWS][COLS];

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {

                if (board[row][col] == 'X' && !visited[row][col]) {

                    count++;

                    processBattleship(board, row, col, visited);
                }
            }
        }
        return count;

    }

    private static void processBattleship(char[][] board, int row, int col, boolean[][] visited) {

        if(!checkBoundaries(board, row, col, visited)) return;

        visited[row][col] = true;

        //up
            processBattleship(board, row - 1, col, visited);

        //down
            processBattleship(board, row + 1, col, visited);

        //left
            processBattleship(board, row, col -1, visited);

        //right
            processBattleship(board, row, col +1, visited);
    }

    private static boolean checkBoundaries(char[][] board,int row, int col, boolean[][] visited) {
        int ROWS = board.length;
        int COLS = board[0].length;

        return row >= 0 && col >= 0 && row < ROWS && col < COLS
                && !visited[row][col]
                && board[row][col] == 'X';
    }
}
