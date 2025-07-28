package leet.code.solutions.graphs.dijkstra;

import java.util.*;

/*
Calculate the minimum cost to reach the destination city from the source city
Given an N × N matrix of non-negative integers, where each cell of the matrix (i, j) indicates the direct flight cost from the city i to city j.
Find the minimum cost to reach the destination city N-1 from the source city 0.

For example,

Input: The cost matrix for 4 cities:
[ 0    20  30  100 ]
[ 20   0   15  75  ]
[ 30   15  0   50  ]
[ 100  75  50  0   ]

Output: The minimum cost is 80. The minimum cost path is: city 0 —> city 2 (cost = 30)city 2 —> city 3 (cost = 50)

 Input: The cost matrix for 5 cities:
 [ 0   25  20  10  105 ]
 [ 20  0   15  80  80  ]
 [ 30  15  0   70  90  ]
 [ 10  10  50  0   100 ]
 [ 40  50  5   10  0   ]

Output: The minimum cost is 100. The minimum cost path is: city 0 —> city 3 (cost = 10)city 3 —> city 1 (cost = 10)city 1 —> city 4 (cost = 80)
 */
public class MinCostFromSourceToDestinationWithPath {

    public static void main(String[] args) {
        // Test case 1
        int[][] cost1 = {
                {0, 20, 30, 100},
                {20, 0, 15, 75},
                {30, 15, 0, 50},
                {100, 75, 50, 0}
        };

        // Test case 2
        int[][] cost2 = {
                {0, 25, 20, 10, 105},
                {20, 0, 15, 80, 80},
                {30, 15, 0, 70, 90},
                {10, 10, 50, 0, 100},
                {40, 50, 5, 10, 0}
        };

        System.out.println("Test case 1:");
        Result result1 = findMinCostWithPath(cost1);
        System.out.println("Minimum cost: " + result1.minCost);
        System.out.println("Path: " + result1.path);

        System.out.println("\nTest case 2:");
        Result result2 = findMinCostWithPath(cost2);
        System.out.println("Minimum cost: " + result2.minCost);
        System.out.println("Path: " + result2.path);
    }

    // Version that also returns the path
    private static Result findMinCostWithPath(int[][] cost) {
        int n = cost.length;

        int[] distances = new int[n];

        int[] parent = new int[n];

        boolean[] visited = new boolean[n];

        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        distances[0] = 0;

        PriorityQueue<int[]> minHip = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        minHip.offer(new int[]{0, 0});

        while (!minHip.isEmpty()) {
            int[] current = minHip.poll();

            int currentDist = current[0];
            int currentCity = current[1];

            if (visited[currentCity]) continue;

            visited[currentCity] = true;

            if (currentCity == n - 1) break;

            for (int nextCity = 0; nextCity < n; nextCity++) {

                if (!visited[nextCity] && cost[currentCity][nextCity] > 0) {

                    int newDist = currentDist + cost[currentCity][nextCity];

                    if (newDist < distances[nextCity]) {

                        distances[nextCity] = newDist;
                        parent[nextCity] = currentCity;

                        minHip.offer(new int[]{newDist, nextCity});

                    }
                }
            }
        }

        // Reconstruct path
        List<Integer> path = new ArrayList<>();
        int current = n - 1;

        while (current != -1) {

            path.add(current);
            current = parent[current];

        }

        Collections.reverse(path);

        return new Result(distances[n - 1], path);
    }

    static class Result {
        int minCost;
        List<Integer> path;

        Result(int cost, List<Integer> path) {
            this.minCost = cost;
            this.path = path;
        }
    }
}
