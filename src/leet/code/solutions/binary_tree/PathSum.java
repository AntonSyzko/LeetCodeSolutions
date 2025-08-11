package leet.code.solutions.binary_tree;

import java.util.Stack;

/*
https://leetcode.com/problems/path-sum/
Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
A leaf is a node with no children.

Example 1:
Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
Output: true
Explanation: The root-to-leaf path with the target sum is shown.

Example 2:
Input: root = [1,2,3], targetSum = 5
Output: false
Explanation: There two root-to-leaf paths in the tree:
(1 --> 2): The sum is 3.
(1 --> 3): The sum is 4.
There is no root-to-leaf path with sum = 5.

Example 3:
Input: root = [], targetSum = 0
Output: false
Explanation: Since the tree is empty, there are no root-to-leaf paths.

Constraints:
The number of nodes in the tree is in the range [0, 5000].
-1000 <= Node.val <= 1000
-1000 <= targetSum <= 1000
 */
public class PathSum {

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<Integer>(1);
        TreeNode<Integer> rootLeft = new TreeNode<Integer>(2);
        TreeNode<Integer> rootRight = new TreeNode<Integer>(3);
        TreeNode<Integer> rootRightRight = new TreeNode<Integer>(5);
        root.left = rootLeft;
        root.right = rootRight;
        root.right.right = rootRightRight;

        boolean res = hasPathSum(root, 9);
        System.out.println(res);

         res = hasPathSumRecursive(root, 9);
        System.out.println(res);
    }

        public static boolean hasPathSum(TreeNode<Integer> root, int targetSum) {
            if (root == null) {
                return false;
            }

            Stack<TreeNode<Integer>> previousNodes = new Stack<>();//track prev nodes
            Stack<Integer> sumDifference = new Stack<>();//track differences from target sum as we go
            previousNodes.add(root);
            sumDifference.add(targetSum - root.val);//add diff from target and root staright away

            while (!previousNodes.isEmpty()) {//while nodes in stack
                TreeNode<Integer> currentNode = previousNodes.pop();//get from top of stack
                int currentSumDiffFromTarget = sumDifference.pop();

                if (currentNode.left == null && currentNode.right == null //leaf - no siblings - end of tree
                    && currentSumDiffFromTarget == 0) {//sum differnece = 0
                    return true;//it's a match
                }

                if (currentNode.left != null) {//more nodes left at left branch
                    previousNodes.add(currentNode.left);//add
                    sumDifference.add(currentSumDiffFromTarget - (int) currentNode.left.val);//decrement from target sum
                }

                if (currentNode.right != null) {
                    previousNodes.add(currentNode.right);
                    sumDifference.add(currentSumDiffFromTarget - (int) currentNode.right.val);
                }
            }
            // got here - no match
            return false;
    }

    private static boolean hasPathSumRecursive(TreeNode<Integer> node, int sum){
        if(node == null) {//recursion base
            return false;
        }

        if(node.left == null && node.right == null){ // both leaves - no more siblings
            return sum == node.val;//actual equality check / the last element is exact difference to make 0 zero sum - hence true ( it's like to our zero 1 is left - and current IS 1
        }

        return hasPathSumRecursive(node.left, sum - node.val) || hasPathSumRecursive(node.right,  sum - node.val);

    }

    private static boolean hasPathSumRecursive2(TreeNode<Integer> tree, int targetSum) {
        return hasPathSumRecursiveHelper(tree, 0, targetSum);
    }

    private static boolean hasPathSumRecursiveHelper(TreeNode<Integer> node, int ongoingSum, int targetSum) {
        if (node == null) {//recursion base
            return false;
        }

        ongoingSum = ongoingSum + node.val;//aggregate sum

        if (node.left == null && node.right == null) { //both leafs - BOTH have NO siblings end of tree
            return ongoingSum == targetSum;//check FINAL equality
        }
        //has siblings - continue aggregating and checking if target hit
        return hasPathSumRecursiveHelper(node.left, ongoingSum, targetSum) || hasPathSumRecursiveHelper(node.right, ongoingSum, targetSum);
    }

}
