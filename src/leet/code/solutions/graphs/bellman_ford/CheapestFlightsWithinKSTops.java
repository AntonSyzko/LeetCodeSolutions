package leet.code.solutions.graphs.bellman_ford;

import java.util.Arrays;

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
public class CheapestFlightsWithinKSTops {

  /*
=== CHEAPEST FLIGHTS WITHIN K STOPS - BELLMAN-FORD APPROACH ===

PROBLEM: Find cheapest flight path with at most K stops
APPROACH: Modified Bellman-Ford algorithm with limited iterations

KEY INSIGHT:
- Standard Bellman-Ford relaxes edges n-1 times to find shortest paths
- Here we relax edges K+1 times to find paths with at most K stops
- Each iteration represents adding one more stop to our journey

ALGORITHM:
1. Initialize distances: src = 0, all others = infinity
2. For each stop (0 to K), relax all edges once
3. After K+1 iterations, we have shortest paths with at most K stops
4. Return distance to destination, or -1 if unreachable

WHY BELLMAN-FORD WORKS:
- Handles the "at most K stops" constraint naturally
- Each iteration finds shortest paths with one additional edge
- No need for complex graph traversal - just edge relaxation
*/

        public static void main(String[] args) {
            // Test case 1
            int n1 = 4;
            int[][] flights1 = {{0, 1, 200}, {1, 2, 100}, {1, 3, 300}, {2, 3, 100}};
            int src1 = 0, dst1 = 3, k1 = 1;
            System.out.println("Result: " + findCheapestPrice(n1, flights1, src1, dst1, k1)); // Expected: 500

            // Test case 2
            int n2 = 3;
            int[][] flights2 = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
            int src2 = 0, dst2 = 2, k2 = 1;
            System.out.println("Result: " + findCheapestPrice(n2, flights2, src2, dst2, k2)); // Expected: 200

            // Test case 3 - No valid path
            int n3 = 3;
            int[][] flights3 = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
            int src3 = 0, dst3 = 2, k3 = 0;
            System.out.println("Result: " + findCheapestPrice(n3, flights3, src3, dst3, k3)); // Expected: 500
        }

        /*
        Time & Space Complexity
            Time complexity:
            O(n+(m∗k))
            Space complexity:
             O(n)

            Where n is the number of cities, m is the number of flights and k is the number of stops.
         */
        private static int findCheapestPrice(int n, int[][] flights, int src, int dst, int allowedStops) {
            // Step 1: Initialize prices array
            // prices[i] = minimum cost to reach airport i from src
            int[] prices = new int[n];
            Arrays.fill(prices, Integer.MAX_VALUE);
            prices[src] = 0;//takes 0 proce to reach src from src

            // Step 2: Relax edges K+1 times (K stops means K+1 edges maximum)
            for (int stops = 0; stops <= allowedStops; stops++) {

                // Step 2a: Create temporary array to avoid using updated values in same iteration
                int[] tempPrices = Arrays.copyOf(prices, n);

                // Step 2b: Try to relax each flight edge
                for (int[] flight : flights) {

                    int from = flight[0];
                    int to = flight[1];
                    int price = flight[2];

                    // Step 2c: Relax edge if we can reach 'from' airport
                    if (prices[from] == Integer.MAX_VALUE) {//INF can't reach
                        continue;//for each cont
                    }

                    int newPrice = prices[from] + price;

                    tempPrices[to] = Math.min(tempPrices[to], newPrice);

                }

                // Step 2d: Update prices with relaxed values
                prices = tempPrices;
            }

            // Step 3: Return result
            return prices[dst] == Integer.MAX_VALUE ? -1 : prices[dst];
        }
    }


/*
=== STEP-BY-STEP EXAMPLE ===

Input: n=4, flights=[[0,1,200],[1,2,100],[1,3,300],[2,3,100]], src=0, dst=3, k=1

Initial: distances = [0, ∞, ∞, ∞]

Iteration 0 (0 stops):
- Relax edge [0,1,200]: distances[1] = min(∞, 0+200) = 200
- Relax edge [1,2,100]: distances[2] = min(∞, ∞+100) = ∞ (can't use)
- Relax edge [1,3,300]: distances[3] = min(∞, ∞+300) = ∞ (can't use)
- Relax edge [2,3,100]: distances[3] = min(∞, ∞+100) = ∞ (can't use)
- Result: distances = [0, 200, ∞, ∞]

Iteration 1 (1 stop):
- Relax edge [0,1,200]: distances[1] = min(200, 0+200) = 200
- Relax edge [1,2,100]: distances[2] = min(∞, 200+100) = 300
- Relax edge [1,3,300]: distances[3] = min(∞, 200+300) = 500
- Relax edge [2,3,100]: distances[3] = min(500, ∞+100) = 500 (can't use)
- Result: distances = [0, 200, 300, 500]

Final answer: distances[3] = 500

=== WHY TEMPORARY ARRAY IS NEEDED ===

Without temp array, we might use updated values from same iteration:
- Update distances[1] = 200
- Later in same iteration, use this new value for distances[2] = 200 + 100 = 300
- This represents 2 edges in one iteration, violating the constraint

With temp array:
- All relaxations in one iteration use distances from previous iteration
- Ensures each iteration represents exactly one additional edge/stop

=== TIME & SPACE COMPLEXITY ===

Time Complexity: O(K × E) where E = number of flights
- We perform K+1 iterations
- Each iteration processes all E edges once
- Total: O((K+1) × E) = O(K × E)

Space Complexity: O(N) where N = number of airports
- distances array: O(N)
- tempDistances array: O(N)
- Total: O(N)

=== WHEN TO USE BELLMAN-FORD FOR THIS PROBLEM ===

ADVANTAGES:
✓ Simple to understand and implement
✓ Naturally handles the "at most K stops" constraint
✓ No need for complex priority queues or BFS logic
✓ Works well when K is small

ALTERNATIVES:
- Dijkstra with state (airport, stops_used) - more complex but faster for large K
- BFS/DFS with pruning - intuitive but potentially less efficient
- Dynamic Programming - similar approach, different perspective

BEST CHOICE: Bellman-Ford is optimal for this problem due to its simplicity
and natural fit for the constraint-based shortest path requirement.
*/

