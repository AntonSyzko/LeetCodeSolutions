package leet.code.solutions.binary_tree;

import leet.code.solutions.sandbox.Sandbox1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
https://leetcode.com/problems/binary-tree-paths/

Given the root of a binary tree, return all root-to-leaf paths in any order.
A leaf is a node with no children.

Example 1:
Input: root = [1,2,3,null,5]
Output: ["1->2->5","1->3"]

Example 2:
Input: root = [1]
Output: ["1"]

Constraints:
The number of nodes in the tree is in the range [1, 100].
-100 <= Node.val <= 100
 */
public class BinTreePaths {
    public static void main(String[] args) {
       TreeNode root = new TreeNode(1);
       TreeNode rootL = new TreeNode(2);
       TreeNode rootR = new TreeNode(3);
       TreeNode rootLR = new TreeNode(5);
       root.left = rootL;
       root.right = rootR;
       rootL.right = rootLR;
       List<String> res = binaryTreePaths(root);
        System.out.println(res);
    }

    private static List<String> binaryTreePaths(TreeNode root) {
        if(root==null) return new ArrayList<>();

        List<String> res = new ArrayList<>();

        dfs(root, res,"");

        return res;
    }

    //Time O(n)
    //Space O(n)
    private static void dfs(TreeNode node, List<String> res, String path) {
        //BASE
        if( node.left == null && node.right == null) {//reached leaf

            path +=  node.val;//also add the very current node to path

            res.add(path);

            return;
        }

        if(node.left != null) {
            dfs(node.left, res, path  + node.val + "->");
        }
        if(node.right != null) {
            dfs(node.right, res, path + node.val + "->");
        }
    }

    private static List<String> binaryTreePaths2(TreeNode root) {
       List<String> res = new LinkedList<>();
       process(root, "", res);
       return res;
    }

    private static void process(TreeNode node, String currentPath, List<String> res) {
        if(node == null){//recusrion base
            return;
        }

        String nextPath = currentPath + "->" + node.val;
        
        if(node.left == null && node.right == null){
            res.add(nextPath.substring(2));//-> will be added to null, so when last time - cut it off
        }

        process(node.left, nextPath, res);
        process(node.right, nextPath, res);
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
