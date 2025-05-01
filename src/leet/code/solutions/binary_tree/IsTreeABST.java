package leet.code.solutions.binary_tree;

/*

https://www.techiedelight.com/determine-given-binary-tree-is-a-bst-or-not/
Given a binary tree, determine whether it is a BST.


 */
public class IsTreeABST {

    public static void main(String[] args) {
         TreeNode root = new TreeNode(20);
         root.left = new TreeNode(10);
         root.right = new TreeNode(40);
         root.right.left = new TreeNode(30);
         root.right.right = new TreeNode(50);

         inOrderTraverse(root);

        System.out.println( "\r\n\t " + isValidBST(root));
    }

    private static void inOrderTraverse(TreeNode root) {
        if(root==null) return;
        inOrderTraverse(root.left);
        System.out.print(root.val + ", ");
        inOrderTraverse(root.right);
    }

    private static Integer prev = null;

    private static boolean isValidBST(TreeNode root) {
        prev = null; // Reset previous value
        return inOrderCheck(root);
    }

    private  static boolean inOrderCheck(TreeNode node) {
        if (node == null) {
            return true;
        }

        // Check left subtree
        if (!inOrderCheck(node.left)) {
            return false;
        }

        // Check current node: it should be greater than the previous visited node
        if (prev != null && node.val <= prev) {
            return false;
        }
        prev = node.val;

        // Check right subtree
        return inOrderCheck(node.right);
    }



    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
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
