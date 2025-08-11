package leet.code.solutions.priority_queue;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/*
https://neetcode.io/problems/kth-smallest-integer-in-bst

Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) in the tree.

A binary search tree satisfies the following constraints:

The left subtree of every node contains only nodes with keys less than the node's key.
The right subtree of every node contains only nodes with keys greater than the node's key.
Both the left and right subtrees are also binary search trees.

Input: root = [2,1,3], k = 1

Output: 1


Input: root = [4,3,5,2,null], k = 4

Output: 5
 */
public class KSmallestIntegerInBST {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        int k = 1;
        int kSmallest = kthSmallestHeap(root, k);
        System.out.println(kSmallest);
    }

    //BFS
    private static int kthSmallestHeap(TreeNode root, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b)-> b - a);

        Queue<TreeNode> bfsQueue = new LinkedList<>();
        bfsQueue.add(root);

        while (!bfsQueue.isEmpty()) {
            TreeNode node = bfsQueue.poll();

            if(node != null){
                maxHeap.add( node.val);

                if (maxHeap.size() > k) {
                    maxHeap.poll();
                }
                bfsQueue.add(node.left);
                bfsQueue.add(node.right);
            }
        }

        return maxHeap.peek();
    }

    private   static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}