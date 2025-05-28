package leet.code.solutions.arrays;

/*
https://leetcode.com/problems/longest-alternating-subarray/description/

You are given a 0-indexed integer array nums. A subarray s of length m is called alternating if:

m is greater than 1.
s1 = s0 + 1.
The 0-indexed subarray s looks like [s0, s1, s0, s1,...,s(m-1) % 2]. In other words, s1 - s0 = 1, s2 - s1 = -1, s3 - s2 = 1, s4 - s3 = -1, and so on up to s[m - 1] - s[m - 2] = (-1)m.
Return the maximum length of all alternating subarrays present in nums or -1 if no such subarray exists.

A subarray is a contiguous non-empty sequence of elements within an array.


Example 1:

Input: nums = [2,3,4,3,4]

Output: 4

Explanation:

The alternating subarrays are [2, 3], [3,4], [3,4,3], and [3,4,3,4]. The longest of these is [3,4,3,4], which is of length 4.

Example 2:

Input: nums = [4,5,6]

Output: 2

Explanation:

[4,5] and [5,6] are the only two alternating subarrays. They are both of length 2.

Constraints:

2 <= nums.length <= 100
1 <= nums[i] <= 104
 */
public class LongestAlternatingSubarray {

    public static void main(String[] args) {
        int[] nums = {2,3,4,3,4};
        int longestAlternatingSubarray = alternatingSubarray(nums);
        System.out.println(longestAlternatingSubarray);

    }

    /*
    Time Complexity:
        O(n), as we only need a single pass through the array.
    Space Complexity:
        O(1), using only a constant amount of extra space.
     */
    private static int alternatingSubarray(int[] nums) {

        if(nums.length == 0) return 0;

        if(nums.length == 1) return 1;

        int longest = Integer.MIN_VALUE;
        int currLen = 1;
        int expectedDiff = 1;// First diff should be 1

        for (int i = 1; i < nums.length; i++) {

            int prev = nums[i - 1];
            int curr = nums[i];

            int actualDiff = curr - prev;

            if(actualDiff == expectedDiff) {

                currLen++;
                // Flip the expected difference for the next iteration
                expectedDiff = -expectedDiff;

            }else if (actualDiff == 1) {
                // Start a new potential alternating subarray
                currLen = 2;
                expectedDiff = - 1;

            }else{
                // Neither continues an alternating subarray nor starts a new one
                currLen = 1;
                expectedDiff = 1;

            }

            // Update maxLength if the current alternating subarray has length > 1
            if (curr > 1) {
                longest = Math.max(longest, currLen);
            }

        }

        return longest;
    }
}
