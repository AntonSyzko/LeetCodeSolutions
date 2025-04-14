package leet.code.solutions.blind75;

/*
https://leetcode.com/problems/subtree-of-another-tree/


Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.

A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants. The tree tree could also be considered as a subtree of itself.



Example 1:


Input: root = [3,4,5,1,2], subRoot = [4,1,2]
Output: true
Example 2:


Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
Output: false


Constraints:

The number of nodes in the root tree is in the range [1, 2000].
The number of nodes in the subRoot tree is in the range [1, 1000].
-104 <= root.val <= 104
-104 <= subRoot.val <= 104
 */
public class SubtreeOfAnotherTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

       // root.left.right.left = new TreeNode(50);//will make false

        TreeNode subTree = new TreeNode(2);
        subTree.left = new TreeNode(4);
        subTree.right = new TreeNode(5);

        boolean isSubTree = isSubtree(root, subTree);
        System.out.println(isSubTree);


    }

    /*
    Time Complexity: O(m × n) where:

            m is the number of nodes in the main tree
            n is the number of nodes in the subtree
            In the worst case, we might need to check for a match starting from each node in the main tree


            Space Complexity: O(h) where h is the height of the main tree

            This is due to the recursion stack
            In the worst case (skewed tree), h could be O(m)
     */

private static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        /*
        Base Cases:

        If subRoot is null, we return true (an empty tree is always a subtree of any tree)
        If root is null but subRoot isn't, we return false (can't find a subtree in an empty tree)

         */
        // Base case: if the subRoot is null, it's always a subtree
        if (subRoot == null) return true;

        // If the main tree is null but subRoot is not, return false ( we know subRoot is not null , checked above)
        if (root == null) return false;

        // Check if trees match starting from the current root node
        if (isSameTree(root, subRoot)) {
            return true;
        }

        // If not, check if subRoot is a subtree of the left or right subtree of root
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    // Helper method to check if two trees are identical
private static boolean isSameTree(TreeNode p, TreeNode q) {
        // If both nodes are null, they're identical
        if (p == null && q == null) {
            return true;
        }

        // If only one is null, they're not identical
        if (p == null || q == null) {
            return false;
        }

        // Check current nodes and recursively check both subtrees
        return p.val == q.val
                &&
                isSameTree(p.left, q.left)
                &&
                isSameTree(p.right, q.right);
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
