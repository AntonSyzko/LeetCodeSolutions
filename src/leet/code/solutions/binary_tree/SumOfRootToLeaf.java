package leet.code.solutions.binary_tree;


import java.util.ArrayDeque;
import java.util.Queue;

/*
https://www.techiedelight.com/calculate-sum-root-to-leaf-digits/

Given a binary tree, where each node stores a value between 0 and 9, calculate the sum of the numbers created by the paths from root to leaf.
Input: Binary Tree     1
                     /   \
                    2     3
                   / \
                 4    5

                 Output: 262


Explanation: Three root-to-leaf paths exist, and they are as follows:
[1 -> 2 -> 4]
[1 -> 2 -> 5]
[1 -> 3]

They represent the numbers 124, 125, and 13, respectively.
 They add up to 124 + 123 + 13 to make 262.
 */
public class SumOfRootToLeaf {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode rootL= new TreeNode(2);
        TreeNode rootR= new TreeNode(3);
        TreeNode  rootLL = new TreeNode(4);
        TreeNode   rootLR = new TreeNode(5);
        root.left = rootL;
        root.right = rootR;
        rootL.left = rootLL;
        rootL.right = rootLR;

        int totalSum = findRootToLeafPathSumIterative(root);
        System.out.println(totalSum);

    }

    private static int findRootToLeafPathSum(TreeNode root) {
        return findBranchSum(root, 0);
    }

    private static int findBranchSum(TreeNode node, int sum) {
        // base case: sum is 0 for an empty tree
        if(node==null){
            return 0;
        }

        //add current node value to sum ( minding tenth offset)
        sum = sum * 10 + node.val;

        // return the sum if the current node is a leaf node
        if(node.left == null && node.right == null){//LEAF
            return sum;
        }

        // recur and return the sum of the left and right subtrees
        return findBranchSum(node.left, sum) + findBranchSum(node.right, sum);
    }

    private static int findRootToLeafPathSumIterative(TreeNode root) {

        //base case
        if(root==null){
            return 0;
        }

        int sum = 0;
        // create an empty queue and enqueue the root node
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while(!queue.isEmpty()){
            // dequeue the next node from the queue
            TreeNode currNode = queue.poll();

            // if the left child exists, enqueue it after updating its data with the sum of path so far
            if(currNode.left != null){
                currNode.left.val = currNode.val * 10 + currNode.left.val;//overriden VAL to aggregated with ten carry + itself
                queue.add(currNode.left);
            }

            // if the right child exists, enqueue it after updating its data with the sum of path so far
            if(currNode.right != null){
                currNode.right.val = currNode.val * 10 + currNode.right.val;//overriden VAL to aggregated with ten carry + itself
                queue.add(currNode.right);
            }

            // if we encountered a leaf node, add the node's data to the result
            if(currNode.left == null && currNode.right == null){//LEAF
                sum += currNode.val;
            }
        }

        return sum;
    }



    private static class TreeNode {

        int val;
        public TreeNode left;
        public TreeNode right;


        public TreeNode(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
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
