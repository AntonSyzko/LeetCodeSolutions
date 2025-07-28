package leet.code.solutions.arrays;

/*
1567

https://leetcode.com/problems/maximum-length-of-subarray-with-positive-product/description/

Given an array of integers nums, find the maximum length of a subarray where the product of all its elements is positive.

A subarray of an array is a consecutive sequence of zero or more values taken out of that array.

Return the maximum length of a subarray with positive product.

Example 1:

Input: nums = [1,-2,-3,4]
Output: 4
Explanation: The array nums already has a positive product of 24.

Example 2:

Input: nums = [0,1,-2,-3,-4]
Output: 3
Explanation: The longest subarray with positive product is [1,-2,-3] which has a product of 6.
Notice that we cannot include 0 in the subarray since that'll make the product 0 which is not positive.

Example 3:

Input: nums = [-1,-2,-3,0,1]
Output: 2
Explanation: The longest subarray with positive product is [-1,-2] or [-2,-3].

Constraints:

1 <= nums.length <= 105
-109 <= nums[i] <= 109
 */
public class MaxLengthOfSubarrayWIthPositiveProduct {

    public static void main(String[] args) {
//        int []nums = {0,1,-2,-3,-4};
//        int maxLen = getMaxLen(nums);
//        System.out.println(maxLen);//3
//
//        int []nums2 = { 1,-2,-3,4};
//        int maxLen2 = getMaxLen(nums2);
//        System.out.println(maxLen2);//4

        int []nums3 = { -1 ,2};
        int maxLen3  = getMaxLen(nums3);
        System.out.println(maxLen3);//1
    }

    private static int getMaxLen(int[] nums) {
        int maxLen = 0;
        int len = nums.length;

        // pos: length of longest subarray ending at current index with positive product
        // neg: length of longest subarray ending at current index with negative product
        int positiveCount = 0;
        int negativeCount = 0;

        for (int i = 0; i < len; i++) {

            int curr = nums[i];

            if(curr == 0){

                positiveCount = 0;//zero resets ALL
                negativeCount = 0;

            }else if ( curr > 0){

                // Positive number extends both positive and negative subarrays , BOTH  + 1
                positiveCount = positiveCount + 1;
                negativeCount = (negativeCount == 0) ? 0 : negativeCount  + 1;

            }else {

                // Negative number swaps positive and negative subarray lengths
                int tempPositive = positiveCount;

                positiveCount = (negativeCount == 0) ? 0 : negativeCount  + 1;

                negativeCount = tempPositive + 1;
            }

            maxLen = Math.max(maxLen, positiveCount);
            
        }
        return maxLen;
    }
}