package leet.code.solutions.binary_search;

/*
https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/

Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus the sum of all keys greater than the original key in BST.
As a reminder, a binary search tree is a tree that satisfies these constraints:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.


Example 1:
Input: root = [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]

Example 2:
Input: root = [0,null,1]
Output: [1,null,1]

Constraints:

The number of nodes in the tree is in the range [1, 100].
0 <= Node.val <= 100
All the values in the tree are unique.

 */

import leet.code.solutions.binary_tree.TreeNode;

public class BST_ToGreaterSumTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode rootL = new TreeNode(3);
        TreeNode rootR = new TreeNode(10);
        TreeNode rootLR = new TreeNode(4);
        TreeNode rootLL = new TreeNode(2);
        TreeNode rootRR = new TreeNode(20);
        root.left = rootL;
        root.right = rootR;
        rootL.left = rootLL;
        rootL.right = rootLR;
        rootR.right = rootRR;

        bstToGst(root);

       // System.out.println(bstToGst(root));

    }

   private static int aggregatingSum = 0;

    private static TreeNode bstToGst(TreeNode root) {

        calculateSum(root);

        return root;
    }

    private static void calculateSum(TreeNode<Integer> currentNode) {
        if (currentNode == null) {//recursion base
            //System.out.println("\r\n\t end of recursion");
            return;
        }
       // System.out.println("calculating for " + currentNode.val);


        calculateSum(currentNode.right);//DFS to the RIGHT bottom ( stck will contain vals AND future method calls
        aggregatingSum += currentNode.val;
       // System.out.println("aggregating " + aggregatingSum);
        currentNode.val = aggregatingSum;//reset current NODE val
        calculateSum(currentNode.left);//DFS to LEFT bottom ( unless right ones are met - these will take priority )

    }
}