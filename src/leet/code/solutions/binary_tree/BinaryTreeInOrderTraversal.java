package leet.code.solutions.binary_tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInOrderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);

        root.left = new TreeNode(3);
        root.right = new TreeNode(8);

        root.left.left = new TreeNode(1);

        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        root.right.right.right = new TreeNode(10);

        inOrderTraversal(root);
    }
    
    private static void inOrderTraversal(TreeNode root) {
        if(root==null) return;

        inOrderTraversal(root.left);
        System.out.print(root.val + ", ");
        inOrderTraversal(root.right);

    }

    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {//OR

            while (root != null) {//all LEFT go first
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            res.add(root.val);

            //then ALL RIGHT
            root = root.right;

        }

        return res;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

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
