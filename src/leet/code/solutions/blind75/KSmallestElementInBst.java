package leet.code.solutions.blind75;

import leet.code.solutions.binary_tree.BinaryTreeInOrderTraversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
https://leetcode.com/problems/kth-smallest-element-in-a-bst/

Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.

Example 1:
Input: root = [3,1,4,null,2], k = 1
Output: 1

Example 2:
Input: root = [5,3,6,2,4,null,null,1], k = 3
Output: 3

Constraints:

The number of nodes in the tree is n.
1 <= k <= n <= 104
0 <= Node.val <= 104
 */
public class KSmallestElementInBst {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);

        root.left = new TreeNode(3);
        root.right = new TreeNode(8);

        root.left.left = new TreeNode(1);

        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        root.right.right.right = new TreeNode(10);

        int kSmalest = kthSmallest(root, 2);
        System.out.println(kSmalest);

    }

/*
        idea is that for BST in-order traversal always yields ASC sorted results ( 1,2,33,666 .... etc )
        so if values are 1,2,3 and k = 2 - smallest element is ust at 2 position
 */
    private static int kthSmallest(TreeNode root, int k) {

        List<Integer> inOrderList = new ArrayList<>();

         inOrder(root, inOrderList);

         if(k > inOrderList.size()){//K exceeds list size
             return -1;
         }
        
        return inOrderList.get(k-1) ;//we are (1-indexed)

    }
    
    
    private static void inOrder(TreeNode root, List<Integer> inOrderList) {
        if(root==null) {//base
            return;
        }

        inOrder(root.left, inOrderList);//goes as deep as all LEFT nodes and on top will be the furthermost LEFT

        //so all lefts are inserted in list
        inOrderList.add(root.val);//insert current

        //goes as deep as all rights starting with smalles right ( due to BST nature ) , but in just binary tree will be just further RIGHTs at the end
        inOrder(root.right, inOrderList);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
