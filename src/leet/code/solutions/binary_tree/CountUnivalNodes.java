package leet.code.solutions.binary_tree;

/*
he task is to determine the number of subtrees within a binary tree where all nodes have the same value. These subtrees, where all nodes share a common value, are referred 
to as uni-value subtrees.
 The input is the root of the binary tree, and the expected output is an integer representing the total count of uni-value subtrees within that tree.
 */
public class CountUnivalNodes {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(1);

        root.right = new TreeNode(1);
        root.right.left = new TreeNode(1);
        root.left.right = new TreeNode(1);

        int unis = countUnivalSubtrees(root);
        System.out.println(unis);
    }

    private  static int COUNT;

    //O(N)
    //O(N)
    public  static int  countUnivalSubtrees(TreeNode root) {
        COUNT = 0;

        isUnival(root);

        return COUNT;
    }

    private static boolean isUnival(TreeNode node) {
        if (node == null) {//BASE
            return true;
        }

        boolean left = isUnival(node.left);
        boolean right = isUnival(node.right);


        if(left && right){//AND both OK

            if(node.left != null && node.left.val != node.val){//check if LEFT childs node val == node val
                return false;
            }

            if(node.right != null && node.right.val != node.val){//check if RIGHT childs node val == node val
                return false;
            }

            COUNT++;

            return true;
        }

        return false;//not left && right
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