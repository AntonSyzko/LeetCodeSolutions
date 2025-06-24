package leet.code.solutions.binary_tree.BST;

import java.util.ArrayList;
import java.util.List;

/*
Given the root of a binary search tree (BST) with duplicates, return all the mode(s) (i.e., the most frequently occurred element) in it.
If the tree has more than one mode, return them in any order.
Assume a BST is defined as follows:
* The left subtree of a node contains only nodes with keys less than or equal to the node's key.
* The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
* Both the left and right subtrees must also be binary search trees.

Example 1:

Input: root = [1,null,2,2]
Output: [2]
Example 2:

Input: root = [0]
Output: [0]

Constraints:

The number of nodes in the tree is in the range [1, 104].
-105 <= Node.val <= 105


Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
 */
public class FindBSTMode {

    private List<Integer> modes = new ArrayList<>();
    private int currentCount = 0;
    private int maxCount = 0;
    private Integer prevVal = null;

    public int[] findMode(TreeNode root) {
        inorderTraversal(root);
        return modes.stream().mapToInt(i -> i).toArray();
    }

    private void inorderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }

        // Traverse left subtree
        inorderTraversal(node.left);

        // Process current node
        processNode(node.val);

        // Traverse right subtree
        inorderTraversal(node.right);
    }

    private void processNode(int val) {
        // If this is the same value as previous, increment count
        if (prevVal != null && val == prevVal) {
            currentCount++;
        } else {
            // New value encountered, reset count
            currentCount = 1;
        }

        // Update modes based on current count
        if (currentCount > maxCount) {
            // Found new maximum frequency
            maxCount = currentCount;
            modes.clear();
            modes.add(val);
        } else if (currentCount == maxCount) {
            // Found another mode with same maximum frequency
            modes.add(val);
        }

        prevVal = val;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
