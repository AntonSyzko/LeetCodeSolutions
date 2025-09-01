package leet.code.solutions.blind75;

import java.util.Arrays;

/*
300

https://leetcode.com/problems/longest-increasing-subsequence/

Given an integer array nums, return the length of the longest strictly increasing subsequence.



Example 1:

Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Example 2:

Input: nums = [0,1,0,3,2,3]
Output: 4
Example 3:

Input: nums = [7,7,7,7,7,7,7]
Output: 1


Constraints:

1 <= nums.length <= 2500
-104 <= nums[i] <= 104


Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?

[10,9,2,5,3,7,101,18]

longest increasing subsequence = [2,3,7,101]
 */
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
       int[] nums = {10,9,2,5,3,7,101,18};
       int longestIncreasingSubsequenceLength = LCS(nums);
       System.out.println(longestIncreasingSubsequenceLength);
    }

    //time O(N^2)
    //space O(N) - dp array itself
    private static int LCS(int[] nums){
        int maxLCSLengthRes = 1;

        int[] LIS = new int[nums.length];//DP
        Arrays.fill(LIS, 1);//initialise with 1 as 1 is default length of subsequence of 1 element and is our base

        for (int outer = 1; outer < nums.length; outer++) {//start from 1 !!!!!!!!!!!
            for (int inner = 0; inner < outer; inner++) {//traverse all

                if (nums[outer] > nums[inner]) {//if current is bigger than prev
                    //pick max of DP of curr OR any prev +1
                    LIS[outer] = Math.max(LIS[outer], LIS[inner] + 1);//recurrence relation

                    maxLCSLengthRes = Math.max(maxLCSLengthRes, LIS[outer]);//update res

                }
            }
        }
        return maxLCSLengthRes;
    }


    //binary search
    private static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] tails = new int[nums.length];
        int size = 0;

        for (int each : nums) {
            int left = 0;
            int right = size;

            // Binary search to find the position to replace
            while (left < right) {

                int mid = left + (right - left) / 2;

                if (tails[mid] < each) {

                    left = mid + 1;

                } else {

                    right = mid;

                }
            }

            tails[left] = each;

            if (left == size) {
                size++;
            }
        }

        return size;
    }
}
//#medium
