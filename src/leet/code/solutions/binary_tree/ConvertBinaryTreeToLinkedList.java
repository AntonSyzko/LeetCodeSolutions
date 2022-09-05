package leet.code.solutions.binary_tree;

import java.util.LinkedList;
import java.util.List;

public class ConvertBinaryTreeToLinkedList {

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(10);
        TreeNode<Integer> rootL = new TreeNode<>(5);
        TreeNode<Integer> rootR = new TreeNode<>(15);
        TreeNode<Integer> rootLL = new TreeNode<>(3);
        TreeNode<Integer> rootRR = new TreeNode<>(20);
        root.left = rootL;
        root.right = rootR;
        rootL.left = rootLL;
        rootR.right = rootRR;

        List<TreeNode<Integer>> res = treeToList(root);
        System.out.println(res);
    }

    private static List<TreeNode<Integer>> treeToList(TreeNode<Integer> tree) {
        List<TreeNode<Integer>> resList = new LinkedList<>();
        addBinTreeLeavesLeftToRightToList(tree, resList);
        return resList;
    }

    private static void addBinTreeLeavesLeftToRightToList(TreeNode<Integer> tree, List<TreeNode<Integer>> resList) {
        if (tree != null) {
            if (tree.left == null && tree.right == null) {//both leaves
                resList.add(tree);
            } else {//subtrees - continue
                addBinTreeLeavesLeftToRightToList(tree.left, resList);
                addBinTreeLeavesLeftToRightToList(tree.right, resList);
            }
        }
    }
}
