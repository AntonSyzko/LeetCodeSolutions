package leet.code.solutions.blind75;

/*
https://leetcode.com/problems/house-robber-ii/

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed.
All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one.
Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.
Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.


Example 1:

Input: nums = [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.

Example 2:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
Example 3:

Input: nums = [1,2,3]
Output: 3


Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 1000
 */
public class HouseRobber2 {

    // Test cases
    public static void main(String[] args) {

        int[] example1 = {2, 3, 2};
        System.out.println("Example 1: " + rob(example1)); // Expected: 3

        int[] example2 = {1, 2, 3, 1};
        System.out.println("Example 2: " + rob(example2)); // Expected: 4

        int[] example3 = {1, 2, 3};
        System.out.println("Example 3: " + rob(example3)); // Expected: 3

        // Additional test cases
        int[] example4 = {200, 3, 140, 20, 10};
        System.out.println("Example 4: " + rob(example4)); // Expected: 340 (200 + 140)
    }

    private static int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        // Since houses are in a circle, we can't rob both first and last houses
        // So we have two cases:
        // 1. Rob houses from 0 to n-2 (excluding the last house)
        // 2. Rob houses from 1 to n-1 (excluding the first house)
        // Then we take the maximum of these two cases

        return Math.max(
                robRange(nums, 0, nums.length - 2),  // Exclude last house
                robRange(nums, 1, nums.length - 1)   // Exclude first house
        );
    }

    // Helper method to solve the original House Robber problem for a range of houses
    private  static int robRange(int[] nums, int start, int end) {
        // Space-optimized DP solution
        int prev1 = 0;  // Max amount if we consider no houses
        int prev2 = 0;  // Will be updated in the first iteration

        for (int i = start; i <= end; i++) {
            int currentMax = Math.max(prev2, prev1 + nums[i]);
            prev1 = prev2;
            prev2 = currentMax;
        }

        return prev2;
    }


    private static int robBottomUp(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        // Since houses are in a circle, we can't rob both first and last houses
        // Calculate for two cases:
        // 1. Including first house but excluding last house
        // 2. Including last house but excluding first house

        return Math.max(
                robRangeBottomUp(nums, 0, nums.length - 2),  // Exclude last house
                robRangeBottomUp(nums, 1, nums.length - 1)   // Exclude first house
        );
    }

    // Solve the original House Robber problem for a range of houses using bottom-up DP
    private static int robRangeBottomUp(int[] nums, int start, int end) {
        int length = end - start + 1;

        // Handle special cases for small ranges
        if (length == 0) return 0;
        if (length == 1) return nums[start];

        // Create DP array
        // dp[i] represents the maximum amount that can be robbed up to the ith house in the range
        int[] dp = new int[length];

        // Base cases
        dp[0] = nums[start];
        dp[1] = Math.max(nums[start], nums[start + 1]);

        // Fill the DP array
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(
                    dp[i - 1],
                    dp[i - 2] + nums[start + i]
            );
        }

        return dp[length - 1];
    }
}
