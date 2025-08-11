package leet.code.solutions.binary_tree;

public class MaxPathSum {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

        int sum = maxPathSum(root);
        System.out.println(sum);
    }

    private  static int MAX_SUM = 0;

    private static int maxPathSum(TreeNode root) {
        if(root==null) return 0;

        maxPathSumHelper(root);

        return MAX_SUM;
    }

    private static int maxPathSumHelper(TreeNode root) {
        if(root==null) return 0;//base

        int left = Math.max(0,maxPathSumHelper(root.left));
        int right = Math.max(0,maxPathSumHelper(root.right));

        int currSum = root.val + left + right;

        MAX_SUM = Math.max(MAX_SUM, currSum);

        return root.val + Math.max(left , right);//main recursive logic
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
