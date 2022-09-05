package leet.code.solutions.arrays;

import java.util.Arrays;

/*
https://leetcode.com/problems/maximum-subarray/

Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
A subarray is a contiguous part of an array.

Example 1:
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.

Example 2:
Input: nums = [1]
Output: 1

Example 3:
Input: nums = [5,4,-1,7,8]
Output: 23

Constraints:
1 <= nums.length <= 105
-104 <= nums[i] <= 104
 */
public class MaxSubarrayKadanes {
    public static void main(String[] args) {
       int [] nums = {1,-1,2,3,-5};
       int maxSum = maxSubArray(nums);
        System.out.println(maxSum);
    }

    //O(n)
    private static int maxSubArray(int[] nums) {

        int maxSoFar = Integer.MIN_VALUE;
        int maxEndingHere = 0;

        for (int each : nums) {

            maxEndingHere = maxEndingHere + each; //previous with current each
            maxEndingHere = Math.max(each, maxEndingHere); // each itself or aggregated with prev

            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }


    private static int maxSubArrayTwoTraversals(int[] nums) {
        int maxInArray = Arrays.stream(nums).max().getAsInt();//first traversal

        if(maxInArray < 0 ) {
            return maxInArray;
        }

        int maxSoFar = 0;
        int maxEndingHere = 0;

        for (int each : nums) {
            maxEndingHere = maxEndingHere + each;
            maxEndingHere = Math.max(0, maxEndingHere);

            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }
    }
