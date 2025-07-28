package leet.code.solutions.binary_tree;

/*
https://leetcode.com/problems/distribute-coins-in-binary-tree/description/

You are given the root of a binary tree with n nodes where each node in the tree has node.val coins. There are n coins in total throughout the whole tree.

In one move, we may choose two adjacent nodes and move one coin from one node to another.
 A move may be from parent to child, or from child to parent.

Return the minimum number of moves required to make every node have exactly one coin.

Input: root = [3,0,0]
Output: 2
Explanation: From the root of the tree, we move one coin to its left child, and one coin to its right child.


Input: root = [0,3,0]
Output: 3
Explanation: From the left child of the root, we move two coins to the root [taking two moves]. Then, we move one coin from the root of the tree to the right child.
 */
public class DistributeCoinsInBST {

    public static void main(String[] args) {
       TreeNode root = new TreeNode(3);
       root.left = new TreeNode(0);
       root.right = new TreeNode(0);

       int numOfMoves = distributeCoins(root);
       System.out.println(numOfMoves);
    }


    private static int NUM_MOVES;

    private static int distributeCoins(TreeNode root) {
        if(root==null) return 0;

         NUM_MOVES = 0;

        dfs(root);

        return NUM_MOVES;
    }

    //to evenly distribute coins amongst BST - we need to add the ABS of each and every  of BST value - 1
    private static int dfs(TreeNode node) {
        if (node == null) return 0;

        // Calculate excess/deficit in left and right subtrees
        int leftDeficit = dfs(node.left);
        int rightDeficit = dfs(node.right);

        // Update the total moves needed
        NUM_MOVES += Math.abs(leftDeficit) + Math.abs(rightDeficit);

        // Return the excess/deficit for this subtree
        // Current node's coins plus excesses from children, minus 1 (the coin we need to keep)
        return node.val + leftDeficit + rightDeficit - 1;
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
      }
}
