package leet.code.solutions.binary_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
https://leetcode.com/problems/binary-tree-right-side-view/

Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example 1:

Input: root = [1,2,3,null,5,null,4]
Output: [1,3,4]

Example 2:

Input: root = [1,2,3,4,null,null,null,5]
Output: [1,3,4,5]


Example 3:

Input: root = [1,null,3]
Output: [1,3]

Example 4:

Input: root = []

Output: []

Constraints:

The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
 */
public class BinaryTreeRightSideView {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(5);

        root.right.right = new TreeNode(4);

        root.left.left.left = new TreeNode(7);

        List<Integer> list =  rightSideViewOptimal(root);
        System.out.println(list);
    }

    private static List<Integer> rightSideViewOptimal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        Queue<TreeNode> bfsQ = new LinkedList<>();
        bfsQ.add(root);

        while (!bfsQ.isEmpty()) {

            int levelNodesSize = bfsQ.size();

            for (int i = 0; i < levelNodesSize; i++) {

                TreeNode currentNode = bfsQ.poll();

                  if(i == 0) {//first in level is always RIGHt as we have inserted RIGHTs first (line 77)
                      res.add(currentNode.val);
                  }

                    if(currentNode.right != null){// RIGHT goes first as it is visible from RIGHT view
                        bfsQ.add(currentNode.right);
                    }

                    if(currentNode.left != null){
                        bfsQ.add(currentNode.left);
                    }
                }
            }

        return res;
    }

    private static List<Integer> rightSideViewMy(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        Queue<TreeNode> bfsQ = new LinkedList<>();
        bfsQ.add(root);

        while (!bfsQ.isEmpty()) {

            int levelNodesSize = bfsQ.size();

             List<Integer> levelNodes = new ArrayList<>();

             for (int i = 0; i < levelNodesSize; i++) {

                 TreeNode currentNodeFromBFS = bfsQ.poll();

                  if (currentNodeFromBFS != null){

                      levelNodes.add(currentNodeFromBFS.val);

                      if(currentNodeFromBFS.left != null){
                          bfsQ.add(currentNodeFromBFS.left);
                      }

                      if(currentNodeFromBFS.right != null){
                          bfsQ.add(currentNodeFromBFS.right);//goes LAST
                      }
                  }
             }

             if(!levelNodes.isEmpty()){
                 res.add(levelNodes.getLast());//LAST is the rightmost visible in the current level
             }

             //after retrieving LAST - drop everything else
         //    levelNodes = new ArrayList<>();//reset level nodes to empty array as we have considered level

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
  }
}
