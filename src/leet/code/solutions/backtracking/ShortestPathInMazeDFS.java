package leet.code.solutions.backtracking;

public class ShortestPathInMazeDFS {

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

        System.out.println("DFS Result: " + findShortestPathDFS(maze, startRow, startCol, endRow, endCol));

    }

    private static int minDistance = Integer.MAX_VALUE;//global val

    private static int findShortestPathDFS(int[][] maze, int startRow, int startCol, int endRow, int endCol) {
        if (maze.length == 0 || maze[0].length == 0) {
            return -1;
        }

        //start itself is invalid
        if (maze[startRow][startCol] != 1 || maze[endRow][endCol] != 1) {
            return -1;
        }

        boolean[][] visited = new boolean[maze.length][maze[0].length];

        minDistance = Integer.MAX_VALUE;
        int currentDistance = 0;

        dfs(maze, visited, startRow, startCol, endRow, endCol, currentDistance);

        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }

    private static void dfs(int[][] maze, boolean[][] visited, int currentRow, int currentCol, int endRow, int endCol, int currentDistance) {
        // Pruning: if current distance >= minDistance, no need to continue
        if (currentDistance >= minDistance) {
            return;
        }

        //base, reached END
        if (currentRow == endRow && currentCol == endCol) {
            minDistance = Math.min(minDistance, currentDistance);
            return;
        }

        visited[currentRow][currentCol] = true;//mark visited

        // Directions: up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] dir : directions) {
            int newRow = currentRow + dir[0];
            int newCol = currentCol + dir[1];

            if (isValid(maze, visited, newRow, newCol)) {
                dfs(maze, visited, newRow, newCol, endRow, endCol, currentDistance + 1);
            }
        }

        visited[currentRow][currentCol] = false; // Backtrack
    }

    private static boolean isValid(int[][] maze, boolean[][] visited, int row, int col) {
        return row >= 0 && row < maze.length &&
                col >= 0 && col < maze[0].length &&
                maze[row][col] == 1 &&
                !visited[row][col];
    }
}