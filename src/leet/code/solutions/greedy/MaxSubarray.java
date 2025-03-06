package leet.code.solutions.greedy;

/*
Given an array of integers nums, find the subarray with the largest sum and return the sum.

A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:

Input: nums = [2,-3,4,-2,2,1,-1,4]

Output: 8
Explanation: The subarray [4,-2,2,1,-1,4] has the largest sum 8.

Example 2:

Input: nums = [-1]

Output: -1
Constraints:

1 <= nums.length <= 1000
-1000 <= nums[i] <= 1000

 */
public class MaxSubarray {

    public static void main(String[] args) {
        int [] nums = {2,-3,4,-2,2,1,-1,4};
        int maxSubArraySum = maxSubArrayKadane(nums);
        System.out.println(maxSubArraySum);
    }

    private static int maxSubArrayKadane(int[] nums) {


        int maxSum =  nums[0];

        int ongoingSum = 0;

            for (int each : nums) {

                if(ongoingSum <0){
                    ongoingSum = 0;
                }

                ongoingSum += each;
                maxSum = Math.max(maxSum, ongoingSum);
            }

        return maxSum;
    }
}
