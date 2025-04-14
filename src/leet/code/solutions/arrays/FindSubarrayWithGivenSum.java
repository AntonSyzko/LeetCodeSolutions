package leet.code.solutions.arrays;

import java.util.*;

/*
https://www.techiedelight.com/find-subarray-having-given-sum-given-array/

Given an integer array, find a subarray having a given sum in it.

For example,

Input:  nums[] = {2, 6, 0, 9, 7, 3, 1, 4, 1, 10},
target = 15
Output: {6, 0, 9}

 Input:  nums[] = {0, 5, -7, 1, -4, 7, 6, 1, 4, 1, 10},
 target = 15
 Output: {1, -4, 7, 6, 1, 4} or {4, 1, 10}

 Input:  nums[] = {0, 5, -7, 1, -4, 7, 6, 1, 4, 1, 10},
 target = -3
 Output: {1, -4} or {-7, 1, -4, 7}
 */
public class FindSubarrayWithGivenSum {

    public static void main(String[] args) {
         int[] nums = {2, 6, 0, 9, 7, 3, 1, 4, 1, 10};
         int target = 15;

         int[] res = findSubarrayWithSum(nums, target);
         System.out.println(Arrays.toString(res));
    }

    private static int[] findSubarrayWithSum(int[] nums, int target) {


        Map<Integer, Integer> prefixSumsToIndex = new HashMap<>();
        prefixSumsToIndex.put(0, -1);

        int ongoingSUm = 0;

        int left = 0;

        for (int right = 0; right < nums.length; right++) {

             ongoingSUm += nums[right];

            int complement = ongoingSUm - target;

            if(prefixSumsToIndex.containsKey(complement)){

                 left = prefixSumsToIndex.get(complement) + 1;

                 return Arrays.copyOfRange(nums, left, right + 1);

             }

             prefixSumsToIndex.put(ongoingSUm, right);

        }

        return new int[0];//no sum found
    }
}
