package leet.code.solutions.backtracking;

public class SudokuSolver {

    private static final int BOARD_SIZE = 9;
    private final static int EMPTY_CELL = 0;

    /*
    Time Complexity: O(9^(empty_cells)) worst case, but pruning makes it much faster in practice
    Space Complexity: O(1) extra space (modifies input), O(depth) recursion stack
     */
    public static boolean solveSudoku(int[][] board) {

        // Find the next empty cell
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {

                if(board[row][col] == EMPTY_CELL){

                    // Try numbers 1-9
                    for(int num = 1; num <= 9; num++){

                        if(isValid(board, row, col,num)){//validation for this current NUM

                            board[row][col] = (char) num;//set

                            // Recursively solve the rest
                            if(solveSudoku(board)){//recur
                                return true;
                            }

                            // Backtrack if solution not found
                            board[row][col] = EMPTY_CELL;//BACKTRACK
                        }
                    }//1 - 9 for ends

                    //won't reach here is line 25 hits TRUE
                    return false; // No valid number found
                }
            }
        }

        return true;//reached here -> All cells filled successfully
    }

    private static boolean isValid(int[][] board, int row, int col, int num){

        //1. Check row
        for (int cl = 0; cl < BOARD_SIZE; cl++) {//iterate cols of the ROW
            if(board[row][cl] == num){
                return false;
            }
        }

        //2. check col
        for(int rw = 0; rw < BOARD_SIZE; rw++){//iterate rows of the COL
            if(board[rw][col] == num){
                return false;
            }
        }

        //3. Check 3x3 box - :
        int boxRow = (row / 3) * 3;//(row/3)*3 gives the top-left corner
        int boxCol = (col / 3) * 3;

        for (int i = boxRow; i < boxRow + 3; i++) {//checks 0 -> 1 -> 2, then next iteration 3 -> 4 -> 5 , lats iteration 6 -> 7 -> 8 , resulting in all 9 checks
        for (int j = boxCol; j < boxCol + 3; j++) {

                if(board[i][j] == num){//this 3 X 3 sub_board has the input NUM already
                    return false;
                }
            }
        }

        //got here -> all valid
        return true;
    }

    // Test the solver
    public static void main(String[] args) {
        int[][] puzzle = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        System.out.println("Original puzzle:");
        printBoard(puzzle);

        if (solveSudoku(puzzle)) {
            System.out.println("\nSolved puzzle:");
            printBoard(puzzle);
        } else {
            System.out.println("No solution exists");
        }
    }

    // Helper method to print the board
    public static void printBoard(int[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("------+-------+------");
            }
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    }

    /*
    Backtracking Problems

N-Queens - Place N queens on N×N chessboard so none attack each other
Word Search - Find if a word exists in a 2D character grid
Generate Parentheses - Generate all valid combinations of n pairs of parentheses
Combination Sum - Find all combinations that sum to a target
Palindrome Partitioning - Partition string into palindromic substrings

2D Grid/Matrix Problems

Number of Islands - Count connected components in binary matrix
Maze Solver - Find path from start to end using BFS/DFS
Word Ladder - Transform one word to another changing one letter at a time
Rotate Matrix - Rotate N×N matrix 90 degrees in-place
Spiral Matrix - Print/generate matrix elements in spiral order

Dynamic Programming

Coin Change - Minimum coins needed to make amount
Longest Common Subsequence - Find LCS between two strings
House Robber - Maximum money without robbing adjacent houses
Edit Distance - Minimum operations to transform one string to another
Knapsack Problem - Maximum value within weight constraint

Tree/Graph Traversal

Binary Tree Level Order - Print nodes level by level (BFS)
Validate Binary Search Tree - Check if tree satisfies BST property
Course Schedule - Detect cycles in directed graph (topological sort)
Clone Graph - Deep copy an undirected graph
Serialize/Deserialize Binary Tree - Convert tree to/from string

String Manipulation

Valid Anagram - Check if two strings are anagrams
Group Anagrams - Group strings that are anagrams of each other
Longest Palindromic Substring - Find longest palindrome in string
String Permutations - Generate all permutations of a string
Regular Expression Matching - Implement regex with '.' and '*'

Array/Sliding Window

Two Sum/Three Sum - Find pairs/triplets that sum to target
Maximum Subarray - Find contiguous subarray with largest sum
Sliding Window Maximum - Maximum in each window of size k
Longest Substring Without Repeating Characters
Merge Intervals - Merge overlapping intervals

Design Problems (System Design Lite)

LRU Cache - Implement Least Recently Used cache
Design Twitter - Basic timeline functionality
Design URL Shortener - Like bit.ly
Design Chat System - Basic messaging system
Design File System - In-memory file operations

Interview Strategy Tips:
Time Allocation (45-50 min total):

5-10 min: Clarify requirements, discuss approach
25-30 min: Code the solution
5-10 min: Test with examples, discuss edge cases
5 min: Discuss optimizations, time/space complexity

What Interviewers Look For:

Problem-solving approach - Do you break down the problem?
Code quality - Clean, readable, well-structured
Edge case handling - Empty inputs, invalid data
Communication - Explain your thinking process
Testing mindset - Walk through examples
Optimization awareness - Discuss trade-offs

Red Flags to Avoid:

Jumping straight to coding without discussion
Not testing your solution
Ignoring edge cases
Writing overly complex solutions
Not explaining your approach

The key is demonstrating structured thinking, clean coding practices, and good communication - just like with the Sudoku solver!
     */