package leet.code.solutions.binary_tree;

import leet.code.solutions.sandbox.Sandbox1;

public class HeightBallancedTree {


    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        TreeNode rootL = new TreeNode(5);
        TreeNode rootR = new TreeNode(20);
        TreeNode rootLL = new TreeNode(2);
        root.left = rootL;
        root.right = rootR;
        rootL.left = rootLL;

        BalanceStatusWithHeight result = checkBalanced(root);
        System.out.println(result);

    }

    /*
  Time Complexity: O(n²)
          The time complexity is O(n²) in the worst case, where n is the number of nodes in the tree. Here's why:

          For each node, you call isBalanced recursively on both its left and right subtrees
          Within each isBalanced call, you also calculate the heights of the left and right subtrees using isBalancedSubtree
          The isBalancedSubtree function itself takes O(n) time in the worst case
          This creates a cascade of overlapping subproblems

          For example, in a skewed tree of n nodes:

          You call isBalanced on each node, starting from the root
          For each node, you recalculate the heights of all its subtrees
          This leads to approximately O(n) + O(n-1) + O(n-2) + ... + O(1) operations, which is O(n²)

Space Complexity: O(h)
      The space complexity is O(h), where h is the height of the tree:

      This is due to the recursion stack
      In the worst case (a skewed tree), h = n, making the space complexity O(n)
      In a balanced tree, h = log(n), making the space complexity O(log n)
   */
    private static boolean isBalanced(TreeNode root) {
        if(root==null) return true;

        int leftDepth =  isBalancedSubtree(root.left);

        int rightDepth = isBalancedSubtree(root.right);

        return ( Math.abs(leftDepth-rightDepth) <= 1  && isBalanced(root.left) && isBalanced(root.right));
    }

    private static int isBalancedSubtree(TreeNode node) {
        if(node==null) return 0;

        return Math.max(isBalancedSubtree(node.left), isBalancedSubtree(node.right)) + 1;
    }

    //--------------------- OPTIMISED --------------------------

    public boolean isBalancedOpt(TreeNode root) {
        return checkHeight(root) != -1;
    }

    private int checkHeight(TreeNode node) {
        if (node == null) return 0;

        int leftHeight = checkHeight(node.left);
        if (leftHeight == -1) return -1; // Left subtree is unbalanced

        int rightHeight = checkHeight(node.right);
        if (rightHeight == -1) return -1; // Right subtree is unbalanced

        if (Math.abs(leftHeight - rightHeight) > 1) return -1; // Current node is unbalanced

        return Math.max(leftHeight, rightHeight) + 1;
    }

    private static BalanceStatusWithHeight checkBalanced(TreeNode tree) {
        if (tree == null) {
            return new BalanceStatusWithHeight(true, -1);//base case for recursion
        }

        BalanceStatusWithHeight leftResult = checkBalanced(tree.left);
        if (!leftResult.balanced) {
            return leftResult;// Left subtree is not balanced.
        }

        BalanceStatusWithHeight rightResult = checkBalanced(tree.right);
        if (!rightResult.balanced) {
            return rightResult;// Right subtree is not balanced.
        }

        boolean isBallanced = Math.abs(leftResult.height - rightResult.height) <= 1;//ballanced when diffrernce in height is NOT more than 1
        int height = Math.max(leftResult.height, rightResult.height) + 1;//new height - is ONE  more than previous and applies for MAX of two

        return new BalanceStatusWithHeight(isBallanced, height);

    }

    private static class BalanceStatusWithHeight {
        public boolean balanced;
        public int height;

        public BalanceStatusWithHeight(boolean balanced, int height) {
            this.balanced = balanced;
            this.height = height;
        }

        public static boolean isBalanced(TreeNode tree) {
            return checkBalanced(tree).balanced;
        }

        @Override
        public String toString() {
            return "BalanceStatusWithHeight{" +
                "balanced=" + balanced +
                ", height=" + height +
                '}';
        }
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
