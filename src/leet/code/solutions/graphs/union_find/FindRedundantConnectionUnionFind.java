package leet.code.solutions.graphs.union_find;


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
public class FindRedundantConnectionUnionFind {

    public static void main(String[] args) {
        System.out.println("=== Redundant Connection Problem ===\n");

        // Test Case 1
        int[][] edges1 = {{1,2}, {1,3}, {3,4}, {2,4}};
        System.out.println("Test 1: edges = [[1,2],[1,3],[3,4],[2,4]]");
        System.out.println("Expected: [2,4] (creates cycle 1-2-4-3-1)");
        System.out.println("Union-Find: " + Arrays.toString(findRedundantConnection_UnionFind(edges1)));
       // System.out.println("DFS: " + Arrays.toString(findRedundantConnection_DFS(edges1)));
      //  System.out.println("Optimized DFS: " + Arrays.toString(findRedundantConnection_OptimizedDFS(edges1)));
        System.out.println();

        // Test Case 2
        int[][] edges2 = {{1,2}, {2,3}, {3,4}, {1,4}, {1,5}};
        System.out.println("Test 2: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]");
        System.out.println("Expected: [1,4] (creates cycle 1-2-3-4-1)");
        System.out.println("Union-Find: " + Arrays.toString(findRedundantConnection_UnionFind(edges2)));
      //  System.out.println("DFS: " + Arrays.toString(findRedundantConnection_DFS(edges2)));
      //  System.out.println("Optimized DFS: " + Arrays.toString(findRedundantConnection_OptimizedDFS(edges2)));
        System.out.println();

        // Step-by-step walkthrough
        System.out.println("=== STEP-BY-STEP WALKTHROUGH ===");
        demonstrateUnionFind(edges1);
    }

    //O(n·α(n)) ≈ O(n)


    // ==========================================
    // APPROACH 1: UNION-FIND (Most Common & Efficient)
    // ==========================================

    /*
    CORE LOGIC:
    - Process edges one by one in order
    - For each edge [u,v], check if u and v are already connected
    - If they are already connected, this edge creates a cycle → return it
    - If not connected, union them and continue
    - The first edge that connects already-connected components is the answer
    */

    public static int[] findRedundantConnection_UnionFind(int[][] edges) {
        int n = edges.length;
        UnionFind uf = new UnionFind(n + 1); // nodes are 1-indexed

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            // If u and v are already connected, adding this edge creates a cycle
            if (uf.find(u) == uf.find(v)) {
                return edge; // This is the redundant edge!
            }

            // Connect u and v
            uf.union(u, v);
        }

        return new int[0]; // Should never reach here given problem constraints
    }

    static class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {
                if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
            }
        }
    }

    private static void demonstrateUnionFind(int[][] edges) {
        System.out.println("\nUnion-Find walkthrough for [[1,2],[1,3],[3,4],[2,4]]:");

        UnionFind uf = new UnionFind(5); // nodes 1-4, index 0 unused

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];

            System.out.printf("\nStep %d: Processing edge [%d,%d]\n", i+1, u, v);
            System.out.printf("  Before: find(%d)=%d, find(%d)=%d\n", u, uf.find(u), v, uf.find(v));

            if (uf.find(u) == uf.find(v)) {
                System.out.printf("  ✓ FOUND REDUNDANT EDGE: [%d,%d]\n", u, v);
                System.out.printf("  Nodes %d and %d are already connected!\n", u, v);
                break;
            } else {
                uf.union(u, v);
                System.out.printf("  After union: find(%d)=%d, find(%d)=%d\n", u, uf.find(u), v, uf.find(v));
                System.out.printf("  Connected nodes %d and %d\n", u, v);
            }
        }
    }
}
