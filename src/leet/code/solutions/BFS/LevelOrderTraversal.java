package leet.code.solutions.BFS;

import leet.code.solutions.binary_tree.TreeNode;

import java.util.*;

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

public class LevelOrderTraversal {

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<Integer>(100);
        TreeNode<Integer> rootL = new TreeNode<Integer>(50);
        TreeNode<Integer> rootR = new TreeNode<Integer>(200);
        TreeNode<Integer> rootLL = new TreeNode<Integer>(40);
        root.left = rootL;
        root.right = rootR;
        rootL.left = rootLL;
        List<List<Integer>> depthOrderedVals = binaryTreeDepthOrderBFS(root);
        System.out.println(depthOrderedVals);
    }

    public static List<List<Integer>> binaryTreeDepthOrderBFS(TreeNode<Integer> root) {
        List<List<Integer>> result = new ArrayList<>();

        Queue<TreeNode<Integer>> BFS_QUEUE = new LinkedList<>();
        BFS_QUEUE.add(root);//insert root to BFS queue to start

        while (!BFS_QUEUE.isEmpty()) {//if smth in BFS Queue

            List<Integer> currentLevelNodesVals = new ArrayList<>();
            //setup for current level - needs re-initialisation each time
            Queue<TreeNode<Integer>> nextLevelNodes = new LinkedList<>();

            while (!BFS_QUEUE.isEmpty()) {
                TreeNode<Integer> currentNode = BFS_QUEUE.poll();

                if (currentNode != null) {
                    currentLevelNodesVals.add(currentNode.val);
                    //null will be checked next time we do the BFS level run at line 61
                    nextLevelNodes.add(currentNode.left);//extract children  and insert in next level QUEUE
                    nextLevelNodes.add(currentNode.right);
                }
            }

            if (!currentLevelNodesVals.isEmpty()) {
                result.add(currentLevelNodesVals);
            }

            BFS_QUEUE = nextLevelNodes;//override BFS queue for next levels
        }

        return result;
    }
}
