package leet.code.solutions.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://neetcode.io/problems/redundant-connection

You are given a connected undirected graph with n nodes labeled from 1 to n. Initially, it contained no cycles and consisted of n-1 edges.

We have now added one additional edge to the graph. The edge has two different vertices chosen from 1 to n, and was not an edge that previously existed in the graph.

The graph is represented as an array edges of length n where edges[i] = [ai, bi] represents an edge between nodes ai and bi in the graph.

Return an edge that can be removed so that the graph is still a connected non-cyclical graph. If there are multiple answers, return the edge that appears last in the input edges.

Example 1:

Input: edges = [[1,2],[1,3],[3,4],[2,4]]

Output: [2,4]
Example 2:

Input: edges = [[1,2],[1,3],[1,4],[3,4],[4,5]]

Output: [3,4]
Constraints:

n == edges.length
3 <= n <= 100
1 <= edges[i][0] < edges[i][1] <= edges.length
There are no repeated edges and no self-loops in the input.
 */
public class FindRedundantConnection {

    public static void main(String[] args) {
        System.out.println("=== Redundant Connection Problem ===\n");

        // Test Case 1
        int[][] edges1 = {{1,2}, {1,3}, {3,4}, {2,4}};
        System.out.println("Test 1: edges = [[1,2],[1,3],[3,4],[2,4]]");
        System.out.println("Expected: [2,4] (creates cycle 1-2-4-3-1)");
        System.out.println("DFS: " + Arrays.toString(findRedundantConnection_DFS(edges1)));
        //  System.out.println("Optimized DFS: " + Arrays.toString(findRedundantConnection_OptimizedDFS(edges1)));
        System.out.println();

        // Test Case 2
        int[][] edges2 = {{1,2}, {2,3}, {3,4}, {1,4}, {1,5}};
        System.out.println("Test 2: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]");
        System.out.println("Expected: [1,4] (creates cycle 1-2-3-4-1)");
        // System.out.println("DFS: " + Arrays.toString(findRedundantConnection_DFS(edges2)));
        //  System.out.println("Optimized DFS: " + Arrays.toString(findRedundantConnection_OptimizedDFS(edges2)));
        System.out.println();

        // Step-by-step walkthrough
        System.out.println("=== STEP-BY-STEP WALKTHROUGH ===");
    }

    // ==========================================
    // APPROACH 2: DFS CYCLE DETECTION
    // ==========================================

    /*
    CORE LOGIC:
    - Try removing each edge one by one (starting from the last)
    - For each removal, check if the remaining graph is still connected and acyclic
    - The first edge whose removal results in a valid tree is our answer
    - Since we need the "last" valid edge, we iterate backwards
    */

    /*
    Time complexity:    O(E∗(V+E))
    Space complexity:   O(V+E)
     */
    public static int[] findRedundantConnection_DFS(int[][] edges) {
        int n = edges.length;

        // Try removing edges from last to first
        for (int i = n - 1; i >= 0; i--) {
            if (isValidTreeWithoutEdge(edges, i, n)) {
                return edges[i];
            }
        }

        return new int[0]; // Should never reach here
    }

    private static boolean isValidTreeWithoutEdge(int[][] edges, int skipIndex, int n) {
        // Build graph without the edge at skipIndex
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        int edgeCount = 0;

        for (int i = 0; i < edges.length; i++) {
            if (i == skipIndex) continue; // Skip this edge

            int u = edges[i][0];
            int v = edges[i][1];

            adjList.get(u).add(v);
            adjList.get(v).add(u);

            edgeCount++;
        }

        // A tree must have exactly n-1 edges
        if (edgeCount != n - 1) {
            return false;
        }

        // Check if the graph is connected and acyclic
        boolean[] visited = new boolean[n + 1];

        int dummyParent = -1;
        // Check for cycles and mark visited nodes
        if (hasCycle(adjList, 1, dummyParent, visited)) {
            return false;
        }

        // Check if all nodes are connected
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                return false;
            }
        }

        return true;
    }

    private static boolean hasCycle(List<List<Integer>> adjList, int node, int parent, boolean[] visited) {
        visited[node] = true;

        for (int neighbor : adjList.get(node)) {
            if (neighbor == parent) {
                continue; // Skip the edge we came from
            }

            if (visited[neighbor]//already visited
                    || // OR
                    hasCycle(adjList, neighbor, node, visited)) { //iterate recursively

                return true; // Cycle found

            }
        }

        return false;
    }

    // ==========================================
    // OPTIMIZED DFS APPROACH
    // ==========================================

    /*
    BETTER DFS LOGIC:
    - Build the full graph first
    - For each edge (in reverse order), temporarily remove it
    - Check if there's still a path between its endpoints
    - If yes, this edge is redundant; if no, this edge is necessary
    */

    public static int[] findRedundantConnection_OptimizedDFS(int[][] edges) {
        int n = edges.length;

        // Build adjacency list
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        // Try removing edges from last to first
        for (int i = n - 1; i >= 0; i--) {

            int u = edges[i][0];
            int v = edges[i][1];

            // Temporarily remove the edge from adj list
            adjList.get(u).remove(Integer.valueOf(v));
            adjList.get(v).remove(Integer.valueOf(u));

            // Check if u and v are still connected
            if (isConnected(adjList, u, v, n)) {
                return edges[i]; // This edge is redundant
            }

            // Add the edge back
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        return new int[0];
    }

    private static boolean isConnected(List<List<Integer>> adjList, int start, int target, int n) {
        boolean[] visited = new boolean[n + 1];
        return dfsConnect(adjList, start, target, visited);
    }

    private static boolean dfsConnect(List<List<Integer>> adjList, int current, int target, boolean[] visited) {
        if (current == target) {
            return true;
        }

        visited[current] = true;

        List<Integer> adjacentEdges = adjList.get(current);

        for (int neighbor : adjacentEdges) {

            if (!visited[neighbor]) {//not yet visited
                if (dfsConnect(adjList, neighbor, target, visited)) {
                    return true;
                }
            }
        }

        return false;
    }
}
