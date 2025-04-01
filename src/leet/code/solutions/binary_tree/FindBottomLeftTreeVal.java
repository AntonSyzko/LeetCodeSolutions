package leet.code.solutions.binary_tree;

import java.util.LinkedList;
import java.util.Queue;

/*
https://leetcode.com/problems/find-bottom-left-tree-value/

Given the root of a binary tree, return the leftmost value in the last row of the tree.

Example 1:
Input: root = [2,1,3]
Output: 1

Example 2:
Input: root = [1,2,3,4,null,5,6,null,null,7]
Output: 7

Constraints:
The number of nodes in the tree is in the range [1, 104].
-231 <= Node.val <= 231 - 1

 */
public class FindBottomLeftTreeVal {

    public static void main(String[] args) {
        TreeNode<Integer> root = new  TreeNode<Integer>(2);
        TreeNode<Integer> rootL1 = new  TreeNode<Integer>(1);
        TreeNode<Integer> rootR1 = new  TreeNode<Integer>(3);
        TreeNode<Integer> rootL1L = new  TreeNode<Integer>(4);

           root.left = rootL1;
           root.right = rootR1;
           rootL1.left = rootL1L;

           int leftMostValue = findBottomLeftValue(root);
           System.out.println(leftMostValue);
    }


    private static int findBottomLeftValue( TreeNode<Integer> root) {
                Queue< TreeNode<Integer>> queue = new LinkedList<>();
                queue.offer(root);

                while (!queue.isEmpty()) {
                    root = queue.poll();//uses root , so we constatntly update the VERY root , not current or temp node ( less memory footprint )

                    if(root.right !=null ) {//right first
                        queue.offer(root.right);
                    }

                    if(root.left != null ) {//left last - so LAST in queue - on top
                        queue.offer(root.left);
                    }
                }
                return root.val;//after all - last in Queue is left most on top
    }


        private static int findBottomLeftValueMy( TreeNode<Integer> root) {
            Queue< TreeNode<Integer>> queue = new LinkedList<>();
            queue.offer(root);
            int lastLeft = 0;

            while (!queue.isEmpty()) {

                TreeNode<Integer> current = queue.poll();// less efficient - since additional temp node

                 if(current.right != null) {
                     queue.offer(current.right);
                 }

                 //roder matters -LEFT last - on TOP of Queue
                 if(current.left != null) {
                     queue.offer(current.left);
                 }

                 lastLeft = current.val;//update to last on top - which is always LEFTmost

            }
            return lastLeft;
    }
}
