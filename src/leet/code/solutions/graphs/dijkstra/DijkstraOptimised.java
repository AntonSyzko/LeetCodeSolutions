package leet.code.solutions.graphs.dijkstra;

import java.util.*;

public class DijkstraOptimised {

    public static void main(String[] args) {
        // Test optimized Dijkstra with adjacency list
        Map<Integer, List<int[]>> adjList = new HashMap<>();
        adjList.put(0, Arrays.asList(new int[]{1, 4}, new int[]{7, 8}));
        adjList.put(1, Arrays.asList(new int[]{0, 4}, new int[]{2, 8}, new int[]{7, 11}));
        adjList.put(2, Arrays.asList(new int[]{1, 8}, new int[]{3, 7}, new int[]{5, 4}, new int[]{8, 2}));
        adjList.put(3, Arrays.asList(new int[]{2, 7}, new int[]{4, 9}, new int[]{5, 14}));
        adjList.put(4, Arrays.asList(new int[]{3, 9}, new int[]{5, 10}));
        adjList.put(5, Arrays.asList(new int[]{2, 4}, new int[]{3, 14}, new int[]{4, 10}, new int[]{6, 2}));
        adjList.put(6, Arrays.asList(new int[]{5, 2}, new int[]{7, 1}, new int[]{8, 6}));
        adjList.put(7, Arrays.asList(new int[]{0, 8}, new int[]{1, 11}, new int[]{6, 1}, new int[]{8, 7}));
        adjList.put(8, Arrays.asList(new int[]{2, 2}, new int[]{6, 6}, new int[]{7, 7}));

        int[] optimizedDistances = dijkstraOptimized(adjList, 0, 9);
        System.out.println("\nOptimized Dijkstra from vertex 0:");
        for (int i = 0; i < optimizedDistances.length; i++) {
            System.out.println("Distance to " + i + ": " + optimizedDistances[i]);
        }

        // Build adjacency list
        Map<Integer, List<int[]>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(new int[]{1, 4}, new int[]{7, 8}));
        graph.put(1, Arrays.asList(new int[]{0, 4}, new int[]{2, 8}, new int[]{7, 11}));
        graph.put(2, Arrays.asList(new int[]{1, 8}, new int[]{3, 7}, new int[]{5, 4}, new int[]{8, 2}));
        graph.put(3, Arrays.asList(new int[]{2, 7}, new int[]{4, 9}, new int[]{5, 14}));
        graph.put(4, Arrays.asList(new int[]{3, 9}, new int[]{5, 10}));
        graph.put(5, Arrays.asList(new int[]{2, 4}, new int[]{3, 14}, new int[]{4, 10}, new int[]{6, 2}));
        graph.put(6, Arrays.asList(new int[]{5, 2}, new int[]{7, 1}, new int[]{8, 6}));
        graph.put(7, Arrays.asList(new int[]{0, 8}, new int[]{1, 11}, new int[]{6, 1}, new int[]{8, 7}));
        graph.put(8, Arrays.asList(new int[]{2, 2}, new int[]{6, 6}, new int[]{7, 7}));

        int[] distances = dijkstraWithMap(graph, 0, 9);
        System.out.println("Dijkstra from vertex 0:");
        for (int i = 0; i < distances.length; i++) {
            if (distances[i] == Integer.MAX_VALUE) {
                System.out.println("Distance to " + i + ": UNREACHABLE");
            } else {
                System.out.println("Distance to " + i + ": " + distances[i]);
            }
        }
    }

    // Dijkstra with Map for distances - O((V + E) log V)
    public static int[] dijkstraWithMap(Map<Integer, List<int[]>> graph, int source, int n) {
        // Step 1: Initialize distances map (only store discovered vertices)
        Map<Integer, Integer> distances = new HashMap<>();
        distances.put(source, 0);

        // Step 2: Priority queue to store [distance, vertex] pairs
        PriorityQueue<int[]> minHip = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        minHip.offer(new int[]{0, source});

        Set<Integer> visited = new HashSet<>();

        // Step 3: Process vertices in order of shortest distance
        while (!minHip.isEmpty()) {

            int[] current = minHip.poll();

            int dist = current[0];
            int u = current[1];

            // Skip if already processed
            if (visited.contains(u)) {
                continue;
            }

            // Mark as visited
            visited.add(u);

            // Step 4: Update distances to neighbors
            if (graph.containsKey(u)) {

                for (int[] edge : graph.get(u)) {

                    int v = edge[0];
                    int weight = edge[1];

                    int newDist = dist + weight;

                    // Update if vertex not visited and we found shorter path
                    if (!visited.contains(v) &&
                            (!distances.containsKey(v) || newDist < distances.get(v))) {

                        distances.put(v, newDist);

                        minHip.offer(new int[]{newDist, v});

                    }
                }
            }
        }

        // Step 5: Convert Map to array for return
        int[] result = new int[n];
        Arrays.fill(result, Integer.MAX_VALUE);

        for (Map.Entry<Integer, Integer> entry : distances.entrySet()) {
            result[entry.getKey()] = entry.getValue();
        }

        return result;
    }

    // Optimized Dijkstra with priority queue - O((V + E) log V)
    public static int[] dijkstraOptimized(Map<Integer, List<int[]>> graph, int source, int n) {
        // Step 1: Initialize distances array
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        // Step 2: Priority queue to store [distance, vertex] pairs
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, source});

        Set<Integer> visited = new HashSet<>();

        // Step 3: Process vertices in order of shortest distance
        while (!pq.isEmpty()) {

            int[] current = pq.poll();

            int dist = current[0];
            int u = current[1];

            // Skip if already processed
            if (visited.contains(u)) {
                continue;
            }

            // Mark as visited
            visited.add(u);

            // Step 4: Update distances to neighbors
            if (graph.containsKey(u)) {

                for (int[] edge : graph.get(u)) {

                    int v = edge[0];
                    int weight = edge[1];

                    if (!visited.contains(v) && dist + weight < distances[v]) {
                        distances[v] = dist + weight;
                        pq.offer(new int[]{distances[v], v});
                    }
                }
            }
        }

        return distances;
    }
}
