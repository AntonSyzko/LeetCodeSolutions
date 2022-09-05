package leet.code.solutions.binary_tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PostOrderTraversal {
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(10);
        TreeNode<Integer> rootLeft = new TreeNode<>(5);

        TreeNode<Integer> rootRight = new TreeNode<>(20);
        TreeNode<Integer> rootRightLeft = new TreeNode<>(15);

        root.left = rootLeft;
        root.right = rootRight;
        root.right.left = rootRightLeft;

        postOrderRecursive(root);
        List<Integer> posOrder = postOrder(root);
        System.out.println("\r\n" + posOrder);
    }

    private static List<Integer> postOrder(TreeNode<Integer> root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode<Integer>> stack = new Stack<>();
        Stack<TreeNode<Integer>> outputStack = new Stack<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode<Integer> current = stack.pop();

            outputStack.push(current);//so first time root goes to the  very bottom , then lefts

            if(current.left != null) {
                stack.push(current.left);
            }

            if(current.right != null){
                stack.push(current.right);
            }
        }

        while (!outputStack.isEmpty()){
            res.add(outputStack.pop().val);
            //or sout if not a list return
        }
        return res;

    }

    private static void postOrderRecursive(TreeNode<Integer> root){
        if(root == null) {
            return;
        }

        postOrderRecursive(root.left);
        postOrderRecursive(root.right);
        System.out.print(root.val + " ");
    }
}
