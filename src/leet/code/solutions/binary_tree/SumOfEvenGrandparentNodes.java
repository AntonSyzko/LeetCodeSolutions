package leet.code.solutions.binary_tree;
/*
Given the root of a binary tree, return the sum of values of nodes with an even-valued grandparent. If there are no nodes with an even-valued grandparent, return 0.

A grandparent of a node is the parent of its parent if it exists.

Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
Output: 18
Explanation: The red nodes are the nodes with even-value grandparent while the blue nodes are the even-value grandparents.

Constraints:

The number of nodes in the tree is in the range [1, 104].
1 <= Node.val <= 100
 */

public class SumOfEvenGrandparentNodes {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
      //  root.left = new TreeNode(9);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(1);

        int sum = sumEvenGrandparentNodes(root);
        System.out.println(sum);
    }

    private static int sumEvenGrandparentNodes(TreeNode root) {
        return calculatSubSum(root, -1,-1);// -1 is because of csontraints 1 <= node <= 100 and -1 itself is not evene so it wson't be considered in result anyways
    }

    private static int calculatSubSum(TreeNode node, int parentValue, int grandParentValue) {
        int subSumOfElementsWithEvenGrandpas = 0;

        if(grandParentValue % 2 == 0){//if grandpa is EVEN
            subSumOfElementsWithEvenGrandpas += node.val;
        }

        if(node.left != null){
            subSumOfElementsWithEvenGrandpas += calculatSubSum(node.left, node.val, parentValue);//!!! current node became parent and EX parent became grandpa
        }

        if(node.right != null){
            subSumOfElementsWithEvenGrandpas += calculatSubSum(node.right, node.val, parentValue);
        }

        return subSumOfElementsWithEvenGrandpas;
    }


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
