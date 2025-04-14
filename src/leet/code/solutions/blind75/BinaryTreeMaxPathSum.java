package leet.code.solutions.blind75;

/*
https://leetcode.com/problems/binary-tree-maximum-path-sum/

A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them.
 A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

The path sum of a path is the sum of the node's values in the path.

Given the root of a binary tree, return the maximum path sum of any non-empty path.



Example 1:


Input: root = [1,2,3]
Output: 6
Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
Example 2:


Input: root = [-10,9,20,null,null,15,7]
Output: 42
Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.

 */
public class BinaryTreeMaxPathSum {


    public static void main(String[] args) {
         TreeNode root = new TreeNode(1);
         root.left = new TreeNode(2);
         root.right = new TreeNode(3);

         root.left.left = new TreeNode(4);
         root.left.right = new TreeNode(5);

         root.right.left = new TreeNode(10);
         root.right.right = new TreeNode(20);
         root.right.left.left = new TreeNode(30);
         root.right.right.right = new TreeNode(40);

         int maxPathSum = maxPathSum(root);
         System.out.println(maxPathSum);
    }


    private static int maxSUm = Integer.MIN_VALUE;

    private  static int maxPathSum(TreeNode root) {
      if(root==null) return 0;

        maxGain2(root);

       return maxSUm;
    }

    private static int maxGain(TreeNode node) {
        if(node==null) return 0;//BASE , as well no gain

        int leftGain = maxGain(node.left);//how much can we gain from adding nodes from left connected edges
        int rightGain = maxGain(node.right);

        int priceNewPAth = node.val + leftGain + rightGain;//accumulated path as we recursively go

        maxSUm = Math.max(maxSUm, priceNewPAth);//update max as we go

        return node.val + Math.max(leftGain, rightGain); //as we continue adding to  current node value whatever BIGGEST value LEFT or RIGHT gain can offer
    }

    private static int maxGain2(TreeNode node) {
        if(node==null) return 0;//BASE , as well no gain

        int leftGain = maxGain2(node.left);//how much can we gain from adding nodes from left connected edges
        int rightGain = maxGain2(node.right);

        int priceNewPAth = node.val + leftGain + rightGain;//recursively accumulated path as we recursively go ( will stop only at BASE)

        maxSUm = Math.max(maxSUm, priceNewPAth);//update max as we go

        int nodeLeftVal = node.left == null ? 0 : node.left.val;
        int nodeRightVal = node.right == null ? 0 : node.right.val;

        return node.val + Math.max(nodeLeftVal, nodeRightVal); //as we continue adding to  current node value whatever BIGGEST value LEFT or RIGHT gain can offer
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
