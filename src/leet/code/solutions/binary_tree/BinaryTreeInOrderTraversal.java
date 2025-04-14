package leet.code.solutions.binary_tree;

public class BinaryTreeInOrderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);

        root.left = new TreeNode(3);
        root.right = new TreeNode(8);

        root.left.left = new TreeNode(1);

        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        root.right.right.right = new TreeNode(10);

        inOrderTraversal(root);
    }
    
    private static void inOrderTraversal(TreeNode root) {
        if(root==null) return;

        inOrderTraversal(root.left);
        System.out.print(root.val + ", ");
        inOrderTraversal(root.right);

    }




    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

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
