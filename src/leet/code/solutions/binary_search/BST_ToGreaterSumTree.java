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

import java.util.Stack;

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

        System.out.println(bstToGst(root));

       // System.out.println(bstToGstStack(root));

    }

   private static int aggregatingSum = 0;

    private static TreeNode bstToGst(TreeNode root) {
       if(root==null) return null;

       calculateSumHelper(root);

       return root;
    }

    private static void calculateSumHelper(TreeNode currentNode) {
        //BASE
        if(currentNode == null) {
            return;
        }

        calculateSumHelper(currentNode.right);//dive as far right as possible

        aggregatingSum += currentNode.val;//aggregate sum as we dive deep

        currentNode.val = aggregatingSum;//override current node's value with aggregated sum

        calculateSumHelper(currentNode.left);//dive as far left as possible

    }


    private static TreeNode bstToGstStack(TreeNode root) {
        if (root == null) return null;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode currentNode = root;
        int sum = 0;

        // Reverse in-order traversal using a stack
        while (!stack.isEmpty() || currentNode != null) {
            // Go as far right as possible
            while (currentNode != null) {

                stack.push(currentNode);

                currentNode = currentNode.right;//continue pushing right subtree nodes as long as there are some

            }

            // Process the current node
            currentNode = stack.pop();
            sum += currentNode.val;     // Add the original value to our running sum
            currentNode.val = sum;      // Update the node's value

            // Move to the left subtree
            currentNode = currentNode.left;
        }

        return root;
    }



    private static class TreeNode {
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
