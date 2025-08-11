package leet.code.solutions.binary_tree;

public class BinaryTreeUpsideDown {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);


        TreeNode upsideDown = upsideDownBinaryTree(root);
        System.out.println(upsideDown);
    }

    private static TreeNode upsideDownBinaryTree(TreeNode root) {
            if(root==null || root.left == null){//BASE
                return root;
            }

            TreeNode newRoot = upsideDownBinaryTree(root.left);

            root.left.left = root.right;
            root.left.right = root;

            root.left = null;
            root.right = null;

            return newRoot;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;


        TreeNode(int val) {
            this.val = val;
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