package leet.code.solutions.graphs;

import java.util.Arrays;

/*
2359

https://leetcode.com/problems/find-closest-node-to-given-two-nodes/description/

You are given a directed graph of n nodes numbered from 0 to n - 1, where each node has at most one outgoing edge.

The graph is represented with a given 0-indexed array edges of size n, indicating that there is a directed edge from node i to node edges[i]. If there is no outgoing edge from i, then edges[i] == -1.

You are also given two integers node1 and node2.

Return the index of the node that can be reached from both node1 and node2, such that the maximum between the distance from node1 to that node, and from node2 to that node is minimized. If there are multiple answers, return the node with the smallest index, and if no possible answer exists, return -1.

Note that edges may contain cycles.

Example 1:

Input: edges = [2,2,3,-1], node1 = 0, node2 = 1
Output: 2
Explanation: The distance from node 0 to node 2 is 1, and the distance from node 1 to node 2 is 1.
The maximum of those two distances is 1. It can be proven that we cannot get a node with a smaller maximum distance than 1, so we return node 2.
Example 2:

Input: edges = [1,2,-1], node1 = 0, node2 = 2
Output: 2
Explanation: The distance from node 0 to node 2 is 2, and the distance from node 2 to itself is 0.
The maximum of those two distances is 2. It can be proven that we cannot get a node with a smaller maximum distance than 2, so we return node 2.

Constraints:

n == edges.length
2 <= n <= 105
-1 <= edges[i] < n
edges[i] != i
0 <= node1, node2 < n
 */
public class FindClosestNodeToGivenNodes {

    public static void main(String[] args) {
        int[] edges = {2,2,3,-1};
        int node1 = 0;
        int node2 = 1;

        int closestMeetingNode = closestMeetingNode(edges, node1, node2);
        System.out.println(closestMeetingNode);
    }

    /*
    Time Complexity: O(N)

        getDistances(): O(N) - each node visited at most once
        Main loop: O(N) - check all nodes
        Total: O(N)

Space Complexity: O(N)

        Two distance arrays: O(N) each
        Visited array: O(N)
        Total: O(N)
     */

    private static int closestMeetingNode(int[] edges, int node1, int node2) {
        int n = edges.length;

        // Calculate distances from both starting nodes
        int[] dist1 = getDistances(edges, node1);
        int[] dist2 = getDistances(edges, node2);

        int minMaxDistance = Integer.MAX_VALUE;
        int result = -1;

        // Check all nodes to find the optimal meeting point
        for (int i = 0; i < n; i++) {
            // Skip if node i is not reachable from either starting node
            if (dist1[i] == -1 || dist2[i] == -1) {
                continue;
            }

            // Calculate max distance for this meeting node
            int maxDistance = Math.max(dist1[i], dist2[i]);

            // Update result if we found a better option
            if (maxDistance < minMaxDistance) {
                minMaxDistance = maxDistance;
                result = i;
            }
            // If same max distance, choose smaller index (already handled by loop order)
        }

        return result;
    }

    /**
     * Calculate shortest distances from start node to all reachable nodes
     * Returns array where dist[i] = shortest distance from start to node i
     * dist[i] = -1 if node i is not reachable
     */
    private static int[] getDistances(int[] edges, int start) {
        int n = edges.length;
        int[] distances = new int[n];
        boolean[] visited = new boolean[n];

        // Initialize all distances as unreachable
        Arrays.fill(distances, -1);

        // Start traversal from the given node
        int current = start;
        int distance = 0;

        // Follow the path until we reach a dead end or revisit a node
        while (current != -1 && !visited[current]) {
            visited[current] = true;
            distances[current] = distance;

            current = edges[current]; // Move to next node
            distance++;
        }

        return distances;
    }
}