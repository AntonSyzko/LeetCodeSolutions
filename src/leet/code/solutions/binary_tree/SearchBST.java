package leet.code.solutions.binary_tree;

/*
You are given the root of a binary search tree (BST) and an integer val.
Find the node in the BST that the node's value equals val and return the subtree rooted with that node.
If such a node does not exist, return null.

Example 1:
Input: root = [4,2,7,1,3], val = 2
Output: [2,1,3]

Example 2:
Input: root = [4,2,7,1,3], val = 5
Output: []

Constraints:
The number of nodes in the tree is in the range [1, 5000].
1 <= Node.val <= 107
root is a binary search tree.
1 <= val <= 107
 */
public class SearchBST {

    public static void main(String[] args) {
        TreeNode<Integer> root = new  TreeNode<Integer>(4);
        TreeNode<Integer> rootLeft = new  TreeNode<Integer>(2);
        TreeNode<Integer> rootRight = new  TreeNode<Integer>(7);
        TreeNode<Integer> rootLeftLeft = new  TreeNode<Integer>(1);
        TreeNode<Integer> rootLeftRight = new  TreeNode<Integer>(3);
        root.left = rootLeft;
        rootLeft.left = rootLeftLeft;
        rootLeft.right = rootLeftRight;
        root.right = rootRight;

        TreeNode<Integer> res = searchBST(root, 2);
        System.out.println(res);
    }

    private static  TreeNode<Integer> searchBST( TreeNode<Integer> root, int val) {
        if (root == null) {//base case for recursion
            return null;
        }

        if (root.val == val) {//main check
            return root;
        }

        //since BST - we can check only Left and Right  branches
        if (val < root.val) {//potential val is at the LEFT
            return searchBST(root.left, val);
        } else {//potential val is at the LEFT
            return searchBST(root.right, val);
        }
    }
}
