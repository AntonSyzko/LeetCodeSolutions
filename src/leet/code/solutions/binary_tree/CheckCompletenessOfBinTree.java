package leet.code.solutions.binary_tree;

import java.util.LinkedList;
import java.util.Queue;

/*
https://leetcode.com/problems/check-completeness-of-a-binary-tree/

Given the root of a binary tree, determine if it is a complete binary tree.
In a complete binary tree, every level, except possibly the last, is completely filled,
 and all nodes in the last level are as far left as possible.
  It can have between 1 and 2h nodes inclusive at the last level h.

Example 1:
Input: root = [1,2,3,4,5,6]
Output: true
Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}),
 and all nodes in the last level ({4, 5, 6}) are as far left as possible.

Example 2:
Input: root = [1,2,3,4,5,null,7]
Output: false
Explanation: The node with value 7 isn't as far left as possible.

Constraints:
The number of nodes in the tree is in the range [1, 100].
1 <= Node.val <= 1000
 */
public class CheckCompletenessOfBinTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);

        TreeNode rootL1 = new TreeNode(2);
        TreeNode rootR1 = new TreeNode(3);

        TreeNode rootL1L1 = new TreeNode(4);
        TreeNode rootL1R1 = new TreeNode(5);

        TreeNode rootR1L1 = new TreeNode(6);

        root.left = rootL1;
        root.right = rootR1;

        rootL1.left = rootL1L1;
        rootL1.right = rootL1R1;

        rootR1.left = rootL1R1;
       // rootR1.right = rootL1R1; // for false result - since not all nodes are as left at the last level

        System.out.println(isCompleteTree(root));

    }

    private static boolean isCompleteTree(TreeNode root) {

        boolean lastNullNodeMet = false;

        Queue<TreeNode> queue = new LinkedList<>();//level order traversal
        queue.offer(root); //offer checks for null

        while (!queue.isEmpty()) {

            TreeNode current = queue.poll();

            if (current == null) { // null node met

                lastNullNodeMet = true;

            } else {//not null node met

                if (lastNullNodeMet) { // if previously NULL node already met ( true ) and now we see NON NULL after that
                    return false;// not a complete tree - NON NULL node met after NULL - so either not complete level or LAST not all nodes are as LEFT
                }

                //ORDER of insertion matters - LEFTS then RIGHTS
                queue.offer(current.left);
                queue.offer(current.right);
            }
        }
        //got here - true anyways
        return true;
    }
}
