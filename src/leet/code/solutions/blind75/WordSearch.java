package leet.code.solutions.blind75;

/*
https://leetcode.com/problems/word-search/

Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.



Example 1:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true
Example 2:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true
Example 3:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
Output: false


Constraints:

m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board and word consists of only lowercase and uppercase English letters.
 */
public class WordSearch {

    public static void main(String[] args) {

    }

    /*
            Time Complexity: O(N × 4^L)

        N = number of cells in the board (m × n)
        L = length of the word
        For each cell, we might explore up to 4^L possible paths (4 directions for each character)

        Space Complexity: O(L)

        The recursion stack can go as deep as the length of the word
        No additional data structures are used except for the recursion stack
     */
    private static boolean exist(char[][] board, String word) {
        int ROWS = board.length;
        int COLS = board[0].length;

        // Try starting the search from each cell
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {

                if (board[row][col] == word.charAt(0)) {
                    if(DFS(board, row, col, word, 0)){
                        return true;
                    }
                }
            }
        }

        return false;//if reached here - word not found
    }

    private  static boolean DFS(char[][] board, int row, int col, String word, int index) {
        // Base case: if we've matched all characters in the word
        if (index == word.length()) {//BASE
            return true;
        }

        // Check if current position is out of bounds or doesn't match current character
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length // boundaries check
                || board[row][col] != word.charAt(index)) { //not a match  in terms of  letter at this index
            return false;
        }

        // Mark the current cell as visited by changing it to a special character
        char temp = board[row][col];
        board[row][col] = '#';//# definitely not a letter so match here will always fail - thus we will avoid processing this cell

        // Explore in all four directions: up, right, down, left
        boolean necessaryCharFound = DFS(board, row - 1, col, word, index + 1) ||  // Up
                DFS(board, row, col + 1, word, index + 1) ||           // Right
                DFS(board, row + 1, col, word, index + 1) ||          // Down
                DFS(board, row, col - 1, word, index + 1);            // Left

        // Restore the original character after exploration
        board[row][col] = temp; // BACKTRACK

        return necessaryCharFound;
    }
}

/*
        Step-by-Step Explanation

        Main Function (exist):

        Iterate through each cell in the board
        If the current cell matches the first character of the word, start DFS from this position
        Return true if any DFS search finds the complete word


        DFS Function:

        Base case: If we've matched all characters (index == word.length), return true
        Check boundaries and character match
        Mark the current cell as visited (replace with '#')
        Recursively search in all four directions
        Restore the original character (backtracking)
        Return true if any direction finds the rest of the word


        Visited Cell Tracking:

        Instead of using a separate visited matrix, we temporarily modify the original board
        Change the character to '#' during exploration of a path
        Restore the original character when backtracking




 */