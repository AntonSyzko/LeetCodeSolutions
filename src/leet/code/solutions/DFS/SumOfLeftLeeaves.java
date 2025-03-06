package leet.code.solutions.DFS;


import leet.code.solutions.queue.TreeNode;

public class SumOfLeftLeeaves {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(sumOfLeftLeaves(root));
    }


    private static int sumOfLeftLeaves(TreeNode node) {

        if(node==null){
            return 0;
        }else if(node.left != null && node.left.left == null && node.left.right == null){//if left node EXISTS ( not NULL) and it is a LEAF
            return node.left.getVal() +  sumOfLeftLeaves(node.right);
        }else{
            return sumOfLeftLeaves(node.left) + sumOfLeftLeaves(node.right);
        }
    }
}
