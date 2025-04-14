package leet.code.solutions.binary_tree;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/

Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.

Example 1:
Input: preorder = [3,9,20,15,7],
 inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]

Example 2:

Input: preorder = [-1],
 inorder = [-1]
Output: [-1]

Constraints:

1 <= preorder.length <= 3000
inorder.length == preorder.length
-3000 <= preorder[i], inorder[i] <= 3000
preorder and inorder consist of unique values.
Each value of inorder also appears in preorder.
preorder is guaranteed to be the preorder traversal of the tree.
inorder is guaranteed to be the inorder traversal of the tree.
 */
public class ConstructBinaryTreeFromPreOrderInOrderTraversal {

    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int [] inorder = { 9,3,15,20,7 };

        TreeNode root = buildTree(preorder,inorder);
        System.out.println(root);

    }


    private  static Map<Integer, Integer> inOrderIndexMap = new HashMap<>();
    private  static int  preOrderIndex = 0;

    // O(N) both
    private static  TreeNode buildTree(int[] preorder, int[] inorder) {

        for (int i = 0; i < inorder.length; i++) {
            inOrderIndexMap.put(inorder[i], i);//in-order value to it's index
        }

        return arrayToTree(preorder, 0, preorder.length-1);
    }

    private static TreeNode arrayToTree(int[] preorder, int left, int right) {

        if(left > right){//BASE
            return null;
        }

        TreeNode root = new TreeNode(preorder[preOrderIndex]);
        preOrderIndex++;

        //build entire LEFT subtree
        root.left = arrayToTree(preorder, left, inOrderIndexMap.get(root.val)-1);//get(root.val)-1 cause in in-order left vals are at left of ROOT

        //build entire RIGHT subtree
        root.right = arrayToTree(preorder, inOrderIndexMap.get(root.val) +1, right);//inOrderIndexMap.get(root.val) +1  cause in in-order right vals are to the RIGH or root

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


