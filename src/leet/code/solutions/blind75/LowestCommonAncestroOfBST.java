package leet.code.solutions.blind75;

/*
https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/

Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”



Example 1:


Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
Output: 6
Explanation: The LCA of nodes 2 and 8 is 6.
Example 2:


Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
Output: 2
Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
Example 3:

Input: root = [2,1], p = 2, q = 1
Output: 2

 */
public class LowestCommonAncestroOfBST {

    public static void main(String[] args) {
          TreeNode root = new TreeNode(3);
          root.left = new TreeNode(9);
          root.right = new TreeNode(20);


          TreeNode lowestCommonAncestor = lowestCommonAncestor(root, root.left, root.right);
          System.out.println(lowestCommonAncestor.val);
    }

    private static TreeNode lowestCommonAncestor(TreeNode root, TreeNode nodeOne, TreeNode nodeTwo) {
          if(root==null || nodeOne == null || nodeTwo == null) {
              return null;
          }

          int parentValue = root.val;
          int nodeOneVal = nodeOne.val;
          int nodeTwoVal = nodeTwo.val;

           if( nodeOneVal < parentValue && nodeTwoVal < parentValue){//both nodeOne and nodeTwo are LOWER than root

               return lowestCommonAncestor(root.left, nodeOne, nodeTwo);//loot at LEFT subtree

           }else if (nodeOneVal > parentValue && nodeTwoVal > parentValue){//if Both nodeOne and nodeTwo are BIGGER than root

               return lowestCommonAncestor(root.right, nodeOne, nodeTwo);//look at RIGHT subtree

           } else { // not left subtree nor right subtree, either nodeOne or nodeTwo == root

               // We've found the split point, this is the LCA
               return root;
           }

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

