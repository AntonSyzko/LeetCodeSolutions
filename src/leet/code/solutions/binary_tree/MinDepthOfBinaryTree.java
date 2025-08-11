package leet.code.solutions.binary_tree;

public class MinDepthOfBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);

        root.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.left.right = new TreeNode(7);

        int minDepth =  minDepth(root);
        System.out.println(minDepth);
    }

    private static int minDepth(TreeNode root) {
       if(root==null) {//base
           return 0;
       }

       if(root.left == null ){
           return 1 + minDepth(root.right);
       }

       if(root.right == null ){
           return 1 + minDepth(root.left);
       }

       return 1 + Math.min(
               minDepth(root.left),//recur
               minDepth(root.right)
                );
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