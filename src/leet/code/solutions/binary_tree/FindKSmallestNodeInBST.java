package leet.code.solutions.binary_tree;

import java.util.concurrent.atomic.AtomicInteger;

/*
https://www.techiedelight.com/find-kth-smallest-node-binary-search-tree/

Given a BST and a positive number k, find the k'th smallest node in it.

For example, the 4th smallest node in the following BST is 15, and the 6th smallest is 20. The 8th smallest node does not exist.
 */
public class FindKSmallestNodeInBST {

    public static void main(String[] args) {
              /* Construct the following BST
                  15
                /    \
               /      \
              10       20
             /  \     /  \
            /    \   /    \
           8     12 16    25
        */

        Node root = new Node(15); // < ---- 4'th smallest
        root.left = new Node(10);
        root.right = new Node(20);
        root.left.left = new Node(8);
        root.left.right = new Node(12);
        root.right.left = new Node(16);
        root.right.right = new Node(25);

        int k = 4;

        // find the k'th smallest node
        Node result = findKthSmallest(root, k);

        if (result != null) {
            System.out.printf("%d'th smallest node is %d", k, result.data);
        }
        else {
            System.out.printf("%d'th smallest node does not exist.", k);
        }
    }

    private static Node findKthSmallest(Node root, int k) {

        // Counter to keep track of the total number of the visited nodes.
        // `AtomicInteger` is used here since `Integer` is passed by value in Java
        //each thread stack will have A LOCAL COPY BUT WE NEED ONE FOR ALL
        AtomicInteger counter = new AtomicInteger(0);

        return  findKSmallest(root, counter, k);
    }

    private static Node findKSmallest(Node root, AtomicInteger counter, int k) {
        if( root == null){
            return null;
        }

        Node left = findKSmallest(root.left, counter, k);// left first
        if(left != null){
            return left;
        }

        if(counter.incrementAndGet() == k){
            return root;
        }

        return findKSmallest(root.right, counter, k);
    }


    // A class to store a BST node
    static class Node {
        int data;
        Node left = null, right = null;

        Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
