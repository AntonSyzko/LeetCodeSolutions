package leet.code.solutions.binary_tree;

/*
https://leetcode.com/problems/invert-binary-tree/

Given the root of a binary tree, invert the tree, and return its root.

Example 1:
Input: root = [4,2,7,1,3,6,9]
Output: [4,7,2,9,6,3,1]

Example 2:
Input: root = [2,1,3]
Output: [2,3,1]

Example 3:
Input: root = []
Output: []

Constraints: The number of nodes in the tree is in the range [0, 100].  -100 <= Node.val <= 100
 */
public class InvertBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(7);
        TreeNode left_left = new TreeNode(1);
        TreeNode left_right = new TreeNode(3);
        TreeNode right_left = new TreeNode(6);
        TreeNode right_right = new TreeNode(9);
        root.left = left;
        root.right = right;
        root.right = right;
        root.left.left = left_left;
        root.left.right = left_right;
        root.right.left = right_left;
        root.right.right = right_right;

        System.out.println(root);
        System.out.println("======================");


        TreeNode inverted = invertTree(root);
        System.out.println(inverted);
    }

    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = right;
        root.right = left;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    public static TreeNode invertTreeOptimized(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = root.left;

        root.left = root.right;
        root.right = left;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}
