package leet.code.solutions.dynamic_programming;

import java.util.HashMap;
import java.util.Map;

/*
https://www.techiedelight.com/increasing-subsequence-with-maximum-sum/

Find a subsequence of a given sequence such that the subsequence sum is as high as possible and the subsequence’s elements are sorted in ascending order.
 This subsequence is not necessarily contiguous or unique.

Please note that the problem specifically targets subsequences that need not be contiguous, i.e., subsequences are not required to occupy consecutive positions within the original sequences.

For example, consider subsequence {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11}.
The maximum sum increasing subsequence is {8, 12, 14} which has sum 34.
 */
public class IncreasingSubsequenceWithMaxSum {

    public static void main(String[] args) {
        int[] nums = { 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11 };

        System.out.println("The maximum sum of the increasing subsequence is " +
                maximumSumIncreasingSubsequence(nums));
    }

    // Main method to start the recursion
    public static int maximumSumIncreasingSubsequence(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int index = 0;
        int prevValue = Integer.MIN_VALUE;//dummy fake

        return msis(nums, index, prevValue);
    }


    private static int msis(int[] nums, int index, int prevValue) {

        // Base case: reached end of array
        if(index == nums.length){
            return 0;
        }

        // Option 1: Skip current element
        int sumExcludingCurrent = msis(nums, index + 1, prevValue);//prev stayed the same

        // Option 2: Include current element (only if it's greater than previous)
        int sumIncludingCurrentElement = 0;

        if(nums[index] > prevValue){//the very current is MORE than previous, cause ask is having em in strictly ASC order

            sumIncludingCurrentElement = nums[index] +  msis(nums, index + 1, nums[index]);//prev value passed as current

        }

        return Math.max(sumExcludingCurrent, sumIncludingCurrentElement);
    }



// ============================================================================
// MEMOIZED VERSION (More Efficient)
// ============================================================================
        private static int maximumSumIncreasingSubsequenceMemo(int[] nums) {
            if (nums == null || nums.length == 0) return 0;

            // Using HashMap for memoization since prevValue can be any integer
            Map<String, Integer> memo = new HashMap<>();
            int startIndex = 0;
            int prev = Integer.MIN_VALUE;

            return msisWithMemo(nums, startIndex, prev, memo);
        }

        private static int msisWithMemo(int[] nums, int index, int prevValue, Map<String, Integer> memo) {
            if (index == nums.length) {
                return 0;
            }

            // Create key for memoization
            String key = index + "," + prevValue;
            if (memo.containsKey(key)) {
                return memo.get(key);
            }

            // Skip current element, prev val stays same
            int excludeCurrent = msisWithMemo(nums, index + 1, prevValue, memo);

            // Include current element if possible
            int includeCurrent = 0;
            if (nums[index] > prevValue) {//strictly ASC order
                includeCurrent = nums[index] + msisWithMemo(nums, index + 1, nums[index], memo);//prev value passed as current
            }

            int result = Math.max(excludeCurrent, includeCurrent);

            memo.put(key, result);

            return result;
        }
}
