package leet.code.solutions.topologicl_sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class TopologicalSortDFS {

    public static void main(String[] args) {
        List<int[]> edges = Arrays.asList(
                new int[]{0, 1},
                new int[]{0, 2},
                new int[]{1, 3},
                new int[]{2, 3}
        );

        List<Integer> order = topologicalSort(4, edges);
        System.out.println("Topological Order: " + order);
    }
    private enum State { UNVISITED, VISITING, VISITED }

    public static List<Integer> topologicalSort(int numVertices, List<int[]> edges) {
        // Build adjacency list
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
        }

        State[] state = new State[numVertices];
        Arrays.fill(state, State.UNVISITED);

        Stack<Integer> finishStack = new Stack<>();

        // Try DFS from each unvisited vertex
        for (int i = 0; i < numVertices; i++) {
            if (state[i] == State.UNVISITED) {
                if (!dfs(i, adjList, state, finishStack)) {
                    throw new IllegalArgumentException("Graph contains a cycle!");
                }
            }
        }

        // Reverse the finish order to get topological order
        List<Integer> result = new ArrayList<>();
        while (!finishStack.isEmpty()) {
            result.add(finishStack.pop());
        }

        return result;
    }

    private static boolean dfs(int vertex, List<List<Integer>> adjList, State[] state, Stack<Integer> finishStack) {
        if (state[vertex] == State.VISITING) {
            return false; // Back edge found - cycle detected!
        }

        if (state[vertex] == State.VISITED) {
            return true; // Already processed
        }

        state[vertex] = State.VISITING;

        // Visit all neighbors
        for (int neighbor : adjList.get(vertex)) {
            if (!dfs(neighbor, adjList, state, finishStack)) {
                return false;
            }
        }

        state[vertex] = State.VISITED;
        finishStack.push(vertex); // Add to finish order
        return true;
    }
    /*
    Practice Problems

Course Schedule (LeetCode 207) - Basic cycle detection
Course Schedule II (LeetCode 210) - Return topological order
Alien Dictionary (LeetCode 269) - Build graph from constraints
Minimum Height Trees (LeetCode 310) - Find graph centers
Parallel Courses (LeetCode 1136) - Minimum time to complete all courses
     */
}
