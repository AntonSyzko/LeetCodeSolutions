package leet.code.solutions.backtracking;

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
             {1,1,0,},
             {0,1,0},
             {1,0,1}
     };

     boolean[][] visitedCells = new boolean[mat.length][mat[0].length];

     int startRow = 0;
     int startCol = 0;

     int endRow = 1;
     int endCol = 1;

     int shortestPath = Integer.MAX_VALUE;
     int startingDistance = 0;

     int minDist =  findShortestPath(mat,visitedCells, startRow,startCol, endRow, endCol, shortestPath, startingDistance);
        System.out.println("\r\n\t res " + minDist);

     if(minDist != Integer.MAX_VALUE){
         System.out.println(minDist);
     } else {
         System.out.println("\r\n\t path does not exist");
     }
    }

    public static int findShortestPath(int[][] mat, boolean[][] visitedCells, int startRow, int startCol, int endRow, int endCol, int min_dist, int dist){
        if(mat.length==0 || mat[0].length==0){
            return Integer.MAX_VALUE;
        }

        if(startRow == endRow && startCol == endCol){//target cell met
            return Math.min(dist, min_dist);
        }

        visitedCells[startRow][startCol] = true;//mark as visited


         if(isSafe(mat, visitedCells, startRow -1,startCol)){// up row
            min_dist =  findShortestPath(mat,visitedCells,startRow -1 ,startCol,endRow,endCol,min_dist,dist +1 );
         }

        if(isSafe(mat, visitedCells, startRow +1,startCol)){//down row
            min_dist =  findShortestPath(mat,visitedCells,startRow + 1 ,startCol,endRow,endCol,min_dist,dist + 1 );
        }

        if(isSafe(mat, visitedCells, startRow ,startCol + 1)){// right col
            min_dist =  findShortestPath(mat,visitedCells,startRow  ,startCol + 1,endRow,endCol,min_dist,dist +1 );
        }

        if(isSafe(mat, visitedCells, startRow,startCol -1)){//left col
            min_dist =  findShortestPath(mat,visitedCells,startRow ,startCol -1 ,endRow,endCol,min_dist,dist +1 );
        }

        visitedCells[startRow][startCol] = false;//backtrack


        return min_dist;
    }


    private static boolean isSafe(int[][] matrix, boolean[][] visitedCells,int startRow, int startCol){

        int ROWS = matrix.length;
        int COLS = matrix[0].length;

        return startRow >= 0 && startRow < ROWS    && // row wihin boundaries
                startCol >= 0 && startCol < COLS   && // col within boundaries
                matrix[startRow][startCol] == 1    && // valid cell
                !visitedCells[startRow][startCol];   // not yet visted

    }
}
