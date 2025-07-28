package leet.code.solutions.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
https://www.techiedelight.com/find-all-paths-from-source-to-destination-in-matrix/

Given an M × N integer matrix, find all paths from the first cell to the last cell. We can only move down or to the right from the current cell.

For example,

Input:      [ 1  2  3 ]
            [ 4  5  6 ]
            [ 7  8  9 ]

Output: 1, 2, 3, 6, 9
        1, 2, 5, 6, 9
        1, 2, 5, 8, 9
        1, 4, 5, 6, 9
        1, 4, 5, 8, 9
        1, 4, 7, 8, 9

 */
public class FindAllPathFromFirstToLastCellOfTheMatrix {

    public static void main(String[] args) {

            int[][] mat =
                    {
                            { 1, 2, 3 },
                            { 4, 5, 6 },
                            { 7, 8, 9 }
                    };

            Stack<Integer> path = new Stack<>();

            // start from `(0, 0)` cell
            int row = 0, col = 0;

            findPaths(mat, path, row, col);
        System.out.println("-------------------");
            boolean[][] visited = new boolean[mat.length][mat[0].length];
            int startRow = 1;
            int startCol = 1;
            findPathsFromAnyCellAnyDirection(mat,path,visited, startRow,startCol);
        System.out.println("-------------------");

        findPathsFromAnyCellOnlyMovingRightOrDown(mat,path, startRow,startCol);

        System.out.println("-------------ALL pATH --------");
       List<List<Integer>> allPath =  findAllPaths(mat);
        System.out.println(allPath);
    }

    /*
Time Complexity: O(2^(M+N)) in worst case

    Number of paths = C(M+N-2, M-1)
    For 3×3 matrix: C(4,2) = 6 paths
    Each path has length (M+N-1)

Space Complexity: O(M+N)

    Recursion depth: O(M+N)
    Path storage: O(M+N)
    If storing all paths: O(paths × path_length) = O(2^(M+N) × (M+N))
 */
    private static List<List<Integer>> findAllPaths(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();

        findPaths(matrix,  0, 0,currentPath, allPaths);
        return allPaths;
    }

    private static void findPaths(int[][] matrix,int row, int col, List<Integer> currentPath,  List<List<Integer>> allPaths) {

        final int ROWS = matrix.length;
        final int COLS = matrix[0].length;

        // Add current cell to path
        currentPath.add(matrix[row][col]);

        // Base case: reached destination
        if (row == ROWS - 1 && col == COLS - 1) {
            allPaths.add(new ArrayList<>(currentPath)); // Create copy for result
            currentPath.remove(currentPath.size() - 1); // Backtrack
            return;
        }

        // Move right if possible
        if (col + 1 < COLS) {
            findPaths(matrix, row, col + 1,currentPath, allPaths);
        }

        // Move down if possible
        if (row + 1 < ROWS) {
            findPaths(matrix, row + 1, col,currentPath, allPaths);
        }

        // Backtrack: remove current cell from path
        currentPath.remove(currentPath.size() - 1);
    }

    private static void findPaths(int[][] matrix, Stack<Integer> pathStack, int row, int col){

        if (matrix == null || matrix.length == 0) {
            return;
        }

        int ROWS = matrix.length;
        int COLS = matrix[0].length;

        //BASE
        if(row == ROWS - 1 && col == COLS - 1){//if reached LAST cell - print out

            pathStack.push(matrix[row][col]);//add current cell as it is a part of the PATH

            System.out.println(pathStack);//actual answer printed

            pathStack.pop();//backtrack

            return;

        }

        pathStack.add(matrix[row][col]);//include  current cell to path

        //move right
        if(col + 1 < COLS){
            findPaths(matrix, pathStack, row, col + 1);
        }

        //move down
        if( row + 1 < ROWS){
            findPaths(matrix, pathStack, row + 1, col);
        }

        // backtrack: remove the current cell from the path
        pathStack.pop();
    }


    private static void findPathsFromAnyCellOnlyMovingRightOrDown(int[][] matrix, Stack<Integer> pathStack, int row, int col){
        if(matrix == null || matrix.length == 0) {
            return;
        };

        int ROWS = matrix.length;
        int COLS = matrix[0].length;

        if( row >= ROWS ||  col >= COLS ){// boundaries  of rightmost ROW and downmost COl
            return;
        }

//BASE
        if(row == ROWS - 1 && col == COLS - 1){//LAST reached

            pathStack.push(matrix[row][col]);
            System.out.println(pathStack);//actual answer printed

            pathStack.pop();//backtrack

            return;
        }

        pathStack.add(matrix[row][col]);

        //all possible directions lookup
        findPathsFromAnyCellOnlyMovingRightOrDown(matrix, pathStack, row + 1, col);//DOWN a row
        findPathsFromAnyCellOnlyMovingRightOrDown(matrix, pathStack, row, col + 1);//RIGHT column

        //main backtrack
        pathStack.pop();
    }


    private static void findPathsFromAnyCellAnyDirection(int[][] matrix, Stack<Integer> pathStack, boolean[][] visited, int row, int col){
       if(matrix == null || matrix.length == 0) {
              return;
       };

       int ROWS = matrix.length;
       int COLS = matrix[0].length;

        if( row < 0 || row >= ROWS || col < 0 || col >= COLS || visited[row][col]){// boundaries or ALREADY visited
            return;
        }

        visited[row][col] = true;

        //BASE
       if(row == ROWS-1 && col == COLS-1){//LAST reached

           pathStack.push(matrix[row][col]);
           System.out.println(pathStack);

           pathStack.pop();//backtrack
           visited[row][col] = false;//backtrack

           return;
       }

       pathStack.add(matrix[row][col]);//backtrack

        //all possible directions lookup
       findPathsFromAnyCellAnyDirection(matrix, pathStack, visited, row - 1, col);//UP a row
       findPathsFromAnyCellAnyDirection(matrix, pathStack, visited, row + 1, col);//DOWN a row
       findPathsFromAnyCellAnyDirection(matrix, pathStack, visited, row, col - 1);//LEFT column
       findPathsFromAnyCellAnyDirection(matrix, pathStack, visited, row, col + 1);//RIGHT column

        //main backtrack
        pathStack.pop();
        visited[row][col] = false;
    }
    }
