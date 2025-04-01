package leet.code.solutions.blind75;

/*
https://leetcode.com/problems/house-robber/description/

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed,
the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

Example 2:

Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.


Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 400
 */
public class RobHouse {

    public static void main(String[] args) {
       int[] nums = {2,1,1,2};
       System.out.println(robBottomUp(nums));
    }

    // Solution 1: Dynamic Programming (Bottom-Up)
    private static int robBottomUp(int[] nums) {
        if(nums.length == 0) return 0;

        if(nums.length == 1) return nums[0];

        int[] DP = new int[nums.length];
        DP[0] = nums[0];
        DP[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            DP[i] = Math.max(DP[i-1],  DP[i-2] + nums[i]);
        }

        return DP[nums.length-1];
    }


    //time O(N)
    //space O(1)
    private static int rob(int[] nums) {
       int maxRes = 0;
       int rob1 = 0;
       int rob2 = 0;

        for (int i = 0; i < nums.length; i++) {
            maxRes = Math.max(rob1 + nums[i], rob2);
            rob1 = rob2;
            rob2 = maxRes;
        }
        return maxRes;
    }

    // Solution 3: Recursive with Memoization (Top-Down)
    private static int robRecursiveWithMemo(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // Use a memo array to store already computed results
        Integer[] memo = new Integer[nums.length];

        return robHelper(nums, nums.length - 1, memo);
    }

    private static int robHelper(int[] nums, int index, Integer[] memo) {
        // Base cases
        if (index < 0) {
            return 0;
        }

        // If we already computed this subproblem
        if (memo[index] != null) {
            return memo[index];
        }

        // Either skip current house or rob it (and skip the previous one)
        int result = Math.max(
                robHelper(nums, index - 1, memo),
                robHelper(nums, index - 2, memo) + nums[index]
        );

        // Save the result before returning
        memo[index] = result;

        return result;
    }

    private static int robRecursiveWithoutMemo(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        return robHelperWithoutMemo(nums, nums.length - 1);
    }

    private static int robHelperWithoutMemo(int[] nums, int index) {
        if(index < 0) {//BASE
            return 0;
        }

        int res = Math.max(
                robHelperWithoutMemo(nums, index -1),
                robHelperWithoutMemo(nums, index - 2) + nums[index]
        );
        return res;
    }
}
