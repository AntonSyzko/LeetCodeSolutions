package leet.code.solutions.binary_tree;

/*
https://leetcode.com/problems/symmetric-tree/
Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).

Input: root = [1,2,2,3,4,4,3]
Output: true

Input: root = [1,2,2,null,3,null,3]
Output: false

Constraints:
The number of nodes in the tree is in the range [1, 1000].
-100 <= Node.val <= 100
 */
public class SymmetricBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left1 = new TreeNode(2);
        TreeNode right1 = new TreeNode(2);
        TreeNode lett1left = new TreeNode(3);
        TreeNode left1right = new TreeNode(4);
        TreeNode right1left = new TreeNode(4);
        TreeNode right1right = new TreeNode(3);

        root.left = left1;
        root.right = right1;
        left1.left = lett1left;
        left1.right = left1right;
        right1.left = right1left;
        right1.right = right1right;

        boolean isSymmetric = isSymmetric(root);
        System.out.println(isSymmetric);


    }

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {//null only - technically symmetric
            return true;
        }

        return checkSymmetry(root.left, root.right);
    }

    private static boolean checkSymmetry(TreeNode left, TreeNode right) {

        if (left == null || right == null) { // if one of passed is NULL
            return left == right; //check if BOTH are NULL to be symmetric
        }

        if (left.val != right.val) { //actual comparison of VALs
            return false;
        }

        return checkSymmetry(left.left, right.right) //outbound left and right compare  ( 3 _ X  &&  X _ 3 )
            &&
            checkSymmetry(left.right, right.left); //inner right and left compare       (X _ 5  &&  5 _ X )
    }


}
