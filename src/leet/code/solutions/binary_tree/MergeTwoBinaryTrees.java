package leet.code.solutions.binary_tree;

/*
https://leetcode.com/problems/merge-two-binary-trees/
You are given two binary trees root1 and root2.
Imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.
 You need to merge the two trees into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node.
 Otherwise, the NOT null node will be used as the node of the new tree.

Return the merged tree.
Note: The merging process must start from the root nodes of both trees.

Example 1:
Input: root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
Output: [3,4,5,5,4,null,7]

Example 2:
Input: root1 = [1], root2 = [1,2]
Output: [2,2]

Constraints:
The number of nodes in both trees is in the range [0, 2000].
-104 <= Node.val <= 104
 */
public class MergeTwoBinaryTrees {
    public static void main(String[] args) {
        TreeNode<Integer> root1 = new  TreeNode<Integer>(4);
        TreeNode<Integer> root1left = new  TreeNode<Integer>(2);
        TreeNode<Integer> root1right = new  TreeNode<Integer>(5);
        TreeNode<Integer> root1rightLeft = new  TreeNode<Integer>(50);
        TreeNode<Integer> root1rightRight = new  TreeNode<Integer>(500);
        root1.left = root1left;
        root1.right = root1right;
        root1.right.left = root1rightLeft;
        root1.right.right = root1rightRight;

        TreeNode<Integer> root2 = new  TreeNode<Integer>(3);
        TreeNode<Integer> root2left = new  TreeNode<Integer>(1);
        TreeNode<Integer> root2right = new  TreeNode<Integer>(6);
        TreeNode<Integer> root2rightLeft = new  TreeNode<Integer>(60);
        TreeNode<Integer> root2rightRight = new  TreeNode<Integer>(600);
        TreeNode<Integer> root2rightRightLeft = new  TreeNode<Integer>(456);
        root2.left = root2left;
        root2.right = root2right;
        root2.right.left = root2rightLeft;
        root2.right.right = root2rightRight;
        root2.right.right.left = root2rightRightLeft;



        TreeNode<Integer> mergedRoots = mergeTrees(root1, root2);
        System.out.println(" MERGED ");
        System.out.println(mergedRoots);
    }

    public static  TreeNode<Integer> mergeTrees( TreeNode<Integer> root1,  TreeNode<Integer> root2) {//optimised
        if(root1==null && root2==null){
            return null;
        }

        if(root1 == null){
            return root2;
        }
        if(root2 == null){
            return root1;
        }

        root1.val += root2.val;//aggregate vals of both nodes ( root1.val + root2.val )

        //recursion
        root1.left = mergeTrees(root1.left, root2.left);//merge left sub_branches

        root1.right = mergeTrees(root1.right, root2.right);//merge right sub_branches

        return root1;
    }



        public static  TreeNode<Integer> mergeTreesMy( TreeNode<Integer> root1,  TreeNode<Integer> root2) {//not optimised
            TreeNode<Integer> result ;
            if (root1 == null && root2 == null) {
                return null;
            }

            if ( root1 == null){
                return root2;
            }
            if (root2 == null){
                return root1;
            }

            result = new  TreeNode<Integer>(root1.val + root2.val);
            result.left = mergeTrees(root1.left, root2.left);
            result.right = mergeTrees(root1.right, root2.right);

            return result;
        }

}
