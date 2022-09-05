package leet.code.solutions.maze;

/*
https://www.techiedelight.com/find-shortest-path-in-maze/
Find the shortest path in a maze
Given a maze in the form of a binary rectangular matrix, find the shortest path’s length in the maze from a given source to a given destination. The path can only be constructed out of cells having value 1, and at any moment, we can only move one step in one of the four directions.

The valid moves are:

Go Top: (x, y) ——> (x – 1, y)
Go Left: (x, y) ——> (x, y – 1)
Go Down: (x, y) ——> (x + 1, y)
Go Right: (x, y) ——> (x, y + 1)

To find the maze’s shortest path, search for all possible paths in the maze from the starting position to the goal position until all possibilities are exhausted.
 We can easily achieve this with the help of backtracking.
 The idea is to start from the given source cell in the matrix and explore all four paths possible and recursively check
 if they will lead to the destination or not. Then update the minimum path length whenever the destination cell is reached.
  If a path doesn’t reach the destination or explored all possible routes from the current cell, backtrack.
   To make sure that the path is simple and doesn’t contain any cycles, keep track of cells involved in the current path in a matrix,
   and before exploring any cell, ignore the cell if it is already covered in the current path.
 */
public class ShortestPathBacktracking {

    // Check if it is possible to go to (x, y) from the current position. The
    // function returns false if the cell is invalid, has value 0, or already visited
    private static boolean isSafe(int[][] mat, boolean[][] visited, int x, int y) {
        return (x >= 0 && x < mat.length && y >= 0 && y < mat[0].length) &&
            mat[x][y] == 1 && !visited[x][y];
    }

    // Find the shortest possible route in a matrix `matrix` from source cell (fromRow, fromCol)
    // to destination cell (rowTarget, colTarget).
    // `min_dist` stores the length of the longest path from source to a destination
    // found so far, and `dist` maintains the length of the path from a source cell
    // to the current cell (fromRow, fromCol).
    public static int findShortestPath(int[][] matrix, boolean[][] visited,
                                       int fromRow, int fromCol, int rowTarget, int colTarget, int min_dist, int dist) {
        // if the destination is found, update `min_dist`
        if (fromRow == rowTarget && fromCol == colTarget) {
            return Integer.min(dist, min_dist);
        }

        // set (fromRow, fromCol) cell as visited BACKTRACKING array
        visited[fromRow][fromCol] = true;

        // go to the bottom cell
        if (isSafe(matrix, visited, fromRow + 1, fromCol)) {
            min_dist = findShortestPath(matrix, visited, fromRow + 1, fromCol, rowTarget, colTarget,
                min_dist, dist + 1);
        }

        // go to the right cell
        if (isSafe(matrix, visited, fromRow, fromCol + 1)) {
            min_dist = findShortestPath(matrix, visited, fromRow, fromCol + 1, rowTarget, colTarget,
                min_dist, dist + 1);
        }

        // go to the top cell
        if (isSafe(matrix, visited, fromRow - 1, fromCol)) {
            min_dist = findShortestPath(matrix, visited, fromRow - 1, fromCol, rowTarget, colTarget,
                min_dist, dist + 1);
        }

        // go to the left cell
        if (isSafe(matrix, visited, fromRow, fromCol - 1)) {
            min_dist = findShortestPath(matrix, visited, fromRow, fromCol - 1, rowTarget, colTarget,
                min_dist, dist + 1);
        }

        // backtrack: remove (fromRow, fromCol) from the visited matrix
        visited[fromRow][fromCol] = false;

        return min_dist;
    }

    // Wrapper over findShortestPath() function
    public static int findShortestPathLength(int[][] mat, int i, int j, int x, int y)
    {
        // base case: invalid input
        if (mat == null || mat.length == 0 || mat[i][j] == 0 || mat[x][y] == 0) {
            return -1;
        }

        // `M × N` matrix
        int M = mat.length;
        int N = mat[0].length;

        int min_dist;

        // construct an `M × N` matrix to keep track of visited cells
        boolean[][] visited = new boolean[M][N];

        min_dist = findShortestPath(mat, visited, i, j, x, y, Integer.MAX_VALUE, 0);
        if (min_dist != Integer.MAX_VALUE) {
            return min_dist;
        }
        return -1;
    }

    public static void main(String[] args)
    {
        int mat[][] =
            {
                { 1, 1, 1, 1, 1, 0, 0, 1, 1, 1 },
                { 0, 1, 1, 1, 1, 1, 0, 1, 0, 1 },
                { 0, 0, 1, 0, 1, 1, 1, 0, 0, 1 },
                { 1, 0, 1, 1, 1, 0, 1, 1, 0, 1 },
                { 0, 0, 0, 1, 0, 0, 0, 1, 0, 1 },
                { 1, 0, 1, 1, 1, 0, 0, 1, 1, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 1, 0, 1 },
                { 0, 1, 1, 1, 1, 1, 1, 1, 0, 0 },
                { 1, 1, 1, 1, 1, 0, 0, 1, 1, 1 },
                { 0, 0, 1, 0, 0, 1, 1, 0, 0, 1 },
            };

        int min_dist = findShortestPathLength(mat, 0, 0, 7, 5);

        if (min_dist != -1) {
            System.out.println("The shortest path from source to destination " +
                "has length " + min_dist);
        } else {
            System.out.println("Destination cannot be reached from source");
        }
    }
}
