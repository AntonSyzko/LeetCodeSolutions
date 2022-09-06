package leet.code.solutions.binary_tree;

/*
https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/

Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.

A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.

Example 1:
Input: nums = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: [0,-10,5,null,-3,null,9] is also accepted:

Example 2:
Input: nums = [1,3]
Output: [3,1]
Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.

Constraints:
1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums is sorted in a strictly increasing order.
 */
public class SortedArrayToBST {

    public static void main(String[] args) {
        int[] nums = new int[]{-10, -3, 0, 5, 9};
        TreeNode res = sortedArrayToBST(nums);
        System.out.println(res);
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        return constructBSTRecursive(nums, 0, nums.length - 1);
    }


    //NOTE !!!  MOST BINARY SEARCH PROBS INDICATE USING RECURSION
    private static TreeNode constructBSTRecursive(int[] nums, int start, int end) {

        //base case
        if (start > end) { // strictly >, when they CROSS - end of comparisons
            return null;
        }


        int mid = start + (end - start) / 2; // mod point for bin search
        TreeNode head = new TreeNode(nums[mid]);

        head.left = constructBSTRecursive(nums, start, mid - 1);//left subtree from start  till current mid point(+1)
        head.right = constructBSTRecursive(nums, mid + 1, end);//right subtree from current midpoint(+1) till end

        return head;
    }
}
