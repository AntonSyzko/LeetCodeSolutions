package leet.code.solutions.binary_tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
https://leetcode.com/problems/range-sum-of-bst/description/

Given the root node of a binary search tree and two integers low and high, return the sum of values of all nodes with a value in the inclusive range [low, high].

Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
Output: 32
Explanation: Nodes 7, 10, and 15 are in the range [7, 15]. 7 + 10 + 15 = 32.
 */
public class RangeSumOfBST {

    public static void main(String[] args) {
         TreeNode root = new TreeNode(10);
         root.left = new TreeNode(5);
         root.right = new TreeNode(15);
         root.left.left = new TreeNode(3);
         root.left.right = new TreeNode(7);
         root.right.right = new TreeNode(18);

         int leftLimit = 7;
         int rightLimit = 15;

         int sum = rangeSumDFS_Recursive(root, leftLimit, rightLimit);
         System.out.println(sum);

    }

    //DFS recursive
    private static int rangeSumDFS_Recursive(TreeNode root, int low, int high) {
        if(root==null) return 0;

        int accumulativeSum = 0;

        if (root.val >= low && root.val <= high) {
            accumulativeSum += root.val;
        }

        if(root.val > low){
            accumulativeSum += rangeSumDFS_Recursive(root.left, low, high);// node val itself is BIGGER than low -> meaning it can satisfy criteria of VAl >= low in future lookups
        }

        if(root.val < high){
            accumulativeSum += rangeSumDFS_Recursive(root.right, low, high);// node val itself is LOWER than HIGH -> meaning it can satisfy criteria of VAl <= HIGH in future lookups
        }

        return accumulativeSum;
    }

    private static int rangeSum_DFS_Iterative(TreeNode root, int low, int high) {
        if(root==null) return 0;

        int rangeSum = 0;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()){
            TreeNode node = stack.pop();

            if(node != null){
                if(node.val >= low && node.val <= high){
                    rangeSum += node.val;
                }

                if(node.val > low){ // node val itself is BIGGER than low -> meaning it can satisfy criteria of VAl >= low in future lookups
                    stack.push(node.left);
                }

                if(node.val < high){// node val itself is LOWER than HIGH -> meaning it can satisfy criteria of VAl <= HIGH in future lookups
                    stack.push(node.right);
                }
            }
        }

        return rangeSum;
    }



        // time O(n)
    //space O(n)
    private static int rangeSum_BFS(TreeNode root, int low, int high) {
        int sum = 0;
        if(root==null) {
            return sum;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            TreeNode currentNode = queue.remove();

            if(currentNode.val >= low && currentNode.val <= high){
                sum += currentNode.val;
            }

            if(currentNode.left != null && currentNode.val > low ) {// node val itself is BIGGER than low -> meaning it can satisfy criteria of VAl >= low in future lookups
                queue.add(currentNode.left);
            }

            if(currentNode.right != null && currentNode.val < high) {// node val itself is LOWER than HIGH -> meaning it can satisfy criteria of VAl <= HIGH in future lookups
                queue.add(currentNode.right);
            }
        }

        return sum;
    }


        static public class TreeNode {
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
