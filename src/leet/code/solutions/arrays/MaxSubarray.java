package leet.code.solutions.arrays;

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

Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
public class MaxSubarray {

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 2, 5, -11, 6};//7
        int res = maxSubArraySum(nums);
        System.out.println(res);

       nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};//6
        res = maxSubArraySum(nums);
        System.out.println(res);

        nums = new int[]{5,4,-1,7,8};//23
       res = maxSubArraySum(nums);
        System.out.println(res);

        nums = new int[]{1};//1
        res = maxSubArraySum(nums);
        System.out.println(res);

        nums = new int[]{8, -7, -3, 5, 6, -2, 3, -4, 2};
        res = maxSubArraySum2(nums);
        System.out.println(res);

    }


    public static int maxSubArraySum2(int[] nums) {//kadane
        int currentsum = nums[0];
        int maxSum = currentsum;

        for (int i = 1; i < nums.length ; i++) {
           currentsum = Math.max(nums[i], currentsum + nums[i]);
           maxSum = Math.max(currentsum, maxSum);
        }
        return maxSum;
    }

    public static int maxSubArraySum(int[] nums) {
        int currentSum = nums[0];
        int maxSum = currentSum;

        for (int i = 1; i < nums.length; i++) {// zero does not count - we have it at start

             if(nums[i] > currentSum + nums[i]){//if the VERY current number is MORE than previous whole sum
                 currentSum = nums[i]; // then RESET current to this ( since even a single digit is MORE than all the SUM before )
             }else {
                 currentSum = currentSum + nums[i]; // otherwise Continue accumulating ongoing SUM
             }
             maxSum = Math.max(maxSum, currentSum);//just choose MAX
        }
        return maxSum;
    }
}
