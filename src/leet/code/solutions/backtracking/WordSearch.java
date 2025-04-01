package leet.code.solutions.backtracking;

import leet.code.solutions.utils.Pair;

import java.util.HashSet;
import java.util.Set;

/*
Given a 2-D grid of characters board and a string word, return true if the word is present in the grid, otherwise return false.

For the word to be present it must be possible to form it with a path in the board with horizontally or vertically neighboring cells.
The same cell may not be used more than once in a word.

Input:
board = [
  ['A','B','C','D'],
  ['S','A','A','T'],
  ['A','C','A','E']
],
word = 'CAT'

Output: true
 */
public class WordSearch {

    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'D'},
                {'S', 'A', 'A', 'T'},
                {'A', 'C', 'A', 'E'},
        };
        String target = "CAT";

        System.out.println(exist(board, target));
    }

    private static boolean[][] visited;

    private static boolean exist(char[][] board, String word) {
        int ROWS = board.length;
        int COLS = board[0].length;

        visited = new boolean[ROWS][COLS];

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col] == word.charAt(0)) {//start seacrching when first matching met

                    if (dfs(board, word, row, col, 0)) {
                        return true;
                    }

                }
            }
        }

        return false;
    }

    private static boolean dfs(char[][] board, String word, int row, int col, int positionOfCharInTargetWord) {
        //BASE
        if (positionOfCharInTargetWord == word.length()) {//found all characters
            return true;
        }

        int ROWS = board.length;
        int COLS = board[0].length;

        if (row < 0 || row >= ROWS || // row boundaries
            col < 0 || col >= COLS || // col boundaries
            board[row][col] != word.charAt(positionOfCharInTargetWord) || // non matching chars
            visited[row][col]) { // already visited

            return false;

        }

        visited[row][col] = true;

        boolean res = dfs(board, word, row + 1, col, positionOfCharInTargetWord + 1) ||           // up row
                      dfs(board, word, row - 1, col, positionOfCharInTargetWord + 1) ||          // down row
                      dfs(board, word, row, col + 1, positionOfCharInTargetWord + 1) ||          // right col
                      dfs(board, word, row, col - 1, positionOfCharInTargetWord + 1);            //left col

        visited[row][col] = false;//BACKTRACK

        return res;
    }


    //------------hash set ------------------
    private static Set<Pair<Integer, Integer>> backtrackingPath = new HashSet<>();

    private static boolean existUsingHashSet(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col] == word.charAt(0)) {//start seacrching when first matching met
                    if (DFS(board, word, row, col, 0)) {
                        return true;
                    }
                }

            }
        }

        return false;
    }

    private static boolean DFS(char[][] board, String word, int row, int col, int positionOfWordChar) {
        if (positionOfWordChar == word.length()) {//hit length equality - all characters of word patter found
            return true;
        }

        int ROWS = board.length;
        int COLS = board[0].length;

        Pair<Integer, Integer> currentPair = new Pair<>(row, col);

        if (row < 0 || row >= ROWS || //row boundary
                col < 0 || col >= COLS || // col boundary
                board[row][col] != word.charAt(positionOfWordChar) || // char mismatch in board and target
                backtrackingPath.contains(currentPair)) { // visited already

            return false;

        }


        if (board[row][col] == word.charAt(positionOfWordChar)) {
            backtrackingPath.add(new Pair<>(row, col));
        }

        boolean res = DFS(board, word, row - 1, col, positionOfWordChar + 1) ||   //up row
                      DFS(board, word, row + 1, col, positionOfWordChar + 1) ||   // down row
                      DFS(board, word, row, col - 1, positionOfWordChar + 1) ||   //left col
                      DFS(board, word, row, col + 1, positionOfWordChar + 1);     //right col

        backtrackingPath.remove(currentPair);//BACKTRACK   remove from visited

        return res;

    }

}
