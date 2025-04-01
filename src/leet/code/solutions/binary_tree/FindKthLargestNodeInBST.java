package leet.code.solutions.binary_tree;

import java.util.concurrent.atomic.AtomicInteger;

/*
https://www.techiedelight.com/find-kth-smallest-largest-element-bst/

Given a BST and a positive number k, find the k'th largest node in the BST.

For example, consider the following binary search tree. If k = 2,

 the k'th largest node is 20.
 */
public class FindKthLargestNodeInBST {

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
        root.right = new Node(20);//< --- second largest
        root.left.left = new Node(8);
        root.left.right = new Node(12);
        root.right.left = new Node(16);
        root.right.right = new Node(25);

        int k = 2;

        // find the k'th smallest node
        Node result = kthLargest(root, k);

        if (result != null) {
            System.out.printf("%d'th largest node is %d", k, result.data);
        }
        else {
            System.out.printf("%d'th largest node does not exist.", k);
        }

    }

    private static Node kthLargest(Node node, int largestK) {
        AtomicInteger counter = new AtomicInteger(0);

        return kthLargest(node, largestK, counter);
    }


        private static Node kthLargest(Node node, int largestK,  AtomicInteger currentK) {
            //BASE
            if(node==null){
                return null;
            }

             Node right = kthLargest(node.right, largestK, currentK);// mind right first - it is reversed in order traversal
              if(right != null){
                  return right;
              }

              if( currentK.incrementAndGet()  == largestK){
                  return node;
              }
              return kthLargest(node.left, largestK, currentK);

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
