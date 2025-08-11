package leet.code.solutions.graphs;

import java.util.*;

public class CloneGraphBFS {

    public Node cloneGraph(Node node) {
        if (node == null)
            return null;

        Queue<Node> q = new ArrayDeque<>(List.of(node));

        Map<Node, Node> mapOfCopies = new HashMap<>();
        Node copy = new Node(node.val);
        mapOfCopies.put(node, copy);

        while (!q.isEmpty()) {

            Node nodeFromQ = q.poll();

            for (Node neighbor : nodeFromQ.neighbors) {

                if (!mapOfCopies.containsKey(neighbor)) {
                    Node neighborCopy = new Node(neighbor.val);
                    mapOfCopies.put(neighbor, neighborCopy);

                    q.offer(neighbor);
                }

                mapOfCopies.get(nodeFromQ).neighbors.add(mapOfCopies.get(neighbor));//since in neighbor for loop - adds neighbors to the parent node on each iteration
            }//for ends

        }

        return mapOfCopies.get(node);
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