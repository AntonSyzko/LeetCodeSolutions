package leet.code.solutions.graphs;

import java.util.*;

/*
https://neetcode.io/problems/clone-graph

Given a node in a connected undirected graph, return a deep copy of the graph.

Each node in the graph contains an integer value and a list of its neighbors.

class Node {
    public int val;
    public List<Node> neighbors;
}
The graph is shown in the test cases as an adjacency list. An adjacency list is a mapping of nodes to lists, used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.

For simplicity, nodes values are numbered from 1 to n, where n is the total number of nodes in the graph. The index of each node within the adjacency list is the same as the node's value (1-indexed).

The input node will always be the first node in the graph and have 1 as the value.

Example 1:

Input: adjList = [[2],[1,3],[2]]

Output: [[2],[1,3],[2]]
Explanation: There are 3 nodes in the graph.
Node 1: val = 1 and neighbors = [2].
Node 2: val = 2 and neighbors = [1, 3].
Node 3: val = 3 and neighbors = [2].

Example 2:

Input: adjList = [[]]

Output: [[]]
Explanation: The graph has one node with no neighbors.

Example 3:

Input: adjList = []

Output: []
Explanation: The graph is empty.

Constraints:

0 <= The number of nodes in the graph <= 100.
1 <= Node.val <= 100
There are no duplicate edges and no self-loops in the graph.
 */
public class CloneGraph {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        node1.neighbors = List.of(node2);
        node2.neighbors = List.of(node1, node3);
        node3.neighbors = List.of(node2);

        Node deepCopy = cloneGraph(node1);

    }

    private static Node cloneGraph(Node node) {
        if(node == null) return null;

        Map<Node,Node> copiesMap = new HashMap<>();

        return dfsClone(node, copiesMap);
    }

    /*
    Time & Space Complexity
            Time complexity:
                  O(V+E)
            Space complexity:
                      O(V)
            Where
                     V is the number of vertices and  E is the number of edges.
     */
    private static Node dfsClone(Node node, Map<Node,Node> copiesMap) {
        if(node == null) {
            return null;
        }

        if(copiesMap.containsKey(node)){//seen before
            return copiesMap.get(node);
        }

        Node copy = new Node(node.val);//make a copy
        copiesMap.put(node, copy);//store in copy map

        for(Node neighbor : node.neighbors){//process all neighbors

            copy.neighbors.add(
                    dfsClone(neighbor, copiesMap)//clone each neighbor
            );

        }

        return copy;
    }

    private static Map<Node, Node> map = new HashMap<>();

    private static Node cloneGraph2(Node node) {
        if (node == null)
            return null;

        if (map.containsKey(node))
            return map.get(node);

        Node newNode = new Node(node.val);
        map.put(node, newNode);

        for (Node neighbor : node.neighbors)
            newNode.neighbors.add(
                    cloneGraph2(neighbor)//recur
            );

        return newNode;
    }

    private  static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

}
