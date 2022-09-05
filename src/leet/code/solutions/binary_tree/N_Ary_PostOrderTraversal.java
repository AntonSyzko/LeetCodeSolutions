package leet.code.solutions.binary_tree;

import java.util.*;

/*
https://leetcode.com/problems/n-ary-tree-postorder-traversal/

Given the root of an n-ary tree, return the postorder traversal of its nodes' values.
Nary-Tree input serialization is represented in their level order traversal.
Each group of children is separated by the null value (See examples)

Example 1:
Input: root = [1,null,3,2,4,null,5,6]
Output: [5,6,3,2,4,1]

Example 2:
Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output: [2,6,14,11,7,3,12,8,4,13,9,10,5,1]

Constraints:
The number of nodes in the tree is in the range [0, 104].
0 <= Node.val <= 104
The height of the n-ary tree is less than or equal to 1000.

Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class N_Ary_PostOrderTraversal {

    public static void main(String[] args) {
        Node root = new Node(1);
        Node root1 = new Node(2);
        Node root2 = new Node(3);
        Node root3 = new Node(4);
        Node root4 = new Node(5);
        Node root5 = new Node(6);
        root.children = List.of(root1, root2);
        root1.children = List.of(root3, root4, root5);

        //  List<Integer> postOrderList = postOrderTraversalRecursive(root);
        //System.out.println(postOrderList);
    }


    private static List<Integer> postorder(Node root) {
        LinkedList<Node> stack = new LinkedList<>();//linked list NOT List<> interface - since pollLast() method is of VERY  linked list
        LinkedList<Integer> output_result = new LinkedList<>();//linked list NOT List<> interface - since addFirst() method is of VERY linked list

        if (root == null) {
            return output_result;//return empty res
        }

        stack.add(root);

        while (!stack.isEmpty()) {

            Node lastInStack = stack.pollLast();//so latest nodes will be on top

            output_result.addFirst(lastInStack.val);//latest from stack go first in result

            for (Node child : lastInStack.children) {
                stack.add(child);
            }
        }
        return output_result;
    }
}
