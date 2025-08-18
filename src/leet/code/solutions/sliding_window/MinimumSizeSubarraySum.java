package leet.code.solutions.sliding_window;

/*
209
https://leetcode.com/problems/minimum-size-subarray-sum/

Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.

Example 1:

Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.
Example 2:

Input: target = 4, nums = [1,4,4]
Output: 1
Example 3:

Input: target = 11, nums = [1,1,1,1,1,1,1,1]
Output: 0

 */
public class MinimumSizeSubarraySum {

    public static void main(String[] args) {
        int[] nums = {2,3,1,2,4,3};
        int target = 7;
        int misSubarrayLen = findMinSizeSubarrayEqualsToSum(nums, target);
        System.out.println(misSubarrayLen);
    }


    private static int findMinSizeSubarrayEqualsToSum(int[] nums, int target){
        if(nums==null || nums.length==0){
            return -1;
        }

        int windowStart = 0;

        int minRes = Integer.MAX_VALUE;

        int ongoingSum = 0;

        for (int widnowEnd = 0; widnowEnd < nums.length; widnowEnd++) {

            ongoingSum += nums[widnowEnd];

            while(ongoingSum >= target){//WHILE !!!

                int windowSize = widnowEnd - windowStart + 1;

                minRes = Math.min(minRes, windowSize);

                ongoingSum -= nums[windowStart];//shrink sum

                windowStart++;//move window left
            }

        }

        return minRes == Integer.MAX_VALUE ? 0 : minRes;
    }
}
