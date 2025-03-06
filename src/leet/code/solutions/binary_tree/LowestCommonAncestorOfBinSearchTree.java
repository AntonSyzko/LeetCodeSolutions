package leet.code.solutions.binary_tree;

/*
Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.

According to the definition of LCA on Wikipedia:
“The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”



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


Constraints:

The number of nodes in the tree is in the range [2, 105].
-109 <= Node.val <= 109
All Node.val are unique.
p != q


 */
public class LowestCommonAncestorOfBinSearchTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);

        TreeNode LCA = lowestCommonAncestorRecursive(root, root.left, root.right.right );
        System.out.println(LCA.val);

    }

    // MIND binary SEARCH TREE -> meaning it balanced - lower at LEFT, higher at RIGHT
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode nodeOne, TreeNode nodeTwo) {
        if(nodeOne.val < root.val && nodeTwo.val < root.val){// both SMALLER -> look up LEFT

            return lowestCommonAncestor(root.left, nodeOne, nodeTwo);//look at the left subtree as BOTH node's vals are LESS than roots LEFT

        } else if( nodeOne.val > root.val && nodeTwo.val > root.val){//both BIGGER -> look up RIGHT

            return lowestCommonAncestor(root.right, nodeOne,nodeTwo);// look at the right as BOTH node's vals are BIGGER than roots RIGHT

        } else {

            return root;//root val is neither bigger or lower than BOTH nodes val and it is the lowest common ancestor

        }
    }

    private  static TreeNode lowestCommonAncestorRecursive(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }
        if (Math.max(p.val, q.val) < root.val) {// both smaller
            return lowestCommonAncestor(root.left, p, q);//lookup left
        } else if (Math.min(p.val, q.val) > root.val) {//both  higher
            return lowestCommonAncestor(root.right, p, q);//lookup right
        } else {
            return root;//not BOTH smaller OR higher
        }
    }

    private static TreeNode lowestCommonAncestorIterative(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode cur = root;

        while (cur != null) {
            if (p.val > cur.val && q.val > cur.val) {//both bigger
                cur = cur.right;// lookup right
            } else if (p.val < cur.val && q.val < cur.val) {//both lower
                cur = cur.left;//lookup left
            } else {
                return cur;//not BOTH bigger OR higher
            }
        }
        return null;
    }


     private static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    }
