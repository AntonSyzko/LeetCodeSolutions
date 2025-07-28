package leet.code.solutions.graphs.dijkstra;

import java.util.*;

/*
https://neetcode.io/problems/cheapest-flight-path

There are n airports, labeled from 0 to n - 1, which are connected by some flights. You are given an array flights where flights[i] = [from_i, to_i, price_i]
 represents a one-way flight from airport from_i to airport to_i with cost price_i. You may assume there are no duplicate flights and no flights from an airport to itself.

You are also given three integers src, dst, and k where:

src is the starting airport
dst is the destination airport
src != dst
k is the maximum number of stops you can make (not including src and dst)
Return the cheapest price from src to dst with at most k stops, or return -1 if it is impossible.

Example 1:

Input: n = 4, flights = [[0,1,200],[1,2,100],[1,3,300],[2,3,100]], src = 0, dst = 3, k = 1

Output: 500
Explanation:
The optimal path with at most 1 stop from airport 0 to 3 is shown in red, with total cost 200 + 300 = 500.
Note that the path [0 -> 1 -> 2 -> 3] costs only 400, and thus is cheaper, but it requires 2 stops, which is more than k.

Example 2:

Input: n = 3, flights = [[1,0,100],[1,2,200],[0,2,100]], src = 1, dst = 2, k = 1

Output: 200
Explanation:
The optimal path with at most 1 stop from airport 1 to 2 is shown in red and has cost 200.

Constraints:

1 <= n <= 100
fromi != toi
1 <= pricei <= 1000
0 <= src, dst, k < n
 */
public class CheapestFlightsWithinKStops {

    public static void main(String[] args) {

        int[][] flights = {{0,1,200},
                           {1,2,100},
                           {1,3,300},
                           {2,3,100}};

        System.out.println(findCheapestPrice(4, flights, 0, 3, 1)); // Expected: 500

    }

    /*
    Time & Space Complexity

        Time complexity:
        O((n+m)∗k)

        Space complexity:
        O(n∗k)

      Where n is the number of cities, m is the number of flights and  k is the number of stops.
     */
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // Step 1: Build adjacency list using array of lists for efficiency
        List<int[]>[] adjacencyList = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adjacencyList[i] = new ArrayList<>();
        }

        for (int[] flight : flights) {
            adjacencyList[flight[0]].add(new int[]{flight[1], flight[2]});
        }

        // Step 2: Initialize 2D distance array
        // dist[airport][stops] = minimum cost to reach airport with exactly 'stops' stops
        int[][] dist = new int[n][k + 2];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[src][0] = 0;

        // Step 3: Priority queue stores [cost, airport, stops] - ordered by cost
        PriorityQueue<int[]> minHip = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        minHip.offer(new int[]{0, src, 0});//cost 0 -> from src 0 to dest 0

        // Step 4: Dijkstra's algorithm with state tracking
        while (!minHip.isEmpty()) {

            int[] current = minHip.poll();

            int cost = current[0];
            int airport = current[1];
            int stops = current[2];

            // Step 4a: Return immediately when destination reached (guaranteed optimal)
            if (airport == dst) {
                return cost;
            }

            // Step 4b: Skip if exceeded stop limit or found better path already
            if (stops > k || dist[airport][stops] < cost) {
                continue;
            }

            // Step 4c: Explore all outgoing flights
            for (int[] neighbor : adjacencyList[airport]) {

                int nextAirport = neighbor[0];
                int price = neighbor[1];

                int newCost = cost + price;

                int newStops = stops + 1;

                // Step 4d: Add to queue only if better path found and within stop limit
                if (newStops <= k + 1 && dist[nextAirport][newStops] > newCost) {
                    dist[nextAirport][newStops] = newCost;
                    minHip.offer(new int[]{newCost, nextAirport, newStops});
                }
            }
        }

        return -1;
    }
}
