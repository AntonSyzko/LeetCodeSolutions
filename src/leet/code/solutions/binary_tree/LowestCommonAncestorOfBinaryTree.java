package leet.code.solutions.binary_tree;

/*
https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/

Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.

Example 2:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.

Example 3:

Input: root = [1,2], p = 1, q = 2
Output: 1
 */
public class LowestCommonAncestorOfBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);


        TreeNode lowestCommonAncestor = lowestCommonAncestor(root, root.left, root.right);
        System.out.println(lowestCommonAncestor.val);
    }


    private static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base case 1: If root is null, return null
        if (root == null) {
            return null;
        }

        // Base case 2: If root is either p or q, return root
        if (root == p || root == q) {
            return root;
        }

        // Recursively search for p and q in left and right subtrees
        TreeNode leftLCA = lowestCommonAncestor(root.left, p, q);
        TreeNode rightLCA = lowestCommonAncestor(root.right, p, q);

        // If both left and right subtrees return non-null, root is the LCA
        if (leftLCA != null && rightLCA != null) {
            return root;
        }

        // If only left subtree contains p or q, return left result
        if (leftLCA != null) {
            return leftLCA;
        }

        //it's like else here below 
        // If only right subtree contains p or q, return right result
        return rightLCA;
    }

    
    
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }

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
