package leet.code.solutions.dynamic_programming;

import java.util.HashMap;
import java.util.Map;

/*
https://www.callicoder.com/longest-increasing-subsequence/

Given an array of integers A, find the length of the longest strictly increasing subsequence of A.

A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements.

 For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7]. but for this example answer is [0,1,2,7]

Example 1:

Input: A = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

Example 2:

Input: nums = [0,1,0,3,2,3]
Output: 4

Example 3:

Input: nums = [7,7,7,7,7,7,7]
Output: 1
 */
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {

        int[]a = {0,3,1,6,2,2,7};
        System.out.printf("Length of longest increasing subsequence = %d%n", lisMemoization(a));//4


        int[]a2 = {10,9,2,5,3,7,101,18};
        System.out.printf("Length of longest increasing subsequence = %d%n", lisMemoization(a2));//4


        int[]a3 = {0,1,0,3,2,3};
        System.out.printf("Length of longest increasing subsequence = %d%n", lisMemoization(a3));//4

        int[]a4 = {7,7,7,7,7,7,7};
        System.out.printf("Length of longest increasing subsequence = %d%n", lisMemoization(a4));//1
    }


    // ============================================================================
    // APPROACH 1: RECURSIVE (Most Intuitive)
    // Time: O(2^n), Space: O(n)
    // ============================================================================

    public static int lisRecursive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int startIndex = 0;
        int prevValue = Integer.MIN_VALUE;

        return lisHelper(nums, startIndex, prevValue);
    }

    private static int lisHelper(int[] nums, int index, int prevValue) {
        // Base case: reached end of array
        if (index == nums.length) {
            return 0;
        }

        // Option 1: Skip current element
        int exclude = lisHelper(nums, index + 1, prevValue);//prev stays SAME

        // Option 2: Include current element (only if it's greater than previous)
        int include = 0;
        if (nums[index] > prevValue) {
            include = 1 + lisHelper(nums, index + 1, nums[index]);//mind +1 - we are calculating length, not a sum of LIS elements
            //prev was passed as current nums[index]
        }

        return Math.max(exclude, include);//max length of both
    }

    // ============================================================================
    // APPROACH 2: MEMOIZATION (Top-Down DP)
    // Time: O(n²), Space: O(n²)
    // ============================================================================

    public static int lisMemoization(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int startIndex = 0;
        int prevValue = Integer.MIN_VALUE;

        Map<String, Integer> memo = new HashMap<>();

        return lisMemoHelper(nums, startIndex, prevValue, memo);
    }

    private static int lisMemoHelper(int[] nums, int index, int prevValue, Map<String, Integer> memo) {
        //BASE
        if (index == nums.length) {
            return 0;
        }

        String key = index + "," + prevValue;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int exclude = lisMemoHelper(nums, index + 1, prevValue, memo);

        int include = 0;
        if (nums[index] > prevValue) {
            include = 1 + lisMemoHelper(nums, index + 1, nums[index], memo);
        }

        int result = Math.max(exclude, include);

        memo.put(key, result);//set memo

        return result;
    }
}
