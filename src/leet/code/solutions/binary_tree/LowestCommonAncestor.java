package leet.code.solutions.binary_tree;

public class LowestCommonAncestor {
    public static void main(String[] args) {

    }


    public static TreeNode<Integer> LCA(TreeNode<Integer> tree, TreeNode<Integer> node0, TreeNode<Integer> node1) {
        return LCAHelper(tree, node0, node1).ancestor;
    }

    // Returns an object consisting of an int and a node. The int field is  0, 1, or 2 depending on how many of {node0, node1} are present in  the tree.
    // If both are present in the tree, when ancestor is assigned to a non-null value, it is the LCA.
    private static Status LCAHelper(TreeNode<Integer> tree, TreeNode<Integer> node0, TreeNode<Integer> node1) {
        if (tree == null) {
            return new Status(0, null);
        }

        Status leftResult = LCAHelper(tree.left, node0, node1);
        if (leftResult.numTargetNodes == 2) {
            // Found both nodes in the left subtree.
            return leftResult;
        }

        Status rightResult = LCAHelper(tree.right, node0, node1);
        if (rightResult.numTargetNodes == 2) {
            // Found both nodes in the right subtree.
            return rightResult;
        }

        int numTargetNodes = leftResult.numTargetNodes + rightResult.numTargetNodes
            + (tree == node0 ? 1 : 0) + (tree == node1 ? 1 : 0);

        return new Status(numTargetNodes, numTargetNodes == 2 ? tree : null);
    }






    private static class Status {
        public int numTargetNodes;
        public TreeNode<Integer> ancestor;

        public Status(int numTargetNodes, TreeNode<Integer> node) {
            this.numTargetNodes = numTargetNodes;
            this.ancestor = node;
        }
    }
}
