package leet.code.solutions.DFS;


/*
Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.

A leaf is a node with no children.

Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
Output: true
Explanation: The root-to-leaf path with the target sum is shown.

 */
public class PathSum {

    public static void main(String[] args) {

     TreeNode root = new TreeNode(1);
     TreeNode rootL = new TreeNode(2);
     TreeNode rootR = new TreeNode(3);
     TreeNode rootLL = new TreeNode(4);
     TreeNode rootLR = new TreeNode(5);//this leaf ends 8

        root.left = rootL;
        root.right = rootR;
        rootL.left = rootLL;
        rootL.right = rootLR;

        int targetSum = 8;

        boolean hasPath = hasPathSum(root, targetSum);
        System.out.println(hasPath);
    }


    private static boolean hasPathSum(TreeNode node, int targetSum) {
        if (node == null) return false;

        int sum  = targetSum  - node.val;//decrease target bu current node's value

        if (node.left == null && node.right == null) {//LEAF
            if (sum == 0) return true;//if decereased all the way through nodes ended 0 -> means there was a target path
        }

        return hasPathSum(node.left, sum ) //recur on left node
                || //if any of left or right nodes ended with zero -> retrun res
                hasPathSum(node.right, sum );//recur on right node
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
        }
    }

