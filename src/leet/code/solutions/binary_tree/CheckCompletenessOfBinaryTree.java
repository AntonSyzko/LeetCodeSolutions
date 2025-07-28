package leet.code.solutions.binary_tree;

import java.util.LinkedList;
import java.util.Queue;

/*
https://leetcode.com/problems/check-completeness-of-a-binary-tree/

Given the root of a binary tree, determine if it is a complete binary tree.

In a complete binary tree, every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.


Example 1:

Input: root = [1,2,3,4,5,6]
Output: true
Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.

Example 2:


Input: root = [1,2,3,4,5,null,7]
Output: false
Explanation: The node with value 7 isn't as far left as possible.


Constraints:

The number of nodes in the tree is in the range [1, 100].
1 <= Node.val <= 1000
 */
public class CheckCompletenessOfBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.right = new TreeNode(6);//change left right to incomplete

        boolean isCompleteTree = isCompleteTree(root);
        System.out.println(isCompleteTree);

    }

    /*
    Time and Space Complexity

        Time Complexity: O(n) where n is the number of nodes in the tree. We visit each node exactly once.
        Space Complexity: O(w) where w is the maximum width of the tree. In the worst case, this could be O(n/2) ≈ O(n) for a complete binary tree.

     */
    private static boolean isCompleteTree(TreeNode root) {

        boolean isEndNode = false;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {

            TreeNode currentNode = queue.poll();

            if(currentNode == null ) { // if curr node is NULL it is an END of the branch

                isEndNode = true;

            } else  { // current currentNode is NOT null

                if(isEndNode) { // but there was a NULL currentNode before

//if there was a NULL currentNode BEFORE and  current currentNode is NOT null - TREE is INCOMPLETE by default, since we visit LEFT nodes before RIGHT ones and if LEFT was NULL - having right as NULL - incomplete
                    return false;

                }
                 /**
                IMPORTANT !!! - LEVEL ORDER TRAVERSAL
                we will be checking LEFT ones before right
                hence if NULL seen before current - it was on left , and right is NULL which makes the tree incomplete
                 since at the last level nodes have to be as LEFT as possible for completeness
                 **/

                 TreeNode left = currentNode.left;// goes first !!!
                 TreeNode right = currentNode.right;

                 queue.offer(left);//added first !!!
                 queue.offer(right);

            }
        }
        return true;
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

/*
 The key insight is that when doing a level-order traversal on a complete binary tree, all null nodes should come after all non-null nodes.
 */



/*
  public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }

        int[] alphabet = new int[26];

        for(int i = 0; i < s.length(); i ++){
             alphabet[s.charAt(i) - 'a']++;
                          alphabet[t.charAt(i) - 'a']--;

        }

        for(int letter : alphabet){
            if(letter != 0){
                return false;
            }
        }

        return true;
    }
 */