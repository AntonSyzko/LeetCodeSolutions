package leet.code.solutions.blind75;

import java.util.Arrays;

/*

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

        for (int i = 1; i < nums.length; i++) {//start from 1 !!!!!!!!!!!
            for (int j = 0; j < i; j++) {//traverse all

                if (nums[i] > nums[j]) {//if current is bigger than prev
                    //pick max of DP of curr OR any prev +1
                    LIS[i] = Math.max(LIS[i], LIS[j] + 1);//recurrence relation

                    maxLCSLengthRes = Math.max(maxLCSLengthRes, LIS[i]);//update res

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
