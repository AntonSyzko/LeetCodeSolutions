package leet.code.solutions.backtracking;

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

           // findPaths(mat, path, row, col);

            boolean[][] visited = new boolean[mat.length][mat[0].length];
            int startRow = 1;
            int startCol = 1;
        //    findPathsFromAnyCellAnyDirection(mat,path,visited, startRow,startCol);
            findPathsFromAnyCellOnlyMovingRightOrDown(mat,path, startRow,startCol);
    }

    private static void findPaths(int[][] matrix, Stack<Integer> pathStack, int row, int col){

        if (matrix == null || matrix.length == 0) {
            return;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        if(row == rows-1 && col == cols-1){//if reached LAST cell - print out
            pathStack.push(matrix[row][col]);//add current cell as it is a part of the PATH
            System.out.println(pathStack);
            pathStack.pop();
            return;
        }

        pathStack.add(matrix[row][col]);//include  current cell to path

        //move right
        if(col + 1 < cols){
            findPaths(matrix, pathStack, row, col+1);
        }

        //move down
        if( row + 1 < rows){
            findPaths(matrix, pathStack, row+1, col);
        }

        // backtrack: remove the current cell from the path
        pathStack.pop();
    }


    private static void findPathsFromAnyCellOnlyMovingRightOrDown(int[][] matrix, Stack<Integer> pathStack, int row, int col){
        if(matrix == null || matrix.length == 0) {
            return;
        };

        int rows = matrix.length;
        int cols = matrix[0].length;

        if( row >= rows ||  col >= cols ){// boundaries  of rightmost ROW and downmost COl
            return;
        }


        if(row == rows-1 && col == cols-1){//LAST reached

            pathStack.push(matrix[row][col]);
            System.out.println(pathStack);

            pathStack.pop();//backtrack

            return;
        }

        pathStack.add(matrix[row][col]);//backtrack

        //all possible directions lookup
        findPathsFromAnyCellOnlyMovingRightOrDown(matrix, pathStack, row+1, col);//DOWN a row
        findPathsFromAnyCellOnlyMovingRightOrDown(matrix, pathStack, row, col+1);//RIGHT column

        //main backtrack
        pathStack.pop();
    }


    private static void findPathsFromAnyCellAnyDirection(int[][] matrix, Stack<Integer> pathStack, boolean[][] visited, int row, int col){
       if(matrix == null || matrix.length == 0) {
       return;
       };

       int rows = matrix.length;
       int cols = matrix[0].length;

        if( row < 0 || row >= rows || col < 0 || col >= cols || visited[row][col]){// boundaries or ALREADY visited
            return;
        }

        visited[row][col] = true;

       if(row == rows-1 && col == cols-1){//LAST reached

           pathStack.push(matrix[row][col]);
           System.out.println(pathStack);

           pathStack.pop();//backtrack
           visited[row][col] = false;//backtrack

           return;
       }

       pathStack.add(matrix[row][col]);//backtrack

        //all possible directions lookup
       findPathsFromAnyCellAnyDirection(matrix, pathStack, visited, row-1, col);//UP a row
       findPathsFromAnyCellAnyDirection(matrix, pathStack, visited, row+1, col);//DOWN a row
       findPathsFromAnyCellAnyDirection(matrix, pathStack, visited, row, col-1);//LEFT column
       findPathsFromAnyCellAnyDirection(matrix, pathStack, visited, row, col+1);//RIGHT column

        //main backtrack
        pathStack.pop();
        visited[row][col] = false;
    }
    }
