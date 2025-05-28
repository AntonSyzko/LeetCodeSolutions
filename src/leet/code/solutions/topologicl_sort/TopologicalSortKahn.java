package leet.code.solutions.topologicl_sort;

import java.util.*;

public class TopologicalSortKahn {

    public static void main(String[] args) {
        // Graph: 0→1, 0→2, 1→3, 2→3
        List<int[]> edges = Arrays.asList(
                new int[]{0, 1},
                new int[]{0, 2},
                new int[]{1, 3},
                new int[]{2, 3}
        );

        List<Integer> order = topologicalSort(4, edges);
        System.out.println("Topological Order: " + order);
        // Output: [0, 1, 2, 3] or [0, 2, 1, 3]
    }

    public static List<Integer> topologicalSort(int numVertices, List<int[]> edges) {
        // Step 1: Build adjacency list and calculate in-degrees
        List<List<Integer>> adjList = new ArrayList<>();
        int[] inDegree = new int[numVertices];

        // Initialize adjacency list
        for (int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<>());
        }

        // Build graph
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            adjList.get(from).add(to);
            inDegree[to]++;
        }

        // Step 2: Find all vertices with in-degree 0
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numVertices; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Step 3: Process vertices level by level
        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            int current = queue.poll();
            result.add(current);

            // Remove current vertex from graph
            for (int neighbor : adjList.get(current)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // Step 4: Check for cycles
        if (result.size() != numVertices) {
            throw new IllegalArgumentException("Graph contains a cycle!");
        }

        return result;
    }
}

    /**
      What is Topological Sort?
      Topological Sort is a linear ordering of vertices in a Directed Acyclic Graph (DAG) such that for every directed edge u → v, vertex u comes before vertex v in the ordering.
      Key Concepts:
     
      Only works on DAGs (graphs with no cycles)
      Multiple valid orderings may exist for the same graph
      Essential for dependency resolution problems
     
      Real-World Applications
     
      Course Prerequisites - Order courses based on dependencies
      Build Systems - Compile files in correct order
      Task Scheduling - Execute tasks respecting dependencies
      Package Management - Install packages with dependencies
      Spreadsheet Formulas - Calculate cells in dependency order
     
      Visual Example
      Consider this dependency graph:
          A ──→ B ──→ D
          │     │
          ↓     ↓
          C ────→ E
      Valid topological orderings:
     
      A → B → C → D → E
      A → C → B → D → E
      A → B → C → E → D
     
      Algorithm 1: Kahn's Algorithm (BFS-based)
      Core Idea
      Always process nodes with no incoming edges first, then remove them from the graph.
      Step-by-Step Process:
     
      Calculate in-degrees for all vertices
      Add all vertices with in-degree 0 to a queue
      While queue is not empty:
     
      Remove a vertex from queue
      Add it to the result
      For each neighbor, decrease its in-degree by 1
      If neighbor's in-degree becomes 0, add it to queue
     
     
      If result contains all vertices → valid topological order
      Otherwise → cycle exists (impossible)
    **/


