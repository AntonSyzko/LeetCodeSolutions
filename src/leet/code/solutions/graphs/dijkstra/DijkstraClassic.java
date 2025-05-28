package leet.code.solutions.graphs.dijkstra;

import java.util.*;

public class DijkstraClassic {

    public static void main(String[] args) {
        // Test graph as adjacency matrix
        int[][] graph = {
                {0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };

        // Test classic Dijkstra
        int[] distances = dijkstraClassic(graph, 0);
        System.out.println("Classic Dijkstra from vertex 0:");
        for (int i = 0; i < distances.length; i++) {
            System.out.println("Distance to " + i + ": " + distances[i]);
        }
    }

    // Classic Dijkstra with adjacency matrix - O(V²)
    public static int[] dijkstraClassic(int[][] graph, int source) {
        int n = graph.length;

        // Step 1: Initialize distances and visited array
        int[] distances = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        // Step 2: Process each vertex
        for (int count = 0; count < n - 1; count++) {

            // Step 2a: Find vertex with minimum distance among unvisited
            int u = findMinDistance(distances, visited);

            // Step 2b: Mark as visited
            visited[u] = true;

            // Step 2c: Update distances of adjacent vertices
            for (int v = 0; v < n; v++) {
                // Update if: not visited, edge exists, and shorter path found
                if (!visited[v] && graph[u][v] != 0 &&
                        distances[u] != Integer.MAX_VALUE &&
                        distances[u] + graph[u][v] < distances[v]) {

                    distances[v] = distances[u] + graph[u][v];
                }
            }
        }

        return distances;
    }

    // Helper: Find unvisited vertex with minimum distance
    private static int findMinDistance(int[] distances, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < distances.length; v++) {
            if (!visited[v] && distances[v] <= min) {
                min = distances[v];
                minIndex = v;
            }
        }

        return minIndex;
    }


    // Path reconstruction using predecessors
    public static int[] dijkstraWithPath(int[][] graph, int source) {
        int n = graph.length;
        int[] distances = new int[n];
        int[] predecessors = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(predecessors, -1);
        distances[source] = 0;

        for (int count = 0; count < n - 1; count++) {
            int u = findMinDistance(distances, visited);
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visited[v] && graph[u][v] != 0 &&
                        distances[u] != Integer.MAX_VALUE &&
                        distances[u] + graph[u][v] < distances[v]) {

                    distances[v] = distances[u] + graph[u][v];
                    predecessors[v] = u; // Track path
                }
            }
        }

        return distances;
    }

    // Reconstruct shortest path from source to destination
    public static List<Integer> getPath(int[] predecessors, int source, int dest) {
        List<Integer> path = new ArrayList<>();

        if (predecessors[dest] == -1 && dest != source) {
            return path; // No path exists
        }

        int current = dest;
        while (current != -1) {
            path.add(current);
            current = predecessors[current];
        }

        Collections.reverse(path);
        return path;
    }
}
