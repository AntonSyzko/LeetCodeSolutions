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
  
  
  boolean isValidSudoku = isValidSudokuConcise(board);
        System.out.println(isValidSudoku);
    }

    private static boolean isValidSudokuConcise(char[][] board) {

        Set<String> seen = new HashSet<>();

        for(int row = 0 ; row < board.length; row++){// 9 X 9 grid
            for(int col = 0 ; col < board[0].length; col ++){

                char currChar = board[row][col];

                if(currChar != '.'){

                    //crux here is Set's add() method rturn FALSE if the value is already present -> thus FALSE for duplicates

                    if(!seen.add(currChar + " at row " + row)//string for detecting duplicate ROWs : i.e. '8 at row 2'
                            ||
                       !seen.add(currChar + " at col " + col)//string for detecting duplicate COLs : i.e. '3 at col 8'
                            ||
                       !seen.add(currChar + " at  box " + row/3 + "/" + col/3)){//string for detecting duplicate 3*3 boxes : i.e. '8 at box 2/3'

                        return false;

                    }

                }
            }
        }

        return true;
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


    private static boolean isValidSudoku2(char[][] board) {

        //check rows
        for (int row = 0; row < 9; row++) {
            Set<Character> rowSet = new HashSet<>();
            for (int col = 0; col < 9; col++) {
                if(board[row][col] != '.'){

                    if(rowSet.contains(board[row][col])){
                        return false;
                    }

                    rowSet.add(board[row][col]);
                }
            }
        }



        //check cols
        for (int col = 0; col < 9; col++) {
            Set<Character> colSet = new HashSet<>();
            for (int row = 0; row < 9; row++) {
                if(board[row][col] != '.'){

                    if(colSet.contains(board[row][col])){
                        return false;
                    }

                    colSet.add(board[row][col]);
                }
            }
        }

        //check box

        for (int rowGroup = 0; rowGroup < 3; rowGroup++) {
            for (int colGroup = 0; colGroup < 3; colGroup++) {

                Set<Character> boxSet = new HashSet<>();

                for (int row = rowGroup * 3; row < rowGroup * 3 + 3; row ++) {
                    for (int col = colGroup * 3; col < colGroup * 3 + 3; col++) {

                        if(board[row][col] != '.'){
                            if(boxSet.contains(board[row][col])){
                                return false;
                            }
                            boxSet.add(board[row][col]);
                        }
                    }
                }
            }
        }
        return true;

    }
    }
