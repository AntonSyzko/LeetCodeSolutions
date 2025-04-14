package leet.code.solutions.binary_tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*
https://leetcode.com/problems/binary-tree-level-order-traversal/

Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[9,20],[15,7]]

Example 2:
Input: root = [1]
Output: [[1]]

Example 3:

Input: root = []
Output: []


Constraints:

The number of nodes in the tree is in the range [0, 2000].
-1000 <= Node.val <= 1000
 */
public class BinaryTreeLevelOrderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
//        root.left.right = new TreeNode(5);
//
//        root.right.left = new TreeNode(6);
//        root.right.right = new TreeNode(7);
//
//        root.left.right.left = new TreeNode(8);

        List<List<Integer>> result = levelOrderRecursive(root);
        System.out.println(result);
    }

    private  static List<List<Integer>> result = new ArrayList<>();

    private static List<List<Integer>> levelOrderRecursive(TreeNode root) {
        if(root==null) return null;

        levelOrderBFS(root, 0);

        return result;
    }

    //O(N)
    //O( H) , H = height of tree
    private static void levelOrderBFS(TreeNode  node, int level) {
        // NOT a BASE actually read line 69
          if(result.size() == level) {//if current level == how many Lists in the res
              result.add(new ArrayList<>());
          }

          result.get(level).add(node.val);//add node val by result level

        /*
             When we reach a node where both node.left and node.right are null, no further recursive calls are made.
             The recursion essentially "bottoms out" by not making any additional recursive calls.
         */

        if(node.left != null) {

            levelOrderBFS(node.left, level + 1);//add to NEXT level
        }

        if(node.right != null) {

            levelOrderBFS(node.right, level + 1);
        }
    }


    private static List<List<Integer>> levelOrder(TreeNode root) {
        if(root==null) return new ArrayList<>();

       List<List<Integer>> res = new ArrayList<>();

       Queue<TreeNode> levelNodes = new ArrayDeque<>();
       levelNodes.add(root);

       while (!levelNodes.isEmpty()){

           List<Integer> currentLevelNodeVals = new ArrayList<>();
           int levelNodesSize = levelNodes.size();

           for (int i = 0; i < levelNodesSize; i++) {
               TreeNode levelNode = levelNodes.poll();

               if(levelNode != null){
                   currentLevelNodeVals.add(levelNode.val);

                   if(levelNode.left != null){
                       levelNodes.add(levelNode.left);
                   }
                   if(levelNode.right!=null){
                       levelNodes.add(levelNode.right);
                   }
               }
           }
           res.add(currentLevelNodeVals);
       }

        return res;
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


        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
