package leet.code.solutions.binary_tree;

/*
https://leetcode.com/problems/binary-tree-pruning/description/

Given the root of a binary tree, return the same tree where every subtree (of the given tree) not containing a 1 has been removed.

A subtree of a node node is node plus every node that is a descendant of node.

Input: root = [1,null,0,0,1]
Output: [1,null,0,null,1]
Explanation:
Only the red nodes satisfy the property "every subtree not containing a 1".
The diagram on the right represents the answer.
 */
public class PruneBinaryTree {
    public static void main(String[] args) {
        
    }


    private static TreeNode pruneTree(TreeNode root) {
       if(root==null) return null;
       containsOne(root);
       return root;

    }

    private static boolean containsOne(TreeNode node) {
        if(node==null || node.val == 0)  return false;

        boolean leftSubtreeContainsOne = containsOne(node.left);
        boolean rightSubtreeContainsOne = containsOne(node.right);

        if(!leftSubtreeContainsOne){
            node.left=null;//set ALL left  subtree to NULL - prune it
        }

        if(!rightSubtreeContainsOne){
            node.right=null;//set ALL right  subtree to NULL - prune it
        }

        return node.val == 1 || //first checking if node.vl == 1 -> if it is true - rest of the evaluation will be skipped anyways
                leftSubtreeContainsOne || //checked if is NULL or node.val == 0 above
                rightSubtreeContainsOne;//checked if is NULL or node.val == 0 above


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
