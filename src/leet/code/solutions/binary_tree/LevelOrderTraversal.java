package leet.code.solutions.binary_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/*
Given a binary tree root, return the level order traversal of it as a nested list, where each sublist contains the values of nodes at a particular level in the tree, from left to right.

Example 1:

Input: root = [1,2,3,4,5,6,7]

Output: [[1],[2,3],[4,5,6,7]]

Example 2:

Input: root = [1]

Output: [[1]]

Example 3:

Input: root = []

Output: []

Constraints:

0 <= The number of nodes in both trees <= 1000.
-1000 <= Node.val <= 1000

 */
public class LevelOrderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        List<List<Integer>> result = levelOrder(root);
        System.out.println(result);
    }


    private static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> levelNodes = new ArrayList<>();//each level list is new

            int allLevelNodes = queue.size();

            for (int i = 0; i < allLevelNodes; i++) {//to poll all nodes of level from QUEUE

                TreeNode node = queue.poll();

                if(node != null){
                    levelNodes.add(node.val);//add polled node to current level nodes

                    // these below ones will go to next level
                    if(node.left != null) {//left siblings exist ?
                        queue.add(node.left);
                    }

                    if(node.right != null){//right siblings exist ?
                        queue.add(node.right);
                    }

                }

            }

            if(levelNodes.size() > 0) {//if smth found in level
                res.add(levelNodes);//store curr levcele in res
            }
        }

        return res;
    }


        public  static class TreeNode {
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
