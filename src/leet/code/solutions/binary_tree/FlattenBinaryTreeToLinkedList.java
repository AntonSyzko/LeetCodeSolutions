package leet.code.solutions.binary_tree;

import leet.code.solutions.sandbox.Sandbox1;

import java.util.Stack;

/*
https://leetcode.com/problems/flatten-binary-tree-to-linked-list/

Given the root of a binary tree, flatten the tree into a "linked list":
The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
The "linked list" should be in the same order as a pre-order traversal of the binary tree.


Example 1:
Input: root = [1,2,5,3,4,null,6]
Output: [1,null,2,null,3,null,4,null,5,null,6]

Example 2:
Input: root = []
Output: []

Example 3:
Input: root = [0]
Output: [0]

Constraints:
The number of nodes in the tree is in the range [0, 2000].
-100 <= Node.val <= 100
 */
public class FlattenBinaryTreeToLinkedList {

    public static void main(String[] args) {
     TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(5);

        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);

        root.right.right = new TreeNode(6);

        System.out.println(root);

        flatten(root);


        System.out.println(root);
    }

    // This will keep track of the previous node in pre-order traversal
    private  static TreeNode prev = null;

    private static void flattenRecursive(TreeNode root) {
        if (root == null) return;

        // Process right first, then left, then root (reverse pre-order)
        flattenRecursive(root.right);
        flattenRecursive(root.left);

        // Set current node's right to previous node
        root.right = prev;
        // Set left to null
        root.left = null;
        // Update previous node
        prev = root;

    }


    private static void flatten(TreeNode root) {
        if(root == null) return;

        Stack<TreeNode> stack = new Stack<>();//try Deque array deque todo
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode currentNode = stack.pop();//pop removes obj

            if(currentNode.right != null) {
                stack.push(currentNode.right);
            }

            if (currentNode.left != null) {
                stack.push(currentNode.left);//LEFT nodes stay on top of stack in whole while iteration
            }

//here to account if root was only one and stack pop from line 42 will give nothing
            if(!stack.isEmpty()) {
                //re-assign right node to previously LEFT node - since LEFT is now on top of STACK
                currentNode.right = stack.peek();//peek does NOT remove
            }
            //left nodes are eliminated
            currentNode.left = null;
        }
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
