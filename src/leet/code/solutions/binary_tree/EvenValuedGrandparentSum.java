package leet.code.solutions.binary_tree;

/*
https://leetcode.com/problems/sum-of-nodes-with-even-valued-grandparent/

Given the root of a binary tree, return the sum of values of nodes with an even-valued grandparent. If there are no nodes with an even-valued grandparent, return 0.
A grandparent of a node is the parent of its parent if it exists.

Example 1:
Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
Output: 18

Explanation: The red nodes are the nodes with even-value grandparent while the blue nodes are the even-value grandparents.

Example 2:
Input: root = [1]
Output: 0

Constraints:
The number of nodes in the tree is in the range [1, 104].
1 <= Node.val <= 100
 */
public class EvenValuedGrandparentSum {
    public static void main(String[] args) {
        TreeNode<Integer> root = new  TreeNode<Integer>(2);
        TreeNode<Integer> rootL = new  TreeNode<Integer>(1);
        TreeNode<Integer> rootR = new  TreeNode<Integer>(1);
        TreeNode<Integer> rootLL = new  TreeNode<Integer>(10);
        TreeNode<Integer> rootRR = new  TreeNode<Integer>(20);
    root.left = rootL;
    root.right = rootR;
    rootL.left = rootLL;
    rootR.right = rootRR;

    int sumOfNodesWithEvenGrandpa = sumEvenGrandparent(root);
        System.out.println(sumOfNodesWithEvenGrandpa);
    }

    private static int sumEvenGrandparent( TreeNode<Integer> root) {

        return calculateSubSum(root, -1,-1);//-1 are arbitrary vals to start with , at this (root) level there are NO parent or grandparent
    }

    private static int calculateSubSum( TreeNode<Integer> currentNode, int parentValue, int grandParentValue) {
        int subSum = 0;

        if(grandParentValue % 2 == 0) {//found a match
            subSum += currentNode.val;
        }

        if(currentNode.left != null) {
            //current node becomes PARENT and EX parent becomes GRANDPARENT
            subSum += calculateSubSum(currentNode.left, currentNode.val, parentValue);
        }

        if(currentNode.right != null) {
            //current node becomes PARENT and EX parent becomes GRANDPARENT
            subSum += calculateSubSum(currentNode.right, currentNode.val, parentValue);
        }

        return subSum;
    }
}
