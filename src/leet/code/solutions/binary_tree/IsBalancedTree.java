package leet.code.solutions.binary_tree;

public class IsBalancedTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(44);

        boolean isBal = isBalanced(root);
        System.out.println(isBal);

    }

    //Time: O(n²), Space: O(n) due to recursion stack

    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        // Check height difference at current node
        int leftMaxHeight = maxDepth(root.left);
        int rightMaxHeight = maxDepth(root.right);

        // Current node is balanced AND both subtrees are balanced
        return Math.abs(leftMaxHeight - rightMaxHeight) <= 1
                &&
                isBalanced(root.left)//recur
                &&
                isBalanced(root.right);
    }

    private static int maxDepth(TreeNode root) {
        if (root == null) return 0;//BASE

        return 1 + Math.max(
                maxDepth(root.left),//recur
                maxDepth(root.right)
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