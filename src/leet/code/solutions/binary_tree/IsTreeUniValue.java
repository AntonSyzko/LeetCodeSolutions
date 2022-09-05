package leet.code.solutions.binary_tree;

/*
https://leetcode.com/problems/univalued-binary-tree/

A binary tree is uni-valued if every node in the tree has the same value.
Given the root of a binary tree, return true if the given tree is uni-valued, or false otherwise.

Example 1:
Input: root = [1,1,1,1,1,null,1]
Output: true

Example 2:
Input: root = [2,2,2,5,2]
Output: false

Constraints:
The number of nodes in the tree is in the range [1, 100].
0 <= Node.val < 100
 */
public class IsTreeUniValue {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode rootL1 = new TreeNode(1);
        TreeNode rootR1 = new TreeNode(1);//set to any but 1 to get false
        root.left = rootL1;
        root.right = rootR1;
        System.out.println(isUnivalTree(root));

    }

    private static boolean isUnivalTree(TreeNode root) {
        boolean isLeftSubtreeUnivalue = root.left == null //if this leaf is null - than nothing to traverse - no recursive call needed
            ||
            root.val == root.left.val // values of current and its left child are SAME
                && isUnivalTree(root.left); //recursively for it's ALL  LEFT children

        boolean isRightSubtreeUnival = root.right == null //if this leaf is null - than nothing to traverse - no recursive call needed
            ||
            root.val == root.right.val //val of current and RIGHT child are SAME
                && isUnivalTree(root.right);//recursively check for ALL  it's RIGH children

        return isLeftSubtreeUnivalue && isRightSubtreeUnival;//compare BOTH left AND right subtrees results 
    }
}
