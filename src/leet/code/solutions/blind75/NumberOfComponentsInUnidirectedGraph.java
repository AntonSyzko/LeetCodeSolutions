package leet.code.solutions.blind75;

import java.util.*;

/*
https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/description/

https://leetcode.ca/all/323.html

323. Number of Connected Components in an Undirected Graph
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.

Example 1:

Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]

     0          3
     |          |
     1 --- 2    4

Output: 2
Example 2:

Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]

     0           4
     |           |
     1 --- 2 --- 3

Output:  1
Note:
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

 */
public class NumberOfComponentsInUnidirectedGraph {

    public static void main(String[] args) {
     int nodes = 4;
     int[][] edges = {
             {1,2},
             {2,3},
             {3,1}
     };

     int numberOfComponents = countComponentsBFS(nodes, edges);
     System.out.println(numberOfComponents);
    }



    //------ BFS ------------------
    private static int countComponentsBFS(int nodesCount, int[][] edges) {
        int res = 0;

        List<List<Integer>> graph = new ArrayList<>();

        Set<Integer> visited = new HashSet<>();

        //prepare graph with empty lists
        for (int i = 0; i < nodesCount; ++i){
                graph.add(new ArrayList<>());
        }

        //just pre-fill the graph with edges nde: list of connected edges to it
        for (int[] edge : edges) {

            final int from = edge[0];
            final int to = edge[1];

            graph.get(from).add(to);
            graph.get(to).add(from);

        }

        for (int node = 0; node < nodesCount; ++node){ // traverse all nodes

            if (!visited.contains(node)) {//if not seen

                BFS(graph, node, visited);//run BFS
                ++res;

            }
        }

        return res;
    }

    private static void BFS(List<List<Integer>> graph, int node, Set<Integer> visited) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(node);

        visited.add(node);//mark current  as visited

        while (!queue.isEmpty()) {

            final int currentNode = queue.poll();//from top of queue

            for (final int connectedNode : graph.get(currentNode)){//for all connected nodes

                if (!visited.contains(connectedNode)) {//if connected node is not yet visisted

                    visited.add(connectedNode);//mark connected  as visited

                    queue.offer(connectedNode);//add to queue

                }
            }
        }

    }


    // --------- DFS ------------------

    public int countComponents(int nodesCount, int[][] edges) {
        int ans = 0;

        List<List<Integer>> graph = new ArrayList<>();

        Set<Integer> seen = new HashSet<>();

        for (int i = 0; i < nodesCount; ++i){
            graph.add(new ArrayList<>());//pre-fill with empty lists
        }

        //pre - populate graph : node : list of edges connected to it
        for (int[] edge : edges) {

            final int from = edge[0];
            final int to = edge[1];

            graph.get(from).add(to);
            graph.get(to).add(from);

        }

        for (int node = 0; node < nodesCount; ++node){

            if (seen.add(node)) {

                dfs(graph, node, seen);

                ++ans;
            }

        }


        return ans;
    }

    private void dfs(List<List<Integer>> graph, int node, Set<Integer> seen) {

        for (final int connectedNodes  : graph.get(node)){

            if (seen.add(connectedNodes)){

                dfs(graph, connectedNodes, seen);

            }

        }
    }

}
