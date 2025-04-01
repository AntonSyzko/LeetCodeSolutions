package leet.code.solutions.matrix;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
https://neetcode.io/problems/valid-sudoku

You are given a a 9 x 9 Sudoku board board. A Sudoku board is valid if the following rules are followed:

Each row must contain the digits 1-9 without duplicates.
Each column must contain the digits 1-9 without duplicates.
Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without duplicates.
Return true if the Sudoku board is valid, otherwise return false

Note: A board does not need to be full or be solvable to be valid.
 */
public class IsValidSudoku {

    public static void main(String[] args) {
  char[][] board = {{'1','2','.','.','3','.','.','.','.'},
                     {'4','.','.','5','.','.','.','.','.'},
                     {'.','9','8','.','.','.','.','.','3'},
                     {'5','.','.','.','6','.','.','.','4'},
                     {'.','.','.','8','.','3','.','.','5'},
                     {'7','.','.','.','2','.','.','.','6'},
                     {'.','.','.','.','.','.','2','.','.'},
                     {'.','.','.','4','1','9','.','.','8'},
                     {'.','.','.','.','8','.','.','7','9'}};
  
  
  boolean isValidSudoku = isValidSudoku(board);
        System.out.println(isValidSudoku);
    }

    private static boolean isValidSudoku(char[][] board) {
        Map<Integer, Set<Character>> cols = new HashMap<>();//to store all values in row to check if duplicates exist
        Map<Integer, Set<Character>> rows = new HashMap<>();//to store all values in cols to check if duplicates exist

        Map<String, Set<Character>> squares = new HashMap<>();//to store all values in 3 * 3 row col squares  to check if duplicates exist

        int ROWS = board.length;//should be 9
        int COLS = board[0].length;//should be 9

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {

                char currentCell = board[row][col];

                if(currentCell == '.'){
                    continue;
                }

                String squareKey = row/3 + "," + col/3;

                if (rows.computeIfAbsent(row, k -> new HashSet<>()).contains(currentCell) ||
                        cols.computeIfAbsent(col, k -> new HashSet<>()).contains(currentCell) ||
                        squares.computeIfAbsent(squareKey, k -> new HashSet<>()).contains(currentCell)) {
                    return false;
                }

                rows.get(row).add(currentCell);//put cell in rows MAP for this very row as a key and add cell in Set of values for this roe ( key)
                cols.get(col).add(currentCell);//pit cell into the COLs MAP for this very col as a key of a map and insert cell into the values SET for this very col as a key
                squares.get(squareKey).add(currentCell);//put cell into the 3 * 3 squares MAP , where square key will be the key of the map and put cell into the value SET for this veru square key
            }
        }
        return true;
    }
    
    }
