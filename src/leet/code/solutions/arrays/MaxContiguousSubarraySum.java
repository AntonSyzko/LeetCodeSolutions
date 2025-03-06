package leet.code.solutions.arrays;


/*
Given an array of integers, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: The subarray [4,-1,2,1] has the largest sum = 6.


 */
public class MaxContiguousSubarraySum {

    public static void main(String[] args) {
      int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
      int res =  maxSubarraySum(nums);
        System.out.println(res);
    }

    private static int maxSubarraySum(int[] nums) {

        if(nums==null || nums.length==0){
            return 0;
        }

        int maxSum = nums[0];

        int maxEndingHere = nums[0];

        for (int i = 1; i <  nums.length; i++) {
            maxEndingHere = Math.max(maxEndingHere + nums[i], nums[i]);
            maxSum = Math.max(maxSum, maxEndingHere);
        }

        return maxSum;
    }

    private static int findMaxSubarraySum(int[] nums) {
        int n = nums.length;
        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];

        for(int i = 1; i < n; i++) {
            if(maxEndingHere + nums[i] < nums[i]) {
                maxEndingHere = nums[i];
            } else {
                maxEndingHere = maxEndingHere + nums[i];
            }

            if(maxEndingHere > maxSoFar) {
                maxSoFar = maxEndingHere;
            }
        }
        return maxSoFar;
    }

    private static int[] findMaxSubarrayIndices(int[] nums) {
        int n = nums.length;
        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];
        int currentStart = 0, maxStart = 0, maxEnd = 0;

        for(int i = 1; i < n; i++) {
            if(maxEndingHere + nums[i] < nums[i]) {
                maxEndingHere = nums[i];
                currentStart = i;
            } else {
                maxEndingHere = maxEndingHere + nums[i];
            }

            if(maxEndingHere > maxSoFar) {
                maxSoFar = maxEndingHere;
                maxStart = currentStart;
                maxEnd = i;
            }
        }

        return new int[]{maxStart, maxEnd};
    }

}

