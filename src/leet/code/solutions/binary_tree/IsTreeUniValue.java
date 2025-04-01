package leet.code.solutions.binary_tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
https://leetcode.com/problems/univalued-binary-tree/

A binary tree is uni-valued if every node in the tree has the same value.
Given the root of a binary tree, return true if the given tree is uni-valued, or false otherwise.

Example 1:
Input: root = [1,1,1,1,1,null,1]
Output: true

Example 2:
Input: root = [2,2,2,5,2]
Output: false

Constraints:
The number of nodes in the tree is in the range [1, 100].
0 <= Node.val < 100
 */
public class IsTreeUniValue {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode rootL1 = new TreeNode(1);
        TreeNode rootR1 = new TreeNode(1);//set to any but 1 to get false
        root.left = rootL1;
        root.right = rootR1;
        System.out.println(isUnivalTreeRecursiveDFS(root));
    }



    private static boolean isUnivalTreeRecursiveDFS(TreeNode root) {
        boolean isLeftSubtreeUnivalue = root.left == null //if this leaf's LEFT is null - than nothing to traverse - no recursive call needed
                ||
                root.val == root.left.val // values of current and its left child are SAME
                        &&
                        isUnivalTreeRecursiveDFS(root.left); //recursively for it's ALL  LEFT children

        boolean isRightSubtreeUnival = root.right == null //if this leaf's RIGHT  is null - than nothing to traverse - no recursive call needed
                ||
                root.val == root.right.val //val of current and RIGHT child are SAME
                        &&
                        isUnivalTreeRecursiveDFS(root.right);//recursively check for ALL  it's RIGH children

        return isLeftSubtreeUnivalue
                &&
                isRightSubtreeUnival;//compare BOTH left AND right subtrees results
    }

    /**
     * DFS Iterative Solution
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(h) where h is the height of the tree
     */
    private static boolean isUnivalTreeDFS(TreeNode root) {
        if(root==null) return true;

        int rootsValue = root.val;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode currNode = stack.pop();

            if(currNode.val != rootsValue){
                return false;
            }

            if(currNode.left != null){
                stack.push(currNode.left);
            }
            if(currNode.right != null){
                stack.push(currNode.right);
            }
        }

        return true;

    }

    /**
     * BFS Iterative Solution
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(w) where w is the maximum width of the tree
     */
    private static boolean isUnivalTreeBFS(TreeNode root) {

        if(root==null) return true;

        int rootsValue = root.val;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            TreeNode currNode = queue.poll();

            if(currNode.val != rootsValue){
                return false;
            }
            if(currNode.left != null){
                queue.offer(currNode.left);
            }
            if(currNode.right != null){
                queue.offer(currNode.right);
            }
        }

        return true;

    }


   static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

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
