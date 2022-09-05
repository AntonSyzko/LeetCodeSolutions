package leet.code.solutions.binary_tree;

import java.util.LinkedList;
import java.util.List;

public class RecursiveOrderDFSs {

    public static void main(String[] args) {
        TreeNode<Integer> root = new  TreeNode<Integer>(1);

        TreeNode<Integer> rootLeft = new  TreeNode<Integer>(2);
        TreeNode<Integer> rootLeftLeft = new  TreeNode<Integer>(23);
        TreeNode<Integer> rootLeftRight = new  TreeNode<Integer>(24);

        TreeNode<Integer> rootRight = new  TreeNode<Integer>(3);
        TreeNode<Integer> rootRightLeft = new  TreeNode<Integer>(33);
        TreeNode<Integer> rootRightRight = new  TreeNode<Integer>(34);

        root.left = rootLeft;
        root.right = rootRight;
        root.left.left = rootLeftLeft;
        root.left.right = rootLeftRight;
        root.right.left = rootRightLeft;
        root.right.right = rootRightRight;

        print(root);
    }

    private static List<Integer> res = new LinkedList<>();


    private static void print( TreeNode<Integer> root) {
        inOrder(root);
        System.out.println("IN ORDER \r\n " + res);
        res.clear();

        preOrder(root);
        System.out.println("PRE ORDER \r\n " + res);
        res.clear();

        postOrder(root);
        System.out.println("POST ORDER \r\n " + res);
        res.clear();
    }

    private static void inOrder( TreeNode<Integer> root) {
        if (root == null) {//base case
            return;
        }

        inOrder(root.left);
        res.add(root.val);//root in middle
        inOrder(root.right);
    }

    private static void preOrder( TreeNode<Integer> root) {
        if (root == null) {//base case
            return;
        }

        res.add(root.val);//root first
        preOrder(root.left);
        preOrder(root.right);
    }

    private static void postOrder( TreeNode<Integer> root) {
        if (root == null) {//base case
            return;
        }

        postOrder(root.left);
        postOrder(root.right);
        res.add(root.val);//root last
    }
}
