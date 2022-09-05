package leet.code.solutions.binary_tree;

import java.util.*;

/*
Given the root of a binary tree, return the inorder traversal of its nodes' values.

Example 1:
Input: root = [1,null,2,3]
Output: [1,3,2]

Example 2:
Input: root = []
Output: []

Example 3:
Input: root = [1]
Output: [1]

Constraints:
The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100

Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class InOrderTraversal {

    public static void main(String[] args) {
        TreeNode<Integer> root = new  TreeNode<Integer>(1);
        TreeNode<Integer> rootLeft = null;
        TreeNode<Integer> rootRight = new  TreeNode<Integer>(2);
        TreeNode<Integer> rootRightLeft = new  TreeNode<Integer>(3);

        root.left = rootLeft;
        root.right = rootRight;
        root.right.left = rootRightLeft;

        List<Integer> res = inorderTraversal2(root);
        System.out.println(res);
    }

    public static List<Integer> inorderTraversal( TreeNode<Integer> root) {
        List<Integer> resultInOrderList = new ArrayList<>();
        Stack< TreeNode<Integer>> stack = new Stack<>();

        if (root == null) {
            return resultInOrderList;
        }

        TreeNode<Integer> currentNode = root;

        while (currentNode != null || !stack.isEmpty()) {
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }
            currentNode = stack.pop();//last in stack is bottom most left

            resultInOrderList.add(currentNode.val);//add to result

            currentNode = currentNode.right;//check the right sibling of current subtree
        }

        return resultInOrderList;
    }

    public static List<Integer> BSTInSortedOrder(TreeNode <Integer> tree) {

        List<Integer> res = new ArrayList<>();
        Deque<TreeNode<Integer>> stack = new ArrayDeque<>();

        TreeNode<Integer> currentNode = tree;

        while (!stack.isEmpty() || currentNode != null) {
            if(currentNode != null){
                //going left
                stack.addFirst(currentNode);//first time will be root
                currentNode = currentNode.left;//this will store all LEFTS

            }else {
                //going up
                currentNode = stack.removeFirst();//this will be root at the beginning ( FIRST ( so at the bottom of the stack )
                res.add(currentNode.val);
                //going right
                currentNode = currentNode.right;

            }
        }
        return res;
    }

    public static List<Integer> inorderTraversal2(TreeNode<Integer> root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;

        Stack<TreeNode<Integer>> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()){

            if(root.left != null){
                stack.push(root.left);
                root = root.left;

            }else{

                root = stack.pop();
                root.left = null;// dunno if needed
                result.add(root.val);

                if(root.right != null){
                    stack.push(root.right);
                    root = root.right;
                }
            }
        }
        return result;
    }
}
