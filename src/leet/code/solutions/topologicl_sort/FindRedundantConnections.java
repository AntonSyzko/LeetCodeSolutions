package leet.code.solutions.topologicl_sort;

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
public class FindRedundantConnections {

    public static void main(String[] args) {

        int[][] edges1 = {{1, 2}, {1, 3}, {3, 4}, {2, 4}};
        System.out.println("Test 1: edges = [[1,2],[1,3],[3,4],[2,4]]");
        System.out.println("Expected: [2,4] (creates cycle 1-2-4-3-1)");
        System.out.println("TOP SORT: " + Arrays.toString(findRedundantConnection(edges1)));
    }

    /*
    Time complexity: O(V+E)

        Space complexity:  O(V+E)

     Where V is the number of vertices and E is the number of edges in the graph.
     */
    public static int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] indegree = new int[n + 1];

        List<List<Integer>> adjacencyList = new ArrayList<>(n + 1);

        for (int i = 0; i <= n; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // Step 1: Build undirected graph and calculate degrees
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adjacencyList.get(u).add(v); // undirected -> add both directions
            adjacencyList.get(v).add(u);

            indegree[u]++; // increment both degrees (misnomer: should be "degree")
            indegree[v]++;
        }

        // Step 2: Find all leaf nodes (degree = 1)
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 1) { // Leaf nodes have degree 1
                q.offer(i);
            }
        }

        // Step 3: Remove leaf nodes layer by layer (like peeling an onion)
        while (!q.isEmpty()) {
            int node = q.poll();
            indegree[node]--; // Mark as removed (degree becomes 0)

            for (int neighbor : adjacencyList.get(node)) {
                indegree[neighbor]--; // Remove connection to this leaf

                if (indegree[neighbor] == 1) { // Neighbor becomes new leaf
                    q.offer(neighbor);
                }
            }
        }

        // Step 4: Find the redundant edge among remaining cycle nodes
        for (int i = edges.length - 1; i >= 0; i--) { // iterate from end
            int u = edges[i][0];
            int v = edges[i][1];

            // Both nodes must be part of the cycle (degree ≥ 2 after leaf removal)
            if (indegree[u] >= 2 && indegree[v] >= 2) {
                return new int[]{u, v};
            }
        }

        return new int[0];
    }
}


/**
        === BRILLIANT INSIGHT: MODIFIED TOPOLOGICAL SORT FOR CYCLE DETECTION ===

        CORE IDEA:
        Instead of traditional topological sort (which removes nodes with in-degree 0),
        this approach removes LEAF NODES (nodes with degree 1) from the tree!

        WHY THIS WORKS:
        - In a tree, leaf nodes have exactly 1 connection
        - By repeatedly removing leaves, we "peel" the tree layer by layer
        - Nodes that remain after this process are part of the CYCLE
        - The cycle nodes will have degree ≥ 2 at the end

        Let's trace through the algorithm step by step:
**/

/*
=== DETAILED ANALYSIS OF THE CONDITION ===

WHY `if (indegree[u] >= 2 && indegree[v] >= 2)` ?

UNDERSTANDING WHAT HAPPENS DURING LEAF REMOVAL:

1. INITIAL STATE: All nodes have degree ≥ 1 (since graph is connected)

2. LEAF REMOVAL PROCESS:
   - Leaf nodes (degree = 1) are removed first
   - When a leaf is removed, its neighbor's degree decreases by 1
   - This might create new leaves, which are then removed
   - Process continues until no more leaves exist

3. FINAL STATE after leaf removal:
   - Degree 0: Nodes that were completely removed (were part of tree structure)
   - Degree 1: IMPOSSIBLE! Any node with degree 1 would have been removed
   - Degree ≥ 2: Nodes that are part of the CYCLE (couldn't be removed as leaves)

4. CYCLE PROPERTY:
   - In the original graph with one extra edge, there's exactly one cycle
   - All cycle nodes have at least 2 connections (to other cycle nodes)
   - Even after removing tree branches, cycle nodes maintain degree ≥ 2

5. WHY CHECK BOTH ENDPOINTS:
   - The redundant edge must connect two nodes that are BOTH in the cycle
   - If one endpoint has degree < 2, it was part of the tree structure (not cycle)
   - Only edges connecting two cycle nodes can be redundant

EXAMPLE WALKTHROUGH:
Original: 1-2-4-3-1 (cycle) with no extra branches
- All nodes in cycle maintain degree ≥ 2 after leaf removal
- Any edge in the cycle can be removed while maintaining connectivity

Original: 1-2-4-3-1 (cycle) with branch 1-5
- Node 5 gets removed as leaf: 1-2-4-3-1 remains
- All cycle nodes (1,2,3,4) have degree ≥ 2
- Node 5 has degree 0 after removal
- Only edges between cycle nodes are redundant

BRILLIANCE OF THIS APPROACH:
- Separates cycle nodes from tree nodes elegantly
- No need for complex cycle detection algorithms
- Final check is simple: both endpoints must be cycle nodes
- Time complexity: O(V + E) - same as standard topological sort!
*/
