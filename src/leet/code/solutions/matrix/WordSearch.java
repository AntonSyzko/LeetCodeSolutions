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
                    if(checkTheRestOfTheBoard(board, word, 0 , row,col, visited)){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static boolean checkTheRestOfTheBoard(char[][] board, String word, int searchFrom,int row, int col, boolean[][] visited) {
        // BASE
        if(searchFrom == word.length()) {//we reached end of the word -> word was found, yaaay
            return true;
        }

        if(checkBoundaries(board, row,col) || // out of grid
                searchFrom > word.length() || // search index exceeded word length
                visited[row][col] || //cell already visisted
                board[row][col] != word.charAt(searchFrom)) { // letter in new cell is not what we need

            return false;
        }

        visited[row][col] = true;

        boolean res =  checkTheRestOfTheBoard(board, word, searchFrom + 1, row + 1, col, visited)//UP
                      || checkTheRestOfTheBoard(board, word,searchFrom + 1, row - 1, col, visited)//DOWN
                      || checkTheRestOfTheBoard(board, word,  searchFrom + 1, row, col - 1, visited)//LEFT
                      || checkTheRestOfTheBoard(board, word,searchFrom + 1,  row, col + 1, visited);//RIGHT

        visited[row][col] = false;//BACKTRACK

        return res;
    }

    private static boolean checkBoundaries(char[][] board, int row, int col) {
        int ROWS = board.length;
        int COLS = board[0].length;
        return row < 0 || col < 0 || row >= ROWS || col >= COLS;
    }
}
