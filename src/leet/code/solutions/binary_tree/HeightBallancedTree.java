package leet.code.solutions.binary_tree;

public class HeightBallancedTree {


    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(10);
        TreeNode<Integer> rootL = new TreeNode<>(5);
        TreeNode<Integer> rootR = new TreeNode<>(20);
        TreeNode<Integer> rootLL = new TreeNode<>(2);
        root.left = rootL;
        root.right = rootR;
        rootL.left = rootLL;

        BalanceStatusWithHeight result = checkBalanced(root);
        System.out.println(result);

    }


    private static BalanceStatusWithHeight checkBalanced(TreeNode<Integer> tree) {
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

        public static boolean isBalanced(TreeNode<Integer> tree) {
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
}
