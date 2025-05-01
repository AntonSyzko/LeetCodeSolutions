package leet.code.solutions.dynamic_programming;

import java.nio.charset.MalformedInputException;

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
                maximumSumIncreasingSubsequence(nums, 0, Integer.MIN_VALUE, 0));
    }

    private static int maximumSumIncreasingSubsequence(int[] nums, int currIndex, int prev, int sum) {

        if(currIndex == nums.length){
            return sum;
        }

        int excludingCurrent = maximumSumIncreasingSubsequence(nums, currIndex + 1, prev, sum);

        int includingCurrentElement = sum;

        if(nums[currIndex] > prev){//the very current is MORE than previous

            includingCurrentElement = maximumSumIncreasingSubsequence(nums, currIndex + 1, nums[currIndex], sum + nums[currIndex]);

        }

        return Math.max(excludingCurrent, includingCurrentElement);
    }
}
