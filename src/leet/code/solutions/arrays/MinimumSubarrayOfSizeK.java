package leet.code.solutions.arrays;

import java.util.Arrays;

/*
https://www.techiedelight.com/find-minimum-sum-subarray-given-size-k/

Given an integer array, find the minimum sum subarray of size k, where k is a positive integer.

For example,

Input:  {10, 4, 2, 5, 6, 3, 8, 1},
 k = 3

 Output: Minimum sum subarray of size 3 is (1, 3)
 */
public class MinimumSubarrayOfSizeK {

    public static void main(String[] args) {
        int[] nums = {10, 4, 2, 5, 6, 3, 8, 1};
        int k = 3;
        int minSum = minSubArraySum(nums, k);
        System.out.println(minSum);

        int [] indexes = minSubArraySumIndexes(nums, k);
        System.out.println(Arrays.toString(indexes));
    }


    private static int minSubArraySum(int[] nums,  int k) {

        int minSum = Integer.MAX_VALUE;
        int ongoingSum = 0;
        int left = 0;


        for (int right = 0; right < nums.length; right++) {

                ongoingSum += nums[right];

                if(right - left == k - 1){//window length = right - left ( k is 1 based, not zero, hence -> -1)

                    minSum = Math.min(minSum, ongoingSum);//update min

                    ongoingSum = ongoingSum - nums[left];//shrink window

                    left++;//move left forward
                }

        }

        return minSum;
    }


    private static int[] minSubArraySumIndexes(int[] nums,  int k) {

        int minSum = Integer.MAX_VALUE;
        int ongoingSum = 0;
        int left = 0;

        int minStartIndex = 0;
        int minEndIndex = 0;

        for (int right = 0; right < nums.length; right++) {


            ongoingSum += nums[right];

            if(right - left == k-1){//window length = right - left ( k is 1 based, not zero, hence -> -1)

                if(ongoingSum < minSum){//update min sum
                    minSum = ongoingSum;
                    //save indexes
                    minStartIndex = left;
                    minEndIndex = right;
                }

                ongoingSum = ongoingSum - nums[left];//shrink window
                left++;//move left forward
            }

        }

        return new int[]{minStartIndex, minEndIndex};
    }
}
