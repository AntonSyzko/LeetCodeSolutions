package leet.code.solutions.linked_list;

/*
https://www.callicoder.com/convert-sorted-array-to-balanced-binary-search-tree/

Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

A height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example

Given the sorted array [1, 2, 3, 4, 5, 6]
 */


import leet.code.solutions.queue.TreeNode;


public class SortedArrayToBallancedBST {

    public static void main(String[] args) {
        int[] sorted = {1,2,3};
        TreeNode bst =  sortedArrayToBST(sorted);
        traverseInorder(bst);
    }

    private  static TreeNode sortedArrayToBST(int[] nums){

        if(nums==null || nums.length==0){
            return null;
        }

        return insertBalanced(nums, 0, nums.length-1);

    }
    private static TreeNode insertBalanced(int[] nums, int start, int end) {
           if(start > end){//BASE case
               return null;
           }

            int mid = start + (end - start)/ 2;
            TreeNode node = new TreeNode(nums[mid]);//create MID node

            node.left = insertBalanced(nums, start, mid-1);//exclude mid as we have  created node for it already
            node.right = insertBalanced(nums, mid+1, end);//exclude mid

            return node;
    }

    private static void traverseInorder(TreeNode node) {
        if(node == null) {
            return;
        }
        traverseInorder(node.left);
        System.out.print( node.getVal() + " " );
        traverseInorder(node.right);
    }
}
