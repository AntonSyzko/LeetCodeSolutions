package leet.code.solutions.BFS;


import java.util.LinkedList;
import java.util.Queue;

/*
https://www.youtube.com/watch?v=tgWTBwjy--A

given chess grid and position of the HORSe  find at how many moves horse can get to the lookup cell

horse gos in L
 */
public class ChessHorseLookup {

    public static void main(String[] args) {
        int[][] chessboard = new int[4][4];

        int horseStartX = 0;
        int horseStartY = 0;
        int horseTargetX = 1;
        int horseTargetY = 2;

        int moves = findMinKnightMoves(chessboard, horseStartX, horseStartY, horseTargetX, horseTargetY);
        System.out.println("Minimum moves required: " + moves);
    }

    //time and space complexity of O(rows × cols)
    private static int findMinKnightMoves(int[][] chessboard, int startX, int startY, int targetX, int targetY) {
        // Check if start and target positions are the same
        if (startX == targetX && startY == targetY) {
            return 0;
        }

        int ROWS = chessboard.length;
        int COLS = chessboard[0].length;

        // Define the 8 possible knight moves of L shape
        int[][] knightMoves = {
                {-2, 1}, {-2, -1}, {2, 1}, {2, -1},
                {-1, 2}, {-1, -2}, {1, 2}, {1, -2}
        };

        // Keep track of visited positions
        boolean[][] visited = new boolean[ROWS][COLS];
        visited[startX][startY] = true;

        // Queue for BFS - each entry is [x, y, distance]
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentX = current[0];
            int currentY = current[1];
            int distanceToReachCurrentPosition = current[2];

            // Check if we've reached the target
            if (currentX == targetX && currentY == targetY) {
                return distanceToReachCurrentPosition;
            }

            // Try all possible knight moves
            for (int[] move : knightMoves) {

                int newX = currentX + move[0];
                int newY = currentY + move[1];

                // Check if the new position is valid and not visited
                if (newX >= 0 && newX < ROWS && newY >= 0 && newY < COLS //within boundaries
                        && !visited[newX][newY]) { // not visited

                    visited[newX][newY] = true;
                    queue.add(new int[]{newX, newY, distanceToReachCurrentPosition + 1});//+1 to distance as we move to next level in BFS

                }
            }
        }

        return -1; // Target not reachable
    }
}
