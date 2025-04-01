package leet.code.solutions.backtracking;

/*
Find the total number of unique paths that the robot can take in a given maze to reach a given destination from a given source.

Positions in the maze will either be open or blocked with an obstacle. The robot can only move to positions without obstacles, i.e., the solution should find paths that contain only open cells. Retracing the one or more cells back and forth is not considered a new path. The set of all cells covered in a single path should be unique from other paths. At any given moment, the robot can only move one step in either of the four directions. The valid moves are:

Go North: (x, y) ——> (x – 1, y)
Go West:  (x, y) ——> (x, y – 1)G
o South: (x, y) ——> (x + 1, y)
Go East:  (x, y) ——> (x, y + 1)
For example, consider the following maze in the form of a binary matrix where 0 represents a blocked cell and 1 represents an open cell:

      [ 1  1  1  1 ]
      [ 1  1  0  1 ]
      [ 0  1  0  1 ]
      [ 1  1  1  1 ]
We have to find the total number of unique paths from source to destination. The above maze contains 4 unique paths (marked in blue color).

            [ 1  1  1  1 ]
            [ 1  1  1  1 ]
            [ 1  1  0  1 ]
            [ 1  1  0  1 ]

           [ 0  1  0  1 ]
           [ 0  1  0  1 ]
           [ 1  1  1  1 ]
           [ 1  1  1  1 ]


         [ 1  1  1  1 ]
         [ 1  1  1  1 ]
         [ 1  1  0  1 ]
         [ 1  1  0  1 ]

           [ 0  1  0  1 ]
           [ 0  1  0  1 ]
           [ 1  1  1  1 ]
           [ 1  1  1  1 ]
 */
public class FindTotalNumberOfUniquePaths {

    public static void main(String[] args) {
        int[][] maze =
                {
                        { 1, 1, 1, 1 },
                        { 1, 1, 0, 1 },
                        { 0, 1, 0, 1 },
                        { 1, 1, 1, 1 }
                };

        // source cell (0, 0), destination cell (3, 3)
        int count = findCount(maze, 0, 0, 3, 3);

        System.out.println("The total number of unique paths are " + count);
    }

    private static int findCount(int[][] maze, int startRow, int startCol, int endRow, int endCol) {
        //invalid input
        if(maze == null || maze.length == 0 || maze[startRow][startCol] == 0 || maze[endRow][endCol] == 0){
            return 0;
        }

        //square matrix size
        int N = maze.length;

        boolean[][] visitedCells = new boolean[N][N];

        return countAllPaths(maze, visitedCells, startRow, startCol, endRow, endCol);

    }

    private static int countAllPaths(int[][] maze, boolean[][] visitedCells, int startRow, int startCol, int targetRow, int targetCol) {
       if(startRow == targetRow && startCol == targetCol){
           return 1;// one full path from start to target is found as target is reached
       }

       int totalPathCount = 0;//total res

       visitedCells[startRow][startCol] = true;//mark as visited

       int MAZE_SIZE = maze.length;//for square matrix

        //check if the current cell we are AT is valid ( is == 1 and within boundaries )
       if( isValidCurrentCell(maze, startRow,startCol)){

           //go NORTH
           if(startRow  - 1 >= 0 && !visitedCells[startRow - 1][startCol]){// MIND checking if the next move cell is NOT ALREADY VISITED
               totalPathCount += countAllPaths(maze, visitedCells, startRow - 1, startCol, targetRow, targetCol);
           }

           //go SOUTH
           if(startRow  + 1 < MAZE_SIZE && !visitedCells[startRow + 1][startCol]){
               totalPathCount += countAllPaths(maze, visitedCells, startRow + 1, startCol, targetRow, targetCol);
           }

           //go EAST
           if(startCol  - 1 >= 0 && !visitedCells[startRow ][startCol - 1 ]){
               totalPathCount += countAllPaths(maze, visitedCells, startRow , startCol - 1, targetRow, targetCol);
           }

           //go WEST
           if(startCol  + 1 < MAZE_SIZE && !visitedCells[startRow ][startCol + 1 ]){
               totalPathCount += countAllPaths(maze, visitedCells, startRow , startCol + 1, targetRow, targetCol);
           }
       }

       visitedCells[startRow][startCol] = false; // BACKTRACK

       return totalPathCount;
    }

    private static boolean isValidCurrentCell(int[][] maze,int startRow, int startCol) {
        return !(startRow < 0 || startRow >= maze.length || startCol < 0 || startCol >= maze.length)//boundaries check
                && maze[startRow][startCol] == 1 ; // the very cell we are on is valid
    }
}
