package leet.code.solutions.sliding_window;

/*
674
https://leetcode.com/problems/longest-continuous-increasing-subsequence/description/

Given an unsorted array of integers nums, return the length of the longest continuous increasing subsequence (i.e. subarray). The subsequence must be strictly increasing.

A continuous increasing subsequence is defined by two indices l and r (l < r) such that it is [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] and for each l <= i < r, nums[i] < nums[i + 1].

Example 1:

Input: nums = [1,3,5,4,7]
Output: 3
Explanation: The longest continuous increasing subsequence is [1,3,5] with length 3.
Even though [1,3,5,7] is an increasing subsequence, it is not continuous as elements 5 and 7 are separated by element
4.
Example 2:

Input: nums = [2,2,2,2,2]
Output: 1
Explanation: The longest continuous increasing subsequence is [2] with length 1. Note that it must be strictly
increasing.

Constraints:

1 <= nums.length <= 104
-109 <= nums[i] <= 109
 */
public class LongestContinuouslyIncreasingSubsequence {


    public static void main(String[] args) {
        int [] nums = {1,3,5,4,7,8,10};
        int lcis = findLengthOfLCIS(nums);
        System.out.println(lcis);
    }


    private static int findLengthOfLCIS(int[] nums) {
        if (nums.length ==0) return 0;
        if(nums.length ==1) return 1;

        int lcis = 1; // by default 1 char is by itself is a subsequence

        int left = 0;

        for(int right = 1 ; right < nums.length; right++){

            if(nums[right] > nums[right-1]){//increasing subsequence is GROWING

                lcis = Math.max(lcis, (right - left) + 1);

            }else{

                left = right;//nothing before can be LONGER than we got so far - so we reset start of new window to RIGHT(where first NON-GROWING occurred)

            }
        }

        return lcis;
    }
}
