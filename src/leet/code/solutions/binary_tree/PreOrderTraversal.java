package leet.code.solutions.binary_tree;

import java.util.*;

public class PreOrderTraversal {
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<Integer>(10);
        TreeNode<Integer> rootLeft = new TreeNode<Integer>(5);

        TreeNode<Integer> rootRight = new TreeNode<Integer>(20);
        TreeNode<Integer> rootRightLeft = new TreeNode<Integer>(15);

        root.left = rootLeft;
        root.right = rootRight;
        root.right.left = rootRightLeft;

        preorderRecursion(root);
        System.out.println("\r\n------------");

        List<Integer> res = preorderMy(root);
        System.out.println(res);
    }

    public static List<Integer> preorderFromBook(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode<Integer>> stack = new LinkedList<>();
        stack.addFirst(root);//first push root

        while (!stack.isEmpty()) {
            TreeNode<Integer> currentNode = stack.removeFirst();

            if (currentNode != null) {

                res.add(currentNode.val);

                stack.addFirst(currentNode.right);
                stack.addFirst(currentNode.left);
            }
        }
        return res;
    }

    public static List<Integer> preorderMy(TreeNode<Integer> root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Stack<TreeNode<Integer>> stack = new Stack<>();
        stack.add(root);//first push root

        while (!stack.isEmpty()) {
            TreeNode<Integer> currentNode = stack.pop();

            if (currentNode != null) {
                res.add(currentNode.val);

                stack.push(currentNode.right);
                stack.push(currentNode.left);
            }
        }
        return res;
    }


    private static void preorderRecursion(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " -> ");//first traverse root node

        preorderRecursion(root.left);
        preorderRecursion(root.right);
    }
}
