package leet.code.solutions.binary_tree;
/*
https://leetcode.com/problems/binary-tree-pruning/

Given the root of a binary tree, return the same tree where every subtree (of the given tree) not containing a 1 has been removed.
Tree can contain 0 or 1 only
A subtree of a node node is node plus every node that is a descendant of node.

Example 1:
Input: root = [1,null,0,0,1]
Output: [1,null,0,null,1]
Explanation:
Only the red nodes satisfy the property "every subtree not containing a 1".
The diagram on the right represents the answer.

Example 2:
Input: root = [1,0,1,0,0,0,1]
Output: [1,null,1,null,1]

Example 3:
Input: root = [1,1,0,1,1,0,1,0]
Output: [1,1,0,1,1,null,1]

Constraints:
The number of nodes in the tree is in the range [1, 200].
Node.val is either 0 or 1
 */
public class BinaryTreePruning {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode rootLeft = null;
        TreeNode rootRight = new TreeNode(1);
        TreeNode rootRightLeft = new TreeNode(0);
        TreeNode rootRightRight = new TreeNode(1);

        root.left = rootLeft;
        root.right = rootRight;
        root.right.left = rootRightLeft;
        root.right.right = rootRightRight;
        System.out.println("ORIGINAL");
        System.out.println(root);

        pruneTree(root);

        System.out.println("PRUNNED");
        System.out.println(root);
    }

    private static TreeNode pruneTree(TreeNode<Integer> root) {
        if (root == null) {
            return null;
        }

        containsOne(root);

        return root;
    }

    private static boolean containsOne(TreeNode<Integer> node) {
        if( node == null || node.val == 0){//BASE
            return false;
        }

        boolean left_contains = containsOne(node.left);
        boolean right_contains = containsOne(node.right);

        if(!left_contains){//on left smth not 1
            node.left = null;//scrape all the tree from it by setting to null
        }
        if(!right_contains){//on right smth not 1
            node.right = null;//scrape all the tree from it by setting to null
        }

        return node.val == 1 || left_contains || right_contains;//all 3 true = all good
    }
}
