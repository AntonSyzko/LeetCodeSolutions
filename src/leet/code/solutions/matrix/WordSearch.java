package leet.code.solutions.matrix;

/*
https://leetcode.com/problems/word-search/description/

Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells,
where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example 1:

board =
[["A","B","C","E"],
["S","F","C","S"],
["A","D","E","E"]],

word = "SEE"
output - TRUE ( SEE exists)

 */
public class WordSearch {

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word = "SEE";
        System.out.println(exist(board, word));
    }

    private  static boolean exist(char[][] board, String word) {
        if(word == null || word.isEmpty()) return false;
        if(board == null || board.length == 0 || board[0].length == 0) return false;

        int ROWS = board.length;
        int COLS = board[0].length;

        boolean[][] visited = new boolean[ROWS][COLS];

        for(int row = 0; row < ROWS; row++) {
            for(int col = 0; col < COLS; col++) {
                if(board[row][col] == word.charAt(0)) {
                    if(checkTheRestOfTheBoard2(board, word, 0 , row,col, visited)){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static boolean checkTheRestOfTheBoard(char[][] board, String word, int searchFromIndex,int row, int col, boolean[][] visited) {
        // BASE
        if(searchFromIndex == word.length()) {//we reached end of the word -> word was found, yaaay
            return true;
        }

        if(isInvalidBoundaries(board, row,col) || // out of grid
                searchFromIndex > word.length() || // search index exceeded word length
                visited[row][col] || //cell already visisted
                board[row][col] != word.charAt(searchFromIndex)) { // letter in new cell is not what we need

            return false;
        }

        visited[row][col] = true;

        boolean res =  checkTheRestOfTheBoard(board, word, searchFromIndex + 1, row + 1, col, visited)//UP
                      || checkTheRestOfTheBoard(board, word,searchFromIndex + 1, row - 1, col, visited)//DOWN
                      || checkTheRestOfTheBoard(board, word,  searchFromIndex + 1, row, col - 1, visited)//LEFT
                      || checkTheRestOfTheBoard(board, word,searchFromIndex + 1,  row, col + 1, visited);//RIGHT

        visited[row][col] = false;//BACKTRACK

        return res;
    }

    private static boolean checkTheRestOfTheBoard2(char[][] board, String word, int searchFromIndex,int row, int col, boolean[][] visited) {
        if(searchFromIndex == word.length()) {
            return true;
        }

        if(isInvalidBoundaries(board, row, col)){
           return false;
        }

        if(searchFromIndex > word.length()){
            return false;
        }

        if(visited[row][col]) {
            return false;
        }

        if(word.charAt(searchFromIndex) != board[row][col]) {
            return false;
        }

        visited[row][col] = true;

        boolean res = checkTheRestOfTheBoard2(board, word, searchFromIndex + 1, row + 1, col, visited)
                      ||
                      checkTheRestOfTheBoard2(board, word, searchFromIndex + 1, row - 1, col, visited)
                         ||
                      checkTheRestOfTheBoard2(board, word, searchFromIndex + 1, row , col + 1 , visited)
                         ||
                      checkTheRestOfTheBoard2(board, word, searchFromIndex + 1, row , col - 1, visited);

        visited[row][col] = false;

        return res;

    }


        private static boolean isInvalidBoundaries(char[][] board, int row, int col) {
            int ROWS = board.length;
            int COLS = board[0].length;

            return row < 0 || col < 0 || row >= ROWS || col >= COLS;
    }
}
