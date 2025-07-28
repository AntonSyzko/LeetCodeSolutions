package leet.code.solutions.binary_tree;


import java.util.Stack;

/*
Within a binary tree, a node x is considered good if the path from the root of the tree to the node x contains no nodes with a value greater than the value of node x

Given the root of a binary tree root, return the number of good nodes within the tree.

Input: root = [2,1,1,3,null,1,5]

Output: 3
 */
public class CountGoodNodesInBinaryTree {

    public static void main(String[] args) {
    TreeNode<Integer> root = new TreeNode(1);
    TreeNode<Integer> rootL = new TreeNode(2);
    TreeNode<Integer> rootR = new TreeNode(-1);
    TreeNode<Integer> rootLL = new TreeNode(3);
    TreeNode<Integer> rootLR = new TreeNode(4);

    root.left = rootL;
    root.right = rootR;
    rootL.left = rootLL;
    rootL.right = rootLR;

    int goodNodes = countNodes(root);
        System.out.println(goodNodes);

    }

    private static int countNodes(TreeNode<Integer> root) {
        return DFS(root, root.val);//root val is what will compare to

    }

    private static int DFS(TreeNode<Integer> node, int maxValSoFar) {
        //BASE
        if(node==null){
            return 0;
        }

         int countOfGoodNodes = (node.val >= maxValSoFar) ? 1 : 0;//if curr node VAL > max so far it ONE more to RES, otherwise carry ZERO onward

        maxValSoFar = Math.max(node.val, maxValSoFar);//max between max so far and curr node val


        countOfGoodNodes += DFS(node.left, maxValSoFar);
        countOfGoodNodes += DFS(node.right, maxValSoFar);

        return countOfGoodNodes;
    }
}
