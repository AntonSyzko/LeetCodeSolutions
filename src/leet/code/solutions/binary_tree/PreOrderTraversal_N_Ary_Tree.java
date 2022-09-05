package leet.code.solutions.binary_tree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
https://leetcode.com/problems/n-ary-tree-preorder-traversal/

Given the root of an n-ary tree, return the preorder traversal of its nodes' values.
Nary-Tree input serialization is represented in their level order traversal.
Each group of children is separated by the null value (See examples)

Example 1:
Input: root = [1,null,3,2,4,null,5,6]
Output: [1,3,5,6,2,4]

Example 2:
Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output: [1,2,3,6,7,11,14,4,8,12,5,9,13,10]

 */
public class PreOrderTraversal_N_Ary_Tree {

    public static void main(String[] args) {

    }


    public static List<Integer> preorder(Node root) {

        LinkedList<Node> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();

        if (root == null) {
            return output;
        }

        stack.add(root);

        while (!stack.isEmpty()) {

            Node topOfStack = stack.pollLast();
            output.add(topOfStack.val);//add to tail  of output

            Collections.reverse(root.children);//reverse to have PRE-ORDER ( Right children go first )

            for (Node child : root.children) {
                stack.add(child);
            }
        }
        return output;
    }
}
