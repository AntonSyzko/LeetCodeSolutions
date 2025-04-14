package leet.code.solutions.blind75;

/*
https://leetcode.com/problems/maximum-depth-of-binary-tree/

Given the root of a binary tree, return its maximum depth.

A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.



Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: 3
Example 2:

Input: root = [1,null,2]
Output: 2


Constraints:

The number of nodes in the tree is in the range [0, 104].
-100 <= Node.val <= 100
 */

public class MaxDepthOfBinTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(3);
//        root.left.left = new TreeNode(4);
//        root.left.right = new TreeNode(5);
//        root.left.right.left = new TreeNode(55);

        int maxDepth = maxDepth(root);
        System.out.println(maxDepth);
    }

    //O(n)
    private static int maxDepth(TreeNode root) {
        if(root==null) {
            return 0;
        }


        if(root.left==null && root.right==null) return 1;

        int maxDepthOnLeft = maxDepth(root.left) + 1;
        int maxDepthOnRight = maxDepth(root.right) + 1;

        return Math.max(maxDepthOnLeft, maxDepthOnRight);
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
