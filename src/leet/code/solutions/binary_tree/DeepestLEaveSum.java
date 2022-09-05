package leet.code.solutions.binary_tree;

import leet.code.solutions.utils.Pair;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/*
https://leetcode.com/problems/deepest-leaves-sum/

Given the root of a binary tree, return the sum of values of its deepest leaves.

Example 1:
Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
Output: 15

Example 2:
Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
Output: 19

Constraints:

The number of nodes in the tree is in the range [1, 104].
1 <= Node.val <= 100
 */
public class DeepestLEaveSum {
    public static void main(String[] args) {
        TreeNode<Integer> root = new  TreeNode<Integer>(1);
        TreeNode<Integer> rootLeft = new  TreeNode<Integer>(2);
        TreeNode<Integer> rootRight = new  TreeNode<Integer>(3);
        TreeNode<Integer> rootLeftLeft = new  TreeNode<Integer>(4);
        TreeNode<Integer> rootRightRight = new  TreeNode<Integer>(5);
        root.left = rootLeft;
        root.right = rootRight;
        rootLeft.left = rootLeftLeft;
        rootRight.right = rootRightRight;

        int maxDepthSum = deepestLeavesSumBFS(root);
        System.out.println(maxDepthSum);

    }

    private static int deepestLeavesSumBFS(TreeNode<Integer> root) {
        Queue< TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);

        int levelSum = 0;
        int deepestLevelSum = 0;

        while (!queue.isEmpty()) {//keeps ONLY LEVEL nodes at a time

            levelSum = 0;//reset level sum each time processing the NEW level

            deepestLevelSum = levelSum;//contsantly updating deepest - but last will be the MOST deep

            //this must be BEFORE FOR loop, othervise polling inside for loop will decrease size each time and some nodes will be BYPASSED
            int levelNodesSize = queue.size();//currently all NODEs in Queue is a level

            for (int i = 0; i < levelNodesSize; i++) {//ONLY nodes of LEVEL

                TreeNode<Integer> currentNode = queue.poll();
                levelSum += currentNode.val;

                if (currentNode.left != null) {
                    queue.offer(currentNode.left);//this will go for NEXT level and FOR loop will NOT take these into account
                }

                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
        }
        return levelSum;
    }


    //suleimanov DFS
    private static int deepestLeavesSumDFS( TreeNode<Integer> root) {
        int maxDepthSum = 0;
        int currentLevelDepth = 0;//level aggregation count
        int maxLevelDepth = 0;

        Deque<Pair< TreeNode<Integer>, Integer>> stack = new ArrayDeque<>();
        stack.push(new Pair<>(root, 0));//push root at the beginning

        while (!stack.isEmpty()) {

            Pair< TreeNode<Integer>, Integer> currentPairFromTopOfStack = stack.pop();//exctract from STACK
            root = currentPairFromTopOfStack.getKey();//override current root we are dealing with
            currentLevelDepth = currentPairFromTopOfStack.getValue();

            if (root.left != null || root.right != null) { //while current NODE has children

                if (root.left != null) {
                    stack.push(new Pair<>(root.left, currentLevelDepth + 1));//push to STACK and increase the current depth level
                }

                if (root.right != null) {
                    stack.push(new Pair<>(root.right, currentLevelDepth + 1));
                }

            } else {//so no children - deepest node found on top of stack (there can be many deepest though )

                if (maxLevelDepth < currentLevelDepth) {
                    maxDepthSum = root.val;//NODE value of the deepest currently found
                    maxLevelDepth = currentLevelDepth;
                } else if (maxLevelDepth == currentLevelDepth) {//if another node found with same current depth
                    maxDepthSum += root.val;//aggregate it's NODE val
                }

            }
        }

        return maxDepthSum;
    }
}
