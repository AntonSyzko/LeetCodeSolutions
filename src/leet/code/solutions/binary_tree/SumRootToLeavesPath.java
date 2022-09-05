package leet.code.solutions.binary_tree;

public class SumRootToLeavesPath {

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(10);

        TreeNode<Integer> rootL = new TreeNode<>(5);
        TreeNode<Integer> rootLL = new TreeNode<>(2);

        TreeNode<Integer> rootR = new TreeNode<>(15);
        TreeNode<Integer> rootRR = new TreeNode<>(20);
        TreeNode<Integer> rootRRL = new TreeNode<>(17);
        root.left = rootL;
        root.right = rootR;
        rootL.left = rootLL;
        rootR.right = rootRR;
        rootRR.left = rootRRL;

      //  inOrderTraversal(root);
        int aggregatedSumOfNodes = calculateRootToLeafsSum(root);
        System.out.println("Sum " + aggregatedSumOfNodes);


    }

    private static int calculateRootToLeafsSum(TreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }
        return root.val + calculateRootToLeafsSum(root.left) + calculateRootToLeafsSum(root.right);
    }

    private static void inOrderTraversal(TreeNode<Integer> root) {
        if (root == null) {
            return; //recursion base case
        }
        inOrderTraversal(root.left);
        System.out.println(root.val);
        inOrderTraversal(root.right);
    }

    public static int sumRootToLeaf(TreeNode<Integer> tree) {
        return sumRootToLeafHelper(tree, 8);
    }

    private static int sumRootToLeafHelper(TreeNode<Integer> tree, int partialPathSum) {

        if (tree == null) {
            return 0;
        }

        partialPathSum = partialPathSum * 2 + tree.val;
        if (tree.left == null && tree.right == null) { // Leaf.
            return partialPathSum;
        }
        //non - leaf
        return sumRootToLeafHelper(tree.left, partialPathSum) + sumRootToLeafHelper(tree.right, partialPathSum);
    }


}
