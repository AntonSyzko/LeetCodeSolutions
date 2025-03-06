package leet.code.solutions.binary_tree;

import java.util.Stack;

/*
Given the root of a binary tree, return its maximum depth.

A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: 3

Example 2:
Input: root = [1,null,2]
Output: 2

Constraints:  the number of nodes in the tree is in the range [0, 104].-100 <= Node.val <= 100


 */
public class MaxDepthOfBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode rootLeftFirst = new TreeNode(9);
        TreeNode rootRightFirst = new TreeNode(20);
        TreeNode rootRightLeft = new TreeNode(15);
        TreeNode rootRightRight = new TreeNode(7);

        root.left = rootLeftFirst;
        root.right = rootRightFirst;
        root.right.left = rootRightLeft;
        root.right.right = rootRightRight;

        int res = maxDepthOfBinTree(root);
        System.out.println(res);
    }

    public static int maxDepthOfBinTree(TreeNode root) {
        if( root == null){
            return 0;
        }

        return countDepth(root, 1) ;
    }


   public static int countDepth(TreeNode root, int depth) {
        if(root==null){
            return 0;
        }

     if(root.left==null && root.right==null){
         return depth;
     }

     return  Math.max(countDepth(root.left, depth + 1), countDepth(root.right, depth + 1));

    }

    private static int answer = 0;

    public static void maxDepth2(TreeNode root,  int depth) {
        if(root == null){
            return ;
        }

        if(root.left == null && root.right == null){
            answer  = Math.max(answer,depth);
        }

        maxDepth2(root.left, depth + 1);
        maxDepth2(root.right, depth + 1);

    }



        // O(n) runtime
    // bottom up recursion - we reach bottom NULL and then aggregate ALL
    public static int maxDepth(TreeNode root) {
        if(root == null) { //bottom max in tree branch will hit null and exit recursion
            return 0;
        }

        int leftBranchesDFS = maxDepth(root.left);
        int rightBranchesDFS = maxDepth(root.right);

        //going down the tree branch we will aggregate level count ( by plus one each time )
        int leftOrRightMaxDepth = Math.max(leftBranchesDFS, rightBranchesDFS);//max of left OR right most deep tree branches

        return leftOrRightMaxDepth + 1; //plus ONE since we are ABOVE current calc level
    }
}
