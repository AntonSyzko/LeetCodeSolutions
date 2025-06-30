package leet.code.solutions.arrays;

/*
775

https://leetcode.com/problems/global-and-local-inversions/description/

You are given an integer array nums of length n which represents a permutation of all the integers in the range [0, n - 1].

The number of global inversions is the number of the different pairs (i, j) where:

0 <= i < j < n
nums[i] > nums[j]
The number of local inversions is the number of indices i where:

0 <= i < n - 1
nums[i] > nums[i + 1]
Return true if the number of global inversions is equal to the number of local inversions.



Example 1:

Input: nums = [1,0,2]
Output: true
Explanation: There is 1 global inversion and 1 local inversion.
Example 2:

Input: nums = [1,2,0]
Output: false
Explanation: There are 2 global inversions and 1 local inversion.

 */
public class GlobalAndLocalInversions {

    public static void main(String[] args) {
        int[] nums = {1,0,2};
        boolean isGlobalEqualLocal = isIdealPermutation(nums);
        System.out.println(isGlobalEqualLocal);

        int[] nums2 = {1,2,0};
        boolean isGlobalEqualLocal2 = isIdealPermutation(nums2);
        System.out.println(isGlobalEqualLocal2);
    }

    /*
    Time: O(n)
    Space: O(1)
     */
    private static boolean isIdealPermutation(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (Math.abs(nums[i] - i) > 1) {
                return false;
            }
        }
        return true;
    }

    /**
      Logic: Check if any element is more than 1 position away from where it "should" be in a sorted array.
      This elegant solution leverages the mathematical property that in a permutation, global inversions equal local inversions precisely when no element is displaced by more than 1 position!
     **/
}
