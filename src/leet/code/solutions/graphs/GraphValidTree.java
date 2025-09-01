package leet.code.solutions.graphs;

import javax.swing.*;
import java.util.*;

/*
https://neetcode.io/problems/valid-tree

Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

Example 1:

Input:
n = 5
edges = [[0, 1], [0, 2], [0, 3], [1, 4]]

Output:
true
Example 2:

Input:
n = 5
edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]]

Output:
false
Note:

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
Constraints:

1 <= n <= 100
0 <= edges.length <= n * (n - 1) / 2

 */
public class GraphValidTree {

    public static void main(String[] args) {
        int[][] edges = {{0,1},{0,2},{0,3},{1,4}};
        int numberOfNodes = 5;

        boolean isValidTree = validTree(numberOfNodes, edges);
        System.out.println(isValidTree);

        int[][] edges2 = {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}};
        int numberOfNodes2 = 5;

        boolean isValidTree2 = validTree(numberOfNodes2, edges2);
        System.out.println(isValidTree2);

    }

    /*
    Time & Space Complexity
        Time complexity:

                 O(V+E)
        Space complexity:

                O(V+E)
        Where V is the number vertices and E is the number of edges in the graph.
     */

    private static boolean validTree(int numberOfNodes, int[][] edges) {
       if(edges.length > numberOfNodes -1) return false;

       List<List<Integer>> adjacentNodes = new ArrayList<>();

       for(int i = 0; i < numberOfNodes; i++) {
           adjacentNodes.add(new ArrayList<>());//create empty arrays to store adjacent lists
       }

       for(int[] edge : edges) {
           int currentNode = edge[0];
           int adjacentNodeToCurrent = edge[1];

           //adding both cause UNDIRECTED graph -> undirected edges (each edge is a pair of nodes)
           adjacentNodes.get(currentNode).add(adjacentNodeToCurrent);
           adjacentNodes.get(adjacentNodeToCurrent).add(currentNode);

       }

       Set<Integer> visitedNodes = new HashSet<>();
       int parentNodeToVeryFirstNode = -1;//dummy fake value to start with
       int startNode = 0;//start with node 0

       if(!dfs(startNode, parentNodeToVeryFirstNode, visitedNodes, adjacentNodes)){//dfs checks cycles
           return false;
       }

       return visitedNodes.size() == numberOfNodes;//this checks if ALL nodes are connected ( so all visited is the number of nodes)
    }

    private static boolean dfs(int node, int parentNode, Set<Integer> visitedNodes, List<List<Integer>> adjacentNodes) {

        if(visitedNodes.contains(node)) {//saw before -> cycle
            return false;
        }

        visitedNodes.add(node);

        for(Integer adjacentNode : adjacentNodes.get(node)) {//iterate over ALL nodes adjacent to current

            if(adjacentNode == parentNode) {//we just ignore parent if same as current
                continue;
            }

            if(!dfs(adjacentNode, node, visitedNodes, adjacentNodes)) {
                return false;
            }
        }

        return true;
    }

    // ----------------------- BFS -----------------------------
    private static boolean validTreeBFS(int n, int[][] edges) {
        if (edges.length > n - 1) {
            return false;
        }

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);//both cause graph is UNDIRECTED
            adj.get(edge[1]).add(edge[0]);
        }

        Set<Integer> visit = new HashSet<>();
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, -1});  // {current node, parent node}//-1 is dummy fake parent node
        visit.add(0);

        while (!q.isEmpty()) {

            int[] pair = q.poll();

            int currNode = pair[0];
            int parent = pair[1];

            for (int adjNeighbor : adj.get(currNode)) {
                if (adjNeighbor == parent) {
                    continue;
                }

                if (visit.contains(adjNeighbor)) {//cycle
                    return false;
                }

                visit.add(adjNeighbor);//no cycle all good, store in visited

                q.offer(new int[]{adjNeighbor, currNode});//currNode became parent to adjacentNeighbors
            }
        }

        return visit.size() == n;
    }
}
