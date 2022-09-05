package leet.code.solutions.maze;

import java.util.ArrayDeque;
import java.util.Queue;

/*
https://www.techiedelight.com/lee-algorithm-shortest-path-in-a-maze/

Given a maze in the form of the binary rectangular matrix, find the shortest path’s length in a maze from a given source to a given destination.

The path can only be constructed out of cells having value 1, and at any given moment, we can only move one step in one of the four directions. The valid moves are:

Go Top: (x, y) ——> (x – 1, y)
Go Left: (x, y) ——> (x, y – 1)
Go Down: (x, y) ——> (x + 1, y)
Go Right: (x, y) ——> (x, y + 1)
For example, consider the following binary matrix. If source = (0, 0) and destination = (7, 5),
 the shortest path from source to destination has length 12.

 We have already discussed a backtracking solution in the previous post.
 The time complexity of the backtracking solution will be higher since all paths need to be traveled.
 However, since it is the shortest path problem, Breadth–first search (BFS) would be an ideal choice.

Note that in BFS, all cells having the shortest path as 1 are visited first, followed by their adjacent cells having the shortest path as 1 + 1 = 2 and so on…
 So if we reach any node in BFS, its shortest path is one more than the shortest path of the parent.
 So, the destination cell’s first occurrence gives us the result, and we can stop our search there.
 It is impossible that the shortest path exists from some other cell for which we haven’t reached the given node yet.
If any such path were possible, we would have already explored it.

 */
public class ShortestPathBFS {

    private static final int[] row = { -1, 0, 0, 1 };
    private static final int[] col = { 0, -1, 1, 0 };

    // Function to check if it is possible to go to position (row, col)
    // from the current position. The function returns false if (row, col)
    // is not a valid position or has a value 0 or already visited.
    private static boolean isValid(int[][] mat, boolean[][] visited, int row, int col)
    {
        return (row >= 0) && (row < mat.length) && (col >= 0) && (col < mat[0].length)
            && mat[row][col] == 1 && !visited[row][col];
    }

    // Find the shortest possible route in a matrix `matrix` from source
    // cell (fromRow, fromCol) to destination cell (targetRow, targetCol)
    private static int findShortestPathLength(int[][] matrix, int fromRow, int fromCol, int targetRow, int targetCol) {
        // base case: invalid input
        if (matrix == null || matrix.length == 0 || matrix[fromRow][fromCol] == 0 || matrix[targetRow][targetCol] == 0) {
            return -1;
        }

        // `M × N` matrix
        int M = matrix.length;
        int N = matrix[0].length;

        // construct a matrix to keep track of visited cells
        boolean[][] visited = new boolean[M][N];

        // create an empty queue
        Queue<Node> q = new ArrayDeque<>();

        // mark the source cell as visited and enqueue the source node
        visited[fromRow][fromCol] = true;
        q.add(new Node(fromRow, fromCol, 0));

        // stores length of the longest path from source to destination
        int min_dist = Integer.MAX_VALUE;

        // loop till queue is empty
        while (!q.isEmpty()) {
            // dequeue front node and process it
            Node node = q.poll();

            // (fromRow, fromCol) represents a current cell, and `dist` stores its
            // minimum distance from the source
            fromRow = node.x;
            fromCol = node.y;
            int dist = node.dist;

            // if the destination is found, update `min_dist` and stop
            if (fromRow == targetRow && fromCol == targetCol) {
                min_dist = dist;
                break;
            }

            // check for all four possible movements from the current cell
            // and enqueue each valid movement
            for (int k = 0; k < 4; k++)
            {
                // check if it is possible to go to position
                // (fromRow + row[k], fromCol + col[k]) from current position
                if (isValid(matrix, visited, fromRow + row[k], fromCol + col[k]))
                {
                    // mark next cell as visited and enqueue it
                    visited[fromRow + row[k]][fromCol + col[k]] = true;
                    q.add(new Node(fromRow + row[k], fromCol + col[k], dist + 1));
                }
            }
        }

        if (min_dist != Integer.MAX_VALUE) {
            return min_dist;
        }
        return -1;
    }

    public static void main(String[] args)
    {
        int[][] mat =
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

    // A Queue Node
    static class Node
    {
        // (x, y) represents matrix cell coordinates, and
        // `dist` represents their minimum distance from the source
        int x, y, dist;

        Node(int x, int y, int dist)
        {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }


}
