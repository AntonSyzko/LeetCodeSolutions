package leet.code.solutions.binary_tree;

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
       TreeNode<Integer> root = new TreeNode<>(1);
       TreeNode<Integer> rootL = new TreeNode<>(2);
       TreeNode<Integer> rootR = new TreeNode<>(3);
       TreeNode<Integer> rootLR = new TreeNode<>(5);
       root.left = rootL;
       root.right = rootR;
       rootL.right = rootLR;
       List<String> res = binaryTreePaths(root);
        System.out.println(res);
    }

    private static List<String> binaryTreePaths(TreeNode root) {
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
}
