package leet.code.solutions.backtracking;

/*
https://www.techiedelight.com/find-longest-possible-route-matrix/

Given a rectangular path in the form of a binary matrix, find the length of the longest possible route from source to destination by moving to only non-zero adjacent positions, i.e.,
We can form the route from positions having their value as 1. Note there should not be any cycles in the output path.
 */
public class LongestRouteInMatrix {

    public static void main(String[] args) {
        // input matrix
        int matrix[][] =
                {
                        { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
                        { 1, 0, 1, 0, 1, 1, 1, 0, 1, 1 },
                        { 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 },
                        { 0, 0, 0, 0, 1, 0, 0, 1, 0, 0 },
                        { 1, 0, 0, 0, 1, 1, 1, 1, 1, 1 },
                        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                        { 1, 0, 0, 0, 1, 0, 0, 1, 0, 1 },
                        { 1, 0, 1, 1, 1, 1, 0, 0, 1, 1 },
                        { 1, 1, 0, 0, 1, 0, 0, 0, 0, 1 },
                        { 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 }
                };

        int rows = matrix.length;
        int cols = matrix[0].length;

        int startRow = 0;
        int startCol = 0;

        int endRow = 5;
        int endCol = 7;

        int longestPath = 0;
        int currentDistance = 0;

        boolean[][] visited = new boolean[rows][cols];

        int longestPathRes = findLongestPath(matrix,visited,startRow, startCol,  endRow, endCol,  longestPath, currentDistance);

        if(longestPathRes==Integer.MIN_VALUE){
            System.out.println("no path");
        }else {
            System.out.println(longestPathRes);
        }
    }

    public static int findLongestPath(int[][] mat, boolean[][] visitedCells, int startRow, int startCol, int endRow, int endCol, int maxDist, int dist){
        if(mat == null || mat.length == 0 || mat[0].length == 0){
            return -1;
        }

        // if the destination is not possible from the current cell
        if(mat[startRow][startCol] == 0){
            return 0;
        }

        if(startRow == endRow && startCol == endCol){
            return Math.max(maxDist,  dist);
        }

        visitedCells[startRow][startCol] = true;

        if(isSafe(mat,visitedCells,startRow - 1 ,startCol)){
            maxDist = findLongestPath(mat,visitedCells,startRow - 1 ,startCol,endRow, endCol, maxDist, dist + 1);
        }

        if(isSafe(mat,visitedCells,startRow + 1 ,startCol)){
            maxDist = findLongestPath(mat,visitedCells,startRow + 1 ,startCol,endRow, endCol, maxDist, dist + 1);
        }

        if(isSafe(mat,visitedCells,startRow  ,startCol - 1 )){
            maxDist = findLongestPath(mat,visitedCells,startRow ,startCol - 1 ,endRow, endCol, maxDist, dist + 1);
        }

        if(isSafe(mat,visitedCells,startRow ,startCol + 1 )){
            maxDist = findLongestPath(mat,visitedCells,startRow  ,startCol +  1 ,endRow, endCol, maxDist, dist + 1);
        }

        visitedCells[startRow][startCol] = false;

        return maxDist;
    }

    private static boolean isSafe(int[][] matrix, boolean[][] visited, int row, int col) {
        int ROWS = matrix.length;
        int COLS = matrix[0].length;

        return row >= 0 && row < ROWS
                && col >= 0 && col < COLS
                && !visited[row][col]
                && matrix[row][col] != 0;
    }
    }
