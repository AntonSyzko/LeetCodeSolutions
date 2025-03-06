package leet.code.solutions.binary_tree;

/*
https://leetcode.com/problems/binary-tree-maximum-path-sum/

A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

The path sum of a path is the sum of the node's values in the path.

Given the root of a binary tree, return the maximum path sum of any non-empty path.
 */
public class BinaryTreeMaxPathSum {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode rootL = new TreeNode(2);
        TreeNode rootR = new TreeNode(3);
        root.left = rootL;
        root.right = rootR;

        int maxPath = maxPathSum(root);
        System.out.println(maxPath);
    }


    private static int maxPathSum = Integer.MIN_VALUE;


    private static int maxPathSum(TreeNode<Integer> root) {
        if(root == null){
            return 0;
        }

        findMathPathDFS(root);

        return maxPathSum;
    }

    private static int findMathPathDFS(TreeNode<Integer> node) {
        if(node == null){
            return 0 ;
        }

        // Get max path sums from left and right subtrees
        // Use Math.max with 0 to handle negative values - if a path sum is negative, we're better off NOT including that path AT ALL !!!
        int leftPathSum = Math.max(0, findMathPathDFS(node.left));
        int rightPathSum = Math.max(0, findMathPathDFS(node.right));

        // Update max path by considering current node as the root of a path
        maxPathSum = Math.max(maxPathSum, node.val + leftPathSum + rightPathSum);

        // Return maximum sum of path that can be extended by parent node
        //remember we can count only left OR right node, hence pick the max of em
        return  node.val + Math.max(leftPathSum, rightPathSum);
    }
}
