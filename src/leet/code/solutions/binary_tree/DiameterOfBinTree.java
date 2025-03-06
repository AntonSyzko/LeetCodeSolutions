package leet.code.solutions.binary_tree;

/*
https://neetcode.io/problems/binary-tree-diameter

The diameter of a binary tree is defined as the length of the longest path between any two nodes within the tree.
 The path does not necessarily have to pass through the root.


The length of a path between two nodes in a binary tree is the number of edges between the nodes.

Given the root of a binary tree root, return the diameter of the tree.

Input: root = [1,null,2,3,4,5]

Output: 3
Explanation: 3 is the length of the path [1,2,3,5] or [5,3,2,4].
 */
public class DiameterOfBinTree {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        TreeNode rootR = new TreeNode(2);
        root.right = rootR;
        TreeNode rootRL = new TreeNode(3);
        rootR.left = rootRL;
        TreeNode rootRR = new TreeNode(4);
        rootR.right = rootRR;
        TreeNode rootRLL = new TreeNode(5);
        rootRR.left = rootRLL;



        int diameter = diameterOfBinaryTree(root);
        System.out.println(diameter);

    }




    private static int diameterOfBinaryTree(TreeNode root) {
        if(root==null){
            return 0;
        }

        int leftSubtreeHeight = maxHeight(root.left);
        int rightSubtreeHeight = maxHeight(root.right);

        int currDiameter = leftSubtreeHeight + rightSubtreeHeight;

        int leftSubtreeDiameter = diameterOfBinaryTree(root.left);
        int rightSubtreeDiameter = diameterOfBinaryTree(root.right);

        int maxSubtreeDiameter  = Math.max(leftSubtreeDiameter,rightSubtreeDiameter);


         int maxOverallDIameter = Math.max( currDiameter,maxSubtreeDiameter);

         return maxOverallDIameter;
    }



    private static int maxHeight(TreeNode node) {
        if(node==null){
            return 0;
        }

        int leftHeight = maxHeight(node.left);
        int rightHeight = maxHeight(node.right);

        return  1 + Math.max(leftHeight, rightHeight);
    }

    //-------------- DFS -----------------

    public int diameterOfBinaryTreeDFS(TreeNode root) {
        int[] res = new int[1];

        dfs(root, res);

        return res[0];
    }

    private int dfs(TreeNode node, int[] res) {
        if (node == null) {
            return 0;
        }

        int left = dfs(node.left, res);
        int right = dfs(node.right, res);

        res[0] = Math.max(res[0], left + right);

        return 1 + Math.max(left, right);
    }

}
