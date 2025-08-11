package leet.code.solutions.graphs.dijkstra;

import java.util.PriorityQueue;

public class FindMinimumTimeToReachLastRoom {

    public static void main(String[] args) {
        // Test the examples
        System.out.println(minTimeToReach(new int[][]{{0,4},{4,4}})); // Output: 6
        System.out.println(minTimeToReach(new int[][]{{0,1},{2,3}})); // Output: 4
        System.out.println(minTimeToReach(new int[][]{{0,0,0},{0,0,0}})); // Output: 3
    }

    public static int minTimeToReach(int[][] moveTime) {
        int ROWS = moveTime.length;
        int COLS = moveTime[0].length;

        // Priority queue: [time, row, col] - always pick earliest time
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        boolean[][] visited = new boolean[ROWS][COLS];

        int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}};

        pq.offer(new int[]{0, 0, 0}); // Start at (0,0) at time 0

        while (!pq.isEmpty()) {

            int[] curr = pq.poll();

            int time = curr[0],
                    row = curr[1],
                    col = curr[2];

            // Reached destination!
            if (row == ROWS-1 && col == COLS-1) {
                return time;
            }

            if (visited[row][col]) continue;

            visited[row][col] = true;

            // Try all 4 directions
            for (int[] dir : dirs) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow >= 0 && newRow < ROWS && newCol >= 0 && newCol < COLS &&
                        !visited[newRow][newCol]) {

                    int arriveTime = time + 1;           // When we arrive
                    int doorOpens = moveTime[newRow][newCol]; // When door opens

                    int enterTime;

                    if (arriveTime >= doorOpens) {
                        // Door already open - enter immediately
                        enterTime = arriveTime;
                    } else {
                        // Need to wait - the key insight!
                        int waitTime = doorOpens - arriveTime;

                        // Even wait: can do exact back-and-forth movements
                        // Odd wait: will be 1 second late due to back-and-forth nature
                        enterTime = doorOpens + (waitTime % 2);
                    }

                    pq.offer(new int[]{enterTime, newRow, newCol});
                }
            }
        }

        return -1;
    }
}