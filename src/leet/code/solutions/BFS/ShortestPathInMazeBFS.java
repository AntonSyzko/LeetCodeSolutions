package leet.code.solutions.BFS;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInMazeBFS {

    public static void main(String[] args) {
        int[][] maze = {
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 1, 1, 0, 1},
                {0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1}
        };

        int startRow = 0, startCol = 0;
        int endRow = 4, endCol = 4;

        System.out.println("BFS Result: " + findShortestPathBFS(maze, startRow, startCol, endRow, endCol));
    }

    // Optimal BFS Solution
    private static int findShortestPathBFS(int[][] maze, int startRow, int startCol, int endRow, int endCol) {
        if (maze.length == 0 || maze[0].length == 0) {
            return -1;
        }

        int ROWS = maze.length;
        int COLS = maze[0].length;

        // Check if start and end are valid
        if (maze[startRow][startCol] != 1 || maze[endRow][endCol] != 1) {
            return -1;
        }

        // If start is the destination
        if (startRow == endRow && startCol == endCol) {
            return 0;
        }

        // Directions: up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        boolean[][] visited = new boolean[ROWS][COLS];
        visited[startRow][startCol] = true;

        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(startRow, startCol, 0));


        while (!queue.isEmpty()) {
            Point current = queue.poll();

            int currentX = current.x;
            int currentY = current.y;
            int currentDistanceTo = current.dist;

            // Explore all 4 directions
            for (int[] dir : directions) {
                int newX = currentX + dir[0];
                int newY = currentY + dir[1];

                // Check if the new position is valid
                if (isValid(maze, visited, newX, newY)) {
                    // Check if we reached the destination
                    if (newX == endRow && newY == endCol) {
                        return currentDistanceTo + 1;//exit fast
                    }

                    visited[newX][newY] = true;
                    queue.offer(new Point(newX, newY, currentDistanceTo  + 1));
                }
            }
        }

        return -1; // No path found
    }

    private static boolean isValid(int[][] maze, boolean[][] visited, int x, int y) {
        return x >= 0 && x < maze.length &&
                y >= 0 && y < maze[0].length &&
                maze[x][y] == 1 &&
                !visited[x][y];
    }

    static class Point {
        int x, y, dist;

        Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
