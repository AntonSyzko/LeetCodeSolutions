package leet.code.solutions.graphs.dijkstra;

import java.util.*;

public class NetworkDelayTime {

    public static void main(String[] args) {

        int[][] times1 = {{1,2,1}, {2,3,1}, {1,4,4}, {3,4,1}};
        int n1 = 4, k1 = 1;
        System.out.println("Test 1: Expected = 3");
        System.out.println("  Fixed version: " + networkDelayTime(times1, n1, k1));
        System.out.println("  Optimized version: " + networkDelayTime(times1, n1, k1));

        int[][] times2 = {{1,2,1}};
        int n2 = 2, k2 = 1;
        System.out.println("\nTest 2: Expected = 1");
        System.out.println("  Fixed version: " + networkDelayTime(times2, n2, k2));
        System.out.println("  Optimized version: " + networkDelayTime(times2, n2, k2));

        int[][] times3 = {{1,2,1}};
        int n3 = 2, k3 = 2;
        System.out.println("\nTest 3: Expected = -1 (unreachable)");
        System.out.println("  Fixed version: " + networkDelayTime(times3, n3, k3));
        System.out.println("  Optimized version: " + networkDelayTime(times3, n3, k3));
    }

    /*
    Time & Space Complexity
        Time complexity:
        O(ElogV)

        Space complexity:
        O(V+E)

        Where V is the number of vertices and E is the number of edges.
     */

    public static int networkDelayTime(int[][] times, int n, int startingNode) {
        // Step 1: Build adjacency list representation of the adjacencyMap
        Map<Integer, List<int[]>> adjacencyMap = new HashMap<>();
        for (int[] time : times) {
            int source = time[0];
            int destination = time[1];
            int travelTime = time[2];

            // Each source maps to list of [destination, travelTime] pairs
            adjacencyMap.computeIfAbsent(source, key -> new ArrayList<>()).add(new int[]{destination, travelTime});
        }

        // Step 2: Initialize Dijkstra's algorithm
        // Priority queue: [distance, node] - ordered by distance (min-heap)
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        Set<Integer> visited = new HashSet<>();

        // Start from source node 'startingNode' with distance 0
        minHeap.offer(new int[]{0, startingNode});

        int maxTimeRes = 0; // Time when the last node receives the signal

        // Step 3: Process nodes in order of shortest distance
        while (!minHeap.isEmpty()) {

            int[] current = minHeap.poll();

            int currentDistance = current[0];
            int currentNode = current[1];

            // Skip if already processed (important for correctness!)
            if (visited.contains(currentNode)) {
                continue;
            }

            // Mark as visited and update the time when signal reaches this node
            visited.add(currentNode);

            maxTimeRes = currentDistance;

            // Step 4: Update distances to neighboring nodes
            if (adjacencyMap.containsKey(currentNode)) {

                for (int[] edge : adjacencyMap.get(currentNode)) {

                    int neighbor = edge[0];
                    int edgeWeight = edge[1];

                    // Only add to queue if neighbor hasn't been finalized
                    if (!visited.contains(neighbor)) {
                        int newDistance = currentDistance + edgeWeight;
                        minHeap.offer(new int[]{newDistance, neighbor});
                    }
                }
            }
        }

        // Step 5: Check if all nodes were reached
        return visited.size() == n ? maxTimeRes : -1;
    }



    // ==========================================
    // OPTIMIZED VERSION WITH DISTANCE ARRAY
    // ==========================================

    public static int networkDelayTime_Optimized(int[][] times, int n, int k) {
        // Build adjacency list
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] time : times) {
            graph.computeIfAbsent(time[0], key -> new ArrayList<>()).add(new int[]{time[1], time[2]});
        }

        // Distance array to track shortest distances (more memory efficient than set)
        int[] distances = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[k] = 0;

        // Priority queue: [distance, node]
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, k});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();

            int dist = current[0];
            int node = current[1];

            // Skip if we've found a shorter path already
            if (dist > distances[node]) {
                continue;
            }

            // Process neighbors
            if (graph.containsKey(node)) {
                for (int[] edge : graph.get(node)) {
                    int neighbor = edge[0];
                    int weight = edge[1];
                    int newDist = dist + weight;

                    // Only update if we found a shorter path
                    if (newDist < distances[neighbor]) {
                        distances[neighbor] = newDist;
                        pq.offer(new int[]{newDist, neighbor});
                    }
                }
            }
        }

        // Find the maximum time among all reachable nodes
        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            if (distances[i] == Integer.MAX_VALUE) {
                return -1; // Some node is unreachable
            }
            maxTime = Math.max(maxTime, distances[i]);
        }

        return maxTime;
    }
}
