package leet.code.solutions.graphs;

import java.util.ArrayList;
import java.util.List;

/*
https://neetcode.io/problems/count-connected-components

There is an undirected graph with n nodes. There is also an edges array, where edges[i] = [a, b] means that there is an edge between node a and node b in the graph.

The nodes are numbered from 0 to n - 1.

Return the total number of connected components in that graph.

Example 1:

Input:
n=3
edges=[[0,1], [0,2]]

Output:
1
Example 2:

Input:
n=6
edges=[[0,1], [1,2], [2,3], [4,5]]

Output:
2
Constraints:

1 <= n <= 100
0 <= edges.length <= n * (n - 1) / 2

 */
public class ConnectedComponentsInUndirectedGraph {

    public static void main(String[] args) {
        int n = 6;
        int[][] edges = {{0,1}, {1,2}, {2,3}, {4,5}};
        int connectedComponents = countComponents(n, edges);
        System.out.println(connectedComponents);

    }

//leetcdoe 547

    /*
 Time & Space Complexity
     Time complexity:

              O(V+E)
     Space complexity:

             O(V+E)
     Where V is the number vertices and E is the number of edges in the graph.
  */
    private static int countComponents(int n, int[][] edges) {

        List<List<Integer>> adjacencyList = new ArrayList<>();
        for(int i = 0 ; i < n; i++){
            adjacencyList.add(i, new ArrayList<>());
        }

        for(int[] edge : edges){
            adjacencyList.get(edge[0]).add(edge[1]);//both cause graph is UNDIRECTED
            adjacencyList.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int res = 0;

        for(int i = 0 ; i < n; i++){

            if(!visited[i]) {//not seen before
                //recursion will explore all connected edges and mark as visisted, hence if not visited it is a new component that we need to explore
                dfs(i, visited, adjacencyList);
                res++;
            }
        }

        return res;
    }


    private static void dfs(int edge, boolean[] visited , List<List<Integer>> adjacencyList) {
        if(visited[edge])  {
            return;
        }

        visited[edge] = true;//mark as seen

        List<Integer> adjacentNodes = adjacencyList.get(edge);
        for(int adjEdge : adjacentNodes){
            dfs(adjEdge, visited, adjacencyList);
        }
    }
}
