package leet.code.solutions.binary_search;

import leet.code.solutions.binary_tree.TreeNode;
import leet.code.solutions.linked_list.ListNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSRecursive {
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

        printBFS(root);
    }

    //NON recursive
    private static void printBFS(TreeNode node) {
        if (node == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {

            TreeNode currentNode = queue.poll();

            System.out.println(currentNode.val);

            if (currentNode.left != null) {
                queue.offer(currentNode.left);
            }
            if (currentNode.right != null) {
                queue.offer(currentNode.right);
            }
        }
    }
}
