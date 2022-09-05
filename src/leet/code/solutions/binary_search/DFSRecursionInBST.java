package leet.code.solutions.binary_search;

import leet.code.solutions.binary_tree.TreeNode;

public class DFSRecursionInBST {


    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode rootL = new TreeNode(3);
        TreeNode rootR = new TreeNode(10);
        TreeNode rootLR = new TreeNode(4);
        TreeNode rootLL = new TreeNode(2);
        TreeNode rootRR = new TreeNode(20);
        root.left = rootL;
        root.right = rootR;
        rootL.left = rootLL;
        rootL.right = rootLR;
        rootR.right = rootRR;

        printRightsThenLefts(root);

        swapRightsAndLefts(root);
        System.out.println("After swap ");

        printRightsThenLefts(root);

      //  System.out.println(root);


    }

    private static void printRightsThenLefts(TreeNode currentNode) {

        if( currentNode == null) {
            return;
        }

        printRightsThenLefts(currentNode.right);
        System.out.println("Node " + currentNode.val);
        printRightsThenLefts(currentNode.left);
    }

    private static TreeNode swapRightsAndLefts(TreeNode node) {
        if(node == null ) {
            return null;
        }
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;

        swapRightsAndLefts(node.left);
        swapRightsAndLefts(node.right);
        return node;
    }

}
