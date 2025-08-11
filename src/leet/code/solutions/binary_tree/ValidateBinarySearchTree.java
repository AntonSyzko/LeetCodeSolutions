package leet.code.solutions.binary_tree;

import leet.code.solutions.sandbox.Sandbox1;

import java.util.Stack;

/*
https://leetcode.com/problems/validate-binary-search-tree/

Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
 
Example 1:
Input: root = [2,1,3]
Output: true

Example 2:
Input: root = [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.

Constraints:

The number of nodes in the tree is in the range [1, 104].
-231 <= Node.val <= 231 - 1

 */
public class ValidateBinarySearchTree {

    public static void main(String[] args) {
        /*
                 4
              2       6
            1       5    7
         */
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(5);//3 to false
        root.right.right = new TreeNode(7);

        boolean isValidBST = isValidBST(root);

        System.out.println(isValidBST);
    }

    private  static Integer previousNodeVal;

    private static boolean isValidBST(TreeNode root) {
        if(root == null) return true;

        previousNodeVal = null;

        return inOrder(root);
    }

/*
   in BST from BOTTOM leftmost node to BOTTOM rightmost mode values will be ASCENDING sorted ( 1,2,33,445 .....etc )
 */
    private static boolean inOrder(TreeNode node){
        if(node==null) {//BASE
            return true;
        }

        if (!inOrder(node.left)) {//if on left subtree any non-sorted met - already FALSE -> quit
            return false;
        }

       if(previousNodeVal != null && node.val <= previousNodeVal){//if current node VAL is LESS or == previous -> non SORTED - FALSE
           return false;
       }

       previousNodeVal = node.val;//update prev to current for further comparisons

       return inOrder(node.right);//finally if any false on right subtree - it will determine the answer as we have passed left subtree check at line 69
    }


    private static boolean isValidBST2(TreeNode root) {
        return validateBST2(root, Long.MIN_VALUE, Long.MAX_VALUE);//min max to start with
    }

    private static boolean validateBST2(TreeNode node, long min, long max) {
        if (node == null) return true;

        // Current node's value must be between min and max
        if (node.val <= min || node.val >= max) {
            return false;
        }

        // Validate left subtree (all values must be less than node.val)
        return validateBST2(node.left, min, node.val)

                &&
                // Validate right subtree (all values must be greater than node.val)
                validateBST2(node.right, node.val, max);
    }

    
    private static boolean isValidBSTIter(TreeNode root) {
        if (root == null) return true;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        TreeNode prev = null;  // Tracks the previous node in inorder traversal

        // In-order traversal (left, root, right)
        while (curr != null || !stack.isEmpty()) {
            // Traverse to leftmost node
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            // Process current node
            curr = stack.pop();

            // In a valid BST, IN-ORDER ( left - root - right)  traversal yields values in ASCENDING order
            // So each node must have value GREATER than the previous node
            if (prev != null && curr.val <= prev.val) {
                return false;
            }

            // Update previous node and move to right subtree
            prev = curr;
            curr = curr.right;
        }

        return true;
    }

    public  static  boolean isValidBST3(TreeNode root) {
        return validateBSTree(root, null, null);
    }

    private static boolean validateBSTree(TreeNode node, Integer min, Integer max) {
        if(node == null){
            return true;
        } else if ( (max != null && node.val >= max) && (min != null && node.val <= min) ) {
            return false;
        }else{
            return validateBSTree(node.left, min, node.val) && validateBSTree(node.right, node.val, max);
        }
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
