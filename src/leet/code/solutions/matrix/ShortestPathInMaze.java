package leet.code.solutions.matrix;

/*
Given a maze in the form of a binary rectangular matrix, find the shortest path’s length in the maze from a given source to a given destination.
The path can only be constructed out of cells having value 1, and at any moment, we can only move one step in one of the four directions.


The valid moves are:

Go Top: (x, y) ——> (x – 1, y)
Go Left: (x, y) ——> (x, y – 1)
Go Down: (x, y) ——> (x + 1, y)
Go Right: (x, y) ——> (x, y + 1)


 For example, consider the following binary matrix. If source = (0, 0) and destination = (7, 5), the shortest path from source to destination has length 12.

[ 1  1  1  1  1  0  0  1  1  1 ]
[ 0  1  1  1  1  1  0  1  0  1 ]
[ 0  0  1  0  1  1  1  0  0  1 ]
[ 1  0  1  1  1  0  1  1  0  1 ]
[ 0  0  0  1  0  0  0  1  0  1 ]
[ 1  0  1  1  1  0  0  1  1  0 ]
[ 0  0  0  0  1  0  0  1  0  1 ]
[ 0  1  1  1  1  1  1  1  0  0 ]
[ 1  1  1  1  1  0  0  1  1  1 ]
[ 0  0  1  0  0  1  1  0  0  1 ]
 */
public class ShortestPathInMaze {

    public static void main(String[] args) {

         int[][] mat = {
                 {1,1,0},
                 {0,1,0},
                 {1,0,1}
         };

         boolean[][] visitedCells = new boolean[mat.length][mat[0].length];

         int startRow = 0;
         int startCol = 0;

         int endRow = 1;
         int endCol = 1;

         int shortestPath = Integer.MAX_VALUE;
         int currentDistance = 0;

         int minDist =  findShortestPath(mat,visitedCells, startRow,startCol, endRow, endCol, shortestPath, currentDistance);

         System.out.println("\r\n\t res " + minDist);

         if(minDist != Integer.MAX_VALUE){
             System.out.println(minDist);
         } else {
             System.out.println("\r\n\t path does not exist");
         }
    }

    public static int findShortestPath(int[][] mat, boolean[][] visitedCells, int startRow, int startCol, int endRow, int endCol, int shortestPath, int currentDistance){
        if(mat.length == 0 || mat[0].length == 0){//invalid input check
            return Integer.MAX_VALUE;
        }

        if(startRow == endRow && startCol == endCol){//target cell reached
            return Math.min(currentDistance, shortestPath);//MIN of 2
        }

        visitedCells[startRow][startCol] = true;//mark as visited

         if(isSafe(mat, visitedCells, startRow - 1,startCol)){// up row
             shortestPath =  findShortestPath(mat,visitedCells,startRow -1 ,startCol,endRow,endCol,shortestPath,currentDistance + 1 );//current dostacnt + ! !!!! CRUX here
         }

        if(isSafe(mat, visitedCells, startRow + 1,startCol)){//down row
            shortestPath =  findShortestPath(mat,visitedCells,startRow + 1 ,startCol,endRow,endCol,shortestPath,currentDistance + 1 );
        }

        if(isSafe(mat, visitedCells, startRow ,startCol + 1)){// right col
            shortestPath =  findShortestPath(mat,visitedCells,startRow  ,startCol + 1,endRow,endCol,shortestPath,currentDistance + 1 );
        }

        if(isSafe(mat, visitedCells, startRow,startCol - 1)){//left col
            shortestPath =  findShortestPath(mat,visitedCells,startRow ,startCol -1 ,endRow,endCol,shortestPath,currentDistance + 1 );
        }

        visitedCells[startRow][startCol] = false;//BACKTRACK

        return shortestPath;
    }


    private static boolean isSafe(int[][] matrix, boolean[][] visitedCells,int startRow, int startCol){

        int ROWS = matrix.length;
        int COLS = matrix[0].length;

        return startRow >= 0 && startRow < ROWS    && // row wihin boundaries
                startCol >= 0 && startCol < COLS   && // col within boundaries
                matrix[startRow][startCol] == 1    && // valid cell
                !visitedCells[startRow][startCol];   // not yet visited

    }
}
