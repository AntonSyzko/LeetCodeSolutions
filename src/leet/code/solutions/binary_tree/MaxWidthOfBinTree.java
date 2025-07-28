package leet.code.solutions.binary_tree;

/*

https://leetcode.com/problems/maximum-width-of-binary-tree/

Given the root of a binary tree, return the maximum width of the given tree.

The maximum width of a tree is the maximum width among all levels.

The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes),
where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that level are also counted into the length calculation.

It is guaranteed that the answer will in the range of a 32-bit signed integer.

Example 1:

Input: root = [1,3,2,5,3,null,9]
Output: 4
Explanation: The maximum width exists in the third level with length 4 (5,3,null,9).
Example 2:

Input: root = [1,3,2,5,null,null,9,6,null,7]
Output: 7
Explanation: The maximum width exists in the fourth level with length 7 (6,null,null,null,null,null,7).
Example 3:

Input: root = [1,3,2,5]
Output: 2
Explanation: The maximum width exists in the second level with length 2 (3,2).

Constraints:

The number of nodes in the tree is in the range [1, 3000].
-100 <= Node.val <= 100
 */

import leet.code.solutions.utils.Pair;

import java.util.LinkedList;
import java.util.Queue;

public class MaxWidthOfBinTree {

    public static void main(String[] args) {
       TreeNode root = new TreeNode(1);
       root.left = new TreeNode(2);
       root.right = new TreeNode(3);
       root.left.left = new TreeNode(4);
    //   root.left.right = new TreeNode(5);


       int width = widthOfBinaryTree(root);
       System.out.println(width);
    }

    /*
    Time and Space Complexity Analysis
        Time Complexity: O(N)

        We visit each node in the tree exactly once, where N is the number of nodes.
        At each node, we perform constant time operations.

        Space Complexity: O(W)

        We use a queue for level-order traversal, where the maximum size of the queue will be the maximum width of the tree (W).
        In the worst case (perfect binary tree), the last level can have up to N/2 nodes, making the space complexity O(N).
        However, since the width is typically much smaller than N, we can consider it O(W).
             */

    private  static  int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        // Queue to hold node and its position
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(root, 0));

        int maxWidth = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            int leftmost = queue.peek().getValue();//always what's in the beginning of que
            int rightmost = leftmost; // Initialize rightmost with leftmost , just as a placeholder

            // Process all nodes at current level
            for (int i = 0; i < levelSize; i++) {

                Pair<TreeNode, Integer> pair = queue.poll();//we poll from BFS
                TreeNode node = pair.getKey();
                int position = pair.getValue();

                // Update rightmost position at this level, as we store LEFTs and then RIGHTs - rightmost will be first out of BFS que
                rightmost = position;

                // Add left child with position calculation (to avoid integer overflow)
                if (node.left != null) {
                    queue.add(new Pair<>(node.left, 2 * (position - leftmost)));
                }

                // Add right child with position calculation
                if (node.right != null) {
                    queue.add(new Pair<>(node.right, 2 * (position - leftmost) + 1));
                }
            }

            // Update maximum width
            maxWidth = Math.max(maxWidth, rightmost - leftmost + 1);
        }

        return maxWidth;
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
  }
}
