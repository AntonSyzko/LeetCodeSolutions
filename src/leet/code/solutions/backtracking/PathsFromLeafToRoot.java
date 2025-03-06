package leet.code.solutions.backtracking;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class PathsFromLeafToRoot {

    public static void main(String[] args) {
        /* Construct the following tree
                   1
                 /   \
                /     \
               /       \
              2         3
             / \       / \
            /   \     /   \
           4     5   6     7
                    / \
                   /   \
                  8     9
        */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.left.left = new Node(8);
        root.right.left.right = new Node(9);

        // print all leaf-to-root paths
        printLeafToRootPaths(root);
    }

    private static void printLeafToRootPaths(Node root) {

        Deque<Node> stack = new ArrayDeque<>();
        printLeafToRootPath(root, stack);

    }

    private static void printLeafToRootPath(Node node, Deque<Node> stack) {
        if(node==null){//BASE
            return;
        }

        stack.addLast(node);

        if(node.left==null && node.right==null){//LEAF
            printPath(stack);
        }

        printLeafToRootPath(node.left, stack);
        printLeafToRootPath(node.right, stack);

        stack.removeLast();//BACKTRACK
    }

    private static void printPath(Deque<Node> stack) {
        Iterator<Node> iterator = stack.descendingIterator();//remove reverse fro root to leaf
        while(iterator.hasNext()){
            Node node = iterator.next();
            System.out.print(node.data+" -> ");
        }
        System.out.println();
    }


    static class Node {
        int data;
        Node left = null, right = null;

        Node(int data) {
            this.data = data;
        }
    }
}
