package leet.code.solutions.graphs;

import java.util.*;

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
public class FindRedundantConnectionsDFS {

    public static void main(String[] args) {
        System.out.println("=== Redundant Connection Problem ===\n");

        // Test Case 1
        int[][] edges1 = {{1, 2}, {1, 3}, {3, 4}, {2, 4}};
        System.out.println("Test 1: edges = [[1,2],[1,3],[3,4],[2,4]]");
        System.out.println("Expected: [2,4] (creates cycle 1-2-4-3-1)");
        System.out.println("DFS: " + Arrays.toString(findRedundantConnection(edges1)));

    }

    private static boolean[] visited;
    private static List<List<Integer>> adjacencyList;
    private static Set<Integer> cycle;
    private static int cycleStart;

    private static int[] findRedundantConnectionOpt(int[][] edges) {
        int n = edges.length;
        adjacencyList = new ArrayList<>();

        for (int i = 0; i <= n; i++){
            adjacencyList.add(new ArrayList<>());
        }


        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjacencyList.get(u).add(v);
            adjacencyList.get(v).add(u);
        }

        visited = new boolean[n + 1];
        cycle = new HashSet<>();
        cycleStart = -1;

        dfs(1, -1);

        for (int i = edges.length - 1; i >= 0; i--) {
            int u = edges[i][0];
            int v = edges[i][1];

            if (cycle.contains(u) && cycle.contains(v)) {
                return new int[]{u, v};
            }

        }

        return new int[0];
    }

    private  static boolean dfs(int node, int par) {
        if (visited[node]) {
            cycleStart = node;
            return true;
        }

        visited[node] = true;

        for (int nei : adjacencyList.get(node)) {

            if (nei == par) continue;

            if (dfs(nei, node)) {
                if (cycleStart != -1) cycle.add(node);

                if (node == cycleStart) {
                    cycleStart = -1;
                }

                return true;
            }
        }
        return false;
    }

    private static int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        List<List<Integer>> adjacencyList = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adjacencyList.get(u).add(v);
            adjacencyList.get(v).add(u);

            boolean[] visit = new boolean[n + 1];//we create visisted every iteration to stop at last pair as task needs LAST

            if (dfs(u, -1, adjacencyList, visit)) {
                return edge;
            }
        }
        return new int[0];
    }

    private  static boolean dfs(int node, int parent, List<List<Integer>> adj, boolean[] visit) {
        if (visit[node]) {
            return true;
        }

        visit[node] = true;

        for (int nei : adj.get(node)) {
            if (nei == parent) {
                continue;
            }
            if (dfs(nei, node, adj, visit)) {
                return true;
            }
        }
        return false;
    }
}
