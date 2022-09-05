package leet.code.solutions.binary_tree;

import java.util.Stack;

/*
https://leetcode.com/problems/flatten-binary-tree-to-linked-list/

Given the root of a binary tree, flatten the tree into a "linked list":
The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
The "linked list" should be in the same order as a pre-order traversal of the binary tree.


Example 1:
Input: root = [1,2,5,3,4,null,6]
Output: [1,null,2,null,3,null,4,null,5,null,6]

Example 2:
Input: root = []
Output: []

Example 3:
Input: root = [0]
Output: [0]

Constraints:
The number of nodes in the tree is in the range [0, 2000].
-100 <= Node.val <= 100
 */
public class FlattenBinaryTreeToLinkedList {

    public static void main(String[] args) {

    }

    private static void flatten(TreeNode<Integer> root) {
        if(root == null) return;

        Stack <TreeNode<Integer>> stack = new Stack<>();//try Deque array deque todo
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode<Integer> currentNode = stack.pop();//pop removes obj

            if(currentNode.right != null) {
                stack.push(currentNode.right);
            }

            if (currentNode.left != null) {
                stack.push(currentNode.left);//LEFT nodes stay on top of stack in whole while iteration
            }

//here to account if root was only one and stack pop from line 42 will give nothing
            if(!stack.isEmpty()) {
                //re-assign right node to previously LEFT node - since LEFT is now on top of STACK
                currentNode.right = stack.peek();//peek does NOT remove
            }
            //left nodes are eliminated
            currentNode.left = null;
        }
    }
}
